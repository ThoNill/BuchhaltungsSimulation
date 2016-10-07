package buchhaltung.views;

import java.awt.GridLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;

import buchungsfabriken.BuchungsFabrik;

/**
 * Listen von Buttons, die die BuchungsFabriken aufrufen
 * 
 * @author Thomas Nill
 * 
 */
public class BuchungsFabrikPanel extends JPanel implements MouseListener {
    private static final long serialVersionUID = 7655704756631710239L;

    public BuchungsFabrikPanel(List<BuchungsFabrik> fabriken) {
        setLayout(new GridLayout(fabriken.size(), 1));
        for (int i = 0; i < fabriken.size(); i++) {
            BuchungsFabrik f = fabriken.get(i);
            JButton b = new JButton();
            b.setAction(f);
            add(b);
            b.addMouseListener(this);
        }
    }

    @Override
    public void mouseClicked(MouseEvent arg0) {
    }

    @Override
    public void mouseEntered(MouseEvent arg0) {
    }

    @Override
    public void mouseExited(MouseEvent arg0) {
    }

    @Override
    public void mousePressed(MouseEvent arg0) {
    }

    @Override
    public void mouseReleased(MouseEvent arg0) {
    }

}
