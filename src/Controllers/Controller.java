package Controllers;

import Clases.WareHouse;
import Enums.EstadoLoc;
import Enums.TipoMov;
import Enums.TipoUser;
import Excepciones.ObjectNotFoundInArray;
import Views.MainView;

import java.util.Iterator;

import Clases.Cliente;
import Clases.Direccion;
import Clases.Movimiento;
import Clases.Sku;
import Clases.UbiXSku;
import Clases.Ubicacion;
import Clases.Usuario;

public class Controller {
	
	private static WareHouse wh;
	
	public static void altaWareHouse() {
		if(WareHouse.existeWH()) {System.out.println("Ya existe un WareHouse, solo se puede generar uno por aplicacion...");return;}
		Controller.wh = WareHouse.getInstance();
		//UserController.getUsers().add(new Usuario("admin","admin",1));
		Controller.wh.getLocs().add(new Ubicacion("MUELLE_INGRESO",1,'A'));
		System.out.println("Se genero con exito el WareHouse!");
	}
	
	private static boolean existeSku(String idSku) {
		/*
		 * Devuelve true o false dependiendo si existe o no el Id del SKU en el
		 * warehouse estimado.
		 */
		Iterator<Sku> it = wh.getArticulos().iterator();
		while (it.hasNext()) {
			Sku sAux = it.next();
			if (sAux.getId().equals(idSku)) {
				return true;
			}
		}
		return false;
	}

	private static boolean existeUbicacion(String loc) {
		Iterator<Ubicacion> it = wh.getLocs().iterator();
		while (it.hasNext()) {
			Ubicacion uAux = it.next();
			if (uAux.getLoc().equals(loc)) {
				return true;
			}
		}
		return false;
	}
	private static boolean existeCliente(String storer) {
		/*
		 * Devuelve true o false dependiendo si existe o no el nombre del Cliente en el
		 * warehouse estimado.
		 */
		Iterator<Cliente> it = wh.getClientes().iterator();
		while (it.hasNext()) {
			Cliente cAux = it.next();
			if (cAux.getStorer().equals(storer)) {
				return true;
			}
		}
		return false;
	}

	public static boolean existeUsuario(String user) {
		/*
		 * Devuelve true o false dependiendo si existe o no el user en el warehouse
		 * estimado.
		 */
		Iterator<Usuario> it = UserController.getUsers().iterator();
		while (it.hasNext()) {
			Usuario uAux = it.next();
			if (uAux.getUser().equals(user)) {
				return true;
			}
		}
		return false;
	}

	private static boolean existeCantidadAlmacenada(String ubi, String sku) {
		Iterator<UbiXSku> it = wh.getSkuxloc().iterator();
		while (it.hasNext()) {
			UbiXSku uxsAux = it.next();
			if (uxsAux.getLoc().equals(ubi) && uxsAux.getSku().equals(sku) && uxsAux.qtyPositiva()) {
				return true;
			}
		}
		return false;
	}

	private static Sku devolverSku(String sku_id) throws ObjectNotFoundInArray {
		Iterator<Sku> it = wh.getArticulos().iterator();
		while (it.hasNext()) {
			Sku sAux = it.next();
			if (sAux.getId().equals(sku_id)) {
				return sAux;
			}
		}
		throw new ObjectNotFoundInArray(
				"Error al buscar el objeto Sku en la clase Controller en la linea n° 76 con el indicador de SKU:"
						+ sku_id);
	}

	private static Cliente devolverCliente(String st) throws ObjectNotFoundInArray {
		Iterator<Cliente> it = wh.getClientes().iterator();
		while (it.hasNext()) {
			Cliente cAux = it.next();
			if (cAux.getStorer().equals(st)) {
				return cAux;
			}
		}
		throw new ObjectNotFoundInArray(
				"Error al buscar el objeto Cliente en la clase Controller en la linea n° 85 con el indicador de Cliente:"
						+ st);
	}

	public static Usuario devolverUsuario(String user) throws ObjectNotFoundInArray {
		Iterator<Usuario> it = UserController.getUsers().iterator();
		while (it.hasNext()) {
			Usuario uAux = it.next();
			if (uAux.getUser().equals(user)) {
				return uAux;
			}
		}
		throw new ObjectNotFoundInArray(
				"Error al buscar el objeto Usuario en la clase Controller en la linea n° 94 con el indicador de Cliente:"
						+ user);
	}

	private static Ubicacion devolverUbicacion(String loc) throws ObjectNotFoundInArray {
		Iterator<Ubicacion> it = wh.getLocs().iterator();
		while (it.hasNext()) {
			Ubicacion uAux = it.next();
			if (uAux.getLoc().equals(loc)) {
				return uAux;
			}
		}
		throw new ObjectNotFoundInArray(
				"Error al buscar el objeto Ubicacion en la clase Controller en la linea n° 103 con el indicador de LOC:"
						+ loc);
	}

	private static UbiXSku devolverUBIXSKU(String loc, String sku) throws ObjectNotFoundInArray {
		Iterator<UbiXSku> it = wh.getSkuxloc().iterator();
		while (it.hasNext()) {
			UbiXSku uxsAux = it.next();
			if (uxsAux.getLoc().equals(loc) && uxsAux.getSku().equals(sku)) {
				return uxsAux;
			}
		}
		throw new ObjectNotFoundInArray(
				"Error al buscar el objeto UbiXSku en la clase Controller en la linea n° 114 con el indicador de SKU:"
						+ sku + " y LOC:" + loc);
	}

	private static void realizarMovimiento(TipoMov type, String fromloc, String toloc, String sku,
			int qty) {
		try {
			if (UserController.getUserLogged() == null || !UserController.getUserLogged().isActive()) {
				System.out.println("No hay un usuario logueado o no esta activo...");
				return;
			}
			if (!Controller.existeSku(sku)) {
				System.out.println("El SKU ingresado para la operacion no existe en los registros...");
				return;
			}
			if (type == TipoMov.EXPEDICION || type == TipoMov.TRASLADO) {
				if (!existeCantidadAlmacenada(fromloc, sku)) {
					System.out.println("No se puede realizar el movimiento requerido, no hay Cantidad del SKU " + sku
							+ " en la Ubicacion " + fromloc);
					return;
				}
				if (devolverUBIXSKU(fromloc, sku).getQty() < qty) {
					System.out.println("No se puede realizar el movimiento requerido, no hay Cantidad del SKU " + sku
							+ " en la Ubicacion " + fromloc);
					return;
				}
			}
			if (type == TipoMov.EXPEDICION) {
				if (!Controller.existeUbicacion(fromloc)) {
					System.out.println("La ubicacion ingresada para la operacion no existe en los registros...");
					return;
				}
				UbiXSku uxsAux = Controller.devolverUBIXSKU(fromloc, sku);
				uxsAux.setQty(uxsAux.getQty() - qty);
			}
			if (type == TipoMov.INGRESO) {
				if (!Controller.existeUbicacion(toloc)) {
					System.out.println("La ubicacion ingresada para la operacion no existe en los registros...");
					return;
				}
				UbiXSku uxsAux = Controller.devolverUBIXSKU(toloc, sku);
				uxsAux.setQty(uxsAux.getQty() + qty);
			}
			if (type == TipoMov.TRASLADO) {
				if (!Controller.existeUbicacion(fromloc) || !Controller.existeUbicacion(toloc)) {
					System.out.println("La ubicacion ingresada para la operacion no existe en los registros...");
					return;
				}
				UbiXSku uxsFrom = Controller.devolverUBIXSKU(fromloc, sku);
				UbiXSku uxsTo = Controller.devolverUBIXSKU(toloc, sku);
				uxsTo.setQty(uxsTo.getQty() + qty);
				uxsFrom.setQty(uxsFrom.getQty() + qty);
			}
			wh.getTransactions()
					.add(new Movimiento(type, Controller.devolverUbicacion(fromloc),
							Controller.devolverUbicacion(toloc), Controller.devolverSku(sku),
							UserController.getUserLogged()));
		} catch (ObjectNotFoundInArray error) {
			System.out.println("Se genero un error al intentar encontrar un error en un array que no existe...");
			ErrorController.agregarError("Error ObjectNotFound en la funcion realizarMovimiento", error);
		}
	}

	private static void realizarMovimiento(TipoMov type, String fromortoloc, String sku, int qty) {
		try {
			if (UserController.getUserLogged() == null || !UserController.getUserLogged().isActive()) {
				System.out.println("No hay un usuario logueado o no esta activo...");
				return;
			}
			if (!Controller.existeSku(sku)) {
				System.out.println("El SKU ingresado para la operacion no existe en los registros...");
				return;
			}
			if (type == TipoMov.EXPEDICION) {
				if (!existeCantidadAlmacenada(fromortoloc, sku)) {
					System.out.println("No se puede realizar el movimiento requerido, no hay Cantidad del SKU " + sku
							+ " en la Ubicacion " + fromortoloc);
					return;
				}
				if (devolverUBIXSKU(fromortoloc, sku).getQty() < qty) {
					System.out.println("No se puede realizar el movimiento requerido, no hay Cantidad del SKU " + sku
							+ " en la Ubicacion " + fromortoloc);
					return;
				}
			}
			if (type == TipoMov.EXPEDICION) {
				if (!Controller.existeUbicacion(fromortoloc)) {
					System.out.println("La ubicacion ingresada para la operacion no existe en los registros...");
					return;
				}
				UbiXSku uxsAux = Controller.devolverUBIXSKU(fromortoloc, sku);
				uxsAux.setQty(uxsAux.getQty() - qty);
			}
			if (type == TipoMov.INGRESO) {
				if (!Controller.existeCantidadAlmacenada(fromortoloc, sku)) {
					wh.getSkuxloc().add(new UbiXSku(Controller.devolverUbicacion(fromortoloc),
							Controller.devolverSku(sku), 0));
				}
				if (!Controller.existeUbicacion(fromortoloc)) {
					System.out.println("La ubicacion ingresada para la operacion no existe en los registros...");
					return;
				}
				UbiXSku uxsAux = Controller.devolverUBIXSKU(fromortoloc, sku);
				uxsAux.setQty(uxsAux.getQty() + qty);
			}
			wh.getTransactions().add(new Movimiento(type, Controller.devolverUbicacion(fromortoloc),
					Controller.devolverSku(sku), UserController.getUserLogged()));
			System.out.println("El movimiento ingreso a la cola de transacciones...");
		} catch (ObjectNotFoundInArray error) {
			System.out.println("Se genero un error al intentar encontrar un error en un array que no existe...");
			ErrorController.agregarError("Error ObjectNotFound en la funcion realizarMovimiento", error);
		}
	}

	public static void realizarRecepcion(String sku, int qty) {
		try {
			Ubicacion MUELLE_INGRESO = devolverUbicacion("MUELLE_INGRESO");
			realizarMovimiento(TipoMov.INGRESO, MUELLE_INGRESO.getLoc(), sku, qty);
			System.out.println("El ingreso se realizo con exito!");
		} catch (ObjectNotFoundInArray error) {
			System.out.println("Se genero un error al intentar encontrar un error en un array que no existe...");
			ErrorController.agregarError("Error ObjectNotFound en la funcion realizarIngreso", error);
		}
	}

	public static void realizarExpedicion(String fromloc, String sku, int qty) {
		realizarMovimiento(TipoMov.EXPEDICION, fromloc, sku, qty);
		System.out.println("La Expedicion fue realizado con exito!");
	}

	public static void realizarTraslado(String fromloc, String toloc, String sku, int qty) {
		realizarMovimiento(TipoMov.TRASLADO, fromloc, toloc, sku, qty);
		System.out.println("El Traslado fue realizado con exito!");
	}

	public static void altaUsuario(String user, String password, String nombre) {
		
		if(UserController.getUserLogged()==null) {System.out.println("Usted no esta logueado...");;return;}
		
		if (Controller.existeCliente(user)) {
			System.out.println("El usuario ya existe...");
			return;
		}

		int optionSelected = -1;
		switch (UserController.getUserLogged().getTipoUsuario()) {
		case ADMIN:
			optionSelected = MainView.consultarTipoUsuarioAdmin();
			break;
		case CONTROLLER:
			optionSelected = MainView.consultarTipoUsuarioController();
			break;
		case WORKER:
			System.out.println("Usted no tiene permisos de creacion de usuario...");
			return;
		}
		if (optionSelected==-1) {
			System.out.println("Volviendo al Menu...");
			return;
		}
		UserController.getUsers().add(new Usuario(nombre, user, password, optionSelected));
		System.out.println("El Usuario fue dado de alta con exito!");

	}

	public static void altaUbicacion(String loc,char ckd) {
		if(existeUbicacion(loc)) {System.out.println("La ubicacion ingresada ya existe...");return;}
		int locType = MainView.consultarTipoUbicacion();
		wh.getLocs().add(new Ubicacion(loc,locType,ckd));
		System.out.println("La Ubicacion fue dada de alta con exito!");
	}

	public static void altaCliente(String storer, Direccion dir) {
		if(Controller.existeCliente(storer)) {System.out.println("El cliente ya existe en el sistema...");return;}
		wh.getClientes().add(new Cliente(storer,dir));
		System.out.println("El cliente se dio de alta con exito!");
	}
	
	public static void altaArticulo(String sku, String desc, String storer, double price) {
		if(Controller.existeSku(sku)) {System.out.println("El sku ingresado ya existe en el sistema...");return;}
		if(!Controller.existeCliente(storer)) {System.out.println("El Cliente ingresado no existe...");return;}
		try {
			Cliente cAux = Controller.devolverCliente(storer);
			wh.getArticulos().add(new Sku(storer,desc,cAux,price));
			System.out.println("El articulo se dio de alta con exito!");
		}
		catch (ObjectNotFoundInArray error) {
		System.out.println("Se genero un error al intentar encontrar un error en un array que no existe...");
		ErrorController.agregarError("Error ObjectNotFound en la funcion realizarMovimiento", error);
		}
	}


	public static void bajaUsuario(String user) {
		if(!Controller.existeUsuario(user)) {System.out.println("El Usuario ingresado no esta registrado en el sistema...");return;}
		if(UserController.getUserLogged().getTipoUsuario()==TipoUser.WORKER) {System.out.println("Con los roles de worker no puede dar de baja ningun usuario...");return;}
		try {
			Usuario uAux = Controller.devolverUsuario(user);
			if(uAux.getTipoUsuario()==TipoUser.ADMIN && UserController.getUserLogged().getTipoUsuario()==TipoUser.CONTROLLER) {
				System.out.println("Usted no tiene permisos como para dar de baja un Usuario tipo Admin...");
				return;
			}
			uAux.setActive(false);
			System.out.println("El Usuario se dio de baja con exito!");
		}
		catch (ObjectNotFoundInArray error) {
			System.out.println("Se genero un error al intentar encontrar un error en un array que no existe...");
			ErrorController.agregarError("Error ObjectNotFound en la funcion realizarMovimiento", error);
		}
	}
	
	public static void bajaCliente(String storer) {
		if(!Controller.existeCliente(storer)) {System.out.println("No se puede dar de baja el cliente, no existe en el sistema...");return;}
		try {
			Controller.devolverCliente(storer).setActive(false);
			System.out.println("Se dio de baja el Cliente!");
		}
		catch (ObjectNotFoundInArray error) {
			System.out.println("Se genero un error al intentar encontrar un error en un array que no existe...");
			ErrorController.agregarError("Error ObjectNotFound en la funcion realizarMovimiento", error);
		}
	}
	
	public static void bajaSku(String sku) {
		if(!Controller.existeSku(sku)) {System.out.println("El SKU ingresado no se encuentra en el sistema...");return;}
		try {
			Controller.devolverSku(sku).setActive(false);
			System.out.println("Se dio de baja el SKU!");
		}
		catch (ObjectNotFoundInArray error) {
			System.out.println("Se genero un error al intentar encontrar un error en un array que no existe...");
			ErrorController.agregarError("Error ObjectNotFound en la funcion realizarMovimiento", error);
		}
	}
	
	public static void reactivarSku(String sku) {
		if(!Controller.existeSku(sku)) {System.out.println("El SKU ingresado no se encuentra en el sistema...");return;}
		try {
			Controller.devolverSku(sku).setActive(true);
			System.out.println("Se reactivo el SKU!");
		}
		catch (ObjectNotFoundInArray error) {
			System.out.println("Se genero un error al intentar encontrar un error en un array que no existe...");
			ErrorController.agregarError("Error ObjectNotFound en la funcion realizarMovimiento", error);
		}
	}
	
	public static void reactivarUsuario(String user) {
		if(!Controller.existeUsuario(user)) {System.out.println("El Usuario ingresado no esta registrado en el sistema...");return;}
		if(UserController.getUserLogged().getTipoUsuario()==TipoUser.WORKER) {System.out.println("Con los roles de worker no puede reactivar ningun usuario...");return;}
		try {
			Usuario uAux = Controller.devolverUsuario(user);
			if(uAux.getTipoUsuario()==TipoUser.ADMIN && UserController.getUserLogged().getTipoUsuario()==TipoUser.CONTROLLER) {
				System.out.println("Usted no tiene permisos como para reactivar un Usuario tipo Admin...");
				return;
			}
			uAux.setActive(false);
			System.out.println("El Usuario se reactivo con exito!");
		}
		catch (ObjectNotFoundInArray error) {
			System.out.println("Se genero un error al intentar encontrar un error en un array que no existe...");
			ErrorController.agregarError("Error ObjectNotFound en la funcion realizarMovimiento", error);
		}
	}
	
	public static void reactivarCliente(String storer) {
		if(!Controller.existeCliente(storer)) {System.out.println("No se puede reactivar el cliente, no existe en el sistema...");return;}
		try {
			Controller.devolverCliente(storer).setActive(true);
			System.out.println("Se dio reactivo el Cliente!");
		}
		catch (ObjectNotFoundInArray error) {
			System.out.println("Se genero un error al intentar encontrar un error en un array que no existe...");
			ErrorController.agregarError("Error ObjectNotFound en la funcion realizarMovimiento", error);
		}
	}
	
	public static void holdLoc(String loc) {
		if(!Controller.existeUbicacion(loc)) {System.out.println("No se puede holdear la ubicacion si no existe...");return;}
		try {
			Controller.devolverUbicacion(loc).setStatus(EstadoLoc.HOLD);
			System.out.println("Se holdeo con exito la posicion "+loc);
		}
		catch (ObjectNotFoundInArray error) {
			System.out.println("Se genero un error al intentar encontrar un error en un array que no existe...");
			ErrorController.agregarError("Error ObjectNotFound en la funcion realizarMovimiento", error);
		}
	}
	
	public static void okLoc(String loc) {
		if(!Controller.existeUbicacion(loc)) {System.out.println("No se puede desholdear la ubicacion si no existe...");return;}
		try {
			Controller.devolverUbicacion(loc).setStatus(EstadoLoc.OK);
			System.out.println("Se desholdeo con exito la posicion "+loc);
		}
		catch (ObjectNotFoundInArray error) {
			System.out.println("Se genero un error al intentar encontrar un error en un array que no existe...");
			ErrorController.agregarError("Error ObjectNotFound en la funcion realizarMovimiento", error);
		}
	}
	
}
