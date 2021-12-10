package com.ezen.spboard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spboard.dao.BoardDao;
import com.ezen.spboard.dao.MemberDao;
import com.ezen.spboard.dto.SpBoard;

@Service
public class BoardService {
	@Autowired
	BoardDao bdao;
	
	public ArrayList<SpBoard> selectBoard(){
		ArrayList<SpBoard> list = bdao.selectBoard();
		
		return list;
	}

}
