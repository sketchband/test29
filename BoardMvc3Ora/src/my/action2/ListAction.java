package my.action2;

import java.sql.Timestamp;
import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board2.BoardBean;
import my.board2.BoardDAO;

public class ListAction implements CommandAction2{

	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable{
	
		BoardBean bean = new BoardBean();
		
		String pageNum = req.getParameter("pageNum");
		if(pageNum==null) {
			pageNum = "1";
		}
		int pageRecords = 5;
		int nowPage = Integer.parseInt(pageNum);
		int startRow = (nowPage-1)*pageRecords+1;
		int endRow = nowPage*pageRecords;
		int count;
		
		List list = null;
		
		BoardDAO dao = BoardDAO.getinstance();
		count = dao.CountArticles();
		
		if(count>0) {
			list = dao.BoardList(startRow, endRow);
		}else {
			list = Collections.EMPTY_LIST;
		}
		
		req.setAttribute("nowPage", new Integer(nowPage));
		req.setAttribute("startRow", new Integer(startRow));
		req.setAttribute("endRow", new Integer(endRow));
		req.setAttribute("count", new Integer(count));
		req.setAttribute("pageRecords", new Integer(pageRecords));
		req.setAttribute("list", list);
		
		return "list.jsp";
	}
}
