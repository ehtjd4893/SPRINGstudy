package com.koreait.integration1.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.integration1.domain.Search;
import com.koreait.integration1.repository.SearchRepository;

public class SearchServiceImpl implements SearchService {

	@Autowired
	private SearchRepository repository;
	
	@Override
	public List<Search> totalList() {
		return repository.selectAll();
	}

	@Override
	public List<Search> queryList(Map<String, String> map) {
		// TODO Auto-generated method stub
		return repository.selectQuery(map);
	}

}
