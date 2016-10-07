package buchungsfabriken;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import buchhaltung.modell.DB;
import buchhaltung.modell.Konto;

/**
 * Erzeugt saldierende Buchungen
 * 
 * @author Thomas Nill
 * 
 */
public class SaldoBuchungsFabrik extends BuchungsFabrik implements
        PropertyChangeListener {
    private static final long serialVersionUID = 8590271640830774350L;

    Konto saldokonto = null;
    boolean sollSaldo = true;

    public SaldoBuchungsFabrik(int nr1, int nr2) {
        super();
        saldokonto = DB.getKonto(nr1);
        saldokonto.addPropertyChangedListener(this);
        addKontoNummer(nr1);
        addKontoNummer(nr2);
        calculateEnabled();
    }

    public boolean isSollSaldo() {
        return sollSaldo;
    }

    public void setSollSaldo(boolean sollSaldo) {
        this.sollSaldo = sollSaldo;
    }

    @Override
    public double getBetrag(int i) {
        return saldokonto.getSaldo();
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        calculateEnabled();

    }

    private void calculateEnabled() {
        setEnabled((sollSaldo) ? saldokonto.getSaldo() > 0 : saldokonto
                .getSaldo() < 0);
    }

}
