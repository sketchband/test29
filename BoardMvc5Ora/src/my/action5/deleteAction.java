package my.action5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board5.BoardDAO;

public class deleteAction implements commandAction5{

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {

		int num = Integer.parseInt(req.getParameter("num"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		dao.BoardDelete(num);
		
		return "deleteForm.jsp";
	}
	
}
