package umu.tds.AppVideo.Lanzador;
import java.awt.EventQueue;

import tds.video.VideoWeb;
import umu.tds.AppVideo.gui.LoginView;

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
