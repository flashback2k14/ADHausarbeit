package algorithmus;
import java.util.ArrayList;

import kante.FlussKapazitaetKostenWerte;
import kante.GerichteteKante;
import netzwerk.Netzwerk;
import netzwerk.Semiweg;
import utilities.NetzwerkUtil;

public class BusackerGowen {
	/**
	 * Attribute
	 */
	private Netzwerk netzwerk;
	private Netzwerk inkrementNetzwerk;
	private ArrayList<Semiweg> semiwege;
	/**
	 * Konstruktor
	 * 
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
		System.out.println("----------------------------------------------------------");
		System.out.println("Kostenminimale Flüsse im Netzwerk");
		System.out.println("----------------------------------------------------------");
		System.out.println("Quelle ist Knoten: " + netzwerk.getQuelle());
		System.out.println("Senke ist Knoten: " + netzwerk.getSenke());
		System.out.println("Flussstärke: " + netzwerk.getMaxFlussstaerke());
		System.out.println("----------------------------------------------------------");
		System.out.println("Notation Graph:");
		System.out.println("<Knoten i, Knoten j, Fluss / Kapatität / Kosten>");
	} 
	/**
	 * ToDo Busacker-Gowen-Algorithmus
	 */
	public void starteAlgorithmus() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Netzwerk initialisiert mit Nullfluss\n");
		//netzwerk.printNetzwerk();
		netzwerk.printNetzwerkOhneNullwerte();
		//
		System.out.println("----------------------------------------------------------");
		System.out.println("Inkrementnetzwerk\n");
		erstelleInkrementNetzwerk(netzwerk);
		//inkrementNetzwerk.printNetzwerk();
		inkrementNetzwerk.printNetzwerkOhneNullwerte();
		//
		findeKuerzestenWeg(netzwerk);
	}
	/**
	 * ToDo
	 * Generiere Inkrementnetzwerk
	 * @param nw
	 */
	private void erstelleInkrementNetzwerk(Netzwerk nw) {
		
		int maxKnoten = NetzwerkUtil.getMaxKnoten(nw);
		
		for (int i = 0; i < maxKnoten; i++) {
			for (int j = 0; j < maxKnoten; j++) {
		
				int knotenI = NetzwerkUtil.getKnoten(i);
				int knotenJ = NetzwerkUtil.getKnoten(j);
				
				if (NetzwerkUtil.existsPfadZwischenKnoten1undKnoten2(nw, knotenI, knotenJ)) 
				{	
					if (NetzwerkUtil.getKapazitaetZwischenKnoten1undKnoten2(nw, knotenI, knotenJ) >= 0)
					{
						FlussKapazitaetKostenWerte tmp = NetzwerkUtil.getFlussKapaKostenWerte(nw, knotenI, knotenJ);
											
						inkrementNetzwerk.addGerichteteKante(new GerichteteKante(knotenI, knotenJ, 
								new FlussKapazitaetKostenWerte(-tmp.getFluss(), tmp.getKapazitaet(), tmp.getKosten())));

						inkrementNetzwerk.addGerichteteKante(new GerichteteKante(knotenJ, knotenI, 
								new FlussKapazitaetKostenWerte(tmp.getFluss(), tmp.getKapazitaet(), tmp.getKosten())));
					}
				}
			}
		}
	}
	/**
	 * ToDo 
	 * Bellman-Ford-Algorithmus
	 * @param nw
	 */
	private void findeKuerzestenWeg(Netzwerk nw) {
		System.out.println("----------------------------------------------------------");
		System.out.println("Durchsuche Netzwerk nach kürzeste Wege\n");
		//
		int maxKnoten = NetzwerkUtil.getMaxKnoten(nw);
		Semiweg semiweg = new Semiweg();

		for (int i = 0; i < maxKnoten; i++) {
			for (int j = 0; j < maxKnoten; j++) {
				
				int knotenI = NetzwerkUtil.getKnoten(i);
				int knotenJ = NetzwerkUtil.getKnoten(j);
				
				if (NetzwerkUtil.existsPfadZwischenKnoten1undKnoten2(nw,knotenI,knotenJ)) 
				{
					if (!istBereitsBesuchteKante(knotenI, knotenJ)) 
					{
						int kosten = NetzwerkUtil.getKostenZwischenKnoten1undKnoten2(nw,knotenI,knotenJ);
						semiweg.setBesuchteKante(knotenI + "," + knotenJ);
						semiweg.setSummeKosten(kosten);
						semiweg.setSummeKapazitaet(NetzwerkUtil.getKapazitaetZwischenKnoten1undKnoten2(nw,knotenI,knotenJ));
					}
				}
			}
		}
		semiwege.add(semiweg);
		//
		printSemiwege();
	}
	/**
	 * Finde 
	 * @param i
	 * @param j
	 * @return
	 */
	private boolean istBereitsBesuchteKante(int i, int j) {
		boolean bereitsBesucht = false;
		String übergebeneKante = i + "," + j;
		
		for (Semiweg sw : semiwege) {
			for (String kante : sw.getBesuchteKanten()) {
				if (kante.contentEquals(übergebeneKante)) {
					bereitsBesucht = true;
				}
			}
		}
			
		return bereitsBesucht;
	}
	/**
	 * Ausgabe der Semiwege von s nach t
	 */
	private void printSemiwege() {
		System.out.println("----------------------------------------------------------");
		System.out.println("Semiwege\n");
		for (Semiweg weg : semiwege) {
			weg.printSemiweg();
		}
	}
}
