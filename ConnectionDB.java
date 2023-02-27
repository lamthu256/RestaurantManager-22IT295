package Restaurants;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import Restaurants.*;
import javax.swing.DefaultBoundedRangeModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ConnectionDB {
	
	public static Connection conn;
	public static ResultSet rs;
	
	public static Connection ConnectionDB()
	{
	    try {
				Class.forName("com.mysql.cj.jdbc.Driver");//nạp driver
				conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/RESTAURANTS", "root", "lamthu2506");
	        } catch (SQLException e) {
                e.printStackTrace();
	        } catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		return conn;
	}
	
    //Login
	public User checkLogin(String tenDangNhap, String matKhau) throws SQLException 
	{
		String sql = "SELECT TenDangNhap, MatKhau, VaiTro FROM NGUOIDUNG WHERE TenDangNhap = ? AND MatKhau = ?";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);//thực hiện truy vấn 
		   ){
			ps.setString(1, tenDangNhap);//đặt giá trị cho chỉ số tham số TenDangNhap = ?
			ps.setString(2, matKhau);
			
			try(ResultSet rs = ps.executeQuery();){//xử lí kết quả
				while(rs.next()) {
					User u = new User();
					u.setTenDangNhap(tenDangNhap);
					u.setVaiTro(rs.getString("VaiTro"));
					return u;
				}
			}
		} 
		return null;
	}
	
	//Main
	public List<Bill> findID() throws SQLException{
		String sql = "SELECT * FROM HOADON";
		try(
		    Connection conn = ConnectionDB();
		    PreparedStatement ps = conn.prepareStatement(sql);)
		{
            try(ResultSet rs = ps.executeQuery();)
            {
            	List<Bill> listBill = new ArrayList();
		        while(rs.next()){
		    	    Bill b = new Bill();
		    	    b.setMaHD(rs.getInt("MaHD"));//dữ liệu đọc được đưa về 
	                b.setMaMA(rs.getInt("MaMA"));
	                b.setTenMA(rs.getString("TenMA"));
	                b.setSoluong(rs.getInt("SOLUONG"));
	                b.setDongia(rs.getFloat("DONGIA"));
	                
	                listBill.add(b);
		        }
		        return listBill;
            }
		}
	}
	
	public boolean insertBill(Bill b) throws Exception{
		String sql = "INSERT INTO HOADON(MaHD, MaMA, TenMA, SOLUONG, DONGIA) VALUES (?,?,?,?,?)";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(1, b.getMaHD());
		    	ps.setInt(2, b.getMaMA());
		    	ps.setString(3, b.getTenMA());
		    	ps.setInt(4, b.getSoluong());
		    	ps.setFloat(5, b.getDongia());
		    	
		    	return ps.executeUpdate()>0;
		}
	}
	
	//Staff
	public List<Staff> findStaff() throws SQLException{
		String sql = "SELECT * FROM NHANVIEN";
		try(
		    Connection conn = ConnectionDB();
		    PreparedStatement ps = conn.prepareStatement(sql);)
		{
            try(ResultSet rs = ps.executeQuery();)
            {
            	List<Staff> liststaff = new ArrayList();
		        while(rs.next()){
		    	    Staff s = new Staff();
		    	    s.setMaNV(rs.getInt("MaNV"));//dữ liệu đọc được đưa về 
	                s.setHOTEN(rs.getString("HOTEN"));
	                s.setCHUCVU(rs.getString("CHUCVU"));
	                liststaff.add(s);
		        }
		        return liststaff;
            }
		}
	}
	
 	public boolean insertStaff(Staff s) throws Exception{
		String sql = "INSERT INTO NHANVIEN(MaNV, HOTEN, CHUCVU) VALUES (?,?,?)";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(1, s.getMaNV());
		    	ps.setString(2, s.getHOTEN());
		    	ps.setString(3, s.getCHUCVU());
		    	return ps.executeUpdate()>0;
		}
	}
	
	public boolean updateStaff(Staff s) throws Exception{
		String sql = "UPDATE NHANVIEN SET HOTEN = ?, CHUCVU = ? WHERE MaNV = ?";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(3, s.getMaNV());
		    	ps.setString(1, s.getHOTEN());
		    	ps.setString(2, s.getCHUCVU());
		    	return ps.executeUpdate()>0;
		}
		
	}
	
	public boolean deleteStaff(int MaNV) throws Exception{
		String sql = "DELETE FROM NHANVIEN WHERE MaNV = ?";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(1, MaNV);
		    	return ps.executeUpdate()>0;
		}
	}
	
	public Staff findbyIdStaff(int MaNV) throws Exception{
		String sql = "SELECT * FROM NHANVIEN WHERE MaNV = ?";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(1, MaNV);
		    	try(ResultSet rs = ps.executeQuery();){
		    		while(rs.next()) {
		    			Staff s = new Staff();
		    			s.setMaNV(rs.getInt("MaNV"));
		    			s.setHOTEN(rs.getString("HOTEN"));
		    			s.setCHUCVU(rs.getString("CHUCVU"));
		    			return s;
		    		}
		    	}
		    	return null;
		    }
	}
	
	//Food
	public List<Food> findFood() throws SQLException{
		String sql = "SELECT * FROM MONAN";
		try(
		    Connection conn = ConnectionDB();
		    PreparedStatement ps = conn.prepareStatement(sql);)
		{
            try(ResultSet rs = ps.executeQuery();)
            {
            	List<Food> list = new ArrayList();
		        while(rs.next()){
		    	    Food f = new Food();
		    	    f.setMaMA(rs.getInt("MaMA"));//dữ liệu đọc được đưa về 
	                f.setTenMA(rs.getString("TenMA"));
	                f.setDONGIA(rs.getFloat("DONGIA"));
	                list.add(f);
		        }
		        return list;
            }
		 }
	  }
	
	public boolean insertFood(Food f) throws Exception{
		String sql = "INSERT INTO MONAN(MaMA, TenMA, DONGIA) VALUES (?,?,?)";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(1, f.getMaMA());
		    	ps.setString(2, f.getTenMA() + "");
		    	ps.setFloat(3, f.getDONGIA());
		    	return ps.executeUpdate()>0;
		}	
	}
	
	public boolean updateFood(Food f) throws Exception{
		String sql = "UPDATE MONAN SET TenMA = ?, DONGIA = ? WHERE MaMA = ?";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(3, f.getMaMA());
		    	ps.setString(1, f.getTenMA() + "");
		    	ps.setFloat(2, f.getDONGIA());
		    	return ps.executeUpdate()>0;
		}
	}
	
	public boolean deleteFood(int MaMA) throws Exception{
		String sql = "DELETE FROM MONAN WHERE MaMA = ?";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(1, MaMA);
		    	return ps.executeUpdate()>0;
		}
	}
	
	public Food findbyIdFood(int MaMA) throws Exception{
		String sql = "SELECT * FROM MONAN WHERE MaMA = ?";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(1, MaMA);
		    	try(ResultSet rs = ps.executeQuery();){
		    		if(rs.next()) {
		    			Food f = new Food();
		    			f.setMaMA(MaMA);
		    			f.setTenMA(rs.getString("TenMA"));
		    			f.setDONGIA(rs.getFloat("DONGIA"));
		    			return f;
		    		}
		    	}
		    	return null;
		     }
	}
	
    //Table
	public List<Table> findTable() throws SQLException{
		String sql = "SELECT * FROM BANAN";
		try(
		    Connection conn = ConnectionDB();
		    PreparedStatement ps = conn.prepareStatement(sql);)
		{
            try(ResultSet rs = ps.executeQuery();)
            {
            	List<Table> listtable = new ArrayList();
		        while(rs.next()){
		    	    Table t = new Table();
		    	    t.setMaBA(rs.getInt("MaBA"));//dữ liệu đọc được đưa về 
	                t.setTenBA(rs.getString("TenBA"));
	                t.setTRANGTHAI(rs.getString("TRANGTHAI"));
	                t.setSOCHONGOI(rs.getInt("SOCHONGOI"));
	                listtable.add(t);
		        }
		        return listtable;
            }
		}
	}
	
	public boolean insertTable(Table t) throws Exception{
		String sql = "INSERT INTO BANAN(MaBA, TenBA, TRANGTHAI, SOCHONGOI) VALUES (?,?,?,?)";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(1, t.getMaBA());
		    	ps.setString(2, t.getTenBA() + "");
		    	ps.setString(3, t.getTRANGTHAI() + "");
		    	ps.setInt(4, t.getSOCHONGOI());
		    	return ps.executeUpdate()>0;
		}	
	}
	
	public boolean updateTable(Table t) throws Exception{
		String sql = "UPDATE BANAN SET TenBA = ?, TRANGTHAI = ?, SOCHONGOI = ? WHERE MaBA = ?";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(4, t.getMaBA());
		    	ps.setString(1, t.getTenBA());
		    	ps.setString(2, t.getTRANGTHAI());
		    	ps.setInt(3, t.getSOCHONGOI());
		    	return ps.executeUpdate()>0;
		}
	}
	
	public boolean deleteTable(int MaBA) throws Exception{
		String sql = "DELETE FROM BANAN WHERE MaBA = ?";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(1, MaBA);
		    	
		    	return ps.executeUpdate()>0;
		}
	}
	
	public Table findbyIdTable(int MaBA) throws Exception{
		String sql = "SELECT * FROM BANAN WHERE MaBA = ?";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(1, MaBA);
		    	try(ResultSet rs = ps.executeQuery();){
		    		if(rs.next()) {
		    			Table t = new Table();
		    			t.setMaBA(MaBA);
		    			t.setTenBA(rs.getString("TenBA"));
		    			t.setTRANGTHAI(rs.getString("TRANGTHAI"));
		    			t.setSOCHONGOI(rs.getInt("SOCHONGOI"));
		    			return t;
		    		}
		    	}
		    	return null;
		    }
	}
	
	//Revenue
    public List<Revenue> findRevenue() throws SQLException{
		String sql = "SELECT * FROM DOANHTHU";
		try(
			Connection conn = ConnectionDB();
			PreparedStatement ps = conn.prepareStatement(sql);
		){
			try(ResultSet rs = ps.executeQuery();){
				List<Revenue> listRevenue = new ArrayList(); 
				while(rs.next()) 
				{
					Revenue r = new Revenue();
					r.setMaHD(rs.getInt("MaHD"));//dữ liệu đọc được đưa về
					r.setTenKH(rs.getString("TenKH"));
					r.setMaBA(rs.getInt("MaBA"));
					r.setMaNV(rs.getInt("MaNV"));
					r.setNgay(rs.getString("Ngay"));
					r.setTongtien(rs.getFloat("TONGTIEN"));
					listRevenue.add(r);
				}
				return listRevenue;
			}
		}	
	}
	
	public boolean insertRevenue(Revenue r) throws Exception{
		String sql = "INSERT INTO DOANHTHU(MaHD, TenKH, MaBA, MaNV, Ngay, TONGTIEN) VALUES (?,?,?,?,?,?)";
		try(
			    Connection conn = ConnectionDB();
			    PreparedStatement ps = conn.prepareStatement(sql);
		    ){
		    	ps.setInt(1, r.getMaHD());
		    	ps.setString(2, r.getTenKH());
		    	ps.setInt(3, r.getMaBA());
		    	ps.setInt(4, r.getMaNV());
		    	ps.setString(5, r.getNgay());
		    	ps.setFloat(6, r.getTongtien());
		    	
		    	return ps.executeUpdate()>0;
		}
	}	   
    
	//ComboBox
	public static ResultSet loadCBStaff(){
		String sql = "SELECT MaNV FROM NHANVIEN WHERE CHUCVU = 'Phục vụ'";
		try {
			Connection conn = ConnectionDB();
			PreparedStatement ps = conn.prepareStatement(sql);
		    rs = ps.executeQuery();
        } catch (SQLException e) {	
            e.printStackTrace();
        }
	   return rs;
	}
	
	public static ResultSet loadCBTable(){
		String sql = "SELECT MaBA FROM BANAN WHERE TRANGTHAI = 'Có Người'";
		try {
			Connection conn = ConnectionDB();
			PreparedStatement ps = conn.prepareStatement(sql);
		    rs = ps.executeQuery();
        } catch (SQLException e) {	
            e.printStackTrace();
        }
	   return rs;
	}
	
    //FreeChart
	public List<Chart> freeChart() throws SQLException{
		String sql = "SELECT SUM(TONGTIEN) AS Tongtien, Ngay FROM DOANHTHU GROUP BY Ngay";
		try(
		    Connection conn = ConnectionDB();
		    PreparedStatement ps = conn.prepareStatement(sql);)
		{
            try(ResultSet rs = ps.executeQuery();)
            {
            	List<Chart> listChart = new ArrayList();
		        while(rs.next()){
		    	    Chart ch = new Chart();
		    	    ch.setTongtien(rs.getFloat("Tongtien"));//dữ liệu đọc được đưa về 
	                ch.setNgay(rs.getString("Ngay"));
	                
	                listChart.add(ch);
		        }
		        return listChart;
            }
		}
	}
}


