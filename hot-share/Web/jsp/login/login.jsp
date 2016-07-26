<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
	isELIgnored="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>英飞拓民用安防后台管理系统-登录页面</title>
<link rel="stylesheet" type="text/css" href="${basePath}/css/icon.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/common.css">
<link rel="stylesheet" type="text/css" href="${basePath}/js/jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css" href="${basePath}/js/jquery/themes/gray/easyui.css">
<script type="text/javascript" src="${basePath}/js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript" src="${basePath}/js/jquery/jquery.md5.js"></script>
<style type="text/css">
form,div {
	background-color: transparent !important;
	text-align: center;
}

form div {
	text-align: left !important;
	margin-bottom: 0px;
}

.formtext {
	height: 38px !important;
	*line-height:38px !important;
	background: #DEE5ED;
	color: #0d2134;
	border: none;
	font-size: 24px;
}
</style>
<script type="text/javascript">
	if (window != top) {
		top.location.href = location.href;
	}

	var random;
	$(function(){
		$("#account").focus();
		$("#changeVerifyCode").click(function() {
			ChangeVerifyCode();
		});
	}).keydown(function(event) {
		if (event.keyCode == 13) {
			//$("#login").click();
		}
	});

	function checkLoginForm() {
		if ($("#account").val() == "") {
			$.messager.alert('操作提示', "请输入用户名！", 'info');
			$("#account").focus();
			return false;
		}
		if ($("#password").val() == "") {
			$.messager.alert('操作提示', "请输入密码！", 'info');
			$("#password").focus();
			return false;
		}
		if ($("#verifyCode").val() == "") {
			$.messager.alert('操作提示',"请输入验证码！", 'info');
			$("#verifyCode").focus();
			return false;
		}
		
		$.ajax({
		   	url:'${basePath}/admin/manager/getRandom?account='+$("#account").val(),
		   	type:"post",
		   	dataType:'json',
		   	async:false,
		   	success:function(data){
		   		random = data.message;
		   	},
		   	error:function(){
		   		$.messager.alert('操作提示',"系统错误！", 'info');
		   	}
		});
		$("#password").val($.md5($.md5($.md5($("#password").val())+random)+$("#account").val()));
		return true;
	}
	function resetLoginForm() {
		$("#account").val('');
		$("#password").val('');
		$("#verifyCode").val('');
		return false;
	}

	function ChangeVerifyCode() {
		$("#changeVerifyCode").attr("src",
				"${basePath}/image/VerifyCodeImage?" + Math.random());
		$("#verifyCode").val('');
	}
</script>
</head>

<body style="background:url(${basePath}/images/login/background.jpg)">
	<form id="loginForm" action="${basePath}/admin/manager/login"
		method="post" name="loginForm">
		<div style="width: 913px; margin: auto; margin-top: 160px">
			<div
				style="background:url(${basePath}/images/login/bj_2.jpg) no-repeat;position:relative">
				<div style="padding-top: 75px;padding-left: 480px;margin-bottom:0;height:auto"><input class="formtext" type="text" id="account"
					name="account" /></div>
					<div style="margin-top: 12px;padding-left: 480px;margin-bottom:0"><input class="formtext" 
					type="password" id="password" name="password" /></div>
					<div style="margin-top: 13px;padding-left: 480px;*margin-top:13px;margin-bottom:0"><input class="formtext"
					type="text" id="verifyCode"
					name="verifyCode" style="margin-right:20px;width:150px;"/><label style="display:inline-block;vertical-align:top;"><img src="${basePath}/image/VerifyCodeImage" id="changeVerifyCode" name="changeVerifyCode"/></label></div>
					<div style="margin-left: 400px; margin-top: 60px;margin-bottom:0"><input
					type="image"
					src="${basePath}/images/login/login.png" id="login"
					onclick="return checkLoginForm();"></div>
			</div>
		</div>
		<c:if test="${manager_info !=null}">
			<%
				response.sendRedirect(request.getAttribute("basePath")+"/admin/main/index");
			%>
		</c:if>
		<c:if test="${not empty R_String_message}">
			<script>
				$.messager.alert('操作提示', '${R_String_message}', 'info');
			</script>
		</c:if>
	</form>
</body>
</html>