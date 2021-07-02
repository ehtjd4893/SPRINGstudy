<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DS - Homepage</title>
	<script>
		$(function(){
			fn_login();
		});	// onload
		
		function fn_login(){
			$('#login_btn').on('click', function(){
				if($('#id').val() == '' || $('#pw').val() == ''){
					// id, pw 텍스트창 중 빈 칸이 있다면
					alert('ID와 PW를 모두 입력해주세요.')
					return false;
				}
				$('#f').submit();
			});	// onclick
		}	// fn_login
	</script>
</head>
<body>
	<h1> 로그인 화면 </h1>
	
	<form id="f" method="post" action="login.do">
		<input type="text" placeholder="ID를 입력하세요." name="id" id="id">
		<input type="text" placeholder="PW를 입력하세요." name="pw" id="pw">
		<input type="button" value="로그인하기" id="login_btn">
	</form>
		<input type="button" value="회원가입" id="signup_btn" onclick="location.href='signupPage.do'">
		<input type="button" value="ID 찾기" id="id_btn" onclick="location.href='findIdPage.do'">
		<input type="button" value="PW 찾기" id="pw_btn" onclick="location.href='findPwPage.do'">
		
</body>
</html>