package hr.java.vjezbe.entitet;
/**
 * Predstavlja sve ocjene koje se mogu dobiti na ispitima
 * @author tgtom
 *
 */
public enum Ocjena {
	
	ODLICAN(5, "„odlican“"),
	VRLO_DOBAR(4, "„vrlo dobar“"),
	DOBAR(3, "„dobar“"),
	DOVOLJAN(2, "„dovoljan“"),
	NEDOVOLJAN(1, "„nedovoljan“");
	
	
	
	private int ocjena;
	private String opisOcjene;
	/**
	 * Vrijednost ocjene i opis ocjene
	 * @param ocjena	Predstavlja vrijednost ocjene.
	 * @param opisOcjene	Predstavlja opis ocjene.
	 */
	private Ocjena(int ocjena, String opisOcjene) {
		this.ocjena = ocjena;
		this.opisOcjene = opisOcjene;
	}

	/**
	 * Vraca ocjenu na ispitu.
	 * @return	Vraca vrijednost ocjene.
	 */
	public int getOcjena() {
		return ocjena;
	}
	
	/**
	 * Vraca opis ocjene na ispitu
	 * @return	Vraca opis ocjene na ispitu.
	 */
	public String getOpisOcjene() {
		return opisOcjene;
	}
}
