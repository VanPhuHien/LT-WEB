<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<style>
.form-container {
	max-width: 600px;
	margin: 30px auto;
	padding: 25px 30px;
	border: 1px solid #ddd;
	border-radius: 8px;
	background-color: #fafafa;
	box-shadow: 0 2px 8px rgba(0, 0, 0, 0.15);
	font-family: Arial, sans-serif;
}

.form-container h2 {
	text-align: center;
	margin-bottom: 20px;
	color: #333;
}

.form-group {
	margin-bottom: 15px;
}

.form-group label {
	display: block;
	font-weight: bold;
	margin-bottom: 6px;
	color: #444;
}

.form-group input {
	width: 100%;
	padding: 8px 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
	transition: border-color 0.2s;
}

.form-group input:focus {
	border-color: #4CAF50;
	outline: none;
}

.form-actions {
	text-align: center;
	margin-top: 20px;
}

.form-actions button, .form-actions a {
	display: inline-block;
	padding: 10px 20px;
	border-radius: 5px;
	text-decoration: none;
	margin: 0 5px;
	font-weight: bold;
	cursor: pointer;
}

.form-actions button {
	background-color: #4CAF50;
	color: white;
	border: none;
}

.form-actions button:hover {
	background-color: #45a049;
}

.form-actions a {
	background-color: #ccc;
	color: #333;
}

.form-actions a:hover {
	background-color: #bbb;
}
</style>

<div class="form-container">
	<c:set var="isNew" value="${param.id == null || param.id == '0'}" />
	<h2>
		<c:out value="${isNew ? 'Thêm User' : 'Sửa User'}" />
	</h2>

	<form
		action="${pageContext.request.contextPath}/admin/user${isNew ? '/create' : '/update'}"
		method="post">
		<input type="hidden" name="id"
			value="${param.id != null ? param.id : user.id}" />

		<div class="form-group">
			<label for="username">Username:</label> <input type="text"
				id="username" name="username" value="${user.username}" required />
		</div>

		<div class="form-group">
			<label for="fullname">Fullname:</label> <input type="text"
				id="fullname" name="fullname" value="${user.fullname}" />
		</div>

		<div class="form-group">
			<label for="password">Password:</label> <input type="password"
				id="password" name="password" value="${user.password}" />
		</div>

		<div class="form-group">
			<label for="roleid">Role ID:</label> <input type="number" id="roleid"
				name="roleid" value="${user.roleid}" />
		</div>

		<div class="form-actions">
			<button type="submit">Lưu</button>
			<a href="${pageContext.request.contextPath}/admin/user"
				class="btn btn-secondary">Quay lại</a>
		</div>
	</form>
</div>
