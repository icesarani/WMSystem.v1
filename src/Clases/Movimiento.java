package Clases;

import Enums.TipoMov;

public class Movimiento {
	private static int counterId = 0;
	private int id;
	private TipoMov tipomove;
	//private Cliente storerkey;
	private Ubicacion fromloc;
	private Ubicacion toloc;
	private Sku articulo;
	private Usuario user;
	
	public Movimiento(TipoMov type, Ubicacion locfrom, Ubicacion locto, Sku articulo, Usuario usuario) {
		this.id = Movimiento.counterId;
		Movimiento.counterId++;
		this.tipomove = type;
		//this.storerkey = storer;
		this.fromloc = locfrom;
		this.toloc = locto;
		this.articulo = articulo;
		this.user = usuario;
	}

	public Movimiento(TipoMov type, Ubicacion fromortoloc, Sku articulo, Usuario usuario) {
		this.id = Movimiento.counterId;
		Movimiento.counterId++;
		this.tipomove = type;
		//this.storerkey = storer;
		this.articulo = articulo;
		this.user = usuario;
		switch(type) {
		case EXPEDICION:
			this.fromloc = fromortoloc;
			break;
		case INGRESO:
			this.toloc = fromortoloc;
			break;
		default:
			break;
		}
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public TipoMov getTipomove() {
		return tipomove;
	}

	public void setTipomove(TipoMov tipomove) {
		this.tipomove = tipomove;
	}

	public Ubicacion getFromloc() {
		return fromloc;
	}

	public void setFromloc(Ubicacion fromloc) {
		this.fromloc = fromloc;
	}

	public Ubicacion getToloc() {
		return toloc;
	}

	public void setToloc(Ubicacion toloc) {
		this.toloc = toloc;
	}

	public Sku getArticulo() {
		return articulo;
	}

	public void setArticulo(Sku articulo) {
		this.articulo = articulo;
	}

	public Usuario getUser() {
		return user;
	}

	public void setUser(Usuario user) {
		this.user = user;
	}	
	
}
