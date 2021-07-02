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
			alert('탈퇴한 상태의 계정으로 로그인하셨습니다.')		
			fn_get_key();
			fn_check_key();
			fn_submit();
		});	// onload
		
		var key = null;
		// 인증번호 발급 함수
		function fn_get_key(){
			$('#get_key_btn').on('click', function(){
				if($('#phone').val() != '${loginUser.phone}'){
					// 가입당시 등록한 휴대폰번호가 아니면
					alert('가입당시 번호를 입력하세요.');
					return false;
				}	// end if
				$.ajax({
					url: 'sendSms.do',
					type: 'get',
					data: 'phone=' + $('#phone').val(),
					dataType: 'json',
					success: function(resultMap){
						alert(resultMap.phone + ", " + resultMap.key);	
						key = resultMap.key;
					},
					error: function(xhr, testStatus, errorThrown){
						alert('키 발급 에러');
					}
				});	// ajax
			});	// onclick
		};	// fn_get_key 
		var pass = false;
		function fn_check_key(){
			$('#check_key_btn').on('click',function(){
				pass = false;
				if($('#check_key_btn').val() == key){
					alert('인증 완료');
					pass = true;
				} // end if
				else{
					alert('인증 실패');
					pass = false;
				}
			});	// onclick
		}	// fn_check_key
		function fn_submit(){
			$('#resignup_btn').on('click', function(){
				if(pass){
					$('#f').submit();
				}
			});	// onclick
		}	// fn_submit
	</script>
</head>
<body>
	<form id="f" method="post" action="resignup.do">
		계정 상태<br>
		<input type="text" value="탈퇴 상태" disabled><br><br>
		ID<br>
		<input disabled type="text" name="id" value="${loginUser.id}"><br><br>
		PW<br>
		<input disabled type="password" name="pw" value="${lgoinUser.pw}"><br><br>
		이름<br>
		<input type="text" name="name" value="${loginUser.name}" disabled><br><br>
		이메일<br>
		<input type="text" name="email" value="${loginUser.email}" disabled><br><br>
		가입당시 연락처 확인<br>
		<input type="text" name="phone" id="phone" placeholder="본인확인을 위해 연락처를 입력하세요."><br>
		<input type="button" id="get_key_btn" value="본인 인증번호 발급"><br>
		
		인증번호<input type="text" id="key" placeholder="인증번호를 입력하세요." >
		<input type="button" id="check_key_btn" value="인증 확인"><br><br>
		
		<input type="button" id="resignup_btn" value="계정 복구하기"><br><br>
		
	</form>	
</body>
</html>