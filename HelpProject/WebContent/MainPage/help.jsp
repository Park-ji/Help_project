<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://fonts.googleapis.com/css?family=Do+Hyeon&display=swap&subset=korean"
	rel="stylesheet">
<link rel= "stylesheet" type="text/css" href="helpform.css">

<script src="http://code.jquery.com/jquery-latest.min.js"></script>

<script>
$(document).ready(function(){
	$("#join").on('click',function(){
		location.href = "join.jsp";
	});	
}); 

</script>

</head>
<body>

<div class="hey">
	<!-- <span class="content">저기요</span>  -->
	<span class="content"><img src="hey.jpg" width=500px></span> 
	<span class="blank"></span><br>
	<div class ="login">	
	<form action="login.mvc">
	<table>
	<tr><td><input type="text" id="id" name="id" placeholder="아이디"></td></tr>
	<tr><td><input type="password" id="pw" name="pw" placeholder="비밀번호"></td></tr>
	<tr align="center"><td colspan="2"><input type="submit" id="login" value="로그인">	
	<input type="button" id="join" value="회원가입"></td></tr>
	</table>
	</form>	
	</div>
</div>

</body>
</html>