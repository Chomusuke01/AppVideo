package umu.tds.AppVideo.modelo;

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
	
	public List<Video> realizarBusqueda(String tituloVideo){
		
		return videos.values().stream()
				.filter(v -> v.getTitulo().contains(tituloVideo))
				.collect(Collectors.toList());
	}
}
