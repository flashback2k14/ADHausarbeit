package algorithmen;

import kanten.GerichteteKante;
import knoten.Knoten;
import netzwerk.Netzwerk;
import wege.ErweiterbarerWeg;

import java.util.Collection;
import java.util.Map;
import java.util.Stack;

import org.apache.commons.collections15.map.HashedMap;


public class BellmanFord  {
    /**
     * Attribute
     */
	Collection<GerichteteKante> edges;
    Map<Knoten, Integer> dist = new HashedMap<Knoten, Integer>();
    Map<Knoten, Knoten> pred = new HashedMap<Knoten, Knoten>();
    final Integer INFINITY = Integer.MAX_VALUE;
    /**
     * Konstruktor + Initialisierung der benötigten Werte zum Finden
     * des kürzesten Weges
     * @param netz
     * @throws Exception
     */
    public BellmanFord (Netzwerk netz) throws Exception {
        /**
         * Initialisierung des Graphens
         */
        for (Knoten knoten : netz.getVertices()) {
			if (knoten.isQuelle()) {
				dist.put(knoten, 0);
			} else {
				dist.put(knoten, INFINITY);
			}
			
			pred.put(knoten, knoten);
		}
        /**
         * wiederholtes entspannen der Kanten
         */
        for (int i = 0; i < netz.getVertexCount(); i++) {
			for (GerichteteKante kante : netz.getEdges()) {
				Knoten start = netz.getSource(kante);
				Knoten end = netz.getDest(kante);
				Integer gewicht = kante.getKosten();
				
				if ((dist.get(start) != Integer.MAX_VALUE) && (dist.get(start) + gewicht < dist.get(end))) {
					dist.remove(end);
					dist.put(end, dist.get(start) + gewicht);
					pred.remove(end);
					pred.put(end,start);
				}
			}
		}
        /**
         * Prüfung auf Zyklen mit negativen Kosten / Gewicht
         */
        for (GerichteteKante kante : netz.getEdges()) {
			Knoten start = netz.getSource(kante);
			Knoten end = netz.getDest(kante);
			Integer gewicht = kante.getKosten();
        	
			if ((dist.get(start) != Integer.MAX_VALUE) && (dist.get(start) + gewicht < dist.get(end))) {
				throw new Exception("Graph enthält Zyklus mit negativen Kosten / Gewicht.");
			}
        }
    }
    /**
     * Rückgabe des kürzesten Weges
     * @param end
     * @return
     */
    public ErweiterbarerWeg getShortestPath(Knoten end) {
    	
    	Stack<Knoten> knotenStack = new Stack<Knoten>();
    	knotenStack.push(end);
    	Knoten current = end;
    	Knoten vorgaenger = pred.get(current);
    	
    	while (current != vorgaenger) {
    		knotenStack.push(vorgaenger);
    		current = vorgaenger;
    		vorgaenger = pred.get(current);
    	}
    	
    	ErweiterbarerWeg weg = new ErweiterbarerWeg();
    	while(knotenStack.isEmpty() == false) {
    		weg.addKnoten(knotenStack.pop());
    	}
    	
    	return weg;
    }
}