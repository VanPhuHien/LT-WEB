<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Trang chủ</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet">
<style>
body {
	background-color: #f0f2f5;
}

.home-box {
	max-width: 500px;
	margin: 120px auto;
	padding: 30px;
	background: #fff;
	border-radius: 12px;
	box-shadow: 0px 4px 12px rgba(0, 0, 0, 0.1);
	text-align: center;
}
</style>
</head>
<body>
	<div class="home-box">
		<h2>
			Chào mừng Phú Hiền
			<c:out value="${sessionScope.username}" />
			!
		</h2>
		<p>Bạn đã đăng nhập thành công</p>
		<a href="${pageContext.request.contextPath}/logout"
			class="btn btn-danger mt-3">Đăng xuất</a>
	</div>
</body>
</html>
