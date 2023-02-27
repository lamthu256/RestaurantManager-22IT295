package Restaurants;

public class Staff {
	private int MaNV;
	private String HOTEN;
	private String CHUCVU;
	
	public Staff(int maNV, String hOTEN, String cHUCVU) {
		MaNV = maNV;
		HOTEN = hOTEN;
		CHUCVU = cHUCVU; 
	}

	public Staff(){
		
	}
	
	public int getMaNV() {
		return MaNV;
	}

	public void setMaNV(int maNV) {
		MaNV = maNV;
	}

	public String getHOTEN() {
		return HOTEN;
	}

	public void setHOTEN(String hOTEN) {
		HOTEN = hOTEN;
	}

	public String getCHUCVU() {
		return CHUCVU;
	}

	public void setCHUCVU(String cHUCVU) {
		CHUCVU = cHUCVU;
	}
	
	

}
