<%@ page language="java" contenType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	
</head>
<body>
	<!--  /mvc04 는 contextPath를 의미한다 -->
	<form action="/mvc04/">		
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
</body>
</html>
