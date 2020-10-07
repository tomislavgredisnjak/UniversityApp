package hr.java.vjezbe.niti;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class DatumRodjenjaNit implements Runnable {

	@Override
	public void run() {

		StringBuilder poruka = new StringBuilder();
		
		try {
			for(Student student : BazaPodataka.dohvatiSlavljenike()) {
				poruka.append(student.toString() + "\n");
			}
		} catch (BazaPodatakaException e) {
			e.printStackTrace();
		}
		
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setTitle("Rođendan studenata");
		alert.setHeaderText("Čestitajte rođendan sljedećim studentima: ");
		alert.setContentText(poruka.toString());
		alert.showAndWait();
		
	}
	

	
}
