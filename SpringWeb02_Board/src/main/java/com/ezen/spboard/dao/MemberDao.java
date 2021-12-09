package com.ezen.spboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.spboard.dto.SpMember;
import com.ezen.spboard.util.DataBaseManager;

@Repository
public class MemberDao {
	
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Autowired
	DataBaseManager dbm;

	public SpMember getMember(String id) {
		SpMember sdto = null;
		con = dbm.getConnection();
		
		dbm.close(con, pstmt, rs);
		return sdto;
	}

}
