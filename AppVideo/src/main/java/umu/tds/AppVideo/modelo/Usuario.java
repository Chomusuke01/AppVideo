package umu.tds.AppVideo.modelo;

import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Usuario {
	
	private final static String FILTRO_DEFECTO = "umu.tds.AppVideo.modelo.NoFiltro";
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
	private Filtro filtro;
	private String filtroActual;
	
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
		filtro = new Filtro(new NoFiltro());
		filtroActual =  FILTRO_DEFECTO;
	}

	public String getFiltroActual() {
		return filtroActual;
	}

	public void setFiltroActual(String filtroActual) {
		this.filtroActual = filtroActual;
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

	// Obtener los videos recientes
	public List<Video> getRecientes() {
		return new LinkedList<Video>(recientes);
	}

	// recuperar todas las listas de rep
	public List<ListaReproduccion> getListasVideos() {
		return new LinkedList<ListaReproduccion>(listasVideos);
	}
	
	//modificar la lista de recientes
	public void modificarListaRecientes(List<Video> listaModificada) {
		recientes = new LinkedList<Video>(listaModificada);
	}
	
	// Obtener una lista de rep a partir de su nombre.
	public ListaReproduccion getListaRep(String lista){
		
		return  listasVideos.stream()
				.filter(l -> l.getNombre().equals(lista)).findFirst().orElse(null);
				
	}
	
	// Devuelve todos los videos de todas las listas de rep
	public List<Video> getVideosFromListas(){
		
		HashSet<Video> videos = new HashSet<Video>();
		
		for(ListaReproduccion lista : listasVideos) {
			videos.addAll(lista.getVideos());
		}
		return new LinkedList<Video>(videos);
	}
	
	// Añade una lista de rep
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
	
	//Elimina una lista de rep
	public void eliminarListaRep (ListaReproduccion lista) {
		int listaBorrar = -1;
		int i = 0;
		for (ListaReproduccion listaReproduccion : listasVideos) {
			if (listaReproduccion.getNombre().equals(lista.getNombre())) {
				listaBorrar = i;
			}
			i++;
		}
		if (listaBorrar != -1) {
			listasVideos.remove(listaBorrar);
		}
		
	}
	
	// Metodo para filtrar videos de una búsqueda
	public List<Video> filtrarVideos(List<Video> videos) {
		
		return videos.stream().filter(v -> filtro.isVideoOK(v,this)).collect(Collectors.toList());
	}
	
	// Metodo para cambiar el filtro actual
	public void cambiarFiltro(FiltroVideo filtro) {
		this.filtro.setFiltro(filtro);
	}
	
}
