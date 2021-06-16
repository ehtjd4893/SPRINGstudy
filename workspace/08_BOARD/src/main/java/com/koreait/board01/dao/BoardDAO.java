package com.koreait.board01.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;
import javax.naming.Context;
import javax.naming.InitialContext;

import com.koreait.board01.dto.Board;

public class BoardDAO {
	
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;
	private String sql;
	
	private DataSource dataSource;
	private static BoardDAO instance;
	private BoardDAO() {
		try {
            Context context = new InitialContext();
         
            dataSource = (DataSource) context.lookup("java:comp/env/jdbc/oracle");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
		
	public static BoardDAO getInstance() {
		if(instance == null)
			instance = new BoardDAO();
		return instance;
	}
	
	public void close(Connection con, PreparedStatement ps, ResultSet rs) {
		try {
			if(con != null) con.close();
			if(ps != null) ps.close();
			if(rs != null) rs.close();			
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public List<Board> selectBoardList(){
		List<Board> list = new ArrayList<Board>();
		try {
			con = dataSource.getConnection(); 
			sql = "SELECT NO, WRITER, TITLE, CONTENT, POSTDATE FROM BOARD ORDER BY NO DESC";
			ps = con.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				Board board = new Board();
				board.setNo(rs.getLong(1));
				board.setWriter(rs.getString(2));
				board.setTitle(rs.getString(3));
				board.setContent(rs.getString(4));
				board.setPostdate(rs.getDate(5));
				list.add(board);
			}
		}catch (Exception e) {
			e.printStackTrace();
		} finally {
			close(con, ps, rs);
		}
		return list;
	}
	
	public void insertBoard(Board board) {
		try {
			con = dataSource.getConnection();
			sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
			ps = con.prepareStatement(sql);
			ps.setString(1, board.getWriter());
			ps.setString(2, board.getTitle());
			ps.setString(3, board.getContent());
			ps.executeUpdate();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con, ps, null);
		}
	}

	public Board selectBoardByNo(Long no) {
		Board b = null;
		try {
			con = dataSource.getConnection();
			sql = "SELECT NO, WRITER, TITLE, CONTENT, POSTDATE FROM BOARD WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			rs = ps.executeQuery();
			if(rs.next()) {
				b = new Board();
				b.setNo(rs.getLong(1));
				b.setWriter(rs.getString(2));
				b.setTitle(rs.getString(3));
				b.setContent(rs.getString(4));
				b.setPostdate(rs.getDate(5));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con,ps,rs);
		}
		
		return b;
	}

	public void updateBoard(Board b) {
		try {
			con = dataSource.getConnection();
			sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setString(1, b.getTitle());
			ps.setString(2, b.getContent());
			ps.setLong(3, b.getNo());
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
	}

	public void deleteBoard(long no) {
		try {
			con = dataSource.getConnection();
			sql = "DELETE FROM BOARD WHERE NO = ?";
			ps = con.prepareStatement(sql);
			ps.setLong(1, no);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			close(con,ps,null);
		}
		
	}
}
