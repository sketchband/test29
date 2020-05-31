package test30;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class BoardDAO {

	private DBConnectionMgr pool;
	
	public BoardDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void postProc(BoardBean bean) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int ref = 0;
		int pos = 1;
		int depth = 1;
		
		try {
			con = pool.getConnection();
			String refsql = "select max(ref) from Board14";
			stmt = con.prepareStatement(refsql);
			rs = stmt.executeQuery();
			if(rs.next())
				ref = rs.getInt(1)+1;
			
			String sql = "insert into Board14 values(?,?,?,?,0,?,?,?,?,?,now())";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, bean.getNum());
			stmt.setInt(2, ref);
			stmt.setInt(3, pos);
			stmt.setInt(4, depth);
			stmt.setString(5, bean.getSubject());
			stmt.setString(6, bean.getName());
			stmt.setString(7, bean.getPw());
			stmt.setString(8, bean.getEmail());
			stmt.setString(9, bean.getContent());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,stmt,rs);
		}
	}
	
	public Vector<BoardBean> BoardList(int start,int end){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Vector<BoardBean> vlist = new Vector<BoardBean>();
		try {
			con = pool.getConnection();
				con = pool.getConnection();
				String sql = "select * from Board14 order by ref desc,pos asc,pos limit ?,?";
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, start-1);
				stmt.setInt(2, end);
				rs = stmt.executeQuery();
				while(rs.next()) {
					BoardBean bean = new BoardBean();
					bean.setNum(rs.getInt("num"));
					bean.setRef(rs.getInt("ref"));
					bean.setPos(rs.getInt("pos"));
					bean.setDepth(rs.getInt("depth"));
					bean.setCount(rs.getInt("count"));
					bean.setSubject(rs.getString("subject"));
					bean.setName(rs.getString("name"));
					bean.setPw(rs.getString("pw"));
					bean.setEmail(rs.getString("email"));
					bean.setContent(rs.getString("content"));
					bean.setRegdate(rs.getString("regdate"));
					vlist.add(bean);
				}
			}catch(Exception e) {
				e.printStackTrace();
			}finally {
				pool.freeConnection(con,stmt,rs);
			}
		return vlist;
	}
	public int Count() {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int count = 0;
		
		try {
			con = pool.getConnection();
			String sql = "select count(*) from Board14";
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
			if(rs.next())
				count = rs.getInt(1);
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,stmt,rs);
		}
		return count;
	}
	
	public BoardBean BoardRead(int num) {
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		BoardBean bean = new BoardBean();
		try {
			con = pool.getConnection();
			
			String countsql = "update Board14 set count = count+1 where num = ?";
			stmt = con.prepareStatement(countsql);
			stmt.setInt(1, num);
			stmt.executeUpdate();
			
			String sql = "select * from Board14 where num = ?";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, num);
			rs = stmt.executeQuery();
			if(rs.next()) {
				bean.setNum(rs.getInt("num"));
				bean.setRef(rs.getInt("ref"));
				bean.setPos(rs.getInt("pos"));
				bean.setDepth(rs.getInt("depth"));
				bean.setCount(rs.getInt("count"));
				bean.setSubject(rs.getString("subject"));
				bean.setName(rs.getString("name"));
				bean.setPw(rs.getString("pw"));
				bean.setEmail(rs.getString("email"));
				bean.setContent(rs.getString("content"));
				bean.setRegdate(rs.getString("regdate"));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,stmt,rs);
		}
		return bean;
	}
	
	public void BoardReply(BoardBean bean) {
		Connection con = null;
		PreparedStatement stmt = null;
		int ref = bean.getRef();
		int pos = bean.getPos();
		int depth = bean.getDepth();
		try {
			con = pool.getConnection();
			String sql = "update Board14 set pos = pos + 1 where ref = ? and pos > ?";
			stmt =con.prepareStatement(sql);
			stmt.setInt(1, ref);
			stmt.setInt(2, pos);
			stmt.executeUpdate();
			
			sql = "insert into Board14 values(?,?,?,?,0,?,?,?,?,?,now())";
			stmt = con.prepareStatement(sql);
			stmt.setInt(1, bean.getNum());
			stmt.setInt(2, ref);
			stmt.setInt(3, pos+1);
			stmt.setInt(4, depth+1);
			stmt.setString(5, bean.getSubject());
			stmt.setString(6, bean.getName());
			stmt.setString(7, bean.getPw());
			stmt.setString(8, bean.getEmail());
			stmt.setString(9, bean.getContent());
			stmt.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,stmt);
		}
	}
}
