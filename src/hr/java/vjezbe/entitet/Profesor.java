package hr.java.vjezbe.entitet;

/**
 * Predstavlja entitet profesora koji nasljeđuje apstraktnu klasu Osoba, a definira ga sifra, ime, prezime i titula.
 * @author tgtom
 *
 */

public class Profesor extends Osoba{
	private String sifra;
	private String ime;
	private String prezime;
	private String titula;

	/**Inicijalizacija podataka o sifri, imenu, prezimenu i tituli profesora.
	 * @author tgtom
	 * @param id Podatak o idu profesora.
	 * @param sifra	Podatak o sifri profesora.
	 * @param ime	Podatak o imenu profesora.
	 * @param prezime	Podatak o prezimenu profesora.
	 * @param titula	Podatak o tituli profesora.
	 */
	public Profesor(Long id, String sifra, String ime, String prezime, String titula) {
		super(id, ime, prezime);
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
		this.titula = titula;
	}
	/**Inicijalizacija podataka o sifri, imenu, prezimenu i tituli profesora.
	 * @author tgtom
	 * 
	 * @param sifra	Podatak o sifri profesora.
	 * @param ime	Podatak o imenu profesora.
	 * @param prezime	Podatak o prezimenu profesora.
	 * @param titula	Podatak o tituli profesora.
	 */
	public Profesor(String sifra, String ime, String prezime, String titula) {
		super(ime, prezime);
		this.sifra = sifra;
		this.ime = ime;
		this.prezime = prezime;
		this.titula = titula;
	}
	
	/**Vraca sifru profesora.
	 * @author tgtom
	 * @return Vraca sifru profesora.
	 * 
	 */
	public String getSifra() {
		return sifra;
	}
	/**Postavlja sifru profesora.
	 * @author tgtom
	 * @param sifra Postavlja sifru profesora.
	 * 
	 */
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	/**Vraca ime profesora.
	 * @author tgtom
	 * @return Vraca ime profesora.
	 *
	 */
	@Override
	public String getIme() {
		return ime;
	}
	/**Postavlja ime profesora.
	 * @author tgtom
	 * @param ime Postavlja ime profesora.
	 * 
	 * 
	 */
	@Override
	public void setIme(String ime) {
		this.ime = ime;
	}
	/**Vraca prezime profesora.
	 * @author tgtom
	 * @return Vraca prezime profesora.
	 * 
	 */
	@Override
	public String getPrezime() {
		return prezime;
	}
	/**Postavlja prezime profesora.
	 * @author tgtom
	 * @param prezime Postavlja prezime profesora.
	 * 
	 */
	@Override
	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}
	/**Vraca titulu profesora.
	 * @author tgtom
	 * @return Vraca titulu profesora.
	 * 
	 */
	public String getTitula() {
		return titula;
	}
	/**Postavlja titulu profesora.
	 * @author tgtom
	 * @param titula Postavlja titulu profesora.
	 * 
	 */
	public void setTitula(String titula) {
		this.titula = titula;
	}
	

}
