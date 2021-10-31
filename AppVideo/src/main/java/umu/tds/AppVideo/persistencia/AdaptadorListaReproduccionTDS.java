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
		eListaRep.setNombre("listaReproduccion");
		eListaRep.setPropiedades(new ArrayList<Propiedad>(Arrays.asList(
				new Propiedad("nombre",listaRep.getNombre()), new Propiedad("videos",obtenerCodigosVideos(listaRep.getVideos())))));
		
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
		
		nombre = servPersistencia.recuperarPropiedadEntidad(eListaRep, "nombre");
		
		ListaReproduccion listaRep = new ListaReproduccion(nombre);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, listaRep);
		
		obtenerVideosDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eListaRep, "videos")).stream().forEach(v -> listaRep.añadirVideo(v));
		
		return listaRep;
	}

	@Override
	public List<ListaReproduccion> recuperarTodasListas() {
		
		return servPersistencia.recuperarEntidades("listaReproduccion").stream()
				.map(listaRep -> recuperarListaReproduccion(listaRep.getId())).collect(Collectors.toList());
	}

	//------------------Funciones auxiliares----------------//
	
	private String obtenerCodigosVideos(List<Video> videos) {
		
		String linea = "";
		
		for(Video v : videos) {
			linea += v.getCodigo() + " ";
		}
		
		return linea.trim();
		
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
