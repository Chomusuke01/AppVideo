package umu.tds.AppVideo.modelo;


import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import umu.tds.AppVideo.persistencia.DAOException;
import umu.tds.AppVideo.persistencia.FactoriaDAO;
import umu.tds.AppVideo.persistencia.IAdaptadorVideoDAO;

public class CatalogoVideos {
	
	private Map<String, Video> videos;
	private static CatalogoVideos unicaInstancia = new CatalogoVideos();
	
	private FactoriaDAO dao;
	private IAdaptadorVideoDAO adaptadorVideo;
	
	private CatalogoVideos() {
		try {
			dao = FactoriaDAO.getInstancia(FactoriaDAO.DAO_TDS);
  			adaptadorVideo = dao.getVideoDAO();
  			videos = adaptadorVideo.recuperarTodosVideos().stream()
  					.collect(Collectors.toMap(v -> v.getUrl(), v -> v)); // Posible explosion del codigo.
  		} catch (DAOException eDAO) {
  			eDAO.printStackTrace();
  		}
	}
	
	public static CatalogoVideos getUnicaInstancia() {
		return unicaInstancia;
	}
	
	public Video getVideo(String url) {
		return videos.get(url);
	}
	
	public void addVideo(Video video) {
		videos.put(video.getUrl(), video);
	}
	
	public void removeViedo(Video video) {
		videos.remove(video.getUrl());
	}
	
	public List<Video> realizarBusqueda(String tituloVideo, List<String> etiquetas){
		
		if (etiquetas == null) {
			return videos.values().stream()
					.filter(v -> v.getTitulo().toLowerCase().contains(tituloVideo.toLowerCase()))
					.collect(Collectors.toList());
		}else if (tituloVideo == null) {
			return videos.values().stream()
					.filter(v -> v.contieneEtiquetas(etiquetas))
					.collect(Collectors.toList());
		}
		return videos.values().stream()
				.filter(v -> v.getTitulo().toLowerCase().contains(tituloVideo.toLowerCase()) && v.contieneEtiquetas(etiquetas))
				.collect(Collectors.toList());
	}
	
	public List<Video> getMasVistos(){
	    
		List<Video> masVistos = videos.values().stream().sorted(Comparator.comparing(Video::getNumReproducciones).reversed()).limit(10).collect(Collectors.toList());
	    return masVistos;
	}
	
	public List<String> getEtiquetasVideos(){
		
		HashSet<String> etiquetas = new HashSet<String>();
		
		for(Video v : videos.values()) {
			List<String> videoLabel = v.getNombreEtiquetas();
			
			if (videoLabel.size() > 0) {
				etiquetas.addAll(videoLabel);
			}
		}
		return new LinkedList<String>(etiquetas);
	}
}
