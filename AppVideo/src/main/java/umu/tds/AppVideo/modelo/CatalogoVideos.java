package umu.tds.AppVideo.modelo;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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
	
	public List<Video> realizarBusqueda(String tituloVideo){
		
		return videos.values().stream()
				.filter(v -> v.getTitulo().contains(tituloVideo))
				.collect(Collectors.toList());
	}
	
	public List<Video> getMasVistos(){
		int i = 0;
		List<Video> masvistos = new LinkedList<Video>();
		
		List<Entry<String, Video>> list = new LinkedList<>(videos.entrySet());
	    Collections.sort(list, new Comparator<Object>() {
	       @SuppressWarnings("unchecked")
		public int compare(Object o1, Object o2) {
	            return ((Comparable<Integer>) ((Map.Entry<String, Video>) (o1)).getValue().getNumReproducciones()).compareTo(((Map.Entry<String, Video>) (o2)).getValue().getNumReproducciones());
	        }
	    });
	    Map<String, Video> result = new LinkedHashMap<>();
	    for (Iterator<Entry<String, Video>> it = list.iterator(); it.hasNext();) {
	        Map.Entry<String, Video> entry = (Map.Entry<String, Video>) it.next();
	        result.put(entry.getKey(), entry.getValue());
	    }
	    
	    for (Entry<String, Video> entry : result.entrySet()) {
	    	if(i >= 10) {
	    		break;
	    	}
			masvistos.add(entry.getValue());
			i++;
		}
	    
	    return masvistos;
	}
	
}
