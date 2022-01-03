package umu.tds.AppVideo;

import static org.junit.Assert.*;

import java.util.LinkedList;

import org.junit.Before;
import org.junit.Test;

import umu.tds.AppVideo.modelo.Etiqueta;
import umu.tds.AppVideo.modelo.Video;

public class VideoTest {

	private Video video;
	LinkedList<String> eti;
	
	
	@Before
	public void update() {
		video = new Video("http://","Video1", new Etiqueta("hola"));
		video.setNumReproducciones(8);
		eti = new LinkedList<>();
	}
	
	//Comprobar que se aumentan automaticamente el numero de rep
	@Test
	public void testAumentarNumReproducciones() {
		video.aumentarNumReproducciones();
		assertEquals(video.getNumReproducciones(),9);
	}

	// Comprobar que tiene una etiqueta en concreto
	@Test
	public void testContieneEtiquetas() {
		eti.add("hola");
		assertEquals(video.contieneEtiquetas(eti),true);
	}
	
	// Comprobar que tiene un etiqueta pero no otra nueva
	@Test
	public void testContieneEtiquetas2() {
		eti.add("NoEtiqueta");
		assertEquals(video.contieneEtiquetas(eti),false);
	}
	
	// Comprobar que tiene las dos etiquetas
	@Test
	public void testContieneEtiquetas3() {
		video.addEtiqueta(new Etiqueta("NoEtiqueta"));
		assertEquals(video.contieneEtiquetas(eti),true);
	}

}
