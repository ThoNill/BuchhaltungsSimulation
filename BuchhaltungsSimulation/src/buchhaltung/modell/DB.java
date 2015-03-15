package buchhaltung.modell;

import java.util.Vector;


/**
 * In Memory "Datenbanktabelle" der Konten
 * 
 * @author Thomas Nill
 * 
 */
public class DB {
	private static Vector<Konto> konten = new Vector<Konto>();

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
			konten.addElement(konto);
		}
	}

	public static Vector<Konto> getKonten() {
		return konten;
	}
}
