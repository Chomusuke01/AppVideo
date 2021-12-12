package umu.tds.AppVideo.gui;

import javax.swing.JPanel;
import javax.swing.JScrollPane;

import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.stream.Collectors;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import umu.tds.AppVideo.controlador.ControladorAppVideo;
import umu.tds.AppVideo.modelo.Video;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.BorderLayout;

public class PanelNuevaLista extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static final int NUM_COLUMNAS_RESULTADO = 4;
	private JTextField txtLista;
	private JTextField txtBusqueda;
	private JPanel panelOeste;
	private JPanel panelBusqueda;
	private JButton btnBuscar;
	private JButton btnEliminar;
	private JPanel panelBotones;
	private JButton btnAñadir;
	private JButton btnQuitar;
	private JButton btnAceptar;
	private JPanel panelResultados;
	private JPanel panelCentro;
	private JPanel panelNorte;
	private JButton btnBuscarVideos;
	private JButton btnNuevaBusqueda;
	private JPanel panelPrincipal;
	private List<Video> listaActual;
	private List<Video> busquedaActual;
	private ListaVideos listaRep;
	private TablaBusqueda resultadoBusqueda;
	private String nombreListaActual;

	/**
	 * Create the panel.
	 */
	public PanelNuevaLista() {
		setBackground(Color.GRAY);
		setPreferredSize(new Dimension(970, 620));
		setMinimumSize(new Dimension(970, 620));
		setMaximumSize(new Dimension(970, 620));
		setLayout(new BorderLayout(0, 0));
		
		panelOeste = new JPanel();
		add(panelOeste, BorderLayout.WEST);
		panelOeste.setLayout(new BorderLayout(0, 0));
		
		panelBusqueda = new JPanel();
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
		
		txtLista = new JTextField();
		GridBagConstraints gbc_textField = new GridBagConstraints();
		gbc_textField.insets = new Insets(0, 0, 5, 5);
		gbc_textField.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField.gridx = 0;
		gbc_textField.gridy = 1;
		panelBusqueda.add(txtLista, gbc_textField);
		txtLista.setColumns(10);
		
		btnBuscar = new JButton("Buscar");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton.gridx = 1;
		gbc_btnNewButton.gridy = 1;
		panelBusqueda.add(btnBuscar, gbc_btnNewButton);
		crearManejadorBtnBuscarLista(btnBuscar);
		
		btnEliminar = new JButton("Eliminar");
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.anchor = GridBagConstraints.EAST;
		gbc_btnNewButton_1.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_1.gridx = 0;
		gbc_btnNewButton_1.gridy = 2;
		panelBusqueda.add(btnEliminar, gbc_btnNewButton_1);
		
		panelBotones = new JPanel();
		panelOeste.add(panelBotones, BorderLayout.SOUTH);
		GridBagLayout gbl_panelBotones = new GridBagLayout();
		gbl_panelBotones.columnWidths = new int[]{59, 63, 0, 0};
		gbl_panelBotones.rowHeights = new int[]{23, 0, 0, 0};
		gbl_panelBotones.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_panelBotones.rowWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		panelBotones.setLayout(gbl_panelBotones);
		
		btnAñadir = new JButton("Añadir");
		GridBagConstraints gbc_btnNewButton_2 = new GridBagConstraints();
		gbc_btnNewButton_2.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_2.anchor = GridBagConstraints.NORTHWEST;
		gbc_btnNewButton_2.gridx = 0;
		gbc_btnNewButton_2.gridy = 0;
		panelBotones.add(btnAñadir, gbc_btnNewButton_2);
		crearManejadorBtnAñadir(btnAñadir);
		
		btnQuitar = new JButton("Quitar");
		GridBagConstraints gbc_btnNewButton_3 = new GridBagConstraints();
		gbc_btnNewButton_3.insets = new Insets(0, 0, 5, 0);
		gbc_btnNewButton_3.gridx = 2;
		gbc_btnNewButton_3.gridy = 0;
		panelBotones.add(btnQuitar, gbc_btnNewButton_3);
		crearManejadorBtnQuitar(btnQuitar);
		
		btnAceptar = new JButton("Aceptar");
		GridBagConstraints gbc_btnNewButton_4 = new GridBagConstraints();
		gbc_btnNewButton_4.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_4.gridx = 1;
		gbc_btnNewButton_4.gridy = 2;
		panelBotones.add(btnAceptar, gbc_btnNewButton_4);
		
		panelResultados = new JPanel();
		panelOeste.add(panelResultados, BorderLayout.CENTER);
		
		panelCentro = new JPanel();
		add(panelCentro, BorderLayout.CENTER);
		panelCentro.setLayout(new BorderLayout(0, 0));
		
		panelNorte = new JPanel();
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
		
		txtBusqueda = new JTextField();
		GridBagConstraints gbc_textField_1 = new GridBagConstraints();
		gbc_textField_1.gridwidth = 16;
		gbc_textField_1.insets = new Insets(0, 0, 5, 5);
		gbc_textField_1.fill = GridBagConstraints.HORIZONTAL;
		gbc_textField_1.gridx = 2;
		gbc_textField_1.gridy = 1;
		panelNorte.add(txtBusqueda, gbc_textField_1);
		txtBusqueda.setColumns(10);
		
		btnBuscarVideos = new JButton("Buscar");
		GridBagConstraints gbc_btnNewButton_5 = new GridBagConstraints();
		gbc_btnNewButton_5.insets = new Insets(0, 0, 5, 5);
		gbc_btnNewButton_5.gridx = 18;
		gbc_btnNewButton_5.gridy = 1;
		panelNorte.add(btnBuscarVideos, gbc_btnNewButton_5);
		crearManejadorBtnBuscarVideos(btnBuscarVideos);
		
		btnNuevaBusqueda = new JButton("Nueva búsqueda");
		GridBagConstraints gbc_btnNewButton_6 = new GridBagConstraints();
		gbc_btnNewButton_6.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton_6.gridx = 2;
		gbc_btnNewButton_6.gridy = 2;
		panelNorte.add(btnNuevaBusqueda, gbc_btnNewButton_6);
		
		panelPrincipal = new JPanel();
		panelCentro.add(panelPrincipal, BorderLayout.CENTER);
		
		listaRep = new ListaVideos(new DefaultListModel<MiniaturaVideo>(),120,150);
		JScrollPane scrollLista=new JScrollPane(listaRep);
		
		scrollLista.setMinimumSize(new Dimension(220,400));
		scrollLista.setPreferredSize(new Dimension(220,400));
		scrollLista.setMaximumSize(new Dimension(220,900));
		
		scrollLista.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelResultados.add(scrollLista);
		
		resultadoBusqueda = new TablaBusqueda(MiniaturaVideo.class, new MiniaturaVideoTableRenderer(), 150, 120, NUM_COLUMNAS_RESULTADO);
		JScrollPane scrollBusqueda=new JScrollPane(resultadoBusqueda);
		scrollBusqueda.setMinimumSize(new Dimension(850,480));
		scrollBusqueda.setPreferredSize(new Dimension(850,480));
		scrollBusqueda.setMaximumSize(new Dimension(850,480));
		panelPrincipal.add(scrollBusqueda);
		
	}
	
	private void crearManejadorBtnBuscarVideos(JButton boton) {
		
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtBusqueda.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(panelPrincipal, "Campo de búsqueda vacio",
							"Error búsqueda", JOptionPane.ERROR_MESSAGE);
				}else {
					List<Video> resultados = ControladorAppVideo.getUnicaInstancia().buscarVideos(txtBusqueda.getText(),null);
					if (resultados.size() == 0) {
						JOptionPane.showMessageDialog(panelPrincipal, "No se han encontrado resultados para " + "\"" + txtBusqueda.getText() + "\"",
								"Buscar", JOptionPane.INFORMATION_MESSAGE);
					}else {
						Object [][] data =  resultadoBusqueda.obtenerTablaResultados(resultados,150,120);
						resultadoBusqueda.setModel(new MyTableModel(data, new String [] {"", "", "",""}));
						busquedaActual = resultados;
					}
				}
			}
		});
	}
	
	
	private void crearManejadorBtnBuscarLista(JButton boton) {
		boton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtLista.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(panelPrincipal, "Introduzca un nombre de lista",
							"Error lista", JOptionPane.ERROR_MESSAGE);
				}else {
					nombreListaActual = txtLista.getText();
					listaActual = ControladorAppVideo.getUnicaInstancia().getListaReproduccion(txtLista.getText());
					if (listaActual == null) {
						int res = JOptionPane.showConfirmDialog(panelPrincipal, "¿Desea crear la lista" + "\"" + txtLista.getText() + "\"?","Lista no encontrada", JOptionPane.YES_NO_OPTION);
						
						if (res == JOptionPane.YES_OPTION) {
							ControladorAppVideo.getUnicaInstancia().añadirNuevaLista(txtLista.getText());
						}
					}else {
						listaRep.reiniciar();
						listaRep.añadirElementos(listaActual.stream().map(v -> new MiniaturaVideo(v.getTitulo(),v.getUrl(),0,150,120)).collect(Collectors.toList()));
					}
				}	
			}
		});
	}
	
	private void crearManejadorBtnAñadir(JButton boton) {
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				int videoSeleccionado = resultadoBusqueda.getSelectedRow()*NUM_COLUMNAS_RESULTADO + resultadoBusqueda.getSelectedColumn();
				
				if (nombreListaActual == null) {
					JOptionPane.showMessageDialog(panelPrincipal, "Seleccione una lista donde añadir",
							"Error Añadir", JOptionPane.ERROR_MESSAGE);
				}else if (busquedaActual == null || videoSeleccionado < 0) {
					JOptionPane.showMessageDialog(panelPrincipal, "Seleccione un video antes de añadir",
							"Error Añadir", JOptionPane.ERROR_MESSAGE);
				}else {
					Video nuevo = busquedaActual.get(videoSeleccionado);
					if (ControladorAppVideo.getUnicaInstancia().añadirVideoLista(txtLista.getText(), nuevo)) {
						listaRep.añadirElemento(new MiniaturaVideo(nuevo.getTitulo(), nuevo.getUrl(), 0, 150, 120));
					}else {
						JOptionPane.showMessageDialog(panelPrincipal, "El video ya está en la lista",
								"Error Añadir", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	
	private void crearManejadorBtnQuitar(JButton boton) {
		boton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				int videoEliminar = listaRep.getSelectedIndex();
				if (videoEliminar == -1) {
					JOptionPane.showMessageDialog(panelPrincipal, "Elige el video de la lista que quieres eliminar",
							"Error eliminar", JOptionPane.ERROR_MESSAGE);
				}else {
					MiniaturaVideo v = listaRep.eliminarElemento(videoEliminar); 
					ControladorAppVideo.getUnicaInstancia().eliminarVideoLista(txtLista.getText(), v.getTitulo(), v.getUrl());
				}
			}
		});
	}
	
	private void crearManejadorBtnNuevaBusuqeda() {
		
	}
	
	private class MyTableModel extends DefaultTableModel {
        private static final long serialVersionUID = 1L;
        public MyTableModel(Object[][] data, Object[] columnNames) {
            super(data, columnNames);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            Class<?> clazz = Object.class;
            Object aux = getValueAt(0, columnIndex);
            if (aux != null) {
                clazz = aux.getClass();
            }

            return clazz;
        }
        @Override
        public boolean isCellEditable (int row, int column)
           {
               return false;
           }
    }
}
