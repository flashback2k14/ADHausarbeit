
public class NetzwerkUtil {
	/**
	 * Prüfen ob Kante zwischen 2 Knoten existiert
	 * @param netzwerk
	 * @param knoten1
	 * @param knoten2
	 * @return
	 */
	public static boolean existsPfadZwischenKnoten1undKnoten2(Netzwerk netzwerk, int knoten1, int knoten2) {
		boolean hasPfadZwischenKnoten1undKnoten2 = false;
		
		for(GerichteterGraph graph : netzwerk.getGerichteteGraphen()) {
			if(graph.getKnoten1() == knoten1) {
				if(graph.getKnoten2() == knoten2) {
					hasPfadZwischenKnoten1undKnoten2 = true;
				}
			}
		}	
		return hasPfadZwischenKnoten1undKnoten2;
	}
	/**
	 * Rückgabe von Objekt FlussKapazitätKostenWerte zwischen 2 Knoten
	 * @param netzwerk
	 * @param knoten1
	 * @param knoten2
	 * @return
	 */
	public static FlussKapazitaetKostenWerte returnFlussKapaKostenWerte(Netzwerk netzwerk, int knoten1, int knoten2) {
		FlussKapazitaetKostenWerte hasFlussKapaKostenWerte = null;
		
		for(GerichteterGraph graph : netzwerk.getGerichteteGraphen()) {
			if(graph.getKnoten1() == knoten1) {
				if(graph.getKnoten2() == knoten2) {
					if (graph.getFlussKapazitaetKostenWerte() != null) {					
						hasFlussKapaKostenWerte = graph.getFlussKapazitaetKostenWerte();
					}
				}
			}
		}
		return hasFlussKapaKostenWerte;
	}
	/**
	 * Umwandlung Index in Knoten 
	 * @param index
	 * @return
	 */
	public static int getKnoten(int index) {
		return index + 1;
	}
	/**
	 * Rückgabe des größsten Knotens
	 * @param netzwerk
	 * @return
	 */
	public static int getMaxKnoten(Netzwerk netzwerk) {
		int maxKnoten = 0;
		
		for(GerichteterGraph graph : netzwerk.getGerichteteGraphen()) {
			if(maxKnoten < graph.getKnoten2()) {
				maxKnoten = graph.getKnoten2();
			}
		}
		
		return maxKnoten;
	}
	/**
	 * Rückgabe des Flusses zwischen 2 Knoten
	 * @param netzwerk
	 * @param knoten1
	 * @param knoten2
	 * @return
	 */
	public static int getFlussZwischenKnoten1undKnoten2(Netzwerk netzwerk, int knoten1, int knoten2) {
		return returnFlussKapaKostenWerte(netzwerk, knoten1, knoten2).getFluss();
	}
	/**
	 * Setzen des Flusses zwischen 2 Knoten
	 * @param netzwerk
	 * @param knoten1
	 * @param knoten2
	 * @param value
	 * @return
	 */
	public static void setFlussZwischenKnoten1undKnoten2(Netzwerk netzwerk, int knoten1, int knoten2, int value) {
		returnFlussKapaKostenWerte(netzwerk, knoten1, knoten2).setFluss(value);
	}
	/**
	 * Rückgabe der Kapazität zwischen 2 Knoten
	 * @param netzwerk
	 * @param knoten1
	 * @param knoten2
	 * @return
	 */
	public static int getKapazitaetZwischenKnoten1undKnoten2(Netzwerk netzwerk, int knoten1, int knoten2) {
		return returnFlussKapaKostenWerte(netzwerk, knoten1, knoten2).getKapazitaet();
	}
	/**
	 * Setzen der Kapazität zwischen 2 Knoten
	 * @param netzwerk
	 * @param knoten1
	 * @param knoten2
	 * @param value
	 * @return
	 */
	public static void setKapazitaetZwischenKnoten1undKnoten2(Netzwerk netzwerk, int knoten1, int knoten2, int value) {
		returnFlussKapaKostenWerte(netzwerk, knoten1, knoten2).setKapazitaet(value);
	}
	/**
	 * Rückgabe der Kosten zwischen 2 Knoten (read only)
	 * @param netzwerk
	 * @param knoten1
	 * @param knoten2
	 * @return
	 */
	public static int getKostenZwischenKnoten1undKnoten2(Netzwerk netzwerk, int knoten1, int knoten2) {
		return returnFlussKapaKostenWerte(netzwerk, knoten1, knoten2).getKosten();
	}
}
