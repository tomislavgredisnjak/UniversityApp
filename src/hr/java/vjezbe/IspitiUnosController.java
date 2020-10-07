package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;

/**
 * Predstavlja Controller klasu za sucelje dodavanja ispita
 * @author tgtom
 *
 */
public class IspitiUnosController implements Initializable{
	
	@FXML
	private ComboBox<Predmet> nazivDodanogPredmetaIspita;

	@FXML
	private ComboBox<Student> imeDodanogStudentaIspita;
	
	@FXML
	private ComboBox<Integer> ocjenaDodanogIspita;
	
	@FXML
	private DatePicker datumDodanogIspita;
	
	@FXML
	private TextField vrijemeDodanogIspita;
	/**
	 * Predstavlja inicijalizaciju podataka prije odabira podataka
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Predmet sviPredmeti = new Predmet(null, null, null, null);
		
		List<Predmet> predmeti = null;
		try {
			predmeti = BazaPodataka.dohvatiPredmetePremaKriterijima(sviPredmeti);
		} catch (BazaPodatakaException e) {
			
			e.printStackTrace();
		}
		
		nazivDodanogPredmetaIspita.getItems().addAll(predmeti);
		
		Student sviStudenti = new Student(null, null, null, null);
		
		List<Student> studenti = null;
		try {
			studenti = BazaPodataka.dohvatiStudentePremaKriterijima(sviStudenti);
		} catch (BazaPodatakaException e) {
			e.printStackTrace();
		}
		
		imeDodanogStudentaIspita.getItems().addAll(studenti);
		
		ocjenaDodanogIspita.getItems().addAll(1,2,3,4,5);
		
	}
	/**
	 * Predstavlja metodu kojom dodajemo trazene podatke
	 */
	
public void dodajIspit() {
		
		boolean svePopunjeno = true;
		
		if(nazivDodanogPredmetaIspita.getSelectionModel().isEmpty() || imeDodanogStudentaIspita.getSelectionModel().isEmpty() || 
				ocjenaDodanogIspita.getSelectionModel().isEmpty() || datumDodanogIspita.getValue() == null || vrijemeDodanogIspita.getText().isEmpty()) {
			svePopunjeno = false;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogrešan unos podataka");
			alert.setHeaderText("Molimo ispravite sljedeće pogreške:");
			StringBuilder poruka = new StringBuilder();
			if(nazivDodanogPredmetaIspita.getSelectionModel().isEmpty()) {
				poruka.append("Naziv predmeta je obavezan podatak!\n");
			}
			if(imeDodanogStudentaIspita.getSelectionModel().isEmpty()) {
				poruka.append("Ime i prezime studenta je obavezan podatak!\n");
			}
			if(ocjenaDodanogIspita.getSelectionModel().isEmpty()) {
				poruka.append("Ocjena je obavezan podatak!\n");
			}
			if(datumDodanogIspita.getValue() == null) {
				poruka.append("Datum ispita je obavezan podatak!\n");
			}
			if(vrijemeDodanogIspita.getText().isEmpty()) {
				poruka.append("Vrijeme ispita je obavezan podatak!");
			}
			alert.setContentText(poruka.toString());
			alert.showAndWait();
		}
		DateTimeFormatter formatterDatuma = DateTimeFormatter.ofPattern("dd.MM.yyyy.");
		DateTimeFormatter formatterDatumaIvremena = DateTimeFormatter.ofPattern("dd.MM.yyyy.' 'HH:mm");
		if(svePopunjeno) {
			
		Predmet predmetX = null;
		
		Predmet sviPredmeti = new Predmet(null,null,null,null);
		
		try {
			for(Predmet predmet1 : BazaPodataka.dohvatiPredmetePremaKriterijima(sviPredmeti)) {
				if(predmet1.getNaziv().equals(nazivDodanogPredmetaIspita.getSelectionModel().getSelectedItem().getNaziv())) {
					predmetX = predmet1;
					
				}
			}
		} catch (BazaPodatakaException e1) {
			e1.printStackTrace();
		}
	
		Student studentX = null;
		Student sviStudenti = new Student(null, null, null, null);
		try {
			for(Student student1 : BazaPodataka.dohvatiStudentePremaKriterijima(sviStudenti)) {
				if(imeDodanogStudentaIspita.getSelectionModel().getSelectedItem().getId().equals(student1.getId())) {
					studentX = student1;
				}
			}
		} catch (BazaPodatakaException e1) {
			e1.printStackTrace();
		}

		LocalDateTime datumIvrijemeIspita = null;
		StringBuilder datumIvrijemeIspitaString = new StringBuilder(datumDodanogIspita.getValue().format(formatterDatuma).toString());
		datumIvrijemeIspitaString.append(" ");
		datumIvrijemeIspitaString.append(vrijemeDodanogIspita.getText());
		
		datumIvrijemeIspita = LocalDateTime.parse(datumIvrijemeIspitaString, formatterDatumaIvremena);
		
		Ispit noviIspit = new Ispit(predmetX, studentX, ocjenaDodanogIspita.getSelectionModel().getSelectedItem(), datumIvrijemeIspita);
		

		
		try {
			BazaPodataka.spremiNoviIspit(noviIspit);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješno spremanje podataka");
			alert.setHeaderText("Spremanje podataka o novom ispitu");
			alert.setContentText("Podaci o novom ispitu su uspješno dodani!");
			alert.showAndWait();
		} catch (BazaPodatakaException e) {
			e.printStackTrace();
		}
	
		

		}
	}
	
/**
 * Predstavlja metodu koja vodi na sucelje za pretragu predmeta
 */
@FXML
public void prikaziPretraguPredmeta() {
	BorderPane root;
	try {
		root = (BorderPane)FXMLLoader.load(
				getClass().getResource("predmeti.fxml"));
		Main.setMainPage(root);
	} catch (IOException e) {
		e.printStackTrace();
		}
	}
/**
 * Predstavlja metodu koja vodi na sucelje za pretragu ispita
 */
@FXML
public void prikaziPretraguIspita() {
	BorderPane root;
	try {
		root = (BorderPane)FXMLLoader.load(
				getClass().getResource("ispiti.fxml"));
		Main.setMainPage(root);
	} catch (IOException e) {
		e.printStackTrace();
		}
	}
/**
 * Predstavlja metodu koja vodi na sucelje za pretragu studenata
 */
@FXML
public void prikaziPretraguStudenta() {
	BorderPane root;
	try {
		root = (BorderPane)FXMLLoader.load(
				getClass().getResource("studenti.fxml"));
		Main.setMainPage(root);
	} catch (IOException e) {
		e.printStackTrace();
		}
	}
/**
 * Predstavlja metodu koja vodi na sucelje za pretragu profesora
 */
@FXML
public void prikaziPretraguProfesora() {
	BorderPane root;
	try {
		root = (BorderPane)FXMLLoader.load(
				getClass().getResource("profesori.fxml"));
		
		Main.setMainPage(root);
	} catch (IOException e) {
		e.printStackTrace();
		}
	}
/**
 * Predstavlja metodu koja vodi na sucelje za dodavanje profesora
 */
@FXML
public void prikaziDodavanjeProfesora() {
	BorderPane root;
	try {
		root = (BorderPane)FXMLLoader.load(
				getClass().getResource("profesoriUnos.fxml"));
		Main.setMainPage(root);
	} catch (IOException e) {
		e.printStackTrace();
		}
	}
/**
 * Predstavlja metodu koja vodi na sucelje za dodavanje studenata
 */
@FXML
public void prikaziDodavanjeStudenta() {
	BorderPane root;
	try {
		root = (BorderPane)FXMLLoader.load(
				getClass().getResource("studentiUnos.fxml"));
		Main.setMainPage(root);
	} catch (IOException e) {
		e.printStackTrace();
		}
	}
/**
 * Predstavlja metodu koja vodi na sucelje za dodavanje predmeta
 */
@FXML
public void prikaziDodavanjePredmeta() {
	BorderPane root;
	try {
		root = (BorderPane)FXMLLoader.load(
				getClass().getResource("predmetiUnos.fxml"));
		Main.setMainPage(root);
	} catch (IOException e) {
		e.printStackTrace();
		}
	}
/**
 * Predstavlja metodu koja vodi na sucelje za dodavanje ispita
 */
@FXML
public void prikaziDodavanjeIspita() {
	BorderPane root;
	try {
		root = (BorderPane)FXMLLoader.load(
				getClass().getResource("ispitiUnos.fxml"));
		Main.setMainPage(root);
	} catch (IOException e) {
		e.printStackTrace();
		}
	}
}