package umu.tds.AppVideo.gui;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.BorderLayout;

public class PanelNuevaLista extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;

	/**
	 * Create the panel.
	 */
	public PanelNuevaLista() {
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(970, 620));
		setMinimumSize(new Dimension(970, 620));
		setMaximumSize(new Dimension(970, 620));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panelOeste = new JPanel();
		add(panelOeste, BorderLayout.WEST);
		panelOeste.setLayout(new BorderLayout(0, 0));
		
		JPanel panelBusqueda = new JPanel();
		panelOeste.add(panelBusqueda, BorderLayout.NORTH);
		GridBagLayout gbl_panelBusqueda = new GridBagLayout();
		gbl_panelBusqueda.columnWidths = new int[]{0, 0, 0};
		gbl_panelBusqueda.rowHeights = new int[]{0, 0, 0, 0};
		gbl_panelBusqueda.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gbl_panelBusqueda.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelBusqueda.setLayout(gbl_panelBusqueda);
		
		JLabel lblNewLabel = new JLabel("Introducir nombre lista:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 0;
		gbc_lblNewLabel.gridy = 0;
		panelBusqueda.add(lblNewLabel, gbc_lblNewLabel);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		panelBusqueda.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		panelBusqueda.add(btnNewButton, gbc_btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 2;
		panelBusqueda.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JPanel panelBotones = new JPanel();
		panelOeste.add(panelBotones, BorderLayout.SOUTH);
		GridBagLayout gbl_panelBotones = new GridBagLayout();
		gbl_panelBotones.columnWidths = new int[]{59, 63, 0, 0};
		gbl_panelBotones.rowHeights = new int[]{23, 0, 0, 0};
		gbl_panelBotones.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelBotones.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelBotones.setLayout(gbl_panelBotones);
		
		JButton btnNewButton_2 = new JButton("Añadir");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 0;
		panelBotones.add(btnNewButton_2, gbc_btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("Quitar");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 2;
		gbc_btnNewButton_3.gridy = 0;
		panelBotones.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("Aceptar");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 2;
		panelBotones.add(btnNewButton_4, gbc_btnNewButton_4);
		
		JPanel panelResultados = new JPanel();
		panelOeste.add(panelResultados, BorderLayout.CENTER);
		
		JPanel panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		JPanel panelNorte = new JPanel();
		panelCentro.add(panelNorte, BorderLayout.NORTH);
		GridBagLayout gbl_panelNorte = new GridBagLayout();
		gbl_panelNorte.columnWidths = new int[]{75, 0, 63, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelNorte.rowHeights = new int[]{25, 14, 0, 0};
		gbl_panelNorte.columnWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelNorte.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelNorte.setLayout(gbl_panelNorte);
		
		JLabel lblNewLabel_1 = new JLabel("Buscar título:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panelNorte.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 16;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 1;
		panelNorte.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_5 = new JButton("Buscar");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 18;
		gbc_btnNewButton_5.gridy = 1;
		panelNorte.add(btnNewButton_5, gbc_btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Nueva búsqueda");
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_6.gridx = 2;
		gbc_btnNewButton_6.gridy = 2;
		panelNorte.add(btnNewButton_6, gbc_btnNewButton_6);
		
		JPanel panelPrincipal = new JPanel();
		panelCentro.add(panelPrincipal, BorderLayout.CENTER);

	}

}
