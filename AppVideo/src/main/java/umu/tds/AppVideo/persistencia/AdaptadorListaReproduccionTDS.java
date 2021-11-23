package umu.tds.AppVideo.persistencia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

import beans.Entidad;
import beans.Propiedad;
import tds.driver.FactoriaServicioPersistencia;
import tds.driver.ServicioPersistencia;
import umu.tds.AppVideo.modelo.ListaReproduccion;
import umu.tds.AppVideo.modelo.Video;

public class AdaptadorListaReproduccionTDS implements IAdaptadorListaReproduccionDAO {
	
	private static ServicioPersistencia servPersistencia;
	private static AdaptadorListaReproduccionTDS unicaInstancia = null;
	private static final String CODIGO = "codigo";
	private static final String LISTA_REPRODUCCION = "lista_reproduccion";
	private static final String NOMBRE = "nombre";
	private static final String VIDEOS = "videos";
	
	public static AdaptadorListaReproduccionTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorListaReproduccionTDS();
		else
			return unicaInstancia;
	}

	private AdaptadorListaReproduccionTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	
	@Override
	public void registrarListaRep(ListaReproduccion listaRep) {
		
		Entidad eListaRep = null;
		
		try {
			eListaRep = servPersistencia.recuperarEntidad(listaRep.getCodigo());
		} catch (NullPointerException e) {}
		if (eListaRep != null) return;
		
		AdaptadorVideoTDS adaptadorVideo = AdaptadorVideoTDS.getUnicaInstancia();
		
		listaRep.getVideos().stream().forEach(v -> adaptadorVideo.addVideo(v));
		
		eListaRep = new Entidad();
		eListaRep.setNombre(LISTA_REPRODUCCION);
		eListaRep.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad(NOMBRE,listaRep.getNombre()), new Propiedad(VIDEOS,obtenerCodigosVideos(listaRep.getVideos())))));
		
		eListaRep = servPersistencia.registrarEntidad(eListaRep);
		listaRep.setCodigo(eListaRep.getId());
	}

	
	
	@Override
	public void borrarListaReproduccion(ListaReproduccion listaRep) {
		
		Entidad eListaRep = servPersistencia.recuperarEntidad(listaRep.getCodigo());
		servPersistencia.borrarEntidad(eListaRep);

	}

	@Override
	public ListaReproduccion recuperarListaReproduccion(int codigo) {
		
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (ListaReproduccion) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		Entidad eListaRep; 
		String nombre;
		
		eListaRep = servPersistencia.recuperarEntidad(codigo);
		
		nombre = servPersistencia.recuperarPropiedadEntidad(eListaRep, NOMBRE);
		
		ListaReproduccion listaRep = new ListaReproduccion(nombre);
		listaRep.setCodigo(codigo); // ESTA LINEA DE MIERDA ME HA HECHO PERDER TODA LA MAÑANA 
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, listaRep);
		
		obtenerVideosDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eListaRep, VIDEOS)).stream().forEach(v -> listaRep.añadirVideo(v));
		
		return listaRep;
	}

	@Override
	public List<ListaReproduccion> recuperarTodasListas() {
		
		return servPersistencia.recuperarEntidades(LISTA_REPRODUCCION).stream()
				.map(listaRep -> recuperarListaReproduccion(listaRep.getId())).collect(Collectors.toList());
	}
	
	@Override
	public void ModificarListaReproduccion(ListaReproduccion lista) {
		Entidad eListaRep = servPersistencia.recuperarEntidad(lista.getCodigo());
		
		for (Propiedad prop : eListaRep.getPropiedades()) {
			if (prop.getNombre().equals(CODIGO)) {
				prop.setValor(String.valueOf(lista.getCodigo()));
			}else if (prop.getNombre().equals(NOMBRE)) {
				prop.setValor(lista.getNombre());
			}else if (prop.getNombre().equals(VIDEOS)) {
				prop.setValor(obtenerCodigosVideos(lista.getVideos()));
			}
			servPersistencia.modificarPropiedad(prop);
		}
	}

	//------------------Funciones auxiliares----------------//
	
	private String obtenerCodigosVideos(List<Video> videos) {
		
		String aux = "";
		
		for(Video v : videos) {
			aux += v.getCodigo() + " ";
		}
		
		return aux.trim();
		
	}
	
	private List<Video> obtenerVideosDesdeCodigos(String videos){
		
		List<Video> listaVideos = new LinkedList<Video>();
		StringTokenizer strTok = new StringTokenizer(videos, " ");
		AdaptadorVideoTDS adaptadorVideo = AdaptadorVideoTDS.getUnicaInstancia();
		
		while (strTok.hasMoreTokens()) {
			listaVideos.add(adaptadorVideo.recuperarVideo(Integer.valueOf((String)strTok.nextElement())));
		}
		
		return listaVideos;
	}


}
