<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
</head>
<body>
	<h1>게시글 보기 화면</h1>
	<form method="post">
		
		작성자<br>
		${board.writer}<br><br>
		
		제목<br>
		<input type="text" name="title" value="${board.title}"><br><br>
		
		내용<br>
		<input type="text" name="content" value="${board.content}"><br><br>

		첨부<br>
		
		
		<input type="button" value="수정하기">
		<input type="button" value="삭제하기">
	</form>
</body>
</html>