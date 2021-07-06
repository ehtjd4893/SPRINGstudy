package com.koreait.integration1.junit;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.integration1.domain.Search;
import com.koreait.integration1.repository.SearchRepository;

public class test {

	@Autowired
	SearchRepository repository;

	@Test
	public void test() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("column", "TITLE");
		map.put("query", "영화");
		List<Search> list = repository.selectQuery(map);

		assertTrue(list.size() > 0); 

		
	}

}
