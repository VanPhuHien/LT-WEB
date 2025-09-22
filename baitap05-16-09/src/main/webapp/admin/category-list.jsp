<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<style>
.page-container {
	max-width: 900px;
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
	width: 220px;
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
	background-color: #f1f1f1;
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
	<h2>Quản lý Category (Admin)</h2>

	<form action="${pageContext.request.contextPath}/admin/category"
		method="get" class="search-box">
		<input type="text" name="search" placeholder="Tìm theo tên" />
		<button type="submit">Tìm</button>
		<a href="${pageContext.request.contextPath}/admin/category"
			class="btn-refresh">Tất cả</a>
	</form>

	<table>
		<tr>
			<th>ID</th>
			<th>Tên</th>
			<th>Icon</th>
			<th>Thao tác</th>
		</tr>
		<c:forEach var="c" items="${categories}">
			<tr>
				<td>${c.cateId}</td>
				<td>${c.cateName}</td>
				<td><c:if test="${not empty c.icons}">
						<img src="${pageContext.request.contextPath}/uploads/${c.icons}"
							alt="icon"
							style="max-width: 50px; max-height: 50px; border: 1px solid #ddd; border-radius: 4px; padding: 2px; background: #fafafa;" />
					</c:if></td>


				<td class="actions"><a class="btn-edit"
					href="${pageContext.request.contextPath}/index.jsp?view=category-form&cateId=${c.cateId}">Sửa</a>
					<a class="btn-delete"
					href="${pageContext.request.contextPath}/admin/category/delete?cateId=${c.cateId}"
					onclick="return confirm('Bạn chắc chắn muốn xóa?')">Xóa</a></td>
			</tr>
		</c:forEach>
	</table>

	<a class="btn-add"
		href="${pageContext.request.contextPath}/index.jsp?view=category-form&cateId=0">Thêm
		mới</a> <a class="btn-back"
		href="${pageContext.request.contextPath}/index.jsp">Quay lại</a>
</div>
