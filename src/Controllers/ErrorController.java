package Controllers;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import Clases.Error;

public class ErrorController {
	private static List<Error> errores = new ArrayList<Error>();

	public static void agregarError(String msj, Exception excepcion) {
		ErrorController.errores.add(new Error(msj,excepcion));
	}
	
	public static List<Error> getErrores(){return ErrorController.errores;}

	public static void imprimirErrores() {
		Iterator<Error> it = ErrorController.errores.iterator();
		if(!it.hasNext()) {
			System.out.println("No hay errores registrados hasta el momento...");
			return;
		}
		System.out.println("Lista de incidencias ocurridas desde el inicio de la app:");
		while(it.hasNext()) {
			System.out.println(it.next());
		}
		System.out.println("No hay más errores...");
	}
	
}
