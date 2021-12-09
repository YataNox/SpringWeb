package com.ezen.spboard.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ezen.spboard.dao.MemberDao;
import com.ezen.spboard.dto.SpMember;

@Service
public class MemberService {

	@Autowired
	MemberDao mdao;
	
	public SpMember getMember(String id) {
		SpMember sdto = mdao.getMember(id);
		return sdto;
	}

}
