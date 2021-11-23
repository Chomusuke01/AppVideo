package umu.tds.AppVideo.gui;

import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import javax.swing.JLabel;
import javax.swing.JComboBox;
import java.awt.Insets;
import javax.swing.JButton;

public class PanelMisListas extends JPanel {

	private static final long serialVersionUID = 1L;

	public PanelMisListas() {
		setPreferredSize(new Dimension(970, 620));
		setMinimumSize(new Dimension(970, 620));
		setMaximumSize(new Dimension(970, 620));
		setLayout(new BorderLayout(0, 0));
		
		JPanel panel_oeste = new JPanel();
		add(panel_oeste, BorderLayout.WEST);
		panel_oeste.setLayout(new BorderLayout(0, 0));
		
		JPanel panel_seleccion = new JPanel();
		panel_oeste.add(panel_seleccion, BorderLayout.NORTH);
		GridBagLayout gbl_panel_seleccion = new GridBagLayout();
		gbl_panel_seleccion.columnWidths = new int[]{5, 0, 0, 46, 0};
		gbl_panel_seleccion.rowHeights = new int[]{0, 0, 14, 0};
		gbl_panel_seleccion.columnWeights = new double[]{1.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_seleccion.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_seleccion.setLayout(gbl_panel_seleccion);
		
		JLabel lblNewLabel_1 = new JLabel(" Seleccione la lista:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 0;
		panel_seleccion.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		JComboBox comboBox = new JComboBox();
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 5, 0);
		gbc_comboBox.gridwidth = 3;
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 1;
		gbc_comboBox.gridy = 1;
		panel_seleccion.add(comboBox, gbc_comboBox);
		
		JButton btnNewButton = new JButton("Reproducir");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridwidth = 3;
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 2;
		panel_seleccion.add(btnNewButton, gbc_btnNewButton);
		
		JPanel panel_cancelar = new JPanel();
		panel_oeste.add(panel_cancelar, BorderLayout.SOUTH);
		GridBagLayout gbl_panel_cancelar = new GridBagLayout();
		gbl_panel_cancelar.columnWidths = new int[]{33, 75, 0};
		gbl_panel_cancelar.rowHeights = new int[]{0, 20, 0};
		gbl_panel_cancelar.columnWeights = new double[]{0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_cancelar.rowWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		panel_cancelar.setLayout(gbl_panel_cancelar);
		
		JButton btnNewButton_1 = new JButton("Cancelar");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 0;
		panel_cancelar.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JPanel panel_resultados = new JPanel();
		panel_oeste.add(panel_resultados, BorderLayout.CENTER);
		
		JPanel panel_centro = new JPanel();
		add(panel_centro, BorderLayout.CENTER);

	}

}