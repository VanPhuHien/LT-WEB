<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="jakarta.tags.core"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/xml" prefix="x"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<form action="login" method="post">
		<h2>Nhập tên và mật khẩu để đăng nhập</h2>
		<c:if test="${alert !=null}">
			<h3 class="alert alertdanger">${alert}</h3>
		</c:if>
		<section>
			<label class="input login-input">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa
fa-user"></i></span>
					<input type="text" placeholder="Tài khoản" name="username"
						class="form-control">
				</div>
			</label> <label class="input login-input">
				<div class="input-group">
					<span class="input-group-addon"><i class="fa
fa-user"></i></span>
					<input type="password" placeholder="Mật khẩu" name="password"
						class="form-control">
				</div>
			</label>

		</section>
		<!-- Nút submit -->
		<div class="d-grid">
			<button type="submit" class="btn btn-primary">Đăng nhập</button>
		</div>
</body>
</html>