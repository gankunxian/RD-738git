<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>系统人员管理列表</title>
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
function doDel(ids){
	$.messager.confirm('确认', '您确认要删除此系统人员数据吗?', function(r){
		if (r){
			$.post("${basePath}/admin/manager/delManager",{
				ids:ids
			},function(data, textStatus) {
						if (data.code == 0) {
							 $.messager.alert("操作提示", "删除系统人员信息成功！", "info", function () {
								 $("#test").datagrid('reload');
						        });
						} else {
							if (data.message) {
								$.messager.alert('操作提示', data.message, 'info');
								return false;
							}
							 $.messager.alert("操作提示", "删除系统人员信息异常，请联系相关技术人员！", "error");
						}
						$("#test").datagrid('unselectAll');
					}, "json");
		}
	});
}

	function doEdit(id){
			hotshare.location('${basePath}/admin/manager/toEdit?managerId=' + id);
	}
	
	
	$(function() {
		$('#test').datagrid({
			nowrap : true,
			striped : true,
			fitColumns : true,
			fit:true,
			url : '${basePath}/admin/manager/listManager',
			sortName : 'createTime',
			sortOrder : 'asc',
			remoteSort : true,
            resizeHandle : 'center',
			idField : 'managerId',
			pageSize : 10,
			pageList : [ 5, 10, 15 ],
			columns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'name',
				title : '名称',
				width : $(this).width() * 0.05,
				align: 'center'
			}, {
				field : 'role',
				title : '角色',
				width : $(this).width() * 0.05,
				align: 'center',
				formatter: function(val, rec) {
                return val == null ? '': val.name;
           	  }
			},{
				field : 'account',
				title : '账号',
				width : $(this).width() * 0.05,
				align: 'center',
				sortable : true
			}, {
				field : 'createTime',
				title : '创建时间',
				align : 'center',
				width : $(this).width() * 0.1
			}, {
				field : 'modifyTime',
				title : '修改时间',
				align : 'center',
				width : $(this).width() * 0.1
			}, {
				field : 'managerId',
				title : '操作',
				align : 'center',
				width : $(this).width() * 0.1,
				formatter  : function(val, rec){
						return '<a href="javascript:void(0);" onclick = "return doEdit(' + val + ');">编辑</a>' +
							'  |  <a href="javascript:void(0);" onclick = "return doDel(' + val + ');">删除</a>';
					}
			} ] ],
			pagination : true,
			rownumbers : true,
			toolbar : [ {
				id : 'btnadd',
				text : '添加系统人员',
				iconCls : 'icon-add',
				handler : function() {
					hotshare.location('${basePath}/admin/manager/toAdd');
				}
			}, '-', {
				id : 'btndel',
				text : '批量删除',
				iconCls : 'icon-delete',
				handler : function() {
					var rows = $('#test').datagrid('getSelections');
					if (!rows || rows.length == 0) {
						$.messager.alert('提示', '请选择要删除的系统人员数据!', 'info');
						return;
					}
					var ids = [];
					$.each(rows, function(i, n) {
						ids.push(n.managerId);
					});
					doDel(ids.join(','));
				}
			}]
		});
		$(window).resize(function() {
			$('#test').datagrid('resize');
		});
	});
</script>
</head>
<body class="easyui-layout">
	<div region="center">
		<table id="test"></table>
	</div>
	
</body>
</html>