package umu.tds.AppVideo.persistencia;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.AppVideo.modelo.ListaReproduccion;
import umu.tds.AppVideo.modelo.Usuario;
import umu.tds.AppVideo.modelo.Video;

public class AdaptadorUsuarioTDS implements IAdaptadorUsuarioDAO{
	
	private static final String CODIGO = "codigo";
	private static final String USUARIO = "usuario";
	private static final String NOMBRE = "nombre";
	private static final String APELLIDOS = "apellidos";
	private static final String FECHA_NACIMIENTO = "fecha_nacimiento";
	private static final String NICK = "nick";
	private static final String CONTRASEÑA = "contraseña";
	private static final String EMAIL = "email";
	private static final String PREMIUM = "premium";
	private static final String LISTAS_REPRODUCCION = "listas_reproduccion";
	private static final String RECIENTES = "recientes";
	
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorUsuarioTDS unicaInstancia = null;
	private SimpleDateFormat dateFormat;
	
	public static AdaptadorUsuarioTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorUsuarioTDS();
		else
			return unicaInstancia;
	}
	
	private AdaptadorUsuarioTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
		dateFormat = new SimpleDateFormat("dd/MM/yy");
	}
	
	
	@Override
	public void registrarUsuario(Usuario u) {
		Entidad eUsuario = null;

		// Si la entidad esta registrada no la registra de nuevo
		try {
			eUsuario = servPersistencia.recuperarEntidad(u.getCodigo());
		} catch (NullPointerException e) {}
		if (eUsuario != null) return;

		// registrar primero los atributos que son objetos
		
		AdaptadorVideoTDS adaptadorVideo = AdaptadorVideoTDS.getUnicaInstancia();
		AdaptadorListaReproduccionTDS adaptadorListaRep = AdaptadorListaReproduccionTDS.getUnicaInstancia();
		
		u.getRecientes().stream().forEach(video -> adaptadorVideo.addVideo(video));
		
		u.getListasVideos().stream().forEach(listaRep -> adaptadorListaRep.registrarListaRep(listaRep));
		
		
		eUsuario = new Entidad();
		eUsuario.setNombre(USUARIO);
		
		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad(NOMBRE, u.getNombre()), new Propiedad(APELLIDOS, u.getApellidos()), 
						new Propiedad (FECHA_NACIMIENTO,dateFormat.format(u.getFechaNacimiento())), new Propiedad (NICK,u.getUsuario()), new Propiedad(CONTRASEÑA,u.getContraseña()), 
						new Propiedad(EMAIL,u.getEmail()), new Propiedad(PREMIUM,String.valueOf(u.isPremium())), 
						new Propiedad (LISTAS_REPRODUCCION, obtenerCodigosListaRep(u.getListasVideos())), 
						new Propiedad(RECIENTES, obtenerCodigosVideosRecientes(u.getRecientes())))));

		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		u.setCodigo(eUsuario.getId());
		
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		
		return servPersistencia.recuperarEntidades(USUARIO).stream()
				.map(u -> recuperarUsuario(u.getId())).collect(Collectors.toList());
	}

	@Override
	public Usuario recuperarUsuario(int codigo) {
		
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
		return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		Entidad eUsuario = servPersistencia.recuperarEntidad(codigo);
		
		String usuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, NICK);
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, NOMBRE);
		String contraseña = servPersistencia.recuperarPropiedadEntidad(eUsuario, CONTRASEÑA);
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, APELLIDOS);
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, EMAIL);
		boolean premium = Boolean.valueOf(servPersistencia.recuperarPropiedadEntidad(eUsuario, PREMIUM));
		Date fecha_nacimiento = null;
		
		try {
			fecha_nacimiento = dateFormat.parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, FECHA_NACIMIENTO));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Usuario u = new Usuario(nombre, apellidos, email, usuario, contraseña, fecha_nacimiento);
		
		u.setCodigo(eUsuario.getId());
		u.setPremium(premium);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, u);
		
		obtenerListasRepDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eUsuario, LISTAS_REPRODUCCION)).stream().forEach(lista -> u.addListaRep(lista));
		obtenerRecientesDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eUsuario, RECIENTES)).stream().forEach(v -> u.addReciente(v));
		
		return u;
	}

	@Override
	public Usuario findUsuario(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeUsuario(Usuario u) {
		
		Entidad eUsuario;
		AdaptadorListaReproduccionTDS adaptadorListaRep = AdaptadorListaReproduccionTDS.getUnicaInstancia();
		
		u.getListasVideos().stream().forEach(lista -> adaptadorListaRep.borrarListaReproduccion(lista));
		
		eUsuario = servPersistencia.recuperarEntidad(u.getCodigo());
		servPersistencia.borrarEntidad(eUsuario);
	}
	
	
	@Override
	public void modificarUsuario(Usuario u) {
		Entidad eUsuario = servPersistencia.recuperarEntidad(u.getCodigo());

		for (Propiedad prop : eUsuario.getPropiedades()) {
			if (prop.getNombre().equals(CODIGO)) {
				prop.setValor(String.valueOf(u.getCodigo()));
			} else if (prop.getNombre().equals(NICK)) {
				prop.setValor(u.getUsuario());
			} else if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(u.getNombre());
			} else if (prop.getNombre().equals(APELLIDOS)) {
				prop.setValor(u.getApellidos());
			} else if (prop.getNombre().equals(EMAIL)) {
				prop.setValor(u.getEmail());
			} else if (prop.getNombre().equals(CONTRASEÑA)) {
				prop.setValor(u.getContraseña());
			} else if (prop.getNombre().equals(FECHA_NACIMIENTO)) {
				prop.setValor(dateFormat.format(u.getFechaNacimiento()));
			} else if (prop.getNombre().equals(LISTAS_REPRODUCCION)) {
				prop.setValor(obtenerCodigosListaRep(u.getListasVideos()));
			} else if (prop.getNombre().equals(RECIENTES)) {
				prop.setValor(obtenerCodigosVideosRecientes(u.getRecientes()));
			} else if (prop.getNombre().equals(PREMIUM)) {
				prop.setValor(String.valueOf(u.isPremium()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}
	
	//----------------------Funciones auxiliares-----------------------//

	private String obtenerCodigosListaRep(List<ListaReproduccion> listaRep) {
		
		String aux = "";
		
		for(ListaReproduccion list : listaRep) {
			aux += list.getCodigo() + " ";
		}
		
		return aux.trim();
	}
	
	private String obtenerCodigosVideosRecientes (List <Video> videos) {
		
		String aux = "";
		
		for (Video v : videos) {
			aux += v.getCodigo() + " ";
		}
		
		return aux.trim();
	}
	
	private List<Video> obtenerRecientesDesdeCodigo(String videos) {
		
		List<Video> recientes = new LinkedList<Video>();
		StringTokenizer strTok = new StringTokenizer(videos, " ");
		AdaptadorVideoTDS adaptadorVideo = AdaptadorVideoTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			recientes.add(adaptadorVideo.recuperarVideo(Integer.valueOf((String) strTok.nextElement())));
		}
		
		return recientes;
	}
	
	private List<ListaReproduccion> obtenerListasRepDesdeCodigo(String listas){
		
		List<ListaReproduccion> listaRep = new LinkedList<ListaReproduccion>();
		StringTokenizer strTok = new StringTokenizer(listas, " ");
		AdaptadorListaReproduccionTDS adaptadorListasRep = AdaptadorListaReproduccionTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			listaRep.add(adaptadorListasRep.recuperarListaReproduccion(Integer.valueOf((String) strTok.nextElement())));
		}
		
		return listaRep;
	}

}
