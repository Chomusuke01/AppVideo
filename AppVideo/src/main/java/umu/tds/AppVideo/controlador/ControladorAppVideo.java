package umu.tds.AppVideo.controlador;


import java.io.FileNotFoundException;
import java.util.Date;
import java.util.EventObject;
import java.util.List;

import com.itextpdf.text.DocumentException;

import umu.tds.AppVideo.gui.PdfGenerator;
import umu.tds.AppVideo.modelo.CatalogoUsuarios;
import umu.tds.AppVideo.modelo.CatalogoVideos;
import umu.tds.AppVideo.modelo.Etiqueta;
import umu.tds.AppVideo.modelo.FiltroVideo;
import umu.tds.AppVideo.modelo.ListaReproduccion;
import umu.tds.AppVideo.modelo.NoFiltro;
import umu.tds.AppVideo.modelo.Usuario;
import umu.tds.AppVideo.modelo.Video;
import umu.tds.AppVideo.persistencia.DAOException;
import umu.tds.AppVideo.persistencia.FactoriaDAO;
import umu.tds.AppVideo.persistencia.IAdaptadorEtiquetaDAO;
import umu.tds.AppVideo.persistencia.IAdaptadorListaReproduccionDAO;
import umu.tds.AppVideo.persistencia.IAdaptadorUsuarioDAO;
import umu.tds.AppVideo.persistencia.IAdaptadorVideoDAO;
import umu.tds.componente.ComponenteCargadorVideos;
import umu.tds.componente.VideosEvent;
import umu.tds.componente.VideosListener;

public class ControladorAppVideo implements VideosListener{
	
	private static ControladorAppVideo unicaInstancia;

	private IAdaptadorUsuarioDAO adaptadorUsuario;
	private IAdaptadorVideoDAO adaptadorVideo;
	private IAdaptadorListaReproduccionDAO adaptadorListaReproduccion;
	private IAdaptadorEtiquetaDAO adpatadorEtiqueta;

	private CatalogoUsuarios catalogoUsuarios;
	private CatalogoVideos catalogoVideos;
	private Usuario usuarioActual;
	private static ComponenteCargadorVideos cv;

	private ControladorAppVideo() {
		inicializarAdaptadores(); 
		inicializarCatalogos();
	}
	
	public static ControladorAppVideo getUnicaInstancia() {
		if (unicaInstancia == null) {
			unicaInstancia = new ControladorAppVideo();
			cv = new ComponenteCargadorVideos();
			cv.addVideosListener(unicaInstancia);
		}
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
			NoFiltro f = new NoFiltro();
			usuarioActual.setFiltroActual(f.getClass().getName());
			usuarioActual.cambiarFiltro(f);
			adaptadorUsuario.modificarUsuario(usuarioActual);
			return true;
		}
		usuarioActual.setPremium(true);
		adaptadorUsuario.modificarUsuario(usuarioActual);
		return false;
	}
	
	public boolean isUsuarioPremium() {
		return usuarioActual.isPremium();
	}
	
	public List<Video> buscarVideos(String tituloVideo, List<String> etiquetas){
		return usuarioActual.filtrarVideos(catalogoVideos.realizarBusqueda(tituloVideo,etiquetas));
	}
	
	public void nuevaReproduccion(Video video) {
		video.aumentarNumReproducciones();
		adaptadorVideo.modificarVideo(video);
	}
	
	public ListaReproduccion getListaReproduccion(String nombre){
		
		return usuarioActual.getListaRep(nombre);
	}
	
	public List<Video> getListaMasVistos(){
		return catalogoVideos.getMasVistos();
	}
	
	public List<Video> getListaRecientes(){
		return usuarioActual.getRecientes();
	}
	
	public ListaReproduccion  añadirNuevaLista(String lista) {
		
		ListaReproduccion listaRep = new ListaReproduccion(lista);
		usuarioActual.addListaRep(listaRep);
		adaptadorListaReproduccion.registrarListaRep(listaRep); /// Preguntar
		adaptadorUsuario.modificarUsuario(usuarioActual);
		return listaRep;
		
	}
	
	public void eliminarLista(ListaReproduccion lista) {
		usuarioActual.eliminarListaRep(lista);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}
	
	public boolean añadirVideoLista(ListaReproduccion lista, Video v) {
		
		if (lista.añadirVideo(v)) {
			adaptadorListaReproduccion.ModificarListaReproduccion(lista);
			return true;
		}
		
		return false;
	}
	
	public void eliminarVideoLista(ListaReproduccion lista, Video video) {
		
		lista.eliminarVideo(video);
		adaptadorListaReproduccion.ModificarListaReproduccion(lista);
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
	
	public List<String> getEtiquetasVideos(){
		return catalogoVideos.getEtiquetasVideos();
	}
	
	public boolean generarPDF() {
		
		try {
			PdfGenerator.generarPDF(usuarioActual.getListasVideos(), usuarioActual.getUsuario());
		} catch (FileNotFoundException | DocumentException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public void cambiarFiltro(FiltroVideo filtro) {
		usuarioActual.setFiltroActual(filtro.getClass().getName());
		usuarioActual.cambiarFiltro(filtro);
		adaptadorUsuario.modificarUsuario(usuarioActual);
	}

	@Override
	public void nuevosVideos(EventObject e) {
		if(e instanceof VideosEvent) {
			VideosEvent evento = (VideosEvent)e;
			
			for (umu.tds.componente.Video v : evento.getVideos().getVideo()) {
//				System.out.println(v.getTitulo());
				if(catalogoVideos.getVideo(v.getURL()) == null) {
					Video vid = new Video(v.getURL(), v.getTitulo(), parseEtiquetas(v.getEtiqueta()));
					catalogoVideos.addVideo(vid);
					adaptadorVideo.addVideo(vid);
				}
			}
		}
	}
	
	
	public void cargarVideos(String ficheroXML){
		cv.setArchivoVideos(ficheroXML);
	}
	
	private Etiqueta [] parseEtiquetas(List<String> etiquetasAnt) {
		Etiqueta[] etiquetas = new Etiqueta[etiquetasAnt.size()];
		
		int i = 0;
		for (String etiqueta : etiquetasAnt) {
			etiquetas[i++] = new Etiqueta(etiqueta);
		}
		
		return etiquetas;
	}
	
	
}
