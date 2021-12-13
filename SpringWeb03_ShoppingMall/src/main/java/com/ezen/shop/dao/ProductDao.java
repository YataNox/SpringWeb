package com.ezen.shop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class ProductDao {
	private JdbcTemplate template;
	// 데이터베이스 운영 객체이며, 이전에 사용하던 con, pstmt, rs가 모두 존재합니다.
	
	// 사용하면 com.mchange.v2.c3p0.ComboPooledDataSource 객체를 꺼내와서
	// template에 초기화해줘야 합니다.
	
	// 스프링 컨테이너에 수동으로 넣어놓은 DBCP 클래스를 꺼내옵니다.
	@Autowired
	ComboPooledDataSource dataSource;
	
	// 생성자 메소드와 꺼내온 DBCP 빈을 이용하여 탬플릿을 초기화합니다.
	public ProductDao(){
		this.template = new JdbcTemplate(dataSource);
	}
	
	// 이로써 연결을 이용한 데이터베이스 운용이 가능해졌습니다.
}
