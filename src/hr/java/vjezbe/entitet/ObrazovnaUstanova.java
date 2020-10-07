package hr.java.vjezbe.entitet;

import java.util.List;

/**
 * Predstavlja apstraktnu klasu koju nasljeđuju FakultetRacunarstva i VeleucilisteJave, a definiraju ju
 * naziv ustanove, predmeti, profesori, studenti i ispiti
 * 
 * @author tgtom
 *
 */

public abstract class ObrazovnaUstanova extends Entitet{
	

	private String nazivUstanove;
	private List<Predmet> predmeti;
	private List<Profesor> profesori;
	private List<Student> studenti;
	private List<Ispit> ispiti;
	/**
	 * Inicijalizacija podataka o nazivu ustanove, predmetima, profesorima, studentima i ispitima
	 * @author tgtom
	 * @param id podatak o idu obrazovne ustanove
	 * @param nazivUstanove	podatak o nazivu obrazovne ustanove
	 * @param predmeti	podatak o predmetima u obrazovnoj ustanovi
	 * @param profesori	podatak o profesorima u obrazovnoj ustanovi
	 * @param studenti	podatak o studentima koji pohađaju obrazovnu ustanovu
	 * @param ispiti	podatak o ispitima u obrazovnoj ustanovi
	 */
	public ObrazovnaUstanova(Long id, String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti,
			List<Ispit> ispiti) {
		super(id);
		this.nazivUstanove = nazivUstanove;
		this.predmeti = predmeti;
		this.profesori = profesori;
		this.studenti = studenti;
		this.ispiti = ispiti;
	}
	
	/**
	 * Inicijalizacija podataka o nazivu ustanove, predmetima, profesorima, studentima i ispitima
	 * @author tgtom
	 * @param nazivUstanove	podatak o nazivu obrazovne ustanove
	 * @param predmeti	podatak o predmetima u obrazovnoj ustanovi
	 * @param profesori	podatak o profesorima u obrazovnoj ustanovi
	 * @param studenti	podatak o studentima koji pohađaju obrazovnu ustanovu
	 * @param ispiti	podatak o ispitima u obrazovnoj ustanovi
	 */
	
	public ObrazovnaUstanova(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti,
			List<Ispit> ispiti) {
		super();
		this.nazivUstanove = nazivUstanove;
		this.predmeti = predmeti;
		this.profesori = profesori;
		this.studenti = studenti;
		this.ispiti = ispiti;
	}
	/**Vraca naziv obrazovne ustanove.
	 * @author tgtom
	 * @return Vraca naziv obrazovne ustanove.
	 * 
	 */
	public String getNazivUstanove() {
		return nazivUstanove;
	}
	/**Postavlja naziv obrazovne ustanove
	 * @author tgtom
	 * @param nazivUstanove Postavlja naziv obrazovne ustanove
	 * 
	 */
	public void setNazivUstanove(String nazivUstanove) {
		this.nazivUstanove = nazivUstanove;
	}
	/**Vraca predmete u ustanovi.
	 * @author tgtom
	 * @return Vraca predmete u ustanovi.
	 * 
	 */
	public List<Predmet> getPredmeti() {
		return predmeti;
	}
	/**Postavlja predmete na obrazovnoj ustanovi.
	 * @author tgtom
	 * @param predmeti Postavlja predmete na obrazovnoj ustanovi.
	 *  
	 */
	public void setPredmeti(List<Predmet> predmeti) {
		this.predmeti = predmeti;
	}
	/**Vraca profesore koji predaju u obrazovnoj ustanovi.
	 * @author tgtom
	 * @return	Vraca profesore koji predaju u obrazovnoj ustanovi.
	 * 
	 */
	public List<Profesor> getProfesori() {
		return profesori;
	}
	/**Postavlja profesore koji predaju u obrazovnoj ustanovi.
	 * @author tgtom
	 * @param profesori Postavlja profesore koji predaju u obrazovnoj ustanovi.
	 * 
	 */
	public void setProfesori(List<Profesor> profesori) {
		this.profesori = profesori;
	}
	/**Vraca studente koji pohađaju obrazovnu ustanovu.
	 * @author tgtom
	 * @return	Vraca studente koji pohađaju obrazovnu ustanovu.
	 * 
	 */
	public List<Student> getStudenti() {
		return studenti;
	}
	/**Postavlja studente koji pohađaju obrazovnu ustanovu.
	 * @author tgtom
	 * @param studenti Postavlja studente koji pohađaju obrazovnu ustanovu.
	 * 
	 */
	public void setStudenti(List<Student> studenti) {
		this.studenti = studenti;
	}
	/**Vraca ispite koji se pisu u obrazovnoj ustanovi.
	 * @author tgtom
	 * @return Vraca ispite koji se pisu u obrazovnoj ustanovi.
	 * 
	 */
	public List<Ispit> getIspiti() {
		return ispiti;
	}
	/**Postavlja ispite koji se pisu u obrazovnoj ustanovi.
	 * @author tgtom
	 * @param ispiti Postavlja ispite koji se pisu u obrazovnoj ustanovi.
	 * 
	 */
	public void setIspiti(List<Ispit> ispiti) {
		this.ispiti = ispiti;
	}
	/**
	 * Oznacuje apstraktnu metodu koja se implementira u klasama FakultetRacunarstva i VeleucilisteJave, a određuje najuspjesnijeg studenta u zadanog godini
	 * @author tgtom
	 * @param godina Oznacuje godinu u kojoj treba izracunati najuspjesnijeg studenta
	 * @return	Vraca najuspjesnijeg studenta zadane godine
	 */
	public abstract Student odrediNajuspjesnijegStudentaNaGodini(int godina);

	/**
	 * Oznacuje apstraktnu metodu koja se implementira u klasama FakultetRacunarstva i VeleucilisteJave, a određuje najuspjesnijeg studenta u zadanog godini
	 * @param godina	Oznacuje godinu u kojoj treba izracunati najuspjesnijeg studenta
	 * @param studentiStudija	Oznacuje listu studenata koji pohađaju tu obrazovnu ustanovu.
	 * @return	Vraca najuspjesnijeg studenta zadane godine
	 */
	public abstract Student odrediNajuspjesnijegStudentaNaGodini(int godina, List<Student> studentiStudija);
	
}
