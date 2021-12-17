package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ezen.shop.dto.Paging;
import com.ezen.shop.dto.ProductVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class AdminDao {
private JdbcTemplate template;
	
	@Autowired
	public AdminDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	public int workerCheck(String workId, String workPwd) {
		int result = 0;
		String sql = "select pwd from worker where id=?";
		List<String> list = template.query(sql, new RowMapper<String>() {
			@Override
			public String mapRow(ResultSet rs, int rowNum) throws SQLException {
				String pwd = rs.getString("pwd");
				return pwd;
			}
		}, workId);
		if(list.size() == 0) {
			result = -1;
		}else if(workPwd.equals(list.get(0))) {
			result = 1;
		}else {
			result = 0;
		}
		return result;
	}

	public List<ProductVO> listProduct(Paging paging, String key) {
		String sql = "select * from ("
				+ "select * from ("
				+ "select rownum as rn, p.* from "
				+ "((select * from product where name like '%'||?||'%' order by pseq desc) p)"
				+ ") where rn >= ?"
				+ ") where rn <= ?";
		
		List<ProductVO> list = template.query(sql, new RowMapper<ProductVO>() {
			@Override
			public ProductVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProductVO pvo = new ProductVO();
				pvo.setPseq(rs.getInt("pseq"));
				pvo.setIndate(rs.getTimestamp("indate"));
				pvo.setName(rs.getString("name"));
				pvo.setPrice1(rs.getInt("price1"));
				pvo.setPrice2(rs.getInt("price2"));
				pvo.setPrice3(rs.getInt("price3"));
				pvo.setImage(rs.getString("image"));
				pvo.setUseyn(rs.getString("useyn"));
				pvo.setBestyn(rs.getString("bestyn"));
				return pvo;
			}
		}, key, paging.getStartNum(), paging.getEndNum());
		
		return list;
	}

	public int getAllCount(String tablename, String fieldname, String key) {
		String sql = "select count(*) as count from " + tablename + " where " + fieldname + " like '%'||?||'%'";
		
		List<Integer> list = template.query(sql, new RowMapper<Integer>() {
			@Override
			public Integer mapRow(ResultSet rs, int rowNum) throws SQLException {
				int count = rs.getInt("count");
				return count;
			}
		}, key);
		
		return list.get(0);
	}

	public void insertProduct(ProductVO pvo) {
		String sql = "insert into product(pseq, kind, name, content, price1, price2, price3, image) "
				+ "values(product_seq.nextVal, ?, ?, ?, ?, ?, ?, ?)";
		
		template.update(sql, pvo.getKind(), pvo.getName(), pvo.getContent(), pvo.getPrice1(),
				pvo.getPrice2(), pvo.getPrice3(), pvo.getImage());
	}
}
