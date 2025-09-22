<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<style>
.form-container {
	max-width: 500px;
	margin: 30px auto;
	padding: 20px 30px;
	border: 1px solid #ddd;
	border-radius: 8px;
	background-color: #fafafa;
	box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
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
	<c:set var="isNew"
		value="${param.cateId == null || param.cateId == '0'}" />
	<h2>
		<c:out value="${isNew ? 'Thêm Category' : 'Sửa Category'}" />
	</h2>

	<form
		action="${pageContext.request.contextPath}/admin/category${isNew ? '/create' : '/update'}"
		method="post" enctype="multipart/form-data">

		<input type="hidden" name="cateId"
			value="${param.cateId != null ? param.cateId : category.cateId}" />

		<div class="form-group">
			<label for="cateName">Tên:</label> <input type="text" id="cateName"
				name="cateName" value="${category.cateName}" required />
		</div>

		<div class="form-group">
			<label for="iconFile">Icon:</label> <input type="file" id="iconFile"
				name="iconFile" accept="image/*" />
			<c:if test="${not empty category.icons}">
				<p>Ảnh hiện tại:</p>
				<img
					src="${pageContext.request.contextPath}/uploads/${category.icons}"
					alt="icon"
					style="max-width: 80px; border: 1px solid #ccc; padding: 3px;" />
			</c:if>
		</div>


		<div class="form-actions">
			<button type="submit">Lưu</button>
			<a href="${pageContext.request.contextPath}/admin/category">Quay
				lại</a>
		</div>
	</form>
</div>
