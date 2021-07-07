package com.dosung.home.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.dosung.home.commandB.BoardListCommand;
import com.dosung.home.commandB.DeleteBoardCommand;
import com.dosung.home.commandB.SearchBoardCommand;
import com.dosung.home.commandB.ShowBoardCommand;
import com.dosung.home.commandB.UpdateBoardCommand;
import com.dosung.home.commandB.UpdateBoardPageCommand;
import com.dosung.home.commandB.WriteCommand;
import com.dosung.home.commandM.FindIdCommand;
import com.dosung.home.commandM.FindPwCommand;
import com.dosung.home.commandM.GetKeyCommand;
import com.dosung.home.commandM.IdCheckCommand;
import com.dosung.home.commandM.LoginCommand;
import com.dosung.home.commandM.MyPhoneCheckCommand;
import com.dosung.home.commandM.PhoneCheckCommand;
import com.dosung.home.commandM.ResignupCommand;
import com.dosung.home.commandM.SignoutCommand;
import com.dosung.home.commandM.SignupCommand;
import com.dosung.home.commandM.UpdateCommand;
import com.dosung.home.commandM.UpdatePwCommand;
import com.dosung.home.commandR.WriteReplyCommand;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
public class MyConfiguration {

	/* 이 아래부터는 커넥션풀과 관련된 bean 생성*/
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
	
	/* 이 아래부터는 Member와 관련된 bean 생성*/
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
	@Bean
	public SignoutCommand signoutCommand() {
		return new SignoutCommand();
	}
	@Bean 
	public MyPhoneCheckCommand myPhoneCheckCommand() {
		return new MyPhoneCheckCommand();
	}
	@Bean
	public ResignupCommand resignupCommand() {
		return new ResignupCommand();
	}
	@Bean
	public UpdatePwCommand updatePwCommand() {
		return new UpdatePwCommand();
	}
	@Bean
	public UpdateCommand updateCommand() {
		return new UpdateCommand();
	}
	@Bean
	public FindIdCommand findIdCommand() {
		return new FindIdCommand();
	}
	@Bean
	public FindPwCommand findPwCommand() {
		return new FindPwCommand();
	}
	
	/* 이 아래부터는 Board와 관련된 bean 생성*/
	
	@Bean
	public CommonsMultipartResolver multipartResolver() {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver();
		multipartResolver.setDefaultEncoding("utf-8");
		multipartResolver.setMaxUploadSize(1024 * 1024 * 10);  // 바이트 단위(10MB)
		return multipartResolver;
	}
	@Bean
	public WriteCommand writeCommand() {
		return new WriteCommand();
	}
	@Bean
	public BoardListCommand boardListCommand() {
		return new BoardListCommand();
	}
	@Bean
	public ShowBoardCommand showBoardCommand() {
		return new ShowBoardCommand();
	}
	@Bean
	public UpdateBoardPageCommand updateBoardPageCommand() {
		return new UpdateBoardPageCommand();
	}
	@Bean
	public UpdateBoardCommand updateBoardCommand() {
		return new UpdateBoardCommand();
	}
	@Bean
	public DeleteBoardCommand deleteBoardCommand() {
		return new DeleteBoardCommand();
	}
	@Bean
	public SearchBoardCommand searchBoardCommand() {
		return new SearchBoardCommand();
	}
	/* 여기부터는 reply 관련 command */
	@Bean
	public WriteReplyCommand writeReplyCommand() {
		return new WriteReplyCommand();
	}
}
