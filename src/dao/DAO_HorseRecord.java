package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vo.HorseInfo;
import vo.HorseRecord;

public class DAO_HorseRecord {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ArrayList<HorseRecord> searchHor(HorseRecord sch){
		ArrayList<HorseInfo> hlist = new ArrayList<HorseInfo>();
		HorseRecord vo= null;
		String sql = "SELECT * FROM HORSE_INFO\n"
				+ "WHERE HNAME LIKE '%'||?||'%' ";
		try {
			con = AA_Con.conn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.gethname());
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new HorseRecord();
				vo.sethname(rs.getString("hname"));
				hlist.add(vo);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			try{
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println(sql);
		return hlist;
	}
}
