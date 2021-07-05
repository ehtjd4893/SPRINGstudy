package com.dosung.practice.service;

import java.util.List;
import java.util.Map;

import com.dosung.practice.domain.Board;

public interface BoardService  {
	
	public List<Board> totalList();
	public List<Board> searchList(Map<String, String> map);
}
