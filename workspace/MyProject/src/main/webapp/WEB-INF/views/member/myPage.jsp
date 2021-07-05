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
			fn_signout();
			fn_update_pw();
		});	// onload
		
		function fn_signout(){
			$('#signout_btn').click(function(){
				if(prompt('정말 삭제하시려면 비밀번호를 입력해주세요.') == '${loginUser.pw}'){
					location.href = 'signout.do?no=${loginUser.no}';
				}
				else{
					alert('잘못된 입력입니다.');
				}
			});	// onclick
		}	// fn_signout
		
		function fn_update_pw(){
			$('#update_pw_btn').click(function(){
				if(confirm('정말 이동하시겠습니까?')){
					$('#f').attr('action', 'updatepwPage.do');
					$('#f').submit();
				}
			});	// onclick
		}	// fn_update_pw
		
		function fn_update(){
			$('#update_btn').click(function(){
				if(confirm('정말 변경하시겠습니까?')){
					$('#f').attr('action', 'update.do');
					$('#f').submit();
				}
			});	// onclick
		}	// fn_update
	</script>
</head>
<body>
	<h1> DS - Homepage에 오신 것을 환영합니다! </h1>
	
	<form id="f" method="post">
		계정 상태<br>
		<input type="text" value="활성화" disabled><br><br>
		ID<br>
		<input disabled type="text" name="id" value="${loginUser.id}"><br><br>
		PW<br>
		<input disabled type="password" name="pw" value="${loginUser.pw}">
		<input type="button" value="비밀번호 변경하러 가기" id="update_pw_btn"><br><br>
		이름<br>
		<input type="text" name="name" value="${loginUser.name}" ><br><br>
		이메일<br>
		<input type="text" name="email" value="${loginUser.email}" ><br><br>
		연락처<br>
		<input type="text" name="phone" id="phone" value="${loginUser.phone}"><br><br>
		

		
		<input type="button" id="signout_btn" value="탈퇴하기"><br><br>
		<input type="button" id="update_btn" value="내 정보 수정하기"><br><br>
		<input type="button" onclick='location.href="mainList.do"' value="뒤로 가기">
	</form>	
	
</body>
</html>