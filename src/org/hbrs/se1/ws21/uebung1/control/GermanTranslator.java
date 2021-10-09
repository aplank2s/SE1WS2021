package org.hbrs.se1.ws21.uebung1.control;

import java.util.HashMap;

public class GermanTranslator implements Translator {

	public String date = "Okt/2021"; // Default-Wert

	/**
	 * Methode zur Übersetzung einer Zahl in eine String-Repraesentation
	 */
	public String translateNumber( int number ) {
		// [ihr Source Code aus Übung 1-2]

		HashMap<Integer, String> h = new HashMap<Integer,String>();
		h.put(1, "eins");
		h.put(2,"zwei");
		h.put(3,"drei");
		h.put(4,"vier");
		h.put(5,"fünf");
		h.put(6,"sechs");
		h.put(7,"sieben");
		h.put(8,"acht");
		h.put(9,"neun");
		h.put(10,"zehn");

		return h.getOrDefault(number,"Übersetzung der Zahl [" + number + "] nicht möglich [" + version +"]");
	}
		
	/**
	 * Objektmethode der Klasse GermanTranslator zur Ausgabe einer Info.
	 */
	public void printInfo(){
		System.out.println( "GermanTranslator v1.9, erzeugt am " + this.date );
	}

	/**
	 * Setzen des Datums, wann der Uebersetzer erzeugt wurde (Format: Monat/Jahr (Beispiel: Okt/2021))
	 * Das Datum sollte system-intern gesetzt werden und nicht von externen View-Klassen
	 */
	public void setDate( String date ) {
		this.date = date;
	}

}
