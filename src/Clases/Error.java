package Clases;

import java.time.LocalDateTime;

public class Error {
	private static int idCounter = 0;
	private int idError;
	private String msj;
	private LocalDateTime addDate;
	private Exception excepcion;
	
	public Error(String msj, Exception excepcion) {
		this.idError = Error.idCounter;
		Error.idCounter++;
		this.msj = msj;
		this.excepcion = excepcion;
		this.addDate = LocalDateTime.now();
	}

	public int getIdError() {
		return idError;
	}

	public void setIdError(int idError) {
		this.idError = idError;
	}

	public String getMsj() {
		return msj;
	}

	public void setMsj(String msj) {
		this.msj = msj;
	}

	public LocalDateTime getAddDate() {
		return addDate;
	}

	public void setAddDate(LocalDateTime addDate) {
		this.addDate = addDate;
	}

	public Exception getExcepcion() {
		return excepcion;
	}

	public void setExcepcion(Exception excepcion) {
		this.excepcion = excepcion;
	}

	@Override
	public String toString() {
		return "Error [idError=" + idError + ", msj=" + msj + ", addDate=" + addDate + ", excepcion=" + excepcion + "]";
	}
	
}
