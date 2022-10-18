package Clases;

import Enums.TipoLoc;
import Enums.EstadoLoc;

public class Ubicacion {
	private String loc;
	private TipoLoc type;
	private EstadoLoc status = EstadoLoc.OK;
	private char checkdigit;
	
	public Ubicacion(String location, TipoLoc tipo, char checkd) {
		this.loc = location;
		this.type = tipo;
		this.checkdigit = checkd;
	}

	public Ubicacion(String location, int tipo, char checkd) {
		this.loc = location;
		this.checkdigit = checkd;
		if(tipo==1) {this.type=TipoLoc.PICK;}else {this.type=TipoLoc.RACK;}
	}

	
	public String getLoc() {
		return loc;
	}

	public void setLoc(String loc) {
		this.loc = loc;
	}

	public TipoLoc getType() {
		return type;
	}

	public void setType(TipoLoc type) {
		this.type = type;
	}

	public EstadoLoc getStatus() {
		return status;
	}

	public void setStatus(EstadoLoc status) {
		this.status = status;
	}

	public char getCheckdigit() {
		return checkdigit;
	}

	public void setCheckdigit(char checkdigit) {
		this.checkdigit = checkdigit;
	}
	
}
