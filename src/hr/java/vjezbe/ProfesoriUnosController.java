package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;


/**
 * Predstavlja Controller klasu za sucelje dodavanja profesora
 * @author tgtom
 *
 */
public class ProfesoriUnosController implements Initializable{
	
	@FXML
	private TextField sifraDodanogProfesora;

	@FXML
	private TextField imeDodanogProfesora;
	
	@FXML
	private TextField prezimeDodanogProfesora;
	
	@FXML
	private TextField titulaDodanogProfesora;
	/**
	 * Predstavlja inicijalizaciju podataka prije dodavanja podataka
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}
	/**
	 * Predstavlja metodu kojom dodajemo trazene podatke
	 */
	public void dodajProfesora() {
		
		boolean svePopunjeno = true;
		
		if(sifraDodanogProfesora.getText().isEmpty() || imeDodanogProfesora.getText().isEmpty() || 
				prezimeDodanogProfesora.getText().isEmpty() || titulaDodanogProfesora.getText().isEmpty()) {
			svePopunjeno = false;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogrešan unos podataka");
			alert.setHeaderText("Molimo ispravite sljedeće pogreške:");
			StringBuilder poruka = new StringBuilder();
			if(sifraDodanogProfesora.getText().isEmpty()) {
				poruka.append("Šifra je obavezan podatak!\n");
			}
			if(imeDodanogProfesora.getText().isEmpty()) {
				poruka.append("Ime je obavezan podatak!\n");
			}
			if(prezimeDodanogProfesora.getText().isEmpty()) {
				poruka.append("Prezime je obavezan podatak!\n");
			}
			if(titulaDodanogProfesora.getText().isEmpty()) {
				poruka.append("Titula je obavezan podatak!");
			}
			alert.setContentText(poruka.toString());
			alert.showAndWait();
		}
		
		Profesor noviProfesor = new Profesor(sifraDodanogProfesora.getText(),
				imeDodanogProfesora.getText(), prezimeDodanogProfesora.getText(),
				titulaDodanogProfesora.getText());
		
		if(svePopunjeno) {
			
			try {
				BazaPodataka.spremiNovogProfesora(noviProfesor);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Uspješno spremanje podataka");
				alert.setHeaderText("Spremanje podataka o novom profesoru");
				alert.setContentText("Podaci o novom profesoru su uspješno dodani!");
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


