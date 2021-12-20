package com.ezen.shop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.shop.dao.AdminDao;
import com.ezen.shop.dto.MemberVO;
import com.ezen.shop.dto.OrderVO;
import com.ezen.shop.dto.Paging;
import com.ezen.shop.dto.ProductVO;
import com.ezen.shop.dto.QnaVO;

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

	public void updateProduct(ProductVO pvo) {
		adao.updateProduct(pvo);
	}

	public List<OrderVO> listOrder(Paging paging, String key) {
		return adao.listOrder(paging, key);
	}

	public void saveOrderResult(String re) {
		adao.saveOrderResult(re);		
	}

	public List<MemberVO> listMember(Paging paging, String key) {
		return adao.listMember(paging, key);
	}

	public List<QnaVO> listQna(Paging paging, String key) {
		return adao.listQna(paging, key);
	}

	public void updateQna(QnaVO qvo) {
		adao.updateQna(qvo);
	}
}
