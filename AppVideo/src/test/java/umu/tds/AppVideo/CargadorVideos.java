package umu.tds.AppVideo;

import umu.tds.AppVideo.modelo.Etiqueta;
import umu.tds.AppVideo.modelo.Video;
import umu.tds.AppVideo.persistencia.AdaptadorVideoTDS;

//Clase provisional para cargar los videos y hacer pruebas, mientras se termina el componente. NO USAR!!!
public class CargadorVideos {

	
	public static void main(String[] args) {
		
		AdaptadorVideoTDS.getUnicaInstancia().addVideo(new Video("https://www.youtube.com/watch?v=LdoGVZTfsh0","swinga it",new Etiqueta("infantil"),new Etiqueta("comedia")));
		AdaptadorVideoTDS.getUnicaInstancia().addVideo(new Video("https://www.youtube.com/watch?v=zn0ijtiP75c","aaara",new Etiqueta("comedia"), new Etiqueta("romance")));
		AdaptadorVideoTDS.getUnicaInstancia().addVideo(new Video("https://www.youtube.com/watch?v=hJeyuF8nGzs","harahare ya",new Etiqueta("romance")));
		
		AdaptadorVideoTDS.getUnicaInstancia().addVideo(new Video("https://www.youtube.com/watch?v=nbc7S3hWlgk","lovefool a"));
		AdaptadorVideoTDS.getUnicaInstancia().addVideo(new Video("https://www.youtube.com/watch?v=3wV3PhJFvg8","gonna go far"));
		AdaptadorVideoTDS.getUnicaInstancia().addVideo(new Video("https://www.youtube.com/watch?v=5JA07af-_Qs","bella y la bestia"));
		
		System.out.println("Videos cargados en la BD");
		
		
	}
}
