package buchhaltung.modell;

import java.util.ArrayList;
import java.util.List;

/**
 * In Memory "Datenbanktabelle" der Konten
 * 
 * @author Thomas Nill
 * 
 */
public class DB {
    private static List<Konto> konten = new ArrayList<Konto>();

    public static Konto getKonto(int nr) {
        for (Konto k : konten) {
            if (k.getNr() == nr) {
                return k;
            }
        }
        return null;
    }

    public static void insert(Konto konto) {
        if (konto != null && getKonto(konto.getNr()) == null) {
            konten.add(konto);
        }
    }

    public static List<Konto> getKonten() {
        return konten;
    }
}
