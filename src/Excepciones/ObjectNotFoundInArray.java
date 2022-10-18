package Excepciones;

public class ObjectNotFoundInArray extends Exception{
	/*
	 * Excepcion que se ejecuta cuando en una lista no se encuentra un objecto buscado y no se puede devolver nada.
	 */
	private static final long serialVersionUID = 1L;
	
	public ObjectNotFoundInArray(String msj) {
		super(msj);
	}
}
