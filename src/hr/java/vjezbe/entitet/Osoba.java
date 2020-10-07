package hr.java.vjezbe.entitet;
/**
 * Predstavlja apstraktnu klasu osoba koju nasljeđuju klase profesor i student, a definirana je imenom i prezimenom.
 * @author tgtom
 *
 */
public abstract class Osoba extends Entitet{
	/**Inicijalizacija podataka o imenu i prezimenu osobe.
	 * @author tgtom
	 * @param id	podatak o idu osobe
	 * @param ime	podatak o imenu osobe.
	 * @param prezime	podatak o prezimenu osobe.
	 */
	public Osoba(Long id, String ime, String prezime) {
		super(id);
		this.ime = ime;
		this.prezime = prezime;
	}
	/**Inicijalizacija podataka o imenu i prezimenu osobe.
	 * @author tgtom
	 * @param ime	podatak o imenu osobe.
	 * @param prezime	podatak o prezimenu osobe.
	 */
	
	public Osoba(String ime, String prezime) {
		super();
		this.ime = ime;
		this.prezime = prezime;
	}
	
	private String ime;	
	private String prezime;
	
	
	/**Vraca ime osobe.
	 * @author tgtom
	 * @return	Vraca ime osobe.
	 * 
	 */
	public String getIme() {
		return ime;
	}
	/**Postavlja ime osobe.
	 * @author tgtom
	 * @param ime	Postavlja ime osobe.
	 * 
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}
	/**Vraca prezime osobe.
	 * @author tgtom
	 * @return	Vraca prezime osobe.
	 * 
	 */
	public String getPrezime() {
		return prezime;
	}
	/**Postavlja prezime osobe.
	 * @author tgtom
	 * @param prezime Postavlja prezime osobe.
	 * 
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}	
	@Override
	public String toString() { 
	    return this.ime + " " + this.prezime;
	}
}
