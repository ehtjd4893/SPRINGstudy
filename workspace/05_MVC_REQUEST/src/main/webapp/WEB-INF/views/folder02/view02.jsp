<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>f2/view01.jsp</h1>
	${Person.name}<br>
	${Person.age}<br>	<!-- ModelAndView 클래스로 넘긴 값(attribute): request로 넘어온다. -->
	<c:forEach var="hobby" items="${Person.hobbies}" varStatus="status">
		${status.count}번째 취미: ${hobby}<br>
	</c:forEach>
</body>
</html>