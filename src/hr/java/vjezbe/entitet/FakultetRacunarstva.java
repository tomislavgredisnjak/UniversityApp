package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;
import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;

/**
 * Predstavlja entitet fakulteta koji je definiran nazivom ustanove, predmetima, profesorima, studentima i ispitima.
 * Nasljeđuje apstraktnu klasu ObrazovnaUstanova i implementira sucelje Diplomski.
 * 
 * 
 * @author tgtom
 *
 */
public class FakultetRacunarstva extends ObrazovnaUstanova implements Diplomski{

	private String nazivUstanove;
	private List<Predmet> predmeti;
	private List<Profesor> profesori;
	private List<Student> studenti;
	private List<Ispit> ispiti;
	
	public static final Logger log = LoggerFactory.getLogger(FakultetRacunarstva.class);
	
	/**
	 * Inicijalizira podatke potrebne za stvaranje objekta fakulteta.
	 * @author tgtom
	 * @param id podatak o id-u ustanove
	 * @param nazivUstanove podatak o nazivu fakulteta.
	 * @param predmeti podatak o predmetima na fakultetu.
	 * @param profesori	podatak o profesorima na fakultetu.
	 * @param studenti	podatak o studentima koji pohađaju fakultet.
	 * @param ispiti	podatak o ispitima na fakultetu.
	 */
	public FakultetRacunarstva(Long id, String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti,
			List<Ispit> ispiti) {
		super(id, nazivUstanove, predmeti, profesori, studenti, ispiti);
		this.nazivUstanove = nazivUstanove;
		this.predmeti = predmeti;
		this.profesori = profesori;
		this.studenti = studenti;
		this.ispiti = ispiti;
	}
	/**
	 * Inicijalizira podatke potrebne za stvaranje objekta fakulteta
	 * @param nazivUstanove	podatak o nazivu fakulteta.
	 * @param predmeti	podatak o predmetima na fakultetu.
	 * @param profesori	podatak o profesorima na fakultetu.
	 * @param studenti	podatak o studentima koji pohađaju fakultet.
	 * @param ispiti	podatak o ispitima na fakultetu.
	 */
	
	public FakultetRacunarstva(String nazivUstanove, List<Predmet> predmeti, List<Profesor> profesori, List<Student> studenti,
			List<Ispit> ispiti) {
		super(nazivUstanove, predmeti, profesori, studenti, ispiti);
		this.nazivUstanove = nazivUstanove;
		this.predmeti = predmeti;
		this.profesori = profesori;
		this.studenti = studenti;
		this.ispiti = ispiti;
	}
	/**
	 * Izracunava konacnu ocjenu studija određenog studenta.
	 * @return Vraca konacnu ocjenu.
	 * @author tgtom
	 */
	@Override
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int ocjenaDiplomskog,
			int ocjenaObraneDiplomskog) {

		BigDecimal ocjenaStudija = new BigDecimal(ocjenaDiplomskog + ocjenaObraneDiplomskog);
		BigDecimal konacnaOcjena = null;
		BigDecimal jedan = new BigDecimal(1);
		
			 BigDecimal tri = new BigDecimal(3);
		try {
			konacnaOcjena = tri.multiply((odrediProsjekOcjenaNaIspitima(ispiti)));
			konacnaOcjena = konacnaOcjena.add(ocjenaStudija);
			konacnaOcjena = konacnaOcjena.divide(new BigDecimal(5));
		} catch (NemoguceOdreditiProsjekStudentaException ex) {
			
			log.error("Iznimka: " + ex);
			System.out.println("Student " + ((Ispit) ispiti).getStudentX().getIme() + " " + ((Ispit) ispiti).getStudentX().getPrezime()
					+ " zbog negativne ocjene na jednom od ispita ima prosjek " + Ocjena.NEDOVOLJAN.getOpisOcjene() + 
					" (" + Ocjena.NEDOVOLJAN.getOcjena() + ").");
			konacnaOcjena = jedan;
			
		}
	
		
	
		return konacnaOcjena;
	};
	
	/**
	 * Odreduje najuspjesnijeg studenta fakulteta određene godine.
	 * 
	 * @return Vraca najuspjesnijeg studenta.
	 * 
	 * @author tgtom
	 */
	
	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina) {
		List<Integer> najUspjesnijiStudent = new ArrayList<>();
		for(Student studenti1 : studenti) {
			int brojac = 0;
			for(Ispit ispiti1 : ispiti) {
				if(ispiti1.getOcjena() == Ocjena.ODLICAN.getOcjena() && studenti1.getJmbag() == ispiti1.getStudentX().getJmbag()) {
					brojac++;
					}
				
				}
			najUspjesnijiStudent.add(brojac);
		}
		
		 for(int i = 0; i < studenti.size()-1; i++){
		        for(int j = 1; j < studenti.size(); j++){
		            if(najUspjesnijiStudent.get(i) < najUspjesnijiStudent.get(j)){
		                Student temp = studenti.get(j);
		                studenti.set(j, studenti.get(i));
		                studenti.set(i, temp);
		            }
		        }
		    }
		 return studenti.get(0);
	};
	
	/**
	 * Odreduje najuspjesnijeg studenta fakulteta određene godine.
	 * 
	 * @return Vraca najuspjesnijeg studenta.
	 * 
	 * @author tgtom
	 */
	
	@Override
	public Student odrediNajuspjesnijegStudentaNaGodini(int godina, List<Student> studentiStudija) {
		List<Integer> najUspjesnijiStudent = new ArrayList<>();
		for(Student studenti1 : studentiStudija) {
			int brojac = 0;
			for(Ispit ispiti1 : ispiti) {
				if(ispiti1.getOcjena() == Ocjena.ODLICAN.getOcjena() && studenti1.getJmbag() == ispiti1.getStudentX().getJmbag()) {
					brojac++;
					}
				
				}
			najUspjesnijiStudent.add(brojac);
		}
		
		 for(int i = 0; i < studentiStudija.size()-1; i++){
		        for(int j = 1; j < studentiStudija.size(); j++){
		            if(najUspjesnijiStudent.get(i) < najUspjesnijiStudent.get(j)){
		                Student temp = studentiStudija.get(j);
		                studentiStudija.set(j, studentiStudija.get(i));
		                studentiStudija.set(i, temp);
		            }
		        }
		    }
		 return studentiStudija.get(0);
	};

	/**
	 * 
	 * Određuje studenta koji je osvojio rektorovu nagradu.
	 * 
	 * @return Vraca studenta s najvecim prosjekom (ukoliko 2 studenta imaju isti najnizi prosjek, vraca mlađeg)
	 * 
	 * @author tgtom
	 * 
	 */
	
	@Override
	public Student odrediStudentaZaRektorovuNagradu() throws PostojiViseNajmladjihStudenataException{
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
		            if(prosjeci.get(i).compareTo(prosjeci.get(j)) == -1 || ((prosjeci.get(i).compareTo(prosjeci.get(j)) == 0) && 
		            		(studenti.get(i).getDatumRodjenja().isAfter(studenti.get(j).getDatumRodjenja()) == true))){
		                Student temp = studenti.get(j);
		                studenti.set(j, studenti.get(i));
		                studenti.set(i, temp);
		            }
		        }
		    }
		 for(int i = 0; i < studenti.size()-1; i++) {
			 for(int j = 1; j < studenti.size(); j++) {
				
				 if(studenti.get(i).getDatumRodjenja().equals(studenti.get(j).getDatumRodjenja())) {
					 throw new PostojiViseNajmladjihStudenataException("Pronađeno je više najmlađih studenata s istim datumom rođenja, a to su"
					 		+ " " + studenti.get(i).getIme() + " " + studenti.get(i).getPrezime() + 
							 " i " + studenti.get(j).getIme() + " " + studenti.get(j).getPrezime() + ".");
					 
				 }
				 else{
					 i = studenti.size() - 1; 
				 }
			 }
		 }
		 
		 
		return studenti.get(0);
	};
	
}