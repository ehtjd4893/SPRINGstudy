package com.koreait.integration1.controller;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.koreait.integration1.domain.Search;
import com.koreait.integration1.service.SearchServiceImpl;

@Controller
public class SearchController {
	
	@Autowired
	private SearchServiceImpl service;
	
	@GetMapping(value="/")
	public String index() {
		return "index";
	}
	
	@GetMapping(value="selectAll.do",produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectAll(HttpServletRequest request, Model model){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		List<Search> list = service.totalList();
		
		if(list.size() == 0) {	// 결과가 없을시
			resultMap.put("list", null);
			resultMap.put("status", 500);
			resultMap.put("message", "검색 결과가 없습니다.");
		} else {	// 검색 성공시
			resultMap.put("list", list);
			for(Search search : list) {
				search.setContent(search.getContent().substring(0,10));
				search.setRegdate(search.getRegdate().substring(0,10));
			}
			resultMap.put("status", 200);
			resultMap.put("message", list.size() + "개의 결과를 찾았습니다.");
		}
		
		return resultMap;
	}

	@GetMapping(value="selectQuery.do",produces="application/json; charset=utf-8")
	@ResponseBody
	public Map<String, Object> selectQuery(HttpServletRequest request, Model model){
		
		Map<String, Object> resultMap = new HashMap<String, Object>();
		String column = request.getParameter("column");
		String query = request.getParameter("query");
		Map<String, String> map = new HashMap<String, String>();
		map.put("column", column);
		map.put("query", query);
		
		List<Search> list = service.queryList(map);
		
		if(list.size() == 0) {	// 결과가 없을시
			resultMap.put("list", null);
			resultMap.put("status", 500);
			resultMap.put("message", query + " 검색 결과가 없습니다.");
		} else {	// 검색 성공시
			for(Search search : list) {
				search.setContent(search.getContent().substring(0,10));
				search.setRegdate(search.getRegdate().substring(0,10));
			}
			resultMap.put("list", list);
			resultMap.put("status", 200);
			resultMap.put("message", query + ", " + list.size() + "개의 결과를 찾았습니다.");
		}
		
		return resultMap;
	}
}
