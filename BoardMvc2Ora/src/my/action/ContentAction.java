package my.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board.BoardDataBean;
import my.controller.BoardDBBean;

public class ContentAction implements CommandAction{

	public String requestPro(HttpServletRequest req,HttpServletResponse res) throws Throwable{
		
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
		
		BoardDBBean dao = BoardDBBean.getInstance();
		BoardDataBean bean = dao.getArticle(num);
		
		req.setAttribute("num", new Integer(num));
		req.setAttribute("pageNum", new Integer(pageNum));
		req.setAttribute("bean", bean);
		
		
		return "content.jsp";
	}
	
}
