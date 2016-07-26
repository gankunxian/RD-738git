<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加系统参数数据</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/js/jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}/js/jquery/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}/js/jquery/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/icon.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/common.css">
<script type="text/javascript"
	src="${basePath}/js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="${basePath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${basePath}/js/jquery/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${basePath}/js/validate.js"></script>
<style type="text/css">
.rt {
	text-align: right !important;
}
.rt2{
    padding-left:40px;
    width:auto;
}
</style>
<script type="text/javascript">
	$(function() {
		$("#returnParameter").click(function(){
			location.href="${basePath}/jsp/parameter/listParameter.jsp";
		});
		$(":submit").click(function() {
			if (!$("#ff").form('validate')) {
				return false;
			}
			$.post("${basePath}/admin/parameter/addParameter",{
				parameterName:$("#parameterName").val(),
				remark:$("#remark").val()
			},function(data, textStatus) {
						if (data.success) {
							$.messager.alert('操作提示', "添加系统参数信息成功", 'info');
							$("#returnParameter").trigger("click");
						} else if (data.message) {
							$.messager.alert('操作提示', data.message, 'info');
						} else {
							$.messager.alert('操作提示', "添加系统参数信息异常，请联系相关技术参数", 'error');
						}
					}, "json");
					return false;
				});
	});
</script>
<style type="text/css">
.combo input[type="text"] {
	width: 150px !important;
}
</style>
</head>
<body class="easyui-layout">
<div region="center" title="添加系统参数" iconCls="icon-add">
<form id="ff" style="border-bottom: 1px solid #99BBE8;background-color:#ffffff" action="">
<table style="margin-top:20px;width:100%;background-color:#E5EEEA;" cellpadding="0" cellspacing="1">
<tr>
  <td class="rt">系统参数名称：</td>
  <td class="rt2">
  <input class="easyui-validatebox formtext" required="true" id="parameterName" name="parameterName" type="text"  maxlength="20"> *最大20个字符
  </td>
</tr>
<tr>
  <td class="rt">备注：</td>
  <td class="rt2">
  <input class="easyui-validatebox formtext" id="remark" name="remark" type="text"  maxlength="20">*最大20个字符
  </td>
</tr>
<tr>
  <td colspan="2" style="padding-left: 200px;padding-top: 15px;padding-bottom:10px"><input class="formbutton" style="margin-right:30px" type="submit" value="提交"> <input class="formbutton" type="button"  id="returnParameter" value="返回"></td>
</tr>
</table></form>
</div>
</body>
</html>