package knoten;

public class Knoten {
	/**
	 * Definition der Knotentypen
	 */
	public static enum Knotentyp { 
		QUELLE, SENKE, NORMAL 
	}
	/**
	 * Attribute
	 */
	private Integer id;
	private Knotentyp typ;
	/**
	 * Konstruktor
	 * @param id
	 */
	public Knoten(Integer id) {
		this(id, Knotentyp.NORMAL);
		
	}
	/**
	 * Konstruktor
	 * @param id
	 * @param typ
	 */
	public Knoten(Integer id, Knotentyp typ) {
		this.id = id;
		this.typ = typ;
	}
	/**
	 * Prüfung, ob Knoten eine Quelle ist.
	 * @return
	 */
	public Boolean isQuelle() {
		return (typ == Knotentyp.QUELLE);
	}
	/**
	 * Prüfung, ob Knoten eine Senke ist.
	 * @return
	 */
	public Boolean isSenke() {
		return (typ == Knotentyp.SENKE);
	}
	/**
	 * Setzen des Knotentypes
	 * @param typ
	 */
	public void setTyp(Knotentyp typ) {
		this.typ = typ;
	}
	/**
	 * Rückgabe der Knoten ID
	 * @return
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * Setzen der Knoten ID
	 * @param id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * Ausgabe des Knotens
	 */
	@Override
	public String toString() {
		return "V" + id;
	}
}