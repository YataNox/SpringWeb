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

	public List<ProductVO> listProduct(Paging paging, String key) {
		return adao.listProduct(paging, key);
	}

	public int getAllCount(String tablename, String fieldname, String key) {
		return adao.getAllCount(tablename, fieldname, key);
	}

	public void insertProduct(ProductVO pvo) {
		adao.insertProduct(pvo);
	}
}
