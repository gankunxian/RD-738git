<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>详细参数列表页面</title>
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
<script type="text/javascript" src="${basePath}/js/lhgdialog/lhgdialog.min.js?skin=mac"></script>
<script type="text/javascript" src="${basePath}/js/base.js"></script>
<script>
	function doDel(ids){
		$.messager.confirm('确认', '您确认要删除此详细参数数据吗?', function(r){
			if (r){
				$.post("${basePath}/admin/parameter/delParameterDetail",{
					ids:ids
				},function(data, textStatus) {
							if (data.code == 0) {
								 $.messager.alert("操作提示", "删除成功！", "info", function () {
									 $("#test").datagrid('reload');
							        });
							} else {
								if (data.message) {
									$.messager.alert('操作提示', data.message, 'info');
									return false;
								}
								 $.messager.alert("操作提示", "功能异常，请联系相关技术人员！", "error");
							}
							$("#test").datagrid('unselectAll');
						}, "json");
			}
		});
	}	

	function doEdit(id){
		window.location.href = '${basePath}/jsp/parameter/editParameterDetail.jsp?detailId=' + id;
	}
	
	function doAdd(){
		window.location.href="${basePath}/jsp/parameter/addParameterDetail.jsp?parameterId="+<%=request.getParameter("parameterId")%>+"&parameterName=" + $('#parameterName').val();
	}

	function doAllDel(){
		var rows = $('#test').datagrid('getSelections');
		if (!rows || rows.length == 0) {
			$.messager.alert('提示', '请选择要删除的详细参数数据!', 'info');
			return;
		}
		var ids = [];
		$.each(rows, function(i, n) {
			ids.push(n.detailId);
		});
		doDel(ids.join(','));
	}

	$(function() {
		$('#parameterId').val(<%=request.getParameter("parameterId")%>);
		$('#parameterName').val('<%=request.getAttribute("parameterName")%>');
		$('#test').datagrid({
			title : '<font color="black"><%=request.getAttribute("parameterName")%></font> - 详细参数',
			border : false,
			nowrap : true,
			striped : true,
			fitColumns : true,
			fit:true,
			url : '${basePath}/admin/parameter/listParameterDetail',
			sortName : 'sort',
			sortOrder : 'asc',
			remoteSort : true,
			queryParams: {
				parameterId : $('#parameterId').val()
			},
            resizeHandle : 'center',
			idField : 'detailId',
			pageSize : 10,
			pageList : [ 5, 10, 15 ],
			columns : [ [ {
				field : 'ck',
				checkbox : true
			}, {
				field : 'detailName',
				title : '名称',
				width : $(this).width() * 0.1,
				align: 'center'
			},  {
				field : 'createTime',
				title : '创建时间',
				align : 'center',
				width : $(this).width() * 0.1
			}, {
				field : 'createPerson',
				title : '创建人',
				width : $(this).width() * 0.1,
				align: 'center',
				sortable : true
			},{
				field : 'modifyTime',
				title : '修改时间',
				align : 'center',
				width : $(this).width() * 0.1
			}, {
				field : 'modifyPerson',
				title : '修改人',
				width : $(this).width() * 0.1,
				align: 'center',
				sortable : true
			},{
				field : 'detailId',
				title : '操作',
				align : 'center',
				width : $(this).width() * 0.1,
				formatter  : function(val, rec){
						return '<a href="javascript:void(0);" onclick = "return doEdit(' + val + ');">编辑</a>'+
							'  |  <a href="javascript:void(0);" onclick = "return doDel(' + val + ');">删除</a>';
					}
			} ] ],
			pagination : true,
			rownumbers : true
		});
		$('#test').datagrid({toolbar:'#tbMenus'});
		$(window).resize(function() {
			$('#test').datagrid('resize');
		});
	});
</script>
</head>
<body class="easyui-layout">
<input id="parameterId" name="parameterId" type="hidden"/>
<input id="parameterName" name="parameterName" type="hidden"/>
	<div region="center">
		<table id="test"></table>
	</div>
			<div id="tbMenus" style="height:auto;">
    	 <div id="tb" style="white-space:nowrap;">
    	   	       <a href="${basePath}/jsp/parameter/listParameter.jsp" style="float:right;text-decoration: underline;margin-top:6px;margin-right:3px">返回系统参数管理</a>
           &nbsp;&nbsp;
             
           <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-add" onclick="doAdd()">添加详细参数</a>
    	   <a href="#" class="datagrid-btn-separator separate"></a>
    	   <!--
    	   <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-mod" onclick="editParameterDetail()">编辑详细参数</a>
    	   <a href="#" class="datagrid-btn-separator separate"></a>
    	   -->
    	   <a href="#" class="easyui-linkbutton" plain="true" iconCls="icon-delete" onclick="doAllDel()">批量删除</a>
        </div>  
       </div>
</body>
</html>