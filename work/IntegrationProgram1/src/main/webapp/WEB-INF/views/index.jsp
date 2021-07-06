<%@page import="java.text.SimpleDateFormat"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Insert title here</title>
	<style>
		td{
			height: 15px;
		}
		tr td:nth-child(1) {
			width: 100px;
		}
		tr td:nth-child(2) {
			width: 150px;
		}
		tr td:nth-child(3) {
			width: 100px;
		}
	</style>
	<script>
		$(function(){
			fn_selectAll();
			fn_init();
			fn_selectQuery();
		});	// onload 
		
		function fn_init(){
			$('#init_btn').click(function(){
				$('#column').val('');
				$('#query').val('');
				fn_selectAll();
			});
		}
		
		
		
		function fn_selectAll(){
			$.ajax({
				url: 'selectAll.do',
				type: 'get',
				dataType: 'json',
				success: function(resultMap){
					alert(resultMap.message);	// 실패했든 성공했든 해당 메세지 출력
					$('#list').empty();
					if(resultMap.status == 200){	// 성공시
						$.each(resultMap.list, function(i, search){
							$('<tr>')
							.append( $('<td>').text(search.title) )
							.append( $('<td>').text(search.content) )
							.append( $('<td>').text(search.regdate) )
							.appendTo('#list');
							
						});	// each
					} else if(resultMap.status == 500){	// 실패시
						$('<tr>')
						.append( $('<td colspan="3">').text('결과 없음') )
						.appendTo('#list');
					}
				},
				error: function(){
					alert('오류 발생');
				}
			});	// ajax
		}	// fn_selectAll
		
		function fn_selectQuery(){
			$('#search_btn').click(function(){
				
				$.ajax({
					url: 'selectQuery.do',
					type: 'get',
					data: $('#f').serialize(),
					dataType: 'json',
					success: function(resultMap){
						alert(resultMap.message);	// 실패했든 성공했든 해당 메세지 출력
						$('#list').empty();
						if(resultMap.status == 200){	// 성공시
							$.each(resultMap.list, function(i, search){
								$('<tr>')
								.append( $('<td>').text(search.title) )
								.append( $('<td>').text(search.content) )
								.append( $('<td>').text(search.regdate) )
								.appendTo('#list');
								
							});	// each
						} else if(resultMap.status == 500){	// 실패시
							$('<tr>')
							.append( $('<td colspan="3">').text('결과 없음') )
							.appendTo('#list');
						}
					},
					error: function(xhr, textStatus, errorThrown){
						console.log(errorThrown);
						console.log(textStatus);
					}
				});	// ajax
			});	// onclick
		}	// fn_selectQuery
	</script>
</head>
<body>
	<form id="f">
		<select id="column" name="column">
			<option value="">::선택::</option>
			<option value="TITLE">제목</option>
			<option value="CONTENT">내용</option>
		</select>
		
		<input type="text" id="query" name="query">
		<input type="button" value="검색" id="search_btn">	
		<input type="button" value="초기화" id="init_btn">	
	</form>
	
	<table border="1" style="table-layout: fixed">
		<thead>
			<tr>
				<th>제목</th>
				<th>내용</th>
				<th>작성일</th>
			</tr>
		</thead>
		<tbody id="list">
		
		</tbody>
	</table>
</body>
</html>