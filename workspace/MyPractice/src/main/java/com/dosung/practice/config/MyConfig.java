package com.dosung.practice.config;

import java.io.IOException;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.dosung.practice.repository.BoardRepository;
import com.dosung.practice.service.BoardService;
import com.dosung.practice.service.BoardServiceImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class MyConfig {
	
	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig hikariConfig = new HikariConfig();
		hikariConfig.setDriverClassName("oracle.jdbc.OracleDriver");
		hikariConfig.setJdbcUrl("jdbc:oracle:thin:@127.0.0.1:1521:xe");
		hikariConfig.setUsername("spring");
		hikariConfig.setPassword("1111");
		
		return hikariConfig;
	}
	
	@Bean(destroyMethod="close")
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
	
		sqlSessionFactory.setDataSource(hikariDataSource());
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/dosung/practice/repository/*.xml"));
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	
	@Bean
	public BoardRepository repository() {
		return new BoardRepository();
	}
	
	@Bean
	public BoardServiceImpl boardService() {
		return new BoardServiceImpl();
	}
}
