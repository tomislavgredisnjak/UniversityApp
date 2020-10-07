package hr.java.vjezbe.entitet;
import java.time.LocalDateTime;

/**
 * Predstavlja entitet ispita na fakultetu ili veleucilistu, definiran predmetom, studentom koji ga pise, ocjenom koju
 * je student dobio i vremenom kada se pisao ispit.
 * 
 * @author tgtom
 *
 */

public class Ispit extends Entitet{
	

	private Predmet predmetX;
	private Student studentX;
	private Integer ocjena;
	private LocalDateTime datumIVrijeme;
	
	/**
	 * Inicijalizacija podataka potrebna za stvaranje objekta Ispit
	 * 
	 * @author tgtom
	 * @param id podatak o idu ispita
	 * @param predmetX	podatak o predmetu koji se pise
	 * @param studentX	podatak o studentu koji pise ispit
	 * @param ocjena	podatak o ocjeni koji je student dobio na ispitu
	 * @param datumIVrijeme	podatak o datumi i vremenu kada se ispit pisao
	 */
	
	public Ispit(Long id, Predmet predmetX, Student studentX, Integer ocjena, LocalDateTime datumIVrijeme) {
		super(id);
		this.predmetX = predmetX;
		this.studentX = studentX;
		this.ocjena = ocjena;
		this.datumIVrijeme = datumIVrijeme;
	}
	/**
	 * Inicijalizacija podataka potrebna za stvaranje objekta Ispit
	 * 
	 * @author tgtom
	 * 
	 * @param predmetX	podatak o predmetu koji se pise
	 * @param studentX	podatak o studentu koji pise ispit
	 * @param ocjena	podatak o ocjeni koji je student dobio na ispitu
	 * @param datumIVrijeme	podatak o datumi i vremenu kada se ispit pisao
	 */
	public Ispit(Predmet predmetX, Student studentX, Integer ocjena, LocalDateTime datumIVrijeme) {
		super();
		this.predmetX = predmetX;
		this.studentX = studentX;
		this.ocjena = ocjena;
		this.datumIVrijeme = datumIVrijeme;
	}
	
	/**Vraca predmet koji se pise na ispitu
	 * @author tgtom
	 * @return Vraca predmet koji se pise na ispitu
	 * 
	 */
	public Predmet getPredmetX() {
		return predmetX;
	}
	
	/**Postavlja predmet koji se pise na ispitu
	 * @author tgtom
	 * @param predmetX postavlja predmet koji se pise na ispitu
	 * 
	 */
	public void setPredmetX(Predmet predmetX) {
		this.predmetX = predmetX;
	}
	/**Vraca studenta koji pise ispit
	 * @author tgtom
	 * @return Vraca studenta koji pise ispit
	 * 
	 */
	
	public Student getStudentX() {
		return studentX;
	}
	
	/**Postavlja studenta koji pise ispit
	 * @author tgtom
	 * @param studentX Postavlja studenta koji pise ispit
	 * 
	 */
	public void setStudentX(Student studentX) {
		this.studentX = studentX;
	}
	
	/**Vraca ocjenu koju je student dobio na ispitu
	 * @author tgtom
	 * @return Vraca ocjenu koju je student dobio na ispitu
	 * 
	 */
	public Integer getOcjena() {
		return ocjena;
	}
	
	/**Postavlja ocjenu koju je student dobio na ispitu
	 * @author tgtom
	 * @param ocjena Postavlja ocjenu koju je student dobio na ispitu
	 * 
	 */
	public void setOcjena(Integer ocjena) {
		this.ocjena = ocjena;
	}
	/**Vraca datum i vrijeme kada se pise ispit
	 * @author tgtom
	 * @return Vraca datum i vrijeme kada se pise ispit
	 *  
	 */
	
	public LocalDateTime getDatumIVrijeme() {
		return datumIVrijeme;
	}
	/**Postavlja datum i vrijeme kada se pise ispit
	 * @author tgtom
	 * @param datumIVrijeme Postavlja datum i vrijeme kada se pise ispit
	 * 
	 */
	public void setDatumIVrijeme(LocalDateTime datumIVrijeme) {
		this.datumIVrijeme = datumIVrijeme;
	}
	
	
}
