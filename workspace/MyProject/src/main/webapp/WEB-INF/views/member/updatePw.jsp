<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>DS - Homepage</title>
	<style>
		.blue{
			color: blue;
		}
		.red{
			color: red;
		}
	</style>
	<script>
		$(function(){
			fn_check_pw0();
			fn_check_pw1();
			fn_check_pw2();
			fn_submit();
		});	// onload
		
		var pw0_pass = false;	// 현재 비밀번호 입력이 올바를 때 true
		var pw1_pass = false;	// 비밀번호 형식 이상 없을 때 true
		var pw2_pass = false;	// 두 비밀번호가 같을 때 true
		
		function fn_check_pw0(){
			$('#pw0').keyup(function(){
				pw0_pass = false;
				if($('#pw0').val() != '${loginUser.pw}'){
					$('#pw0_result').html('<a class="red">올바르지 않은 비밀번호입니다.</a>');
					pw0_pass = true;
				} else{
					$('#pw0_result').html('<a class="blue">올바른 비밀번호입니다.</a>');
				}
			})
		}
		
		function fn_check_pw1(){
			var regPw = /^[A-Za-z0-9!@#$]{4,20}$/;
			$('#pw').keyup(function(){
				pw1_pass = false;
				
				if( !regPw.test($('#pw').val()) ){
					$('#pw_result').html('<a class="red">대소문자, 숫자, !, @, #, $를 이용한 4~20자의 비밀번호를 입력해주세요.</a>');
				}	// end if
				else {	// 정규식 통과했다면
					$('#pw_result').html('<a class="blue">가능한 비밀번호 형식입니다.</a>');
					pw1_pass = true;
					
					// 정규식 통과한 상태에서 pw2와 비교
	 				if($('#pw').val() == $('#pw2').val()){
						// 두 비밀번호 입력이 다른 경우에
						// 첫번째 입력란을 고쳐서 일치시켰다면
						pw2_pass = true;
						$('#pw2_result').html('<a class="blue">사용 가능한 비밀번호입니다.</a>');
					} else if($('#pw').val() != $('#pw2').val()){
						// 고쳐서 입력했는데
						// 두 입력이 여전히 다른 경우
						pw2_pass = false;
						$('#pw2_result').html('<a class="red">두 비밀번호가 서로 다릅니다.</a>');
					}
					
				}	// end else
				

			}); // onkeydown

		}
		
		function fn_check_pw2(){
			$('#pw2').keyup(function(){
				pw2_pass = false;	// 초기화
				if($('#pw').val() == $('#pw2').val() ){
					// 두 비밀번호가 일치한다면
					$('#pw2_result').html('<a class="blue">사용 가능한 비밀번호입니다.</a>');
					pw2_pass = true;
				} else{
					$('#pw2_result').html('<a class="red">두 비밀번호가 서로 다릅니다.</a>');
				}
			});	// onkeyup
		}
		
		function fn_submit(){
			$('#update_btn').click(function(){
				$('#f').submit();
			});
		}
		
	</script>
</head>
<body>
	<h1> 비밀번호 변경 </h1>
	
	<form id="f" method="post" action="updatePw.do">
		<input type="hidden" value="${loginUser.no}" name="no">
		현재 비밀번호<br><input type="password" placeholder="현 PW를 입력하세요." name="pw0" id="pw0"><br>
		<span id="pw0_result"></span><br><br>
		새 비밀번호<br><input type="password" placeholder="새 PW를 입력하세요." name="pw" id="pw"><br>
		<span id="pw_result"></span><br><br>
		새 비밀번호 확인<br><input type="password" placeholder="PW를 확인하세요." name="pw2" id="pw2"><br>
		<span id="pw2_result"></span><br><br>
		<input type="button" value="변경하기" id="update_btn">
	</form>
	<input type="button" onclick='location.href="myPage.do"' value="뒤로 가기">
</body>
</html>