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
		
		#phone{
			width:97px;
		}
		#phone0{
			width: 45px;
		}
		.blue{
			color: blue;
		}
		.red{
			color: red;
		}
	</style>
	
	<script>
		$(function(){
			alert('탈퇴한 상태의 계정으로 로그인하셨습니다.')		
			fn_get_key();
			fn_check_key();
			fn_submit();
			fn_check_phone();
		});	// onload
		
		// 전역변수
		var key_pass = false;	// 인증키 인증 성공시 true
		var phone_pass = false; // 입력한 번호가 가입당시 연락처라면 true
		var key = null;			// 발급받은 인증키값 저장
		
		
		function fn_check_phone(){
			$('#phone').keyup(function(){
				// 번호 입력이 바뀐다면 중복확인과 인증번호 확인을 다시 해야 한다.
				phone_pass = false;
				key_pass = false;
			
				var regPhone = /^[0-9]{8}$/;
				
				if(!regPhone.test($('#phone').val())){
					$('#phone_result').html('<a class="red"> - 없이 8자리의 정수를 입력해주세요.</a>');
					return;
				} // end if
				// 핸드폰 번호 중복여부 체크
				$.ajax({
					url: 'myPhoneCheck.do',
					type: 'get',
					data: 'phone=010' + $('#phone').val() + '&no=${loginUser.no}',
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.phonePass == 1){	
							// 당시 사용하던 연락처라면
							$('#phone_result').html('<a class="red">인증번호 확인이 필요합니다.</a>');
							phone_pass = true;
						}	// end if
						else{
							// 당시 사용하던 연락처가 아니면 알려줌
							$('#phone_result').html('<a class="red">잘못된 연락처입니다.</a>');
						}
					}, // end success
				});	// end ajax;
				
			}); // onkeyup
		}
	


		// 인증번호 발급 함수
		function fn_get_key(){
			
			$('#get_key_btn').on('click', function(){
				
				if(phone_pass == true){	// 사용해도 문제 없는 번호라면			
				// 인증번호 발급 ajax
					$.ajax({
						url: 'sendSms.do',
						type: 'get',
						data: 'phone=010' + $('#phone').val(),
						dataType: 'json',
						success: function(resultMap){
							alert('인증번호가 발급되었습니다' + resultMap.phone + ", " + resultMap.key);	
							key = resultMap.key;
						},
						error: function(xhr, textStatus, errorThrown){
							alert('키 발급 에러');
						}
					});	// ajax
				}	// end if ( phone_pass )
			});	// onclick
		};	// fn_get_key 
		
		// 입력받은 인증번호 정상여부 확인
		function fn_check_key(){
			$('#check_key_btn').on('click',function(){
				key_pass = false;	// 초기화
				if($('#key').val() == key){
					$('#phone_result').html('<a class="blue">인증 완료</a>');
					key_pass = true;
				} // end if
				else{
					$('#phone_result').html('<a class="red">인증 실패</a>');
				}
			});	// onclick
		}	// fn_check_key
		
		// 필수항목 입력여부 확인 후 DB에 등록
		function fn_submit(){
			$('#resignup_btn').on('click', function(){		
				if(!key_pass || !phone_pass){
					alert('휴대폰 인증이 필요합니다.');
					return false;
				} else{
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
		<input type="hidden" value="${loginUser.no}" name="no">
		ID<br>
		<input disabled type="text" name="id" value="${loginUser.id}"><br><br>
		PW<br>
		<input disabled type="password" name="pw" value="${lgoinUser.pw}"><br><br>
		이름<br>
		<input type="text" name="name" value="${loginUser.name}" disabled><br><br>
		이메일<br>
		<input type="text" name="email" value="${loginUser.email}" disabled><br><br>
		가입 당시의 연락처<br>
		<input type="text" value="010" disabled id="phone0"> - <input type="text" name="phone" id="phone">
		<input type="button" id="get_key_btn" value="인증번호 발급"><br>
		인증번호<br>
		<input type="text" name="key" id="key">
		<input type="button" id="check_key_btn" value="인증번호 확인"><br><br>
		<span id="phone_result"></span><br><br>
		
		<input type="button" id="resignup_btn" value="계정 복구하기"><br><br>
	</form>	
</body>
</html>