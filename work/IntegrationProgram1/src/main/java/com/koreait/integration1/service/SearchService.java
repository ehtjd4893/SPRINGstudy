package com.koreait.integration1.service;

import java.util.List;
import java.util.Map;

import com.koreait.integration1.domain.Search;

public interface SearchService {

	public List<Search> totalList();
	
	public List<Search> queryList(Map<String, String> map);
}
