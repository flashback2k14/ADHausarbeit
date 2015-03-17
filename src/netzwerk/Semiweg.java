package netzwerk;
import java.util.ArrayList;


public class Semiweg {
	/**
	 * Attribute
	 */
	private ArrayList<String> besuchteKante;
	private int summeKosten;
	private int summeKapazitaet;
	/**
	 * Konstruktor
	 */
	public Semiweg() {
		this.besuchteKante = new ArrayList<String>();
	}
	/**
	 * Getter / Setter
	 */
	public int getSummeKosten() {
		return summeKosten;
	}

	public void setSummeKosten(int summeKosten) {
		this.summeKosten += summeKosten;
	}

	public int getSummeKapazitaet() {
		return summeKapazitaet;
	}

	public void setSummeKapazitaet(int summeKapazitaet) {
		this.summeKapazitaet += summeKapazitaet;
	}
	/**
	 * hinzufügen einer Kante zum Semiweg von s nach t
	 */
	public void setBesuchteKante(String besuchteKante) {
		this.besuchteKante.add(besuchteKante);
	}
	/**
	 * Ausgabe aller besuchten Kanten
	 */
	public ArrayList<String> getBesuchteKanten() {
		return besuchteKante;
	}
	/**
	 * Ausgabe des Semiwegs, Summe Kosten und Summe Kapazität
	 */
	public void printSemiweg() {
		StringBuilder sb = new StringBuilder();
		sb.append("Pfad: [");

		for(int i = 0; i < besuchteKante.size(); i++) {
			if(i == besuchteKante.size() - 1) {
				sb.append(besuchteKante.get(i));
			} else {
				sb.append(besuchteKante.get(i)).append(" --> ");
			}
		}
			
		sb.append("]").append("\n");
		sb.append("Summe Kosten: ").append(getSummeKosten()).append("\n");
		sb.append("Summe Kapazität: ").append(getSummeKapazitaet());
		System.out.println(sb.toString());
	}
}