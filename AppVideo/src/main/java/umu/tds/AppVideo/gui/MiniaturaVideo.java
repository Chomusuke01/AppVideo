package umu.tds.AppVideo.gui;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;

public class MiniaturaVideo extends JPanel{
	
	/**
	 * 
	 */
	private int id;
	private JPanel panelMiniatura;
	private JPanel panelTitulo;
	private static final long serialVersionUID = 1L;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
//
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
	
	public MiniaturaVideo(String titulo, String url, int id) {
		
		this.id = id;
		this.setMaximumSize(new Dimension (300,240));
		this.setMinimumSize(new Dimension (300,240));
		this.setPreferredSize(new Dimension (300,240));
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
}