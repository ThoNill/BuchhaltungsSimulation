package buchungsfabriken;

import java.awt.event.ActionEvent;
import java.util.Vector;

import javax.swing.AbstractAction;

import buchhaltung.modell.Buchung;
import buchhaltung.modell.DB;



/**
 * Erzeugt standardisierte Buchungen 
 * 
 * @author Thomas Nill
 * 
 */
public abstract class BuchungsFabrik extends AbstractAction {
	private static final long serialVersionUID = -3898356509489220445L;
	private int art;
	private Vector<Integer> kontonummern = new Vector<Integer>();

	public void setArt(int art) {
		this.art = art;
	}

	public int getArt() {
		return art;
	}

	public String getText() {
		return (String) getValue(NAME);
	}

	public void setText(String text) {
		putValue(NAME, text);
	}
	
	public void setErlaeuterung(String text) {
		putValue(SHORT_DESCRIPTION, text);
	}


	public Vector<Integer> getKontonummern() {
		return kontonummern;
	}

	public void addKontoNummer(int nr) {
		kontonummern.addElement(new Integer(nr));
	}

	public void actionPerformed(ActionEvent arg0) {
		createBuchung();
	}

	public Buchung createBuchung() {
		Buchung buchung = new Buchung(getArt(), getText());
		int anz = kontonummern.size();
		for (int i = 0; i < anz - 1; i++) {
			buchung.addBewegung(DB.getKonto(kontonummern.get(i)), getBetrag(i));
		}
		;
		buchung.addAusgleich(DB.getKonto(kontonummern.get(anz - 1)));
		return buchung;
	}

	public abstract double getBetrag(int i);

}
