package my.action2;

import java.sql.Timestamp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board2.BoardBean;
import my.board2.BoardDAO;

public class WriteProAction implements CommandAction2{

	public String requestPro(HttpServletRequest req,HttpServletResponse resp) throws Throwable{
		
		req.setCharacterEncoding("UTF-8");
		
        BoardBean bean = new BoardBean();
		
		bean.setNum(Integer.parseInt(req.getParameter("num")));
		bean.setWriter(req.getParameter("writer"));
		bean.setEmail(req.getParameter("email"));
		bean.setSubject(req.getParameter("subject"));
		bean.setPasswd(req.getParameter("passwd"));
		bean.setReg_date(new Timestamp(System.currentTimeMillis()));
		bean.setRef(Integer.parseInt(req.getParameter("ref")));
		bean.setPos(Integer.parseInt(req.getParameter("pos")));
		bean.setDepth(Integer.parseInt(req.getParameter("depth")));
		bean.setContent(req.getParameter("content"));
		bean.setIp(req.getRemoteAddr());
		
		
		BoardDAO dao = BoardDAO.getinstance();
		dao.InsertBoard(bean);
		
		return "writePro.jsp";
	}
	
}
