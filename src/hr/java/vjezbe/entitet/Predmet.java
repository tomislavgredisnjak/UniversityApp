package hr.java.vjezbe.entitet;


import java.util.HashSet;
import java.util.Set;

/**
 * Predstavlja entitet predmeta koji je definiran sifrom predmeta, nazivom predmeta, brojem ects bodova koje predmet nosi,
 * profesorom nositeljem predmeta i studentima koji slušaju predmet.
 * @author tgtom
 *
 */

public class Predmet extends Entitet{
	private String sifra;
	private String naziv;
	private Integer brojEctsBodova;
	private Profesor nositelj;
	private Set<Student> studenti;
	
	/**
	 *Inicijalizacija podataka o sifri, nazivu, broju ects bodova, nostitelju i studentima predmeta.
	 * @author tgtom
	 * @param id Podatak o idu premeta
	 * @param sifra	Podatak o sifri predmeta.
	 * @param naziv	Podatak o nazivu predmeta.
	 * @param brojEctsBodova	Podatak o broju ects bodova koji predmet nosi.
	 * @param nositelj	Podatak o profesoru nositelju predmeta.
	 * @param brojStudenata	Podatak o broju studenta koji pohađaju predmet.
	 */
	public Predmet(Long id,String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj) {
		super(id);
		this.sifra = sifra;
		this.naziv = naziv;
		this.brojEctsBodova = brojEctsBodova;
		this.nositelj = nositelj;
		this.studenti = new HashSet<>();
	}
	/**
	 *Inicijalizacija podataka o sifri, nazivu, broju ects bodova, nostitelju i studentima predmeta.
	 * @author tgtom
	 * 
	 * @param sifra	Podatak o sifri predmeta.
	 * @param naziv	Podatak o nazivu predmeta.
	 * @param brojEctsBodova	Podatak o broju ects bodova koji predmet nosi.
	 * @param nositelj	Podatak o profesoru nositelju predmeta.
	 * @param brojStudenata	Podatak o broju studenta koji pohađaju predmet.
	 */
	public Predmet(String sifra, String naziv, Integer brojEctsBodova, Profesor nositelj) {
		super();
		this.sifra = sifra;
		this.naziv = naziv;
		this.brojEctsBodova = brojEctsBodova;
		this.nositelj = nositelj;
		this.studenti = new HashSet<>();//mozda ovo maknut
	}
	
	/**Vraca studente koji pohađaju predmet.
	 * @author tgtom
	 * @return Vraca studente koji pohađaju predmet.
	 */
	public Set<Student> getStudenti() {
		return studenti;
	}

	/**Postavalja studente koji pohađaju predmet.
	 * @author tgtom
	 * @param studenti Postavalja studente koji pohađaju predmet.
	 * 
	 */
	public void setStudenti(Student studenti) {
		
		this.studenti.add(studenti);
		
	}

	/**Postavlja broj ects bodova koje predmet nosi.
	 * @author tgtom
	 * @param brojEctsBodova Postavlja broj ects bodova koje predmet nosi.
	 * 
	 */
	public void setBrojEctsBodova(Integer brojEctsBodova) {
		this.brojEctsBodova = brojEctsBodova;
	}
	
	/**Vraca sifru predmeta.
	 * @author tgtom
	 * @return	Vraca sifru predmeta.
	 * 
	 */
	public String getSifra() {
		return sifra;
	}
	/**Postavlja sifru predmeta.
	 * @author tgtom
	 * @param sifra Postavlja sifru predmeta.
	 * 
	 */
	public void setSifra(String sifra) {
		this.sifra = sifra;
	}
	/**Vraca naziv predmeta.
	 * @author tgtom
	 * @return	Vraca naziv predmeta.
	 * 
	 */
	public String getNaziv() {
		return naziv;
	}
	/**Postavlja naziv predmeta.
	 * @author tgtom
	 * @param naziv Postavlja naziv predmeta.
	 * 
	 */
	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}
	/**Vraca broj ects bodova koje predmet nosi.
	 * @author tgtom
	 * @return	Vraca broj ects bodova koje predmet nosi.
	 * 
	 */
	public Integer getBrojEctsBodova() {
		return brojEctsBodova;
	}
	/**Vraca profesora nositelj predmeta.
	 * @author tgtom
	 * @return Vraca profesora nositelj predmeta.
	 * 
	 */
	public Profesor getNositelj() {
		return nositelj;
	}
	/**Postavlja profesora nositelja predmeta.
	 * @author tgtom
	 * @param nositelj Postavlja profesora nositelja predmeta.
	 * 
	 */
	public void setNositelj(Profesor nositelj) {
		this.nositelj = nositelj;
	}

	@Override
	public String toString() { 
	    return this.naziv;
	}
}
