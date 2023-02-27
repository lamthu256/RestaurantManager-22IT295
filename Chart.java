package Restaurants;

public class Chart {
	private Float Tongtien;
	private String Ngay;
	
	public Chart(Float tongtien, String ngay) {
		Tongtien = tongtien;
		Ngay = ngay;
	}
	
	public Chart() {
		
	}

	public Float getTongtien() {
		return Tongtien;
	}

	public void setTongtien(Float tongtien) {
		Tongtien = tongtien;
	}

	public String getNgay() {
		return Ngay;
	}

	public void setNgay(String ngay) {
		Ngay = ngay;
	}
}
