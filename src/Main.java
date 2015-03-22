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
		 * lokale Variablen f�r das Netzwerk
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
		 * Setzen der Netzwerkdaten
		 */
		path = "src/daten/Daten12B.txt";
		netzwerkData = Util.readFile(path);
		quelle = 4;
		senke = 16;
		maxFlussstaerke = 6;
		/**
		 * Erstellung des Netzwerks aus �bergebener Datendatei
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

			GerichteteKante kante = new GerichteteKante(kantenDaten[3], kantenDaten[2], 0);
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
		System.out.println("----------------------------------------------------------");
		System.out.println("Kostenminimale Fl�sse im Netzwerk");
		System.out.println("----------------------------------------------------------");
		System.out.println("Quelle ist Knoten: " + quelle);
		System.out.println("Senke ist Knoten: " + senke);
		System.out.println("Flussst�rke: " + maxFlussstaerke);
		System.out.println("----------------------------------------------------------");
		/**
		 * Iteratorbeginn
		 */
		int i = 1;
		/**
		 * Ausf�hrung des Algorithmus zum Finden von kostenminimalen Fl�ssen in einem Netzwerk
		 */
		do {	
			/**
			 * Schrittweise Abarbeitung des Algorithmus
			 */
			System.out.println("Enter dr�cken f�r Iteration: " + i);
			BufferedReader inp = new BufferedReader (new InputStreamReader(System.in));
			 try {
				inp.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			/**
			 * Erstellung Inkrementnetzwerk
			 */
			Netzwerk inkrementNetz = BusackerGowen.erstelleInkrementNetzwerk(netzwerk);
			/**
			 * Ausgabe des Netzwerkes und des Inkrementnetzwerks
			 */
			BusackerGowen.ausgabeNetzwerk("Netzwerk " + i, netzwerk);
			BusackerGowen.ausgabeNetzwerk("Inkrementnetzwerk " + i, inkrementNetz);
			System.out.println("\nNetzwerk: " + netzwerk.toString());
			System.out.println("\nInkrementnetzwerk: " + inkrementNetz.toString());
			/**
			 * Finden eines k�rzestens Weges im Inkrementnetzwerk und Ausgabe
			 */
			try {
				weg = BusackerGowen.findeErweiterbarenWeg(inkrementNetz);
				System.out.println("\nPFAD: " + weg.toString());
				System.out.println("Minimale Kapazitaet: " + weg.getMinimaleKapazitaet(inkrementNetz) + "\n");
				System.out.println("----------------------------------------------------------");
			} catch (Exception e) {
				e.printStackTrace();
				break;
			}
			/**
			 * Vergr��erung des Flusses
			 */
			int fluss = BusackerGowen.vergroesserungFluss(netzwerk, inkrementNetz, weg, maxFlussstaerke);
			/**
			 * Neuberechnung Inkrementnetzwerk
			 */
			try {
				BusackerGowen.updateFluss(netzwerk, inkrementNetz, weg, fluss);
			} catch (Exception e) {
				e.printStackTrace();
			}
			/**
			 * Erh�hung Iterator
			 */
			i++;
		/**
		 * Abbruchbedingung des Algorithmuses	
		 */
		} while (weg != null && weg.isEmpty() == false && maxFlussstaerke != netzwerk.getFluss());
		/**
		 * Pr�fung des Netzwerks und Ausgabe des Gesamtfluss und der Gesamtkosten
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