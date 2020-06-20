package my.board6;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import my.board6.BoardDAO;

public class BoardDAO {

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
	
	public void BoardPost(BoardBean bean) {
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
			
			sql = "select max(ref) from board6";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
				ref = rs.getInt(1)+1;
			
			sql = "select max(num) from board6";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
				num = rs.getInt(1)+1;
			
			sql = "update board6 set pos = pos + 1 where ref = ? and pos > ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, ref);
			stmt.setInt(2, pos);
			stmt.executeUpdate();
			
			
			sql = "insert into board6 values(?,?,?,?,?,?,0,?,?,?,?,?)";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, num);
			stmt.setString(2, bean.getWriter());
			stmt.setString(3, bean.getEmail());
			stmt.setString(4, bean.getSubject());
			stmt.setString(5, bean.getPasswd());
			stmt.setTimestamp(6, bean.getReg_date());
			stmt.setInt(7, ref);
			stmt.setInt(8, pos+1);
			stmt.setInt(9, depth+1);
			stmt.setString(10, bean.getContent());
			stmt.setString(11, bean.getIp());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally{
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(stmt!=null)try {stmt.close();}catch(Exception e) {}
			if(con!=null)try {con.close();}catch(Exception e) {}
		}
	}
	
//	public Vector<BoardBean> BoardList(int start,int end){
//		Connection con = null;
//		PreparedStatement stmt = null;
//		ResultSet rs = null;
//		String sql = null;
//		Vector<BoardBean> vlist = null;
//		try {
//			con = getConnection();
//			sql = " select a.* " + " from ( " + " select ROWNUM as RNUM, b.* " + " from ( " + " select * " + " from board " + " order by ref desc,pos asc " + " ) b " + " ) a " + " where a.RNUM >= ? " + " and a.RNUM <= ? " ;
//			stmt = con.prepareStatement(sql);
//			stmt.setInt(1, start);
//			stmt.setInt(2, end);
//			rs = stmt.executeQuery();
//			while(rs.next()) {
//				BoardBean bean = new BoardBean();
//				bean.setNum(rs.getInt("num"));
//				bean.setWriter(rs.getString("writer"));
//				bean.setEmail(rs.getString("email"));
//				bean.setSubject(rs.getString("subject"));
//				bean.setPasswd(rs.getString("passwd"));
//				bean.setReg_date(rs.getTimestamp("reg_date"));
//				bean.setReadcount(rs.getInt("readcount"));
//				bean.setRef(rs.getInt("ref"));
//				bean.setPos(rs.getInt("pos"));
//				bean.setDepth(rs.getInt("depth"));
//				bean.setContent(rs.getString("content"));
//				bean.setIp(rs.getString("ip"));
//				vlist.add(bean);
//			}
//		}catch(Exception e) {
//			e.printStackTrace();
//		}finally {
//			if(rs!=null)try {rs.close();}catch(Exception e) {}
//			if(stmt!=null)try {stmt.close();}catch(Exception e) {}
//			if(con!=null)try {con.close();}catch(Exception e) {}
//		}
//		return vlist;
//	}
	
	public List BoardList2(int start,int end){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		List vlist2 = new ArrayList(end);
		try {
			con = getConnection();
			sql = " select a.* " + " from ( " + " select ROWNUM as RNUM, b.* " + " from ( " + " select * " + " from board6 " + " order by ref desc,pos asc " + " ) b " + " ) a " + " where a.RNUM >= ? " + " and a.RNUM <= ? " ;
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, start);
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
				vlist2.add(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(stmt!=null)try {stmt.close();}catch(Exception e) {}
			if(con!=null)try {con.close();}catch(Exception e) {}
		}
		return vlist2;
	}
	
	public BoardBean BoardRead(int num) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String sql = null;
		BoardBean bean = new BoardBean();
		try {
			con = getConnection();
			
			sql = "select * from board6 where num = ?";
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
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(stmt!=null)try {stmt.close();}catch(Exception e) {}
			if(con!=null)try {con.close();}catch(Exception e) {}
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
			
			sql = "update board6 set subject = ?, content = ?, email = ? where num = ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bean.getSubject());
			stmt.setString(2, bean.getContent());
			stmt.setString(3, bean.getEmail());
			stmt.setInt(4, bean.getNum());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			if(rs!=null)try {rs.close();}catch(Exception e) {}
			if(stmt!=null)try {stmt.close();}catch(Exception e) {}
			if(con!=null)try {con.close();}catch(Exception e) {}
		}
	}
	
}
