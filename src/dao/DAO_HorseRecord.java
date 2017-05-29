package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import vo.HorseRecord;

public class DAO_HorseRecord {
	private Connection con;
	private PreparedStatement pstmt;
	private ResultSet rs;
	
	public ArrayList<HorseRecord> searchHor(HorseRecord sch){
		ArrayList<HorseRecord> hlist = new ArrayList<HorseRecord>();
		HorseRecord vo= null;
		String sql = "SELECT * FROM HORSE_RECORD\n"
				+ "WHERE HNAME LIKE '%'||?||'%' ";
		try {
			con = AA_Con.conn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sch.gethname());
			rs = pstmt.executeQuery();
			while(rs.next()){
				vo = new HorseRecord();
				vo.sethname(rs.getString("hname"));
				vo.sethnum((rs.getInt("hnum")));
				vo.setTotrace(rs.getInt("totrace"));
				vo.setFirst((rs.getInt("first")));
				vo.setSecond((rs.getInt("second")));
				vo.setThird((rs.getInt("third")));
				vo.setTotprize(rs.getInt("totprize"));
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
	public static void main(String[] args) {
		DAO_HorseRecord dao= new DAO_HorseRecord();
		HorseRecord sch= new HorseRecord();
		sch.sethname("");
		for(HorseRecord vo:dao.searchHor(sch)){
			System.out.println(vo.gethname());
			System.out.println(vo.gethnum());
		}
	}
}
