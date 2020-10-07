package hr.java.vjezbe.sortiranje;

import java.util.Comparator;

import hr.java.vjezbe.entitet.Student;
/**
 * Predstavlja klasu koja sortira studente, nasljeđuje Comparator
 * @author tgtom
 *
 */
public class StudentSorter implements Comparator<Student>{
	
	/**
	 * Uspoređuje dva studenta i zamjenjuje im mjesta u listi ovisno o prezimenu. Ako je isto prezime, ovisno o imenu.
	 */
	@Override
	public int compare(Student st1, Student st2) {
		if(st1.getPrezime().compareTo(st2.getPrezime()) > 0) {
			return 1;
		}
		
		else if(st1.getPrezime().compareTo(st2.getPrezime()) == 0) {
			if(st1.getIme().compareTo(st2.getIme()) > 0) {
				return 1;
			}
			else {
				return -1;
			}
		}
		
		else {
			return -1;
		}
	}
	
}
