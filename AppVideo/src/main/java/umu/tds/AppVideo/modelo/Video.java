package umu.tds.AppVideo.modelo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Video {
	private String url;
	private int codigo;
	private String titulo;
	private int numReproducciones;
	private LinkedList<Etiqueta> etiquetas;
	
	

	public Video(String url, String titulo, Etiqueta...etiquetas ) {
		this.url = url;
		this.titulo = titulo;
		this.etiquetas = new LinkedList<Etiqueta>(); //TODO Un video tiene al menos una etiqueta, pero no se que etiqueta poner.
		
		Arrays.asList(etiquetas).stream()
			.forEach(e -> this.etiquetas.add(e));
		
		this.numReproducciones = 0;
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


	public void setNumReproducciones(int numReproducciones) {
		this.numReproducciones = numReproducciones;
	}

	public void aumentarNumReproducciones() {
		numReproducciones += 1;
	}
	
	public List<String> getNombreEtiquetas(){
		return etiquetas.stream().map(e -> e.getNombre()).collect(Collectors.toList());
	}
	
}
