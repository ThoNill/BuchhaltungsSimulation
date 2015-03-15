package buchhaltung.modell;

/**
 * Kontobewegungen einer Buchung
 * 
 * @author Thomas Nill
 * 
 */
public class Bewegung extends UID {
	private double betrag;
	private Konto konto;
	private Buchung buchung;

	public Bewegung(Buchung buchung, Konto konto, double betrag) {
		super();
		this.betrag = betrag;
		this.konto = konto;
		this.buchung = buchung;
		konto.addBewegung(this);
		buchung.addBewegung(this);
	}

	public double getBetrag() {
		return betrag;
	}

	public Konto getKonto() {
		return konto;
	}

	public Buchung getBuchung() {
		return buchung;
	}

}
