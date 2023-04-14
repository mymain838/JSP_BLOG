package blog.web;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import blog.domain.user.dto.JoinReqDto;
import blog.domain.user.dto.LoginReqDto;
import blog.service.UserService;
import blog.util.Script;

//http://localhost:8080/blog/user
@WebServlet("/user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public UserController() {
        super();
     
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	//http://localhost:8080/blog/user?cmd=?
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		UserService userService = new UserService(); 
		if(cmd.equals("loginForm")) {
			//서비스 호출
			response.sendRedirect("user/loginForm.jsp");
			
		}else if(cmd.equals("login")) {
			//서비스 호출
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			LoginReqDto dto = new LoginReqDto();
			dto.setUsername(username);
			dto.setPassword(password);
			userService.로그인(dto);
		}
		if(cmd.equals("joinForm")) {
			
			response.sendRedirect("user/joinForm.jsp");
			
		}else if(cmd.equals("join")) {
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String email = request.getParameter("email");
			String address = request.getParameter("address");
			JoinReqDto dto = new JoinReqDto();
			dto.setAddress(address);
			dto.setUsername(username);
			dto.setEmail(email);
			dto.setPassword(password);
			System.out.println("회원가입:" +dto);
			int result = userService.회원가입(dto);
			if(result == 1) {
				response.sendRedirect("index.jsp");
				
			}else {
				Script.back(response, "회원가입 실패");
			}
			
		}
		if(cmd.equals("usernameCheck")) {
			BufferedReader br = request.getReader();
			String username = br.readLine();
			System.out.println(username);
			int result = userService.유저네임중복체크(username);
			PrintWriter out = response.getWriter();
			if(result == 1) {
				out.print("ok");
				
			}else {
				out.print("fail");
			}
			out.flush();
		}
		
		
	}
		

}
