package algorithmen;

import java.awt.Dimension;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;

import javax.swing.JFrame;

import kanten.GerichteteKante;
import kanten.InkrementKante;
import knoten.Knoten;
import netzwerk.Netzwerk;

import org.apache.commons.collections15.Transformer;

import wege.ErweiterbarerWeg;
import edu.uci.ics.jung.algorithms.layout.CircleLayout;
import edu.uci.ics.jung.algorithms.layout.Layout;
import edu.uci.ics.jung.visualization.VisualizationViewer;
import edu.uci.ics.jung.visualization.control.DefaultModalGraphMouse;
import edu.uci.ics.jung.visualization.control.ModalGraphMouse;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel.Position;

public class BusackerGowen {
	/**
	 * Erstelle Inkrementnetzwerk aus übergebenen Netzwerk
	 * 
	 * @param netzwerk
	 * @return Inkrementnetzwerk
	 */
	public static Netzwerk erstelleInkrementNetzwerk(Netzwerk netzwerk) {

		Netzwerk inkNet = new Netzwerk();

		for (Knoten knoten : netzwerk.getVertices()) {
			inkNet.addVertex(knoten);
		}

		for (GerichteteKante kante : netzwerk.getEdges()) {
			Integer verfuegbareKapazitaet = kante.getKapzitaet()
					- kante.getFluss();
			// Kante in selbe Richtung mit gleichen Kosten
			if (verfuegbareKapazitaet > 0) {
				InkrementKante inkKante1 = new InkrementKante(
						kante.getKosten(), verfuegbareKapazitaet);
				inkNet.addEdge(inkKante1, netzwerk.getEndpoints(kante));
			}
			// Kante in entgegengesetzte Richtung mit negativen Kosten
			if (kante.getFluss() > 0) {
				InkrementKante inkKante2 = new InkrementKante(
						-kante.getKosten(), kante.getFluss());
				inkNet.addEdge(inkKante2, netzwerk.getDest(kante),
						netzwerk.getSource(kante));
			}
		}
		return inkNet;
	}

	/**
	 * Ausgabe des Netzwerks
	 * 
	 * @param titel
	 * @param nw
	 */
	public static void ausgabeNetzwerk(String titel, Netzwerk nw) {

		Layout<Knoten, GerichteteKante> layout = new CircleLayout<Knoten, GerichteteKante>(
				nw);
		layout.setSize(new Dimension(1300, 700));

		VisualizationViewer<Knoten, GerichteteKante> vv = new VisualizationViewer<Knoten, GerichteteKante>(
				layout);
		vv.setPreferredSize(new Dimension(1320, 720));
		Transformer<Knoten, Shape> vertexSize = new Transformer<Knoten, Shape>() {
			@Override
			public Shape transform(Knoten knoten) {
				Ellipse2D circle = new Ellipse2D.Double(-15, -15, 20, 20);
				return AffineTransform.getScaleInstance(2, 2)
						.createTransformedShape(circle);
			}
		};
		vv.getRenderContext().setVertexShapeTransformer(vertexSize);
		vv.getRenderContext().setVertexLabelTransformer(
				new ToStringLabeller<Knoten>());
		vv.getRenderContext().setEdgeLabelTransformer(
				new ToStringLabeller<GerichteteKante>());
		vv.getRenderContext().setLabelOffset(15);
		vv.getRenderer().getVertexLabelRenderer().setPosition(Position.CNTR);
		DefaultModalGraphMouse<Object, Object> gm = new DefaultModalGraphMouse<Object, Object>();
		gm.setMode(ModalGraphMouse.Mode.TRANSFORMING);
		vv.setGraphMouse(gm);

		JFrame frame = new JFrame("Kostenminimaler Fluss: " + titel);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(vv);
		frame.pack();
		frame.setVisible(true);
	}

	/**
	 * Rückgabe des kürzesten Weges Ermittlung mittels Bellman-Ford-Algorithmus
	 * 
	 * @param net
	 * @return
	 * @throws Exception
	 */
	public static ErweiterbarerWeg findeErweiterbarenWeg(Netzwerk net)
			throws Exception {
		/**
		 * lokale Variablen
		 */
		Netzwerk inkNet = net;
		Knoten senke = null;
		ErweiterbarerWeg weg = null;
		/**
		 * Ermittlung der Senke im Netzwerk
		 */
		for (Knoten knoten : inkNet.getVertices()) {
			if (knoten.isSenke()) {
				senke = knoten;
			}
		}
		/**
		 * finden des kürzesten Weges
		 */
		BellmanFord bf = new BellmanFord(inkNet);
		weg = bf.getShortestPath(senke);
		/**
		 * Rückgabe des Weges
		 */
		return weg;
	}

	/**
	 * Vergrößerung des Flusses
	 */
	public static int vergroesserungFluss(Netzwerk netzwerk,
			Netzwerk inkrementNetzwerk, ErweiterbarerWeg weg,
			int maxFlussstaerke) {
		int flussUpdate = 0;

		if (maxFlussstaerke < netzwerk.getFluss()
				+ weg.getMinimaleKapazitaet(inkrementNetzwerk)) {
			flussUpdate = maxFlussstaerke
					- weg.getMinimaleKapazitaet(inkrementNetzwerk);
		} else {
			flussUpdate = weg.getMinimaleKapazitaet(inkrementNetzwerk);
		}

		return flussUpdate;
	}

	/**
	 * Aktualisierung des Netzwerks anhand des Flusses
	 * 
	 * @param netzwerk
	 * @param inkrementNetzwerk
	 * @param weg
	 * @param fluss
	 * @throws Exception
	 */
	public static void updateFluss(Netzwerk netzwerk,
			Netzwerk inkrementNetzwerk, ErweiterbarerWeg weg, Integer fluss)
			throws Exception {

		for (GerichteteKante kante : weg.getKantenListe(inkrementNetzwerk)) {
			/**
			 * 
			 */
			if (kante.getKapzitaet() < kante.getFluss() + fluss) {
				throw new Exception("Der Fluss ist zu groß.");
			}
			/**
			 * 
			 */
			Knoten start = inkrementNetzwerk.getSource(kante);
			Knoten end = inkrementNetzwerk.getDest(kante);

			if (kante.getKosten() >= 0) {
				//
				GerichteteKante origKante = netzwerk.findEdge(start, end);
				//
				if (origKante.getKapzitaet() < origKante.getFluss() + fluss) {
					throw new Exception("Der Fluss ist zu groß.");
				}
				//
				origKante.setFluss(origKante.getFluss() + fluss);
			} else {
				// Kosten Kante < 0 --> update entgegengesetzte Kante
				GerichteteKante origKante = netzwerk.findEdge(end, start);
				//
				if (origKante.getKapzitaet() < origKante.getFluss() - fluss) {
					throw new Exception("Der Fluss ist zu groß.");
				}
				//
				origKante.setFluss(origKante.getFluss() - fluss);
			}
		}
	}

	/**
	 * Prüfung des Netzwerks auf Gleichheit des eingehenden Fluss mit dem
	 * ausgehenden Fluss
	 * 
	 * @param netzwerk
	 * @return
	 */
	public static boolean checkNetzwerk(Netzwerk netzwerk) {
		int startflow = 0;
		int endflow = 0;

		for (Knoten knoten : netzwerk.getVertices()) {
			/**
			 * Prüfung der Knoten, wenn nicht Quelle oder Senke
			 */
			if (knoten.isQuelle() == false && knoten.isSenke() == false) {
				int inflow = 0;
				int outflow = 0;

				for (GerichteteKante inKante : netzwerk.getInEdges(knoten)) {
					inflow += inKante.getFluss();
				}
				for (GerichteteKante outKante : netzwerk.getOutEdges(knoten)) {
					outflow += outKante.getFluss();
				}
				if (outflow != inflow) {
					/**
					 * Ausgabe des Prüfergebnisses
					 */
					return false;
				}
			}
			/**
			 * Prüfung des Quelle Knotens
			 */
			if (knoten.isQuelle()) {
				for (GerichteteKante outKante : netzwerk.getOutEdges(knoten)) {
					startflow += outKante.getFluss();
				}
			}
			/**
			 * Prüfung des Senke Knotens
			 */
			if (knoten.isSenke()) {
				for (GerichteteKante inKante : netzwerk.getInEdges(knoten)) {
					endflow += inKante.getFluss();
				}
			}
		}
		/**
		 * Ausgabe des Prüfergebnisses
		 */
		return (startflow == endflow) ? true : false;
	}
}
