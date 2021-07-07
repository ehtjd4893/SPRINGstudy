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
			fn_write();	// 새 글 작성 버튼
			fn_init();	// 전체 목록 불러오기 버튼
			if(${query == null}){	
				// 만약 model을 통해 query가 채워지지 않은 상태라면,
				// search 결과가 아니라 전체 목록을 가져온다.
				fn_list();				
			} else{
				// 만약 검색 버튼을 통해 검색한 후, paging된 숫자를 눌렀다면,
				// 전체 목록이 아니라 search결과만을 띄워준다.
				fn_search();
			}
			
			$('#search_btn').click(function(){
				fn_search();			
			});	// onclick
		});	// onload
		
		function fn_init(){
			$('#init_btn').click(function(){
				fn_list();
			});	// onclick
		}	// fn_init
		
		// 검색결과 불러오기
		function fn_search(){
				$.ajax({
					url: 'searchBoard.do',
					type: 'get',
					data: 'column=' + $('#column').val() + '&query=' + $('#query').val() + '&page=${page}',
					dataType: 'json',
					success: function(resultMap){
						$('#list').empty();
						$.each(resultMap.list, function(i, board){
							if(board.image == 'null'){
								$('<tr>')
								.append( $('<td>').text(board.no) )
								.append( $('<td>').text(board.writer) )
								.append( $('<td>').html('<a href="showBoard.do??no=' + board.no + '">' + board.title + '</a>') )
								.append( $('<td>').text(board.postdate) )
								.append( $('<td>').text(board.hit) )
								.append( $('<td>').text('') )
								.appendTo('#list');
							}
							else{
								$('<tr>')
								.append( $('<td>').text(board.no) )
								.append( $('<td>').text(board.writer) )
								.append( $('<td>').html('<a href="showBoard.do?no=' + board.no + '">' + board.title + '</a>') )
								.append( $('<td>').text(board.postdate) )
								.append( $('<td>').text(board.hit) )
								.append( $('<td>').html('<i class="far fa-image"></i>') )
								.appendTo( $('#list') );
							} 
						});	 // each function
						$('#paging').empty();
						$('#paging').html(resultMap.paging);
					},	// success
					error: function(){
						alert('검색 오류');
					}
				});	// ajax

		}	// fn_search
		
		// 전체 리스트 불러오기
		function fn_list(){
			$.ajax({
				url: 'boardList.do',
				type: 'get',
				data: 'page=${page}',
				dataType: 'json',
				success: function(resultMap){
					$('#list').empty();
					$.each(resultMap.list, function(i, board){
						if(board.image == 'null'){
							$('<tr>')
							.append( $('<td>').text(board.no) )
							.append( $('<td>').text(board.writer) )
							.append( $('<td>').html('<a href="showBoard.do?no=' + board.no + '">' + board.title + '</a>') )
							.append( $('<td>').text(board.postdate) )
							.append( $('<td>').text(board.hit) )
							.append( $('<td>').text('') )
							.appendTo('#list');
						}
						else{
							$('<tr>')
							.append( $('<td>').text(board.no) )
							.append( $('<td>').text(board.writer) )
							.append( $('<td>').html('<a href="showBoard.do?no=' + board.no + '">' + board.title + '</a>') )
							.append( $('<td>').text(board.postdate) )
							.append( $('<td>').text(board.hit) )
							.append( $('<td>').html('<i class="far fa-image"></i>') )
							.appendTo( $('#list') );
						} 
					});	// each
					$('#paging').empty();
					$('#paging').html(resultMap.paging);
				},
				error: function(){
					alert('전체 목록 가져오기 실패');
				}
			});	// ajax
		}	// fn_list
		
		// 새 글 작성하러 가기
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
	
	<!-- 검색한 결과를 띄워줄 때는, 그 전에 사용했던 column이 선택되어있도록 해준다. -->
	<select id="column">
		<c:if test="${column eq 'TITLE'}">
			<option value="TITLE" selected>제목</option>
		</c:if>
		<c:if test="${column ne 'TITLE'}">
			<option value="TITLE">제목</option>
		</c:if>
		<c:if test="${column eq 'WRITER'}">
			<option value="WRITER" selected>작성자</option>
		</c:if>
		<c:if test="${column ne 'WRITER'}">
			<option value="WRITER">작성자</option>
		</c:if>
		<c:if test="${column eq 'CONTENT'}">
			<option value="CONTENT" selected>내용</option>
		</c:if>
		<c:if test="${column ne 'CONTENT'}">
			<option value="CONTENT">내용</option>
		</c:if>

	</select>
	
	<!-- 검색했을 때 입력한 값 그대로 query를 유지해준다. -->
	<input type="text" id="query" value="${query}">
	
	
	<input type="button" id="search_btn" value="검색">
	<input type="button" id="init_btn" value="전체 목록 보기">
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
		
		<tfoot id="paging">
			
		</tfoot>
	</table>
	<input type="button" id="write_btn" value="새 글 작성하기">
		
</body>
</html>