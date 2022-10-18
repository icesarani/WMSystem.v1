package Controllers;

import java.util.ArrayList;
import java.util.List;

import Clases.Usuario;
import Excepciones.ObjectNotFoundInArray;

public class UserController {
	private static Usuario userLogged;
	private static List<Usuario> users = new ArrayList<Usuario>();
	
	public static Usuario getUserLogged() {return UserController.userLogged;}
	
	public static void setUserLogged(Usuario user) {UserController.userLogged=user;}
	
	public static List<Usuario> getUsers() {return UserController.users;}
	
	public static void logIn(String user, String pass) {
		if(!Controller.existeUsuario(user)) {
			System.out.println("No existe el usuario ingresado...");
			return;
		}
		try {
		Usuario uAux = Controller.devolverUsuario(user);
		if(uAux.isActive() && uAux.getPassword().equals(pass)) {
			UserController.setUserLogged(uAux);
			System.out.println("Se logueo correctamente con el usuario "+user);
		}else {
			System.out.println("Hubo un error en el Logueo, volver a intentarlo...");
			return;
		}
		}
		catch(ObjectNotFoundInArray error) {
			ErrorController.agregarError("Se genero un error al intentar loguearse en la funcion loggIn del controlador UserController", error);
			System.out.println("Se genero un error al intentar loguearse en la funcion loggIn del controlador UserController");
			return;
		}
	}
	
	public static void logOut() {
		UserController.setUserLogged(null);
		System.out.println("Ya se deslogueo del sistema...");
		}
	
}
