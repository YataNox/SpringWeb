package com.ezen.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.shop.service.MemberService;

@Controller
public class MemberController {
	@Autowired
	MemberService ms;
}
