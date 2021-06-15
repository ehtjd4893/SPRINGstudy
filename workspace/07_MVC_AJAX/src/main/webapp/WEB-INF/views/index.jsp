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
			fn1();
		})
		// 함수
		function fn1(){
			$('#btn1').on('click',function(){
				$.ajax({
					url: 'v01',		// @RequestMapping(value="v01")
					type: 'get',	// @RequestMapping(method=RequestMethod.GET)
					data: 'name=' + $('#name').val() + "&age=" + $('#age').val(),	// @RequestParam("name"), @RequestParam("age")으로 받는다.
					dataType: 'text',	// @RequestMapping(produces="text/plain; charset=utf-8")
					success: function(responseData){	// responseData에 return p로 반환한 String 데이터가 반환된다.
						console.log(responseData);
					},
					error: function(xhr, text, error){
						alert('에러');
						console.log(xhr.responseText + ", " + text + ", " + error);
					}
					
				})
			})
		} // end fn1()
		
 		function fn2(){
			$('#btn2').on('click',function(){
				$.ajax({
					url: 'v02',		// @RequestMapping(value="v01")
					type: 'get',	// @RequestMapping(method=RequestMethod.GET)
					data: $('#f').serialize(),	// @RequestParam("name"), @RequestParam("age")으로 받는다.
					dataType: 'text',	// @RequestMapping(produces="text/plain; charset=utf-8")
					success: function(responseData){	// responseData에 return p로 반환한 String 데이터가 반환된다.
						console.log(responseData);
					},
					error: function(xhr, text, error){
						alert('에러');
						console.log(xhr.responseText + ", " + text + ", " + error);
					}
					
				})
			})
		} // end fn2() 
		
		</script>
</head>
<body>
	<form id="f">
		<input type="text" id="name" name="name" placeholder="이름"><br>
		<input type="text" id="age" name="age" placeholder="나이"><br>
		<input type="button" id="btn1" value="전송1">
		<input type="button" id="btn2" value="전송2">
		<input type="button" id="btn3" value="전송3">
		<input type="button" id="btn4" value="전송4">
	</form>
</body>
</html>