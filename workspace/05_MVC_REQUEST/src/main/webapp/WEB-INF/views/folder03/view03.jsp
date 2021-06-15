<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>f3/view03.jsp</h1>
	${person1.name}<br>
	${person1.age}<br>	<!-- ModelAndView 클래스로 넘긴 값(attribute): request로 넘어온다. -->
	<c:forEach var="hobby" items="${person1.hobbies}" varStatus="status">
		${status.count}번째 취미: ${hobby}<br>
	</c:forEach>
</body>
</html>