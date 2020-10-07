package hr.java.vjezbe.entitet;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import hr.java.vjezbe.iznimke.NemoguceOdreditiProsjekStudentaException;

/**
 * Predstavlja sucelje Visokoskolska koje nasljeđuje sucelje diplomski i implementira klasa VeleucilisteJave
 * @author tgtom
 *
 */

public interface Visokoskolska{
	/**@author tgtom
	 * Apstraktna metoda koja izracunava konacnu ocjenu studija za odredenog studenta na temelju studentovih ocjena iz ispita,
	 * zavrsnog rada i obrane zavrsnog rada
	 * @param ispiti	Podatak o ispitima studenta.
	 * @param ocjenaPismenog	Podatak o ocjeni pismenog dijela zavrsnog rada.
	 * @param ocjenaObrane	Podatak o ocjeni obrane zavrsnog rada. 
	 * @return	Vraca konacnu ocjenu studenta, tipa BigDecimal
	 */
	public BigDecimal izracunajKonacnuOcjenuStudijaZaStudenta(List<Ispit> ispiti, int ocjenaPismenog,
			int ocjenaObrane);
	/**@author tgtom
	 * Određuje prosjek ocjena na ispitima, ukoliko je ocjena na nekog od ispita nedovoljan, baca NemoguceOdreditiProsjekStudentaException iznimku
	 * @param ispiti	Prima podatak o ispitima studenta kojem je potrebno odrediti prosjek ocjena na ispitima
	 * @return	Vraca prosjek ocjena na ispitima, tipa BigDecimal
	 * @throws NemoguceOdreditiProsjekStudentaException	Baca ovu iznimku ukoliko je jedan od ispita ocjenjen sa nedovoljan.
	 */
	default public BigDecimal odrediProsjekOcjenaNaIspitima(List<Ispit> ispiti) throws NemoguceOdreditiProsjekStudentaException{
			BigDecimal prosjek = null;
			int zbrojOcjena = 0;
			int brojPolozenihIspita = 0;
			for(Ispit ispiti1 : ispiti) {
				if(ispiti1.getOcjena() > Ocjena.NEDOVOLJAN.getOcjena()) {
					zbrojOcjena = zbrojOcjena + ispiti1.getOcjena();
					brojPolozenihIspita++;
				}
				else if(ispiti1.getOcjena() == 1) {
					throw new NemoguceOdreditiProsjekStudentaException("Student " + ispiti1.getStudentX().getIme()
							+ " " + ispiti1.getStudentX().getPrezime() + " je ocjenjen ocjenom " + Ocjena.NEDOVOLJAN.getOpisOcjene() + 
							"(" + Ocjena.NEDOVOLJAN.getOpisOcjene() + ") iz " + "predmeta " + ispiti1.getPredmetX().getNaziv() + ".");
				}
			}
			
			prosjek = BigDecimal.valueOf(zbrojOcjena/brojPolozenihIspita);
			
			return prosjek;
		};
	
	/**
	 * Filtrira ispite na kojima je student ocjenjen ocjenom vecom od nedovoljan
	 * @author tgtom
	 * @param ispiti Prima podatke o ispitima studenta kojemu je potrebno filtrirati polozene ispite.
	 * @return	Vraca ispite koje je student položio.
	 */
	private List<Ispit> filtrirajPolozeneIspite(List<Ispit> ispiti) {
		List<Ispit> pozitivniIspiti = new ArrayList<>();
		
		for(Ispit ispiti1 : ispiti) {
			if(ispiti1.getOcjena() > Ocjena.NEDOVOLJAN.getOcjena()) {
				pozitivniIspiti.add(ispiti1);
			
			}
		}
		return pozitivniIspiti;
	};
	/**
	 * Filtrira ispite na koje je student izasao
	 * @author tgtom
	 * @param ispiti	Prima ispite na koje je student izasao.
	 * @param student	Prima podatke o studentu kojemu treba filtrirati ispite
	 * @return	Vraca ispite na koje je student izasao.
	 */
	default public List<Ispit> filtrirajIspitePoStudentu(List<Ispit> ispiti, Student student) {
		
	
		List<Ispit> ispitiStudenta = new ArrayList<>();
		ispitiStudenta = ispiti.stream()
				.filter(i -> i.getStudentX().getJmbag().equals(student.getJmbag()))
				.collect(Collectors.toList());
				
		
		return ispitiStudenta;
	};
	
}
