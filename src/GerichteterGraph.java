
public class GerichteterGraph {

	private int knoten1;
	private int knoten2;
	private FlussKapazitaetKostenWerte flussKapazitaetKostenWerte;
	
	public GerichteterGraph(int knoten1, int knoten2, FlussKapazitaetKostenWerte flkako) {
		this.knoten1 = knoten1;
		this.knoten2 = knoten2;
		this.flussKapazitaetKostenWerte = flkako;
	}

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
	
	public void printInitGerichtetenGraph() {
		System.out.println("<" + getKnoten1() 
				+ "," + getKnoten2() 
				+ "," + getFlussKapazitaetKostenWerte().getKapazitaet()
				+ "," + getFlussKapazitaetKostenWerte().getKosten() + ">");
	}
	
	public void printGerichtetenGraph() {
		System.out.println("<" + getKnoten1() 
							+ "," + getKnoten2() 
							+ "," + getFlussKapazitaetKostenWerte().getFluss()
							+ "," + getFlussKapazitaetKostenWerte().getKapazitaet()
							+ "," + getFlussKapazitaetKostenWerte().getKosten() + ">");
	}
}
