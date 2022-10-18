package Clases;

import java.util.ArrayList;
import java.util.List;

public class WareHouse {
	private List<Sku> articulos = new ArrayList<Sku>();
	private List<Cliente> clientes = new ArrayList<Cliente>();
	private List<Ubicacion> locs = new ArrayList<Ubicacion>();
	private List<Movimiento> transactions = new ArrayList<Movimiento>();
	private List<UbiXSku> skuxloc = new ArrayList<UbiXSku>();
	static WareHouse wh;
	
	private WareHouse() {}
	
	public static WareHouse getInstance() {
		if(wh==null) {
			wh=new WareHouse();
			return wh;
		}else {
			return wh;
		}
	}
	
	public static boolean existeWH() {if(WareHouse.wh!=null) {return true;}return false;}
	
	public List<Sku> getArticulos() {
		return articulos;
	}
	public List<Cliente> getClientes() {
		return clientes;
	}
	public List<Ubicacion> getLocs() {
		return locs;
	}
	public List<Movimiento> getTransactions() {
		return transactions;
	}
	
	public List<UbiXSku> getSkuxloc() {
		return skuxloc;
	}
	
}
