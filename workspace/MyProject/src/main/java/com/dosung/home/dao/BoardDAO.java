package com.dosung.home.dao;

import java.util.List;

import com.dosung.home.dto.BoardDTO;

public interface BoardDAO {

	public void write(BoardDTO dto);

	public List<BoardDTO> boardList();

}
