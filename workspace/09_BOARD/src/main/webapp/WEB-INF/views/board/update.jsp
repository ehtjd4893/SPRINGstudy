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
			$('#f').submit(functiom(event){
				if('${board.title}' == $('title').val() && '${board.title}' == $('content').val()){
					alert('변경할 내역이 없습니다');
					event.preventDefault();
					return false;
				}
				f.submit();
			})
			
			$('#list_btn').on('click',function(){
				location.href = 'selectBoardList.do';
			})
		})
	</script>
</head>
<body>
	<h1>${board.no}번 게시글 수정 페이지</h1>
	<form action="updateBoard.do" method="post">
		제목<br>
		<input type="text" name="title" value="${board.title}"><br><br>
		내용<br>
		<input type="text" name="content" value="${board.content}"><br><br>
		<input type="hidden" name="no" value="${board.no}">
		<button>수정하기</button>
		<input type="button" value="목록으로 이동" id="list_btn">
	 </form>
</body>
</html>