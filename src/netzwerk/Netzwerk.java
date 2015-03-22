package netzwerk;
import java.util.Map;

import kanten.GerichteteKante;
import knoten.Knoten;

import org.apache.commons.collections15.map.HashedMap;

import edu.uci.ics.jung.graph.DirectedSparseMultigraph;

/**
 * Klasse Netzwerk
 * abgeleitet von Klasse DirectedSparseMultigraph des Frameworks von JUNG 
 */
public class Netzwerk extends DirectedSparseMultigraph<Knoten, GerichteteKante>{
	/**
	 * Attribute
	 */
	private static final long serialVersionUID = 1L;
	private Map<Integer, Knoten> idKnotenMap = new HashedMap<Integer, Knoten>();
	/**
	 * Konstruktor
	 */
	public Netzwerk() {
		super();
	}
	/**
	 * Überschreiben der Standardmethode zum Hinzufügen von Knoten
	 * Prüfung, ob Knoten bereits enthalten
	 */
	@Override
	public boolean addVertex(Knoten vertex) {
		boolean result = super.addVertex(vertex);
		if (result && idKnotenMap.containsKey(vertex.getId()) == false) {
			idKnotenMap.put(vertex.getId(), vertex);
		}
		return result;
	}
	/**
	 * Überschreiben der Standardmethode zum Löschen von Knoten
	 * Prüfung, ob Knoten bereits enthalten
	 */
	@Override
	public boolean removeVertex(Knoten knoten) {
		boolean result = super.removeVertex(knoten);
		if (result) {
			idKnotenMap.remove(knoten.getId());
		}
		return result;
	}
	/**
	 * Rückgabe des Knotens mit übergebener ID
	 * @param id
	 * @return
	 */
	public Knoten getVertexById(Integer id) {
		return idKnotenMap.get(id);
	}
	/**
	 * Rückgabe des Flusses
	 * @return
	 */
	public int getFluss() {
		int fluss = 0;
		Knoten quelle = null;
		for (Knoten knoten : this.getVertices()) {
			if (knoten.isQuelle()) {
				quelle = knoten;
			}
		}
		for (GerichteteKante outKante : this.getOutEdges(quelle)) {
			fluss  += outKante.getFluss();
		}
		return fluss;
	}
	/**
	 * Rückgabe der Flusskosten	
	 * @return
	 */
	public int getFlussKosten() {
		int kosten = 0;
		for (GerichteteKante kante : this.getEdges()) {
			kosten += kante.getFluss() * kante.getKosten();
		}
		return kosten;
	}
}
