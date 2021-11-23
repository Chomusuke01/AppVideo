package umu.tds.AppVideo.gui;

import java.util.List;

import javax.swing.DefaultListModel;
import javax.swing.JList;


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

	public void añadirElementos (List<MiniaturaVideo> v) {
		
		for (MiniaturaVideo miniaturaVideo : v) {
			model.addElement(miniaturaVideo);
		}
		this.setModel(model);
	}
	
	public void añadirElemento(MiniaturaVideo min) {
		model.addElement(min);
	}
	
	public void reiniciar () {
		model = new DefaultListModel<MiniaturaVideo>();
	}
	
	public MiniaturaVideo eliminarElemento(int indice) {
		return model.remove(indice);
	}
}
