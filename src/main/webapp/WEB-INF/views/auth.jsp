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
    사이트 이용 전 이메일 인증
	<div class="auth_Wrap">
		<div>
			<label for="email">Email</label>
			<input type="text" id="email">
			<button type="button" id="email_btn">인증메일 발송</button>
			<p id="error_Text"></p>
		</div>

		<div>
		    <button type="button" id="auth_Check">완료</button>
		</div>
	</div>
</body>
</html>