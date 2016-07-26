<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改用户</title>
		<link rel="stylesheet" type="text/css"
			href="${basePath}/js/jquery/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="${basePath}/js/jquery/themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="${basePath}/js/jquery/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}/css/icon.css">
		<link rel="stylesheet" type="text/css"
			href="${basePath}/css/common.css">
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

.rt2 {
	padding-left: 40px;
	width: auto;
}
</style>
		<script type="text/javascript">
			$(function() {
				$("#returnUser").click(function() {
					location.href = "${basePath}/jsp/user/listUser.jsp";
				});
				$(":submit").click(function() {
					if (!$("#ff").form('validate')) {
						return false;
				}
		
			$.post("${basePath}/admin/user/modifyUser", {
					name : $("#name").val(),
					account : $("#account").val(),
					nickname : $("#nickname").val(),
					regTime : $("#regTime").val(),
					groupId: $("#groupSelect").val(),
					password: $("#password").val(),
					phone: $("#phone").val(),
					userId :$("#userId").val()
			}, function(data, textStatus) {
					if (data.success) {
						$.messager.alert('操作提示', "更新用户信息成功", 'info');
						$("#returnUser").trigger("click");
					} else if (data.message) {
						$.messager.alert('操作提示', data.message, 'info');
					} else {
						$.messager.alert('操作提示', "更新用户信息异常，请联系相关技术人员", 'error');
					}
				}, "json");
					return false;
				});
			});
		</script>
	</head>
	<body class="easyui-layout">
		<div region="center" title="编辑用户" iconCls="icon-mod">
			<form id="ff"
				style="border-bottom: 1px solid #99BBE8; background-color: #ffffff"
				action="">
				<input id="userId" name="userId" value="${user.userId}" type="hidden" />
				<input id="regTime" name="regTime" value="${user.regTime}" type="hidden" />
				<input id="password" name="password" value="${user.password}" type="hidden" />
				<table
					style="margin-top: 20px; width: 100%; background-color: #E5EEEA;"
					cellpadding="0" cellspacing="1">
					<tr>
						<td class="rt">
							用户姓名：
						</td>
						<td class="rt2">
							<input class="easyui-validatebox formtext" required="true"
								id="name" name="name" value="${user.name}" type="text" maxlength="20"
								validType="name">
							*最大20个字符
						</td>
					</tr>
					<tr>
						<td class="rt">
							登录账号：
						</td>
						<td class="rt2">
							<input class="easyui-validatebox formtext" readOnly
								required="true" value="${user.account}" id="account" name="account" type="text"
								validType="username" maxlength="20">
							*字母开头，允许6-16字节，允许字母数字下划线
						</td>
					</tr>
					<tr>
						<td class="rt">
							用户昵称：
						</td>
						<td class="rt2">
							<input class="easyui-validatebox formtext" required="true"
								id="nickname" value="${user.nickname}" name="nickname" type="text" maxlength="20"
								validType="name">
							*最大20个字符
						</td>
					</tr>
					<tr>
					  <td class="rt">手机号码：</td>
					  <td class="rt2">
					  <input class="easyui-validatebox formtext" value="${user.phone}" required="true" id="phone" name="phone" type="text" validType="mobile"  maxlength="20"> *手机号码格式不正确
					  </td>
					</tr>
					<tr>
						<td class="rt">
							所属分组:
						</td>
						<td class="rt2">
							<select class="easyui-validatebox formtext" name="groupSelect" id="groupSelect">
								<option value="">
									--请选择--
								</option>
								<c:forEach var="group" items="${groupList}">
									<option value="${group.groupId}" <c:if test="${group.groupId==user.group.groupId}" >selected</c:if> >
										${group.groupName}
									</option>
								</c:forEach>
							</select>
						</td>
					</tr>
					<tr>
						<td colspan="2"
							style="padding-left: 200px; padding-top: 15px; padding-bottom: 10px">
							<input class="formbutton" style="margin-right: 30px"
								type="submit" value="提交">
							<input class="formbutton" type="button" id="returnUser"
								value="返回">
						</td>
					</tr>

				</table>
			</form>
		</div>
	</body>
</html>