package my.action6;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board6.BoardBean;
import my.board6.BoardDAO;

public class readAction implements CommandAction6{

	@Override
	public String RequestPro(HttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		int pageNum = Integer.parseInt(request.getParameter("pageNum"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardBean bean = dao.BoardRead(num);
		
		request.setAttribute("bean", bean);
		
		return "read.jsp";
	}
	
}
