package umu.tds.AppVideo.gui;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.border.BevelBorder;
import javax.swing.JSeparator;
import java.awt.Rectangle;

public class PanelNuevaLista extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField textField_1;
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public PanelNuevaLista() {
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(970, 620));
		setMinimumSize(new Dimension(970, 620));
		setMaximumSize(new Dimension(970, 620));
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{203, 762, 0};
		gridBagLayout.rowHeights = new int[]{81, 0, 0};
		gridBagLayout.columnWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{1.0, 1.0, Double.MIN_VALUE};
		setLayout(gridBagLayout);
		
		JPanel panel = new JPanel();
		panel.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel = new GridBagConstraints();
		gbc_panel.fill = GridBagConstraints.BOTH;
		gbc_panel.insets = new Insets(0, 0, 5, 5);
		gbc_panel.gridx = 0;
		gbc_panel.gridy = 0;
		add(panel, gbc_panel);
		GridBagLayout gbl_panel = new GridBagLayout();
		gbl_panel.columnWidths = new int[]{10, 112, 65, 0};
		gbl_panel.rowHeights = new int[]{10, 14, 23, 23, 0};
		gbl_panel.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		panel.setLayout(gbl_panel);
		
		JLabel lblNewLabel = new JLabel("Introducir nombre lista:");
		GridBagConstraints gbc_lblNewLabel = new GridBagConstraints();
		gbc_lblNewLabel.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel.gridx = 1;
		gbc_lblNewLabel.gridy = 1;
		panel.add(lblNewLabel, gbc_lblNewLabel);
		
		textField_1 = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.gridx = 1;
		gbc_textField_1.gridy = 2;
		panel.add(textField_1, gbc_textField_1);
		textField_1.setColumns(10);
		
		JButton btnNewButton_7 = new JButton("Buscar");
		GridBagConstraints gbc_btnNewButton_7 = new GridBagConstraints();
		gbc_btnNewButton_7.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_7.gridx = 2;
		gbc_btnNewButton_7.gridy = 2;
		panel.add(btnNewButton_7, gbc_btnNewButton_7);
		
		JButton btnNewButton_1 = new JButton("Eliminar");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridwidth = 2;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 1;
		gbc_btnNewButton_1.gridy = 3;
		panel.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JPanel panel_1 = new JPanel();
		panel_1.setMinimumSize(new Dimension(10, 20));
		panel_1.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_1.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_1 = new GridBagConstraints();
		gbc_panel_1.fill = GridBagConstraints.BOTH;
		gbc_panel_1.insets = new Insets(0, 5, 0, 0);
		gbc_panel_1.gridx = 1;
		gbc_panel_1.gridy = 0;
		add(panel_1, gbc_panel_1);
		GridBagLayout gbl_panel_1 = new GridBagLayout();
		gbl_panel_1.columnWidths = new int[]{32, 63, 555, 35, 65, 0};
		gbl_panel_1.rowHeights = new int[]{32, 23, 23, 0};
		gbl_panel_1.columnWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_1.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		panel_1.setLayout(gbl_panel_1);
		
		JButton btnNewButton_3 = new JButton("Nueva Busqueda");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		
		JLabel lblNewLabel_1 = new JLabel("Buscar título:");
		GridBagConstraints gbc_lblNewLabel_1 = new GridBagConstraints();
		gbc_lblNewLabel_1.anchor = GridBagConstraints.EAST;
		gbc_lblNewLabel_1.insets = new Insets(0, 0, 5, 5);
		gbc_lblNewLabel_1.gridx = 1;
		gbc_lblNewLabel_1.gridy = 1;
		panel_1.add(lblNewLabel_1, gbc_lblNewLabel_1);
		
		textField = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.gridx = 2;
		gbc_textField.gridy = 1;
		panel_1.add(textField, gbc_textField);
		textField.setColumns(10);
		
		JButton btnNewButton_2 = new JButton("Buscar");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_2.gridx = 4;
		gbc_btnNewButton_2.gridy = 1;
		panel_1.add(btnNewButton_2, gbc_btnNewButton_2);
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_3.gridx = 2;
		gbc_btnNewButton_3.gridy = 2;
		panel_1.add(btnNewButton_3, gbc_btnNewButton_3);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		panel_3.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_3 = new GridBagConstraints();
		gbc_panel_3.insets = new Insets(0, 0, 0, 5);
		gbc_panel_3.fill = GridBagConstraints.BOTH;
		gbc_panel_3.gridx = 0;
		gbc_panel_3.gridy = 1;
		add(panel_3, gbc_panel_3);
		GridBagLayout gbl_panel_3 = new GridBagLayout();
		gbl_panel_3.columnWidths = new int[]{59, 71, 63, 0};
		gbl_panel_3.rowHeights = new int[]{422, 23, 35, 23, 0};
		gbl_panel_3.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panel_3.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE};
		panel_3.setLayout(gbl_panel_3);
		
		JButton btnNewButton_4 = new JButton("Añadir");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_4.gridx = 0;
		gbc_btnNewButton_4.gridy = 1;
		panel_3.add(btnNewButton_4, gbc_btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("Quitar");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_5.gridx = 2;
		gbc_btnNewButton_5.gridy = 1;
		panel_3.add(btnNewButton_5, gbc_btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("Aceptar");
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_6.gridx = 1;
		gbc_btnNewButton_6.gridy = 3;
		panel_3.add(btnNewButton_6, gbc_btnNewButton_6);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.GRAY);
		GridBagConstraints gbc_panel_2 = new GridBagConstraints();
		gbc_panel_2.fill = GridBagConstraints.BOTH;
		gbc_panel_2.gridx = 1;
		gbc_panel_2.gridy = 1;
		add(panel_2, gbc_panel_2);

	}

}
