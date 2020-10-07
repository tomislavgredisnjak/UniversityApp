package hr.java.vjezbe.entitet;

import java.time.LocalDate;

/**
 * Predstavlja entitet Studenta koji nasljeđujee klasu Osoba, a definiran je imenom, prezimenom, jmbagom i datumom rođenja
 * @author tgtom
 *
 */

public class Student extends Osoba{
	
	private String ime;
	private String prezime;
	private String jmbag;
	private LocalDate datumRodjenja;
	
	/**Inicijalizacija podataka o imenu, prezimenu, jmbagu i datumu rođenja studenta.
	 * @author tgtom
	 * @param id Podatak o idu studenta.
	 * @param ime	Podatak o imenu studenta.
	 * @param prezime	Podatak o prezimenu studenta.
	 * @param jmbag		Podatak o jmbagu studenta.
	 * @param datumRodjenja	Podatak o datumu rođenja studenta.
	 */
	public Student(Long id, String ime, String prezime, String jmbag, LocalDate datumRodjenja) {
		super(id, ime, prezime);
		this.ime = ime;
		this.prezime = prezime;
		this.jmbag = jmbag;
		this.datumRodjenja = datumRodjenja;
	}
	
	/**Inicijalizacija podataka o imenu, prezimenu, jmbagu i datumu rođenja studenta.
	 * @author tgtom
	 * 
	 * @param ime	Podatak o imenu studenta.
	 * @param prezime	Podatak o prezimenu studenta.
	 * @param jmbag		Podatak o jmbagu studenta.
	 * @param datumRodjenja	Podatak o datumu rođenja studenta.
	 */
	public Student(String ime, String prezime, String jmbag, LocalDate datumRodjenja) {
		super(ime, prezime);
		this.ime = ime;
		this.prezime = prezime;
		this.jmbag = jmbag;
		this.datumRodjenja = datumRodjenja;
	}
	/**Vraca ime studenta.
	 * @return Vraca ime studenta.
	 * 
	 * @author tgtom
	 */
	public String getIme() {
		return ime;
	}
	/**Postavlja ime studenta.
	 * @param ime Postavlja ime studenta.
	 * 
	 * @author tgtom
	 */
	public void setIme(String ime) {
		this.ime = ime;
	}
	/**Vraca prezime studenta.
	 * @return Vraca prezime studenta.
	 * 
	 * @author tgtom
	 */
	public String getPrezime() {
		return prezime;
	}
	/**Postavlja prezime studenta.
	 * @param prezime Postavlja prezime studenta.
	 * 
	 * @author tgtom
	 */
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	/**Vraca jmbag studenta.
	 * @author tgtom
	 * @return	Vraca jmbag studenta.
	 *
	 */
	public String getJmbag() {
		return jmbag;
	}
	/**Postavlja jmbag studenta.
	 * @author tgtom
	 * @param jmbag	Postavlja jmbag studenta.
	 * 
	 */
	public void setJmbag(String jmbag) {
		this.jmbag = jmbag;
	}
	/**Vraca datum rođenja studenta.
	 * @author tgtom
	 * @return Vraca datum rođenja studenta.
	 * 
	 */
	public LocalDate getDatumRodjenja() {
		return datumRodjenja;
	}
	/**Postavlja datum rođenja studenta
	 * @author tgtom
	 * @param datumRodjenja Postavlja datum rođenja studenta
	 *  
	 */
	public void setDatumRodjenja(LocalDate datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((datumRodjenja == null) ? 0 : datumRodjenja.hashCode());
		result = prime * result + ((ime == null) ? 0 : ime.hashCode());
		result = prime * result + ((jmbag == null) ? 0 : jmbag.hashCode());
		result = prime * result + ((prezime == null) ? 0 : prezime.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Student other = (Student) obj;
		if (datumRodjenja == null) {
			if (other.datumRodjenja != null)
				return false;
		} else if (!datumRodjenja.equals(other.datumRodjenja))
			return false;
		if (ime == null) {
			if (other.ime != null)
				return false;
		} else if (!ime.equals(other.ime))
			return false;
		if (jmbag == null) {
			if (other.jmbag != null)
				return false;
		} else if (!jmbag.equals(other.jmbag))
			return false;
		if (prezime == null) {
			if (other.prezime != null)
				return false;
		} else if (!prezime.equals(other.prezime))
			return false;
		return true;
	}	
	
	@Override
	public String toString() { 
	    return this.ime + " " + this.prezime;
	}
}

