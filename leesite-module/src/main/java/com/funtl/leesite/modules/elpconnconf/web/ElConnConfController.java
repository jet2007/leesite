/*
 * Copyright 2015-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.funtl.leesite.modules.elpconnconf.web;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;

import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.funtl.leesite.common.utils.DateUtils;
import com.funtl.leesite.common.utils.MyBeanUtils;
import com.funtl.leesite.common.config.Global;
import com.funtl.leesite.common.persistence.Page;
import com.funtl.leesite.common.web.BaseController;
import com.funtl.leesite.common.utils.StringUtils;
import com.funtl.leesite.common.utils.excel.ExportExcel;
import com.funtl.leesite.common.utils.excel.ImportExcel;
import com.funtl.leesite.modules.elpconnconf.entity.ElConnConf;
import com.funtl.leesite.modules.elpconnconf.service.ElConnConfService;

/**
 * 链接基础配置Controller
 * @author JET
 * @version 2018-07-19
 */
@Controller
@RequestMapping(value = "${adminPath}/elpconnconf/elConnConf")
public class ElConnConfController extends BaseController {

	@Autowired
	private ElConnConfService elConnConfService;
	
	@ModelAttribute
	public ElConnConf get(@RequestParam(required=false) String id) {
		ElConnConf entity = null;
		if (StringUtils.isNotBlank(id)){
			entity = elConnConfService.get(id);
		}
		if (entity == null){
			entity = new ElConnConf();
		}
		return entity;
	}
	
	/**
	 * 链接基础配置列表页面
	 */
	@RequiresPermissions("elpconnconf:elConnConf:list")
	@RequestMapping(value = {"list", ""})
	public String list(ElConnConf elConnConf, HttpServletRequest request, HttpServletResponse response, Model model) {
		Page<ElConnConf> page = elConnConfService.findPage(new Page<ElConnConf>(request, response), elConnConf); 
		model.addAttribute("page", page);
		return "modules/elpconnconf/elConnConfList";
	}

	/**
	 * 查看，增加，编辑链接基础配置表单页面
	 */
	@RequiresPermissions(value={"elpconnconf:elConnConf:view","elpconnconf:elConnConf:add","elpconnconf:elConnConf:edit"},logical=Logical.OR)
	@RequestMapping(value = "form")
	public String form(ElConnConf elConnConf, Model model) {
		model.addAttribute("elConnConf", elConnConf);
		return "modules/elpconnconf/elConnConfForm";
	}

	/**
	 * 保存链接基础配置
	 */
	@RequiresPermissions(value={"elpconnconf:elConnConf:add","elpconnconf:elConnConf:edit"},logical=Logical.OR)
	@RequestMapping(value = "save")
	public String save(ElConnConf elConnConf, Model model, RedirectAttributes redirectAttributes) throws Exception{
		if (!beanValidator(model, elConnConf)){
			return form(elConnConf, model);
		}
		if(!elConnConf.getIsNewRecord()){//编辑表单保存
			ElConnConf t = elConnConfService.get(elConnConf.getId());//从数据库取出记录的值
			MyBeanUtils.copyBeanNotNull2Bean(elConnConf, t);//将编辑表单中的非NULL值覆盖数据库记录中的值
			elConnConfService.save(t);//保存
		}else{//新增表单保存
			elConnConfService.save(elConnConf);//保存
		}
		addMessage(redirectAttributes, "保存链接基础配置成功");
		return "redirect:"+Global.getAdminPath()+"/elpconnconf/elConnConf";
	}
	
	/**
	 * 删除链接基础配置
	 */
	@RequiresPermissions("elpconnconf:elConnConf:del")
	@RequestMapping(value = "delete")
	public String delete(ElConnConf elConnConf, RedirectAttributes redirectAttributes) {
		elConnConfService.delete(elConnConf);
		addMessage(redirectAttributes, "删除链接基础配置成功");
		return "redirect:"+Global.getAdminPath()+"/elpconnconf/elConnConf";
	}
	
	/**
	 * 批量删除链接基础配置
	 */
	@RequiresPermissions("elpconnconf:elConnConf:del")
	@RequestMapping(value = "deleteAll")
	public String deleteAll(String ids, RedirectAttributes redirectAttributes) {
		String idArray[] =ids.split(",");
		for(String id : idArray){
			elConnConfService.delete(elConnConfService.get(id));
		}
		addMessage(redirectAttributes, "删除链接基础配置成功");
		return "redirect:"+Global.getAdminPath()+"/elpconnconf/elConnConf";
	}
	
	/**
	 * 导出excel文件
	 */
	@RequiresPermissions("elpconnconf:elConnConf:export")
    @RequestMapping(value = "export", method=RequestMethod.POST)
    public String exportFile(ElConnConf elConnConf, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "链接基础配置"+DateUtils.getDate("yyyyMMddHHmmss")+".xlsx";
            Page<ElConnConf> page = elConnConfService.findPage(new Page<ElConnConf>(request, response, -1), elConnConf);
    		new ExportExcel("链接基础配置", ElConnConf.class).setDataList(page.getList()).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导出链接基础配置记录失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/elpconnconf/elConnConf";
    }

	/**
	 * 导入Excel数据
	 */
	@RequiresPermissions("elpconnconf:elConnConf:import")
    @RequestMapping(value = "import", method=RequestMethod.POST)
    public String importFile(MultipartFile file, RedirectAttributes redirectAttributes) {
		try {
			int successNum = 0;
			int failureNum = 0;
			StringBuilder failureMsg = new StringBuilder();
			ImportExcel ei = new ImportExcel(file, 1, 0);
			List<ElConnConf> list = ei.getDataList(ElConnConf.class);
			for (ElConnConf elConnConf : list){
				try{
					elConnConfService.save(elConnConf);
					successNum++;
				}catch(ConstraintViolationException ex){
					failureNum++;
				}catch (Exception ex) {
					failureNum++;
				}
			}
			if (failureNum>0){
				failureMsg.insert(0, "，失败 "+failureNum+" 条链接基础配置记录。");
			}
			addMessage(redirectAttributes, "已成功导入 "+successNum+" 条链接基础配置记录"+failureMsg);
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入链接基础配置失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/elpconnconf/elConnConf";
    }
	
	/**
	 * 下载导入链接基础配置数据模板
	 */
	@RequiresPermissions("elpconnconf:elConnConf:import")
    @RequestMapping(value = "import/template")
    public String importFileTemplate(HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
            String fileName = "链接基础配置数据导入模板.xlsx";
    		List<ElConnConf> list = Lists.newArrayList(); 
    		new ExportExcel("链接基础配置数据", ElConnConf.class, 1).setDataList(list).write(response, fileName).dispose();
    		return null;
		} catch (Exception e) {
			addMessage(redirectAttributes, "导入模板下载失败！失败信息："+e.getMessage());
		}
		return "redirect:"+Global.getAdminPath()+"/elpconnconf/elConnConf";
    }
	
	
	

}