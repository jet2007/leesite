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
package com.funtl.leesite.modules.elpconnconf.entity;

import org.hibernate.validator.constraints.Length;

import com.funtl.leesite.common.persistence.DataEntity;
import com.funtl.leesite.common.utils.excel.annotation.ExcelField;

/**
 * 链接基础配置Entity
 * @author JET
 * @version 2018-07-19
 */
public class ElConnConf extends DataEntity<ElConnConf> {
	
	private static final long serialVersionUID = 1L;
	private String connServerType;		// 数据库类型
	private String connServerCategory;		// 类别
	private String connIdValid;		// 是否生效
	private String connUrl;		// URL
	private String connDriver;		// DRIVER
	private String defaultValJson;		// 默认值JSON格式
	
	public ElConnConf() {
		super();
	}

	public ElConnConf(String id){
		super(id);
	}

	@Length(min=1, max=100, message="数据库类型长度必须介于 1 和 100 之间")
	@ExcelField(title="数据库类型", dictType="ELP链接类型", align=2, sort=0)
	public String getConnServerType() {
		return connServerType;
	}

	public void setConnServerType(String connServerType) {
		this.connServerType = connServerType;
	}
	
	@Length(min=1, max=100, message="类别长度必须介于 1 和 100 之间")
	@ExcelField(title="类别", dictType="ELP链接类别", align=2, sort=1)
	public String getConnServerCategory() {
		return connServerCategory;
	}

	public void setConnServerCategory(String connServerCategory) {
		this.connServerCategory = connServerCategory;
	}
	
	@Length(min=1, max=1, message="是否生效长度必须介于 1 和 1 之间")
	@ExcelField(title="是否生效", dictType="ELP是否生效", align=2, sort=2)
	public String getConnIdValid() {
		return connIdValid;
	}

	public void setConnIdValid(String connIdValid) {
		this.connIdValid = connIdValid;
	}
	
	@Length(min=0, max=500, message="URL长度必须介于 0 和 500 之间")
	@ExcelField(title="URL", align=2, sort=3)
	public String getConnUrl() {
		return connUrl;
	}

	public void setConnUrl(String connUrl) {
		this.connUrl = connUrl;
	}
	
	@Length(min=0, max=500, message="DRIVER长度必须介于 0 和 500 之间")
	@ExcelField(title="DRIVER", align=2, sort=4)
	public String getConnDriver() {
		return connDriver;
	}

	public void setConnDriver(String connDriver) {
		this.connDriver = connDriver;
	}
	
	@ExcelField(title="默认值JSON格式", align=2, sort=5)
	public String getDefaultValJson() {
		return defaultValJson;
	}

	public void setDefaultValJson(String defaultValJson) {
		this.defaultValJson = defaultValJson;
	}
	
}