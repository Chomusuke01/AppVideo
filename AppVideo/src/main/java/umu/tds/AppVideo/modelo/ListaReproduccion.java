package umu.tds.AppVideo.modelo;

import java.util.LinkedList;
import java.util.List;

public class ListaReproduccion {
	private String nombre;
	private int codigo;
	private LinkedList<Video> videos;
	
	public ListaReproduccion(String nombre) {
		this.nombre = nombre;
		videos = new LinkedList<Video>();
		codigo = 0;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Video> getVideos() {
		return new LinkedList<Video>(videos);
	}
	
	public void añadirVideo(Video video) {
		videos.add(video);
	}
	
	
}
