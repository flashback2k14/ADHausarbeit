package kanten;
/**
 * Klasse InkrementKante
 * abgeleitet von Klasse GerichteteKante
 */
public class InkrementKante extends GerichteteKante {
	/**
	 * Konstruktor
	 * @param kosten
	 * @param kapazitaet
	 */
	public InkrementKante(Integer kosten, Integer kapazitaet) {
		super(kosten, kapazitaet,0);
	}
	/**
	 * Ausgabe Inkrementkante
	 */
	@Override
	public String toString() {
		return "Kosten: "+this.getKosten() + " Kapaz: " + this.getKapzitaet();
	}
}
