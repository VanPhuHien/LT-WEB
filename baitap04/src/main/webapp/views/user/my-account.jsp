<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
Object o = session.getAttribute("user");
if (o == null) {
	response.sendRedirect(request.getContextPath() + "/login");
	return;
}
hien.project.models.UserModel u = (hien.project.models.UserModel) o;
%>
<!doctype html>
<html lang="vi">
<head>
<meta charset="utf-8" />
<title>My Account</title>
<!-- Bootstrap CSS -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet">
</head>
<body class="bg-light">
	<div class="container my-5">
		<div class="card shadow-sm" style="max-width: 600px; margin: auto;">
			<div
				class="card-header d-flex justify-content-between align-items-center">
				<h5 class="mb-0">Thông tin cá nhân</h5>
				<a href="${pageContext.request.contextPath}/home"
					class="btn btn-sm btn-secondary">← Quay lại</a>
			</div>
			<div class="card-body">
				<h6 class="text-muted mb-3">
					Tên đăng nhập: <span class="fw-bold"><%=u.getUserName()%></span>
				</h6>
				<c:if test="${not empty message}">
					<div class="alert alert-info">${message}</div>
				</c:if>
				<form action="${pageContext.request.contextPath}/profile"
					method="post" enctype="multipart/form-data">
					<div class="mb-3">
						<label class="form-label">Họ và tên</label> <input type="text"
							name="fullname" value="${u.fullName}" class="form-control" />
					</div>
					<div class="mb-3">
						<label class="form-label">Số điện thoại</label> <input type="text"
							name="phone" value="${u.phone}" class="form-control" />
					</div>
					<div class="mb-3">
						<label class="form-label">Avatar</label><br />
						<c:if test="${not empty u.avatar}">
							<img src="${pageContext.request.contextPath}/uploads/${u.avatar}"
								alt="avatar" class="rounded mb-2" width="120" />
							<br />
						</c:if>
						<input type="file" name="avatar" accept="image/*"
							class="form-control" />
					</div>
					<div class="d-flex justify-content-end">
						<button type="submit" class="btn btn-primary">Lưu thay
							đổi</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<!-- Bootstrap JS -->
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>