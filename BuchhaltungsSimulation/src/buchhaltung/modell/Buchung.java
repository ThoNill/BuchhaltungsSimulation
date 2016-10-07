package buchhaltung.modell;

import java.util.ArrayList;
import java.util.List;

/**
 * Buchung
 * 
 * @author Thomas Nill
 * 
 */
public class Buchung extends UID {
    private List<Bewegung> bewegungen = new ArrayList<Bewegung>();
    private double summe;
    private int art;
    private String text;

    public Buchung(int art, String text) {
        super();
        this.art = art;
        this.text = text;
    }

    public int getArt() {
        return art;
    }

    public String getText() {
        return text;
    }

    public List<Bewegung> getBewegungen() {
        return bewegungen;
    }

    public void addBewegung(Bewegung bewegung) {
        bewegungen.add(bewegung);
        summe += bewegung.getBetrag();
    }

    public void addBewegung(Konto konto, double betrag) {
        new Bewegung(this, konto, betrag);
    }

    public void addAusgleich(Konto konto) {
        addBewegung(konto, -summe);
    }
}
// https://www.evangelische-beratung.info/pbs-stuttgart
// www.evangelische-beratung.info/pbs-stuttgart/antwort
// Urlaub mit Tochter aus geschiedener Ehe