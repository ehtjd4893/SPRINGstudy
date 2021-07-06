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
			fn_signout();
			fn_update_pw();
			fn_check_phone();
			fn_update();
		});	// onload
		
		// 전역변수
		var phone_pass = false;	// 휴대폰번호 양식이 올바른지 체크
		var key = null;			// 인증번호 발급시 저장해두는 변수
		var key_pass = false;	// 인증번호 입력이 올바르면 true
		
		function fn_signout(){
			$('#signout_btn').click(function(){
				if(prompt('정말 삭제하시려면 비밀번호를 입력해주세요.') == '${loginUser.pw}'){
					location.href = 'signout.do?no=${loginUser.no}';
				}
				else{
					alert('잘못된 입력입니다.');
				}
			});	// onclick
		}	// fn_signout
		
		function fn_update_pw(){
			$('#update_pw_btn').click(function(){
				if(confirm('정말 이동하시겠습니까?')){
					$('#f').attr('action', 'updatepwPage.do');
					$('#f').submit();
				}
			});	// onclick
		}	// fn_update_pw
		
		function fn_update(){
			$('#update_btn').click(function(){
				if(confirm('정말 변경하시겠습니까?')){
					
					if($('#name').val() != '${loginUser.name}'
						|| $('#email').val() != '${loginUser.email}' 
						|| $('#phone').val() != '${loginUser.phone}'
						|| $('#address').val() != '${loginUser.address}'){
						// 하나의 정보라도 변경된 게 있을 때,
						
						
						if($('#phone').val() != '${loginUser.phone}'){
							// 휴대폰 변호를 변경하였다면 인증 필요
							fn_get_key();

						}	// end if ( phone )
						else{	// 휴대폰 번호 변경 없을시 인증 없이 진행	
							$('#f').attr('action', 'update.do');
							$('#f').submit();
						}
						
					}	// end if ( val vs loginUser)
					
					
				}	// end if (confirm)
			});	// onclick
		}	// fn_update
		
		function fn_check_phone(){
			$('#phone').keyup(function(){
				// 번호 입력이 바뀐다면 중복확인과 인증번호 확인을 다시 해야 한다.
				phone_pass = false;
				key_pass = false;
			
				var regPhone = /^010[0-9]{8}$/;
				
				if(!regPhone.test($('#phone').val())){
					$('#phone_result').html('<a class="red"> 010을 포함하여 - 없이 11자리의 정수를 입력해주세요.</a>');
					return;
				} // end if
				
				// 핸드폰 번호 중복여부 체크
				$.ajax({
					url: 'phoneCheck.do',
					type: 'get',
					data: 'phone=' + $('#phone').val(),
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.phonePass == 0){	
							// 중복ID 없으면 인증번호 확인 요구
							$('#phone_result').html('<a class="blue">변경 가능한 연락처입니다. </a>');
							phone_pass = true;
						}	// end if
						else{
							// 중복이 있으면 알려줌
							$('#phone_result').html('<a class="red">이미 사용 중인 연락처입니다.</a>');
						}
					}, // end success
				});	// end ajax;
				
			}); // onkeyup
		}
		
		// 인증번호 발급 함수
		function fn_get_key(){		
			if(phone_pass == true){	// 사용해도 문제 없는 번호라면			
			// 인증번호 발급 ajax
				$.ajax({
					url: 'sendSms.do',
					type: 'get',
					data: 'phone=' + $('#phone').val(),
					dataType: 'json',
					success: function(resultMap){
						key = resultMap.key;
						var input = prompt('변경된 번호로 전송된 인증번호를 입력해주세요.');
						fn_check_key(input);
					},
					error: function(xhr, textStatus, errorThrown){
						alert('키 발급 에러');
					}
				});	// ajax
			}	// end if ( phone_pass )
		};	// fn_get_key 
		
		// 입력받은 인증번호 정상여부 확인
		function fn_check_key(input){
			
			key_pass = false;	// 초기화
			if(input == key){
				alert('인증 완료');
				key_pass = true;
				$('#f').attr('action', 'update.do');
				$('#f').submit();
			} // end if
			else{
				alert('인증 실패');
			}
		}	// fn_check_key
	</script>
</head>
<body>
	<h1> DS - Homepage에 오신 것을 환영합니다! </h1>
	
	<form id="f" method="post">
		계정 상태<br>
		<input type="hidden" value="${loginUser.no}" name="no">
		<input type="text" value="활성화" disabled><br><br>
		ID<br>
		<input disabled type="text" name="id" value="${loginUser.id}"><br><br>
		PW<br>
		<input disabled type="password" name="pw" value="${loginUser.pw}">
		<input type="button" value="비밀번호 변경하러 가기" id="update_pw_btn"><br><br>
		이름<br>
		<input type="text" name="name" id="name" value="${loginUser.name}" ><br><br>
		이메일<br>
		<input type="text" name="email" id="email" value="${loginUser.email}" ><br><br>
		주소<br>
		<input type="text" name="address" id="address" value="${loginUser.address}"><br><br>
		가입일<br>
		<input type="text" name="regdate" id="regdate" value="${loginUser.regdate}" disabled><br><br>
		
		연락처<br>
		<input type="text" name="phone" id="phone" value="${loginUser.phone}">
		<span id="phone_result"></span><br><br>

		
		<input type="button" id="signout_btn" value="탈퇴하기"><br><br>
		<input type="button" id="update_btn" value="내 정보 수정하기"><br><br>
		<input type="button" onclick='location.href="mainList.do"' value="뒤로 가기">
	</form>	
	
</body>
</html>