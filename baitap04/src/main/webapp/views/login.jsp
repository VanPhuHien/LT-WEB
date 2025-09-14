<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Đăng nhập</title>

<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Bootstrap Icons -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.css"
	rel="stylesheet">

<style>
body {
	font-family: Arial, sans-serif;
	/* nền gradient */
	background: linear-gradient(135deg, #4a90e2, #7b4397);
	margin: 0;
	display: flex;
	height: 100vh;
	align-items: center;
	justify-content: center;
}

.login-box {
	width: 380px;
	padding: 30px;
	background: #fff;
	border-radius: 12px;
	box-shadow: 0 6px 20px rgba(0, 0, 0, 0.2);
}

.login-box h2 {
	text-align: center;
	margin-bottom: 25px;
	font-weight: bold;
	color: #4a90e2;
}

.btn-custom {
	background: #4a90e2;
	color: white;
	font-weight: 500;
	transition: 0.3s;
}

.btn-custom:hover {
	background: #357ab8;
	color: #fff;
	transform: translateY(-2px);
}

.forgot {
	text-align: center;
	margin-top: 15px;
}

.forgot a {
	color: #7b4397;
	text-decoration: none;
	font-weight: 500;
}

.forgot a:hover {
	text-decoration: underline;
}

.input-group-text {
	background: #f0f2f5;
}
</style>
</head>
<body>
	<div class="login-box">
		<h2>Đăng nhập</h2>

		<c:if test="${alert != null}">
			<div class="alert alert-danger text-center">${alert}</div>
		</c:if>

		<form action="${pageContext.request.contextPath}/login" method="post">

			<!-- Username -->
			<div class="mb-3">
				<label class="form-label">Tài khoản</label>
				<div class="input-group">
					<span class="input-group-text"><i class="bi bi-person"></i></span>
					<input type="text" name="username" class="form-control"
						placeholder="Nhập tài khoản" required>
				</div>
			</div>

			<!-- Password -->
			<div class="mb-3">
				<label class="form-label">Mật khẩu</label>
				<div class="input-group">
					<span class="input-group-text"><i class="bi bi-lock"></i></span> <input
						type="password" name="password" class="form-control"
						placeholder="Nhập mật khẩu" required>
				</div>
			</div>

			<button type="submit" class="btn btn-custom w-100">Đăng nhập</button>
		</form>

		<div class="forgot">
			<a href="${pageContext.request.contextPath}/forgot">Quên mật
				khẩu?</a>
		</div>
	</div>

	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
