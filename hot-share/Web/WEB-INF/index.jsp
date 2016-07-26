<%@ page language="java" contentType="text/html; charset=UTF-8"
	isELIgnored="false" pageEncoding="UTF-8"%>
<%@ taglib prefix="broadin" uri="/WEB-INF/menutag.tld"%>
<%@include file="../common/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>管理中心-英飞拓智慧云运营管理后台</title>
<link rel="stylesheet" type="text/css"
	href="${basePath}/js/jquery/themes/default/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}/js/jquery/themes/gray/easyui.css">
<link rel="stylesheet" type="text/css"
	href="${basePath}/js/jquery/themes/icon.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/icon.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/common.css">
<link rel="stylesheet" type="text/css" href="${basePath}/css/topnav.css">
<script type="text/javascript"
	src="${basePath}/js/jquery/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="${basePath}/js/jquery/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="${basePath}/js/jquery/easyui-lang-zh_CN.js"></script>
<script type="text/javascript"
	src="${basePath}/js/index/outlook.js"></script>
<style type="text/css">
a:hover {
	background: none repeat scroll 0 0 #F3F3FA;
    border: 1px solid #db9f00;
    cursor: pointer;
    color:black;
    font-weight:bold;
}
.selected{
color:black;
font-weight:bold;
background: none repeat scroll 0 0 #F3F3FA;
border: 1px solid #db9f00;
}
</style>
<script type="text/javascript">
function managerLogout(){
	$.messager.confirm('系统提示', '确定要退出运营管理平台吗?', function(r){
		if (r){
			try {
			top.location.href = '${basePath}/admin/manager/loginOut';}
			catch (e) {
				
			}
		}
	});
}
	$(function() {
		$('#win').window({
			width : 350,
			height : 260,
			top : ($(window).height() - 300) * 0.5,
			left : ($(window).width() - 400) * 0.5,
			modal : true,
			closed : true,
			resizable : false,
			collapsible : false,
			maximizable : false
		});
		$("#changePwd").click(function(){
			$('#win').window('open');
			$('#left').accordion('select', '系统设置');
			$('#left').find("a[rel='修改密码']").trigger("click");
			return false;
			});
		$(":submit").click(function(){
			if(!$("#ff").form('validate')){
			return false;
			}
			if ($.trim($("#newpwd").val()) != $.trim($("#newpwd2").val()))
	        {
	           $.messager.alert('操作提示', '两次新密码输入不一致!', 'info');
	            return false;
	        }
		    if ($.trim($("#newpwd").val()) == $.trim($("#oldpwd").val()))
	        {
	            $.messager.alert('操作提示', '新密码和旧密码输入重复!', 'info');
	            return false;
	        }
		});

	$('#ff').form({
			url : "${basePath}/admin/manager/changePassword",
			success : function(data) {
				var data = eval('(' + data + ')');
				$.messager.alert('提示', data.message, 'info');
				if (data.success) {
					$("input[type=reset]").trigger("click");
					$('#win').window('close');
				}
			}
		});
		$(window).resize(function() {
			if($('#DivLocker').is(":visible"))
			$('#DivLocker').css({
				'height' : function() {
					return document.documentElement.clientHeight==0?document.body.clientHeight:document.documentElement.clientHeight;
				},
				'width' : function() {
					return document.documentElement.clientWidth==0?document.body.clientWidth:document.documentElement.clientWidth;
				}
			});
			$('#loading').css({
				'top' : function() {
					return (document.documentElement.clientHeight==0?document.body.clientHeight:document.documentElement.clientHeight - this.height)
					/ 2;
		},
				'left' : function() {
					return (document.documentElement.clientWidth==0?document.body.clientWidth:document.documentElement.clientWidth - this.width)
					/ 2;
		}
			});
		});
		//$("#mainFrame").attr("src",$('#left').find("li a").eq(0).attr("href"));
	});
</script>
</head>
<body class="easyui-layout">
    <div id="DivLocker" style="display:none"></div>
    <img title="请稍等..." src="${basePath}/js/ajaxfileupload/loading.gif" id="loading" style="position:absolute;display: none;width:32px;height:32px;">
    <div region="north"  style="height:79px;border:0;overflow:hidden">
			<div class="header">
				<div class="header_logo"></div>
				<div class="header_button">
					<ul class="header_button_list">
					    <li><a href="#" title="修改密码"><img 
src="${basePath}/images/topnav/icon_2.png" id="changePwd" border="0"/></a></li>
						<li><a href="#" title="退出系统" onclick="managerLogout();return false;"><img 
src="${basePath}/images/topnav/icon_3.png" id="loginOut" border="0"/></a></li>
					</ul>
				</div>
			</div>
	</div>
	<div region="west" split="false" style="width:170px;overflow:hidden">
		<div class="easyui-panel" title='管理员&nbsp;&nbsp;<font color="black">${managerinfo.account}</font>' border="false">
			<div id="left" class="easyui-accordion" fit="false" border="false"
				style="padding-top: 5px">
             <broadin:menu/>
			</div>
		</div>
	</div>
	<div region="center" style="overflow:hidden">
		<!-- <iframe id="mainFrame" name="mainFrame" src=""
			width="100%" height="100%" scrolling="auto" frameborder="0"
			marginheight="0" marginwidth="0"></iframe> -->
		<div id="tabs" class="easyui-tabs"  fit="true" border="false">
		<div title="欢迎使用" data-options="iconCls:'icon-welcome'" style="height:100%">
		<div class="easyui-panel" data-options="fit:false,iconCls:'icon-guide',border:true" title="英飞拓智慧云后台管理系统使用指南" style="padding:20px;overflow:hidden;height:100%" id="home">
				
			<h1>英飞拓智慧云后台管理系统</h1>

			</div>
		</div>
		</div>
	</div>
	<div region="south"  style="background: url(${basePath}/images/topnav/footer_bg.jpg) repeat-x;height: 47px;overflow:hidden;line-height: 47px;text-align: center;">
		Copyright © 2014- 2015 hotshare. All Rights Reserved. —— 深圳英飞拓软件开发有限公司 版权所有
    </div>
	<div id="mm" class="easyui-menu" style="width:150px;">
		<div id="mm-tabupdate">刷新</div>
		<div class="menu-sep"></div>
		<div id="mm-tabclose">关闭</div>
		<div id="mm-tabcloseall">全部关闭</div>
		<div id="mm-tabcloseother">除此之外全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-tabcloseright">当前页右侧全部关闭</div>
		<div id="mm-tabcloseleft">当前页左侧全部关闭</div>
		<div class="menu-sep"></div>
		<div id="mm-exit">退出</div>
	</div>
	<div id="win" iconCls="window-define-header" style="background: #E4EDFE; padding-top:30px;" title="修改密码">
      <div class="easyui-layout" fit="true" >
        <div region="center" border="false" style="background: #E4EDFE;height:190px">  
	    <form id="ff" method="post">
	        <div>
	            <label for="oldpwd">输入旧密码</label>
	            <input class="easyui-validatebox formtext" type="password" id="oldpwd" name="oldpwd" required="true"></input>
	        </div>
	        <div>
	            <label for="newpwd">输入新密码</label>
	            <input class="easyui-validatebox formtext" type="password" id="newpwd" name="newpwd" required="true"></input>
	        </div>
	        <div>
	            <label for="newpwd2">确认新密码</label>
	            <input class="easyui-validatebox formtext" type="password" id="newpwd2" name="newpwd2" required="true"></input>
	        </div>
	        <div id="submit-div">
	        <input class="formbutton" id="btns" type="submit" value="提交">
	        <input class="formbutton" id="btnr" type="reset" value="重置">
	        </div>
	    </form>
	    </div>
	</div>
 </div>
</body>
</html>