package Views;

import java.util.Scanner;

import Clases.Usuario;
import Controllers.Controller;
import Controllers.ErrorController;
import Controllers.UserController;
import Excepciones.ObjectNotFoundInArray;

public class MainView {
	
	public static void start() {
		try (Scanner sc = new Scanner(System.in)) {
			System.out.println("Bienvenido a WMS Palleti't");
			System.out.println("Debe loguearse para poder continuar...");
			System.out.println("Ingrese su usuario: ");
			String user = sc.next();
			while(!Controller.existeUsuario(user)) {
				System.out.println("El usuario ingresado no se encuentra en el sistema, ingrese otro usuario: ");
				user = sc.next();
			}
			try {
				Usuario uAux = Controller.devolverUsuario(user);
				System.out.println("Ingrese la contraseña: ");
				String pass = sc.next();
				while(!uAux.getPassword().equals(pass)) {
					System.out.println("La contraseña ingresada es incorrecta pruebe con una nueva...");
					pass = sc.next();
				}
				System.out.println("Logueado con exito, sera redireccionado al menu principal!");
				UserController.setUserLogged(uAux);
				MainView.mainMenu();
			}
			catch (ObjectNotFoundInArray error) {
				System.out.println("Se genero un error al intentar encontrar un error en un array que no existe...");
				ErrorController.agregarError("Error ObjectNotFound en la funcion realizarMovimiento", error);
			}
		}
	}
	
	public static void mainMenu() {
		while(UserController.getUserLogged()!=null) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Ingrese que desea realizar: ");
			System.out.println("1. Altas");
			System.out.println("2. Bajas");
			System.out.println("3. Modificaciones");
			System.out.println("4. Expedicion/Recepcion/Traslado");
			System.out.println("5. Errores");
			System.out.println("6. Log Out");
			int optionSelected = sc.nextInt();
			switch(optionSelected) {
			case 1:
				MainView.menuAltas();
				break;
			case 2:
				MainView.menuBajas();
				break;
			case 3:
				MainView.menuModificiones();
				break;
			case 4:
				MainView.menuMovimientos();
				break;
			case 5:
				MainView.menuErrores();
				break;
			case 6:
				UserController.logOut();
				System.out.println("El Log Out se realizo con exito! Chau!");
				break;
			default:
				break;
			}
		}
	}
	
	public static void menuAltas() {
		System.out.println("Ingreso al menu de altas, ingrese que alta desea realizar:");
		System.out.println("1. Cliente");
		System.out.println("2. SKU");
		System.out.println("3. Ubicacion");
		System.out.println("4. Usuario");
		System.out.println("5. WareHouse");
		try (Scanner sc = new Scanner(System.in)) {
			int optionSelected = sc.nextInt();
			switch(optionSelected) {
			case 1:
				MainView.menuAltaCliente();
				break;
			case 2:
				MainView.menuAltaSku();
				break;
			case 3:
				MainView.menuAltaUbicacion();
				break;
			case 4:
				MainView.menuAltaUsuario();
				break;
			case 5:
				Controller.altaWareHouse();
				break;
			default:
				break;
			}
		}
	}
	
	public static int consultarTipoUsuarioAdmin() {
		try (Scanner sc = new Scanner(System.in)) {
			int optionSelected = -1;
			System.out.println("Elija que permisos tendra el usuario:");
			System.out.println("1. ADMIN");
			System.out.println("2. CONTROLLER");
			System.out.println("3. WORKER");
			optionSelected = sc.nextInt();
			if(optionSelected<1 || optionSelected>3) {System.out.println("El permiso ingresado es invalido, se cansela la creacion de usuario...");;optionSelected = -1;}
			return optionSelected;
		}
	}
	
	public static int consultarTipoUsuarioController() {
		try (Scanner sc = new Scanner(System.in)) {
			int optionSelected = -1;
			System.out.println("Elija que permisos tendra el usuario:");
			System.out.println("1. CONTROLLER");
			System.out.println("2. WORKER");
			optionSelected = sc.nextInt();
			if (optionSelected == 1) {
				optionSelected = 2;
			} else {
				if(optionSelected == 2) {
					optionSelected = 3;
				}else {
					optionSelected = -1;
				}
			}
			if(optionSelected == -1) {System.out.println("El permiso ingresado es invalido, se cancela la creacion de usuario...");}
			return optionSelected;
		}
	}
	
	public static int consultarTipoUbicacion() {
		try (Scanner sc = new Scanner(System.in)) {
			int optionSelected = -1;
			System.out.println("Seleccion el Tipo de Ubicacion que se desea generar:");
			System.out.println("1. PICK (Preparacion)");
			System.out.println("2. RACK (Almacenamiento)");
			optionSelected = sc.nextInt();
			if(optionSelected < 1 || optionSelected >2) {optionSelected = -1;}
			return optionSelected;
		}
	}
	
}
