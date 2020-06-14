package my.action2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board2.BoardBean;

public class WriteFormAction implements CommandAction2{

	public String requestPro(HttpServletRequest req,HttpServletResponse resp) throws Throwable{
		
		int num = 0,ref = 1, pos = 0, depth = 0;
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
		
		return "writeForm.jsp";
		/*BoardBean bean = new BoardBean();
		
		int num = Integer.parseInt(req.getParameter("num"));
		int ref = Integer.parseInt(req.getParameter("ref"));
		int pos = Integer.parseInt(req.getParameter("pos"));
		int depth = Integer.parseInt(req.getParameter("depth"));
		String writer = req.getParameter("writer");
		String subject = req.getParameter("subject");
		String passwd = req.getParameter("passwd");
		String email = req.getParameter("email");
		String content = req.getParameter("content");
		
		req.setAttribute("num", new Integer(num));
		req.setAttribute("ref", new Integer(ref));
		req.setAttribute("pos", new Integer(pos));
		req.setAttribute("depth", new Integer(depth));
		req.setAttribute("writer", writer);
		req.setAttribute("subject", subject);
		req.setAttribute("passwd", passwd);
		req.setAttribute("email", email);
		req.setAttribute("content", content);
		
		return "writePro.jsp";*/
	}
	
}
