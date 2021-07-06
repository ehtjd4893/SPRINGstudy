package com.koreait.integration1.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.koreait.integration1.repository.SearchRepository;
import com.koreait.integration1.service.SearchServiceImpl;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class SearchConfig {

	@Bean
	public HikariConfig hikariConfig() {
		HikariConfig config = new HikariConfig();
		config.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		config.setJdbcUrl("jdbc:oracle:thin:@localhost:1521:xe");
		config.setUsername("SERVER_USER");
		config.setPassword("1111");
		return config;
	}
	@Bean(destroyMethod="close")
	public HikariDataSource hikariDataSource() {
		return new HikariDataSource(hikariConfig());
	}
	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception{
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(hikariDataSource());
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/koreait/integration1/repository/*.xml"));
		return sqlSessionFactory.getObject();
	}
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception{
		return new SqlSessionTemplate(sqlSessionFactory());		
	}
	@Bean
	public SearchServiceImpl service() {
		return new SearchServiceImpl();
	}
	@Bean
	public SearchRepository repository() {
		return new SearchRepository();
	}
	
}
