package Clases;

public class Sku {
	private String id;
	private String desc;
	private Cliente storerkey;
	private boolean active = true;
	private double price;
	
	public Sku(String id, String descripcion, Cliente storer, double precio) {
		this.id = id;
		this.desc = descripcion;
		this.storerkey = storer;
		this.price = precio;
	}
	
	public Sku(String id, Cliente storer) {
		this.id = id;
		this.storerkey = storer;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public Cliente getStorerkey() {
		return storerkey;
	}

	public void setStorerkey(Cliente storerkey) {
		this.storerkey = storerkey;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}
	
	
	
}
