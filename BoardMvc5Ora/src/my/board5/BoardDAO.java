package my.board5;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import my.board5.BoardBean;

public class BoardDAO{

	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	
	public BoardDAO() {
		
	}
	
	public Connection getConnection() throws Exception {
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource)envCtx.lookup("jdbc/oracle");
		return ds.getConnection();
	}
	
	public void insertDate(BoardBean bean) throws Exception{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		int num = bean.getNum();
		int ref = bean.getRef();
		int pos = bean.getPos();
		int depth = bean.getDepth();
		
		try {
			
			con = getConnection();
			
			sql = "select max(ref) from board3";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
			ref = rs.getInt(1)+1;
			
			sql = "update board3 set pos = pos + 1 where ref = ? and pos > ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, ref);
			stmt.setInt(2, pos);
			stmt.executeUpdate();
			
			sql = "insert into board3 values(board_num_seq3,?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bean.getWriter());
			stmt.setString(2, bean.getEmail());
			stmt.setString(3, bean.getSubject());
			stmt.setString(4, bean.getPasswd());
			stmt.setTimestamp(5, bean.getReg_date());
			stmt.setInt(6, ref);
			stmt.setInt(7, pos+1);
			stmt.setInt(8, depth+1);
			stmt.setString(9, bean.getContent());
			stmt.setString(10, bean.getIp());
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e){}
			if(stmt!=null) try {stmt.close();}catch(Exception e){}
			if(con!=null) try {con.close();}catch(Exception e){}
		}
		
	}
	
}
