package umu.tds.AppVideo.gui;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import umu.tds.AppVideo.controlador.ControladorAppVideo;
import umu.tds.AppVideo.modelo.Video;

import javax.swing.JButton;
import java.awt.Color;

public class PanelExplorar extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtBusqueda;
	private JTable resultadoBusqueda;
	private static final int NUM_COLUMNAS_RESULTADO = 4;
	private List<Video> busquedaActual;
	private JPanel panelEtiquetas;
	private JPanel panelPrincipal;
	private JPanel reproductor;
	private JPanel panelBusqueda;
	private JPanel panelResultados;
	private JPanel panelExplorar;
	
	
	
	public PanelExplorar() {
		initialize();
	}
	
	public void initialize() {
		//panelExplorar = new JPanel();
		this.setMaximumSize(new Dimension(970,620));
		this.setMinimumSize(new Dimension(970,620));
		this.setPreferredSize(new Dimension(970,620));
		this.setLayout(new BorderLayout(0, 0));
		
		panelEtiquetas = new JPanel();
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
		
		panelPrincipal = new JPanel();
		
		panelPrincipal.setLayout(new BorderLayout(0, 0));
		
		panelExplorar = new JPanel();
		panelExplorar.setLayout(new CardLayout(0,0));
		panelExplorar.add(panelPrincipal,"principal");
		
		reproductor = new PanelReproductor();
		panelExplorar.add(reproductor,"reproductor");
		this.add(panelExplorar);
		
		panelBusqueda = new JPanel();
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
		crearManejadorBtnBuscar(btnBuscar,this);
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
		crearManejadorBtnNuevBusqueda(btnNuevaBusqueda);
		
		panelResultados = new JPanel();
		panelPrincipal.add(panelResultados, BorderLayout.CENTER);
		
		resultadoBusqueda = new JTable();
		resultadoBusqueda.getTableHeader().setReorderingAllowed(false);
		resultadoBusqueda.setShowVerticalLines(false);
		resultadoBusqueda.setShowHorizontalLines(false);
		resultadoBusqueda.setShowGrid(false);
		resultadoBusqueda.setBackground(new Color(240,240,240));
		resultadoBusqueda.setSelectionForeground(new Color(240,240,240));
		resultadoBusqueda.setSelectionBackground(new Color(240,240,240));
		
		resultadoBusqueda.setRowHeight(150);
		resultadoBusqueda.setDefaultRenderer(MiniaturaVideo.class, new MiniaturaVideoTableRenderer());
		resizeColumnWidth(resultadoBusqueda);
		crearEventoRaton(resultadoBusqueda);

		JScrollPane scroll=new JScrollPane(resultadoBusqueda);
		scroll.setMinimumSize(new Dimension(940,480));
		scroll.setPreferredSize(new Dimension(940,480));
		scroll.setMaximumSize(new Dimension(940,480));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelResultados.add(scroll);
		
	}
	
	private class MyTableModel extends DefaultTableModel {
        /**
         * 
         */
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
	
	private void resizeColumnWidth(JTable table) {
	    //Se obtiene el modelo de la columna
	    TableColumnModel columnModel = table.getColumnModel();
	    int width = 230;
	    //Se obtiene el total de las columnas
	    for (int column = 0; column < table.getColumnCount(); column++) {
	        columnModel.getColumn(column).setMinWidth(width);
	    }
	}
	
	private Object [][] obtenerTablaResultados (List<Video> videos) {
		
		Object [][] data = new Object [(int) Math.ceil((double)videos.size()/NUM_COLUMNAS_RESULTADO)][NUM_COLUMNAS_RESULTADO];
		int pos = 0;
		int fila = 0;
		int columna = 0;
		while (pos < videos.size()) {
			data [fila][columna] = new MiniaturaVideo(videos.get(pos).getTitulo(), videos.get(pos).getUrl(), pos);
			pos++;
			columna++;
			if (columna == NUM_COLUMNAS_RESULTADO) {
				fila++;
				columna = 0;
			}
		}
		return data;
	}
	
	private void crearManejadorBtnBuscar(JButton btnBuscar, JPanel panel) {
		btnBuscar.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				if (txtBusqueda.getText().trim().isEmpty()) {
					// Mensaje de error
					JOptionPane.showMessageDialog(panel, "El campo de búsqueda está vacio",
							"Error búsqueda", JOptionPane.ERROR_MESSAGE);
					// Puede que tengas etiquetas seleccionadas, por lo que esa condición cambiará en un futuro.
				}else {
					// Limpiar la búsqueda anterior
					limpiarTabla();
					// Realizar busqueda, que cuando tengas las etiquetas seguramente cambie un poquito.
					List<Video> resultados = ControladorAppVideo.getUnicaInstancia().buscarVideos(txtBusqueda.getText());
					if (resultados.size() == 0) {
						JOptionPane.showMessageDialog(panel, "No se han encontrado resultados para " + "\"" + txtBusqueda.getText() + "\"",
								"Buscar", JOptionPane.INFORMATION_MESSAGE);
					}else {
						Object [][] data = obtenerTablaResultados(resultados);
						resultadoBusqueda.setModel(new MyTableModel(data, new String [] {"", "", "",""}));
						busquedaActual = resultados;
					}
				}
			}
		});
	}
	
	private void limpiarTabla() {
		resultadoBusqueda.setModel(new DefaultTableModel());
	}
	
	private void crearManejadorBtnNuevBusqueda(JButton btn) {
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				limpiarTabla();
				txtBusqueda.setText("");
				busquedaActual = null;
			}
		});
	}
	
	private void crearEventoRaton(JTable tabla) {
		tabla.addMouseListener(new MouseAdapter() {
			public void mouseClicked (MouseEvent e) {
				if (e.getClickCount() == 2) {
					int videoSeleccionado = tabla.getSelectedRow()*NUM_COLUMNAS_RESULTADO + tabla.getSelectedColumn();
					CardLayout cl = null;
					if (videoSeleccionado < busquedaActual.size()) {
						((PanelReproductor) reproductor).reproducirVideo(AppMain.videoWeb,busquedaActual.get(videoSeleccionado));
						ControladorAppVideo.getUnicaInstancia().nuevaReproduccion(busquedaActual.get(videoSeleccionado));
						cl = ((CardLayout) panelExplorar.getLayout());
						cl.show(panelExplorar, "reproductor");
					}
				}	
			}
		});
	}
	
	public void cambiarExplorar() {
		CardLayout cl = ((CardLayout) panelExplorar.getLayout());
		cl.show(panelExplorar, "principal");
	}
}

//Object [][] data = new Object [][] {{new MiniaturaVideo("El conejo","https://www.youtube.com/watch?v=twayP7FqZmc",0),
//new MiniaturaVideo("El conejo1","https://www.youtube.com/watch?v=twayP7FqZmc",1),
//new MiniaturaVideo("El conejo2","https://www.youtube.com/watch?v=twayP7FqZmc",2)}};