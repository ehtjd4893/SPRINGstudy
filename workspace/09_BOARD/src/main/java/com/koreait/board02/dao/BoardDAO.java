package com.koreait.board02.dao;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;

import com.koreait.board02.dto.Board;

public class BoardDAO {
	
	// BoardDAO boardDAO bean은 스프링에 의해서 singleton으로 생성된다.
	
	@Autowired
	private JdbcTemplate template;
	private String sql;
	
	// list
	public List<Board> selectBoardList(){
		sql = "SELECT NO, WRITER, TITLE, CONTENT, POSTDATE FROM BOARD ORDER BY NO DESC";
		return template.query(sql, new BeanPropertyRowMapper<Board>(Board.class)); 
	}
	
	// view
	public Board selectBoardByNo(Long no) {
		sql = "SELECT NO, WRITER, TITLE, CONTENT, POSTDATE FROM BOARD WHERE NO = ?";
		return template.queryForObject(sql, new BeanPropertyRowMapper<Board>(Board.class), no);
	}
	
	// update
	public void updateBoard(Board board) {
		sql = "UPDATE BOARD SET TITLE = ?, CONTENT = ? WHERE NO = ? ";
		template.update(sql,new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, board.getTitle());
				ps.setString(2, board.getContent());
				ps.setLong(3, board.getNo());
			}
		});
	}
	
	// delete
	public void deletBoard(long no) {
		sql = "DELETE FROM BOARD WHERE NO = ?";
		template.update(sql, new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setLong(1, no);
			}
		});
	}

	public void insertBoard(Board board) {
		sql = "INSERT INTO BOARD VALUES(BOARD_SEQ.NEXTVAL, ?, ?, ?, SYSDATE)";
		template.update(sql, new PreparedStatementSetter() {		
			@Override
			public void setValues(PreparedStatement ps) throws SQLException {
				ps.setString(1, board.getWriter());
				ps.setString(2, board.getTitle());
				ps.setString(3, board.getContent());
				
			}
		});		
	}
}
