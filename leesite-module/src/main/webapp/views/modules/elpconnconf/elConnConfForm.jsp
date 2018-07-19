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
    <meta name="decorator" content="form"/>

</head>
<body style="background: white;">
<div class="form-body">
	<form:form id="inputForm" modelAttribute="elConnConf" action="${ctx}/elpconnconf/elConnConf/save" method="post" class="form-horizontal" cssStyle="padding: 5px;">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>

		<table class="table table-striped table-bordered table-hover">
			<tbody>
				<tr>
					<td class="active" style="width: 15%;"><label class="pull-right"><span style="color: #E7505A;"> * </span>数据库类型：</label></td>
					<td style="width: 35%;">
						<form:select path="connServerType" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('ELP链接类型')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="active" style="width: 15%;"><label class="pull-right"><span style="color: #E7505A;"> * </span>类别：</label></td>
					<td style="width: 35%;">
						<form:select path="connServerCategory" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('ELP链接类别')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
				</tr>
				<tr>
					<td class="active" style="width: 15%;"><label class="pull-right"><span style="color: #E7505A;"> * </span>是否生效：</label></td>
					<td style="width: 35%;">
						<form:select path="connIdValid" class="form-control required">
							<form:option value="" label=""/>
							<form:options items="${fns:getDictList('ELP是否生效')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
						</form:select>
					</td>
					<td class="active" style="width: 15%;"><label class="pull-right">URL：</label></td>
					<td style="width: 35%;">
						<form:input path="connUrl" htmlEscape="false" maxlength="500" class="form-control "/>
					</td>
				</tr>
				<tr>
					<td class="active" style="width: 15%;"><label class="pull-right">DRIVER：</label></td>
					<td style="width: 35%;">
						<form:input path="connDriver" htmlEscape="false" maxlength="500" class="form-control "/>
					</td>
					<td class="active" style="width: 15%;"><label class="pull-right">默认值JSON格式：</label></td>
					<td style="width: 35%;">
						<form:textarea path="defaultValJson" htmlEscape="false" rows="3" class="form-control "/>
					</td>
				</tr>
			</tbody>
		</table>
	</form:form>
</div>

<%@include file="/views/include/foot.jsp" %>
<script type="text/javascript">
	var validateForm;
	function doSubmit(){//回调函数，在编辑和保存动作时，供openDialog调用提交表单。
	  if(validateForm.form()){
		  $("#inputForm").submit();
		  return true;
	  }

	  return false;
	}

	$(document).ready(function() {
		validateForm = $("#inputForm").validate({
			errorElement: 'span',
            errorClass: 'help-inline border-red font-red',
            focusInvalid: false,
            errorContainer: "#messageBox",

			submitHandler: function(form){
				loading('正在提交，请稍等...');
				form.submit();
			},

			errorPlacement: function(error, element) {
				$("#messageBox").text("输入有误，请先更正。");
				if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
					error.appendTo(element.parent().parent());
				} else {
					error.insertAfter(element);
				}
			}
		});

	});
</script>
</body>
</html>