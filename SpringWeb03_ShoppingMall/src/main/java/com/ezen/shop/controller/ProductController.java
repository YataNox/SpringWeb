package com.ezen.shop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.ezen.shop.service.ProductService;

@Controller
public class ProductController {
	@Autowired
	ProductService ps;
	
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String index(Model model) {
		// List<ProductVO> nlist = ps.getNewList();
		// List<ProductVO> blist = ps.getBestList();
		// List는 ArrayList의 부모 객체입니다.
		// 우리가 이 프로젝트에서 사용하려고 하는 템플릿(데이터베이스 객체)가 List만 지원할 수 있어서
		// List를 사용합니다.
		
		model.addAttribute("newProductList", ps.getNewList());
		model.addAttribute("bestProductList", ps.getBestList());
		
		return "index";
	}
}
