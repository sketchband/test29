package my.action2;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board2.BoardBean;
import my.board2.BoardDAO;

public class UpdateProAction implements CommandAction2{

	public String requestPro(HttpServletRequest req,HttpServletResponse resp) throws Throwable{
		
		req.setCharacterEncoding("UTF-8");
		
		String pageNum = req.getParameter("pageNum");
		
		BoardBean bean = new BoardBean();
		
		int num = Integer.parseInt(req.getParameter("num"));
		
		
		System.out.println(num);
		
		bean.setNum(Integer.parseInt(req.getParameter("num")));
		bean.setWriter(req.getParameter("writer"));
		bean.setEmail(req.getParameter("email"));
		bean.setSubject(req.getParameter("subject"));
		bean.setContent(req.getParameter("content"));
		bean.setPasswd(req.getParameter("passwd"));
		
		BoardDAO dao = BoardDAO.getinstance();
		boolean check = dao.PassCheck(num);
		
		if(check) {
			return "updatePro.jsp";
		}
		
		/*BoardBean bean = new BoardBean();
		BoardDAO dao = BoardDAO.getinstance();
		String passwd = req.getParameter("passwd");
		
		int num = Integer.parseInt(req.getParameter("num"));
		
		System.out.println(dao.PassCheck(num));
		
		if(passwd.equals(dao.PassCheck(num))) {
			System.out.println("true");
		}else {
			System.out.println("false");
		}*/
		
		return "updatePro2.jsp";
	}
	
}
