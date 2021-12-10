package com.ezen.spboard.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.spboard.dto.SpBoard;
import com.ezen.spboard.service.BoardService;

@Controller
public class BoardController{
	
	@Autowired
	BoardService bs;
	// Controller에서 Service의 메소드 호출 -> Service의 메소드에서 Dao의 메소드 호출
	// Dao의 메소드에서 Service의 메소드로 리턴 -> Service 메소드들에서 Controller 메소드로 리턴
	// 리턴받은 내용을 model에 싣고, ~.jsp로 이동
	@RequestMapping(value="/main")
	public String main(Model model, HttpServletRequest request) {
		
		ArrayList<SpBoard> list = bs.selectBoard();
		model.addAttribute("boardList", list);
		// 게시물 조회 후 main.jsp로 이동
		return "main";
	}
}
