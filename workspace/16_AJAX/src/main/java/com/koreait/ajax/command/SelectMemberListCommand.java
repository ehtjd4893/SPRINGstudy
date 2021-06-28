package com.koreait.ajax.command;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;
import org.springframework.ui.Model;

import com.koreait.ajax.dao.MemberDAO;
import com.koreait.ajax.dto.Member;

public class SelectMemberListCommand implements MemberCommand {

	@Override
	public Map<String, Object> execute(SqlSession sqlSession, Model model) {
		Map<String, Object> map = model.asMap();
		int page = (int)map.get("page");
		System.out.println("hi1");
		MemberDAO memberDAO = sqlSession.getMapper(MemberDAO.class);
		System.out.println("hi2");
		int totalRecord = memberDAO.getTotalMemberCount();
		System.out.println("hi3");
		int recordPerPage = 5;
		int beginRecord = (page - 1) * recordPerPage + 1;
		int endRecord = beginRecord + recordPerPage - 1;
		endRecord = endRecord < totalRecord ? endRecord : totalRecord;
		
		Map<String, Integer> pagingMap = new HashMap<String, Integer>();
		pagingMap.put("beginRecord", beginRecord);
		pagingMap.put("endRecord", endRecord);
		System.out.println("hi4");
		List<Member> list = memberDAO.selectMemberList(pagingMap);
		System.out.println("회원 수: " + list.size());
		
		Map<String, Object> resultMap = new HashMap<>();
		resultMap.put("list", list);
		resultMap.put("exists", list.size() > 0);
		return resultMap;	
	}

}
