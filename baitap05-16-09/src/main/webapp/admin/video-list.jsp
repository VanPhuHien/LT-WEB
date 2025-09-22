<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<style>
.page-container {
	max-width: 1000px;
	margin: 20px auto;
	font-family: Arial, sans-serif;
}

h2 {
	text-align: center;
	margin-bottom: 20px;
	color: #333;
}

.search-box {
	text-align: right;
	margin-bottom: 15px;
}

.search-box input[type="text"] {
	padding: 8px 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	width: 250px;
}

.search-box button {
	padding: 8px 15px;
	border: none;
	background-color: #4CAF50;
	color: #fff;
	border-radius: 5px;
	cursor: pointer;
}

.search-box button:hover {
	background-color: #45a049;
}

table {
	width: 100%;
	border-collapse: collapse;
	background-color: #fff;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
}

th, td {
	padding: 12px;
	text-align: left;
	border-bottom: 1px solid #ddd;
}

th {
	background-color: #4CAF50;
	color: white;
}

tr:hover {
	background-color: #f9f9f9;
}

.actions a {
	display: inline-block;
	padding: 6px 12px;
	border-radius: 5px;
	text-decoration: none;
	margin: 0 3px;
	font-size: 14px;
}

.btn-edit {
	background-color: #ffc107;
	color: #333;
}

.btn-edit:hover {
	background-color: #e0a800;
}

.btn-delete {
	background-color: #dc3545;
	color: #fff;
}

.btn-delete:hover {
	background-color: #c82333;
}

.btn-add {
	display: inline-block;
	margin-top: 15px;
	padding: 10px 20px;
	background-color: #007bff;
	color: #fff;
	border-radius: 5px;
	text-decoration: none;
}

.btn-add:hover {
	background-color: #0056b3;
}

.btn-back {
	display: inline-block;
	margin-top: 15px;
	padding: 10px 20px;
	background-color: #6c757d;
	color: #fff;
	border-radius: 5px;
	text-decoration: none;
}

.btn-back:hover {
	background-color: #5a6268;
}

.btn-refresh {
	display: inline-block;
	padding: 8px 15px;
	margin-left: 5px;
	background-color: #17a2b8;
	color: #fff;
	border-radius: 5px;
	text-decoration: none;
}

.btn-refresh:hover {
	background-color: #138496;
}
</style>

<div class="page-container">
	<h2>Quản lý Video (Admin)</h2>

	<!-- Form tìm kiếm -->
	<form action="${pageContext.request.contextPath}/admin/video"
		method="get" class="search-box">
		<input type="text" name="search" placeholder="Tìm theo title" />
		<button type="submit">Tìm</button>
		<a href="${pageContext.request.contextPath}/admin/video"
			class="btn-refresh">Tất cả</a>
	</form>

	<!-- Bảng Video -->
	<table>
		<tr>
			<th>ID</th>
			<th>Title</th>
			<th>Description</th>
			<th>URL</th>
			<th>Views</th>
			<th>Category</th>
			<th>User</th>
			<th>Thao tác</th>
		</tr>
		<c:forEach var="v" items="${videos}">
			<tr>
				<td>${v.videoId}</td>
				<td>${v.title}</td>
				<td><c:choose>
						<c:when test="${fn:length(v.description) > 50}">
							${fn:substring(v.description, 0, 50)}...
						</c:when>
						<c:otherwise>
							${v.description}
						</c:otherwise>
					</c:choose></td>
				<td><a href="${v.url}" target="_blank">Link</a></td>
				<td>${v.views}</td>
				<td>${v.category.cateName}</td>
				<td>${v.user.fullname}</td>
				<td class="actions"><a class="btn-edit"
					href="${pageContext.request.contextPath}/index.jsp?view=video-form&id=${v.videoId}">Sửa</a>
					<a class="btn-delete"
					href="${pageContext.request.contextPath}/admin/video/delete?id=${v.videoId}"
					onclick="return confirm('Bạn có chắc muốn xóa video này?')">Xóa</a></td>
			</tr>
		</c:forEach>
	</table>

	<a class="btn-add"
		href="${pageContext.request.contextPath}/index.jsp?view=video-form&id=0">Thêm
		mới</a> <a class="btn-back"
		href="${pageContext.request.contextPath}/index.jsp">Quay lại</a>
</div>
