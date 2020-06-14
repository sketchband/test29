package my.action;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board.BoardDataBean;
import my.controller.BoardDBBean;

public class WriteProAction implements CommandAction{
	public String requestPro(HttpServletRequest request,HttpServletResponse response) throws Throwable{
		request.setCharacterEncoding("UTF-8");
		BoardDataBean bean = new BoardDataBean();
		bean.setNum(Integer.parseInt(request.getParameter("num")));
		bean.setWriter(request.getParameter("writer"));
		bean.setEmail(request.getParameter("email"));
		bean.setSubject(request.getParameter("subject"));
		bean.setPasswd(request.getParameter("passwd"));
		bean.setReg_date(new Timestamp(System.currentTimeMillis()));
		bean.setRef(Integer.parseInt(request.getParameter("ref")));
		bean.setPos(Integer.parseInt(request.getParameter("pos")));
		bean.setDepth(Integer.parseInt(request.getParameter("depth")));
		bean.setContent(request.getParameter("content"));
		bean.setIp(request.getRemoteAddr());
		
		System.out.println(Integer.parseInt(request.getParameter("num")));
		
		BoardDBBean dao = BoardDBBean.getInstance();
		dao.insertArticle(bean);
		
		return "writePro.jsp";
		
	}
}
