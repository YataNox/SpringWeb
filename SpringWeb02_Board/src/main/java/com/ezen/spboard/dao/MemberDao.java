package com.ezen.spboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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

	// 매개변수 id값을 가진 Member 리턴
	public SpMember getMember(String id) {
		SpMember sdto = null;
		con = dbm.getConnection();
		String sql = "select * from spmember where id = ?";
		
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, id);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				sdto = new SpMember();
				sdto.setId(id);
				sdto.setPw(rs.getString("pw"));
				sdto.setName(rs.getString("name"));
				sdto.setEmail(rs.getString("email"));
				sdto.setPhone1(rs.getString("phone1"));
				sdto.setPhone2(rs.getString("phone2"));
				sdto.setPhone3(rs.getString("phone3"));
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
		return sdto;
	}

}
