package umu.tds.AppVideo.gui;

import java.awt.Dimension;

import javax.swing.JPanel;

//import tds.video.VideoWeb;

public class PanelReproductor extends JPanel{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static VideoWeb videoWeb;
	
	public PanelReproductor() {
		this.setMaximumSize(new Dimension(970,620));
		this.setMinimumSize(new Dimension(970,620));
		this.setPreferredSize(new Dimension(970,620));

	}
	
	public void reproducir(String url) {
		this.add(AppMain.videoWeb);
		AppMain.videoWeb.playVideo(url);
	}
	
}
