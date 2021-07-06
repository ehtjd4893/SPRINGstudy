<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DS - Homepage</title>
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css" referrerpolicy="no-referrer" />
	<style>
		#myContainer{
			box-sizing: border-box;
			display: flex;
			justify-content: flex-end;
			position: absolute;
			top: 20px;
			right: 10%;
			width: 200px;
			height: 30px;
			z-index: 1;
		}
		table{
			border-collapse: collapse;
		}
	</style>
	
	<script>
		$(function(){
			fn_write();
			fn_list();
		});	// onload
		
		function fn_list(){
			$.ajax({
				url: 'boardList.do',
				type: 'get',
				dataType: 'json',
				success: function(resultMap){
					$.each(resultMap.list, function(i, board){
						if(board.image == 'null'){
							$('<tr>')
							.append( $('<td>').text(board.no) )
							.append( $('<td>').text(board.writer) )
							.append( $('<td>').html('<a href="showBoard.do?no="' + board.no + '">' + board.title + '</a>') )
							.append( $('<td>').text(board.postdate) )
							.append( $('<td>').text(board.hit) )
							.append( $('<td>').text('') )
							.appendTo('#list');
						}
						else{
							$('<tr>')
							.append( $('<td>').text(board.no) )
							.append( $('<td>').text(board.writer) )
							.append( $('<td>').html('<a href="showBoard.do?no="' + board.no + '">' + board.title + '</a>') )
							.append( $('<td>').text(board.postdate) )
							.append( $('<td>').text(board.hit) )
							.append( $('<td>').html('<i class="far fa-image"></i>') )
							.appendTo( $('#list') );
						} 
					});	// each
				},
				error: function(){
					
				}
			});	// ajax
		}	// fn_list
		
		function fn_write(){
			$('#write_btn').click(function(){
				if( ${loginUser == null}){	// 로그인한 상태가 아니라면
					alert('로그인 후 이용가능합니다.');
					location.href='loginPage.do';
				} else{	// 로그인한 상태라면
					location.href='writePage.do';
				}
			});	// onclick
		}	// fn_write
	</script>
	
</head>
<body>
	<div id="myContainer">
	<c:if test="${loginUser ne null}">
		<input type="button" value="로그아웃" onclick="location.href='logout.do'">
		<input type="button" value="내 정보" onclick="location.href='myPage.do'">		
	</c:if>
	<c:if test="${loginUser eq null}">
		<input type="button" value="로그인" onclick="location.href='loginPage.do'">
		<input type="button" value="회원가입" onclick="location.href='signupPage.do'">		
	</c:if>
	</div>
	<table border="1">
		<thead>
			<tr>
				<td>글번호</td>
				<td>작성자</td>
				<td>제목</td>
				<td>작성일</td>
				<td>조회수</td>
				<td>이미지</td>
			</tr>
		</thead>
		
		<tbody id="list">
				
		</tbody>
		
		<tfoot>
			
		</tfoot>
	</table>
	<input type="button" id="write_btn" value="새 글 작성하기">
		
</body>
</html>