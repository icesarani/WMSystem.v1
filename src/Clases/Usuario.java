package Clases;

import Enums.TipoUser;

public class Usuario {
	private static int idCounter = 0;
	private int id;
	private String name;
	private String user;
	private String password;
	private boolean active = true;
	private TipoUser type;
	
	public Usuario(String nombre, String usuario, String contrasena, int tipoUsuario) {
		this.id = Usuario.idCounter;
		Usuario.idCounter++;
		this.name = nombre;
		this.user = usuario;
		this.password = contrasena;
		switch(tipoUsuario) {
		case 1:
			this.type = TipoUser.ADMIN;
			break;
		case 2:
			this.type = TipoUser.CONTROLLER;
			break;
		case 3:
			this.type = TipoUser.WORKER;
			break;
		default:
			this.type = TipoUser.WORKER;
			break;
		}
	}
	
	public Usuario(String usuario, String contrasena, int tipoUsuario) {
		this.id = Usuario.idCounter;
		Usuario.idCounter++;
		this.user = usuario;
		this.password = contrasena;
		switch(tipoUsuario) {
		case 1:
			this.type = TipoUser.ADMIN;
			break;
		case 2:
			this.type = TipoUser.CONTROLLER;
			break;
		case 3:
			this.type = TipoUser.WORKER;
			break;
		default:
			this.type = TipoUser.WORKER;
			break;
		}
	}
	
	public Usuario(String usuario, String contrasena) {
		this.id = Usuario.idCounter;
		Usuario.idCounter++;
		this.user = usuario;
		this.password = contrasena;
		this.type = TipoUser.WORKER;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

	public TipoUser getTipoUsuario() {
		return type;
	}

	public void setTipoUsuario(TipoUser type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "Usuario [user=" + user + ", password=" + password + ", type=" + type + "]";
	}	
	
	
	
}
