package umu.tds.AppVideo.modelo;

public class Etiqueta {
	private String nombre;
	private int codigo; 

	public Etiqueta(String nombre) {
		this.nombre = nombre;
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
	
}
