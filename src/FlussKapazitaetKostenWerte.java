
public class FlussKapazitaetKostenWerte {
	/**
	 * Attribute
	 */
	private int fluss;
	private int kapazitaet;
	private int kosten;
	/**
	 * Konstruktor
	 * @param fluss
	 * @param kapazitaet
	 * @param kosten
	 */
	public FlussKapazitaetKostenWerte(int fluss, int kapazitaet, int kosten) {
		this.fluss = fluss;
		this.kapazitaet = kapazitaet;
		this.kosten = kosten;
	}
	/**
	 * Konstruktor
	 * @param kapazitaet
	 * @param kosten
	 */
	public FlussKapazitaetKostenWerte(int kapazitaet, int kosten) {
		this.fluss = 0;
		this.kapazitaet = kapazitaet;
		this.kosten = kosten;
	}
	/**
	 * Getter / Setter
	 */
	public int getFluss() {
		return fluss;
	}

	public void setFluss(int fluss) {
		this.fluss = fluss;
	}

	public int getKapazitaet() {
		return kapazitaet;
	}

	public void setKapazitaet(int kapazitaet) {
		this.kapazitaet = kapazitaet;
	}

	public int getKosten() {
		return kosten;
	}

	public void setKosten(int kosten) {
		this.kosten = kosten;
	}
	/**
	 * Ausgabe der Werte von Fluss, Kapazität und Kosten
	 * @return
	 */
	public String printFlussKapazitaetKostenWerte() {
		return getFluss() + "/" + getKapazitaet() + "/" + getKosten();
	}
}