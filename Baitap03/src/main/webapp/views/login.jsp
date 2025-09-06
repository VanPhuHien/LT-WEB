<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>
<style>
body {
	font-family: Arial, sans-serif;
	background: #eef2f7;
	margin: 0;
	display: flex;
	height: 100vh;
	align-items: center;
	justify-content: center;
}

.login-box {
	width: 320px;
	padding: 25px;
	background: white;
	border-radius: 10px;
	box-shadow: 0 0 12px rgba(0, 0, 0, 0.1);
}

.login-box h2 {
	margin: 0 0 20px;
	font-size: 22px;
	text-align: center;
	color: #333;
}

.login-box label {
	font-size: 14px;
	margin-bottom: 6px;
	display: block;
	color: #555;
}

.login-box input {
	width: 100%;
	padding: 10px;
	margin-bottom: 15px;
	border: 1px solid #ccc;
	border-radius: 6px;
	box-sizing: border-box;
}

.login-box button {
	width: 100%;
	padding: 10px;
	background: #4a90e2;
	border: none;
	color: white;
	font-size: 15px;
	border-radius: 6px;
	cursor: pointer;
}

.login-box button:hover {
	background: #357ab8;
}

.forgot {
	text-align: center;
	margin-top: 12px;
}

.forgot a {
	color: #4a90e2;
	text-decoration: none;
	font-size: 13px;
}

.alert {
	background: #f8d7da;
	color: #842029;
	padding: 8px;
	margin-bottom: 15px;
	border-radius: 6px;
	font-size: 14px;
	text-align: center;
}
</style>
</head>
<body>
	<div class="login-box">
		<h2>Đăng nhập</h2>
		<c:if test="${alert != null}">
			<div class="alert">${alert}</div>
		</c:if>
		<form action="${pageContext.request.contextPath}/login" method="post">
			<label>Tài khoản</label> <input type="text" name="username" required>

			<label>Mật khẩu</label> <input type="password" name="password"
				required>

			<button type="submit">Đăng nhập</button>
		</form>
		<div class="forgot">
			<a href="${pageContext.request.contextPath}/forgot">Quên mật
				khẩu?</a>
		</div>
	</div>
</body>
</html>
