package my.board5;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import java.util.*;

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
		int num = bean.getNum();
		int ref = bean.getNum();
		int pos = bean.getNum();
		int depth = bean.getDepth();
		int number = 0;
		String sql = "";
		
		try {
			con = getConnection();
			stmt = con.prepareStatement("select max(num) from board3");
			rs = stmt.executeQuery();
			if(rs.next())
				number = rs.getInt(1)+1;
			else
				number = 1;
			if(num!=0) {
				sql = "update board3 set pos = pos+1 where ref = ? and pos > ?";
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
			sql = "insert into board3 values(board_num_seq3.nextval,?,?,?,?,?,?,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bean.getWriter());
			stmt.setString(2, bean.getEmail());
			stmt.setString(3, bean.getSubject());
			stmt.setString(4, bean.getPasswd());
			stmt.setTimestamp(5, bean.getReg_date());
			stmt.setInt(6, bean.getReadcount());
			stmt.setInt(7, ref);
			stmt.setInt(8, pos);
			stmt.setInt(9, depth);
			stmt.setString(10, bean.getContent());
			stmt.setString(11, bean.getIp());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(con!=null) try {con.close();}catch(SQLException e) {}
		}
		
	}
	
	public List BoardList(int start,int end){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List vlist = null;
		//" select a.* " + " from ( " + " select ROWNUM as RNUM, b.* " + " from ( " + " select * " + " from board3 " + " order by ref desc,pos asc " + " ) b " + " ) a " + " where a.RNUM >= ? " + " and a.RNUM <= ? " 
		try {
			con = getConnection();
			String sql = " select a.* " + " from "
					+ "( " + " select ROWNUM as RNUM, b.* " + " from ( " + " select * " + " from board3 "
					+ " order by ref desc,pos asc " + " ) b " + " ) a " + " where a.RNUM >= ? " + 
					" and a.RNUM <= ? " ;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, start-2);
			stmt.setInt(2, end);
			rs = stmt.executeQuery();
			if(rs.next()) {
			vlist = new ArrayList(end);
			
				while(rs.next()) {
					BoardBean bean = new BoardBean();
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
					vlist.add(bean);
				}
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(stmt!=null) try {stmt.close();}catch(Exception e) {}
			if(con!=null) try {con.close();}catch(Exception e) {}
		}
		
		return vlist;
	}
	
	public Vector<BoardBean> BoardVector(int start,int end){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Vector<BoardBean> vlist = null;
		//" select a.* " + " from ( " + " select ROWNUM as RNUM, b.* " + " from ( " + " select * " + " from board3 " + " order by ref desc,pos asc " + " ) b " + " ) a " + " where a.RNUM >= ? " + " and a.RNUM <= ? " 
		try {
			con = getConnection();
			String sql = " select a.* " + " from "
					+ "( " + " select ROWNUM as RNUM, b.* " + " from ( " + " select * " + " from board3 "
					+ " order by ref desc,pos asc " + " ) b " + " ) a " + " where a.RNUM >= ? " + 
					" and a.RNUM <= ? " ;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, start-1);
			stmt.setInt(2, end);
			rs = stmt.executeQuery();
			
				while(rs.next()) {
					BoardBean bean = new BoardBean();
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
					vlist.add(bean);
				
			}
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(stmt!=null) try {stmt.close();}catch(Exception e) {}
			if(con!=null) try {con.close();}catch(Exception e) {}
		}
		
		return vlist;
	}
	
	public int getAllBoardCount() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			con = getConnection();
			sql = "select count(*) from board3";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
				count = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(stmt!=null) try {stmt.close();}catch(Exception e) {}
			if(con!=null) try {con.close();}catch(Exception e) {}
		}
		
		return count;
	}
	
	public BoardBean BoardRead(int num) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		BoardBean bean = new BoardBean();
		try {
			con = getConnection();
			sql = "select * from board3 where num = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, num);
			rs = stmt.executeQuery();
			if(rs.next()) {
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
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(stmt!=null) try {stmt.close();}catch(Exception e) {}
			if(con!=null) try {con.close();}catch(Exception e) {}
		}
		return bean;
	}
	
	public void BoardUpdate(BoardBean bean) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		
		
		try {
			con = getConnection();
			sql = "update board3 set subject = ?, email = ?, content = ?, passwd = ? where num = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bean.getSubject() );
			stmt.setString(2, bean.getEmail());
			stmt.setString(3, bean.getContent());
			stmt.setString(4, bean.getPasswd());
			stmt.setInt(5, bean.getNum());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(stmt!=null) try {stmt.close();}catch(Exception e) {}
			if(con!=null) try {con.close();}catch(Exception e) {}
		}
	}
	
	public void BoardDelete(int num) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		
		try {
			con = getConnection();
			sql = "delete from board3 where num = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, num);
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(Exception e) {}
			if(stmt!=null) try {stmt.close();}catch(Exception e) {}
			if(con!=null) try {con.close();}catch(Exception e) {}
		}
	}
	
}
