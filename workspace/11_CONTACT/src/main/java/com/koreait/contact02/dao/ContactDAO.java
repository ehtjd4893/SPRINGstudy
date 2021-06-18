package com.koreait.contact02.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.koreait.contact02.dto.Contact;

public class ContactDAO {
	
	@Autowired
	private JdbcTemplate template;
	private String sql;
	
	
	public ContactDAO(JdbcTemplate template) {
		this.template = template;
	}


	public List<Contact> selectList() {
		sql = "SELECT NO, NAME, TEL, ADDR, EMAIL FROM CONTACT";
		return template.query(sql, new BeanPropertyRowMapper<Contact>(Contact.class));
	}

	public void insert(final Contact c) {
		sql = "INSERT INTO CONTACT VALUES(CONTACT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, c.getName());
				ps.setString(2, c.getTel());
				ps.setString(3, c.getAddr());
				ps.setString(4, c.getEmail());
				ps.setString(5, c.getNote());
			}
		});
	}

	public Contact selectByNo(long no) {
		sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT WHERE NO = ?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<Contact>(Contact.class), no);
	}

	public void update(final Contact c) {
		sql = "UPDATE CONTACT SET NAME = ?, TEL = ?, ADDR = ?, EMAIL = ?, NOTE = ? WHERE NO = ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, c.getName());
				ps.setString(2, c.getTel());
				ps.setString(3, c.getAddr());
				ps.setString(4, c.getEmail());
				ps.setString(5, c.getNote());
				ps.setLong(6, c.getNo());
			}
		});
	}

	public void deleteByNo(long no) {
		sql = "DELETE FROM CONTACT WHERE NO = ?";
		template.update(sql, no);
	}
}
