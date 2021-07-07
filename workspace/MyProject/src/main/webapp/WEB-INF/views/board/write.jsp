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
		$('#write_btn').click(function(){
			if(confirm('작성하시겠습니까?')){
				$('#f').attr('action', 'write.do');
				$('#f').attr('method', 'post');
				$('#f').submit();
			}
		});
	});
	</script>
</head>
<body>

	
	<h1>게시글 작성 화면</h1>
	<form id="f" enctype="multipart/form-data">
		
		작성자<br>
		<input type="text" disabled value="${loginUser.id}"><br><br>
		<input type="hidden" name="writer" value="${loginUser.id}"><br><br>
		
		제목<br>
		<input type="text" name="title" ><br><br>
		
		내용<br>
		<textarea rows="10" cols="50" name="content"></textarea><br><br>

		첨부<br>
		<input type="file" name="files" multiple><br><br>	<!-- multiple : 다중 첨부 가능 -->	
		
		<input type="button" value="작성 완료" id="write_btn">
	</form>
</body>
</html>