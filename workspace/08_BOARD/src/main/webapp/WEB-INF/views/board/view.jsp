<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<script>
		$(function(){
			$('#update_page_btn').on('click', function(){
				$('#f').attr('action', 'updatePage.do');
				$('#f').submit();
			})
			
			$('#delete_btn').on('click', function(){
				var key = prompt('삭제를 위해 작성자명을 입력해주세요.');
				if('${board.writer}' == key){
					$('#f').attr('action', 'deleteBoard.do');
					$('#f').submit();
				}
			})
			
			$('#list_btn').on('click',function(){
				location.href = 'selectBoardList.do';
			})
		})
	</script>
</head>
<body>
	<h1>${board.no}번 게시글</h1>
	작성자: ${board.writer}<br>
	제목: ${board.title}<br>
	내용:${board.content}<br>
	작성일: ${board.postdate}<br><br>
	
	<form method="post" id="f">
		<input type="hidden" name="no" value="${board.no}">
		<input type="hidden" name="title" value="${board.title}">
		<input type="hidden" name="content" value="${board.content}">
		<input type="button" value="수정하기" id="update_page_btn">
		<input type="button" value="삭제하기" id="delete_btn">
		<input type="button" value="목록으로 이동" id="list_btn">
	</form>
</body>
</html>