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
import java.util.LinkedList;
import java.util.List;

import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.DefaultTableModel;

import umu.tds.AppVideo.Lanzador.AppMain;
import umu.tds.AppVideo.controlador.ControladorAppVideo;
import umu.tds.AppVideo.modelo.Video;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;

public class PanelExplorar extends JPanel {
	private static final int ALTO_CELDA = 240;
	private static final int ANCHO_CELDA = 300;
	private static final int ALTO_SCROLL_TABLA = 480;
	private static final int ANCHO_SCROLL_TABLA = 940;
	private static final int ANCHO_COLUMNA_TABLA = 230;
	private static final int ALTO_FILA_TABLA = 150;
	private static final int ALTO_LISTA_ETIQUETAS = 50;
	private static final int ANCHO_LISTA_ETIQUETAS = 60;
	private static final int ALTO_PANEL = 620;
	private static final int ANCHO_PANEL = 970;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtBusqueda;
	private TablaBusqueda resultadoBusqueda;
	private static final int NUM_COLUMNAS_RESULTADO = 4;
	private List<Video> busquedaActual;
	private JPanel panelEtiquetas;
	private JPanel panelPrincipal;
	private JPanel reproductor;
	private JPanel panelBusqueda;
	private JPanel panelResultados;
	private JPanel panelExplorar;
	private JList<String> listEtiquetas;
	private JList<String> listEtiquetaSel;
	public  DefaultListModel<String> modelListaEtiquetas;
	public  DefaultListModel<String> modelListaEtiquetasSel;
	private JPanel panelCard;
	private JPanel panelPrincipalBusqueda;
	private JScrollPane scrollListaEtiquetas; 
	private JScrollPane scrollListaEtiquetasSel;

	public PanelExplorar() {
		initialize();
	}

	public void initialize() {
		this.setMaximumSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));
		this.setMinimumSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));
		this.setPreferredSize(new Dimension(ANCHO_PANEL, ALTO_PANEL));
		this.setLayout(new BorderLayout(0, 0));

		panelEtiquetas = new JPanel();
		panelCard = new JPanel();
		this.add(panelCard);
		panelCard.setLayout(new CardLayout(0, 0));

		panelPrincipalBusqueda = new JPanel();

		GridBagLayout gbl_panelEtiquetas = new GridBagLayout();
		gbl_panelEtiquetas.columnWidths = new int[] { 10, 0, 10, 0 };
		gbl_panelEtiquetas.rowHeights = new int[] { 20, 0, 0, 0, 0, 0, 0, 0, 0, ALTO_LISTA_ETIQUETAS, 0, 0, 0, 0, 0, 0, 0, 0, 20, 0 };
		gbl_panelEtiquetas.columnWeights = new double[] { 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelEtiquetas.rowWeights = new double[] { 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		panelEtiquetas.setLayout(gbl_panelEtiquetas);

		JLabel lblEtiquetasDisponibles = new JLabel("Etiquetas disponibles");
		GridBagConstraints gbc_lblEtiquetasDisponibles = new GridBagConstraints();
		gbc_lblEtiquetasDisponibles.fill = GridBagConstraints.VERTICAL;
		gbc_lblEtiquetasDisponibles.insets = new Insets(0, 0, 5, 5);
		gbc_lblEtiquetasDisponibles.gridx = 1;
		gbc_lblEtiquetasDisponibles.gridy = 1;
		panelEtiquetas.add(lblEtiquetasDisponibles, gbc_lblEtiquetasDisponibles);

		listEtiquetas = new JList<String>();
		modelListaEtiquetas = new DefaultListModel<String>();
		modelListaEtiquetas.addAll(ControladorAppVideo.getUnicaInstancia().getEtiquetasVideos());
		listEtiquetas.setModel(modelListaEtiquetas);
		crearEventoRatonLista(listEtiquetas);

		GridBagConstraints gbc_listEtiquetas = new GridBagConstraints();
		gbc_listEtiquetas.gridheight = 6;
		gbc_listEtiquetas.insets = new Insets(0, 0, 5, 5);
		gbc_listEtiquetas.fill = GridBagConstraints.BOTH;
		gbc_listEtiquetas.gridx = 1;
		gbc_listEtiquetas.gridy = 3;
		scrollListaEtiquetas = new JScrollPane(listEtiquetas);
		scrollListaEtiquetas.setMaximumSize(new Dimension(ANCHO_LISTA_ETIQUETAS, ALTO_LISTA_ETIQUETAS));
		scrollListaEtiquetas.setMinimumSize(new Dimension(ANCHO_LISTA_ETIQUETAS, ALTO_LISTA_ETIQUETAS));
		scrollListaEtiquetas.setPreferredSize(new Dimension(ANCHO_LISTA_ETIQUETAS, ALTO_LISTA_ETIQUETAS));
		scrollListaEtiquetas.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelEtiquetas.add(scrollListaEtiquetas, gbc_listEtiquetas);

		JLabel lblBuscarEtiquetas = new JLabel("Buscar etiquetas:");
		GridBagConstraints gbc_lblBuscarEtiquetas = new GridBagConstraints();
		gbc_lblBuscarEtiquetas.fill = GridBagConstraints.VERTICAL;
		gbc_lblBuscarEtiquetas.insets = new Insets(0, 0, 5, 5);
		gbc_lblBuscarEtiquetas.gridx = 1;
		gbc_lblBuscarEtiquetas.gridy = 10;
		panelEtiquetas.add(lblBuscarEtiquetas, gbc_lblBuscarEtiquetas);

		listEtiquetaSel = new JList<String>();
		modelListaEtiquetasSel = new DefaultListModel<String>();
		listEtiquetaSel.setModel(modelListaEtiquetasSel);
		crearEventoRatonRemoveFromLista(listEtiquetaSel);

		GridBagConstraints gbc_listEtiquetaSel = new GridBagConstraints();
		gbc_listEtiquetaSel.gridheight = 6;
		gbc_listEtiquetaSel.insets = new Insets(0, 0, 5, 5);
		gbc_listEtiquetaSel.fill = GridBagConstraints.BOTH;
		gbc_listEtiquetaSel.gridx = 1;
		gbc_listEtiquetaSel.gridy = 12;
		scrollListaEtiquetasSel = new JScrollPane(listEtiquetaSel);
		scrollListaEtiquetasSel.setMaximumSize(new Dimension(ANCHO_LISTA_ETIQUETAS, ALTO_LISTA_ETIQUETAS));
		scrollListaEtiquetasSel.setMinimumSize(new Dimension(ANCHO_LISTA_ETIQUETAS, ALTO_LISTA_ETIQUETAS));
		scrollListaEtiquetasSel.setPreferredSize(new Dimension(ANCHO_LISTA_ETIQUETAS, ALTO_LISTA_ETIQUETAS));
		scrollListaEtiquetasSel.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelEtiquetas.add(scrollListaEtiquetasSel, gbc_listEtiquetaSel);

		panelPrincipal = new JPanel();

		panelPrincipal.setLayout(new BorderLayout(0, 0));
		panelPrincipalBusqueda.setLayout(new BorderLayout(0, 0));
		panelPrincipalBusqueda.add(panelEtiquetas, BorderLayout.EAST);
		panelExplorar = new JPanel();
		panelExplorar.setLayout(new BorderLayout(0, 0));
		panelExplorar.add(panelPrincipal);

		reproductor = new PanelReproductor();

		panelPrincipalBusqueda.add(panelExplorar);

		panelBusqueda = new JPanel();
		panelPrincipal.add(panelBusqueda, BorderLayout.NORTH);
		GridBagLayout gbl_panelBusqueda = new GridBagLayout();
		gbl_panelBusqueda.columnWidths = new int[] { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
				0, 0 };
		gbl_panelBusqueda.rowHeights = new int[] { 14, 0, 10, 0, 0, 0, 0, 0 };
		gbl_panelBusqueda.columnWeights = new double[] { 1.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0,
				0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0, Double.MIN_VALUE };
		gbl_panelBusqueda.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, 1.0, 1.0, Double.MIN_VALUE };
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
		crearManejadorBtnBuscar(btnBuscar, this);
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

		resultadoBusqueda = new TablaBusqueda(MiniaturaVideo.class, new MiniaturaVideoTableRenderer(), ALTO_FILA_TABLA, ANCHO_COLUMNA_TABLA,
				NUM_COLUMNAS_RESULTADO);
		crearEventoRatonTabla(resultadoBusqueda);

		JScrollPane scroll = new JScrollPane(resultadoBusqueda);
		scroll.setMinimumSize(new Dimension(ANCHO_SCROLL_TABLA, ALTO_SCROLL_TABLA));
		scroll.setPreferredSize(new Dimension(ANCHO_SCROLL_TABLA, ALTO_SCROLL_TABLA));
		scroll.setMaximumSize(new Dimension(ANCHO_SCROLL_TABLA, ALTO_SCROLL_TABLA));
		scroll.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		panelResultados.add(scroll);

		panelCard.add(panelPrincipalBusqueda, "principal");
		panelCard.add(reproductor, "reproductor");

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
		public boolean isCellEditable(int row, int column) {
			return false;
		}
	}

	//---------------Manejador de los botones---------------------//
	
	private void crearManejadorBtnBuscar(JButton btnBuscar, JPanel panel) {
		btnBuscar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				boolean error = false;

				// Limpiar la búsqueda anterior
				resultadoBusqueda.limpiarTabla();
				// Realizar busqueda
				List<Video> resultados = null;

				if (txtBusqueda.getText().trim().isEmpty() && modelListaEtiquetasSel.size() > 0) {
					resultados = ControladorAppVideo.getUnicaInstancia().buscarVideos(null, getEtiquetasSelccionadas());
				} else if (modelListaEtiquetasSel.size() == 0 && !txtBusqueda.getText().trim().isEmpty()) {
					resultados = ControladorAppVideo.getUnicaInstancia().buscarVideos(txtBusqueda.getText(), null);
				} else if (!txtBusqueda.getText().trim().isEmpty() && modelListaEtiquetasSel.size() > 0) {
					resultados = ControladorAppVideo.getUnicaInstancia().buscarVideos(txtBusqueda.getText(),
							getEtiquetasSelccionadas());
				} else {
					// Mensaje de error
					JOptionPane.showMessageDialog(panel,
							"El campo de búsqueda está vacio y no ha etiquetas seleccionadas", "Error búsqueda",
							JOptionPane.ERROR_MESSAGE);
					error = true;
				}
				if (!error) {
					if (resultados.size() == 0) {
						JOptionPane.showMessageDialog(panel,
								"No se han encontrado resultados para " + "\"" + txtBusqueda.getText()
										+ "\" con las etiquetas seleccionadas",
								"Buscar", JOptionPane.INFORMATION_MESSAGE);
					} else {
						Object[][] data = resultadoBusqueda.obtenerTablaResultados(resultados, ANCHO_CELDA, ALTO_CELDA);
						resultadoBusqueda.setModel(new MyTableModel(data, new String[] { "", "", "", "" }));
						busquedaActual = resultados;
					}
				}
			}
		});
	}

	private void crearManejadorBtnNuevBusqueda(JButton btn) {
		btn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				resultadoBusqueda.limpiarTabla();
				txtBusqueda.setText("");
				busquedaActual = null;
				modelListaEtiquetasSel = new DefaultListModel<String>();
				listEtiquetaSel.setModel(modelListaEtiquetasSel);
			}
		});
	}

	private void crearEventoRatonTabla(JTable tabla) {
		tabla.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					int videoSeleccionado = tabla.getSelectedRow() * NUM_COLUMNAS_RESULTADO + tabla.getSelectedColumn();
					CardLayout cl = null;
					if (videoSeleccionado < busquedaActual.size()) {
						ControladorAppVideo.getUnicaInstancia()
								.añadirVideoReciente(busquedaActual.get(videoSeleccionado));
						((PanelReproductor) reproductor).reproducirVideo(AppMain.videoWeb,
								busquedaActual.get(videoSeleccionado));
						ControladorAppVideo.getUnicaInstancia()
								.nuevaReproduccion(busquedaActual.get(videoSeleccionado));
						cl = ((CardLayout) panelCard.getLayout());
						cl.show(panelCard, "reproductor");
					}
				}
			}
		});
	}

	public void updateChanges() {
		modelListaEtiquetas = new DefaultListModel<String>();
		modelListaEtiquetas.addAll(ControladorAppVideo.getUnicaInstancia().getEtiquetasVideos());
		listEtiquetas.setModel(modelListaEtiquetas);
		CardLayout cl = ((CardLayout) panelCard.getLayout());
		cl.show(panelCard, "principal");
	}

	private void crearEventoRatonLista(JList<String> lista) {
		lista.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2 && !modelListaEtiquetasSel.contains(modelListaEtiquetas.get(lista.getSelectedIndex()))) {
					modelListaEtiquetasSel.addElement(modelListaEtiquetas.get(lista.getSelectedIndex()));
				}
			}
		});
	}

	private void crearEventoRatonRemoveFromLista(JList<String> lista) {
		lista.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				if (e.getClickCount() == 2) {
					modelListaEtiquetasSel.remove(lista.getSelectedIndex());
				}
			}
		});
	}

	// Metodo para obtener todas las etiquetas seleccionadas a la hora de realizar una busqueda.
	private List<String> getEtiquetasSelccionadas() {

		LinkedList<String> etiquetas = new LinkedList<String>();

		for (int i = 0; i < modelListaEtiquetasSel.size(); i++) {
			etiquetas.add(modelListaEtiquetasSel.get(i));
		}

		return etiquetas;
	}
}