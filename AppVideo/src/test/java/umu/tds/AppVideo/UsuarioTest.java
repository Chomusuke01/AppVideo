package umu.tds.AppVideo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import umu.tds.AppVideo.modelo.Etiqueta;
import umu.tds.AppVideo.modelo.ListaReproduccion;
import umu.tds.AppVideo.modelo.Usuario;
import umu.tds.AppVideo.modelo.Video;

public class UsuarioTest {

	private Usuario usuario;
	private ListaReproduccion lista1;

	
	@Before
	public void setup() {
		usuario = new Usuario("juan", "perez", "juan@hola", "juan95", "1234", null);
		lista1 = new ListaReproduccion("miLista");
		
		
		lista1.añadirVideo(new Video("https1", "titulo1"));
		lista1.añadirVideo(new Video("http2", "titulo2", new Etiqueta("comedia")));
		lista1.añadirVideo(new Video("http3", "titulo3", new Etiqueta("comedia"), new Etiqueta("terror")));
		
		usuario.addListaRep(lista1);
	}
	
	// Recuperamos una lista que existe
	@Test
	public void testGetListaRep() {
		assertEquals(lista1,usuario.getListaRep(lista1.getNombre()));
	}
	
	// Recuperamos una lista que no existe
	@Test
	public void testGetListaRep2() {
		usuario.addListaRep(new ListaReproduccion("nueva")); //Añadimos una nueva lista para que tenga varias.
		assertEquals(usuario.getListaRep("NoExiste"),null);
	}
	
	// Recuperamos los videos de las listas
	@Test
	public void testGetVideosFromListas() {
		assertEquals(lista1.getVideos(), usuario.getVideosFromListas());
	}

	//Intentamos borrar una lista que no existe
	@Test
	public void testEliminarListaRep() {
		ListaReproduccion aux = new ListaReproduccion("NoExite");
		usuario.eliminarListaRep(aux);
		assertEquals(lista1.getVideos(), usuario.getVideosFromListas());
	}

}
