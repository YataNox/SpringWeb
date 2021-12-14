package com.ezen.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.ezen.shop.service.QnaService;

@Controller
public class QnaController {
	@Autowired
	QnaService qs;
}
