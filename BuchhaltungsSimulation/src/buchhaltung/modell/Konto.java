package buchhaltung.modell;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Vector;


/**
 * Konto
 * 
 * @author Thomas Nill
 * 
 */
public class Konto extends UID {

	private PropertyChangeSupport changeHelper = null;
	private Vector<Bewegung> bewegungen = new Vector<Bewegung>();
	private double soll;
	private double haben;
	private int nr;
	private String bezeichnung;

	public Konto(int nr, String bezeichnung) {
		super();
		this.nr = nr;
		this.bezeichnung = bezeichnung;
		changeHelper = new PropertyChangeSupport(this);
	}


	public void clear() {
		bewegungen = new Vector<Bewegung>();
		soll = 0.0;
		haben = 0.0;
		changed();
	}
	
	public double getSoll() {
		return soll;
	}
	
	public double getHaben() {
		return haben;
	}
	
	
	public double getSaldo() {
		return soll - haben;
	}
	

	public int getNr() {
		return nr;
	}

	public String getBezeichnung() {
		return bezeichnung;
	}

	public void addPropertyChangedListener(PropertyChangeListener l) {
		changeHelper.addPropertyChangeListener(l);
	}

	public void removePropertyChangedListener(PropertyChangeListener l) {
		changeHelper.removePropertyChangeListener(l);
	}

	private void changed() {
		changeHelper.firePropertyChange("addBewegung", null, null);
	}

	public Vector<Bewegung> getBewegungen() {
		return bewegungen;
	}

	public void addBewegung(Bewegung bewegung) {
		bewegungen.addElement(bewegung);
		double betrag = bewegung.getBetrag();
		if (betrag < 0) {
			soll += (-betrag);
		} else {
			haben += betrag;
		}
		changed();
	}

}
