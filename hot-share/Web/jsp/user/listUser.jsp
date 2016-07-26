<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>用户管理列表</title>
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
	src="${basePath}/js/hotshare.js"></script>
<script type="text/javascript"
	src="${basePath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${basePath}/js/jquery/easyui-lang-zh_CN.js"></script>
<script type="text/javascript" src="${basePath}/js/base.js"></script>
<script>
function doState(ids,state){
	var str = "启用";
	if(state==0){
		str = "禁用";
	}
	$.messager.confirm('确认', '您确认要'+str+'此用户吗?', function(r){
		if (r){
			$.post("${basePath}/admin/user/doUserState",{
				ids:ids,state:state
			},function(data, textStatus) {
						if (data.code == 0) {
							 $.messager.alert("操作提示", str+"用户成功！", "info", function () {
								 $("#test").datagrid('reload');
						        });
						} else {
							if (data.message) {
								$.messager.alert('操作提示', data.message, 'info');
								return false;
							}
							 $.messager.alert("操作提示", str+"用户信息异常，请联系相关技术人员！", "error");
						}
						$("#test").datagrid('unselectAll');
					}, "json");
		}
	});
}

	function doEdit(id){
		hotshare.location('${basePath}/admin/user/toEdit?userId=' + id);
	}
	
	function sendSystemMessage(ids){
		hotshare.location('${basePath}/admin/systemMessage/toSendSystemMessage?ids=' + ids);
	}
	
	function listUserEquipment(userId){
		window.location.href = '${basePath}/admin/equipment/toListUserEquipment?userId=' + userId;
	}
	
	function doSearch(){
		var account = $("#account").val();
		var groupId = $("#groupId").val();
		var state = $("#state").val();
		hotshare.location('${basePath}/admin/user/toListUser?account=' + account+'&groupId='+groupId+'&state='+state);
	}
	
	$(function() {
		$('#test').datagrid({
			nowrap : true,
			striped : true,
			fitColumns : true,
			fit:true,
			url : '${basePath}/admin/user/listUser?account=${account}&groupId=${groupId}&state=${state}',
			sortName : 'regTime',
			sortOrder : 'desc',
			remoteSort : true,
            resizeHandle : 'center',
			idField : 'userId',
			pageSize : 20,
			pageList : [ 20, 50, 100 ],
			columns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'account',
				title : '账号',
				width : $(this).width() * 0.05,
				align: 'center',
				sortable : true
				
			}, {
				field : 'nickname',
				title : '昵称',
				width : $(this).width() * 0.05,
				align: 'center'
			},{
				field : 'name',
				title : '真实姓名',
				width : $(this).width() * 0.05,
				align: 'center'
			}, {
				field : 'group',
				title : '所属用户组',
				width : $(this).width() * 0.05,
				align: 'center',
				formatter: function(val, rec) {
               	 	return val.groupName == null? '暂无分组': val.groupName;
           	  	}
			}, {
				field : 'phone',
				title : '联系电话',
				width : $(this).width() * 0.05,
				align: 'center',
				sortable : true
			}, {
				field : 'regTime',
				title : '注册时间',
				align : 'center',
				width : $(this).width() * 0.1
			}, {
				field : 'userId',
				title : '操作',
				align : 'center',
				width : $(this).width() * 0.1,
				formatter  : function(val, rec){
						var str = '';
						if(rec.state==0){
							str = '  |  <a href="javascript:void(0);" onclick = "return doState(' + val+','+0 + ');">禁用</a>';
						}else{
							str = '  |  <a href="javascript:void(0);" onclick = "return doState(' + val+','+-1 + ');">启用</a>';
						}
						return '<a href="javascript:void(0);" onclick = "return listUserEquipment(' + val + ');">查看设备</a>' +
							'  |  <a href="javascript:void(0);" onclick = "return doEdit(' + val + ');">编辑</a>' + str
							;
					}
			} ] ],
			pagination : true,
			rownumbers : true,
			toolbar : [ {
				id : 'btnadd',
				text : '添加用户',
				iconCls : 'icon-add',
				handler : function() {
					hotshare.location('${basePath}/admin/user/toAdd');
				}
			}, '-', {
				id : 'btndel',
				text : '批量启用',
				iconCls : 'icon-delete',
				handler : function() {
					var rows = $('#test').datagrid('getSelections');
					if (!rows || rows.length == 0) {
						$.messager.alert('提示', '请选择要启用的用户数据!', 'info');
						return;
					}
					var ids = [];
					$.each(rows, function(i, n) {
						ids.push(n.userId);
					});
					doState(ids.join(','),-1);
				}
			}, '-', {
				id : 'btndel',
				text : '批量禁用',
				iconCls : 'icon-delete',
				handler : function() {
					var rows = $('#test').datagrid('getSelections');
					if (!rows || rows.length == 0) {
						$.messager.alert('提示', '请选择要禁用的用户数据!', 'info');
						return;
					}
					var ids = [];
					$.each(rows, function(i, n) {
						ids.push(n.userId);
					});
					doState(ids.join(','),0);
				}
			}, '-', {
				id : 'btnInformation',
				text : '发送系统消息',
				iconCls : 'icon-back',
				handler : function() {
					var rows = $('#test').datagrid('getSelections');
					if (!rows || rows.length == 0) {
						$.messager.alert('提示', '请选择接收系统消息的用户!', 'info');
						return;
					}
					var ids = [];
					$.each(rows, function(i, n) {
						ids.push(n.userId);
					});
					sendSystemMessage(ids.join(','));
				}
			}]
		});
		$(window).resize(function() {
			$('#test').datagrid('resize');
		});
		
		$("#selfDIV").appendTo('.datagrid-toolbar table tbody tr');
	});
</script>
</head>
<body class="easyui-layout">
	<div region="center">
		<table id="test"></table>
	</div>
	<div id="selfDIV">
	 		<div class="datagrid-btn-separator"></div>
		 	<div class="l-btn l-btn-plain" style="float:left;padding-left:10px;" id="">
		 		账号:<input value="${account}" class="searchInput" name="account" id="account"/>
			</div>
			<div class="datagrid-btn-separator"></div>
			<div class="l-btn l-btn-plain" style="float:left;padding-left:10px;" id="">
				用户组:<select class="searchInput" name="groupId" id="groupId"><option value="">--请选择--</option><c:forEach var="group" items="${groupList}"><option value="${group.groupId}" <c:if test="${group.groupId==groupId}">selected</c:if>>${group.groupName}</option></c:forEach></select>
			</div>
			<div class="datagrid-btn-separator"></div>
			<div class="l-btn l-btn-plain" style="float:left;padding-left:10px;" id="">
				状态:<select class="searchInput" name="state" id="state"><option value="">--全部--</option><option value="0" <c:if test="${state==0}">selected</c:if>>启用</option><option value="-1" <c:if test="${state==-1}">selected</c:if>>禁用</option></select>
			</div>
			<div class="datagrid-btn-separator"></div>
			<a href="javascript:void(0)" class="l-btn l-btn-plain" id="">
				<span class="l-btn-left">
				<span class="l-btn-text icon-search l-btn-icon-left" style="padding-left: 20px;" onclick="doSearch();">查询</span>
				</span>
			</a>
		</div>
</body>
</html>