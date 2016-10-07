package main;

/**
 * Die verwendeten Kontonamen
 * 
 * @author Thomas Nill
 * 
 */
public enum KontenName {
    Kasse(100), Kunde(200), Hersteller(300), ForderungKasse(105), AuszahlungKunde(
            205), ForderungHersteller(305), ZahlungKasse(401), ZahlungHersteller(
            403), RueckstellungenKasse(110), AbsetzungKasse(120);

    private final int nr;

    public int getNr() {
        return nr;
    }

    KontenName(int nr) {
        this.nr = nr;
    };

}
