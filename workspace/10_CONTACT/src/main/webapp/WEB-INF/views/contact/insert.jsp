<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script>
		$(function(){
			$('#f').on('submit', function(event){
				if($('#name').val() == '' || $('#addr').val() == '' || $('#tel').val() == ''){
					alert('이름, 전화, 주소는 반드시 입력해주세요.');
					event.preventDefault();
					return false;
				}
				$('#f').submit();
			})
			
			$('#list_btn').on('click', function(){
				location.href = 'listPage.do';
			})
		})
	</script>
	<style>

	</style>
</head>
<body>
	<h1>연락처 등록</h1>
	<form id="f" method="post" action="insert.do">
		이름<br>
		<input type="text" name="name" id="name"><br>
		전화<br>
		<input type="text" name="tel" id="tel"><br>
		주소<br>
		<input type="text" name="addr" id="addr"><br>
		이메일<br>
		<input type="text" name="email" id="email"><br>
		비고<br>
		<input type="text" name="note" id="note"><br>
		<button>연락처 저장하기</button>
		<input type="button" value="전체연락처" id="list_btn">
		
	</form>
</body>
</html>