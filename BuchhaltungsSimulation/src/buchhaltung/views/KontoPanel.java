package buchhaltung.views;

import java.awt.Dimension;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.EtchedBorder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;

import buchhaltung.modell.Bewegung;
import buchhaltung.modell.Konto;

/**
 * Visuelle Darstellung der Bewegungen auf einem Kontos
 * 
 * @author Thomas Nill
 * 
 */
public class KontoPanel extends JPanel implements PropertyChangeListener {
    private static final long serialVersionUID = 2391263194747078324L;
    private Konto konto;
    private JLabel kontoname = new JLabel();
    private JTable tabelle = new JTable();
    private transient TableCellRenderer cellRenderer;
    private FontScale scale;

    public KontoPanel(TableCellRenderer cellRenderer, FontScale scale) {
        super();
        this.cellRenderer = cellRenderer;
        this.scale = scale;
    }

    public void init() {
        tabelle.setIntercellSpacing(new Dimension(5, 5));
        tabelle.setRowHeight((int) (tabelle.getRowHeight()
                * scale.getScale() * 1.5));
        kontoname.setBorder(new EtchedBorder(4));

        setBorder(new EtchedBorder(4));
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        JScrollPane sp = new JScrollPane(tabelle);
        add(kontoname);
        add(sp);
    }

    public void setKonto(Konto konto) {
        if (this.konto != null && (!this.konto.equals(konto))) {
            this.konto.removePropertyChangedListener(this);
        }
        if (konto != null && (!konto.equals(this.konto))) {
            konto.addPropertyChangedListener(this);
            kontoname.setText(konto.getBezeichnung());
            this.konto = konto;
            changeModel();
        }
    }

    private void changeModel() {
        DefaultTableModel model = new DefaultTableModel(new String[] { "Soll",
                "Haben", "Bewegung" }, konto.getBewegungen().size() + 1);

        int row = addBewegungen(model);
        addSummenzeile(model, row);

        tabelle.setModel(model);
        tabelle.getColumnModel().getColumn(0).setCellRenderer(cellRenderer);
        tabelle.getColumnModel().getColumn(1).setCellRenderer(cellRenderer);
        tabelle.getColumnModel().removeColumn(
                tabelle.getColumnModel().getColumn(2));

    }

    private void addSummenzeile(DefaultTableModel model, int row) {
        model.setValueAt(konto.getSoll(), row, 0);
        model.setValueAt(konto.getHaben(), row, 1);
        model.setValueAt(null, row, 2);
    }

    private int addBewegungen(DefaultTableModel model) {
        int row = 0;
        for (Bewegung bewegung : konto.getBewegungen()) {
            if (bewegung.getBetrag() < 0) {
                model.setValueAt(-bewegung.getBetrag(), row, 0);
                model.setValueAt(bewegung.getBuchung().getText(), row, 1);

            } else {
                model.setValueAt(bewegung.getBetrag(), row, 1);
                model.setValueAt(bewegung.getBuchung().getText(), row, 0);

            }
            model.setValueAt(bewegung, row, 2);
            row++;
        }
        return row;
    }

    @Override
    public void propertyChange(PropertyChangeEvent arg0) {
        changeModel();
    }

}
