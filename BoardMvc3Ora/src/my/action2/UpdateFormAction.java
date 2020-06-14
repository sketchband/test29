package my.action2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board2.BoardBean;
import my.board2.BoardDAO;

public class UpdateFormAction implements CommandAction2{

	
	public String requestPro(HttpServletRequest req,HttpServletResponse resp) throws Throwable{
	
		
		int num = Integer.parseInt(req.getParameter("num"));
		
		BoardDAO dao = new BoardDAO();
		
		BoardBean bean = dao.BoardRead(num);
		req.setAttribute("bean", bean);
		
		return "updateForm.jsp";
	}
}
