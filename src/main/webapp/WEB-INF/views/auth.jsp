<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Email Auth</title>
<script src="https://code.jquery.com/jquery-3.4.1.js"></script>
<script src="resources/js/common/emailValid.js"></script>
<style>
	.auth_Wrap {text-align:center;}
</style>
</head>
<body>
	<div class="auth_Wrap">
		<div>
			<label for="email">Email</label>
			<input type="text" id="email">
			<button type="button" id="email_btn">인증번호 전송</button>
			<p id="error_Text"></p>
		</div>

		<div>
			<label for="authKey">인증번호</label>
			<input type="text" id="authKey">
			<button type="button" id="certi_Btn">인증하기</button>
			<p id="auth_Error"></p>
		</div>

		<div>
			<button type="button" id="auth_Check">인증 완료</button>
		</div>
	</div>
</body>
</html>