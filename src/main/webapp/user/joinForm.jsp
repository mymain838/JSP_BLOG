<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="../layout/header.jsp"%>



<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.4/jquery.min.js"></script>
</head>
<body>

	<div class="container">
		<h2>환영 합니다</h2>

		<form action="/blog/user?cmd=join" method="post" onsubmit="return valid()">
			<div class="d-flex justify-content-end">
				<button type="button" class="btn btn-info" onclick="usernameCheck();">중복체크</button>
			</div>
			<div class="form-group">
				<label for="username">Username:</label> <input type="text"
					class="form-control" id="username" placeholder="Enter Usesrname"
					name="username" required />
			</div>
			<div class="form-group">
				<label for="pwd">Password:</label> <input type="password"
					class="form-control" id="pwd" placeholder="Enter Password"
					name="password" required />
			</div>
			<div class="form-group">
				<label for="email">Email:</label> <input type="email"
					class="form-control" id="email" placeholder="Enter Email"
					name="email" required />
			</div>
			<div class="d-flex justify-content-end">
				<button type="button" class="btn btn-info" onclick="goPopup();">주소검색</button>
			</div>
			<div class="form-group">
				<label for="address">Address:</label> <input type="text"
					class="form-control" id="address" placeholder="Enter Address"
					name="address" required readonly />
			</div>
			<button type="submit" class="btn btn-primary">회원가입완료</button>
		</form>
	</div>

	<script>
		// opener관련 오류가 발생하는 경우 아래 주석을 해지하고, 사용자의 도메인정보를 입력합니다. ("팝업API 호출 소스"도 동일하게 적용시켜야 합니다.)
		//document.domain = "abc.go.kr";
		var isChecking = false;

		function valid() {
			if(isChecking==false){
				alert('아이디 중복체크를 해주세요');
			}
			return isChecking;
		}

		function usernameCheck() {
			//DB에서 확인 하면 isChecking true 해야함
			var username = $("#username").val();
			
			$.ajax({
				type: "POST",
				url: "/blog/user?cmd=usernameCheck",
				data: username,
				contentType:"text/plain; charset=utf-8",
				dataType:"text" //응답 받을 데이터의 타입을 적으면 자바 스크립트 오브젝트로 파싱해줌.
				
				
			}).done(function(data){
				if(data==='ok'){ //유저네임 있다는 것
					alert('유저네임이 중복되었습니다.')
				isChecking = false; //집에서 확인 12강 35:00
				}else{
					isChecking = true;	
					alert('해당 유저네임을 사용할 수 있습니다.')
				}
			});
			
		}
			
			
			

		function goPopup() {
			// 주소검색을 수행할 팝업 페이지를 호출합니다.
			// 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrLinkUrl.do)를 호출하게 됩니다.
			var pop = window.open("/blog/user/jusoPopup.jsp", "pop",
					"width=570,height=420, scrollbars=yes, resizable=yes");

			// 모바일 웹인 경우, 호출된 페이지(jusopopup.jsp)에서 실제 주소검색URL(https://business.juso.go.kr/addrlink/addrMobileLinkUrl.do)를 호출하게 됩니다.
			//var pop = window.open("/popup/jusoPopup.jsp","pop","scrollbars=yes, resizable=yes"); 
		}

		function jusoCallBack(roadFullAddr) {
			// 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
			var addressEl = document.querySelector("#address");
			addressEl.value = roadFullAddr;
		}
	</script>

</body>
</html>
