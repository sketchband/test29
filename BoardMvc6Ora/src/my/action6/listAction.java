package my.action6;

import java.util.List;
import java.util.Vector;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import my.board6.BoardBean;
import my.board6.BoardDAO;

public class listAction implements CommandAction6{

	@Override
	public String RequestPro(HttpServletRequest request, HttpServletResponse response) {
		
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum==null) {
			pageNum = "1";
		}
		
		int nowPage = Integer.parseInt(pageNum);
		
		int pageRecords = 5;
		int startRow = (nowPage-1)*pageRecords+1;
		int endRow = pageRecords*nowPage;
		List vlist2 = null;
		BoardBean bean = new BoardBean();
		BoardDAO dao = BoardDAO.getInstance();
		int count = 5;
		//Vector<BoardBean> vlist = dao.BoardList(startRow, endRow);
		vlist2 = dao.BoardList2(startRow, endRow);
		//request.setAttribute("vlist", vlist);
		request.setAttribute("nowPage", new Integer(nowPage));
		request.setAttribute("startRow", new Integer(startRow));
		request.setAttribute("endRow", new Integer(endRow));
		request.setAttribute("count", new Integer(count));
		request.setAttribute("pageRecords", new Integer(pageRecords));
		request.setAttribute("vlist2", vlist2);
		
		return "list.jsp";
	}
	
}
