package umu.tds.AppVideo.gui;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

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
	
	
	private static final long serialVersionUID = 1L;
	private JPanel panel_oeste;
	private JPanel panel_centro;
	private List<Video> listaMasVistos;
	private ListaVideos listaRep;
	private PanelReproductor reproductor;
	private JPanel panelVacio;

	public PanelMasVistos() {
		setPreferredSize(new Dimension(970, 620));
		setMinimumSize(new Dimension(970, 620));
		setMaximumSize(new Dimension(970, 620));
		setLayout(new BorderLayout(0, 0));
		
		panel_oeste = new JPanel();
		add(panel_oeste, BorderLayout.WEST);
		
		panel_centro = new JPanel();
		add(panel_centro, BorderLayout.CENTER);
		panel_centro.setLayout(new CardLayout(0, 0));
		
		listaRep = new ListaVideos(new DefaultListModel<MiniaturaVideo>(),120,150);
		JScrollPane scrollLista=new JScrollPane(listaRep);
		crearEventoRaton(listaRep);
		
		scrollLista.setMinimumSize(new Dimension(220,550));
		scrollLista.setPreferredSize(new Dimension(220,550));
		scrollLista.setMaximumSize(new Dimension(220,550));
		
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
		listaRep.añadirElementos(listaMasVistos.stream().map(v -> new MiniaturaVideo(v.getTitulo(),v.getUrl(),0,150,120)).collect(Collectors.toList()));
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
