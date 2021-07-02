package com.dosung.home.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.dosung.home.command.GetKeyCommand;
import com.dosung.home.command.IdCheckCommand;
import com.dosung.home.command.LoginCommand;
import com.dosung.home.command.PhoneCheckCommand;
import com.dosung.home.command.SignupCommand;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class MemberConfiguration {

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
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(hikariDataSource());
		sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/dosung/home/dao/*.xml"));
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}
	@Bean
	public LoginCommand loginCommand() {
		return new LoginCommand();
	}
	@Bean
	public GetKeyCommand getKeyCommand() {
		return new GetKeyCommand();
	}
	@Bean
	public SignupCommand signupCommand() {
		return new SignupCommand();
	}
	@Bean
	public IdCheckCommand idCheckCommand() {
		return new IdCheckCommand();
	}
	@Bean
	public PhoneCheckCommand phoneCheckCommand() {
		return new PhoneCheckCommand();
	}
}
