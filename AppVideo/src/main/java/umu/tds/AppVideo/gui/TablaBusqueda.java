package umu.tds.AppVideo.gui;

import java.awt.Color;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableColumnModel;

import umu.tds.AppVideo.modelo.Video;

// Clase para implementar una tabla de búsqueda.
public class TablaBusqueda extends JTable {

	
	private static final long serialVersionUID = 1L;
	private int numColumnas;

	public TablaBusqueda (Class<?> columnClass, TableCellRenderer renderer, int RowHeight, int ColumnWidth, int numColumnas) {
		this.getTableHeader().setReorderingAllowed(false);
		this.setShowVerticalLines(false);
		this.setShowHorizontalLines(false);
		this.setShowGrid(false);
		this.setBackground(new Color(240,240,240));
		this.setSelectionForeground(new Color(240,240,240));
		this.setSelectionBackground(new Color(240,240,240));
		
		this.setRowHeight(RowHeight);
		this.setDefaultRenderer(columnClass, renderer);
		resizeColumnWidth(ColumnWidth);
		this.numColumnas = numColumnas;
	}
	
	// Ajustas el tamaño de las columnas al especificado
	private void resizeColumnWidth(int width) {
	    //Se obtiene el modelo de la columna
	    TableColumnModel columnModel = this.getColumnModel();
	    
	    //Se obtiene el total de las columnas
	    for (int column = 0; column < this.getColumnCount(); column++) {
	        columnModel.getColumn(column).setMinWidth(width);
	    }
	}
	
	// vacia la tabla
	public void limpiarTabla() {
		this.setModel(new DefaultTableModel());
	}
	
	// Devuelve los resultados en forma de tabla para su posterior representación
	public Object [][] obtenerTablaResultados (List<Video> videos,int cellHeight, int cellWidth) {
		
		Object [][] data = new Object [(int) Math.ceil((double)videos.size()/numColumnas)][numColumnas];
		int pos = 0;
		int fila = 0;
		int columna = 0;
		while (pos < videos.size()) {
			data [fila][columna] = new MiniaturaVideo(videos.get(pos).getTitulo(), videos.get(pos).getUrl(), pos,cellWidth,cellHeight);
			pos++;
			columna++;
			if (columna == numColumnas) {
				fila++;
				columna = 0;
			}
		}
		return data;
	}
}
