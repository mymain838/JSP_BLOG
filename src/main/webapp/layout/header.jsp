<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<title>JSP블로그</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.4/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<link
	href="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.css"
	rel="stylesheet">
<script
	src="https://cdn.jsdelivr.net/npm/summernote@0.8.18/dist/summernote-bs4.min.js"></script>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">
</head>
<body>

	<nav class="navbar navbar-expand-md bg-dark navbar-dark">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/index.jsp">블로그</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#collapsibleNavbar">
			<span class="navbar-toggler-icon"></span>
		</button>
		<c:choose>
			<c:when test="${sessionScope.principal != null }">
				<div class="collapse navbar-collapse" id="collapsibleNavbar">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/board?cmd=saveForm">글쓰기</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/user?cmd=updateForm">회원정보</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/user?cmd=logout">로그아웃</a></li>
					</ul>
				</div>
			</c:when>
			<c:otherwise>
				<div class="collapse navbar-collapse" id="collapsibleNavbar">
					<ul class="navbar-nav">
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/user?cmd=joinForm">회원가입</a></li>
						<li class="nav-item"><a class="nav-link"
							href="<%=request.getContextPath()%>/user?cmd=loginForm">로그인</a></li>
					</ul>
				</div>
			</c:otherwise>
		</c:choose>
	</nav>
	<br>

</body>
</html>