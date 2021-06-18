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
			console.log('${contact.name}');
			$('#f').on('submit', function(event){
				if($('#name').val() == '${contact.name}' && 
				   $('#addr').val() == '${contact.addr}' && 
				   $('#tel').val() == '${contact.tel}' && 
				   $('#email').val() == '${contact.email}' && 
				   $('#note').val() == '${contact.note}'){
					alert('변경 내역이 없습니다.');
					event.preventDefault();
					return false;
				}
				$('#f').submit();
			})
			
			$('#delete_btn').on('click', function(){
				if(confirm('삭제하시겠습니까?'))
					location.href = 'delete.do?no=${contact.no}';
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
	<h1>연락처 보기</h1>
	<form id="f" method="post" action="update.do">
		이름<br>
		<input type="text" name="name" id="name" value="${contact.name}"><br>
		전화<br>
		<input type="text" name="tel" id="tel" value="${contact.tel}"><br>
		주소<br>
		<input type="text" name="addr" id="addr" value="${contact.addr}"><br>
		이메일<br>
		<input type="text" name="email" id="email" value="${contact.email}"><br>
		비고<br>
		<input type="text" name="note" id="note" value="${contact.note}"><br>
		<input type="hidden" name="no" id="no" value="${contact.no}">
		<button>수정하기</button>
		<input type="button" value="삭제하기" id="delete_btn">
		<input type="button" value="전체연락처" id="list_btn">
		
	</form>
</body>
</html>