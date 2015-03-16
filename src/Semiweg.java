import java.util.ArrayList;


public class Semiweg {
	/**
	 * Attribute
	 */
	private ArrayList<Integer> semiwegKnoten;
	/**
	 * Konstruktor
	 */
	public Semiweg() {
		this.semiwegKnoten = new ArrayList<Integer>();
	}
	/**
	 * hinzufügen eines Knotens zum Semiweg von s nach t
	 */
	public void addSemiwegKnoten(Integer semiwegKnoten) {
		if (!this.semiwegKnoten.contains(semiwegKnoten)) {
			this.semiwegKnoten.add(semiwegKnoten);
		}
	}
	/**
	 * Ausgabe des Semiwegs
	 */
	public void printSemiweg() {
		StringBuilder sb = new StringBuilder();
		sb.append("Pfad: [");

		for(int i = 0; i < semiwegKnoten.size(); i++) {
			if(i == semiwegKnoten.size() - 1) {
				sb.append(i + 1);
			} else {
				sb.append(i + 1).append(" --> ");
			}
		}
			
		sb.append("]");
		System.out.println(sb.toString());
	}
}