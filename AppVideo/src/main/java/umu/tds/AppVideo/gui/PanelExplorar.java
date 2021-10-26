package umu.tds.AppVideo.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;

public class PanelExplorar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private JPanel panelExplorar;
	private JTextField txtBusqueda;
	
	public PanelExplorar() {
		initialize();
	}
	
	public void initialize() {
		//panelExplorar = new JPanel();
		this.setMaximumSize(new Dimension(970,620));
		this.setMinimumSize(new Dimension(970,620));
		this.setPreferredSize(new Dimension(970,620));
		this.setLayout(new BorderLayout(0, 0));
		
		JPanel panelEtiquetas = new JPanel();
		this.add(panelEtiquetas, BorderLayout.EAST);
		GridBagLayout gbl_panelEtiquetas = new GridBagLayout();
		gbl_panelEtiquetas.columnWidths = new int[]{10, 0, 10, 0};
		gbl_panelEtiquetas.rowHeights = new int[]{20, 0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0};
		gbl_panelEtiquetas.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelEtiquetas.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelEtiquetas.setLayout(gbl_panelEtiquetas);
		
		JLabel lblEtiquetasDisponibles = new JLabel("Etiquetas disponibles");
		GridBagConstraints gbc_lblEtiquetasDisponibles = new GridBagConstraints();
		gbc_lblEtiquetasDisponibles.fill = GridBagConstraints.VERTICAL;
		gbc_lblEtiquetasDisponibles.insets = new Insets(0, 0, 5, 5);
		gbc_lblEtiquetasDisponibles.gridx = 1;
		gbc_lblEtiquetasDisponibles.gridy = 1;
		panelEtiquetas.add(lblEtiquetasDisponibles, gbc_lblEtiquetasDisponibles);
		
		JTextArea textArea = new JTextArea();
		textArea.setSize(new Dimension(10, 10));
		textArea.setPreferredSize(new Dimension(50, 22));
		textArea.setMaximumSize(new Dimension(50, 22));
		textArea.setEditable(false);
		GridBagConstraints gbc_textArea = new GridBagConstraints();
		gbc_textArea.fill = GridBagConstraints.BOTH;
		gbc_textArea.gridheight = 6;
		gbc_textArea.insets = new Insets(0, 0, 5, 5);
		gbc_textArea.gridx = 1;
		gbc_textArea.gridy = 3;
		panelEtiquetas.add(textArea, gbc_textArea);
		
		JLabel lblBuscarEtiquetas = new JLabel("Buscar etiquetas:");
		GridBagConstraints gbc_lblBuscarEtiquetas = new GridBagConstraints();
		gbc_lblBuscarEtiquetas.fill = GridBagConstraints.VERTICAL;
		gbc_lblBuscarEtiquetas.insets = new Insets(0, 0, 5, 5);
		gbc_lblBuscarEtiquetas.gridx = 1;
		gbc_lblBuscarEtiquetas.gridy = 10;
		panelEtiquetas.add(lblBuscarEtiquetas, gbc_lblBuscarEtiquetas);
		
		JTextArea textArea_1 = new JTextArea();
		GridBagConstraints gbc_textArea_1 = new GridBagConstraints();
		gbc_textArea_1.insets = new Insets(0, 0, 5, 5);
		gbc_textArea_1.gridheight = 6;
		gbc_textArea_1.fill = GridBagConstraints.BOTH;
		gbc_textArea_1.gridx = 1;
		gbc_textArea_1.gridy = 12;
		panelEtiquetas.add(textArea_1, gbc_textArea_1);
		
		JPanel panelPrincipal = new JPanel();
		this.add(panelPrincipal, BorderLayout.CENTER);
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBusqueda = new JPanel();
		panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);
		GridBagLayout gbl_panelBusqueda = new GridBagLayout();
		gbl_panelBusqueda.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelBusqueda.rowHeights = new int[]{14, 0, 10, 0, 0, 0, 0, 0};
		gbl_panelBusqueda.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		gbl_panelBusqueda.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE};
		panelBusqueda.setLayout(gbl_panelBusqueda);
		
		JLabel lblBuscar = new JLabel("Buscar tíltulo:");
		GridBagConstraints gbc_lblBuscar = new GridBagConstraints();
		gbc_lblBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_lblBuscar.fill = GridBagConstraints.BOTH;
		gbc_lblBuscar.gridx = 1;
		gbc_lblBuscar.gridy = 1;
		panelBusqueda.add(lblBuscar, gbc_lblBuscar);
		
		txtBusqueda = new JTextField();
		GridBagConstraints gbc_txtBusqueda = new GridBagConstraints();
		gbc_txtBusqueda.gridwidth = 17;
		gbc_txtBusqueda.insets = new Insets(0, 0, 5, 5);
		gbc_txtBusqueda.fill = GridBagConstraints.HORIZONTAL;
		gbc_txtBusqueda.gridx = 2;
		gbc_txtBusqueda.gridy = 1;
		panelBusqueda.add(txtBusqueda, gbc_txtBusqueda);
		txtBusqueda.setColumns(10);
		
		JButton btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnBuscar = new GridBagConstraints();
		gbc_btnBuscar.fill = GridBagConstraints.HORIZONTAL;
		gbc_btnBuscar.insets = new Insets(0, 0, 5, 5);
		gbc_btnBuscar.gridx = 20;
		gbc_btnBuscar.gridy = 1;
		panelBusqueda.add(btnBuscar, gbc_btnBuscar);
		
		JButton btnNuevaBusqueda = new JButton("Nueva búsqueda");
		GridBagConstraints gbc_btnNuevaBusqueda = new GridBagConstraints();
		gbc_btnNuevaBusqueda.gridwidth = 8;
		gbc_btnNuevaBusqueda.fill = GridBagConstraints.VERTICAL;
		gbc_btnNuevaBusqueda.insets = new Insets(0, 0, 5, 5);
		gbc_btnNuevaBusqueda.gridx = 5;
		gbc_btnNuevaBusqueda.gridy = 3;
		panelBusqueda.add(btnNuevaBusqueda, gbc_btnNuevaBusqueda);
		
		JPanel panelResultados = new JPanel();
		panelResultados.setBackground(Color.GRAY);
		panelPrincipal.add(panelResultados, BorderLayout.CENTER);
	}
}
