package umu.tds.AppVideo.gui;

import javax.swing.DefaultListModel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import umu.tds.AppVideo.controlador.ControladorAppVideo;
import umu.tds.AppVideo.modelo.Video;

import java.awt.Dimension;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.util.List;
import java.util.stream.Collectors;

public class PanelMasVistos extends JPanel {
	
	private static final int NUM_COLUMNAS_RESULTADO = 4;
	private JPanel panel_oeste;
	private JPanel panel_centro;
	private List<Video> listaMasVistos;
	private ListaVideos listaRep;
	private TablaBusqueda resultadoBusqueda;

	/**
	 * Create the panel.
	 */
	public PanelMasVistos() {
		setPreferredSize(new Dimension(970, 620));
		setMinimumSize(new Dimension(970, 620));
		setMaximumSize(new Dimension(970, 620));
		setLayout(new BorderLayout(0, 0));
		
		panel_oeste = new JPanel();
		add(panel_oeste, BorderLayout.WEST);
		panel_oeste.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		panel_centro = new JPanel();
		add(panel_centro, BorderLayout.CENTER);
		
		listaRep = new ListaVideos(new DefaultListModel<MiniaturaVideo>(),120,150);
		JScrollPane scrollLista=new JScrollPane(listaRep);
		
		scrollLista.setMinimumSize(new Dimension(220,615));
		scrollLista.setPreferredSize(new Dimension(220,615));
		scrollLista.setMaximumSize(new Dimension(220,615));
		
		scrollLista.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_oeste.add(scrollLista);
		
		resultadoBusqueda = new TablaBusqueda(MiniaturaVideo.class, new MiniaturaVideoTableRenderer(), 150, 120, NUM_COLUMNAS_RESULTADO);
		JScrollPane scrollBusqueda=new JScrollPane(resultadoBusqueda);
		scrollBusqueda.setMinimumSize(new Dimension(745,615));
		scrollBusqueda.setPreferredSize(new Dimension(745,615));
		scrollBusqueda.setMaximumSize(new Dimension(745,615));
		panel_centro.add(scrollBusqueda);
		
		listaMasVistos = ControladorAppVideo.getUnicaInstancia().getListaMasVistos();
		listaRep.reiniciar();
		listaRep.añadirElementos(listaMasVistos.stream().map(v -> new MiniaturaVideo(v.getTitulo(),v.getUrl(),0,150,120)).collect(Collectors.toList()));
	}

}
