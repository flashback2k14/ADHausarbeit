import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {

		String path = "";
		int quelle = 0;
		int senke = 0;
		int maxFlussstaerke = 0;		
		
		ArrayList<Integer[]> netzwerk = new ArrayList<Integer[]>();
		ArrayList<GerichteterGraph> gerichteteGraphen = new ArrayList<GerichteterGraph>();

		if (args[0].equals("1")) {
			path = "src/Daten12B.txt";
			netzwerk = Util.readFile(path);
			
			quelle = 4;
			senke = 16;
			maxFlussstaerke = 6;
			
		} else if (args[0].equals("2")) {
			path = "src/Uebung62.txt";
			netzwerk = Util.readFile(path);
			
			quelle = 1;
			senke = 6;
			maxFlussstaerke = 19;
			
		} else {
			path = args[0];
			netzwerk = Util.readFile(path);
			
			try {
				quelle = Integer.valueOf(args[1]);
				senke = Integer.valueOf(args[2]);
				maxFlussstaerke = Integer.valueOf(args[3]);
			} catch (NumberFormatException nfe) {
				System.out.println("Error: " + nfe.getMessage());
			}
		}
		
		if (netzwerk != null) {
			for (Integer[] graph : netzwerk) {
				GerichteterGraph gerichteterGraph = new GerichteterGraph(graph[0], graph[1], new FlussKapazitaetKostenWerte(graph[2], graph[3]));
				gerichteteGraphen.add(gerichteterGraph);
			}
			
			BusackerGowen busackerGowen = new BusackerGowen(quelle, senke, maxFlussstaerke, gerichteteGraphen);
			busackerGowen.printInitStatus();
			busackerGowen.starteAlgorithmus();
		}
	}
}