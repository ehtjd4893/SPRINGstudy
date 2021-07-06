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
			fn_find_pw();
		});	// onload
		
		var key = null;			// 발급받은 인증키값 저장
		var pw = null;			// pw를 받아올 변수		
		function fn_find_pw(){
			$('#find_pw_btn').on('click', function(){
				if($('#phone').val() == '' || $('#name').val() == ''){
					// id, pw 텍스트창 중 빈 칸이 있다면
					alert('이름,연락처를 모두 입력해주세요.')
					return false;
				}
				$.ajax({
					url: 'findPw.do',
					type: 'get',
					data: $('#f').serialize(),
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.status == 500){
							alert("입력하신 정보와 일치하는 계정이 없습니다.");
							return false;
						}
						else if(resultMap.status == 200){
							
							pw = resultMap.pw;
							fn_get_key();
							return false;
						}
					},
					error: function(){
						alert('오류 발생');
					}
				})	// ajax
			});	// onclick
		}	// fn_login
		
		// 인증번호 발급 함수
		function fn_get_key(){				
			// 인증번호 발급 ajax
				$.ajax({
					url: 'sendSms.do',
					type: 'get',
					data: 'phone=' + $('#phone').val(),
					dataType: 'json',
					success: function(resultMap){
						key = resultMap.key;
						var input = prompt('변경된 번호로 전송된 인증번호를 입력해주세요.');
						fn_check_key(input);
					},
					error: function(xhr, textStatus, errorThrown){
						alert('키 발급 에러');
					}
				});	// ajax
		};	// fn_get_key 
		
		// 입력받은 인증번호 정상여부 확인
		function fn_check_key(input){
		
			if(input == key){
				alert('찾으시는 PW는' + pw + '입니다.');			
			} // end if
			else{
				alert('인증 실패');
			}
		}	// fn_check_key
	</script>
</head>
<body>
	<h1> 비밀번호 찾기 </h1>
	
	<form id="f" method="post" action="findId.do">
		<input type="text" placeholder="연락처를 입력하세요." name="phone" id="phone">
		<input type="text" placeholder="ID를 입력하세요." name="id" id="id">
		<input type="button" value="PW 찾기" id="find_pw_btn">
	</form>
		<input type="button" value="뒤로 가기"  onclick="history.back()">
		<input type="button" value="로그인하기"  onclick="location.href='loginPage.do'">
		<input type="button" value="ID 찾기" id="pw_btn" onclick="location.href='findIdPage.do'">

		
</body>
</html>