package com.ezen.spboard.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spboard.dao.BoardDao;
import com.ezen.spboard.dto.ReplyVO;
import com.ezen.spboard.dto.SpBoard;

@Service
public class BoardService {
	@Autowired
	BoardDao bdao;
	
	public ArrayList<SpBoard> selectBoard(){
		ArrayList<SpBoard> list = bdao.selectBoard();
		
		return list;
	}

	public SpBoard boardView(String num) {
		bdao.plusReadCount(num);
		return bdao.getBoard(num);
	}

	public ArrayList<ReplyVO> selectReply(String num) {
		return bdao.selectReply(num);
	}

}
