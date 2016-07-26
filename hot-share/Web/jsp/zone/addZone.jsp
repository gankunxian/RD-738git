<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>添加地区数据</title>
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
			url:"${basePath}/admin/zone/findProvinceList",
			type:"post",
			data:{level:1,zoneNo:1},
			dataType: "html",
			contentType:"application/x-www-form-urlencoded;charset=utf-8",
			success:function(result) {
				$("#province").empty();
				$("#city").empty();
                $("#area").empty();
                $("#circle").empty();
                $("#city").append("<option value='2'>--请选择--</option>");
                $("#area").append("<option value='3'>--请选择--</option>");
                $("#circle").append("<option value='4'>--请选择--</option>");
                $("#province").append(result); 
			},
		    error:function(){
		    	$.messager.alert('操作提示', "加载地区信息异常，请联系相关技术人员", 'info');
		    	$("#returnZone").trigger("click");
		    }
		});
		
	      $("#province").change(function(){
	           var  province = $("#province").val();
	           if(Number(province)!=1){
	               $.ajax({
                       type:"post",
                       url:"${basePath}/admin/zone/findProvinceList",
                       data:{level:2,zoneNo:province},
                       dataType: "html",
                       contentType:"application/x-www-form-urlencoded;charset=utf-8",
                       success:function(result){
                        $("#city").empty();
                        $("#area").empty();
                        $("#circle").empty();
                        $("#area").append("<option value='3'>--请选择--</option>");
                        $("#circle").append("<option value='4'>--请选择--</option>");
                        $("#city").append(result);   
                       }   
	               });
	           }else{
	              $("#city").empty();
	              $("#area").empty();
	              $("#circle").empty();
	              $("#city").append("<option value='2'>--请选择--</option>");   
	              $("#area").append("<option value='3'>--请选择--</option>");
	              $("#circle").append("<option value='4'>--请选择--</option>");
	           }
	     });
	     
	     
	          $("#city").change(function(){
	           var  city = $(this).children('option:selected').val();
	           if(city!="2"){
	                $.ajax({
                        type:"post",
                        url:"${basePath}/admin/zone/findProvinceList",
                     	data:{level:3,zoneNo:city},
                      	dataType: "html",
                        contentType:"application/x-www-form-urlencoded;charset=utf-8",
                        error:function(){
                        Dialog.alert("请求失败");
                        },
                        success:function(result){
                         $("#area").empty();
                         $("#circle").empty();
                         $("#area").append(result); 
                         $("#circle").append("<option value='4'>--请选择--</option>");
                        }   
	               });
	           }else{
	              $("#area").empty();
	              $("#circle").empty();
	              $("#area").append("<option value='3'>--请选择--</option>"); 
	              $("#circle").append("<option value='4'>--请选择--</option>");    
	           }
	      }); 
	      
	      
	      $("#area").change(function(){
	           var  area = $("#area").val();
	           if(area!="3"){
	                $.ajax({
                        type:"post",
                        url:"${basePath}/admin/zone/findProvinceList",
                     	data:{level:4,zoneNo:area},
                      	dataType: "html",
                        contentType:"application/x-www-form-urlencoded;charset=utf-8",
                        error:function(){
                        	Dialog.alert("请求失败");
                        },
                        success:function(result){
                         $("#circle").empty();
                         $("#circle").append(result);   
                        }   
	               });
	           }else{
	              $("#circle").empty();
	              $("#circle").append("<option value='4'>--请选择--</option>");   
	           }
	      });  
	 
	
	
		$("#returnZone").click(function(){
			location.href="${basePath}/jsp/zone/listZone.jsp";
		});
		$(":submit").click(function() {
			if (!$("#ff").form('validate')) {
				return false;
			}
			$.post("${basePath}/admin/zone/addZone",{
				zoneName:$("#zoneName").val(),
				isHot:$("#isHot").val(),
				province:$("#province").val(),
				city:$("#city").val(),
				area:$("#area").val(),
				sort:$("#sort").val()
			},function(data, textStatus) {
						if (data.success) {
							$.messager.alert('操作提示', "添加地区信息成功", 'info');
							$("#returnZone").trigger("click");
						} else if (data.message) {
							$.messager.alert('操作提示', data.message, 'info');
						} else {
							$.messager.alert('操作提示', "添加地区信息异常，请联系相关技术人员", 'error');
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
<div region="center" title="添加地区" iconCls="icon-add">
<form id="ff" style="border-bottom: 1px solid #99BBE8;background-color:#ffffff" action="">
<table style="margin-top:20px;width:100%;background-color:#E5EEEA;" cellpadding="0" cellspacing="1">
<tr>
  <td class="rt">所属地区：</td>
  <td  class="rt2">
	<select name="province" id="province" style="width: 130px;" class="formtext">
	   <option value="1">--请选择--</option>
	</select>&nbsp;
	<select name="city" id="city"  style="width: 130px;" class="formtext">
         <option value="2">--请选择--</option>
    </select>&nbsp;
     <select name="area" id="area" style="width: 130px;" class="formtext">
         <option value="3">--请选择--</option>
      </select>&nbsp;
      <select name="circle" id="circle" style="width: 130px;" class="formtext">
         <option value="4">--请选择--</option>
      </select>
	</td>
</tr>
<tr>
  <td class="rt">地区名称：</td>
  <td class="rt2">
  <input class="easyui-validatebox formtext" required="true" id="zoneName" name="zoneName" type="text"  maxlength="20" validType="chinese"> *最大20个字符
  </td>
</tr>
<tr>
  <td class="rt">是否热门：</td>
  <td class="rt2">
  	<select class="easyui-validatebox formtext" name="isHot" id="isHot" style="width:100px">
		<option value="0">否</option>
     	 <option value="1">是</option>
 	</select>
  </td>
</tr>
<tr>
  <td class="rt">排序：</td>
  <td class="rt2">
  <input value="0" required="true" class="easyui-validatebox formtext" onkeyup="this.value=this.value.replace(/\D/g,'')" id="sort" name="sort" type="text"  maxlength="3">*请填写数字 
  </td>
</tr>
<tr>
  <td colspan="2" style="padding-left: 200px;padding-top: 15px;padding-bottom:10px"><input class="formbutton" style="margin-right:30px" type="submit" value="提交"> <input class="formbutton" type="button"  id="returnZone" value="返回"></td>
</tr>
</table></form>
</div>
</body>
</html>