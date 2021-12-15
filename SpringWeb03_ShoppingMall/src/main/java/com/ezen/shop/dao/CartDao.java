package com.ezen.shop.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.ezen.shop.dto.CartVO;
import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class CartDao {
	private JdbcTemplate template;
	
	@Autowired
	public CartDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	public void insertCart(CartVO cvo) {
		String sql = "insert into cart(cseq, id, pseq, quantity)"
				+ " values(cart_seq.nextVal, ?, ?, ?)";
		
		int result = template.update(sql, cvo.getId(), cvo.getPseq(), cvo.getQuantity());
	}

	public List<CartVO> listCart(String id) {
		String sql = "select * from cart_view where id = ?";
		
		List<CartVO> list = template.query(sql, new RowMapper<CartVO>() {
			@Override
			public CartVO mapRow(ResultSet rs, int rowNum) throws SQLException {
				CartVO cvo = new CartVO();
				cvo.setCseq(rs.getInt("cseq"));
				cvo.setId(rs.getString("id"));
				cvo.setMname(rs.getString("mname"));
				cvo.setPseq(rs.getInt("pseq"));
				cvo.setPname(rs.getString("pname"));
				cvo.setQuantity(rs.getInt("quantity"));
				cvo.setPrice2(rs.getInt("price2"));
				cvo.setResult(rs.getString("result"));
				cvo.setIndate(rs.getTimestamp("indate"));
				return cvo;
			}
		}, id);
		return list;
	}

	public void deleteCart(String cseq) {
		String sql = "delete from cart where cseq=?";
		
		template.update(sql, cseq);
	}
}
