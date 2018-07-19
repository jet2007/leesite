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
package com.funtl.leesite.modules.elpconnconf.dao;

import com.funtl.leesite.common.persistence.CrudDao;
import com.funtl.leesite.common.persistence.annotation.MyBatisDao;
import com.funtl.leesite.modules.elpconnconf.entity.ElConnConf;

/**
 * 链接基础配置DAO接口
 * @author JET
 * @version 2018-07-19
 */
@MyBatisDao
public interface ElConnConfDao extends CrudDao<ElConnConf> {

	
}