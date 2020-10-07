package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.util.StringConverter;
/**
 * Predstavlja Controller klasu za sucelje dodavanja studenta
 * @author tgtom
 *
 */
public class StudentiUnosController implements Initializable{
	
	@FXML
	private TextField jmbagDodanogStudenta;

	@FXML
	private TextField imeDodanogStudenta;
	
	@FXML
	private TextField prezimeDodanogStudenta;
	
	@FXML
	private DatePicker datumRodjenjaDodanogStudenta;
	/**
	 * Predstavlja inicijalizaciju podataka prije dodavanja podataka
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		datumRodjenjaDodanogStudenta.setConverter(new StringConverter<LocalDate>() {
			 String pattern = "dd. MM. yyyy.";
			 DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

			 @Override public String toString(LocalDate date) {
			     if (date != null) {
			         return dateFormatter.format(date);
			     } else {
			         return "";
			     }
			 }

			 @Override public LocalDate fromString(String string) {
			     if (string != null && !string.isEmpty()) {
			         return LocalDate.parse(string, dateFormatter);
			     } else {
			         return null;
			     }
			 }
			});
		
	}
	/**
	 * Predstavlja metodu kojom dodajemo trazene podatke
	 */
	public void dodajStudenta() {
		
		boolean svePopunjeno = true;
		
		if(jmbagDodanogStudenta.getText().isEmpty() || imeDodanogStudenta.getText().isEmpty() || 
				prezimeDodanogStudenta.getText().isEmpty() || datumRodjenjaDodanogStudenta.getValue() == null) {
			svePopunjeno = false;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogrešan unos podataka");
			alert.setHeaderText("Molimo ispravite sljedeće pogreške:");
			StringBuilder poruka = new StringBuilder();
			if(jmbagDodanogStudenta.getText().isEmpty()) {
				poruka.append("JMBAG je obavezan podatak!\n");
			}
			if(imeDodanogStudenta.getText().isEmpty()) {
				poruka.append("Ime je obavezan podatak!\n");
			}
			if(prezimeDodanogStudenta.getText().isEmpty()) {
				poruka.append("Prezime je obavezan podatak!\n");
			}
			if(datumRodjenjaDodanogStudenta.getValue() == null) {
				poruka.append("Datum rođenja je obavezan podatak!");
			}
			alert.setContentText(poruka.toString());
			alert.showAndWait();
		}
		
		
		if(svePopunjeno) {
		Student noviStudent = new Student(imeDodanogStudenta.getText(), prezimeDodanogStudenta.getText(),
				 jmbagDodanogStudenta.getText(), datumRodjenjaDodanogStudenta.getValue());
		try {
			BazaPodataka.spremiNovogStudenta(noviStudent);
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Uspješno spremanje podataka");
			alert.setHeaderText("Spremanje podataka o novom studentu");
			alert.setContentText("Podaci o novom studentu su uspješno dodani!");
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