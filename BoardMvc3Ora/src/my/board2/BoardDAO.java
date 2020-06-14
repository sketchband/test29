package my.board2;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class BoardDAO {

	
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getinstance() {
		return instance;
	}
	
	
	public  BoardDAO() {
		
	}
	
	public Connection getConnection() throws Throwable{
		Context initCtx = new InitialContext();
		Context envCtx = (Context) initCtx.lookup("java:comp/env");
		DataSource ds = (DataSource) envCtx.lookup("jdbc/oracle");
		return ds.getConnection();
	}
	
	public void InsertBoard(BoardBean bean) throws Throwable {
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
			stmt = con.prepareStatement("select max(num) from board2");
			rs = stmt.executeQuery();
			if(rs.next())
				number = rs.getInt(1)+1;
			else
				number = 1;
			if(num!=0) {
				sql = "update board2 set ref = ref+1 where ref = ? and pos > ?";
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
			sql = "insert into board2(num,writer,email,subject,passwd,reg_date,";
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
	
	public int CountArticles() throws Throwable{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			con = getConnection();
			sql = "select count(*) from board2";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
				count = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(con!=null) try {con.close();}catch(SQLException e) {}
		}
		
		return count;
	}
	
	public List BoardList(int start,int end) throws Throwable{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		List list = null;
		
		try {
			con = getConnection();
			sql = "select * from (select A.* , Rownum Rnum from "
					+ "( select * from board2 order by ref desc, pos asc ) A )"
					+ " where Rnum >= ? and Rnum < ? ";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, start);
			stmt.setInt(2, end);
			rs = stmt.executeQuery();
			
			if(rs.next()) {
				list = new ArrayList(end);
			do {
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
				list.add(bean);
			}while(rs.next());
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(con!=null) try {con.close();}catch(SQLException e) {}
		}
		return list;
	}
	
	public BoardBean BoardRead(int num) throws Throwable{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		BoardBean bean = new BoardBean();
		try {
			con = getConnection();
			sql = "update board2 set readcount = readcount+1 where num = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, num);
			stmt.executeUpdate();
			
			sql = "select * from board2 where num = ? ";
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
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(con!=null) try {con.close();}catch(SQLException e) {}
		}
		return bean;
	}


	public void BoardUpdate(BoardBean bean) throws Throwable{
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		String passwd2 = "";
		int num = bean.getNum();
		try {
			con = getConnection();
			
			sql = "update board2 set subject = ?, writer = ? , email = ?, content = ? where num = ? ";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bean.getSubject());
			stmt.setString(2, bean.getWriter());
			stmt.setString(3, bean.getEmail());
			stmt.setString(4, bean.getContent());
			stmt.setInt(5, num);
			stmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null) try {rs.close();}catch(SQLException e) {}
			if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
			if(con!=null) try {con.close();}catch(SQLException e) {}
		}
	}
	
	public boolean PassCheck(int num) throws Throwable {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		boolean check = false;
		try {
			con = getConnection();
			
			sql = "select passwd from board2 where num = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, num);
			rs = stmt.executeQuery();
			check = rs.next();
	}catch(Exception e) {
		e.printStackTrace();
	}finally {
		if(rs!=null) try {rs.close();}catch(SQLException e) {}
		if(stmt!=null) try {stmt.close();}catch(SQLException e) {}
		if(con!=null) try {con.close();}catch(SQLException e) {}
		}
		return check;
	}
}