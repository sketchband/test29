package my.action6;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board6.BoardBean;
import my.board6.BoardDAO;

public class updateFormAction implements CommandAction6{

	@Override
	public String RequestPro(HttpServletRequest request, HttpServletResponse response) {
		
		int num = Integer.parseInt(request.getParameter("num"));
		
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardBean bean = dao.BoardRead(num);
		
		System.out.println(num);
		
		request.setAttribute("bean", bean);
		
		return "updateForm.jsp";
	}
	
}
