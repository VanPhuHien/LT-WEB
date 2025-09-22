<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8" />
<title>Admin Dashboard</title>
<style>
/* layout đơn giản */
* {
	box-sizing: border-box;
}

body {
	font-family: Arial, sans-serif;
	margin: 0;
}

header {
	background: #333;
	color: #fff;
	padding: 12px 20px;
	display: flex;
	align-items: center;
	justify-content: space-between;
}

header h1 {
	margin: 0;
	font-size: 18px;
}

nav a {
	color: #fff;
	text-decoration: none;
	margin-left: 14px;
}

.container {
	padding: 20px;
	margin-top: 0;
	min-height: calc(100vh - 110px);
}

footer {
	background: #333;
	color: #fff;
	text-align: center;
	padding: 10px;
}

.topbar {
	display: flex;
	align-items: center;
	gap: 12px;
}

.actions a {
	display: inline-block;
	padding: 8px 12px;
	background: #4CAF50;
	color: #fff;
	text-decoration: none;
	border-radius: 6px;
	margin-right: 8px;
}

.table {
	width: 100%;
	border-collapse: collapse;
	margin-top: 12px;
}

.table th, .table td {
	border: 1px solid #ddd;
	padding: 8px;
	text-align: left;
}
</style>
</head>
<body>

	<header>
		<div style="display: flex; align-items: center; gap: 16px;">
			<h1>Admin Management System</h1>
			<nav>
				<a href="${pageContext.request.contextPath}/admin/category">Categories</a>
				<a href="${pageContext.request.contextPath}/admin/user">Users</a> <a
					href="${pageContext.request.contextPath}/admin/video">Videos</a>

			</nav>
		</div>

		<div>
			<!-- nút nhanh: chuyển về index (dashboard) -->
			<a href="${pageContext.request.contextPath}/"
				style="color: #fff; text-decoration: none;">Go to site</a>
		</div>
	</header>

	<div class="container">
		<c:choose>
			<c:when test="${param.view == null || param.view == 'dashboard'}">
				<h2>Chào mừng đến với Trang Quản lý Admin!</h2>
				<p>Chọn menu để quản lý danh mục, người dùng hoặc video.</p>
			</c:when>

			<c:when test="${param.view == 'category-list'}">
				<jsp:include page="/admin/category-list.jsp" />
			</c:when>

			<c:when test="${param.view == 'user-list'}">
				<jsp:include page="/admin/user-list.jsp" />
			</c:when>

			<c:when test="${param.view == 'video-list'}">
				<jsp:include page="/admin/video-list.jsp" />
			</c:when>

			<c:when test="${param.view == 'category-form'}">
				<jsp:include page="/admin/category-form.jsp" />
			</c:when>

			<c:when test="${param.view == 'user-form'}">
				<jsp:include page="/admin/user-form.jsp" />
			</c:when>

			<c:when test="${param.view == 'video-form'}">
				<jsp:include page="/admin/video-form.jsp" />
			</c:when>

			<c:otherwise>
				<p>View không hợp lệ.</p>
			</c:otherwise>
		</c:choose>
	</div>

	<footer>
		<p>&copy; Văn Phú Hiền - 23110213.</p>
	</footer>

</body>
</html>
