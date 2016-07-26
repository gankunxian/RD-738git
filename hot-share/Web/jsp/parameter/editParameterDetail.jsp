<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改详细参数</title>
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
	$.ajax({
		url:"${basePath}/admin/parameter/parameterDetailInfo",
		type:"POST",
		data:{id:<%=request.getParameter("detailId")%>},
		dataType:"json",
		success:function(data, textStatus) {
			if (data.success) {
				$("#parameterId").val(data.data.parameter.parameterId);
				document.getElementById("parameterName").innerHTML = data.data.parameter.parameterName;
				$("#detailName").val(data.data.detailName);
				$("#sort").val(data.data.sort);
				$("#remark").val(data.data.remark);
				$("#createTime").val(data.data.createTime);
				$("#createPerson").val(data.data.createPerson);
				$("#oldName").val(data.data.detailName);
			}else {
				$.messager.alert('操作提示', "加载详细参数信息异常，请联系相关技术人员", 'info');
				$("#returnParameterDetail").trigger("click");
			}
		},
	    error:function(){
	    	$.messager.alert('操作提示', "加载详细参数信息异常，请联系相关技术人员", 'info');
	    	$("#returnParameterDetail").trigger("click");
	    }
	});
	$("#returnParameterDetail").click(function(){
		var parameterId = $('#parameterId').val();
		location.href="${basePath}/admin/parameter/toListParameterDetail?parameterId="+parameterId;
	});
	$(":submit").click(function() {
		if (!$("#ff").form('validate')) {
			return false;
		}
		
		$.post("${basePath}/admin/parameter/modifyParameterDetail",{
			parameterId:$("#parameterId").val(),
			detailName:$("#detailName").val(),
			remark:$("#remark").val(),
			createTime:$("#createTime").val(),
			createPerson:$("#createPerson").val(),
			sort:$("#sort").val(),
			oldName:$("#oldName").val(),
			detailId:<%=request.getParameter("detailId")%>
		},function(data, textStatus) {
					if (data.success) {
						$.messager.alert('操作提示', "更新详细参数信息成功", 'info');
						$("#returnParameterDetail").trigger("click");
					} else if (data.message) {
						$.messager.alert('操作提示', data.message, 'info');
					} else {
						$.messager.alert('操作提示', "更新详细参数信息异常，请联系相关技术人员", 'error');
					}
				}, "json");
				return false;
			});
});
</script>
</head>
<body class="easyui-layout">
<div region="center" title="编辑详细参数" iconCls="icon-mod">
<form id="ff" style="border-bottom: 1px solid #99BBE8;background-color:#ffffff" action="">
<input id="parameterId" name="parameterId" type="hidden"/>
<input id="createTime" name="createTime" type="hidden"/>
<input id="createPerson" name="createPerson" type="hidden"/>
<input id="oldName" name="oldName" type="hidden" />
<table style="margin-top:20px;width:100%;background-color:#E5EEEA;" cellpadding="0" cellspacing="1">
<tr>
  <td class="rt">参数名称：</td>
  <td class="rt2">
  <label id="parameterName"></label>
  </td>
</tr>
<tr>
  <td class="rt">详细参数名称：</td>
  <td class="rt2">
  <input class="easyui-validatebox formtext" required="true" id="detailName" name="detailName" type="text"  maxlength="20"> *最大20个字符
  </td>
</tr>
<tr>
  <td class="rt">排序：</td>
  <td class="rt2">
  <input class="easyui-validatebox formtext" required="true" onkeyup="this.value=this.value.replace(/\D/g,'')" id="sort" name="sort" type="text"  maxlength="3">*请填写数字 
  </td>
</tr>
<tr>
  <td class="rt">备注：</td>
  <td class="rt2">
  <input class="easyui-validatebox formtext" id="remark" name="remark" type="text"  maxlength="20"> *最大20个字符
  </td>
</tr>
<tr>
  <td colspan="2" style="padding-left: 200px;padding-top: 15px;padding-bottom:10px"><input class="formbutton" style="margin-right:30px" type="submit" value="提交"> <input class="formbutton" type="button"  id="returnParameterDetail" value="返回"></td>
</tr>
</table></form>
</div>
</body>
</html>