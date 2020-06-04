package my.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

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
}
