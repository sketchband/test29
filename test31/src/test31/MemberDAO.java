package test31;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Vector;

public class MemberDAO {

	private DBConnectionMgr pool;
	
	public MemberDAO() {
		try {
			pool = DBConnectionMgr.getInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public Vector<zipcodeBean> zipcodeRead(String area3){
		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Vector<zipcodeBean> vlist = new Vector<zipcodeBean>();
		try {
			con = pool.getConnection();
			String sql = "select * from tblZipcode2 where like ?";
			stmt = con.prepareStatement(sql);
			stmt.setString(1, "%"+area3+"%");
			rs = stmt.executeQuery();
			while(rs.next()) {
				zipcodeBean bean = new zipcodeBean();
				bean.setArea1(rs.getString("area1"));
				bean.setArea2(rs.getString("area2"));
				bean.setArea3(rs.getString("area3"));
				bean.setArea4(rs.getString("area4"));
				bean.setZipcode(rs.getString("zipcode"));
				vlist.addElement(bean);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			pool.freeConnection(con,stmt,rs);
		}
		return vlist;
	}
	
}
