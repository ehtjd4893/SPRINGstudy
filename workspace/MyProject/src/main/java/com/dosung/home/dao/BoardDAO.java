package com.dosung.home.dao;

import java.util.List;
import java.util.Map;

import com.dosung.home.dto.BoardDTO;
import com.dosung.home.dto.PageDTO;

public interface BoardDAO {

	public int write(BoardDTO dto);

	public List<BoardDTO> boardList(PageDTO pageDTO);

	public BoardDTO selectBoardByNo(long no);

	public void updateHitByNo(long no);

	public void updateBoard(BoardDTO dto);

	public void deleteBoard(long no);

	public List<BoardDTO> searchList(Map<String, String> container);

	public int getTotalRecord();

	public int getSearchedlRecord(Map<String, String> container);

	public void writeReply(Map<String, String> map);


}
