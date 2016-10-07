package main;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;

import buchhaltung.modell.DB;
import buchhaltung.modell.Konto;
import buchhaltung.modell.Neustart;
import buchhaltung.views.BuchungsFabrikPanel;
import buchhaltung.views.FontResize;
import buchhaltung.views.KontoPanel;
import buchungsfabriken.BuchungsFabrik;
import buchungsfabriken.EinfacheBuchungsFabrik;
import buchungsfabriken.SaldoBuchungsFabrik;

/**
 * Simulationsprogramm für Herstellerbuchungen
 * 
 * @author Thomas Nill
 * 
 */
public class MainFrame extends JFrame {
    private static final long serialVersionUID = -4886328326638275741L;
    private KontoPanel kp1;
    private KontoPanel kp2;
    private MainFrameResize sizePlus = new MainFrameResize(true, this);
    private MainFrameResize sizeMinus = new MainFrameResize(false, this);

    private static MainFrame mFrame;

    public static void main(String args[]) {
        mFrame = new MainFrame();
    }

    public void setKonten(int nr1, int nr2) {
        kp1.setKonto(DB.getKonto(nr1));
        kp2.setKonto(DB.getKonto(nr2));
    }

    public MainFrame() {
        super();
        FontResize.initFontScale();
        initialisiereKonten();

        JScrollPane aktionsPanel = createAktionsPanel();

        JPanel oben = createZuruecksetzenKleinerGroesserPanel();
        JPanel kasse_kunde = createKasseKundePanel();
        JScrollPane kontenPanel = createKontenPanel();

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();

        JSplitPane rechtesPane1 = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                kasse_kunde, kontenPanel);
        rechtesPane1.setDividerLocation(dim.width / 2);

        JSplitPane inDieMitte = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                aktionsPanel, rechtesPane1);
        inDieMitte.setDividerLocation(dim.width / 4);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.getContentPane().setLayout(new BorderLayout());

        this.getContentPane().add(oben, BorderLayout.NORTH);
        this.getContentPane().add(inDieMitte, BorderLayout.CENTER);

        this.setBounds(0, 0, dim.width, dim.height);
        this.setTitle("Simulation Herstellerrabatt");
        this.setVisible(true);
    }

    private JScrollPane createAktionsPanel() {
        List<BuchungsFabrik> fabriken = createBuchungsFabriken();

        JScrollPane fabrikPanel = new JScrollPane(new BuchungsFabrikPanel(
                fabriken));
        return fabrikPanel;
    }

    private JPanel createZuruecksetzenKleinerGroesserPanel() {
        JButton neu = new JButton(new Neustart());
        JButton fontKleiner = new JButton(sizePlus);
        JButton fontGroesser = new JButton(sizeMinus);
        JPanel oben = new JPanel();
        oben.add(neu);
        oben.add(fontKleiner);
        oben.add(fontGroesser);
        return oben;
    }

    private JScrollPane createKontenPanel() {
        JPanel kontenPanel = new JPanel();
        kontenPanel.setLayout(new BoxLayout(kontenPanel, BoxLayout.Y_AXIS));

        for (Konto konto : DB.getKonten()) {
            KontoPanel kp = new KontoPanel(new BewegungRenderer(), sizePlus);
            kp.setKonto(konto);
            kp.init();
            kontenPanel.add(kp);
        }
        return new JScrollPane(kontenPanel);
    }

    private JPanel createKasseKundePanel() {
        JPanel kontenPanel = new JPanel();
        kontenPanel.setLayout(new BoxLayout(kontenPanel, BoxLayout.X_AXIS));
        kp1 = new KontoPanel(new BewegungRenderer(), sizePlus);
        kp1.setKonto(DB.getKonto(KontenName.Kasse.getNr()));
        kp1.init();
        kontenPanel.add(kp1);
        kp2 = new KontoPanel(new BewegungRenderer(), sizePlus);
        kp2.setKonto(DB.getKonto(KontenName.Kunde.getNr()));
        kp2.init();
        kontenPanel.add(kp2);
        return kontenPanel;
    }

    private List<BuchungsFabrik> createBuchungsFabriken() {
        List<BuchungsFabrik> fabriken = new ArrayList<BuchungsFabrik>();

        newEinfacheBuchungsFabrik(fabriken, BuchungsBeschreibung.RezeptEingang,
                KontenName.Kunde, KontenName.Kasse, 100.00);

        newEinfacheBuchungsFabrik(fabriken,
                BuchungsBeschreibung.Herstellerrabatt, KontenName.Kasse,
                KontenName.Hersteller, 20.00);

        newEinfacheBuchungsFabrik(fabriken,
                BuchungsBeschreibung.KundeOhneInkasso, KontenName.Kasse,
                KontenName.Kunde, 10.00);

        newEinfacheBuchungsFabrik(fabriken,
                BuchungsBeschreibung.GutschriftAnKunde, KontenName.Kunde,
                KontenName.Hersteller, 20.00);

        newEinfacheBuchungsFabrik(fabriken,
                BuchungsBeschreibung.Nachberechnung,
                KontenName.RueckstellungenKasse, KontenName.Hersteller, 20.00);

        newSaldoBuchungsFabrik(fabriken,
                BuchungsBeschreibung.AbsetzungHersteller,
                KontenName.RueckstellungenKasse, KontenName.ForderungKasse,
                false);

        newSaldoBuchungsFabrik(fabriken, BuchungsBeschreibung.RechnungAnKasse,
                KontenName.Kasse, KontenName.ForderungKasse);

        newSaldoBuchungsFabrik(fabriken,
                BuchungsBeschreibung.RechnungAnHersteller,
                KontenName.Hersteller, KontenName.ForderungHersteller);

        newSaldoBuchungsFabrik(fabriken,
                BuchungsBeschreibung.AuszahlungAnKunden, KontenName.Kunde,
                KontenName.AuszahlungKunde, false);

        newSaldoBuchungsFabrik(fabriken, BuchungsBeschreibung.ZahlungKasse,
                KontenName.ForderungKasse, KontenName.AuszahlungKunde);

        newSaldoBuchungsFabrik(fabriken,
                BuchungsBeschreibung.ZahlungHersteller,
                KontenName.ForderungHersteller, KontenName.AuszahlungKunde);

        return fabriken;
    }

    private void initialisiereKonten() {
        DB.insert(createKonto(KontenName.Kasse));
        DB.insert(createKonto(KontenName.Kunde));
        DB.insert(createKonto(KontenName.Hersteller));
        DB.insert(createKonto(KontenName.ForderungKasse));
        DB.insert(createKonto(KontenName.ForderungHersteller));
        DB.insert(createKonto(KontenName.AuszahlungKunde));
        DB.insert(createKonto(KontenName.RueckstellungenKasse));
    }

    private Konto createKonto(KontenName name) {
        return new Konto(name.getNr(), name.name());
    }

    private void newSaldoBuchungsFabrik(List<BuchungsFabrik> fabriken,
            BuchungsBeschreibung b, KontenName n1, KontenName n2,
            boolean sollSaldo) {
        SaldoBuchungsFabrik fabrik = new SaldoBuchungsFabrik(n1.getNr(),
                n2.getNr());
        fabrik.setSollSaldo(sollSaldo);
        fabrik.setArt(b.ordinal());
        fabrik.setText(b.getText());
        fabrik.setErlaeuterung(b.getErlaeuterung());
        fabriken.add(fabrik);
    }

    private void newSaldoBuchungsFabrik(List<BuchungsFabrik> fabriken,
            BuchungsBeschreibung b, KontenName n1, KontenName n2) {
        newSaldoBuchungsFabrik(fabriken, b, n1, n2, true);
    }

    private void newEinfacheBuchungsFabrik(List<BuchungsFabrik> fabriken,
            BuchungsBeschreibung b, KontenName n1, KontenName n2, double betrag) {
        EinfacheBuchungsFabrik fabrik = new EinfacheBuchungsFabrik(n1.getNr(),
                n2.getNr(), betrag);
        fabrik.setArt(b.ordinal());
        fabrik.setText(b.getText());
        fabrik.setErlaeuterung(b.getErlaeuterung());

        fabriken.add(fabrik);
    }

    public static MainFrame getMainFrame() {
        return mFrame;
    }

}
