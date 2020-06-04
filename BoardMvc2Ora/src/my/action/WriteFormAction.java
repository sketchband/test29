package my.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class WriteFormAction implements CommandAction{
	
	public String requestPro(HttpServletRequest request,HttpServletResponse response) {
		int num=0,ref=1,pos=0,depth=0;
		try {
			if(request.getParameter("num")!=null) {
				num = Integer.parseInt(request.getParameter("num"));
				ref = Integer.parseInt(request.getParameter("ref"));
				pos = Integer.parseInt(request.getParameter("pos"));
				depth = Integer.parseInt(request.getParameter("depth"));
			
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		request.setAttribute("num", new Integer(num));
		request.setAttribute("ref", new Integer(ref));
		request.setAttribute("pos", new Integer(pos));
		request.setAttribute("depth", new Integer(depth));
		
		return "writeForm.jsp";
	}
	
}
