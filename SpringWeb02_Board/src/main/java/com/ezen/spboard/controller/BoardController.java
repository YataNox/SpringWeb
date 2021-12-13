package com.ezen.spboard.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ezen.spboard.dto.ReplyVO;
import com.ezen.spboard.dto.SpBoard;
import com.ezen.spboard.service.BoardService;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

@Controller
public class BoardController{
	
	@Autowired
	BoardService bs;
	// Controller에서 Service의 메소드 호출 -> Service의 메소드에서 Dao의 메소드 호출
	// Dao의 메소드에서 Service의 메소드로 리턴 -> Service 메소드들에서 Controller 메소드로 리턴
	// 리턴받은 내용을 model에 싣고, ~.jsp로 이동
	
	@Autowired
	ServletContext context; // Session을 통하지 않고, 스프링 컨테이너에 있는 빈으로 가져다씁니다.
	
	@RequestMapping(value="/main")
	public String main(Model model, HttpServletRequest request) {
		
		ArrayList<SpBoard> list = bs.selectBoard();
		model.addAttribute("boardList", list);
		// 게시물 조회 후 main.jsp로 이동
		return "main";
	}
	
	@RequestMapping(value="/boardView")
	public String boardView(Model model, HttpServletRequest request) {
		String num = request.getParameter("num");
		SpBoard sb = bs.boardView(num);
		model.addAttribute("board", sb);
		
		ArrayList<ReplyVO> list = bs.selectReply(num);
		model.addAttribute("replyList", list);
		
		return "board/boardView";
	}
	
	@RequestMapping(value="/addReply")
	public String addReply(Model model, HttpServletRequest request) {
		String boardnum = request.getParameter("boardnum");
		
		ReplyVO rvo = new ReplyVO();
		rvo.setUserid(request.getParameter("userid"));
		rvo.setContent(request.getParameter("reply"));
		rvo.setBoardnum(Integer.parseInt(boardnum));
		
		bs.addReply(rvo);
		return "redirect:/boardViewWithoutcount?num=" + boardnum;
	}
	
	@RequestMapping(value="/boardViewWithoutcount")
	public String boardViewWithoutcount(Model model, HttpServletRequest request) {
		String num = request.getParameter("num");
		SpBoard sb = bs.getBoard(num);
		model.addAttribute("board", sb);
		ArrayList<ReplyVO> list = bs.selectReply(num);
		model.addAttribute("replyList", list);
		return "board/boardView";
	}
	
	@RequestMapping(value="/deleteReply")
	public String reply_delete(Model model, HttpServletRequest request) {
		String num = request.getParameter("num");
		String boardnum = request.getParameter("boardnum");
		
		bs.deleteReply(num);
		return "redirect:/boardViewWithoutcount?num=" + boardnum;
	}
	
	@RequestMapping(value="/boardWriteForm")
	public String write_form(Model model, HttpServletRequest request) {
		String url = "board/boardWriteForm";
		HttpSession session = request.getSession();
		if(session.getAttribute("loginUser") == null)
			url = "loginform";
		return url;
	}
	
	@RequestMapping(value="/boardWrite")
	public String board_write(Model model, HttpServletRequest request) {
		// HttpSession session = request.getSession();
		// ServletContext context = session.getServletContext();
		// session을 거치지않고 스프링 컨테이너에 있는 빈형태의 ServletContext를 가져다씁니다.
		String path = context.getRealPath("resources/upload");
		
		try {
			MultipartRequest multi = new MultipartRequest(request, path, 1024*1024*5, "UTF-8", new DefaultFileRenamePolicy());
			
			SpBoard sb = new SpBoard();
			sb.setPass(multi.getParameter("pw"));
			sb.setUserid(multi.getParameter("userid"));
			sb.setEmail(multi.getParameter("email"));
			sb.setTitle(multi.getParameter("title"));
			sb.setContent(multi.getParameter("content"));
			sb.setImagename(multi.getParameter("imgfilename"));
			
			bs.insertBoard(sb);
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		return "redirect:/main";
	}
	
	@RequestMapping(value="/boardEditForm")
	public String board_edit_form(Model model, HttpServletRequest request) {
		String num = request.getParameter("num");
		model.addAttribute("num", num);
		return "board/boardCheckPassForm";
	}
}
