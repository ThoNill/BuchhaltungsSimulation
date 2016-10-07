package buchungsfabriken;

/**
 * Erzeugt eine Buchung mit einem gewissen Betrag
 * 
 * @author Thomas Nill
 * 
 */
public class EinfacheBuchungsFabrik extends BuchungsFabrik {
    private static final long serialVersionUID = -133003789675119884L;

    double betrag;

    public EinfacheBuchungsFabrik(int nr1, int nr2, double betrag) {
        super();
        this.betrag = betrag;
        addKontoNummer(nr1);
        addKontoNummer(nr2);
    }

    @Override
    public double getBetrag(int i) {
        return betrag;
    }

}
