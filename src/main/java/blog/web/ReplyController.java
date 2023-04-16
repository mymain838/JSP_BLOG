package blog.web;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import blog.domain.board.dto.CommonRespDto;
import blog.domain.reply.Reply;
import blog.domain.reply.dto.SaveReqDto;
import blog.service.BoardService;
import blog.service.ReplyService;
import blog.service.UserService;
import blog.util.Script;


@WebServlet("/reply")
public class ReplyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public ReplyController() {
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
		ReplyService replyService = new ReplyService();
		if(cmd.equals("save")) {
			BufferedReader br = request.getReader();
			String reqData = br.readLine();
			Gson gson = new Gson();
			SaveReqDto dto = gson.fromJson(reqData, SaveReqDto.class);
			System.out.println(dto);
			
			CommonRespDto<Reply> commonRespDto = new CommonRespDto<>();
		Reply reply = null;	
		int result = replyService.댓글쓰기(dto);
		if(result != -1) {
			reply = replyService.댓글찾기(result);
			commonRespDto.setStatusCode(1);
			commonRespDto.setData(reply);
		}else {
			commonRespDto.setStatusCode(-1);
		}
		System.out.println(result);
		
		String responseData = gson.toJson(commonRespDto);
			System.out.println(responseData);
			Script.responseData(response, responseData);
			
		}else if(cmd.equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			int result = replyService.댓글삭제(id);
			CommonRespDto commonRespDto = new CommonRespDto<>();
			commonRespDto.setStatusCode(result);
			Gson gson = new Gson();
			Script.responseData(response, gson.toJson(commonRespDto));
			
		}
	}

}
