package umu.tds.AppVideo.gui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

public class MiniaturaVideoListRenderer extends DefaultListCellRenderer {

	private static final long serialVersionUID = 1L;

	@Override
	public Component getListCellRendererComponent(JList<?> list, Object value, int index,boolean isSelected, boolean cellHasFocus) {
		if (value != null && value instanceof MiniaturaVideo) {
			MiniaturaVideo ele  =(MiniaturaVideo) value;
			if (isSelected) {
				ele.setBackground(Color.PINK);
			}else {
				ele.setBackground(Color.WHITE);
			}
			return ele;
		}
		else return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
	}
}
