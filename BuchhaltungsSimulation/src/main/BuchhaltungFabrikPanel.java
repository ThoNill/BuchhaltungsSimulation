package main;

import java.awt.event.MouseEvent;
import java.util.Vector;

import javax.swing.JButton;

import buchhaltung.views.BuchungsFabrikPanel;
//import main.MainFrame;
import buchungsfabriken.BuchungsFabrik;

/**
 * Listen von Buttons, die die BuchungsFabriken aufrufen
 * 
 * @author Thomas Nill
 * 
 */
public class BuchhaltungFabrikPanel extends BuchungsFabrikPanel {
	private static final long serialVersionUID = 7655704756631710239L;


	public BuchhaltungFabrikPanel(Vector<BuchungsFabrik> fabriken) {
		super(fabriken);
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		JButton b = (JButton) arg0.getComponent();
		BuchungsFabrik f = (BuchungsFabrik) b.getAction();
		MainFrame.setKonten(f.getKontonummern().get(0), f
				.getKontonummern().get(1));

	}

}
