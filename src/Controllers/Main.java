package Controllers;

import Clases.Usuario;
import Views.MainView;

public class Main {

	public static void main(String[] args) {
		UserController.getUsers().add(new Usuario("admin","admin",1));
		MainView.start();
	}

}
