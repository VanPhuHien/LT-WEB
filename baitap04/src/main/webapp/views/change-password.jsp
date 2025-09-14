<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
    Object o = session.getAttribute("user");
    if (o == null) {
        response.sendRedirect(request.getContextPath() + "/login");
        return;
    }
%>
<!doctype html>
<html lang="vi">
<head>
<meta charset="utf-8" />
<title>Đổi mật khẩu</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">

	<div class="container my-5">
		<div class="card shadow-sm" style="max-width: 500px; margin: auto;">
			<div
				class="card-header d-flex justify-content-between align-items-center">
				<h5 class="mb-0">Đổi mật khẩu</h5>
				<a href="${pageContext.request.contextPath}/home"
					class="btn btn-sm btn-secondary">← Quay lại</a>
			</div>
			<div class="card-body">

				<c:if test="${not empty error}">
					<div class="alert alert-danger">${error}</div>
				</c:if>
				<c:if test="${not empty message}">
					<div class="alert alert-success">${message}</div>
				</c:if>

				<form action="${pageContext.request.contextPath}/change-password"
					method="post">
					<div class="mb-3">
						<label class="form-label">Mật khẩu cũ</label> <input
							type="password" name="oldpass" class="form-control" required />
					</div>

					<div class="mb-3">
						<label class="form-label">Mật khẩu mới</label> <input
							type="password" name="newpass" class="form-control" required />
					</div>

					<div class="mb-3">
						<label class="form-label">Xác nhận mật khẩu mới</label> <input
							type="password" name="confirmpass" class="form-control" required />
					</div>

					<div class="d-flex justify-content-end">
						<button type="submit" class="btn btn-primary">Đổi mật
							khẩu</button>
					</div>
				</form>
			</div>
		</div>
	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>
