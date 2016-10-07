package buchhaltung.modell;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

/**
 * Konten wieder zurücksetzen
 * 
 * @author Thomas Nill
 * 
 */

public class Neustart extends AbstractAction {

    private static final long serialVersionUID = 3560329301623878945L;

    public Neustart() {
        super("Neustart");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for (Konto konto : DB.getKonten()) {
            konto.clear();
        }

    }

}
