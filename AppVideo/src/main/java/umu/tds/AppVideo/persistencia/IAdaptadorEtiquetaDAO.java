package umu.tds.AppVideo.persistencia;

import java.util.List;

import umu.tds.AppVideo.modelo.Etiqueta;


public interface IAdaptadorEtiquetaDAO {
	
	public void registrarEtiqueta (Etiqueta etiqueta);
	public void borrarEtiqueta(Etiqueta etiqueta);
	public Etiqueta recuperarEtiqueta (int codigo);
	public List<Etiqueta> recuperarTodasEtiquetas();
	
}
