package my.action5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board5.BoardBean;
import my.board5.BoardDAO;

public class updateFormAction implements commandAction5{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		
		int num = Integer.parseInt(req.getParameter("num"));
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardBean bean = dao.BoardRead(num);
		
		req.setAttribute("num", bean.getNum());
		req.setAttribute("writer", bean.getWriter());
		req.setAttribute("subject", bean.getSubject());
		req.setAttribute("bean", bean);
		
		return "updateForm.jsp";
	}
	
}
