package Restaurants;

public class Table {
	private int MaBA;
	private String TenBA;
	private String TRANGTHAI;
	private int SOCHONGOI;
	
	public Table(int maBA, String tenBA, String tRANGTHAI, int sOCHONGOI) {
		MaBA = maBA;
		TenBA = tenBA;
		TRANGTHAI = tRANGTHAI;
		SOCHONGOI = sOCHONGOI;
	}
	
	public Table() {
		
	}
	
	public int getMaBA() {
		return MaBA;
	}

	public void setMaBA(int maBA) {
		MaBA = maBA;
	}

	public String getTenBA() {
		return TenBA;
	}

	public void setTenBA(String tenBA) {
		TenBA = tenBA;
	} 

	public String getTRANGTHAI() {
		return TRANGTHAI;
	}

	public void setTRANGTHAI(String tRANGTHAI) {
		TRANGTHAI = tRANGTHAI;
	}

	public int getSOCHONGOI() {
		return SOCHONGOI;
	}

	public void setSOCHONGOI(int sOCHONGOI) {
		SOCHONGOI = sOCHONGOI;
	}

}
