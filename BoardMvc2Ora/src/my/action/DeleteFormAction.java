package my.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DeleteFormAction implements CommandAction{
	
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable{
		
		int num = Integer.parseInt(req.getParameter("num"));
		String pageNum = req.getParameter("pageNum");
				
		req.setAttribute("num", new Integer(num));
		req.setAttribute("pageNum", new Integer(pageNum));
		System.out.println(num);
		System.out.println(pageNum);
		return "deleteForm.jsp"; 		
	}
	
}
