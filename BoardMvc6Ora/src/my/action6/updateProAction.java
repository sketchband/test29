package my.action6;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board6.BoardBean;
import my.board6.BoardDAO;

public class updateProAction implements CommandAction6{
	
	@Override
	public String RequestPro(HttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardBean bean = new BoardBean();		
		BoardDAO dao = BoardDAO.getInstance();
		
		bean.setNum(num);
		bean.setSubject(request.getParameter("subject"));
		bean.setEmail(request.getParameter("email"));
		bean.setContent(request.getParameter("content"));
		
		dao.BoardUpdate(bean);
		
		return "updatePro.jsp";
	}
	
}
