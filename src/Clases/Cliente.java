package Clases;

public class Cliente {
	private String storer;
	private boolean active = true;
	private Direccion direction;
	
	public Cliente(String nombre, String calle, int altura) {
		this.storer = nombre;
		this.direction = new Direccion(calle,altura);
	}
	
	public Cliente(String nombre, Direccion direc) {
		this.storer = nombre;
		this.direction = direc;
	}
	
	public Cliente(String nombre) {
		this.storer = nombre;
	}

	public String getStorer() {
		return storer;
	}

	public void setStorer(String storer) {
		this.storer = storer;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public Direccion getDirection() {
		return direction;
	}

	public void setDirection(Direccion direction) {
		this.direction = direction;
	}
	
}
