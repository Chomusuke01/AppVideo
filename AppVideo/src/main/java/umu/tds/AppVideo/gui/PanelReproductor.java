package umu.tds.AppVideo.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

import tds.video.VideoWeb;

import umu.tds.AppVideo.modelo.Video;

import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;

import java.awt.FlowLayout;
import java.awt.Color;

import javax.swing.border.BevelBorder;


public class PanelReproductor extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel panelNorte;
	private JPanel panelTitulo;
	private JLabel lblTitulo;
	private JPanel panelReproducciones;
	private JLabel lblReproducciones;
	private JPanel panelCentro;
	private JPanel panelVideoWeb;
	private JPanel panelEtiquetas;
	private JPanel panelNuevaEtiqueta;
	
	public PanelReproductor() {
		this.setMaximumSize(new Dimension(970,620));
		this.setMinimumSize(new Dimension(970,620));
		this.setPreferredSize(new Dimension(970,620));
		setLayout(new BorderLayout(0, 0));
		
		panelNorte = new JPanel();
		add(panelNorte, BorderLayout.NORTH);
		panelNorte.setLayout(new BorderLayout(0, 0));
		
		panelTitulo = new JPanel();
		panelNorte.add(panelTitulo, BorderLayout.NORTH);
		
		lblTitulo = new JLabel();
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 50));
		panelTitulo.add(lblTitulo);
		
		panelReproducciones = new JPanel();
		panelNorte.add(panelReproducciones, BorderLayout.SOUTH);
		
		lblReproducciones = new JLabel();
		lblReproducciones.setFont(new Font("Tahoma", Font.PLAIN, 25));
		panelReproducciones.add(lblReproducciones);
		
		panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		
		panelVideoWeb = new JPanel();
		panelCentro.add(panelVideoWeb, BorderLayout.NORTH);
		
		panelEtiquetas = new JPanel();
		panelCentro.add(panelEtiquetas, BorderLayout.CENTER);
		
		panelNuevaEtiqueta = new JPanel();
		panelCentro.add(panelNuevaEtiqueta, BorderLayout.SOUTH);
		panelEtiquetas.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
	}
	
	
	public void reproducirVideo(VideoWeb videoWeb, Video video) {
		panelVideoWeb.add(videoWeb);
		lblTitulo.setText(video.getTitulo());
		if (video.getNumReproducciones() == 1 ) {
			lblReproducciones.setText("Visto por : 1 usuario");
		}else {
			lblReproducciones.setText("Visto por : " + video.getNumReproducciones() + " usuarios");
		}
		panelEtiquetas.removeAll();
		panelEtiquetas.repaint();
		
		for (String etiqueta : video.getNombreEtiquetas()) {
			JLabel label = new JLabel (etiqueta);
			label.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
			label.setFont(new Font("Comic Sans MS", Font.PLAIN, 15));
			label.setBackground(new Color(92,253,204));
			label.setOpaque(true);
			panelEtiquetas.add(label);
		}
		videoWeb.playVideo(video.getUrl());
	}
}
