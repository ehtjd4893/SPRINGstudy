<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<h1>f5/view04.jsp</h1>
	<!-- forward로 넘어온 request.parameter의 영역 --> 

	<!-- model을 통해 넘어온 request.attribute의 영역 --> 
	<h3>attribute</h3>
	${person.name}<br>
	${person.age}<br>
</body>
</html>