package com.koreait.integration1.repository;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.koreait.integration1.domain.Search;

@Repository
public class SearchRepository {

	@Autowired
	private SqlSession sqlSession;

	public List<Search> selectAll() {
		return sqlSession.selectList("com.koreait.integration1.repository.search.selectAll");
	}

	public List<Search> selectQuery(Map<String, String> map) {
		return sqlSession.selectList("com.koreait.integration1.repository.search.selectQuery", map);
	}
	
	
}
