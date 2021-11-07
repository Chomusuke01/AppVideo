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
		eUsuario.setNombre("usuario");
		
		eUsuario.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad("nombre", u.getNombre()), new Propiedad("apellidos", u.getApellidos()), 
						new Propiedad ("fecha_nacimiento",dateFormat.format(u.getFechaNacimiento())), new Propiedad ("usuario",u.getUsuario()), new Propiedad("contraseña",u.getContraseña()), 
						new Propiedad("email",u.getEmail()), new Propiedad("premium",String.valueOf(u.isPremium())), 
						new Propiedad ("listasReproduccion", obtenerCodigosListaRep(u.getListasVideos())), 
						new Propiedad("recientes", obtenerCodigosVideosRecientes(u.getRecientes())))));

		eUsuario = servPersistencia.registrarEntidad(eUsuario);
		// asignar identificador unico
		// Se aprovecha el que genera el servicio de persistencia
		u.setCodigo(eUsuario.getId());
		
	}

	@Override
	public List<Usuario> recuperarTodosUsuarios() {
		
		return servPersistencia.recuperarEntidades("usuario").stream()
				.map(u -> recuperarUsuario(u.getId())).collect(Collectors.toList());
	}

	@Override
	public Usuario recuperarUsuario(int codigo) {
		
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
		return (Usuario) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		Entidad eUsuario = servPersistencia.recuperarEntidad(codigo);
		
		String usuario = servPersistencia.recuperarPropiedadEntidad(eUsuario, "usuario");
		String nombre = servPersistencia.recuperarPropiedadEntidad(eUsuario, "nombre");
		String contraseña = servPersistencia.recuperarPropiedadEntidad(eUsuario, "contraseña");
		String apellidos = servPersistencia.recuperarPropiedadEntidad(eUsuario, "apellidos");
		String email = servPersistencia.recuperarPropiedadEntidad(eUsuario, "email");
		boolean premium = Boolean.valueOf(servPersistencia.recuperarPropiedadEntidad(eUsuario, "premium"));
		Date fecha_nacimiento = null;
		
		try {
			fecha_nacimiento = dateFormat.parse(servPersistencia.recuperarPropiedadEntidad(eUsuario, "fecha_nacimiento"));
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		Usuario u = new Usuario(nombre, apellidos, email, usuario, contraseña, fecha_nacimiento);
		
		u.setCodigo(eUsuario.getId());
		u.setPremium(premium);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, u);
		
		obtenerListasRepDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eUsuario, "listasReproduccion")).stream().forEach(lista -> u.addListaRep(lista));
		obtenerRecientesDesdeCodigo(servPersistencia.recuperarPropiedadEntidad(eUsuario, "recientes")).stream().forEach(v -> u.addReciente(v));
		
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
			if (prop.getNombre().equals("codigo")) {
				prop.setValor(String.valueOf(u.getCodigo()));
			} else if (prop.getNombre().equals("usuario")) {
				prop.setValor(u.getUsuario());
			} else if (prop.getNombre().equals("nombre")) {
				prop.setValor(u.getNombre());
			} else if (prop.getNombre().equals("apellidos")) {
				prop.setValor(u.getApellidos());
			} else if (prop.getNombre().equals("email")) {
				prop.setValor(u.getEmail());
			} else if (prop.getNombre().equals("contraseña")) {
				prop.setValor(u.getContraseña());
			} else if (prop.getNombre().equals("fecha_nacimiento")) {
				prop.setValor(dateFormat.format(u.getFechaNacimiento()));
			} else if (prop.getNombre().equals("listasReproduccion")) {
				prop.setValor(obtenerCodigosListaRep(u.getListasVideos()));
			} else if (prop.getNombre().equals("recientes")) {
				prop.setValor(obtenerCodigosVideosRecientes(u.getRecientes()));
			} else if (prop.getNombre().equals("premium")) {
				prop.setValor(String.valueOf(u.isPremium()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}
	
	//----------------------Funciones auxiliares-----------------------//

	private String obtenerCodigosListaRep(List<ListaReproduccion> listaRep) {
		
		String lineas = "";
		
		for(ListaReproduccion list : listaRep) {
			lineas += list.getCodigo() + " ";
		}
		
		return lineas.trim();
	}
	
	private String obtenerCodigosVideosRecientes (List <Video> videos) {
		
		String lineas = "";
		
		for (Video v : videos) {
			lineas += v.getCodigo() + " ";
		}
		
		return lineas.trim();
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
