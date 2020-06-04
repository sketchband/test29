package my.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import my.board.BoardDataBean;

public class BoardDBBean {
	
	private static BoardDBBean instance = new BoardDBBean();
	
	public static BoardDBBean getInstance() {
		return instance;
	}
	public BoardDBBean() {
		
	}
	
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/oracle");
		return ds.getConnection();
	}
	
	public void insertArticle(BoardDataBean bean) throws Exception{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int num = bean.getNum();
		int ref = bean.getNum();
		int pos = bean.getNum();
		int depth = bean.getDepth();
		int number = 0;
		String sql = "";
		
		try {
			con = getConnection();
			stmt = con.prepareStatement("select max(num) from board");
			rs = stmt.executeQuery();
			if(rs.next())
				number = rs.getInt(1)+1;
			else
				number = 1;
			if(num!=0) {
				sql = "update board set pos = pos+1 where ref = ? and pos > ?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, ref);
				stmt.setInt(2, pos);
				stmt.executeUpdate();
				pos = pos+1;
				depth = depth+1;
			}else {
				ref = number;
				pos = 0;
				depth = 0;
			}
			sql = "insert into board(num,writer,email,subject,passwd,reg_date,";
			sql = sql + "ref,pos,depth,content,ip)"
					  + " values(board_num_seq.nextval,?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bean.getWriter());
			stmt.setString(2, bean.getEmail());
			stmt.setString(3, bean.getSubject());
			stmt.setString(4, bean.getPasswd());
			stmt.setTimestamp(5, bean.getReg_date());
			stmt.setInt(6, ref);
			stmt.setInt(7, pos);
			stmt.setInt(8, depth);
			stmt.setString(9, bean.getContent());
			stmt.setString(10, bean.getIp());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(con!=null) try {con.close();}catch(SQLException e) {}
		}
	}
	
	public int getArticleCount() throws Exception{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			con = getConnection();
			sql = "select count(*) from board";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(stmt!=null) try {stmt.close();}catch(Exception e) {}
			if(con!=null) try {con.close();}catch(Exception e) {}
		}
		return count;
	}
	
	public List getArticles(int start,int end)throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List articleList = null;
		
		try {
			con = getConnection();
			String sql = "select a.* from (select ROWNUM as RNUM, b.* from ( select * from board order by ref desc,pos asc, where ref = ? and pos > ? )b)a where a.RNUM>=? and a.RNUM<=?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				articleList = new ArrayList(end);
				do {
					BoardDataBean bean = new BoardDataBean();
					bean.setNum(rs.getInt("num"));
					bean.setWriter(rs.getString("writer"));
					bean.setEmail(rs.getString("email"));
					bean.setSubject(rs.getString("subject"));
					bean.setPasswd(rs.getString("passwd"));
					bean.setReg_date(rs.getTimestamp("reg_date"));
					bean.setReadcount(rs.getInt("readcount"));
					bean.setRef(rs.getInt("ref"));
					bean.setPos(rs.getInt("pos"));
					bean.setDepth(rs.getInt("depth"));
					bean.setContent(rs.getString("content"));
					bean.setIp(rs.getString("ip"));
					articleList.add(bean);
				}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(stmt!=null) try {stmt.close();}catch(Exception e) {}
			if(con!=null) try {con.close();}catch(Exception e) {}
		}
		
		return articleList;
	}
}
