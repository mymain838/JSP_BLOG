package blog.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import blog.domain.board.Board;
import blog.domain.board.dto.SaveReqDto;
import blog.domain.user.User;
import blog.service.BoardService;
import blog.service.UserService;
import blog.util.Script;


@WebServlet("/board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public BoardController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request,response);
	}
	
	protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cmd = request.getParameter("cmd");
		BoardService boardService = new BoardService();
		HttpSession session = request.getSession();
		
		if(cmd.equals("saveForm")) {
			User principal = (User) session.getAttribute("principal");
			if(principal != null) {
				RequestDispatcher dis = request
						.getRequestDispatcher("board/saveForm.jsp");
				dis.forward(request, response);	
			}else {
				RequestDispatcher dis = request
						.getRequestDispatcher("user/loginForm.jsp");
				dis.forward(request, response);
			}
			
		}else if(cmd.equals("save")) {
			int userId = Integer.parseInt(request.getParameter("userId"));
			String title = request.getParameter("title");
			String content = request.getParameter("content");
			SaveReqDto dto = new SaveReqDto();
			dto.setUserId(userId);
			dto.setTitle(title);
			dto.setContent(content);
			int result = boardService.글쓰기(dto);
			if(result == 1) {
				response.sendRedirect("index.jsp");	
			}else {
				Script.back(response, "글쓰기 실패");
			}
		}
		if(cmd.equals("list")) {
			int page = Integer.parseInt(request.getParameter("page")); //최초 : 0, Nxt:1, next: 2
	
			List<Board> boards = boardService.글목록보기(page);
			request.setAttribute("boards", boards);
			int boardCount=boardService.글개수();
			int lastPage = (boardCount-1)/4;
			double currentPosition= (double)page/(lastPage)*100;
			System.out.println(currentPosition);
			request.setAttribute("lastpage",lastPage);
			request.setAttribute("currentPosition", currentPosition);
			RequestDispatcher dis = request
					.getRequestDispatcher("board/list.jsp");
			dis.forward(request, response);
		}
	}

}
