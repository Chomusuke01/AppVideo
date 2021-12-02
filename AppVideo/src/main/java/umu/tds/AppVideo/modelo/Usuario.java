package umu.tds.AppVideo.modelo;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Usuario {
	
	private String nombre;
	private String apellidos;
	private String email;
	private String usuario;
	private String contraseña;
	private boolean premium;
	private Date fechaNacimiento;
	private int codigo;
	private LinkedList<Video> recientes;
	private LinkedList<ListaReproduccion> listasVideos;
	
	
	public Usuario(String nombre, String apellidos, String email, String usuario, String contraseña, Date fechaNacimiento) {
		codigo = 0;
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.email = email;
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.fechaNacimiento = fechaNacimiento;
		this.premium = false;
		listasVideos = new LinkedList<ListaReproduccion>();
		recientes = new LinkedList<Video>();
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


	public List<Video> getRecientes() {
		return new LinkedList<Video>(recientes);
	}


	public List<ListaReproduccion> getListasVideos() {
		return new LinkedList<ListaReproduccion>(listasVideos);
	}
	
	public void modificarListaRecientes(List<Video> listaModificada) {
		recientes = new LinkedList<Video>(listaModificada);
	}
	
	public List<Video> getVideosFromLista(String lista){
		
	
		if (listasVideos.stream()
						.filter(l -> l.getNombre().equals(lista)).count() == 0) {
			return null;
		}
		
		return   listasVideos.stream()
				.filter(l -> l.getNombre().equals(lista))
				.flatMap(l -> l.getVideos().stream())
				.collect(Collectors.toList());
				
	}
	
	public void addListaRep (ListaReproduccion lista) {
		listasVideos.add(lista);
	}
	
	// Metodo para añadir los videos recientes desde la BD
	public void addReciente (Video reciente) {
		recientes.add(reciente);
		
	}
	
	// Metodo para añadir videos recientes desde la app
	public void nuevoReciente(Video reciente){
		
		int index = recientes.indexOf(reciente);
		if (index == -1) {
			recientes.addFirst(reciente);
		}else {
			recientes.remove(index);
			recientes.addFirst(reciente);
		}
		
		if (recientes.size() > 5) {
			recientes.removeLast();
		}
	}
	
	public ListaReproduccion añadirNuevoVideo(String lista, Video v) {
		
		ListaReproduccion listaRep = listasVideos.stream()
												 .filter(l -> l.getNombre().equals(lista))
												 .findFirst().orElse(null); // Nunca va a devolver null por que estamos seguros de que la lista en este punto existe.
		if (listaRep.añadirVideo(v)) { // No queremos videos repetidos en una lista.
			return listaRep;
		}
		return null;
	}
	
	public ListaReproduccion eliminarVideoLista(String lista, Video v) {
		
		ListaReproduccion listaRep = listasVideos.stream()
				 .filter(l -> l.getNombre().equals(lista))
				 .findFirst().orElse(null);
		
		listaRep.eliminarVideo(v);
		
		return listaRep;
	}
}
