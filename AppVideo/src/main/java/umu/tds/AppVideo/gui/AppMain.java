package umu.tds.AppVideo.gui;
import java.awt.EventQueue;

import tds.video.VideoWeb;

public class AppMain {

	public static VideoWeb videoWeb;
	
	public static void main(final String[] args){
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					videoWeb = new VideoWeb();
					LoginView ventana = new LoginView();
					ventana.mostrarVentana();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
}
