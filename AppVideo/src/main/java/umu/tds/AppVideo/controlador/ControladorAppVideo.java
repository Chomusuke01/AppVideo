package umu.tds.AppVideo.controlador;


import java.util.Date;
import java.util.List;

import umu.tds.AppVideo.modelo.CatalogoUsuarios;
import umu.tds.AppVideo.modelo.CatalogoVideos;
import umu.tds.AppVideo.modelo.Etiqueta;
import umu.tds.AppVideo.modelo.ListaReproduccion;
import umu.tds.AppVideo.modelo.Usuario;
import umu.tds.AppVideo.modelo.Video;
import umu.tds.AppVideo.persistencia.DAOException;
import umu.tds.AppVideo.persistencia.FactoriaDAO;
import umu.tds.AppVideo.persistencia.IAdaptadorEtiquetaDAO;
import umu.tds.AppVideo.persistencia.IAdaptadorListaReproduccionDAO;
import umu.tds.AppVideo.persistencia.IAdaptadorUsuarioDAO;
import umu.tds.AppVideo.persistencia.IAdaptadorVideoDAO;

public class ControladorAppVideo {
	
	private static ControladorAppVideo unicaInstancia;

	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorVideoDAO adaptadorVideo;
	private IAdaptadorListaReproduccionDAO adaptadorListaReproduccion;
	private IAdaptadorEtiquetaDAO adpatadorEtiqueta;

	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoVideos catalogoVideos;
	private Usuario usuarioActual;

	private ControladorAppVideo() {
		inicializarAdaptadores(); 
		inicializarCatalogos();
	}
	
	public static ControladorAppVideo getUnicaInstancia() {
		if (unicaInstancia == null)
			unicaInstancia = new ControladorAppVideo();
		return unicaInstancia;
	}
	
	private void inicializarAdaptadores() {
		FactoriaDAO factoria = null;
		try {
			factoria = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
		} catch (DAOException e) {
			e.printStackTrace();
		}
		adaptadorUsuario = factoria.getUsuarioDAO();
		adaptadorVideo = factoria.getVideoDAO();
		adaptadorListaReproduccion = factoria.getListaReproduccionDAO();
		adpatadorEtiqueta = factoria.getEtiquetaDAO();
	}
	
	private void inicializarCatalogos() {
		catalogoUsuarios = CatalogoUsuarios.getUnicaInstancia();
		catalogoVideos = CatalogoVideos.getUnicaInstancia();
	}
	
	public void registrarUsuario(String usuario, String nombre,String contraseña, String email, String apellidos, Date fecha_nacimiento) {

		Usuario u = new Usuario(nombre, apellidos, email, usuario, contraseña, fecha_nacimiento);
		adaptadorUsuario.registrarUsuario(u);
		catalogoUsuarios.addUsuario(u);
	}
	
	public boolean exiteUsuario(String usuario) {
		return catalogoUsuarios.getUsuario(usuario) != null;
	}
	
	public boolean loginUsuario (String usuario, String contraseña) {
		
		Usuario u = catalogoUsuarios.getUsuario(usuario);
		if (u != null && u.getContraseña().equals(contraseña)) {
			usuarioActual = u;
			return true;
		}
		return false;
	}
	
	public boolean usuarioPremium() {
		
		if (usuarioActual.isPremium()) {
			usuarioActual.setPremium(false);
			adaptadorUsuario.modificarUsuario(usuarioActual);
			return true;
		}
		usuarioActual.setPremium(true);
		adaptadorUsuario.modificarUsuario(usuarioActual);
		return false;
	}
	
	public List<Video> buscarVideos(String tituloVideo){
		return catalogoVideos.realizarBusqueda(tituloVideo);
	}
	
	public void nuevaReproduccion(Video video) {
		video.aumentarNumReproducciones();
		adaptadorVideo.modificarVideo(video);
	}
	
	public List<Video> getListaReproduccion(String nombre){
		return catalogoUsuarios.getListaReproduccion(usuarioActual.getUsuario(), nombre);
	}
	
	public List<Video> getListaMasVistos(){
		return catalogoVideos.getMasVistos();
	}
	
	public List<Video> getListaRecientes(){
		return usuarioActual.getRecientes();
	}
	
	public void añadirNuevaLista(String lista) {
		
		ListaReproduccion listaRep = new ListaReproduccion(lista);
		usuarioActual.addListaRep(listaRep);
		adaptadorListaReproduccion.registrarListaRep(listaRep); /// Preguntar
		adaptadorUsuario.modificarUsuario(usuarioActual);
		
	}
	
	public boolean añadirVideoLista(String lista, Video v) {
		
		ListaReproduccion listaModificada = usuarioActual.añadirNuevoVideo(lista, v);
		if (listaModificada == null)
		{
			return false;
		}
		adaptadorListaReproduccion.ModificarListaReproduccion(listaModificada);
		return true;
	}
	
	public void eliminarVideoLista(String lista, String titulo, String url) {
		
		ListaReproduccion listaModificada = usuarioActual.eliminarVideoLista(lista, new Video(url,titulo));
		adaptadorListaReproduccion.ModificarListaReproduccion(listaModificada);
	}
	
	public List<ListaReproduccion> getListasReproduccion(){
		return usuarioActual.getListasVideos();
	}
	
	public void añadirVideoReciente(Video video) {
		usuarioActual.nuevoReciente(video);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}
	
	public void modificarRecientes(List<Video> recientes) {
		usuarioActual.modificarListaRecientes(recientes);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}
	
	public boolean añadirEtiqueta (Video v, String etiqueta) {
		
		Etiqueta e = new Etiqueta(etiqueta);
		adpatadorEtiqueta.registrarEtiqueta(e);
		if (v.addEtiqueta(e)) {
			adaptadorVideo.modificarVideo(v);
			return true;
		}
		adpatadorEtiqueta.borrarEtiqueta(e);
		return false;
	}
}
