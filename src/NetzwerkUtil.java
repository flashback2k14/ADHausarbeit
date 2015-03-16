
public class NetzwerkUtil {
	/**
	 * Pr�fen ob Kante zwischen 2 Knoten existiert
	 * @param netzwerk
	 * @param knoten1
	 * @param knoten2
	 * @return
	 */
	public static boolean existsPfadZwischenKnoten1undKnoten2(Netzwerk netzwerk, int knoten1, int knoten2) {
		boolean hasPfadZwischenKnoten1undKnoten2 = false;
		
		for(GerichteteKante kante : netzwerk.getGerichteteKanten()) {
			if(kante.getKnoten1() == knoten1) {
				if(kante.getKnoten2() == knoten2) {
					hasPfadZwischenKnoten1undKnoten2 = true;
				}
			}
		}	
		return hasPfadZwischenKnoten1undKnoten2;
	}
	/**
	 * R�ckgabe von Objekt FlussKapazit�tKostenWerte zwischen 2 Knoten
	 * @param netzwerk
	 * @param knoten1
	 * @param knoten2
	 * @return
	 */
	public static FlussKapazitaetKostenWerte getFlussKapaKostenWerte(Netzwerk netzwerk, int knoten1, int knoten2) {
		FlussKapazitaetKostenWerte flussKapaKostenWerte = null;
		
		for(GerichteteKante kante : netzwerk.getGerichteteKanten()) {
			if(kante.getKnoten1() == knoten1) {
				if(kante.getKnoten2() == knoten2) {
					if (kante.getFlussKapazitaetKostenWerte() != null) {					
						flussKapaKostenWerte = kante.getFlussKapazitaetKostenWerte();
					}
				}
			}
		}
		return flussKapaKostenWerte;
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
	 * R�ckgabe des gr��sten Knotens
	 * @param netzwerk
	 * @return
	 */
	public static int getMaxKnoten(Netzwerk netzwerk) {
		int maxKnoten = 0;
		
		for(GerichteteKante kante : netzwerk.getGerichteteKanten()) {
			if(maxKnoten < kante.getKnoten2()) {
				maxKnoten = kante.getKnoten2();
			}
		}
		return maxKnoten;
	}
	/**
	 * R�ckgabe des Flusses zwischen 2 Knoten
	 * @param netzwerk
	 * @param knoten1
	 * @param knoten2
	 * @return
	 */
	public static int getFlussZwischenKnoten1undKnoten2(Netzwerk netzwerk, int knoten1, int knoten2) {
		return getFlussKapaKostenWerte(netzwerk, knoten1, knoten2).getFluss();
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
		getFlussKapaKostenWerte(netzwerk, knoten1, knoten2).setFluss(value);
	}
	/**
	 * R�ckgabe der Kapazit�t zwischen 2 Knoten
	 * @param netzwerk
	 * @param knoten1
	 * @param knoten2
	 * @return
	 */
	public static int getKapazitaetZwischenKnoten1undKnoten2(Netzwerk netzwerk, int knoten1, int knoten2) {
		return getFlussKapaKostenWerte(netzwerk, knoten1, knoten2).getKapazitaet();
	}
	/**
	 * Setzen der Kapazit�t zwischen 2 Knoten
	 * @param netzwerk
	 * @param knoten1
	 * @param knoten2
	 * @param value
	 * @return
	 */
	public static void setKapazitaetZwischenKnoten1undKnoten2(Netzwerk netzwerk, int knoten1, int knoten2, int value) {
		getFlussKapaKostenWerte(netzwerk, knoten1, knoten2).setKapazitaet(value);
	}
	/**
	 * R�ckgabe der Kosten zwischen 2 Knoten (read only)
	 * @param netzwerk
	 * @param knoten1
	 * @param knoten2
	 * @return
	 */
	public static int getKostenZwischenKnoten1undKnoten2(Netzwerk netzwerk, int knoten1, int knoten2) {
		return getFlussKapaKostenWerte(netzwerk, knoten1, knoten2).getKosten();
	}
}
