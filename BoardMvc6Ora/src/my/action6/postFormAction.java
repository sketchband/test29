package my.action6;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board6.BoardBean;
import my.board6.BoardDAO;

public class postFormAction implements CommandAction6{

	@Override
	public String RequestPro(HttpServletRequest request, HttpServletResponse response) {
		
		int num = 0 , ref = 1 , pos = 0, depth = 0;
	
		request.setAttribute("num", new Integer(num));
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("pos", new Integer(pos));
		request.setAttribute("depth", new Integer(depth));
		
		return "postForm.jsp";
	}
	
}
