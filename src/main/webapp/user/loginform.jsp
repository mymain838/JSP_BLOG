<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div class="container">
		<h2>환영 합니다</h2>

		<form action="/blog/user?cmd=login" method="post">
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" id="username" placeholder="Enter Usesrname"
					name="username" required />
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" id="pwd" placeholder="Enter Password"
					name="password" required />
			<button type="submit" class="btn btn-primary">로그인</button>
		</form>
	</div>
</body>
</html>