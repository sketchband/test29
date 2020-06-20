package my.action5;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board5.BoardBean;
import my.board5.BoardDAO;

public class postProAction implements commandAction5{

	public String requestPro
	(HttpServletRequest req, HttpServletResponse resp) throws Throwable{
		req.setCharacterEncoding("UTF-8");
		
		BoardBean bean = new BoardBean();
		
		bean.setNum(Integer.parseInt(req.getParameter("num")));
		bean.setRef(Integer.parseInt(req.getParameter("ref")));
		bean.setPos(Integer.parseInt(req.getParameter("pos")));
		bean.setDepth(Integer.parseInt(req.getParameter("depth")));
		bean.setWriter(req.getParameter("writer"));
		bean.setEmail(req.getParameter("email"));
		bean.setSubject(req.getParameter("subject"));
		bean.setPasswd(req.getParameter("passwd"));
		bean.setReg_date(new Timestamp(System.currentTimeMillis()));
		bean.setIp(req.getRemoteAddr());
		bean.setContent(req.getParameter("content"));
		
		BoardDAO dao = BoardDAO.getInstance();
		dao.insertDate(bean);
		//dao2.insertArticle(bean);	
		return "postProc.jsp";
	}
	
}
