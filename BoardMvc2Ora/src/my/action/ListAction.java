package my.action;

import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sun.java.swing.plaf.windows.WindowsTreeUI.CollapsedIcon;

import my.controller.BoardDBBean;

public class ListAction implements CommandAction{

	public String requestPro(HttpServletRequest request, HttpServletResponse response) throws Throwable{
		
		String pageNum = request.getParameter("pageNum");
		
		if(pageNum==null) {
			pageNum = "1";
		}
		int pageRecords = 10;
		int nowPage = Integer.parseInt(pageNum);
		int startRow = (nowPage-1)*pageRecords+1;
		int endRow = nowPage*pageRecords;
		int count = 0;
		int number = 0;
		
		List articleList = null;
		BoardDBBean dao = new BoardDBBean();
		count = dao.getArticleCount();
		
		if(count>0) {
			articleList = dao.getArticles(startRow, endRow);
		}else {
			articleList = Collections.EMPTY_LIST;
		}
		
		number = count-(nowPage-1)*pageRecords;
		request.setAttribute("nowPage", new Integer("nowPage"));
		request.setAttribute("startRow", new Integer("startRow"));
		request.setAttribute("endRow", new Integer("endRow"));
		request.setAttribute("count", new Integer("count"));
		request.setAttribute("pageRecords", new Integer("pageRecords"));
		request.setAttribute("number", new Integer("number"));
		request.setAttribute("articleList", articleList);
		
		return "list.jsp";
		}
	
}
