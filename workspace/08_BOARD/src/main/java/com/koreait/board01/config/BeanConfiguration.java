package com.koreait.board01.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.koreait.board01.command.BoardListCommand;
import com.koreait.board01.command.DeleteBoardCommand;
import com.koreait.board01.command.InsertBoardCommand;
import com.koreait.board01.command.SelectBoardByNoCommand;
import com.koreait.board01.command.UpdateBoardCommand;
import com.koreait.board01.command.UpdatePageCommand;

@Configuration
public class BeanConfiguration {
	@Bean
	public BoardListCommand boardListCommand() {
		return new BoardListCommand();
	}
	
	@Bean 
	DeleteBoardCommand deleteBoardCommand() {
		return new DeleteBoardCommand();
	}
	
	@Bean
	InsertBoardCommand insertBoardCommand() {
		return new InsertBoardCommand();
	}
	
	@Bean
	SelectBoardByNoCommand selectBoardByNoCommand() {
		return new SelectBoardByNoCommand();
	}
	
	@Bean
	UpdateBoardCommand updateBoardCommand() {
		return new UpdateBoardCommand();
	}
	
	@Bean UpdatePageCommand updatePageCommand() {
		return new UpdatePageCommand();
	}
}
