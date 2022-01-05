package umu.tds.AppVideo.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class MiniaturaVideoTableRenderer extends DefaultTableCellRenderer {

	/**
	 * Clase para el render de las celdas de la tabla.
	 */
	private static final long serialVersionUID = 1L;
	
	@Override
	public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,int row, int column) {
		
		
		if (value != null && value instanceof MiniaturaVideo) {
			MiniaturaVideo ele  =(MiniaturaVideo) value;
			if (table.getSelectedRow()*4 + table.getSelectedColumn() == ele.getId()) {
				ele.setBackground(Color.PINK);
			}else {
				ele.setBackground(new Color(240,240,240));
			}
			return ele;
		}
		else return super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
	}
}
