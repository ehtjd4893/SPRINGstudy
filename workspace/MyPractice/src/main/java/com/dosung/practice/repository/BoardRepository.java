package com.dosung.practice.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dosung.practice.domain.Board;

@Repository
public class BoardRepository{
	
	@Autowired
	private SqlSession sqlSession;
	
	
	public List<Board> selectAll(){
		return sqlSession.selectList("com.dosung.practice.repository.board.selectAll");
	}
	
	public List<Board> selectQuery(Map<String, String> map){
		return sqlSession.selectList("com.dosung.practice.repository.board.selectQuery", map);
	}
}
