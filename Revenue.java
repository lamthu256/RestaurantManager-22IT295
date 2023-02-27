package Restaurants;

import java.util.Date;

public class Revenue {
	private int MaHD;
	private String TenKH;
	private int MaBA;
	private int MaNV;
	private String Ngay;
	private Float Tongtien;
	
	public Revenue(int maHD, String tenKH, int maBA, int maNV, String ngay, Float tongtien) {
		MaHD = maHD;
		TenKH = tenKH;
		MaBA = maBA;
		MaNV = maNV;
		Ngay = ngay;
		Tongtien = tongtien;
	}
		public Revenue() {
	
	}

	public int getMaHD() {
			return MaHD;
		}
	
    public void setMaHD(int maHD) {
			MaHD = maHD;
		}
    
	public String getTenKH() {
		return TenKH;
	}

	public void setTenKH(String tenKH) {
		TenKH = tenKH;
	}

	public int getMaBA() {
		return MaBA;
	}

	public void setMaBA(int maBA) {
		MaBA = maBA;
	}

	public int getMaNV() {
		return MaNV;
	}

	public void setMaNV(int maNV) {
		MaNV = maNV;
	}

	public String getNgay() {
		return Ngay;
	}

	public void setNgay(String ngay) {
		Ngay = ngay;
	}
	
	public Float getTongtien() {
		return Tongtien;
	}
	
	public void setTongtien(Float tongtien) {
		Tongtien = tongtien;
	}
}
