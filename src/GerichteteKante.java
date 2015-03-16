
public class GerichteteKante {
	/**
	 * Attribute
	 */
	private int knoten1;
	private int knoten2;
	private FlussKapazitaetKostenWerte flussKapazitaetKostenWerte;
	/**
	 * Konstruktor
	 * @param knoten1
	 * @param knoten2
	 * @param flkako
	 */
	public GerichteteKante(int knoten1, int knoten2, FlussKapazitaetKostenWerte flkako) {
		this.knoten1 = knoten1;
		this.knoten2 = knoten2;
		this.flussKapazitaetKostenWerte = flkako;
	}
	/**
	 * Getter / Setter
	 */
	public int getKnoten1() {
		return knoten1;
	}

	public void setKnoten1(int knoten1) {
		this.knoten1 = knoten1;
	}

	public int getKnoten2() {
		return knoten2;
	}

	public void setKnoten2(int knoten2) {
		this.knoten2 = knoten2;
	}

	public FlussKapazitaetKostenWerte getFlussKapazitaetKostenWerte() {
		return flussKapazitaetKostenWerte;
	}

	public void setFlussKapazitaetKostenWerte(FlussKapazitaetKostenWerte flkako) {
		this.flussKapazitaetKostenWerte = flkako;
	}	
	/**
	 * Ausgabe Graph ohne Flussstärke
	 */
//	public void printInitGerichtetenGraph() {
//		System.out.println("<" + getKnoten1() 
//				+ "," + getKnoten2() 
//				+ "," + getFlussKapazitaetKostenWerte().getKapazitaet()
//				+ "," + getFlussKapazitaetKostenWerte().getKosten() + ">");
//	}
	/**
	 * Ausgabe Graph mit Flussstärke
	 */
//	public void printGerichtetenGraph() {
//		System.out.println("<" + getKnoten1() 
//				+ "," + getKnoten2() 
//				+ "," + getFlussKapazitaetKostenWerte().getFluss()
//				+ "," + getFlussKapazitaetKostenWerte().getKapazitaet()
//				+ "," + getFlussKapazitaetKostenWerte().getKosten() + ">");
//	}
}
