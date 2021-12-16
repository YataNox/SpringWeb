package com.ezen.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dao.AdminDao;
import com.ezen.shop.dto.Paging;
import com.ezen.shop.dto.ProductVO;

@Service
public class AdminService {
	@Autowired
	AdminDao adao;

	public int workerCheck(String workId, String workPwd) {
		return adao.workerCheck(workId, workPwd);
	}

	public List<ProductVO> listProduct(Paging paging) {
		return adao.listProduct(paging);
	}

	public int getAllCount(String tablename) {
		return adao.getAllCount(tablename);
	}
}
