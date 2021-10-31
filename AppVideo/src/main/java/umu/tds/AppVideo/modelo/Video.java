package umu.tds.AppVideo.modelo;

import java.util.LinkedList;
import java.util.List;

public class Video {
	private String url;
	private int codigo;
	private String titulo;
	private int numReproducciones;
	private LinkedList<Etiqueta> etiquetas;
	
	
	public Video(String url, String titulo) {
		this(url,titulo,0); //TODO NO SE ....
	}

	public Video(String url, String titulo, int numReproducciones) {
		this.url = url;
		this.titulo = titulo;
		etiquetas = new LinkedList<Etiqueta>(); //TODO Un video tiene al menos una etiqueta, pero no se que etiqueta poner.
		this.numReproducciones = numReproducciones;
		codigo = 0;
	}

	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public List<Etiqueta> getEtiquetas() {
		return new LinkedList<Etiqueta>(etiquetas);
	}

	//TODO Seguramente se tenga que tratar el caso de que las etiquetas esten repetidas y que las etiquetas esten disponibles.
	public void addEtiqueta (Etiqueta e) {
		etiquetas.add(e);
	}
	
	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public int getNumReproducciones() {
		return numReproducciones;
	}


	public void aumentarNumReproducciones() {
		numReproducciones += 1;
	}
	
}
