package main;

import java.awt.Color;


/**
 * Beschreibungen zu den einzelnen Buchungsarten
 * 
 * @author Thomas Nill
 * 
 */
public enum BuchungsBeschreibung {
	Herstellerrabatt("Herstellerrabatt",Color.green,"Herstellerrabatt vom Hersteller zur Kasse"),
	RezeptEingang("Rezepteingang",Color.blue,"normaler Rezepteingang vom Kunden"),
	KundeOhneInkasso("Kunde direkt mit Hersteller",Color.cyan,"Der Kunde rechnet mit einem Teil oder allen Herstellern, selbst ab"),
	GutschriftAnKunde("Gutschrift an Kunden",Color.gray,"Eine Krankenhausapotheke bekommt Ihren Herstellerrabatt vom Hersteller"),
	RechnungAnKasse("Rechnung an Kasse",Color.magenta,"Rechnungsstellung an die Kassen"),
	RechnungAnHersteller("Rechnung an Hersteller",Color.orange,"Rechnungsstellung an die Hersteller"),
	AuszahlungAnKunden("Auszahlung an Kunden",Color.darkGray,"Die Auszahlung an den Kunden"),
	ZahlungKasse("Zahlung der Kasse",Color.pink,"Die Kasse zahlt"),
	ZahlungHersteller("Zahlung des Herstellers",Color.red,"Der Hersteller zahlt"),
	Nachberechnung("Nachberechnung Herstellerrabatt",Color.black,"Frühere Rechnungen an Hersteller werden korrigiert"),
	AbsetzungHersteller("Absetzung Nachberechnung",Color.lightGray,"Die Kasse setzt einen Anspruch aus Nachberechnungen ab");
	
	private final String text;
	private final Color color;
	private final String erlaeuterung;

	public String getErlaeuterung() {
		return erlaeuterung;
	}


	public Color getColor() {
		return color;
	}


	public String getText() {
		return text;
	}


	BuchungsBeschreibung(String text,Color color,String erlaeuterung) {
		this.text = text;
		this.color = color;
		this.erlaeuterung = erlaeuterung;
	};

}
