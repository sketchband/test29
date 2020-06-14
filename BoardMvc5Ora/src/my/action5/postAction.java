package my.action5;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class postAction implements commandAction5{

	public String requestPro
	(HttpServletRequest req, HttpServletResponse resp) throws Throwable{
		
		int num = 0, ref = 1, pos = 0, depth = 0;
		
		try {
			if(req.getParameter("num")!=null) {
				num = Integer.parseInt(req.getParameter("num"));
				ref = Integer.parseInt(req.getParameter("ref"));
				pos = Integer.parseInt(req.getParameter("pos"));
				depth = Integer.parseInt(req.getParameter("depth"));			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		req.setAttribute("num", new Integer(num));
		req.setAttribute("ref", new Integer(ref));
		req.setAttribute("pos", new Integer(pos));
		req.setAttribute("depth", new Integer(depth));
		
		return "postForm.jsp";
	}
	
}
