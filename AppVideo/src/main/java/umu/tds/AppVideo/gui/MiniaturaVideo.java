package umu.tds.AppVideo.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import umu.tds.AppVideo.Lanzador.AppMain;

import java.awt.BorderLayout;
import java.awt.Color;


// Clase para representar las miniaturas de los videos que aparecen en las lista y en los resultados de las busquedas
public class MiniaturaVideo extends JPanel{
	
	/**
	 * 
	 */
	private int id;
	private JPanel panelMiniatura;
	private JPanel panelTitulo;
	private String titulo;
	private String url;
	private static final long serialVersionUID = 1L;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	// Hay que redefinirlo por que tiene dos paneles para colorear 
	@Override
	public void setBackground(Color bg) {
		if (panelMiniatura != null ) {
			panelMiniatura.setBackground(bg);
		}
		
		if ( panelTitulo != null) {
			panelTitulo.setBackground(bg);
		}
		super.setBackground(bg);
	}
	
	//300,240
	public MiniaturaVideo(String titulo, String url, int id,int width, int height) {
		
		this.id = id;
		this.titulo = titulo;
		this.url = url;
		this.setMaximumSize(new Dimension (width,height));
		this.setMinimumSize(new Dimension (width,height));
		this.setPreferredSize(new Dimension (width,height));
		setLayout(new BorderLayout(0, 0));
		
		panelMiniatura = new JPanel();
		add(panelMiniatura, BorderLayout.CENTER);
		
		panelTitulo = new JPanel();
		add(panelTitulo, BorderLayout.SOUTH);
		
		JLabel lblTitulo = new JLabel();
		lblTitulo.setText(titulo);
		panelMiniatura.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		JLabel lblMiniatura = new JLabel();
		lblMiniatura.setIcon(AppMain.videoWeb.getThumb(url));
		panelMiniatura.add(lblMiniatura);
		panelTitulo.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		panelTitulo.add(lblTitulo);
	}

	public String getTitulo() {
		return titulo;
	}

	public String getUrl() {
		return url;
	}
	
}