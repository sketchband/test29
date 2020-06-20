package my.action6;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board6.BoardBean;
import my.board6.BoardDAO;

public class postProAction implements CommandAction6{

	@Override
	public String RequestPro(HttpServletRequest request, HttpServletResponse response) {
		
		BoardBean bean = new BoardBean();
		BoardDAO dao = BoardDAO.getInstance();
		
//		int num = Integer.parseInt(request.getParameter("num"));
//		int ref = Integer.parseInt(request.getParameter("ref"));
//		int pos = Integer.parseInt(request.getParameter("pos"));
//		int depth = Integer.parseInt(request.getParameter("depth"));
	
		bean.setWriter(request.getParameter("writer"));
		bean.setEmail(request.getParameter("email"));
		bean.setPasswd(request.getParameter("passwd"));
		bean.setContent(request.getParameter("content"));
		bean.setSubject(request.getParameter("subject"));
		bean.setNum(Integer.parseInt(request.getParameter("num")));
		bean.setRef(Integer.parseInt(request.getParameter("ref")));
		bean.setPos(Integer.parseInt(request.getParameter("pos")));
		bean.setDepth(Integer.parseInt(request.getParameter("depth")));
		bean.setReg_date(new Timestamp(System.currentTimeMillis()));
		bean.setIp(request.getRemoteAddr());
//		bean.setNum(num);
//		bean.setRef(ref);
//		bean.setPos(pos);
//		bean.setDepth(depth);
		
		dao.BoardPost(bean);
		
		return "postPro.jsp";
	}
	
}
