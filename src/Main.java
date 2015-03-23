import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import kanten.GerichteteKante;
import knoten.Knoten;
import netzwerk.Netzwerk;
import utilities.Util;
import wege.ErweiterbarerWeg;
import algorithmen.BusackerGowen;

public class Main {

	public static void main(String[] args) {
		/**
		 * lokale Variablen fuer das Netzwerk
		 */
		String path = "";
		int quelle = 0;
		int senke = 0;
		int maxFlussstaerke = 0;
		//
		ArrayList<Integer[]> netzwerkData = new ArrayList<Integer[]>();
		Netzwerk netzwerk = new Netzwerk();
		ErweiterbarerWeg weg = null;
		/**
		 * Setzen der Netzwerkdaten anhand der Nutzerauswahl
		 */
		if ("1".equals(args[0])) {
			path = "src/daten/Daten12B.txt";
			netzwerkData = Util.readFile(path);
			quelle = 4;
			senke = 16;
			maxFlussstaerke = 6;
		} else if ("2".equals(args[0])) {
			path = "src/daten/Uebung62.txt";
			netzwerkData = Util.readFile(path);
			quelle = 1;
			senke = 6;
			maxFlussstaerke = 19;
		} else {
			try {
				path = args[0];
				netzwerkData = Util.readFile(path);
				quelle = Integer.valueOf(args[1]);
				senke = Integer.valueOf(args[2]);
				maxFlussstaerke = Integer.valueOf(args[3]);
			} catch (Exception e) {
				System.out
						.println("Etwas ist schief gegangen. Bitte Eingabe pruefen und wiederholen.");
			}
		}

		/**
		 * Erstellung des Netzwerks aus uebergebener Datendatei
		 */
		for (Integer[] kantenDaten : netzwerkData) {
			Knoten knoten1 = netzwerk.getVertexById(kantenDaten[0]);
			Knoten knoten2 = netzwerk.getVertexById(kantenDaten[1]);

			if (knoten1 == null) {
				knoten1 = new Knoten(kantenDaten[0]);
				netzwerk.addVertex(knoten1);
			}
			if (knoten2 == null) {
				knoten2 = new Knoten(kantenDaten[1]);
				netzwerk.addVertex(knoten2);
			}

			GerichteteKante kante = new GerichteteKante(kantenDaten[3],
					kantenDaten[2], 0);
			netzwerk.addEdge(kante, knoten1, knoten2);
		}
		/**
		 * Festlegung der Quelle und der Senke des Netzwerkes
		 */
		netzwerk.getVertexById(quelle).setTyp(Knoten.Knotentyp.QUELLE);
		netzwerk.getVertexById(senke).setTyp(Knoten.Knotentyp.SENKE);
		/**
		 * Ausgabe der Initialdaten
		 */
		System.out
				.println("----------------------------------------------------------");
		System.out.println("Kostenminimale Fluesse im Netzwerk");
		System.out
				.println("----------------------------------------------------------");
		System.out.println("Quelle ist Knoten: " + quelle);
		System.out.println("Senke ist Knoten: " + senke);
		System.out.println("Flussstaerke: " + maxFlussstaerke);
		System.out
				.println("----------------------------------------------------------");
		/**
		 * Iteratorbeginn
		 */
		int i = 1;
		/**
		 * Ausfuehrung des Algorithmus zum Finden von kostenminimalen Fluessen in
		 * einem Netzwerk
		 */
		do {
			/**
			 * Schrittweise Abarbeitung des Algorithmus
			 */
			System.out.println("Enter druecken fuer Iteration: " + i);
			BufferedReader inp = new BufferedReader(new InputStreamReader(
					System.in));
			try {
				inp.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			/**
			 * Erstellung Inkrementnetzwerk
			 */
			Netzwerk inkrementNetz = BusackerGowen
					.erstelleInkrementNetzwerk(netzwerk);
			/**
			 * Ausgabe des Netzwerkes und des Inkrementnetzwerks
			 */
			BusackerGowen.ausgabeNetzwerk("Netzwerk " + i, netzwerk);
			BusackerGowen.ausgabeNetzwerk("Inkrementnetzwerk " + i,
					inkrementNetz);
			System.out.println("\nNetzwerk: " + netzwerk.toString());
			System.out.println("\nInkrementnetzwerk: "
					+ inkrementNetz.toString());
			/**
			 * Finden eines kuerzestens Weges im Inkrementnetzwerk und Ausgabe
			 */
			try {
				weg = BusackerGowen.findeErweiterbarenWeg(inkrementNetz);
				System.out.println("\nPFAD: " + weg.toString());
				System.out.println("Minimale Kapazitaet: "
						+ weg.getMinimaleKapazitaet(inkrementNetz) + "\n");
				System.out
						.println("----------------------------------------------------------");
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
			/**
			 * Vergroesserung des Flusses
			 */
			int fluss = BusackerGowen.vergroesserungFluss(netzwerk,
					inkrementNetz, weg, maxFlussstaerke);
			/**
			 * Neuberechnung Inkrementnetzwerk
			 */
			try {
				BusackerGowen.updateFluss(netzwerk, inkrementNetz, weg, fluss);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/**
			 * Erhoehung Iterator
			 */
			i++;
			/**
			 * Abbruchbedingung des Algorithmuses
			 */
		} while (weg != null && weg.isEmpty() == false
				&& maxFlussstaerke != netzwerk.getFluss());
		/**
		 * Pruefung des Netzwerks und Ausgabe des Gesamtfluss und der
		 * Gesamtkosten
		 */
		if (BusackerGowen.checkNetzwerk(netzwerk)) {
			System.out.println("\nErgebnis:");
			System.out.println("Fluss: " + netzwerk.getFluss());
			System.out.println("Kosten: " + netzwerk.getFlussKosten());
		} else {
			System.out.println("Etwas ist schief gelaufen.");
		}
	}
}
