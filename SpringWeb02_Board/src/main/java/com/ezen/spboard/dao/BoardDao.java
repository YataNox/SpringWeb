package com.ezen.spboard.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ezen.spboard.dto.SpBoard;
import com.ezen.spboard.util.DataBaseManager;

@Repository
public class BoardDao {
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	
	@Autowired
	DataBaseManager dbm;

	// Board 전체 조회
	public ArrayList<SpBoard> selectBoard() {
		ArrayList<SpBoard> list = new ArrayList<SpBoard>();
		String sql = "select * from spboard order by num desc";
		con = dbm.getConnection();
		try {
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				SpBoard sb = new SpBoard();
				sb.setNum(rs.getInt("num"));
				sb.setPass(rs.getString("pass"));
				sb.setUserid(rs.getString("userid"));
				sb.setEmail(rs.getString("email"));
				sb.setTitle(rs.getString("title"));
				sb.setContent(rs.getString("content"));
				sb.setReadcount(rs.getInt("readcount"));
				sb.setWritedate(rs.getTimestamp("writedate"));
				sb.setReplycnt(rs.getInt("replycnt"));
				sb.setImagename(rs.getString("imagename"));
				list.add(sb);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}finally {
			dbm.close(con, pstmt, rs);
		}
		
		return list;
	}
	
}
