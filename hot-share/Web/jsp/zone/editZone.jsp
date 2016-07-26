<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@include file="../../common/common.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>修改地区数据</title>
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
                        $("#area").append("<option value='3'>--请选择--</option>");
                        $("#city").append(result);   
                       }   
	               });
	           }else{
	              $("#city").empty();
	              $("#area").empty();
	              $("#city").append("<option value='2'>--请选择--</option>");   
	              $("#area").append("<option value='3'>--请选择--</option>");
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
                         $("#area").append(result); 
                        }   
	               });
	           }else{
	              $("#area").empty();
	              $("#area").append("<option value='3'>--请选择--</option>"); 
	           }
	      }); 
	      
	      
	      

	
	$("#returnZone").click(function(){
		location.href="${basePath}/jsp/zone/listZone.jsp";
	});
	$(":submit").click(function() {
		if (!$("#ff").form('validate')) {
			return false;
		}
		var level = $("#level").val();
		if(level=='2'){
			if($("#province").val()=='1'){
				$.messager.alert('操作提示', "请选择省份!", 'info');
				return false;
			}
		}else if(level=='3'){
			if($("#province").val()=='1'){
				$.messager.alert('操作提示', "请选择省份!", 'info');
				return false;
			}
			if($("#city").val()=='2'){
				$.messager.alert('操作提示', "请选择城市!", 'info');
				return false;
			}
		}else if(level=='4'){
			if($("#province").val()=='1'){
				$.messager.alert('操作提示', "请选择省份!", 'info');
				return false;
			}
			if($("#city").val()=='2'){
				$.messager.alert('操作提示', "请选择城市!", 'info');
				return false;
			}
			if($("#area").val()=='3'){
				$.messager.alert('操作提示', "请选择区域!", 'info');
				return false;
			}
		}
		
		
		$.post("${basePath}/admin/zone/modifyZone",{
			zoneName:$("#zoneName").val(),
			isHot:$("#isHot").val(),
			province:$("#province").val(),
			city:$("#city").val(),
			area:$("#area").val(),
			createTime:$("#createTime").val(),
			zoneNo:$("#zoneNo").val(),
			sort:$("#sort").val(),
			level:$("#level").val(),
			oldName:$("#oldName").val(),
			zoneId:<%=request.getParameter("zoneId")%>
		},function(data, textStatus) {
					if (data.success) {
						$.messager.alert('操作提示', "更新地区信息成功", 'info');
						$("#returnZone").trigger("click");
					} else if (data.message) {
						$.messager.alert('操作提示', data.message, 'info');
					} else {
						$.messager.alert('操作提示', "更新地区信息异常，请联系相关技术人员", 'error');
					}
				}, "json");
				return false;
			});
});
</script>
</head>
<body class="easyui-layout">
<div region="center" title="编辑地区" iconCls="icon-mod">
<form id="ff" style="border-bottom: 1px solid #99BBE8;background-color:#ffffff" action="">
<input id="createTime" name="createTime" type="hidden" value="${zone.createTime}"/>
<input id="zoneNo" name="zoneNo" type="hidden" value="${zone.zoneNo}"/>
<input id="level" name="level" type="hidden" value="${zone.level}"/>
<input id="oldName" name="oldName" type="hidden" value="${zone.zoneName}"/>
<table style="margin-top:20px;width:100%;background-color:#E5EEEA;" cellpadding="0" cellspacing="1">
<tr>
	<td class="rt">
		所属地区：
	</td>
	<td class="rt2">
		<select name="province" id="province" style="width: 130px;" class="formtext">
		<c:if test="${zone.level==3||zone.level==4}">
		      <c:forEach var="province" items="${provinceList}">
				<option value="${province.zoneNo}"
					<c:if test="${province.zoneId eq curProvence.zoneId }"> selected </c:if>>
					${province.zoneName}
				</option>
			</c:forEach>
		</c:if>
		<c:if test="${zone.level==2}">
			<c:forEach var="province" items="${provinceList}">
				<option value="${province.zoneNo}"
					<c:if test="${province.zoneId eq fatherZone.zoneId }"> selected </c:if>>
					${province.zoneName}
				</option>
			</c:forEach>
	    </c:if>
	    
		</select>&nbsp;
		<select class="formtext" name="city" id="city"  style="width: 130px;"  <c:if test="${zone.level==2}" > disabled="disabled" </c:if>>
               <option value="2">--请选择--</option>
               <c:if test="${zone.level==2}">
               <c:forEach var="city" items="${cityList}">
				<option value="${city.zoneNo}"
					<c:if test="${city.zoneId eq zone.zoneId }"   > selected </c:if> >
					${city.zoneName}
				</option>
			</c:forEach>
			</c:if>
			<c:if test="${zone.level==3}">
			   <c:forEach var="city" items="${cityList}">
				<option value="${city.zoneNo}"
					<c:if test="${city.zoneId eq fatherZone.zoneId }"> selected </c:if> >
					${city.zoneName}
				</option>
			  </c:forEach>   
			</c:if>
			
			<c:if test="${zone.level==4}">
			   <c:forEach var="city" items="${cityList}">
				<option value="${city.zoneNo}"
					<c:if test="${city.zoneId eq curCity.zoneId }"> selected </c:if> >
					${city.zoneName}
				</option>
			  </c:forEach>   
			</c:if>
			
          </select> &nbsp;
          <select class="formtext" name="area" id="area" style="width: 130px;" <c:if test="${zone.level==3}" > disabled="disabled" </c:if>
          <c:if test="${zone.level==2}" > disabled="disabled" </c:if>>
               <option value="3">--请选择--</option>
               <c:if test="${zone.level==4}">
                <c:forEach var="cityarea" items="${areaList}">
					<option value="${cityarea.zoneNo}"
						<c:if test="${cityarea.zoneId eq curArea.zoneId }"> selected </c:if> >
						${cityarea.zoneName}
					</option>
				</c:forEach>
			</c:if>
			<c:if test="${zone.level==3}">
                <c:forEach var="cityarea" items="${areaList}">
					<option value="${cityarea.zoneNo}"
						<c:if test="${cityarea.zoneId eq zone.zoneId }"> selected </c:if> >
						${cityarea.zoneName}
					</option>
				</c:forEach>
			</c:if>
			
             </select>&nbsp;
             <select class="formtext" name="circle" id="circle" style="width: 130px;" disabled="disabled">
               <option value="4">--请选择--</option>
                <c:forEach var="circle" items="${circleList}">
					<option value="${circle.zoneNo}"
						<c:if test="${circle.zoneId eq zone.zoneId }"> selected </c:if> >
						${circle.zoneName}
					</option>
				</c:forEach>
             </select>
	</td>
</tr>
<tr>
  <td class="rt">地区名称：</td>
  <td class="rt2">
  <input class="easyui-validatebox formtext" value="${zone.zoneName}" required="true" id="zoneName" name="zoneName" type="text"  maxlength="20" validType="input"> *最大20个字符
  </td>
</tr>
<tr>
  <td class="rt">是否热门：</td>
  <td class="rt2">
  	<select class="easyui-validatebox formtext" name="isHot" id="isHot" style="width:100px">
		<option value="0" <c:if test="${zone.isHot==0}">selected</c:if>>否</option>
     	 <option value="1" <c:if test="${zone.isHot==1}">selected</c:if>>是</option>
 	</select>
  </td>
</tr>
<tr>
  <td class="rt">排序：</td>
  <td class="rt2">
  <input class="easyui-validatebox formtext" required="true" value="${zone.sort}" onkeyup="this.value=this.value.replace(/\D/g,'')" id="sort" name="sort" type="text"  maxlength="3">*请填写数字 
  </td>
</tr>
<tr>
  <td colspan="2" style="padding-left: 200px;padding-top: 15px;padding-bottom:10px"><input class="formbutton" style="margin-right:30px" type="submit" value="提交"> <input class="formbutton" type="button"  id="returnZone" value="返回"></td>
</tr>
</table></form>
</div>
</body>
</html>