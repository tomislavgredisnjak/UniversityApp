package hr.java.vjezbe.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
/**
 * Predstavlja klasu pomocu koje učitavamo datoteke.
 * @author tgtom
 *
 */
public class Datoteke {

	
	/**
	 * Predstavlja metodu ucitavanja profesora.
	 * @return	Vraca listu profesora.
	 */
	public static List<Profesor> ucitajDatotekuProfesora() {
		List<Profesor> profesori = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader("dat/profesori.txt"))){
			String line;
			int linija = 0;
			String sifra = null;
			String ime = null;
			String prezime = null;
			String titula = null;
			Long id = null;
			for(int i = 0; i >= 0 ; i++) {
				line = in.readLine();
				if(line != null) {
					if(i == 0 || i == linija) {
						id = Long.parseLong(line);
					}
					else if(i == 1 || i == linija+1) {
						
						sifra = line;
					}
					else if(i == 2 || i == linija+2) {
						ime = line;
					}
					else if(i == 3 || i == linija+3) {
						prezime = line;
					}
					else if(i == 4 || i == linija+4) {
						titula = line;
						
						Profesor profesori1 = new Profesor(id, sifra, ime, prezime, titula);
						profesori.add(profesori1);
						linija += 5;
					}
					
				}
				else
					break;
			}
			
		}
		catch (IOException e) {
			System.err.println(e);
			
		}
		return profesori;
	
	}
	/**
	 * Predstavlja metodu ucitavanja studenata.
	 * @return	Vraca listu studenata.
	 */
	public static List<Student> ucitajDatotekuStudenata() {
		List<Student> studenti = new ArrayList<>();
		try (BufferedReader in = new BufferedReader(new FileReader("dat/studenti.txt"))){
			String line;
			int linija = 0;
			
			String ime = null;
			String prezime = null;
			LocalDate datumRodjenja = null;
			String jmbag = null;
			DateTimeFormatter formatter = null;
			Long id = null;
			for(int i = 0; i >= 0 ; i++) {
				line = in.readLine();
				if(line != null) {
					if(i == 0 || i == linija) {
						id = Long.parseLong(line);
					}
					else if(i == 1 || i == linija+1) {
						
						ime = line;
					}
					else if(i == 2 || i == linija+2) {
						prezime = line;
					}
					else if(i == 3 || i == linija+3) {
						jmbag = line;
					}
					else if(i == 4 || i == linija+4) {
						
						String datumRodjenjaString = line;
						formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
						datumRodjenja = LocalDate.parse(datumRodjenjaString, formatter);
						Student studenti1 = new Student(id, ime, prezime, jmbag, datumRodjenja);
						studenti.add(studenti1);
						linija += 5;
					}
					
				}
				else
					break;
			}
			
		}
		catch (IOException e) {
			System.err.println(e);
			
		}
		return studenti;
		
	}
	/**
	 * Predstavlja metodu ucitavanja predmeta.
	 * @return	Vraca listu predmeta.
	 */
	public static List<Predmet> ucitajDatotekuPredmeta() {
		
		List<Profesor> profesori = ucitajDatotekuProfesora();
		List<Student> studenti = ucitajDatotekuStudenata();
		List<Predmet> predmeti = new ArrayList<>();
		
		try (BufferedReader in = new BufferedReader(new FileReader("dat/predmeti.txt"))){
			
			String line;
			int linija = 0;
			String sifra = null;
			String imePredmeta = null;
			Integer brojEctsBodova = null;
			Long idProfesora = null;
			Long id = null;
			int brojPredmeta = 0;
			for(int i = 0; i >= 0 ; i++) {
				line = in.readLine();
				if(line != null) {
					if(i == 0 || i == linija) {
						id = Long.parseLong(line);
					}
					else if(i == 1 || i == linija+1) {
						
						sifra = line;
					}
					else if(i == 2 || i == linija+2) {
						imePredmeta = line;
					}
					else if(i == 3 || i == linija+3) {
						brojEctsBodova = Integer.parseInt(line);
						
					}
					else if(i == 4 || i == linija+4) {
						idProfesora = Long.parseLong(line);
						
						for(Profesor profesorii : profesori) {
							if(profesorii.getId() == idProfesora) {
								Predmet predmeti1 = new Predmet(id, sifra, imePredmeta, brojEctsBodova, profesorii);
								predmeti.add(predmeti1);
								
							}
						}
					}
					else if(i == 5 || i == linija+5) {
						
						Scanner s = new Scanner(line);
						List<Long> idStudenata = new ArrayList<>(); 
						while(s.hasNextLong()) {
						idStudenata.add(s.nextLong());
						}
						s.close();
						for(int j = 0; j < idStudenata.size(); j++) {
							for(Student studentii : studenti) {
								if(studentii.getId().equals(idStudenata.get(j))){
									predmeti.get(brojPredmeta).setStudenti(studentii);
								}
							}
						}
						brojPredmeta++;
						linija += 6;
					}
					
				}
				else
					break;
			}
			
		}
		catch (IOException e) {
			System.err.println(e);
			
		}
		return predmeti;
		
	}
	/**
	 * Predstavlja metodu ucitavanja ispita.
	 * @return	Vraca listu ispita.
	 */
	public static List<Ispit> ucitajDatotekuIspita() {
		List<Ispit> ispiti = new ArrayList<>();
		
		List<Predmet> predmeti = ucitajDatotekuPredmeta();
		List<Student> studenti = ucitajDatotekuStudenata();
		
		String line;
		int linija = 0;
	
		Integer ocjena = null;
		LocalDateTime datumIVrijeme = null;
		Long id = null;
		Long idPredmeta = null;
		Long idStudenta = null;
		DateTimeFormatter formatter = null;
		
		try (BufferedReader in = new BufferedReader(new FileReader("dat/ispiti i ocjene.txt"))){
		
		for(int i = 0; i >= 0 ; i++) {
			line = in.readLine();
			if(line != null) {
				if(i == 0 || i == linija) {
					id = Long.parseLong(line);
				}
				else if(i == 1 || i == linija+1) {
					idPredmeta = Long.parseLong(line);
				}
				else if(i == 2 || i == linija+2) {
					idStudenta = Long.parseLong(line);
				}
				else if(i == 3 || i == linija+3) {
					ocjena = Integer.parseInt(line);
					
				}
				else if(i == 4 || i == linija+4) {
					String datumIVrijemeString = line;
					formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy.'T'HH:mm");
					datumIVrijeme = LocalDateTime.parse(datumIVrijemeString, formatter);
					
					for(Predmet predmeti1 : predmeti) {
						for(Student studenti1 : studenti) {
						if(predmeti1.getId().equals(idPredmeta) && studenti1.getId().equals(idStudenta)){
							
							Ispit ispiti1 = new Ispit(id, predmeti1, studenti1, ocjena, datumIVrijeme);
							ispiti.add(ispiti1);
							
							}
						}
					}
					
					linija += 5;
					
				}
				
				
			}
			else
				break;
		}
		
	}
	catch (IOException e) {
		System.err.println(e);	
		}
		return ispiti;
		
	}
	
}
