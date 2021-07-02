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
			fn_get_key();
			fn_check_key();
			fn_submit();
			fn_id_check();
			fn_check_pw1();
			fn_check_pw2();
			fn_check_phone();
		});	// onload
		
		// 전역변수
		var key_pass = false;	// 인증키 인증 성공시 true
		var id_pass = false;	// ID 형식, 중복확인 통과시 true
		var phone_pass = false;	// 연락처 형식, 중복확인 통과시 true
		var pw1_pass = false;	// 비밀번호 형식 이상 없을 때 true
		var pw2_pass = false;	// 두 비밀번호가 같을 때 true
		var key = null;			// 발급받은 인증키값 저장
		
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
		
		// ID 형식 및 중복여부 체크 함수
		var id = null;
		function fn_id_check(){
			// 함수 실행 전 초기화작업
			$('#id').keyup(function(){
				id_pass = false;
				$('#id_result').html('<a class="red">중복확인이 필요합니다.</a>');
			});	// onkeyup
			
			
			var regID = /^[A-Za-z0-9]{4,10}$/;
			$('#id_check_btn').click(function(){
				if( !regID.test($('#id').val()) ){
					alert('ID는 대소문자, 숫자 조합의 4~10자만 가능합니다.');
					return false;
				}	// end if
				// ID형식에 문제가 없다면 Ajax를 통한 중복체크
				$.ajax({
					url: 'idCheck.do',
					type: 'get',
					data: 'id=' + $('#id').val(),
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.idPass == 0){	
							// 중복ID 없으면 span을 통해 알려줌.
							$('#id_result').html('<a class="blue">멋진 ID입니다.</a>');
							id_pass = true;
						}	// end if
						else{
							$('#id_result').html('<a class="red">이미 사용 중인 ID입니다.</a>');
						}
					}, // end success
				});	// end ajax;
			});	// onkeyup
		}	// fn_id_check
		
		
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
					url: 'phoneCheck.do',
					type: 'get',
					data: 'phone=' + "010" + $('#phone').val(),
					dataType: 'json',
					success: function(resultMap){
						if(resultMap.phonePass == 0){	
							// 중복ID 없으면 인증번호 확인 요구
							$('#phone_result').html('<a class="red">인증번호 확인이 필요합니다.</a>');
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
			$('#join_btn').on('click', function(){		
				if(!id_pass){
					alert('ID 중복확인이 필요합니다.');
					$('#id').focus();
					return false;
				} else if(!phone_pass){
					alert('휴대폰 번호 확인이 필요합니다.');
					$('#phone1').focus();
					return false;
				} else if(!key_pass){
					alert('휴대폰 인증이 필요합니다.');
					return false;
				} else if(!pw1_pass || !pw2_pass){
					alert('비밀번호 확인이 필요합니다.')
					$('#pw').focus();
					return false;
				} else{
					$('#f').submit();
				}
			});	// onclick
		}	// fn_submit
	</script>
	
</head>
<body>
	<h1>회원가입</h1>
	
	<form id="f" method="post" action="signup.do">
		
		아이디<br>
		<input type="text" name="id" id="id">
		<input type="button" id="id_check_btn" value="중복확인"><br>
		<span id="id_result"></span><br><br>
		
		비밀번호<br>
		<input type="password" name="pw" id="pw"><br>
		<span id="pw_result"></span><br>
		비밀번호 확인<br>
		<input type="password" name="pw2" id="pw2"><br>
		<span id="pw2_result"></span><br><br>
		
		이름<br>
		<input type="text" name="name" id="name"><br><br>
		
		핸드폰<br>
		<input type="text" value="010" disabled id="phone0"> - <input type="text" name="phone" id="phone">
		<input type="button" id="get_key_btn" value="인증번호 발급"><br>
		인증번호<br>
		<input type="text" name="key" id="key">
		<input type="button" id="check_key_btn" value="인증번호 확인"><br><br>
		<span id="phone_result"></span><br><br>
		
		이메일<br>
		<input type="text" name="email" id="email"><br><br>
		
		주소<br>
		<input type="text" name="address" id="address"><br><br>
		
		<input type="button" value="가입하기" id="join_btn">
		
	</form>
</body>
</html>