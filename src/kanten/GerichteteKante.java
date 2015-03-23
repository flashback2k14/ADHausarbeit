package kanten;

public class GerichteteKante {
	/**
	 * Attribute
	 */
	private int kosten, kapzitaet, fluss;

	/**
	 * Konstruktor
	 * 
	 * @param kosten
	 * @param kapazitaet
	 * @param fluss
	 */
	public GerichteteKante(int kosten, int kapazitaet, int fluss) {
		this.kosten = kosten;
		this.kapzitaet = kapazitaet;
		this.fluss = fluss;
	}

	/**
	 * Getter / Setter Kosten
	 * 
	 * @return
	 */
	public int getKosten() {
		return kosten;
	}

	public void setKosten(int kosten) {
		this.kosten = kosten;
	}

	/**
	 * Getter / Setter Kapazität
	 * 
	 * @return
	 */
	public int getKapzitaet() {
		return kapzitaet;
	}

	public void setKapzitaet(int kapzitaet) {
		this.kapzitaet = kapzitaet;
	}

	/**
	 * Getter / Setter Fluss
	 * 
	 * @return
	 */
	public int getFluss() {
		return fluss;
	}

	public void setFluss(int fluss) {
		this.fluss = fluss;
	}

	/**
	 * Ausgabe gerichtete Kante
	 */
	@Override
	public String toString() {
		return "\nKosten " + this.kosten + " Fluss: " + this.fluss + "/"
				+ this.kapzitaet + "\n";
	}
}
