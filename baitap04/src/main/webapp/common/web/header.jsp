<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>

<nav class="navbar navbar-expand-lg navbar-light bg-light shadow-sm">
	<div class="container">
		<a class="navbar-brand fw-bold"
			href="${pageContext.request.contextPath}/home"> Trang web của Phú
			Hiền </a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navMain">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navMain">
			<ul class="navbar-nav ms-auto">
				<c:choose>
					<c:when test="${not empty sessionScope.user}">
						<li class="nav-item dropdown"><a
							class="nav-link dropdown-toggle d-flex align-items-center"
							href="#" id="userMenu" role="button" data-bs-toggle="dropdown">
								<img class="rounded-circle me-2"
								src="${pageContext.request.contextPath}/uploads/${sessionScope.user.avatar}"
								onerror="this.src='${pageContext.request.contextPath}/uploads/cat.png'"
								alt="avatar" width="32" height="32" /> <span>${sessionScope.user.fullName}</span>
						</a>
							<ul class="dropdown-menu dropdown-menu-end shadow"
								aria-labelledby="userMenu">
								<li><a class="dropdown-item"
									href="${pageContext.request.contextPath}/profile"> <i
										class="bi bi-person-circle me-2"></i> My Account
								</a></li>
								<li><a class="dropdown-item"
									href="${pageContext.request.contextPath}/change-password">
										<i class="bi bi-key me-2"></i> Đổi mật khẩu
								</a></li>
								<li><hr class="dropdown-divider"></li>
								<li><a class="dropdown-item text-danger"
									href="${pageContext.request.contextPath}/logout"> <i
										class="bi bi-box-arrow-right me-2"></i> Logout
								</a></li>
							</ul></li>
					</c:when>
					<c:otherwise>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/login">Login</a></li>
						<li class="nav-item"><a class="nav-link"
							href="${pageContext.request.contextPath}/register">Register</a></li>
					</c:otherwise>
				</c:choose>
			</ul>
		</div>
	</div>
</nav>
