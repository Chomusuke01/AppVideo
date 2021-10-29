package umu.tds.AppVideo.modelo;

import java.util.Date;
//import java.util.LinkedList;
//import java.util.List;

public class Usuario {
	
	private String nombre;
	private String apellidos;
	private String email;
	private String usuario;
	private String contraseña;
	private boolean premium;
	private Date fechaNacimiento;
	private int codigo;
	//private LinkedList<Video> recientes;
	
	
	public Usuario(String nombre, String apellidos, String email, String usuario, String contraseña, Date fechaNacimiento) {
		codigo = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.fechaNacimiento = fechaNacimiento;
		premium = false;
		//recientes = new LinkedList<Video>();
	}


	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getApellidos() {
		return apellidos;
	}


	public void setApellidos(String apellidos) {
		this.apellidos = apellidos;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getContraseña() {
		return contraseña;
	}


	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}


	public boolean isPremium() {
		return premium;
	}


	public void setPremium(boolean premium) {
		this.premium = premium;
	}


	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}


	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

/*
	public List<Video> getRecientes() {
		return new LinkedList<Video>(recientes);
	}
*/
}
