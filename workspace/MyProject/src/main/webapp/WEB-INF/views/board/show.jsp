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
			fn_delete();
		});	// onload
		
		// 삭제 버튼 클릭시 작동 함수
		function fn_delete(){
			$('#delete_btn').click(function(){
				if(confirm('정말 게시물을 삭제하시겠습니까?'))
					$('#f').submit();
			});	// onclick
		}	// fn_delete
		
		function fn_reply(){
			$('#reply_btn').click(function(){
				$.ajax({
					url: 'writeReply.do',
					data: $('#f').serialize(), 
					type: 'get',
					dataType: 'json',
					success: function(resultMap){
						alert(resultMap.message);
					},
					error: function(){
						alert('댓글 오류 발생');
					}
				});	// ajax
			});	// onclick
		}	// fn_reply
	</script>
</head>
<body>

	<form id="f" action="deleteBoard.do" method="post" enctype="multipart/form-data">
		글번호 <input type="text" value="${Board.no}" disabled>
		작성자 <input type="text" name="writer" value="${Board.writer}" disabled><br><br>
		조회수 <input type="text" name="hit" value="${Board.hit}" disabled><br><br>
		
		제목 <input type="text" name="title" value="${Board.title}" disabled>
		작성일 <input type="text" name="postdate" value="${Board.postdate}" disabled>
		최근수정일 <input type="text" name="lastmodified" value="${Board.lastmodified}" disabled><br><br>
		내용<br>
		<textarea  cols="50" rows="10" disabled>${Board.content}</textarea><br><br>
		첨부 이미지<br>
		<c:if test="${Board.image ne 'null'}">
			<img name="image" alt="${Board.image}" src="resources/archive/${Board.image}" style="width: 300px">
			<input type="hidden" name="filename" value="${Board.image}">
		</c:if>
		<c:if test="${Board.image eq 'null'}">
			<input type="text" name="image" value="null" disabled>
		</c:if>
		<br><br>
	
		<c:if test="${loginUser.id == Board.writer}">
			<input id="update_btn" type="button" value="수정하기" onclick='location.href="updateBoardPage.do?no=${Board.no}"'>
			<input id="delete_btn" type="button" value="삭제하기" >
		</c:if>
		<input type="button" value="목록으로 돌아가기" onclick='history.back()'>
		
		<input type="hidden" name="no" value="${Board.no}">
		<input type="hidden" name="content" value="${Board.content}">
		<input type="hidden" name="replyWriter" value="${loginUser.id}">
		<c:if test="${loginUser ne null}">
			<textarea rows="2" cols="30" id="reply" name="reply" placeholder="댓글을 입력하세요"></textarea>
			<input type="button" id="reply_btn" value="댓글 달기">
		</c:if>
	
	</form>
</body>
</html>