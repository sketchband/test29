package my.action5;

import java.util.Collections;
import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board5.BoardBean;
import my.board5.BoardDAO;

public class listAction implements commandAction5 {

	@Override
	public String requestPro(HttpServletRequest req, HttpServletResponse resp) throws Throwable {
		
		
		int pageRecords = 5;
		String pageNum = req.getParameter("pageNum");
		if(pageNum==null){
			pageNum = "1";
		}
		int nowPage = Integer.parseInt(pageNum);
		int startRow = (nowPage-1)*pageRecords+1;
		int endRow = pageRecords;
		int count = 0;
		int number = 0;
		
		//Vector<BoardBean> vlist = null;
		List vlist = null;
		Vector<BoardBean> vlist2 = null;
		
		BoardBean bean = new BoardBean();
		BoardDAO dao = BoardDAO.getInstance();
		
		count = dao.getAllBoardCount();
		
		
		vlist = dao.BoardList(startRow, endRow);
		
		number = count-(nowPage-1)*pageRecords;
		req.setAttribute("nowPage", new Integer(pageNum));
		req.setAttribute("startRow", new Integer(startRow));
		req.setAttribute("endRow", new Integer(endRow));
		req.setAttribute("pageRecords", new Integer(pageRecords));
		req.setAttribute("count", new Integer(count));
		req.setAttribute("number", new Integer(number));
		req.setAttribute("vlist", vlist);
		
		return "list.jsp";
	}

}
