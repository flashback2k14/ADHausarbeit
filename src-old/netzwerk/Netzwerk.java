package netzwerk;
import java.util.ArrayList;

import kante.FlussKapazitaetKostenWerte;
import kante.GerichteteKante;
import utilities.NetzwerkUtil;



public class Netzwerk {
	/**
	 * Attribute
	 */
	private int quelle;
	private int senke;
	private int maxFlussstaerke;
	private ArrayList<GerichteteKante> gerichteteKanten;

	/**
	 * Konstruktor
	 */
	public Netzwerk() {
		this.quelle = 0;
		this.senke = 0;
		this.maxFlussstaerke = 0;
		this.gerichteteKanten = new ArrayList<GerichteteKante>();
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

	public ArrayList<GerichteteKante> getGerichteteKanten() {
		return gerichteteKanten;
	}

	public void setGerichteteKanten(ArrayList<GerichteteKante> gerichteteKanten) {
		this.gerichteteKanten = gerichteteKanten;
	}

	/**
	 * hinzufügen einer Kante zum Netzwerk
	 */
	public void addGerichteteKante(GerichteteKante gerichteteKante) {
		this.gerichteteKanten.add(gerichteteKante);
	}

	/**
	 * Ausgabe aller Kanten
	 */
	public void printNetzwerk() {

		StringBuilder sb = new StringBuilder();
		int maxKnoten = NetzwerkUtil.getMaxKnoten(this);

		for (int i = 0; i < maxKnoten; i++) {
			for (int j = 0; j < maxKnoten; j++) {

				int knotenI = NetzwerkUtil.getKnoten(i);
				int knotenJ = NetzwerkUtil.getKnoten(j);
								
				FlussKapazitaetKostenWerte tmp = NetzwerkUtil.getFlussKapaKostenWerte(this,knotenI,knotenJ);

				if (tmp != null) {
					sb.append("<" + knotenI + "," + knotenJ + "," + tmp.printFlussKapazitaetKostenWerte() + ">");
				} else {
					sb.append("<" + knotenI + "," + knotenJ + ",-/-/-" + ">");
				}
				sb.append("\t");
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	
	public void printNetzwerkOhneNullwerte() {

		StringBuilder sb = new StringBuilder();
		int maxKnoten = NetzwerkUtil.getMaxKnoten(this);

		for (int i = 0; i < maxKnoten; i++) {
			for (int j = 0; j < maxKnoten; j++) {

				int knotenI = NetzwerkUtil.getKnoten(i);
				int knotenJ = NetzwerkUtil.getKnoten(j);
								
				if (NetzwerkUtil.existsPfadZwischenKnoten1undKnoten2(this,knotenI,knotenJ)) 
				{
					FlussKapazitaetKostenWerte tmp = NetzwerkUtil.getFlussKapaKostenWerte(this,knotenI,knotenJ);

					if (tmp != null) {
						sb.append("<" + knotenI + "," + knotenJ + "," + tmp.printFlussKapazitaetKostenWerte() + ">");
					} else {
						sb.append("<" + knotenI + "," + knotenJ + ",-/-/-" + ">");
					}
					sb.append("\t");
				}
			}
			sb.append("\n");
		}
		System.out.println(sb.toString());
	}
	/**
	 * Ausgabe aller Graphen mit Flussstärke
	 */
	// public void printNetzwerk() {
	// for(GerichteterGraph graph : getGerichteteGraphen()) {
	// graph.printGerichtetenGraph();
	// }
	// }
}
