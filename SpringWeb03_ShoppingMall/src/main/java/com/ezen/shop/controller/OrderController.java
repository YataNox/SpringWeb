package com.ezen.shop.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;
import com.ezen.shop.service.CartService;
import com.ezen.shop.service.OrderService;

@Controller
public class OrderController {
	@Autowired
	OrderService os;
	
	@Autowired
	CartService cs;
	
	@RequestMapping(value="/orderInsert")
	public String orderInsert(HttpServletRequest request) {
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		int oseq = 0;
		if(mvo == null) {
			return "member/login";
		}else {
			// 현재 로그인 중인 사용자의 아이디로 카트리스트 조회
			List<CartVO> cartList = cs.listCart(mvo.getId());
			
			// 카트 리스트를 주문으로 추가
			oseq = os.insertOrder(cartList, mvo.getId());
			
			// 방금 추가한 주문 번호를 리턴받아 오더 리스트의 주문번호로 적용합니다..
		}
		return "redirect:/orderList?oseq=" + oseq;
	}
	
	@RequestMapping(value="/orderList")
	public ModelAndView orderList(HttpServletRequest request, @RequestParam("oseq") int oseq) {
		ModelAndView mav = new ModelAndView();
		HttpSession session = request.getSession();
		MemberVO mvo = (MemberVO)session.getAttribute("loginUser");
		if(mvo == null) {
			mav.setViewName("member/login");
		}else {
			List<OrderVO> list = os.listOrderByOseq(oseq);
			 int totalPrice=0;
	         for(OrderVO ovo : list)
	            totalPrice += ovo.getPrice2() * ovo.getQuantity();
	         mav.addObject("orderList", list);
	         mav.addObject("totalPrice", totalPrice);
	         mav.setViewName("mypage/orderList");

		}
		
		return mav;
	}
}
