import java.util.ArrayList;


public class BusackerGowen {

	private int quelle;
	private int senke;
	private int maxFlussstaerke;
	private ArrayList<GerichteterGraph> gerichteteGraphen;
	private ArrayList<Semiweg> semiwege;
	
	public BusackerGowen(int quelle, int senke, int maxFlussstaerke, ArrayList<GerichteterGraph> gerichteteGraphen) {
		this.quelle = quelle;
		this.senke = senke;
		this.maxFlussstaerke = maxFlussstaerke;
		this.gerichteteGraphen = gerichteteGraphen;
		
		this.semiwege = new ArrayList<Semiweg>();
	}

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
	
	public void printInitStatus() {
		System.out.println("---------------------------------------");
		System.out.println("Quelle ist Knoten: " + getQuelle());
		System.out.println("Senke ist Knoten: " + getSenke());
		System.out.println("Flussstärke: " + getMaxFlussstaerke());
		System.out.println("---------------------------------------");
		System.out.println("Netzwerk\n");
		for(GerichteterGraph graph : getGerichteteGraphen()) {
			graph.printInitGerichtetenGraph();
		}
	}
	
	/**
	 * ToDo
	 */
	public void starteAlgorithmus() {
		System.out.println("---------------------------------------");
		System.out.println("Netzwerk initialisiert mit Nullwerten\n");
		for(GerichteterGraph graph : getGerichteteGraphen()) {
			graph.printGerichtetenGraph();
		}
		
	}
	
	/**
	 * ToDo
	 */
	private void findeKuerzestenWeg() {
		
	}
	
	public void printSemiwege() {
		System.out.println("---------------------------------------");
		System.out.println("Semiwege\n");
		for(Semiweg weg : semiwege) {
			weg.printSemiweg();
		}
	}
}
