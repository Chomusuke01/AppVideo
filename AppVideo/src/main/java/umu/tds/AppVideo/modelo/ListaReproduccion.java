package umu.tds.AppVideo.modelo;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class ListaReproduccion {
	private String nombre;
	private int codigo;
	private HashSet<Video> videos;
	
	public ListaReproduccion(String nombre) {
		this.nombre = nombre;
		videos = new HashSet<Video>();
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
	
	public boolean añadirVideo(Video video) {
		return videos.add(video);
	}
	
	public void eliminarVideo(Video video) {
		videos.remove(video);
	}
	
	@Override
	public String toString() {
		return nombre;
	}
}
