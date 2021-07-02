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
			$('#in_with').on('click', function(){
				location.href = 'loginPage.do';
			});
			$('#in_without').on('click', function(){
				location.href = 'mainList.do';
			});
		})
	</script>
</head>
<body>
	<h1> DS - Homepage에 오신 것을 환영합니다! </h1>
	
	<input type="button" value="로그인 후 이용하기" id="in_with">
	<input type="button" value="로그인 없이 이용하기" id="in_without">
	
</body>
</html>