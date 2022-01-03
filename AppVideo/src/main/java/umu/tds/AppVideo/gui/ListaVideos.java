package umu.tds.AppVideo.gui;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;

// Clase para implementar una lista de videos (las miniaturas de los videos)
public class ListaVideos extends JList<MiniaturaVideo> {

	
	DefaultListModel<MiniaturaVideo> model;
	private static final long serialVersionUID = 1L;
	
	public ListaVideos (DefaultListModel<MiniaturaVideo> model, int CellHeight, int CellWidth) {
		this.setModel(model);
		this.setFixedCellHeight(120);
		this.setFixedCellWidth(150);
		this.setCellRenderer(new MiniaturaVideoListRenderer());
		this.model = model;
	}
	
	// Añade nuevas miniaturas
	public void añadirElementos (List<MiniaturaVideo> v) {
		
		for (MiniaturaVideo miniaturaVideo : v) {
			model.addElement(miniaturaVideo);
		}
		this.setModel(model);
	}
	
	// Añade miniaturas al modelo
	public void añadirElemento(MiniaturaVideo min) {
		model.addElement(min);
	}
	
	//Añades al principio
	public void añadirPrincipio(MiniaturaVideo min) {
		model.add(0, min);
	}
	
	// Vaciar una lista
	public void reiniciar () {
		model = new DefaultListModel<MiniaturaVideo>();
		this.setModel(model);
	}
	
	//Borrar un elemento de la lista.s
	public MiniaturaVideo eliminarElemento(int indice) {
		return model.remove(indice);
	}
}
