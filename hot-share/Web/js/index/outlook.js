$(function() {
	$(".tabs-inner").removeAttr("href");
	$(".tabs-inner").css("cursor", "pointer");
	tabClose();
	tabCloseEven();
	InitLeftMenu();
	//$('#left').find("li a").eq(0).trigger("click");
	$('#tabs').tabs({
		onBeforeClose : function(title, index) {
			var currTab = $('#tabs').tabs('getSelected');
			var stitle = currTab.panel('options').title;
			if (title != stitle)
				return true;
			if (typeof $(".panel:visible > div > .mainFrame")[0].contentWindow.is_form_changed == "function") {
				if (!$(".panel:visible > div > .mainFrame")[0].contentWindow
						.is_form_changed()) {
				} else {
					var target = this;
					$.messager.confirm('确认', '您确认不保存此表单数据吗?', function(r) {
								if (r) {
									var opts = $(target).tabs('options');
									var bc = opts.onBeforeClose;
									opts.onBeforeClose = function() {
									}; // allowed to close now
									$(target).tabs('close', title);
									opts.onBeforeClose = bc; // restore the
									// event
									// function
								}
							});
					return false; // prevent from closing
				}
			} else {
			}
			return true;
		}
	});
});

// 增加导航栏
function addNav(data) {

	$.each(data, function(i, sm) {
				var menulist = "";
				menulist += '<ul>';
				$.each(sm.menus, function(j, o) {
							menulist += '<li><div><a ref="' + o.menuid
									+ '" href="#" rel="' + o.url
									+ '" ><span class="icon ' + o.icon
									+ '" >&nbsp;</span><span class="nav">'
									+ o.menuname + '</span></a></div></li> ';
						});
				menulist += '</ul>';

				$('#wnav').accordion('add', {
							title : sm.menuname,
							content : menulist,
							iconCls : 'icon ' + sm.icon
						});

			});

	var pp = $('#wnav').accordion('panels');
	var t = pp[0].panel('options').title;
	$('#wnav').accordion('select', t);

}

// 初始化左侧导航栏
function InitLeftMenu() {

	$('#left li a').live('click', function() {
				var tabTitle = $(this).text();
				var url = $(this).attr("href");
				var menuname = $(this).attr("rel");
				addTab(tabTitle, url, getIcon(menuname));
				$('#left li a').removeClass("selected");
				$(this).addClass("selected");
				return false;
			});

}

// 获取左侧导航的图标
function getIcon(menuname) {
	// 配置tab选项卡图标
	var _menus = [{
				"menuid" : "1",
				"icon" : "icon-user-list",
				"menuname" : "后台用户管理"
			}, {
				"menuid" : "2",
				"icon" : "icon-channel-list",
				"menuname" : "频道列表"
			}, {
				"menuid" : "3",
				"icon" : "icon-video-list",
				"menuname" : "节目列表"
			}, {
				"menuid" : "4",
				"icon" : "icon-live-list",
				"menuname" : "直播信息管理"
			}, {
				"menuid" : "5",
				"icon" : "icon-setting",
				"menuname" : "修改密码"
			}, {
				"menuid" : "6",
				"icon" : "icon-upload",
				"menuname" : "导入频道"
			}, {
				"menuid" : "7",
				"icon" : "icon-upload",
				"menuname" : "导入节目"
			}, {
				"menuid" : "8",
				"icon" : "icon-area",
				"menuname" : "地区管理"
			}, {
				"menuid" : "9",
				"icon" : "icon-conver-list",
				"menuname" : "节目汇列表"
			}, {
				"menuid" : "10",
				"icon" : "icon-auto",
				"menuname" : "自动编排"
			}];
	var icon = 'icon-user-list';
	$.each(_menus, function(i, n) {
				if (n.menuname == menuname) {
					icon = n.icon;
					return false;
				}
			});
	return icon;
}

// 增加tab选项卡
function addTab(subtitle, url, icon) {
	if (!$('#tabs').tabs('exists', subtitle)) {
		$('#tabs').tabs('add', {
					title : subtitle,
					content : createFrame(url),
					closable : true,
					icon : icon
				});
	} else {
		$('#tabs').tabs('select', subtitle);
		if (typeof $(".panel:visible > div > .mainFrame")[0].contentWindow.is_form_changed == "function") {
			if (!$(".panel:visible > div > .mainFrame")[0].contentWindow
					.is_form_changed()) {
				$('#mm-tabupdate').click();
			} else {
				$.messager.confirm('确认', '您确认不保存此表单数据吗?', function(r) {
							if (r) {
								$('#mm-tabupdate').click();
							}
						});
			}
		} else {
			$('#mm-tabupdate').click();
		}
	}
	$(".tabs-inner").removeAttr("href");
	$(".tabs-inner").css("cursor", "pointer");
	tabClose();
}

// 创建tab选项卡
function createFrame(url) {

	var s = '<iframe id="mainFrame" class="mainFrame" name="mainFrame" width="100%" height="100%" scrolling="auto" frameborder="0" marginheight="0" marginwidth="0"  src="'
			+ url + '"></iframe>';
	return s;
}

function tabClose() {
	/* 双击关闭TAB选项卡 */
	$(".tabs-inner").dblclick(function() {
				var subtitle = $(this).children(".tabs-closable").text();
				$('#tabs').tabs('close', subtitle);
			});
	/* 为选项卡绑定右键 */
	$(".tabs-inner").bind('contextmenu', function(e) {
				$('#mm').menu('show', {
							left : e.pageX - 15,
							top : e.pageY
						});

				var subtitle = $(this).children(".tabs-closable").text();

				$('#mm').data("currtab", subtitle);
				return false;
			});
}

// 绑定右键菜单事件
function tabCloseEven() {
	// 刷新
	$('#mm-tabupdate').click(function() {
				var currTab = $('#tabs').tabs('getSelected');
				var url = $(currTab.panel('options').content).attr('src');
				if (typeof(url) == 'undefined')
					return false;
				$('#tabs').tabs('update', {
							tab : currTab,
							options : {
								content : createFrame(url)
							}
						});
			});
	// 关闭当前
	$('#mm-tabclose').click(function() {
				var currtab_title = $('#mm').data("currtab");
				$('#tabs').tabs('close', currtab_title);
			});
	// 全部关闭
	$('#mm-tabcloseall').click(function() {
				$('.tabs-inner:gt(0) span').each(function(i, n) {
							var t = $(n).text();
							$('#tabs').tabs('close', t);
						});
			});
	// 关闭除当前之外的TAB
	$('#mm-tabcloseother').click(function() {
				$('#mm-tabcloseright').click();
				$('#mm-tabcloseleft').click();
			});
	// 关闭当前右侧的TAB
	$('#mm-tabcloseright').click(function() {
				var nextall = $('.tabs-selected').nextAll();
				if (nextall.length == 0) {
					return false;
				}
				nextall.each(function(i, n) {
							var t = $('a:eq(0) span', $(n)).text();
							$('#tabs').tabs('close', t);
						});
				return false;
			});
	// 关闭当前左侧的TAB
	$('#mm-tabcloseleft').click(function() {
				var prevall = $('.tabs-selected').prevAll(":not(:last)");
				if (prevall.length == 0) {
					return false;
				}
				prevall.each(function(i, n) {
							var t = $('a:eq(0) span', $(n)).text();
							$('#tabs').tabs('close', t);
						});
				return false;
			});

	// 退出
	$("#mm-exit").click(function() {
				$('#mm').menu('hide');
			});
}