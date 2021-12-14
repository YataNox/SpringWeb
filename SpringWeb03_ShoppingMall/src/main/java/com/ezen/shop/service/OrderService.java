package com.ezen.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dao.OrderDao;

@Service
public class OrderService {
	@Autowired
	OrderDao odao;
}
