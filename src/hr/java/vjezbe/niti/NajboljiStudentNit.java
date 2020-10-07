package hr.java.vjezbe.niti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.OptionalDouble;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.stage.Stage;

public class NajboljiStudentNit implements Runnable{

	private static Stage stage;
	
	public NajboljiStudentNit(Stage primaryStage) {
		
		stage = primaryStage;
		
	}

	
	@Override
	public void run() {
		
		Student sviS = new Student(null,null,null,null);
		Ispit sviI = new Ispit(null,null,null,null);
		try {
			List<Student> sviStudenti = BazaPodataka.dohvatiStudentePremaKriterijima(sviS);
			List<Ispit> sviIspiti = BazaPodataka.dohvatiIspitePremaKriterijima(sviI);
			Map<Student, Double> prosjekStudenta = new HashMap<>();
			for(Student student : sviStudenti) {
				List<Ispit> listaIspitaZaStudenta = new ArrayList<>();
				for(Ispit ispit : sviIspiti) {
					if(student.getId().equals(ispit.getStudentX().getId())) {
						listaIspitaZaStudenta.add(ispit);
					}
				}
				OptionalDouble prosjek = listaIspitaZaStudenta.stream().mapToDouble(i ->
				i.getOcjena()).average();
				if(!prosjek.isEmpty()) {
					prosjekStudenta.put(student, prosjek.getAsDouble());
				}
			}
			Map.Entry<Student, Double> maxEntry = null;

			for (Map.Entry<Student, Double> entry : prosjekStudenta.entrySet())
			{
			    if (maxEntry == null || entry.getValue().compareTo(maxEntry.getValue()) > 0)
			    {
			        maxEntry = entry;
			        
			    }
			}
			
			stage.setTitle("Najbolji student: " + maxEntry.getKey().getPrezime() + " " + maxEntry.getKey().getIme()
					+ "(" + maxEntry.getValue().toString() + ")");
			
		} catch (BazaPodatakaException e) {
			e.printStackTrace();
		}
		
		
		
	}

}
