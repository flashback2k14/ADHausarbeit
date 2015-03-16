import java.util.ArrayList;


public class Netzwerk {
	/**
	 * Attribute
	 */
	private int quelle;
	private int senke;
	private int maxFlussstaerke;
	private ArrayList<GerichteterGraph> gerichteteGraphen;
	/**
	 * Konstruktor
	 */
	public Netzwerk() {
		this.quelle = 0;
		this.senke = 0;
		this.maxFlussstaerke = 0;
		this.gerichteteGraphen = new ArrayList<GerichteterGraph>();
	}	
	/**
	 * Getter / Setter
	 */	
	public int getQuelle() {
		return quelle;
	}

	public void setQuelle(int quelle) {
		this.quelle = quelle;
	}

	public int getSenke() {
		return senke;
	}

	public void setSenke(int senke) {
		this.senke = senke;
	}

	public int getMaxFlussstaerke() {
		return maxFlussstaerke;
	}

	public void setMaxFlussstaerke(int maxFlussstaerke) {
		this.maxFlussstaerke = maxFlussstaerke;
	}

	public ArrayList<GerichteterGraph> getGerichteteGraphen() {
		return gerichteteGraphen;
	}

	public void setGerichteteGraphen(ArrayList<GerichteterGraph> gerichteteGraphen) {
		this.gerichteteGraphen = gerichteteGraphen;
	}
	/**
	 * hinzufügen eines Graphen zum Netzwerk
	 */
	public void addGerichtetenGraph(GerichteterGraph gerichteterGraph) {
		this.gerichteteGraphen.add(gerichteterGraph);
	}
	/**
	 * Ausgabe aller Graphen ohne Flussstärke
	 */
	public void printNetzwerk() {		
		
		StringBuilder sb = new StringBuilder();
		int maxKnoten = NetzwerkUtil.getMaxKnoten(this);
					
		for(int i = 0; i < maxKnoten; i++) {
			for(int j = 0; j < maxKnoten; j++) {
				
				FlussKapazitaetKostenWerte tmp = NetzwerkUtil.returnFlussKapaKostenWerte(this, (i + 1), (j + 1));
				
				if(tmp != null ) {
					sb.append("<" + (i + 1) + "," + (j + 1) + "," + tmp.printFlussKapazitaetKostenWerte() + ">");	
				} else {
					sb.append("<" + (i + 1) + "," + (j + 1)+ ",-/-/-" + ">");	
				}
				sb.append("\t");
			}		
			sb.append("\n");
		}
		System.out.println(sb.toString());			
	}
	/**
	 * Ausgabe aller Graphen mit Flussstärke
	 */
//	public void printNetzwerk() {
//		for(GerichteterGraph graph : getGerichteteGraphen()) {
//			graph.printGerichtetenGraph();
//		}
//	}
}
