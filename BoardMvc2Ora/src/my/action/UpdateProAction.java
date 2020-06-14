package my.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board.BoardDataBean;
import my.controller.BoardDBBean;

public class UpdateProAction implements CommandAction {

	public String requestPro(HttpServletRequest req,HttpServletResponse res) throws Throwable{
		
		req.setCharacterEncoding("UTF-8");
		
		String pageNum = req.getParameter("pageNum");
		
		 BoardDataBean bean = new BoardDataBean();
		 bean.setNum(Integer.parseInt(req.getParameter("num")));
		 bean.setWriter(req.getParameter("writer"));
		 bean.setEmail(req.getParameter("email"));
		 bean.setSubject(req.getParameter("subject"));
		 bean.setContent(req.getParameter("content"));
		 bean.setPasswd(req.getParameter("passwd"));
		 
		 BoardDBBean dao = BoardDBBean.getInstance();
		 int check = dao.updateArticle(bean);
		 req.setAttribute("pageNum", new Integer(pageNum));
		 req.setAttribute("check", new Integer(check));
		 System.out.println(pageNum);
		 System.out.println(check);
		 
		 return "updatePro.jsp";
		
	}

	
}
