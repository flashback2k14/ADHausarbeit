import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		/**
		 * Attribute
		 */
		String path = "";		
		ArrayList<Integer[]> netzwerkData = new ArrayList<Integer[]>();
		Netzwerk netzwerk = new Netzwerk();
		/**
		 * Einlesen der Daten
		 * Setzen der Netzwerkdaten
		 */
		if (args[0].equals("1")) {
			path = "src/Daten12B.txt";
			netzwerkData = Util.readFile(path);
			netzwerk.setQuelle(4);
			netzwerk.setSenke(16);
			netzwerk.setMaxFlussstaerke(6);
			
		} else if (args[0].equals("2")) {
			path = "src/Uebung62.txt";
			netzwerkData = Util.readFile(path);
			netzwerk.setQuelle(1);
			netzwerk.setSenke(6);
			netzwerk.setMaxFlussstaerke(19);
			
		} else {
			try {
				path = args[0];
				netzwerkData = Util.readFile(path);
				netzwerk.setQuelle(Integer.valueOf(args[1]));
				netzwerk.setSenke(Integer.valueOf(args[2]));
				netzwerk.setMaxFlussstaerke(Integer.valueOf(args[3]));
			} catch (Exception e) {
				System.out.println("Error: " + e.getMessage());
			}
		}
		/**
		 * Wenn Netzwerkdaten ungleich null, dann füge
		 * - gerichtete Graphen zum Netzwerk hinzu 
		 * - Algorithmus starten
		 * - Ausgabe des Ergebnisses
		 */
		if (netzwerkData != null) {
			/**
			 * gerichtete Graphen zum Netzwerk hinzu
			 */
			for (Integer[] graph : netzwerkData) {
				GerichteteKante gerichteteKante = new GerichteteKante(graph[0], graph[1], new FlussKapazitaetKostenWerte(graph[2], graph[3]));
				netzwerk.addGerichteteKante(gerichteteKante);
			}
			/**
			 * ToDo
			 * Algorithmus starten
			 */
			BusackerGowen busackerGowen = new BusackerGowen(netzwerk);
			busackerGowen.printInitStatus();
			busackerGowen.starteAlgorithmus();
			/**
			 * ToDo
			 * Ausgabe des Ergebnisses
			 */
			
		}
	}
}