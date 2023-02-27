package Restaurants;

public class Bill {
	private int MaHD;
	private int MaMA;
	private String TenMA;
	private int Soluong;
	private float Dongia;
	
	public Bill() {
		
	}
	
	public Bill(int maHD, int maMA, String tenMA, int soluong, float dongia) {
		MaHD = maHD;
		MaMA = maMA;
		TenMA = tenMA;
		Soluong = soluong;
		Dongia = dongia;
	}

	public int getMaHD() {
		return MaHD;
	}

	public void setMaHD(int maHD) {
		MaHD = maHD;
	}

	public int getMaMA() {
		return MaMA;
	}

	public void setMaMA(int maMA) {
		MaMA = maMA;
	}

	public String getTenMA() {
		return TenMA;
	}

	public void setTenMA(String tenMA) {
		TenMA = tenMA;
	}

	public int getSoluong() {
		return Soluong;
	}

	public void setSoluong(int soluong) {
		Soluong = soluong;
	}

	public float getDongia() {
		return Dongia;
	}

	public void setDongia(float dongia) {
		Dongia = dongia;
	}
}
