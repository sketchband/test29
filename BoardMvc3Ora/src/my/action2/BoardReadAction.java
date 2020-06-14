package my.action2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board2.BoardBean;
import my.board2.BoardDAO;

public class BoardReadAction implements CommandAction2{

	public String requestPro(HttpServletRequest req,HttpServletResponse resp) throws Throwable{
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		
		BoardDAO dao = BoardDAO.getinstance();
		
		BoardBean bean = dao.BoardRead(num);
		
		req.setAttribute("num", new Integer(num));
		req.setAttribute("pageNum", new Integer(pageNum));
		req.setAttribute("bean", bean);
		
		return "boardReadForm.jsp";
	}
	
}
