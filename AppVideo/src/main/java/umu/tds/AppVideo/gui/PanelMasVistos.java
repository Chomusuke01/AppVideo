package umu.tds.AppVideo.gui;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import umu.tds.AppVideo.Lanzador.AppMain;
import umu.tds.AppVideo.controlador.ControladorAppVideo;
import umu.tds.AppVideo.modelo.Video;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.stream.Collectors;


public class PanelMasVistos extends JPanel {
	
	
	private static final int ANCHO_CELDA = 150;
	private static final int ALTO_CELDA = 120;
	private static final int ALTO_SCROLL = 550;
	private static final int ANCHO_SCROLL = 220;
	private static final int ALTO_PANEL = 620;
	private static final int ANCHO_PANEL = 970;
	private static final long serialVersionUID = 1L;
	private JPanel panel_oeste;
	private JPanel panel_centro;
	private List<Video> listaMasVistos;
	private ListaVideos listaRep;
	private PanelReproductor reproductor;
	private JPanel panelVacio;

	public PanelMasVistos() {
		setPreferredSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));
		setMinimumSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));
		setMaximumSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));
		setLayout(new BorderLayout(0, 0));
		
		panel_oeste = new JPanel();
		add(panel_oeste, BorderLayout.WEST);
		
		panel_centro = new JPanel();
		add(panel_centro, BorderLayout.CENTER);
		panel_centro.setLayout(new CardLayout(0, 0));
		
		listaRep = new ListaVideos(new DefaultListModel<MiniaturaVideo>(),ALTO_CELDA,ANCHO_CELDA);
		JScrollPane scrollLista=new JScrollPane(listaRep);
		crearEventoRaton(listaRep);
		
		scrollLista.setMinimumSize(new Dimension(ANCHO_SCROLL,ALTO_SCROLL));
		scrollLista.setPreferredSize(new Dimension(ANCHO_SCROLL,ALTO_SCROLL));
		scrollLista.setMaximumSize(new Dimension(ANCHO_SCROLL,ALTO_SCROLL));
		
		scrollLista.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_oeste.add(scrollLista);
		
		reproductor = new PanelReproductor();
		panelVacio = new JPanel();
		
		panel_centro.add(panelVacio,"vacio");
		panel_centro.add(reproductor,"reproductor");
	}
	
	public void mostrarTop10() {
		CardLayout cl = ((CardLayout) panel_centro.getLayout());
		cl.show(panel_centro, "vacio");
		listaMasVistos = ControladorAppVideo.getUnicaInstancia().getListaMasVistos();
		listaRep.reiniciar();
		listaRep.añadirElementos(listaMasVistos.stream().map(v -> new MiniaturaVideo(v.getTitulo(),v.getUrl(),0,ANCHO_CELDA,ALTO_CELDA)).collect(Collectors.toList()));
	}
	
	private void crearEventoRaton(ListaVideos lista) {
		
		lista.addMouseListener(new MouseAdapter() {
			public void mouseClicked (MouseEvent e) {
				if (e.getClickCount() == 2) {
					int indice = lista.getSelectedIndex();
					Video videoSel = listaMasVistos.get(indice);
					CardLayout cl = ((CardLayout) panel_centro.getLayout());
					cl.show(panel_centro, "reproductor");
					reproductor.reproducirVideo(AppMain.videoWeb, videoSel);
					ControladorAppVideo.getUnicaInstancia().añadirVideoReciente(videoSel);
					ControladorAppVideo.getUnicaInstancia().nuevaReproduccion(videoSel);
				}
			}
		});
	}

}
