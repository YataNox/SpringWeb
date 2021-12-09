package com.ezen.spboard.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.spboard.dto.SpMember;
import com.ezen.spboard.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	MemberService ms;
	
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
	
	// loginform.jsp에서 로그인시 작동할 Mapping
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String login(Model model, HttpServletRequest request) {
		
		// 이동할 jsp파일 url
		String url = "loginform";
		// 전달받은 id, pw값 
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		SpMember sdto = ms.getMember(id);
		
		if(sdto == null) {
			model.addAttribute("message", "id를 확인하세요.");
		}else if(sdto.getPw() == null) {
			model.addAttribute("message", "비밀번호 오류. 관리자에게 문의하세요.");
		}else if(!sdto.getPw().equals(pw)) {
			model.addAttribute("message", "비밀번호가 틀립니다.");
		}else if(sdto.getPw().equals(pw)){
			HttpSession session = request.getSession();
			session.setAttribute("loginUser", sdto);
			url = "main";
		}else {
			model.addAttribute("message", "알 수 없는 이유로 로그인 실패");
		}
		
		return url;
	}
}
