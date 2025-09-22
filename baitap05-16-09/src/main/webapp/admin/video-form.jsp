<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ taglib uri="jakarta.tags.core" prefix="c"%>

<style>
.form-container {
	max-width: 600px;
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
}

.form-group input, .form-group textarea, .form-group select {
	width: 100%;
	padding: 8px 10px;
	border: 1px solid #ccc;
	border-radius: 5px;
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
	cursor: pointer;
	font-weight: bold;
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
		<c:out value="${isNew ? 'Thêm Video' : 'Sửa Video'}" />
	</h2>

	<form
		action="${pageContext.request.contextPath}/admin/video${isNew ? '/create' : '/update'}"
		method="post">
		<input type="hidden" name="id"
			value="${param.id != null ? param.id : video.videoId}" />

		<div class="form-group">
			<label>Title</label> <input type="text" name="title"
				value="${video.title}" required />
		</div>

		<div class="form-group">
			<label>Description</label>
			<textarea name="description" rows="3">${video.description}</textarea>
		</div>

		<div class="form-group">
			<label>URL</label> <input type="text" name="url"
				value="${video.url}" />
		</div>

		<div class="form-group">
			<label>Views</label> <input type="number" name="views"
				value="${video.views}" />
		</div>

		<div class="form-group">
			<label>Category</label> <select name="categoryId">
				<c:forEach var="cat" items="${categories}">
					<option value="${cat.cateId}"
						${video.category != null && video.category.cateId == cat.cateId ? 'selected' : ''}>
						${cat.cateName}</option>
				</c:forEach>
			</select>
		</div>

		<div class="form-group">
			<label>User</label> <select name="userId">
				<c:forEach var="u" items="${users}">
					<option value="${u.id}"
						${video.user != null && video.user.id == u.id ? 'selected' : ''}>
						${u.fullname}</option>
				</c:forEach>
			</select>
		</div>

		<div class="form-actions">
			<button type="submit">Lưu</button>
			<a href="${pageContext.request.contextPath}/admin/video"
				class="btn btn-secondary">Quay lại</a>
		</div>
	</form>
</div>
