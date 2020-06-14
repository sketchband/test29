package my.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.controller.BoardDBBean;

public class DeleteProAction implements CommandAction{

	public String requestPro(HttpServletRequest req,HttpServletResponse resq) {
		
		int num = Integer.parseInt(req.getParameter("num"));
		int pageNum = Integer.parseInt(req.getParameter("pageNum"));
		String passwd = req.getParameter("passwd");

		BoardDBBean dao = BoardDBBean.getInstance();
		int check = dao.deleteArticle(num, passwd);
		
		req.setAttribute("check", new Integer(check));
		req.setAttribute("pageNum", new Integer(pageNum));
		
		
		return "deletePro.jsp";
	}
	
}
