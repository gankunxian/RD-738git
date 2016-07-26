<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>修改系统人员</title>
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
		$.ajax({
			url : "${basePath}/admin/manager/managerInfo",
			type : "POST",
			data : {
				id :'${manager.managerId}'
	},
			dataType : "json",
			success : function(data, textStatus) {
				if (data.success) {
					$("#name").val(data.data.name);
					$("#account").val(data.data.account);
					$("#password").val(data.data.password);
					$("#createTime").val(data.data.createTime);
				} else {
					$.messager.alert('操作提示', "加载系统人员信息异常，请联系相关技术人员", 'info');
					$("#returnManager").trigger("click");
				}
			},
			error : function() {
				$.messager.alert('操作提示', "加载系统人员信息异常，请联系相关技术人员", 'info');
				$("#returnManager").trigger("click");
			}
		});
		$("#returnManager").click(function() {
			location.href = "${basePath}/jsp/manager/listManager.jsp";
		});
		$(":submit").click(function() {
			if (!$("#ff").form('validate')) {
				return false;
			}

			$.post("${basePath}/admin/manager/modifyManager ", {
				name : $("#name").val(),
				account : $("#account").val(),
				password : $("#password").val(),
				createTime : $("#createTime").val(),
				roleID: $("#roleSelect").val(),
				groupId: $("#groupSelect").val(),
				managerId :'${manager.managerId}'
	}, function(data, textStatus) {
				if (data.success) {
					$.messager.alert('操作提示', "更新系统人员信息成功", 'info');
					$("#returnManager").trigger("click");
				} else if (data.message) {
					$.messager.alert('操作提示', data.message, 'info');
				} else {
					$.messager.alert('操作提示', "更新系统人员信息异常，请联系相关技术人员", 'error');
				}
			}, "json");
			return false;
		});
	});
</script>
	</head>
	<body class="easyui-layout">
		<div region="center" title="编辑系统人员" iconCls="icon-mod">
			<form id="ff"
				style="border-bottom: 1px solid #99BBE8; background-color: #ffffff"
				action="">
				<input id="createTime" name="createTime" type="hidden" />
				<table
					style="margin-top: 20px; width: 100%; background-color: #E5EEEA;"
					cellpadding="0" cellspacing="1">
					<tr>
						<td class="rt">
							系统人员名称：
						</td>
						<td class="rt2">
							<input class="easyui-validatebox formtext" required="true"
								id="name" name="name" type="text" maxlength="20"
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
								required="true" id="account" name="account" type="text"
								validType="username" maxlength="20">
							*字母开头，允许6-16字节，允许字母数字下划线
						</td>
					</tr>
					<tr>
						<td class="rt">
							密码：
						</td>
						<td class="rt2">
							<input class="easyui-validatebox formtext" required="true"
								id="password" name="password" type="password" maxlength="20">
							*请输入密码
						</td>
					</tr>
					<tr>
						<td class="rt">
							管理员权限:
						</td>
						<td class="rt2">
							<select class="easyui-validatebox formtext" name="roleSelect" id="roleSelect">
								<option value="role">
									--请选择--
								</option>
								<c:forEach var="role" items="${roleList}">
									<option value="${role.id}"  <c:if test="${role.id==manager.role.id}" >selected</c:if> >
										${role.name}
									</option>
								</c:forEach>
							</select>
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
									<option value="${group.groupId}" <c:if test="${group.groupId==manager.group.groupId}" >selected</c:if> >
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
							<input class="formbutton" type="button" id="returnManager"
								value="返回">
						</td>
					</tr>

				</table>
			</form>
		</div>
	</body>
</html>