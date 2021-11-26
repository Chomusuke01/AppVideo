package umu.tds.AppVideo.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import umu.tds.AppVideo.controlador.ControladorAppVideo;
import umu.tds.AppVideo.modelo.ListaReproduccion;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Insets;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JButton;

public class PanelMisListas extends JPanel {

	private static final long serialVersionUID = 1L;
	private JComboBox<ListaReproduccion> comboBoxLista;
	private JPanel panel_oeste;
	private JPanel panel_seleccion;
	private JPanel panel_cancelar;
	private JButton btnReproducir;
	private JButton btnCancelar;
	private JPanel panel_resultados;
	private JPanel panel_centro;
	private ListaVideos listaRep;

	public PanelMisListas() {
		setPreferredSize(new Dimension(970, 620));
		setMinimumSize(new Dimension(970, 620));
		setMaximumSize(new Dimension(970, 620));
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
		
		btnReproducir = new JButton("Reproducir");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		panel_seleccion.add(btnReproducir, gbc_btnNewButton);
		
		panel_cancelar = new JPanel();
		panel_oeste.add(panel_cancelar, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_cancelar = new GridBagLayout();
		gbl_panel_cancelar.columnWidths = new int[]{33, 75, 0, 0};
		gbl_panel_cancelar.rowHeights = new int[]{0, 20, 0};
		gbl_panel_cancelar.columnWeights = new double[]{1.0, 0.0, 1.0, Double.MIN_VALUE};
		gbl_panel_cancelar.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		panel_cancelar.setLayout(gbl_panel_cancelar);
		
		btnCancelar = new JButton("Cancelar");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 1;
		panel_cancelar.add(btnCancelar, gbc_btnNewButton_1);
		
		panel_resultados = new JPanel();
		panel_oeste.add(panel_resultados, BorderLayout.CENTER);
		
		panel_centro = new JPanel();
		add(panel_centro, BorderLayout.CENTER);
		
		listaRep = new ListaVideos(new DefaultListModel<MiniaturaVideo>(),120,150);
		
		JScrollPane scroll = new JScrollPane(listaRep);
		scroll.setMinimumSize(new Dimension(220,450));
		scroll.setPreferredSize(new Dimension(220,450));
		scroll.setMaximumSize(new Dimension(220,450));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panel_resultados.add(scroll);
		

	}
	
	private void cargarListas() {
		DefaultComboBoxModel<ListaReproduccion> modelLista = (DefaultComboBoxModel<ListaReproduccion>)comboBoxLista.getModel();
		List<ListaReproduccion> listaRep = ControladorAppVideo.getUnicaInstancia().getListasReproduccion();
		comboBoxLista.removeAllItems();
		comboBoxLista.addItem(new ListaReproduccion("<Seleccione la lista de videos>"));
		modelLista.addAll(listaRep);
	}

	public void actualizar() {
		cargarListas();
	}
}