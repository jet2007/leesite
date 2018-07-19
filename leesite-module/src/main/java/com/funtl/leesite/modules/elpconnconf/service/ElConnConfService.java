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
package com.funtl.leesite.modules.elpconnconf.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funtl.leesite.common.persistence.Page;
import com.funtl.leesite.common.service.CrudService;
import com.funtl.leesite.modules.elpconnconf.entity.ElConnConf;
import com.funtl.leesite.modules.elpconnconf.dao.ElConnConfDao;

/**
 * 链接基础配置Service
 * @author JET
 * @version 2018-07-19
 */
@Service
@Transactional(readOnly = true)
public class ElConnConfService extends CrudService<ElConnConfDao, ElConnConf> {

	public ElConnConf get(String id) {
		return super.get(id);
	}
	
	public List<ElConnConf> findList(ElConnConf elConnConf) {
		return super.findList(elConnConf);
	}
	
	public Page<ElConnConf> findPage(Page<ElConnConf> page, ElConnConf elConnConf) {
		return super.findPage(page, elConnConf);
	}
	
	@Transactional(readOnly = false)
	public void save(ElConnConf elConnConf) {
		super.save(elConnConf);
	}
	
	@Transactional(readOnly = false)
	public void delete(ElConnConf elConnConf) {
		super.delete(elConnConf);
	}
	
	
	
	
}