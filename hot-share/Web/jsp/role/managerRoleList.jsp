<%@page contentType="text/html;charset=UTF-8" isELIgnored="false"%>
<%@include file="../../common/common.jsp"%>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>英飞拓智慧云管理平台-系统配置-角色列表</title>
		<link rel="stylesheet" type="text/css"
			href="${basePath}/js/jquery/themes/default/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="${basePath}/js/jquery/themes/gray/easyui.css">
		<link rel="stylesheet" type="text/css"
			href="${basePath}/js/jquery/themes/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}/css/icon.css">
		<link rel="stylesheet" type="text/css" href="${basePath}/css/Aqua/css/ligerui-form.css">
			<link rel="stylesheet" type="text/css" href="${basePath}/css/Gray/css/form.css">
		<link rel="stylesheet" type="text/css"
			href="${basePath}/css/common.css">
		<script type="text/javascript"
			src="${basePath}/js/jquery/jquery-1.7.1.min.js"></script>
		<script type="text/javascript"
			src="${basePath}/js/jquery/jquery.easyui.min.js"></script>
			<script type="text/javascript"
			src="${basePath}/js/hotshare.js"></script>
		<script type="text/javascript"
			src="${basePath}/js/jquery/easyui-lang-zh_CN.js"></script>
		<script type="text/javascript">
		hotshare.ns('hotshare.managerGroup.list');
		$(function(){
			$("#managerGroupAdd").hide();
			$("#managerGroupInfo").hide();
			$("#managerGroupList").datagrid({
				url:'${basePath}/admin/role/list',
				singleSelect: true,
				columns : [ [ {
					title : '系统角色名称',
					field : 'groupName',
					align : 'left',
					width : 200
				}, {
					title:'系统角色描述',
					field:'groupDesc',
					align:'left',
					width:250,
					formatter:function(val,rec){
						return val.length > 20 ? val.substring(0,20)+"..." : val;
					}
				}, {
					title:'操作',
					field:'id',
					align:'center',
					width:200,
					formatter:function(val,rec){
						return hotshare.managerGroup.list.operation(val,rec);
					}
				} ] ],
				toolbar:[
					<hotshare:permission permission="hotshare-set.groupList.add">
					 {
						text	: "新增系统角色",
						iconCls	: "icon-add",
						handler	: function(){
							hotshare.managerGroup.list.managerGroupAdd();
						}
					  }
					</hotshare:permission>	  
				]
			});
		});
		
		hotshare.managerGroup.list.operation = function(id,options){
			return '<a href="javascript:void(0);" onclick="hotshare.managerGroup.list.managerGroupBrowse('+id+',\''+options.groupName+'\',\''+options.groupDesc+'\');">权限详情</a>'+
			       '| <a href="javascript:void(0);" onclick="hotshare.managerGroup.list.managerGroupMod('+id+',\''+options.groupName+'\',\''+options.groupDesc+'\');">编辑</a>' +
			       ' | <a href="javascript:void(0);" onclick="hotshare.managerGroup.list.managerGroupDel('+id+');">删除</a>';
		}
		
		hotshare.managerGroup.list.managerGroupAdd = function(){
			hotshare.managerGroup.list.clearRoleValueList();
			hotshare.managerGroup.list.managerRoleList();
			$("#managerGroupAdd").show();
			$("#managerGroupAdd").dialog({title:'添加系统角色',width:750,height:400,modal:true,buttons:[ {
					text:'保存角色',iconCls:'icon-ok',handler:function(){
						if(hotshare.managerGroup.list.validateForm()){
							if(hotshare.managerGroup.list.getRoleValueList() == ''){
								hotshare.showConfirmMsg("系统提示","您确定不给当前创建的用户赋予权限吗",function(){
									hotshare.managerGroup.list.add();
									hotshare.managerGroup.list.clearMainFormData();
								});
							} else {
								hotshare.managerGroup.list.add();
								hotshare.managerGroup.list.clearMainFormData();
							}
						}
					}
				}, {
					text:'取消编辑',iconCls:'icon-cancel',handler:function(){
						hotshare.managerGroup.list.clearMainFormData();
						$('#managerGroupAdd').dialog('close');
					}
				}
			]});
		}

		hotshare.managerGroup.list.managerGroupMod = function(id,groupName,groupDesc){
			hotshare.managerGroup.list.clearRoleValueList();
			$('#ManagerUserGroupInfoRoleList').treegrid({
				url:'${basePath}/admin/module/tree',
				fit:true,
				nowrap: false,
				animate:true,
				collapsible:true,
				idField:'businessID',
				treeField:'businessName',
				queryParams:{
					roleID : id
				},
				columns: [ [ {
					title : '业务名称',
					field : 'businessName',
					width : 150,
					formatter : function(val,rec){
						return hotshare.managerGroup.list.getCheckboxHtmlByAll(val);
					}
				}, {
					field : 'operation',
					title : '权限列表',
					width : 480,
					formatter : function(val,rec){
						return hotshare.managerGroup.list.getCheckboxHtml(val);
					}
				}
				] ]
			});
			$("#groupInfoName").val(groupName);
			$("#groupInfoDesc").val(groupDesc);
			$("#groupInfoID").val(id);
			$("#managerGroupInfo").show();
			$("#managerGroupInfo").dialog({title:'修改系统角色详细信息',width:750,height:430,modal:true,buttons:[ {
					text:'保存信息',iconCls:'icon-ok',handler:function(){
						if(hotshare.managerGroup.list.validateFormMod()){
							if(hotshare.managerGroup.list.getRoleValueList() == ''){
								hotshare.showConfirmMsg("系统提示","您确定不给当前创建的用户赋予权限吗",function(){
									hotshare.managerGroup.list.mod();
								});
							} else {
								hotshare.managerGroup.list.mod();
							}
						}
					}
				}, {
					text:'关闭窗口',iconCls:'icon-cancel',handler:function(){
						$('#managerGroupInfo').dialog('close');
					}
				}
			]});
		}

		hotshare.managerGroup.list.managerGroupDel = function(id){
			hotshare.showConfirmMsg("系统提示","您确定要删除该系统角色吗?删除前先取消该管理员的全部权限",function(){
				hotshare.ajax({
					url	: '${basePath}/admin/role/del',
					data: {
						id : id
					},
					success : function(data){
						if(data.code == 0){
							hotshare.showMsg("系统提示","系统角色删除成功!",'info');
							$("#managerGroupList").datagrid("reload");
						} else {
							hotshare.showMsg("系统提示","系统角色删除失败!",'info');
						}
					}
				});
			});
		}

		hotshare.managerGroup.list.managerGroupBrowse = function(id,groupName,groupDesc){
			hotshare.managerGroup.list.clearRoleValueList();
			$('#ManagerUserGroupInfoRoleList').treegrid({
				url:'${basePath}/admin/module/tree',
				height:'auto',
				nowrap: false,
				animate:true,
				collapsible:true,
				idField:'businessID',
				treeField:'businessName',
				queryParams:{
					roleID : id
				},
				columns: [ [ {
					title : '业务名称',
					field : 'businessName',
					width : 150,
					formatter : function(val,rec){
						return hotshare.managerGroup.list.getCheckboxHtmlByAll(val);
					}
				}, {
					field : 'operation',
					title : '权限列表',
					width : 480,
					formatter : function(val,rec){
						return hotshare.managerGroup.list.getCheckboxHtmlBrowse(val);
					}
				}
				] ]
			});
			$("#groupInfoName").val(groupName);
			$("#groupInfoName").attr("disabled","false");
			$("#groupInfoDesc").val(groupDesc);
			$("#groupInfoDesc").attr("disabled","false");
			$("#groupInfoID").val(id);
			$("#managerGroupInfo").show();
			$("#managerGroupInfo").dialog({title:'查看系统角色权限详细信息',width:750,height:430,modal:true,buttons:[ {
					text:'关闭窗口',iconCls:'icon-cancel',handler:function(){
						$("#groupInfoDesc").removeAttr("disabled");
						$("#groupInfoName").removeAttr("disabled");
						$('#managerGroupInfo').dialog('close');
					}
				}
			]});
		}
		hotshare.managerGroup.list.managerRoleList = function(){
			$('#ManagerUserGroupRoleList').treegrid({
				url:'${basePath}/admin/module/allTree',
				height:'auto',
				nowrap: false,
				animate:true,
				collapsible:true,
				idField:'businessID',
				treeField:'businessName',
				columns: [ [ {
					title : '业务名称',
					width : 150,
					field : 'businessName',
					formatter : function(val,rec){
						return hotshare.managerGroup.list.getCheckboxHtmlByAll(val);
					}
				}, {
					field : 'operation',
					title : '权限列表',
						width : 480,
					formatter : function(val,rec){
						return hotshare.managerGroup.list.getCheckboxHtml(val);
					}
				}
				] ]
			});
		}
		
		hotshare.managerGroup.list.getCheckboxHtml = function(value){
			var htmlCode = "";
			$.each(value,function(i,item){
				if(item.operationFlag == 0){
					htmlCode += '<label><input type="checkbox" name="userRole" checked value="'+ item.id +'">&nbsp;' + item.operationName +'</label>&nbsp;&nbsp;';
				} else {
					htmlCode += '<label><input type="checkbox" name="userRole" value="'+ item.id +'">&nbsp;' + item.operationName +'</label>&nbsp;&nbsp;';		
				}
			});
			return htmlCode;
		}

		hotshare.managerGroup.list.getCheckboxHtmlBrowse = function(value){
			var htmlCode = "";
			$.each(value,function(i,item){
				if(item.operationFlag == 1){
					htmlCode += '<label><input type="checkbox" name="userRole" disabled="false" checked value="'+ item.id +'">&nbsp;' + item.operationName +'</label>&nbsp;&nbsp;';
				} else {
					htmlCode += '<label><input type="checkbox" name="userRole" disabled="false" value="'+ item.id +'">&nbsp;' + item.operationName +'</label>&nbsp;&nbsp;';		
				}
			});
			return htmlCode;
		}

		hotshare.managerGroup.list.getCheckboxHtmlByAll = function(name){
			return name;
		}

		hotshare.managerGroup.list.clearMainFormData = function(){
			$("#groupName").val('');
			$("#remark").val('');
		}

		hotshare.managerGroup.list.validateForm = function(){
			if($("#groupName").val() == ''){$.messager.alert('操作提示', "系统角色名称不能为空!", 'info'); return false; }
			if($("#groupName").val().length > 16){ $.messager.alert('操作提示', "系统角色名称太长!", 'info'); return false;}
			return true;
		}

		hotshare.managerGroup.list.validateFormMod = function(){
			if($("#groupInfoName").val() == ''){ $.messager.alert('操作提示',"系统角色名称不能为空!", 'info'); return false; }
			if($("#groupInfoName").val().length > 16){ $.messager.alert('操作提示',"系统角色名称太长!", 'info'); return false ;}
			return true;
		}

		hotshare.managerGroup.list.getRoleValueList = function(){
			var result = new Array();
			$("[name = userRole]:checkbox").each(function () {
				if ($(this).is(":checked")) {
					result.push($(this).attr("value"));
				}
			});
			return result.join("|");
		}

		hotshare.managerGroup.list.clearRoleValueList = function(){
			$("[name = userRole]:checkbox").each(function () {
				if ($(this).is(":checked")) {
					$(this).removeAttr("checked")
				}
			});
		}

		hotshare.managerGroup.list.add = function(){
			hotshare.ajax({
				url	: '${basePath}/admin/role/add',
				data: {
					roleName     : $("#groupName").val(),
					roleDesc     : $("#remark").val(),
					moduleFunctions : hotshare.managerGroup.list.getRoleValueList()
				},
				success : function(data){
					if(data.code == 0){
						hotshare.showMsg("系统提示","系统角色添加成功!",'info');
						$('#managerGroupAdd').dialog('close');
						$("#managerGroupList").datagrid("reload");
					} else {
						hotshare.showMsg("系统提示","系统角色添加失败!",'info');
					}
				}
			});
		}

		hotshare.managerGroup.list.mod = function(){
			hotshare.ajax({
				url	: '${basePath}/admin/role/mod',
				data: {
					id		  : $("#groupInfoID").val(),
					roleName     : $("#groupInfoName").val(),
					roleDesc     : $("#groupInfoDesc").val(),
					moduleFunctions : hotshare.managerGroup.list.getRoleValueList()
				},
				success : function(data){
					if(data.code == 0){
						hotshare.showMsg("系统提示","系统角色信息修改成功!",'info');
						$('#managerGroupInfo').dialog('close');
						$("#managerGroupList").datagrid("reload");
					} else {
						hotshare.showMsg("系统提示","系统角色信息修改失败!",'info');
					}
				}
			});
		}
	</script>
	</head>
	<body style="margin: 0; padding: 0; height: 100%;">
		<div id="managerGroupList"></div>
		<div id="managerGroupAdd">
			<form id="mainform" class="l-form" name="l-form" action="#"
				method="post">
				<div class="l-group l-group-hasicon">
					<img src="${basePath}/images/icon/cog_edit.png" />
					<span>系统角色信息添加</span>
				</div>
				<ul>
					<li style="width: 90px; text-align: left">
						角色名称:
					</li>
					<li style="width: 170px; text-align: left">
						<div class="l-text" style="width: 168px;">
							<input class="l-text-field" type="text" style="width: 168px;"
								name="groupName" id="groupName" tip="请输入创建系统角色的名称" />
							<div class="l-text-l"></div>
							<div class="l-text-r"></div>
						</div>
					</li>
				</ul>
				<ul>
					<li style="width: 90px; text-align: left">
						角色介绍:
					</li>
					<li style="width: 550px; text-align: left">
						<textarea style="width: 540px; height: 40px;" class="l-textarea"
							name="remark" id="remark" tip="请输入200位以内的备注说明"></textarea>
					</li>
				</ul>
				<ul style="height: 225px; overflow-x: hidden;">
					<div id="ManagerUserGroupRoleList"></div>
				</ul>
			</form>
		</div>
		<div id="managerGroupInfo">
			<form id="mainformInfo" class="l-form" name="l-form" action="#"
				method="post">
				<input type="hidden" id="groupInfoID" />
				<div class="l-group l-group-hasicon">
					<img src="${basePath}/images/icon/cog_edit.png" />
					<span>系统角色详细信息</span>
				</div>
				<ul>
					<li style="width: 90px; text-align: left">
						角色名称:
					</li>
					<li style="width: 170px; text-align: left">
						<div class="l-text" style="width: 168px;">
							<input class="l-text-field" type="text" style="width: 168px;"
								name="groupInfoName" id="groupInfoName" tip="请输入创建系统角色的名称" />
							<div class="l-text-l"></div>
							<div class="l-text-r"></div>
						</div>
					</li>
				</ul>
				<ul>
					<li style="width: 90px; text-align: left">
						角色介绍:
					</li>
					<li style="width: 550px; text-align: left">
						<textarea style="width: 540px; height: 40px;" class="l-textarea"
							name="groupInfoDesc" id="groupInfoDesc" tip="请输入200位以内的备注说明"></textarea>
					</li>
				</ul>
				<ul style="height: 225px; overflow-x: hidden;">
					<div id="ManagerUserGroupInfoRoleList"></div>
				</ul>
			</form>
		</div>
	</body>
</html>