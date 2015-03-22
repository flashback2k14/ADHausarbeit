package wege;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import netzwerk.Netzwerk;
import kanten.GerichteteKante;
import knoten.Knoten;

public class ErweiterbarerWeg {
	/**
	 * Attribute
	 */
	private List<Knoten> knotenListe = new ArrayList<Knoten>();
	private List<GerichteteKante> kantenListe = null;
	/**
	 * hinzufügen eines Knotens zur Knotenliste
	 * @param knoten
	 */
	public void addKnoten(Knoten knoten) {
		knotenListe.add(knoten);
	}
	/**
	 * Erstelle Kantenliste aus Inkrementnetzwerk
	 * @param inkrementNetzwerk
	 */
	private void erstelleKantenListe(Netzwerk inkrementNetzwerk) {
		if (kantenListe == null) {
			kantenListe = new ArrayList<GerichteteKante>();
			Iterator<Knoten> iterator = knotenListe.iterator(); 
			Knoten currentKnoten = iterator.next();
			while (iterator.hasNext()) {
				Collection<GerichteteKante> outKanten = inkrementNetzwerk.getOutEdges(currentKnoten);
				Knoten nextKnoten = iterator.next();
				GerichteteKante candidateKante =  null;
				for (GerichteteKante gerichteteKante : outKanten) {
					if (inkrementNetzwerk.getDest(gerichteteKante) == nextKnoten) {
						if (candidateKante == null) {
							candidateKante = gerichteteKante;
						} else {
							if (candidateKante.getKosten() > gerichteteKante.getKosten()) {
								candidateKante = gerichteteKante;
							}
						}
					}
				}
				if (candidateKante != null) {
					kantenListe.add(candidateKante);
				}
				currentKnoten = nextKnoten;
			}
		}
	}
	/**
	 * Rückgabe der Minimalen Kapazität des Inkrementnetzwerks
	 * @param inkrementNetzwerk
	 * @return
	 */
	public Integer getMinimaleKapazitaet(Netzwerk inkrementNetzwerk) {
		if (kantenListe == null) {
			erstelleKantenListe(inkrementNetzwerk);
		}
		
		Integer minKap = Integer.MAX_VALUE;
		
		for (GerichteteKante kante : kantenListe) {
			minKap = Math.min(minKap, kante.getKapzitaet());
		}
		return minKap;
	}
	/**
	 * Rückgabe der Kantenliste des Inkrementnetzwerks
	 * @param inkrementNetzwerk
	 * @return
	 */
	public List<GerichteteKante> getKantenListe(Netzwerk inkrementNetzwerk) {
		if (kantenListe == null) {
			erstelleKantenListe(inkrementNetzwerk);
		}
		return kantenListe;
	}
	/**
	 * Prüfung, ob Knotenliste leer ist
	 * @return
	 */
	public boolean isEmpty() {
		for (Knoten knoten : knotenListe) {
			if (knoten.isQuelle()) {
				return false;
			}
		}
		return true;
	}
	/**
	 * Ausgabe des Erweiterbaren Weges
	 */
	@Override
	public String toString() {
		StringBuilder mes = new StringBuilder();
		for (Knoten knoten : knotenListe) {
			mes.append(knoten.toString() + " ");
		}
		return mes.toString();
	}
}
