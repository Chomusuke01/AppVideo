package umu.tds.AppVideo.modelo;

import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class Video {
	private String url;
	private int codigo;
	private String titulo;
	private int numReproducciones;
	private HashSet<Etiqueta> etiquetas;
	
	
	public Video(String url, String titulo, Etiqueta...etiquetas ) {
		this.url = url;
		this.titulo = titulo;
		this.etiquetas = new HashSet<Etiqueta>(); 
		this.etiquetas.addAll(Arrays.asList(etiquetas));
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


	public boolean addEtiqueta (Etiqueta e) {
		return etiquetas.add(e);
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

	public boolean contieneEtiquetas (List<String> etiquetas) {
		
		List <String> etiquetasVideo = this.getNombreEtiquetas();
		
		for (String etiqueta : etiquetas) {
			if (!etiquetasVideo.contains(etiqueta)) {
				return false;
			}
		}
		return true;
	}
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((titulo == null) ? 0 : titulo.hashCode());
		result = prime * result + ((url == null) ? 0 : url.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Video other = (Video) obj;
		if (titulo == null) {
			if (other.titulo != null)
				return false;
		} else if (!titulo.equals(other.titulo))
			return false;
		if (url == null) {
			if (other.url != null)
				return false;
		} else if (!url.equals(other.url))
			return false;
		return true;
	}
	
}
