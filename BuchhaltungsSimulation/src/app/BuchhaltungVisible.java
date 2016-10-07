package app;

import javax.swing.JFrame;

import main.BewegungRenderer;
import buchhaltung.modell.DB;
import buchhaltung.modell.Konto;
import buchhaltung.views.FontResize;
import buchhaltung.views.KontoPanel;
import buchungsfabriken.EinfacheBuchungsFabrik;

public class BuchhaltungVisible {

    public static void main(String args[]) {

        DB.insert(new Konto(100, "test1"));
        DB.insert(new Konto(200, "test2"));
        DB.insert(new Konto(300, "test3"));

        EinfacheBuchungsFabrik fabrik = new EinfacheBuchungsFabrik(100, 200,
                20.20);
        fabrik.setArt(2);
        fabrik.setText("Testbuchung");

        fabrik.createBuchung();

        JFrame f = new JFrame();
        KontoPanel kp = new KontoPanel(new BewegungRenderer(),new FontResize(true));
        kp.setKonto(DB.getKonto(100));
        kp.init();
        f.getContentPane().add(kp);
        f.setSize(100, 200);
        f.setVisible(true);
    }
}
