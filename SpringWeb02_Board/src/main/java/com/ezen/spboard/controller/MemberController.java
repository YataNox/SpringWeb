package com.ezen.spboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MemberController {
	
	/* @RequestMapping(value="/", method=RequestMethod.GET) */
	@RequestMapping(value="/")
	public String firstRequest(Model model, HttpServletRequest request) {
		
		// 세션 객체를 전달 받아
		HttpSession session = request.getSession();
		
		// 로그인 유저가 없다면
		if(session.getAttribute("loginUser") == null)
			return "loginform"; // loginform.jsp 파일로 이동
		else // 있다면
			return "main"; // main.jsp파일로 이동
	}
}
