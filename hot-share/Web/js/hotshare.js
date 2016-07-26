/**
 * 
 * hotshare系统js扩展工具包
 * 
 * author:陈世龙
 * 
 * date: 2012-5-2 14:41:20
 * 
 */
var hotshare = $.extend({}, hotshare); /* 全局对象 */

(function($) {

	/**
	 * 
	 * 扩展formatString功能
	 * 
	 * 使用方法：hotshare.fs('字符串{0}字符串{1}字符串','第一个变量','第二个变量');
	 */
	hotshare.fs = function(str) {
		for ( var i = 0; i < arguments.length - 1; i++) {
			str = str.replace("{" + i + "}", arguments[i + 1]);
		}
		return str;
	};

	/**
	 * 
	 * 扩展命名空间功能
	 * 
	 * 使用方法：sy.ns('jQuery.bbb.ccc','jQuery.eee.fff');
	 */
	hotshare.ns = function() {
		var o = {}, d;
		for ( var i = 0; i < arguments.length; i++) {
			d = arguments[i].split(".");
			o = window[d[0]] = window[d[0]] || {};
			for ( var k = 0; k < d.slice(1).length; k++) {
				o = o[d[k + 1]] = o[d[k + 1]] || {};
			}
		}
		return o;
	};

	/**
	 * 扩展方法,获得项目根路径
	 * 
	 * 使用方法：hotshare.bp();
	 */
	hotshare.bp = function() {
		var curWwwPath = window.document.location.href;
		var pathName = window.document.location.pathname;
		var pos = curWwwPath.indexOf(pathName);
		var localhostPaht = curWwwPath.substring(0, pos);
		var projectName = pathName.substring(0,
				pathName.substr(1).indexOf('/') + 1);
		return (localhostPaht + projectName);
	};

	/**
	 * 扩展JS的Date对象
	 * 
	 * 将 Date 转化为指定格式的String
	 * 
	 * 月(M)、日(d)、12小时(h)、24小时(H)、分(m)、秒(s)、周(E)、季度(q) 可以用 1-2 个占位符
	 * 
	 * 年(y)可以用 1-4 个占位符，毫秒(S)只能用 1 个占位符(是 1-3 位的数字)
	 * 
	 * (new Date()).pattern("yyyy-MM-dd hh:mm:ss.S") ==> 2006-07-02 08:09:04.423
	 * 
	 * (new Date()).pattern("yyyy-MM-dd E HH:mm:ss") ==> 2009-03-10 二 20:09:04
	 * 
	 * (new Date()).pattern("yyyy-MM-dd EE hh:mm:ss") ==> 2009-03-10 周二 08:09:04
	 * 
	 * (new Date()).pattern("yyyy-MM-dd EEE hh:mm:ss") ==> 2009-03-10 星期二
	 * 08:09:04
	 * 
	 * (new Date()).pattern("yyyy-M-d h:m:s.S") ==> 2006-7-2 8:9:4.18
	 */
	Date.prototype.pattern = function(fmt) {
		var o = {
			"M+" : this.getMonth() + 1, /* 月份 */
			"d+" : this.getDate(), /* 日 */
			"h+" : this.getHours() % 12 == 0 ? 12 : this.getHours() % 12, /* 小时 */
			"H+" : this.getHours(), /* 小时 */
			"m+" : this.getMinutes(), /* 分 */
			"s+" : this.getSeconds(), /* 秒 */
			"q+" : Math.floor((this.getMonth() + 3) / 3), /* 季度 */
			"S" : this.getMilliseconds()
		/* 毫秒 */
		};
		var week = {
			"0" : "\u65e5",
			"1" : "\u4e00",
			"2" : "\u4e8c",
			"3" : "\u4e09",
			"4" : "\u56db",
			"5" : "\u4e94",
			"6" : "\u516d"
		};
		if (/(y+)/.test(fmt)) {
			fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "")
					.substr(4 - RegExp.$1.length));
		}
		if (/(E+)/.test(fmt)) {
			fmt = fmt
					.replace(
							RegExp.$1,
							((RegExp.$1.length > 1) ? (RegExp.$1.length > 2 ? "\u661f\u671f"
									: "\u5468")
									: "")
									+ week[this.getDay() + ""]);
		}
		for ( var k in o) {
			if (new RegExp("(" + k + ")").test(fmt)) {
				fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k])
						: (("00" + o[k]).substr(("" + o[k]).length)));
			}
		}
		return fmt;
	};

	/**
	 * 解决IE6背景图片缓存问题
	 */
	try {
		document.execCommand("BackgroundImageCache", false, true);
	} catch (e) {

	}

	/**
	 * 扩展生成UUID方法
	 * 
	 * 使用方法 hotshare.uuid()
	 */
	hotshare.uuid = function() {
		var s = [], itoh = '0123456789ABCDEF';
		for ( var i = 0; i < 36; i++)
			s[i] = Math.floor(Math.random() * 0x10);
		s[14] = 4;
		s[19] = (s[19] & 0x3) | 0x8;
		for ( var i = 0; i < 36; i++)
			s[i] = itoh[s[i]];
		s[8] = s[13] = s[18] = s[23] = '-';
		return s.join('');
	};

	$.ajaxSetup({
		error : function(XMLHttpRequest, textStatus, errorThrown) { /* 扩展AJAX出出现错误的提示 */
			$.messager.alert('系统提示', "服务器异常,请联系管理员(" + XMLHttpRequest.status
					+ ")", 'error'); // 和Jquery easyui的提示框绑定
		}
	});

	/**
	 * ajax发送请求提示用户信息
	 */
	hotshare.showAjaxLoding = function() {
		var loading = $("#ajaxLoding");
		if (loading.length < 1) {
			$("body")
					.append(
							'<div id="ajaxLoding" style="z-index: 9999999; position: absolute; top: 0px; right: 0px; background-color:#FFFEE6; color:#8F5700; padding:5px;"><div>数据处理中,请稍后...</div></div>');
		} else {
			loading.show();
		}
	};
	/**
	 * ajax发送请求完毕隐藏信息
	 */
	hotshare.hidenAjaxLoding = function() {
		$('#ajaxLoding').fadeOut(500);
	};

	/**
	 * 封装Jquery的Ajax方法
	 * 
	 */
	hotshare.ajax = function(options) {
		$.ajax({
			url : options.url,
			data : options.data,
			dataType : 'json',
			type : 'POST',
			beforeSend : function(XMLHttpRequest) {
				hotshare.showAjaxLoding();
			},
			success : function(response) {
				options.success(response);
			},
			complete : function(XMLHttpRequest, textStatus) {
				hotshare.hidenAjaxLoding();
			},
			error : function(response) {
				hotshare.hidenAjaxLoding();
				hotshare.showMsg('系统提示', '服务器繁忙,请联系系统管理人员(' + response.status
						+ ')', 'error');
			}
		});
	}

	hotshare.showMsg = function(title, msg, type) {
		title = title || "系统提示";
		type = type || "info";
		$.messager.alert(title, msg, type);
	}

	hotshare.showConfirmMsg = function(title, msg, savedCallback) {
		$.messager.confirm(title, msg, function(r) {
			if (r) {
				savedCallback();
			}
		});
	}
	hotshare.showPromptMsg = function(title, desc, savedCallback) {
		$.messager.prompt(title, desc, function(r) {
			if (r)
				savedCallback(r);
		});
	}

	hotshare.location = function(url) {
		window.location.href = url;
	}
	hotshare.back = function() {
		window.history.back();
	}
	hotshare.go = function(number) {
		history.go(number);
	}
	hotshare.submitForm = function(options) {
		$("#" + options.clickButtion)
				.click(
						function() {
							$("#" + options.form)
									.submit(
											function() {
												$(this)
														.ajaxSubmit(
																{
																	dataType : 'json',
																	success : function(
																			data) {
																		if (data.dataArea[0].result == 0) {
																			hotshare
																					.showMsg(
																							"系统提示",
																							options.successMsg,
																							'info');
																			hotshare
																					.location(options.successUrl);
																		} else {
																			hotshare
																					.showMsg(
																							"系统提示",
																							options.errorMsg,
																							'info');
																		}
																	}
																});
												return false;
											});
						});
	}
	hotshare.getUrlParam = function(name) {
		var erg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
		var r = window.location.search.substr(1).match(reg);
		if (r != null)
			return unescape(r[2]);
		return null;
	}
	hotshare.loadData = function(options) {
		$('#' + options.form).form('load', options.data);
	}
	hotshare.toFixed = function(number) {
		return number.replace(/([0-9]+\.[0-9]{1})[0-9]*/, "$1");
	}
	hotshare.getDateStr = function(date) {
		var year = date.getFullYear();
		var month = (date.getMonth() + 1);
		var day = date.getDate();
		return year + "-" + month + "-" + day;
	}
	hotshare.getDateStr2 = function(date) {
		var year = date.getFullYear() + 1;
		var month = date.getMonth() + 1;
		var day = date.getDate();
		return year + "-" + month + "-" + day;
	}
	hotshare.getParamsByForm = function(form) {
		var formParams = {};
		var $form = $(form).serializeArray();
		$.each($form, function() {
			if ($.trim(this.value)) {
				formParams[this.name] = $.trim(this.value);
			}
		});
		return formParams;
	}
})(jQuery);
