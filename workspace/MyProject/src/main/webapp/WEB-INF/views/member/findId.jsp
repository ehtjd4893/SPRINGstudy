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
			fn_find_id();
		});	// onload
		
		function fn_find_id(){
			$('#find_id_btn').on('click', function(){
				if($('#phone').val() == '' || $('#name').val() == ''){
					// id, pw 텍스트창 중 빈 칸이 있다면
					alert('이름,연락처를 모두 입력해주세요.')
					return false;
				}
				$.ajax({
					url: 'findId.do',
					type: 'get',
					data: $('#f').serialize(),
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.status == 500){
							alert('입력하신 정보와 일치하는 계정이 없습니다.');
							return false;
						}
						else if(resultMap.status == 200){
							alert("찾으시는 계정의 ID는" + resultMap.id + "입니다.");
							return false;
						}
					},
					error: function(){
						alert('오류 발생');
					}
				})	// ajax
			});	// onclick
		}	// fn_login
	</script>
</head>
<body>
	<h1> 아이디 찾기 </h1>
	
	<form id="f" method="post" action="findId.do">
		<input type="text" placeholder="연락처를 입력하세요." name="phone" id="phone">
		<input type="text" placeholder="성함을 입력하세요." name="name" id="name">
		<input type="button" value="ID 찾기" id="find_id_btn">
	</form>
		<input type="button" value="뒤로 가기"  onclick="history.back()">
		<input type="button" value="로그인하기"  onclick="location.href='loginPage.do'">
		<input type="button" value="PW 찾기" id="pw_btn" onclick="location.href='findPwPage.do'">

		
</body>
</html>