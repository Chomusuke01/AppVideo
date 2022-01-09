package umu.tds.AppVideo.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import umu.tds.AppVideo.Lanzador.AppMain;
import umu.tds.AppVideo.controlador.ControladorAppVideo;
import umu.tds.AppVideo.modelo.ListaReproduccion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.CardLayout;

public class PanelMisListas extends JPanel {

	private static final int ALTO_SCROLL = 450;
	private static final int ANCHO_SCROLL = 220;
	private static final int ANCHO_CELDA = 150;
	private static final int ALTO_CELDA = 120;
	private static final int ALTO_PANEL = 620;
	private static final int ANCHO_PANEL = 970;
	private static final long serialVersionUID = 1L;
	private JComboBox<ListaReproduccion> comboBoxLista;
	private JPanel panel_oeste;
	private JPanel panel_seleccion;
	private JButton btnReproducir;
	private JPanel panel_resultados;
	private JPanel panel_centro;
	private ListaVideos listaRep;
	private PanelReproductor reproductor;
	private JPanel panelVacio;
	private ListaReproduccion listaActual;

	public PanelMisListas() {
		setPreferredSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));
		setMinimumSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));
		setMaximumSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));
		setLayout(new BorderLayout(0, 0));
		
		panel_oeste = new JPanel();
		add(panel_oeste, BorderLayout.WEST);
		panel_oeste.setLayout(new BorderLayout(0, 0));
		
		panel_seleccion = new JPanel();
		panel_oeste.add(panel_seleccion, BorderLayout.NORTH);
		GridBagLayout gbl_panel_seleccion = new GridBagLayout();
		gbl_panel_seleccion.columnWidths = new int[]{5, 0, 0, 46, 0, 0};
		gbl_panel_seleccion.rowHeights = new int[]{0, 0, 14, 0};
		gbl_panel_seleccion.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_seleccion.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_seleccion.setLayout(gbl_panel_seleccion);
		
		JLabel lblNewLabel_1 = new JLabel(" Seleccione la lista:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.WEST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panel_seleccion.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		comboBoxLista = new JComboBox<ListaReproduccion>();
		comboBoxLista.setModel(new DefaultComboBoxModel<ListaReproduccion>());
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 5);
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		panel_seleccion.add(comboBoxLista, gbc_comboBox);
		crearManejedorCombobox(comboBoxLista);
		
		btnReproducir = new JButton("Reproducir");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		panel_seleccion.add(btnReproducir, gbc_btnNewButton);
		crearManejadorBtnReproducir(btnReproducir);
		
		panel_resultados = new JPanel();
		panel_oeste.add(panel_resultados, BorderLayout.CENTER);
		
		panel_centro = new JPanel();
		add(panel_centro, BorderLayout.CENTER);
		panel_centro.setLayout(new CardLayout(0, 0));
		
		listaRep = new ListaVideos(new DefaultListModel<MiniaturaVideo>(),ALTO_CELDA,ANCHO_CELDA);
		
		JScrollPane scroll = new JScrollPane(listaRep);
		scroll.setMinimumSize(new Dimension(ANCHO_SCROLL,ALTO_SCROLL));
		scroll.setPreferredSize(new Dimension(ANCHO_SCROLL,ALTO_SCROLL));
		scroll.setMaximumSize(new Dimension(ANCHO_SCROLL,ALTO_SCROLL));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_resultados.add(scroll);
		
		reproductor = new PanelReproductor();
		panelVacio = new JPanel();
		panel_centro.add(panelVacio,"vacio");
		panel_centro.add(reproductor,"reproductor");
	}
	// Cargar las listas de la BD
	private void cargarListas() {
		DefaultComboBoxModel<ListaReproduccion> modelLista = (DefaultComboBoxModel<ListaReproduccion>)comboBoxLista.getModel();
		List<ListaReproduccion> listaRep = ControladorAppVideo.getUnicaInstancia().getListasReproduccion();
		comboBoxLista.removeAllItems();
		comboBoxLista.addItem(new ListaReproduccion("<Seleccione la lista de videos>"));
		modelLista.addAll(listaRep);
	}
	//Actualizar la vista
	public void actualizar() {
		cargarListas();
		CardLayout cl = (CardLayout) (panel_centro.getLayout());
		cl.show(panel_centro, "vacio");
		listaRep.reiniciar();
		listaActual = null;
	}
	
	// ------------- Manejador de los botones --------------//
	
	
	private void crearManejadorBtnReproducir(JButton btn) {
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				int videoSeleccionado = listaRep.getSelectedIndex();
				if (videoSeleccionado >= 0 && listaActual != null) {
					reproductor.reproducirVideo(AppMain.videoWeb, listaActual.getVideos().get(videoSeleccionado));
					CardLayout cl = (CardLayout) (panel_centro.getLayout());
					cl.show(panel_centro, "reproductor");
					ControladorAppVideo.getUnicaInstancia().añadirVideoReciente(listaActual.getVideos().get(videoSeleccionado));
					ControladorAppVideo.getUnicaInstancia().nuevaReproduccion(listaActual.getVideos().get(videoSeleccionado));
				}
			}
		});
	}
	
	private void crearManejedorCombobox(JComboBox<ListaReproduccion> comboBox) {
		comboBox.addActionListener(new ActionListener (){
			@Override
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedIndex() > 0) {
					listaActual = ControladorAppVideo.getUnicaInstancia().getListaReproduccion(((ListaReproduccion) comboBox.getSelectedItem()).getNombre());
					listaRep.reiniciar();
					listaRep.añadirElementos(listaActual.getVideos().stream().map(v -> new MiniaturaVideo(v.getTitulo(),v.getUrl(),0,ANCHO_CELDA,ALTO_CELDA)).collect(Collectors.toList()));
				}	
			}
			
		});
	}
}