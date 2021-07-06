package com.koreait.apptest;

import static org.junit.Assert.*;

import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.koreait.apptest.config.BeanConfiguration;
import com.koreait.apptest.dao.MemberDAO;
import com.koreait.apptest.dto.Member;

//@ContextConfiguration("classpath:aaa.xml")
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes= {BeanConfiguration.class})
public class MemberTest {

	// junit 테스트는 스프링을 모두 돌리지 않는다.
	// 때문에 sqlSession의 @Autowired가 정상적으로 작동하지 않는다.
	// Oracle JDBC도 tomcat에 넣어두면 동작하지 않는다.
	// 현재 프로젝트에 포함되어 있어야 한다. (pom.xml 참고)
	
	// junit 테스트시 스프링 모든 기능 활용을 위해서
	// spring-test 디펜던시를 추가한다. (스프링 프레임워크와 같은 버전)
	
	// spring-test 디펜던시 지원 애너테이션
	// @RunWith: 이 테스트는 스프링을 함께 돌려달라.
	// @ContextConfiguration: Bean을 여기서 찾아라
	
	@Autowired
	private SqlSession sqlSession;
	
	@Test
	public void joinTest() {
		Member member = new Member();
		member.setId("test");
		member.setPw("1111");
		member.setName("테스트");
		member.setEmail("test@test.com");
		
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		int count = dao.join(member);
		
		assertEquals("가입 실패", 1, count);
		
		// 1과 count가 같으면 검사 통과
		// 1과 count가 다르면 검사 실패(가입 실패)
	}

	// 아이디 중복 점검 테스트
	// admin 아이디를 가진 아이디가 없으면 검사 통과
	// admin 아이디를 가진 아이디가 있으면 검사 실패
	@Test
	public void idCheckTest() {	
		MemberDAO dao = sqlSession.getMapper(MemberDAO.class);
		int count = dao.idCheck("admin");
		
		assertEquals("중복 체크 실패", 1, count);
	}
	
}
