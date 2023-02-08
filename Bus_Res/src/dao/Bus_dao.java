package dao;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Bus_dao {
	private Connection con = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;
	

	//StartDes_출발지 List
	//group by 써서 하나의 출발지만 뽑아 냈음
	public ArrayList<Bus_dto> Bus_startList() {			
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		String sql = "select b_start , count(*) as cnt "
				+ "from tbl_bus "
				+ "group by b_start "
				+ "having count(*)>=1 ";
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
		
			while(rs.next()) {
				String bStart = rs.getString(1);
				Bus_dto dto = new Bus_dto(bStart);
				dtos.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("con 오류 : "+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	
	//StartDes_도착지 List	
	public ArrayList<Bus_dto> Bus_desList() {			
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		String sql = "select b_des , count(*) as cnt "
				+ "from tbl_bus "
				+ "group by b_des "
				+ "having count(*)>=1 ";
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(sql);
			rs = stmt.executeQuery();
		
			while(rs.next()) {
				String bDes = rs.getString(1);
				
				Bus_dto dto = new Bus_dto(bDes);
				dtos.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("con 오류 : "+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}

	
	//busSelect_버스 List
	//버스 번호, 회사, 출발지, 출발시간, 도착시간, 도착지, 우등/일반, 잔여석
	public ArrayList<Bus_dto> Bus_Select(String st, String ds) {			
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		String sql = "select b.b_no, b.b_company, b.b_start, b.b_start_Time, b.b_des_Time, b.b_des, b.b_grade, b.s_count "
				+ "from tbl_bus b "
				+ "where b.b_start = ? AND b.b_des = ? ";
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, st);
			stmt.setString(2, ds);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String bNo = rs.getString(1);
				String bCompany = rs.getString(2);
				String bStart = rs.getString(3);
				String bStartTime = rs.getString(4);
				String bDesTime = rs.getString(5);
				String bDes = rs.getString(6);
				String bGrade = rs.getString(7);
				int sCount = rs.getInt(8);
				
				Bus_dto dto = new Bus_dto(bNo, bCompany, bStart, bStartTime ,bDesTime , bDes, bGrade, sCount);
				dtos.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("con 오류 : "+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	//payment_고객 수 입력
	//티켓번호, 어른, 청소년, 아동 
	public int Cust_Insert(Bus_dto dto) {
		String sql = "insert into tbl_cust values(?, ?,  ?, ?) ";
		int result = 0;
		try{
				con = DBConnection.getConnection();
				stmt = con.prepareStatement(sql);
				stmt.setString(1, dto.getticketNo());
				stmt.setInt(2, dto.getAdult());
				stmt.setInt(3, dto.getStudent());
				stmt.setInt(4, dto.getChild());
				result = stmt.executeUpdate();
		}catch(SQLException e) {
			System.out.println("con 오류 : "+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//seatSelect_좌석 등급 보여줄라구 bus검색함
	//버스 번호, 출발지, 도착지, 출발 시간, 도착 시간, 우등/일반, 잔여석, 좌석, 어른 좌석 가격, 청소년 좌석 가격, 아동 좌석 가격
	public ArrayList<Bus_dto> Bus_Select(String bN, String st, String ds, String stt) {			
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		String sql = "select b.b_no, b.b_start, b.b_des, b.b_start_time, b.b_des_time, b.b_grade, b.s_count, "
				+ " s.seat, s.s_aprice, s.s_sprice, s.s_cprice "
				+ "from tbl_bus b, tbl_seat s "
				+ "where b.b_no = s.b_no AND b.b_start_time = s.b_start_time AND "
				+ "b.b_no = ? AND b.b_start = ? AND b.b_des = ? AND b.b_start_time = ? ";
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bN);
			stmt.setString(2, st);
			stmt.setString(3, ds);
			stmt.setString(4, stt);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String bNo = rs.getString(1);
				String bStart = rs.getString(2);
				String bDes = rs.getString(3);
				String bstartTime = rs.getString(4);
				String bdesTime = rs.getString(5);
				String bGrade = rs.getString(6);
				int sCount = rs.getInt(7);
				String seat = rs.getString(8);
				int aPrice = rs.getInt(9);
				int sPrice = rs.getInt(10);
				int cPrice = rs.getInt(11);
				
				Bus_dto dto = new Bus_dto(bNo, bStart, bDes, bstartTime, bdesTime, bGrade, sCount, seat, aPrice, sPrice, cPrice);
				dtos.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("con 오류 : "+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	//seatSelect_좌석 보여줌
	//버스 번호, 출발지, 도착지, 출발시간, 좌석
	public ArrayList<Bus_dto> Bus_seatShow(String bN, String st, String ds, String stt){
		ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
		String sql = "select b_no, b_start, b_des, b_start_time, seat "
				+ "from tbl_seat "
				+ "where b_no = ? AND b_start = ? AND b_des = ? AND b_start_time = ? ";
		try {
			con = DBConnection.getConnection();
			stmt = con.prepareStatement(sql);
			stmt.setString(1, bN);
			stmt.setString(2, st);
			stmt.setString(3, ds);
			stmt.setString(4, stt);
			rs = stmt.executeQuery();
			
			while(rs.next()) {
				String bNo = rs.getString(1);
				String bStart = rs.getString(2);
				String bDes = rs.getString(3);
				String bstartTime = rs.getString(4);
				String Seat = rs.getString(5);
				
				
				Bus_dto dto = new Bus_dto(bNo,bStart,Seat);
				dtos.add(dto);
			}
		}catch(SQLException e) {
			System.out.println("con 오류 : "+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return dtos;
	}
	
	//좌석 수정
	public int Seat_Update(Bus_dto dto) {

		String sql = "UPDATE tbl_Seat set seat = ? "
				+ "Where b_No = ? AND b_start_time = ? ";
		int result = 0;
		try{
				con = DBConnection.getConnection();
				stmt = con.prepareStatement(sql);
				stmt.setString(1, dto.getSeat());
				stmt.setString(2, dto.getbNo());
				stmt.setString(3, dto.getbStartTime());
				result = stmt.executeUpdate();
				
		}catch(SQLException e) {
			System.out.println("con 오류 : "+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	//버스에 잔여석 sCount 업데이트
	public int sCount_Update(Bus_dto dto) {

		String sql = "UPDATE tbl_Bus set s_Count = ? "
				+ "Where b_No = ? AND b_start_time = ? ";
		int result = 0;
		try{
				con = DBConnection.getConnection();
				stmt = con.prepareStatement(sql);
				stmt.setInt(1, dto.getsCount());
				stmt.setString(2, dto.getbNo());
				stmt.setString(3, dto.getbStartTime());
				result = stmt.executeUpdate();
				
		}catch(SQLException e) {
			System.out.println("con 오류 : "+e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				if(rs != null) rs.close();
				if(stmt != null) stmt.close();
				
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	//1. 예매할 때, 티켓 조회
	//출발지, 도착지, 출발 시간, 도착 시간, 금액들
		public ArrayList<Bus_dto> ticket_Select1(String tNo){
			ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
			
			String sql = "SELECT b.b_no, b.b_start, b.b_des, b.b_start_time, b.b_des_time,  "
					+ "s.seat, s.s_aprice, s.s_sprice, s.s_cprice "
					+ "FROM tbl_bus b, tbl_seat s "
					+ "WHERE substr(?, 1, 4) = b.b_no AND b.b_no = s.b_no ";
				
			try {
				
				con = DBConnection.getConnection();
				stmt = con.prepareStatement(sql);
				stmt.setString(1, tNo);
				
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					String bNo = rs.getString(1);
					String bStart = rs.getString(2);
					String bDes = rs.getString(3);
					String bstartTime = rs.getString(4);
					String bdesTime = rs.getString(5);
					String seat = rs.getString(6);
					int aPrice = rs.getInt(7);
					int sPrice = rs.getInt(8);
					int cPrice = rs.getInt(9);
					
					Bus_dto dto = new Bus_dto(bNo, bStart, bDes, bstartTime, bdesTime,seat, aPrice, sPrice, cPrice);
					dtos.add(dto);
				}
			}catch(SQLException e) {
				System.out.println("con 오류 : "+e.getMessage());
				e.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return dtos;
		}
		
		//2. 예매 조회 & 3. 예매 변경
		public ArrayList<Bus_dto> ticket_Select2(String tNo){
			ArrayList<Bus_dto> dtos = new ArrayList<Bus_dto>();
			
			String sql = "SELECT b.b_no, b.b_start, b.b_des, b.b_start_time, b.b_des_time, b.b_grade, b.s_count, "
					+ "s.seat, s.s_aprice, s.s_sprice, s.s_cprice, "
					+ "c.adult, c.student, c.child "
					+ "FROM tbl_bus b, tbl_seat s, tbl_cust c "
					+ "WHERE c.ticket_no = ? AND substr(c.ticket_no, 1, 6) = b.b_no||substr(b.b_start_time, 1,2) AND b.b_no = s.b_no ";
				
			try {
				
				con = DBConnection.getConnection();
				stmt = con.prepareStatement(sql);
				stmt.setString(1, tNo);
				rs = stmt.executeQuery();
				
				while(rs.next()) {
					String bNo = rs.getString(1);
					String bStart = rs.getString(2);
					String bDes = rs.getString(3);
					String bstartTime = rs.getString(4);
					String bdesTime = rs.getString(5);
					String bGrade = rs.getString(6);
					int sCount = rs.getInt(7);
					String seat = rs.getString(8);
					int aPrice = rs.getInt(9);
					int sPrice = rs.getInt(10);
					int cPrice = rs.getInt(11);
					int adult = rs.getInt(12);
					int student = rs.getInt(13);
					int child = rs.getInt(14);
					
					Bus_dto dto = new Bus_dto(bNo, bStart, bDes, bstartTime, bdesTime, bGrade,sCount, seat, aPrice, sPrice, cPrice, adult, student, child);
					dtos.add(dto);
				}
			}catch(SQLException e) {
				System.out.println("con 오류 : "+e.getMessage());
				e.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return dtos;
		}
		
		//3. 예매 변경
		public int ticket_Update(Bus_dto dto) {
			String sql = "update tbl_seat " 
					+ "set seat = ? "
					+ "where b_no = ? AND b_start_time = ? ";
			
			int result = 0;
			try{
					con = DBConnection.getConnection();
					stmt = con.prepareStatement(sql);
					stmt.setString(1, dto.getSeat());
					stmt.setString(2, dto.getbNo());
					stmt.setString(3, dto.getbStartTime());
					result = stmt.executeUpdate();
					
			}catch(SQLException e) {
				System.out.println("con 오류 : "+e.getMessage());
				e.printStackTrace();
			}finally {
				try {
					if(rs != null) rs.close();
					if(stmt != null) stmt.close();
					
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
		
		//4. 예매 취소
		public int ticket_Delete(String tNo) {
			String sql = "delete from tbl_cust where ticket_no = ?";
			int result = 0;
			try {
				con = DBConnection.getConnection();
				stmt = con.prepareStatement(sql);
				stmt.setString(1, tNo);
				result = stmt.executeUpdate();
				
			}catch(SQLException e) {
				System.out.println("con 오류 : " + e.getMessage());
				e.printStackTrace();
			} finally {
				try {
					if(stmt != null) stmt.close();
				} catch(Exception e) {
					e.printStackTrace();
				}
			}
			return result;
		}
}

