<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

    
    <%
    RequestDispatcher dis =
    request.getRequestDispatcher("board?cmd=list&page=0");
    dis.forward(request, response); //톰캣이 생성하는 request와 pesponse를 재사용한다. 내부적으로 움직인다뜻.

    
    %>