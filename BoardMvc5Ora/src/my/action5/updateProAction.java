package my.action5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board5.BoardBean;
import my.board5.BoardDAO;

public class updateProAction implements commandAction5{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		
		int num = Integer.parseInt(req.getParameter("num"));
		
		BoardDAO dao = BoardDAO.getInstance();
		System.out.println(num);
		
		BoardBean bean = new BoardBean();
		
		 bean.setNum(Integer.parseInt(req.getParameter("num")));
		 bean.setWriter(req.getParameter("writer"));
		 bean.setEmail(req.getParameter("email"));
		 bean.setSubject(req.getParameter("subject"));
		 bean.setContent(req.getParameter("content"));
		 bean.setPasswd(req.getParameter("passwd"));
		
		dao.BoardUpdate(bean);
		
		
		
		return "updatePro.jsp";
	}
	
}
