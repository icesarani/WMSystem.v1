package Clases;

public class Direccion {
	private String calle;
	private int altura;
	private int codZip;
	private String piso;
	private String depto;
	private String observacion;
	
	public Direccion(String calle,int altura,int codigoZip,String piso,String depto,String observacion) {
		this.calle = calle;
		this.altura = altura;
		this.codZip = codigoZip;
		this.piso = piso;
		this.depto = depto;
		this.observacion = observacion;
	}
	
	public Direccion(String calle,int altura,int codigoZip,String piso,String depto) {
		this.calle = calle;
		this.altura = altura;
		this.codZip = codigoZip;
		this.piso = piso;
		this.depto = depto;
	}
	
	public Direccion(String calle,int altura) {
		this.calle = calle;
		this.altura = altura;
	}

	public String getCalle() {
		return calle;
	}

	public void setCalle(String calle) {
		this.calle = calle;
	}

	public int getAltura() {
		return altura;
	}

	public void setAltura(int altura) {
		this.altura = altura;
	}

	public int getCodZip() {
		return codZip;
	}

	public void setCodZip(int codZip) {
		this.codZip = codZip;
	}

	public String getPiso() {
		return piso;
	}

	public void setPiso(String piso) {
		this.piso = piso;
	}

	public String getDepto() {
		return depto;
	}

	public void setDepto(String depto) {
		this.depto = depto;
	}

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	
}
