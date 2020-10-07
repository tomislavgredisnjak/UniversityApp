package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

/**
 * Predstavlja entitet VeleucilistaJave koja nasljeđuje apstraktnu klasu ObrazovnaUstanova i implementira sucelje Visokoskolska,
 * a definraju ga naziv veleucilista, predmeti, profesori, studenti i ispiti
 * 
 * @author tgtom
 *
 */
public class VeleucilisteJave extends ObrazovnaUstanova implements Visokoskolska{

	public static final Logger log = LoggerFactory.getLogger(VeleucilisteJave.class);
	
	private String nazivUstanove;
	private List<Predmet> predmeti;
	private List<Profesor> profesori;
	private List<Student> studenti;
	private List<Ispit> ispiti;
	
	/**
	 * Inicijalizacija podataka o nazivu veleucilista, predmetima na veleucilistu, profesorima koji predaju na veleucilistu,
	 * studentima koji pohađaju veleuciliste i ispitima koji se pisu na veleucilistu
	 * @param id Podatak o idu veleucilista.
	 * @param nazivUstanove	Podatak o nazivu veleuclista.
	 * @param predmeti	Podatak o predmetima na veleuclistu.
	 * @param profesori	Podatak o profesorima koji predaju na veleucilistu.
	 * @param studenti	Podatak o studentima koji pohađaju veleuciliste.
	 * @param ispiti	Podatak o ispitima na veleucilistu.
	 */
	
	public VeleucilisteJave(Long id, String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti,
			List<Ispit> ispiti) {
		super(id, nazivUstanove, predmeti, profesori, studenti, ispiti);
		this.nazivUstanove = nazivUstanove;
		this.predmeti = predmeti;
		this.profesori = profesori;
		this.studenti = studenti;
		this.ispiti = ispiti;
	}
	/**
	 * Inicijalizacija podataka o nazivu veleucilista, predmetima na veleucilistu, profesorima koji predaju na veleucilistu,
	 * studentima koji pohađaju veleuciliste i ispitima koji se pisu na veleucilistu
	 * @param nazivUstanove	Podatak o nazivu veleuclista.
	 * @param predmeti	Podatak o predmetima na veleuclistu.
	 * @param profesori	Podatak o profesorima koji predaju na veleucilistu.
	 * @param studenti	Podatak o studentima koji pohađaju veleuciliste.
	 * @param ispiti	Podatak o ispitima na veleucilistu.
	 */
	public VeleucilisteJave(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti,
			List<Ispit> ispiti) {
		super(nazivUstanove, predmeti, profesori, studenti, ispiti);
		this.nazivUstanove = nazivUstanove;
		this.predmeti = predmeti;
		this.profesori = profesori;
		this.studenti = studenti;
		this.ispiti = ispiti;
	}
	/**
	 * @author tgtom
	 * @return Vraca konacnu ocjenu tipa BigDecimal koja se u glavnoj klasi zaokruzuje.
	 * Izracunava konacnu ocjenu studija za odredenog studenta na temelju ocjena iz ispita, zavrsnog rada i obrane zavrsnog rada.
	 */
	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int ocjenaPismenog,
			int ocjenaObrane) {
		BigDecimal ocjenaStudija = new BigDecimal(ocjenaPismenog + ocjenaObrane);
		BigDecimal konacnaOcjena = null;
		
		BigDecimal dva = new BigDecimal(2);
		BigDecimal jedan = new BigDecimal(1);
		try {
			konacnaOcjena = dva.multiply((odrediProsjekOcjenaNaIspitima(ispiti)));
			konacnaOcjena = konacnaOcjena.add(ocjenaStudija);
			konacnaOcjena = konacnaOcjena.divide(new BigDecimal(4));
		} catch (NemoguceOdreditiProsjekStudentaException ex) {
			
			log.error("Iznimka: " + ex);
			System.out.println("Student " + ((Ispit) ispiti).getStudentX().getIme() + " " + ((Ispit) ispiti).getStudentX().getPrezime()
					+ " zbog negativne ocjene na jednom od ispita ima prosjek " + Ocjena.NEDOVOLJAN.getOpisOcjene() + " (" +
					Ocjena.NEDOVOLJAN.getOcjena() + ")!");
			konacnaOcjena = jedan;
			}
		
		
		
		return konacnaOcjena;
	};
		
	/**
	 * @author tgtom
	 * Odreduje najuspjesnijeg studenta u zadanoj godini na temelju studentovog prosjeka.
	 * @param list Lista studenta studija
	 * @return Vraca najuspjesnijeg studenta (tipa Student). 
	 */
	
	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina, List<Student> studentiStudija) {
		BigDecimal jedan = new BigDecimal(1);
		List<BigDecimal> prosjeci = new ArrayList<>();
		for(Student studenti1 : studentiStudija) {
		
			try {
				prosjeci.add(odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(ispiti, studenti1)));
			} catch (NemoguceOdreditiProsjekStudentaException e) {
			
				prosjeci.add(jedan);
			}
			
		}
		
		 for(int i = 0; i < studentiStudija.size()-1; i++){
		        for(int j = 1; j < studentiStudija.size(); j++){
		            if(prosjeci.get(i).compareTo(prosjeci.get(j)) == -1){
		                Student temp = studentiStudija.get(j);
		                studentiStudija.set(j, studentiStudija.get(i));
		                studentiStudija.set(i, temp);
		            }
		        }
		    }
		return studentiStudija.get(0);
	};
	/**
	 * @author tgtom
	 * Odreduje najuspjesnijeg studenta u zadanoj godini na temelju studentovog prosjeka.
	 * @return Vraca najuspjesnijeg studenta (tipa Student). 
	 */
	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		BigDecimal jedan = new BigDecimal(1);
		List<BigDecimal> prosjeci = new ArrayList<>();
		for(Student studenti1 : studenti) {
		
			try {
				prosjeci.add(odrediProsjekOcjenaNaIspitima(filtrirajIspitePoStudentu(ispiti, studenti1)));
			} catch (NemoguceOdreditiProsjekStudentaException e) {
			
				prosjeci.add(jedan);
			}
			
		}
		
		 for(int i = 0; i < studenti.size()-1; i++){
		        for(int j = 1; j < studenti.size(); j++){
		            if(prosjeci.get(i).compareTo(prosjeci.get(j)) == -1){
		                Student temp = studenti.get(j);
		                studenti.set(j, studenti.get(i));
		                studenti.set(i, temp);
		            }
		        }
		    }
		return studenti.get(0);
	};
	
	
}

