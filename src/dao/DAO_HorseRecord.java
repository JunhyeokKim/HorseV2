package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import vo.HorseRecord;

public class DAO_HorseRecord {
	private Connection con;
	private Statement stmt;
	private PreparedStatement pstmt;
	private ResultSet rs;

	public ArrayList<HorseRecord> popNRow(int n) {
		ArrayList<HorseRecord> voList = new ArrayList<HorseRecord>();
		String sql = "SELECT * FROM ( \n"
				+ "SELECT ROWNUM NO, A.* FROM ( \n"
				+ "SELECT * FROM HORSE_RECORD A ) A) WHERE NO BETWEEN ? AND ? ";
		
		HorseRecord vo = null;
		try {
			con = AA_Con.conn();
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, 20*n);
			pstmt.setInt(2, 20*(n+1));
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new HorseRecord();
				vo.setHname(rs.getString("hname"));
				vo.setHnum((rs.getInt("hnum")));
				vo.setTotrace(rs.getInt("totrace"));
				vo.setFirst((rs.getInt("first")));
				vo.setSecond((rs.getInt("second")));
				vo.setThird((rs.getInt("third")));
				vo.setTotprize(rs.getInt("totprize"));
				voList.add(vo);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		return voList;
	}

	public ArrayList<HorseRecord> searchHor(HorseRecord sch) {
		ArrayList<HorseRecord> hlist = new ArrayList<HorseRecord>();
		HorseRecord vo = null;
		String sql = "SELECT * FROM HORSE_RECORD\n" + "WHERE HNAME LIKE '%'||?||'%' ";
		try {
			con = AA_Con.conn();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, sch.getHname());
			rs = pstmt.executeQuery();
			while (rs.next()) {
				vo = new HorseRecord();
				vo.setHname(rs.getString("hname"));
				vo.setHnum((rs.getInt("hnum")));
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
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (con != null)
					con.close();
			} catch (SQLException e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
		System.out.println(sql);
		return hlist;
	}

	public ArrayList<HorseRecord> fiveHorse(){
		ArrayList<HorseRecord> flist = new ArrayList<HorseRecord>();
		String sql = "SELECT * FROM (SELECT * FROM HORSE_RECORD ORDER BY DBMS_RANDOM.RANDOM) WHERE ROWNUM <= 5";
		
		try {
			con = AA_Con.conn();
			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);
			
			HorseRecord hr = null;
			while(rs.next()){
				hr = new HorseRecord();
				hr.setHname(rs.getString("hname"));
				hr.setHnum(rs.getInt("hnum"));
				hr.setTotrace(rs.getInt("totrace"));
				hr.setFirst(rs.getInt("first"));
				hr.setSecond(rs.getInt("second"));
				hr.setThird(rs.getInt("third"));
				hr.setTotprize(rs.getInt("totprize"));
				flist.add(hr);
			}
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return flist;
	}

	public static void main(String[] args) {
		DAO_HorseRecord dao = new DAO_HorseRecord();
		HorseRecord sch = new HorseRecord();
		sch.setHname("");
		for (HorseRecord vo : dao.searchHor(sch)) {
			System.out.println(vo.getHname());
			System.out.println(vo.getHnum());
		}
	}
}
