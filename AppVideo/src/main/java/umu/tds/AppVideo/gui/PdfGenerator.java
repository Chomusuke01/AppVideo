package umu.tds.AppVideo.gui;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import umu.tds.AppVideo.modelo.ListaReproduccion;
import umu.tds.AppVideo.modelo.Video;

public class PdfGenerator {

	public static void generarPDF(List<ListaReproduccion> lista, String usuario, String ruta) throws DocumentException, FileNotFoundException {
		
		FileOutputStream archivo = new FileOutputStream(ruta + "\\MisListas.pdf");
		Document documento = new Document();
		PdfWriter.getInstance(documento, archivo);
		documento.open();
		
		
		Paragraph titulo = new Paragraph("Listas de reproducción del usuario: " + usuario, FontFactory.getFont("arial",25,Font.BOLD));
		titulo.setAlignment(Element.ALIGN_CENTER);
		documento.add(titulo);
		documento.add(new Paragraph(" "));
		documento.add(new Paragraph(" "));
		documento.add(new Paragraph(" "));
		
		for (ListaReproduccion listaRep : lista) {
			
			documento.add(new Paragraph(listaRep.getNombre(),FontFactory.getFont("arial",18,Font.BOLD)));
			
			for(Video video : listaRep.getVideos()) {
				documento.add(new Paragraph("     -    " + video.getTitulo() + "    Reproducciones: " + video.getNumReproducciones()));
			}
			documento.add(new Paragraph(" "));
			documento.add(new Paragraph(" "));
		}
		
		documento.close();
	}
}
