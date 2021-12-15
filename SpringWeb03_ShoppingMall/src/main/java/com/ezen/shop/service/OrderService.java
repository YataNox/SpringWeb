package com.ezen.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dao.OrderDao;
import com.ezen.shop.dto.CartVO;
import com.ezen.shop.dto.OrderVO;

@Service
public class OrderService {
	@Autowired
	OrderDao odao;

	public int insertOrder(List<CartVO> cartList, String id) {
		// orders 테이블에 레코드 추가
		odao.insertOrder(id); // 아이디와 주문 날짜(현재날짜)이용
		
		// order_detail 테이블에 레코드 추가시 같이 입력될 orders 테이블의 주문번호 조회
		int oseq = odao.LookupMaxOseq(); // orders 테이블에서 가장 큰 oseq(마지막에 추가된) 조회
		
		// cartList에 있는 상품을 하나하나 order_detail 테이블에 추가합니다. 위의 oseq와 함께
		for(CartVO cvo : cartList) {
			odao.insertOrderDetail(cvo, oseq);
			odao.deleteCart(cvo.getCseq()); // 추가된 주문은 장바구니에서 삭제합니다.
		}
		return oseq;
	}

	public List<OrderVO> listOrderByOseq(int oseq) {
		 return odao.listOrderByOseq(oseq);
	}
}
