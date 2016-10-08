package test;

import java.util.List;

import javax.swing.JFrame;

import main.BewegungRenderer;
import main.MainFrameResize;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import buchhaltung.modell.Bewegung;
import buchhaltung.modell.Buchung;
import buchhaltung.modell.DB;
import buchhaltung.modell.Konto;
import buchhaltung.views.FontResize;
import buchhaltung.views.FontScale;
import buchhaltung.views.KontoPanel;
import buchungsfabriken.EinfacheBuchungsFabrik;

public class BuchhaltungTest {

    @Before
    public void setUp() {
        DB.insert(new Konto(100, "test1"));
        DB.insert(new Konto(200, "test2"));
        DB.insert(new Konto(300, "test3"));
    }

    @Test
    public void testBuchungen() {
        EinfacheBuchungsFabrik fabrik = new EinfacheBuchungsFabrik(100, 200,
                20.20);
        fabrik.setArt(2);
        Buchung buchung = fabrik.createBuchung();
        Assert.assertEquals("Art gleich", fabrik.getArt(), buchung.getArt());
        Assert.assertEquals("Text gleich", fabrik.getText(), buchung.getText());
        List<Bewegung> bewegungen = buchung.getBewegungen();
        Assert.assertEquals("Anzahl Bewegungen", 2, bewegungen.size());
        Bewegung b100 = bewegungen.get(0);
        Assert.assertEquals("Konto 100", 100, b100.getKonto().getNr());
        Assert.assertEquals("Betrag 1", 20.20, b100.getBetrag(), 0.001);

        Bewegung b200 = bewegungen.get(1);
        Assert.assertEquals("Konto 200", 200, b200.getKonto().getNr());
        Assert.assertEquals("Betrag 1", -20.20, b200.getBetrag(), 0.001);
    }

    public static void testCreateKontoPanel() {
        JFrame f = new JFrame();
        KontoPanel kp = new KontoPanel(new BewegungRenderer(),new FontScale());
        kp.setKonto(DB.getKonto(100));
        f.getContentPane().add(kp);
        f.setSize(100, 200);
        f.setVisible(true);
    }

    public static void main(String args) {
        testCreateKontoPanel();
    }
}
