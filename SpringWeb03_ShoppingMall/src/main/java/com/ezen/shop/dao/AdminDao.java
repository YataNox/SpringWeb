package com.ezen.shop.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Repository
public class AdminDao {
private JdbcTemplate template;
	
	@Autowired
	public AdminDao(ComboPooledDataSource dataSource) {
		this.template = new JdbcTemplate(dataSource);
	}

	public int workerCheck(String workId, String workPwd) {
		
		return 0;
	}
}
