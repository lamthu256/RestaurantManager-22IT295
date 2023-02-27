package Restaurants;

public class Food {
	private int MaMA;
	private String TenMA;
	private Float DONGIA;
	
	public Food(int maMA, String tenMA, Float dONGIA) {
		MaMA = maMA;
		TenMA = tenMA;
		DONGIA = dONGIA;
	}
	
	public Food(){
		
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

	public Float getDONGIA() {
		return DONGIA;
	}

	public void setDONGIA(Float dONGIA) {
		DONGIA = dONGIA;
	}

	
}
