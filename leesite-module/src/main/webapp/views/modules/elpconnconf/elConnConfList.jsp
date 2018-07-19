<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/views/include/taglib.jsp" %>

<!DOCTYPE html>
<!--[if IE 8]> <html lang="zh" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="zh" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!-->
<html>
<!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
	<title>${fns:getConfig('productName')} | 链接基础配置</title>
    <meta name="decorator" content="default"/>
</head>

<body class="page-container-bg-solid page-header-fixed page-sidebar-closed-hide-logo">
<div class="page-container">
	<div class="page-sidebar-wrapper">
        <div class="page-sidebar navbar-collapse collapse">
            <ul class="page-sidebar-menu" data-keep-expanded="false" data-auto-scroll="true" data-slide-speed="200">
                <li class="heading">
                    <h3 class="uppercase">功能菜单</h3>
                </li>
                <t:menu menu="${fns:getTopMenu()}" parentName="链接基础配置" currentName="链接基础配置"></t:menu>
            </ul>
        </div>
    </div>

    <div class="page-content-wrapper">
        <div class="page-content" style="padding-top: 0;">
            <div class="row">
                <div class="col-md-12">
                    <div class="portlet light">
                        <div class="portlet-title">
                            <div class="caption">
                                <span class="caption-subject bold font-grey-gallery uppercase"> 链接基础配置 </span>
                                <span class="caption-helper"></span>
                            </div>
                            <div class="tools">
                                <a href="" class="fullscreen"> </a>
                            </div>
                        </div>
                        <div class="portlet-body">
                            <sys:message content="${message}"/>
                            <div class="row" style="margin-bottom: 20px;">
                                <div class="col-md-12">
									<form:form id="searchForm" modelAttribute="elConnConf" action="${ctx}/elpconnconf/elConnConf" method="post" class="form-inline">
										<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
										<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
										<table:sortColumn id="orderBy" name="orderBy" value="${page.orderBy}" callback="sortOrRefresh();"/><!-- 支持排序 -->
										<div class="form-group">
                                            		<label>数据库类型：</label>
														<form:select path="connServerType"  class="form-control input-sm">
															<form:option value="" label=""/>
															<form:options items="${fns:getDictList('ELP链接类型')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
														</form:select>
                                            		<label>是否生效：</label>
														<form:select path="connIdValid"  class="form-control input-sm">
															<form:option value="" label=""/>
															<form:options items="${fns:getDictList('ELP是否生效')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
														</form:select>
                                        </div>
									</form:form>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="pull-left">
										<shiro:hasPermission name="elpconnconf:elConnConf:add">
											<table:addRow url="${ctx}/elpconnconf/elConnConf/form" title="链接基础配置"></table:addRow><!-- 增加按钮 -->
										</shiro:hasPermission>
										<shiro:hasPermission name="elpconnconf:elConnConf:edit">
											<table:editRow url="${ctx}/elpconnconf/elConnConf/form" title="链接基础配置" id="contentTable"></table:editRow><!-- 编辑按钮 -->
										</shiro:hasPermission>
										<shiro:hasPermission name="elpconnconf:elConnConf:del">
											<table:delRow url="${ctx}/elpconnconf/elConnConf/deleteAll" id="contentTable"></table:delRow><!-- 删除按钮 -->
										</shiro:hasPermission>
										<shiro:hasPermission name="elpconnconf:elConnConf:import">
											<table:importExcel url="${ctx}/elpconnconf/elConnConf/import"></table:importExcel><!-- 导入按钮 -->
										</shiro:hasPermission>
										<shiro:hasPermission name="elpconnconf:elConnConf:export">
											<table:exportExcel url="${ctx}/elpconnconf/elConnConf/export"></table:exportExcel><!-- 导出按钮 -->
										</shiro:hasPermission>
                                        <button class="btn btn-default btn-sm" onclick="sortOrRefresh()" title="刷新"><i class="fa fa-refresh"></i> 刷新</button>
                                    </div>
                                    <div class="pull-right">
                                        <button class="btn btn-primary btn-sm" onclick="search()"><i class="fa fa-search"></i> 查询</button>
                                        <button class="btn btn-primary btn-sm" onclick="reset()"><i class="fa fa-refresh"></i> 重置</button>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <div class="col-md-12">
                                    <div class="table-scrollable">
                                        <table id="contentTable" class="table table-striped table-bordered table-hover table-checkable">
											<thead>
												<tr style="cursor: pointer">
													<th>
														<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
															<input type="checkbox" class="group-checkable" data-set=".checkboxes" />
															<span></span>
														</label>
													</th>
													<th class="sort-column connServerType">数据库类型</th>
													<th class="sort-column connServerCategory">类别</th>
													<th class="sort-column connIdValid">是否生效</th>
													<th class="sort-column connUrl">URL</th>
													<th class="sort-column connDriver">DRIVER</th>
													<th class="sort-column defaultValJson">默认值JSON格式</th>
													<th>操作</th>
												</tr>
											</thead>
											<tbody>
											<c:forEach items="${page.list}" var="elConnConf">
												<tr>
													<td>
														<label class="mt-checkbox mt-checkbox-single mt-checkbox-outline">
                                                            <input type="checkbox" class="checkboxes i-checks" id="${elConnConf.id}" />
                                                            <span></span>
                                                        </label>
													</td>
													<td><a  href="#" onclick="openDialogView('查看链接基础配置', '${ctx}/elpconnconf/elConnConf/form?id=${elConnConf.id}','900px', '600px')">
														${fns:getDictLabel(elConnConf.connServerType, 'ELP链接类型', '')}
													</a></td>
													<td>
														${fns:getDictLabel(elConnConf.connServerCategory, 'ELP链接类别', '')}
													</td>
													<td>
														${fns:getDictLabel(elConnConf.connIdValid, 'ELP是否生效', '')}
													</td>
													<td>
														${elConnConf.connUrl}
													</td>
													<td>
														${elConnConf.connDriver}
													</td>
													<td>
														${elConnConf.defaultValJson}
													</td>
													<td>
														<shiro:hasPermission name="elpconnconf:elConnConf:view">
															<a href="#" onclick="openDialogView('查看链接基础配置', '${ctx}/elpconnconf/elConnConf/form?id=${elConnConf.id}','900px', '600px')" class="btn btn-info btn-xs" ><i class="fa fa-search-plus"></i> 查看</a>
														</shiro:hasPermission>
														<shiro:hasPermission name="elpconnconf:elConnConf:edit">
															<a href="#" onclick="openDialog('修改链接基础配置', '${ctx}/elpconnconf/elConnConf/form?id=${elConnConf.id}','900px', '600px')" class="btn btn-success btn-xs" ><i class="fa fa-edit"></i> 修改</a>
														</shiro:hasPermission>
														<shiro:hasPermission name="elpconnconf:elConnConf:del">
															<a href="${ctx}/elpconnconf/elConnConf/delete?id=${elConnConf.id}" onclick="return confirmx('确认要删除该链接基础配置吗？', this.href)" class="btn btn-danger btn-xs"><i class="fa fa-trash"></i> 删除</a>
														</shiro:hasPermission>
													</td>
												</tr>
											</c:forEach>
											</tbody>
										</table>
                                    </div>
                                    <table:page page="${page}"></table:page>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<%@ include file="/views/include/foot.jsp" %>
<script type="text/javascript">
	$(document).ready(function() {
	});
</script>
</body>
</html>