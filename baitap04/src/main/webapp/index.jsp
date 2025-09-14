<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!doctype html>
<html>
<head>
<meta charset="utf-8" />
<title>Landing</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/bootstrap.min.css" />
</head>
<body class="p-4">
	<div class="container">
		<c:choose>
			<c:when test="${empty sessionScope.user}">
				<h3>Welcome — Bạn chưa đăng nhập</h3>
				<a class="btn btn-primary"
					href="${pageContext.request.contextPath}/login">Đăng nhập</a>
				<a class="btn btn-outline-secondary"
					href="${pageContext.request.contextPath}/views/register.jsp">Đăng
					ký</a>
			</c:when>
			<c:otherwise>
				<h3>Xin chào, ${sessionScope.user.fullName}</h3>
				<a class="btn btn-info" href="${pageContext.request.contextPath}/">Trang
					chính</a>
				<a class="btn btn-success"
					href="${pageContext.request.contextPath}/profile">Profile</a>
				<a class="btn btn-danger"
					href="${pageContext.request.contextPath}/logout">Logout</a>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
