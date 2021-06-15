<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title></title>
	
</head>
<body>
	<!-- form action의 결론 -->
	<!-- 
		1. 슬래시로 시작하려면 컨텍스트 패스부터 작성한다
		2. 슬래시 없이 시작하려면 매핑만 작성한다.
	 -->


	<!--  /mvc04 는 contextPath를 의미하며 ('/')로 시작해야 한다. -->
	<form action="/mvc04/v01">		<
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
	
	<hr>
	
	<!-- action="mvc04/v01"의 경우 디렉터리로 인식되어 제대로 동작하지 않음. -->
	<form action="mvc04/v01">		
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
	
	<hr>
	
	<!-- action="/v01"의 경우 매핑이 컨텍스트패스로 인식되어 제대로 동작하지 않음. -->
	<form action="/v01">		
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
	
	<hr>
	
	<!-- 매핑: 잘 동작함. -->
	<form action="v01">		
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
	
	<hr>
	<h2>v02</h2>
	<!-- 새로운 매핑 -->
	<form action="v02">		
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
	
	<hr>
	<h2>v04</h2>
	<!-- 새로운 매핑 -->
	<form action="v04">		
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
	<hr>
	<h2>v06</h2>
	<!-- 새로운 매핑 -->
	<form action="v06">		
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
</body>
</html>
