package com.hotshare.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 系统主界面
 * 
 * @author lhzh
 * @create 2013-1-19 下午2:11:12
 * @since
 */
@Controller
@RequestMapping(value = "/main")
public class MainController {

	@RequestMapping(value = "/index")
	public String toIndex(HttpServletRequest request, HttpServletResponse response, Model model) {
		return "WEB-INF/index";
	}
}
