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
import umu.tds.AppVideo.modelo.Etiqueta;
import umu.tds.AppVideo.modelo.Video;

public class AdaptadorVideoTDS implements IAdaptadorVideoDAO {

	private static ServicioPersistencia servPersistencia;
	private static AdaptadorVideoTDS unicaInstancia = null;
	
	
	public static AdaptadorVideoTDS getUnicaInstancia() { // patron singleton
		if (unicaInstancia == null)
			return new AdaptadorVideoTDS();
		else
			return unicaInstancia;
	}
	
	private AdaptadorVideoTDS() {
		servPersistencia = FactoriaServicioPersistencia.getInstance().getServicioPersistencia();
	}
	
	@Override
	public void addVideo(Video video) {
		Entidad eVideo = null;
		
		try {
			eVideo = servPersistencia.recuperarEntidad(video.getCodigo());
		} catch (NullPointerException e) {}
		if (eVideo != null)	return;
		
		
		AdaptadorEtiquetaTDS adaptadorLabel = AdaptadorEtiquetaTDS.getUnicaInstancia();
		video.getEtiquetas().stream().forEach(lb -> adaptadorLabel.registrarEtiqueta(lb)); // Puede explotar. AVISO ....
		
		
		eVideo = new Entidad();
		eVideo.setNombre("video");
		
		eVideo.setPropiedades(new ArrayList<Propiedad>(
				Arrays.asList(new Propiedad ("url",video.getUrl()), new Propiedad ("titulo",video.getTitulo()), 
						new Propiedad("numReproducciones",String.valueOf(video.getNumReproducciones())), new Propiedad ("etiquetas",obtenerCodigosEtiquetas(video.getEtiquetas())))));
		
		eVideo = servPersistencia.registrarEntidad(eVideo);
		video.setCodigo(eVideo.getId());
	}

	@Override
	public List<Video> recuperarTodosVideos() {
		
		return servPersistencia.recuperarEntidades("video").stream()
				.map(v -> recuperarVideo(v.getId())).collect(Collectors.toList()); // posible explosion
	}

	@Override
	public Video recuperarVideo(int codigo) {
		
		if (PoolDAO.getUnicaInstancia().contiene(codigo))
			return (Video) PoolDAO.getUnicaInstancia().getObjeto(codigo);
		
		Entidad eVideo = servPersistencia.recuperarEntidad(codigo);
		
		
		String url = servPersistencia.recuperarPropiedadEntidad(eVideo, "url");
		String titulo = servPersistencia.recuperarPropiedadEntidad(eVideo, "titulo");
		int numReproducciones = Integer.parseInt(servPersistencia.recuperarPropiedadEntidad(eVideo, "numReproducciones"));
		
		Video video = new Video(url, titulo,numReproducciones);
		video.setCodigo(codigo);
		
		PoolDAO.getUnicaInstancia().addObjeto(codigo, video); // Creo que no hace falta .
		
		obtenerEtiquetasDesdeCodigos(servPersistencia.recuperarPropiedadEntidad(eVideo, "etiquetas")).stream().forEach(lb -> video.addEtiqueta(lb)); //Puede explotar
	
		return video;
	}

	@Override
	public Video findVideo(Video video) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeVideo(Video video) {
		
		Entidad eVideo;
		eVideo = servPersistencia.recuperarEntidad(video.getCodigo());
		servPersistencia.borrarEntidad(eVideo);
	}
	
	// -------------------Funciones auxiliares-----------------------------//
	
	private String obtenerCodigosEtiquetas(List<Etiqueta> etiquetas) {
		
		String lineas = "";
		
		//TODO Seguramente se pueda hacer con un stream pero ahora no veo como, así que lo dejo provisional de esta manera.
		for (Etiqueta etiqueta : etiquetas) {
			lineas += etiqueta.getCodigo() + " ";
		}
		
		return lineas.trim();
		
	}

	private List<Etiqueta> obtenerEtiquetasDesdeCodigos(String lineas) {
		
		List<Etiqueta> lineasVenta = new LinkedList<Etiqueta>();
		StringTokenizer strTok = new StringTokenizer(lineas, " ");
		AdaptadorEtiquetaTDS adaptadorEtiqueta = AdaptadorEtiquetaTDS.getUnicaInstancia();
		while (strTok.hasMoreTokens()) {
			lineasVenta.add(adaptadorEtiqueta.recuperarEtiqueta(Integer.valueOf((String) strTok.nextElement())));
		}
		return lineasVenta;
	}

}
