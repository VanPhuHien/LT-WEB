<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<title>Quên mật khẩu</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f0f2f5;
}

.forgot-box {
	max-width: 420px;
	margin: 100px auto;
	padding: 25px 30px;
	background: #fff;
	border-radius: 12px;
	box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.15);
}
</style>
</head>
<body>
	<div class="forgot-box">
		<h3 class="text-center mb-4">Quên mật khẩu</h3>

		<!-- Thông báo -->
		<c:if test="${not empty error}">
			<div class="alert alert-danger">${error}</div>
		</c:if>
		<c:if test="${not empty info}">
			<div class="alert alert-info">${info}</div>
		</c:if>
		<c:if test="${not empty message}">
			<div class="alert alert-success">${message}</div>
		</c:if>

		<!-- Form -->
		<form action="${pageContext.request.contextPath}/forgot" method="post">
			<div class="mb-3">
				<label class="form-label">Email</label> <input type="email"
					name="email" value="${email}" class="form-control" required>
			</div>

			<div class="mb-3">
				<label class="form-label">Mật khẩu mới</label> <input
					type="password" name="newpass" class="form-control">
			</div>

			<div class="mb-3">
				<label class="form-label">Xác nhận mật khẩu</label> <input
					type="password" name="confirm" class="form-control">
			</div>

			<button type="submit" class="btn btn-primary w-100">Cập nhật
				mật khẩu</button>
		</form>

		<div class="text-center mt-3">
			<a href="${pageContext.request.contextPath}/login">← Quay lại
				đăng nhập</a>
		</div>
	</div>
</body>
</html>
