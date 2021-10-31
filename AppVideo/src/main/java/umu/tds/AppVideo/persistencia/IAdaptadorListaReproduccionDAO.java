package umu.tds.AppVideo.persistencia;

import java.util.List;

import umu.tds.AppVideo.modelo.ListaReproduccion;


public interface IAdaptadorListaReproduccionDAO {
	public void registrarListaRep (ListaReproduccion listaRep);
	public void borrarListaReproduccion(ListaReproduccion listaRep);
	public ListaReproduccion recuperarListaReproduccion (int codigo);
	public List<ListaReproduccion> recuperarTodasListas();
	
}
