package main;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

import buchhaltung.modell.Bewegung;

/**
 * Zellformatierung in der Tabelle der Bewegungen, setzt die Farbe und Ausrichtung 
 * 
 * @author Thomas Nill
 * 
 */

public class BewegungRenderer extends JLabel implements TableCellRenderer {

	private static final long serialVersionUID = 4948467334678498444L;

	public BewegungRenderer() {
		setOpaque(true);
		setBackground(Color.white);
	}

	public Component getTableCellRendererComponent(JTable table, Object value,
			boolean isSelected, boolean hasFocus, int row, int column) {
		TableModel m = table.getModel();
		if (m.getValueAt(row, 2) != null) {
			Bewegung bewegung = (Bewegung) m.getValueAt(row, 2);
			int art = bewegung.getBuchung().getArt();
			BuchungsBeschreibung b = BuchungsBeschreibung.values()[art];
			setForeground(b.getColor());
		} else {
			setForeground(Color.black);
		}

		setHorizontalAlignment((value instanceof Double) ? RIGHT : LEFT);
		setText(value.toString());
		return this;
	}

}
