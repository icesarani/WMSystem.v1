package Clases;

public class UbiXSku {
	private Ubicacion loc;
	private Sku sku;
	private int qty;
	
	public UbiXSku(Ubicacion location, Sku sku, int newQty) {
		this.loc = location;
		this.sku = sku;
		this.qty = newQty;
	}
	
	public UbiXSku(Ubicacion location, Sku sku) {
		this.loc = location;
		this.sku = sku;
	}

	public Ubicacion getLoc() {
		return loc;
	}

	public void setLoc(Ubicacion loc) {
		this.loc = loc;
	}

	public Sku getSku() {
		return sku;
	}

	public void setSku(Sku sku) {
		this.sku = sku;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
	public boolean qtyPositiva() {
		if(this.qty>0) {return true;}
		return false;
	}
	
}
