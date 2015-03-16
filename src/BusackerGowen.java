import java.util.ArrayList;

public class BusackerGowen {
	/**
	 * Attribute
	 */
	private Netzwerk netzwerk;
	private Netzwerk inkrementNetzwerk;
	private ArrayList<Semiweg> semiwege;
	/**
	 * Konstruktor
	 * @param netzwerk
	 */
	public BusackerGowen(Netzwerk netzwerk) {
		this.netzwerk = netzwerk;

		this.inkrementNetzwerk = new Netzwerk();
		this.semiwege = new ArrayList<Semiweg>();
	}
	/**
	 * Getter / Setter
	 */
	public Netzwerk getNetzwerk() {
		return netzwerk;
	}

	public void setNetzwerk(Netzwerk netzwerk) {
		this.netzwerk = netzwerk;
	}

	public Netzwerk getInkrementNetzwerk() {
		return inkrementNetzwerk;
	}

	public void setInkrementNetzwerk(Netzwerk inkrementNetzwerk) {
		this.inkrementNetzwerk = inkrementNetzwerk;
	}
	/**
	 * Ausgabe der Netzwerkdaten
	 */
	public void printInitStatus() {
		System.out.println("---------------------------------------");
		System.out.println("Quelle ist Knoten: " + netzwerk.getQuelle());
		System.out.println("Senke ist Knoten: " + netzwerk.getSenke());
		System.out.println("Flussstärke: " + netzwerk.getMaxFlussstaerke());
//		System.out.println("---------------------------------------");
//		System.out.println("Netzwerk\n");
//		netzwerk.printInitNetzwerk();
	}
	/**
	 * ToDo
	 * Busacker-Gowen-Algorithmus
	 */
	public void starteAlgorithmus() {
		System.out.println("---------------------------------------");
		System.out.println("Netzwerk initialisiert mit Nullwerten\n");
		netzwerk.printNetzwerk();
		//
		erstelleInkrementNetzwerk();
		//
		System.out.println("---------------------------------------");
		System.out.println("Inkrementnetzwerk\n");
		inkrementNetzwerk.printNetzwerk();
		//
		findeKuerzestenWeg();
	}
	/**
	 * ToDo
	 * 
	 */
	private void erstelleInkrementNetzwerk() {
		for (GerichteterGraph graph : getNetzwerk().getGerichteteGraphen()) {

			int gKnoten1 = graph.getKnoten1();
			int gKnoten2 = graph.getKnoten2();
			int gFluss = graph.getFlussKapazitaetKostenWerte().getFluss();
			int gKapazitaet = graph.getFlussKapazitaetKostenWerte().getKapazitaet();
			int gKosten = graph.getFlussKapazitaetKostenWerte().getKosten();

			if ((0 <= gFluss) && (gFluss <= gKosten)) {
				if ((gFluss != 0) && ((gFluss - gKosten) != 0)) {
					inkrementNetzwerk.addGerichtetenGraph(new GerichteterGraph(
							gKnoten1, gKnoten2, new FlussKapazitaetKostenWerte(
									-gFluss, gKapazitaet, gKosten)));
					
					inkrementNetzwerk.addGerichtetenGraph(new GerichteterGraph(
							gKnoten2, gKnoten1, new FlussKapazitaetKostenWerte(
									gFluss, gKapazitaet, gKosten)));
				}
			}
		}
	}
	/**
	 * ToDo
	 * Bellman-Ford-Algorithmus
	 */
	private void findeKuerzestenWeg() {
		System.out.println("---------------------------------------");
		System.out.println("Durchsuche Netzwerk nach kürzeste Wege\n");
		//
		int maxKnoten = NetzwerkUtil.getMaxKnoten(netzwerk);
		int minKosten = 0;
		Semiweg semiweg = new Semiweg();
		
		for(int i = 0; i < maxKnoten; i++) {
			for(int j = 0; j < maxKnoten; j++) {
				if(NetzwerkUtil.existsPfadZwischenKnoten1undKnoten2(netzwerk, NetzwerkUtil.getKnoten(i), NetzwerkUtil.getKnoten(j))) {
					
					minKosten = NetzwerkUtil.getKostenZwischenKnoten1undKnoten2(netzwerk, NetzwerkUtil.getKnoten(i), NetzwerkUtil.getKnoten(j));
					semiweg.addSemiwegKnoten(Integer.valueOf(NetzwerkUtil.getKnoten(i)));
					semiweg.addSemiwegKnoten(Integer.valueOf(NetzwerkUtil.getKnoten(j)));
				}
			}
		}
		semiwege.add(semiweg);
		//
		printSemiwege();
	}
	/**
	 * Ausgabe der Semiwege von s nach t
	 */
	private void printSemiwege() {
		System.out.println("---------------------------------------");
		System.out.println("Semiwege\n");
		for (Semiweg weg : semiwege) {
			weg.printSemiweg();
		}
	}
}
