<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DS - Homepage</title>
	<style>
		#myContainer{
			box-sizing: border-box;
			display: flex;
			justify-content: flex-end;
			position: absolute;
			top: 20px;
			right: 10%;
			width: 200px;
			height: 30px;
			z-index: 1;
		}
		table{
			border-collapse: collapse;
		}
	</style>
</head>
<body>
	<div id="myContainer">
	<c:if test="${loginUser ne null}">
		<input type="button" value="로그아웃" onclick="location.href='logout.do'">
		<input type="button" value="내 정보" onclick="location.href='myPage.do'">		
	</c:if>
	<c:if test="${loginUser eq null}">
		<input type="button" value="로그인" onclick="location.href='loginPage.do'">
		<input type="button" value="회원가입" onclick="location.href='signupPage.do'">		
	</c:if>
	</div>
	<table border="1">
		<thead>
			<tr>
				<td>글번호</td>
				<td>작성자</td>
				<td>제목</td>
				<td>작성일</td>
				<td>조회수</td>
				<td>이미지</td>
			</tr>
		</thead>
		<tbody id="list">
			<c:if test="${loginUser.status == 1}">
				<tr>
					<td colspan="6">탈퇴한 상태의</td>
				</tr>			
			</c:if>
			<c:if test="${loginUser.status == 0}">
				<tr>
					<td colspan="6">로그인 상태의</td>
				</tr>			
			</c:if>
		</tbody>
	</table>
		
</body>
</html>