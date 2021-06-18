package com.koreait.contact01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.koreait.contact01.dto.Contact;

public class ContactDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private DataSource dataSource;
	
	private static ContactDAO instance;
	
	private ContactDAO() {
		try {
            Context context = new InitialContext();
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static ContactDAO getInstance() {
		if(instance == null)
			instance = new ContactDAO();
		return instance;
	}
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null) con.close();
			if(ps != null) ps.close();
			if(rs != null) rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}

	public List<Contact> selectList() {
		List<Contact> list = new ArrayList<Contact>();
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, NAME, TEL, ADDR, EMAIL FROM CONTACT";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Contact c = new Contact();
				c.setNo(rs.getLong(1));
				c.setName(rs.getString(2));
				c.setTel(rs.getString(3));
				c.setAddr(rs.getString(4));
				c.setEmail(rs.getString(5));
				list.add(c);
				System.out.println(c.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}

	public void insert(Contact c) {
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO CONTACT VALUES(CONTACT_SEQ.NEXTVAL, ?, ?, ?, ?, ?)";
			ps = con.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setString(2, c.getTel());
			ps.setString(3, c.getAddr());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getNote());
			ps.executeUpdate();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
	}

	public Contact selectByNo(long no) {
		Contact c = new Contact();
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, NAME, TEL, ADDR, EMAIL, NOTE FROM CONTACT WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				c.setNo(rs.getLong(1));
				c.setName(rs.getString(2));
				c.setTel(rs.getString(3));
				c.setAddr(rs.getString(4));
				c.setEmail(rs.getString(5));
				c.setNote(rs.getString(6));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return c;
	}

	public void update(Contact c) {
		try {
			con = dataSource.getConnection();
			sql = "UPDATE CONTACT SET NAME = ?, TEL = ?, ADDR = ?, EMAIL = ?, NOTE = ? WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, c.getName());
			ps.setString(2, c.getTel());
			ps.setString(3, c.getAddr());
			ps.setString(4, c.getEmail());
			ps.setString(5, c.getNote());
			ps.setLong(6, c.getNo());
			ps.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
	}

	public void deleteByNo(long no) {
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM CONTACT WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		
	}
}
