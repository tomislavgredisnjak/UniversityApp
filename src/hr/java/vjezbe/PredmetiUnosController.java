package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
/**
 * Predstavlja Controller klasu za sucelje dodavanja predmeta
 * @author tgtom
 *
 */
public class PredmetiUnosController implements Initializable{
	
	@FXML
	private TextField sifraDodanogPredmeta;

	@FXML
	private TextField nazivDodanogPredmeta;
	
	@FXML
	private TextField brojEctsBodovaDodanogPredmeta;
	
	@FXML
	private ComboBox<Profesor> nositeljDodanogPredmeta;
	/**
	 * Predstavlja inicijalizaciju podataka prije dodavanja podataka
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Profesor sviProfesori = new Profesor(null, null, null, null);
		
		List<Profesor> profesori = null;
		try {
			profesori = BazaPodataka.dohvatiProfesorePremaKriterijima(sviProfesori);
		} catch (BazaPodatakaException e1) {
			e1.printStackTrace();
		}
		
		nositeljDodanogPredmeta.getItems().addAll(profesori);
		
	}
	/**
	 * Predstavlja metodu kojom dodajemo trazene podatke
	 */
	
	public void dodajPredmet() {
		
		boolean svePopunjeno = true;
		
		Profesor sviProfesori = new Profesor(null, null, null, null);
		List<Profesor> profesori = null;
		try {
			profesori = BazaPodataka.dohvatiProfesorePremaKriterijima(sviProfesori);
		} catch (BazaPodatakaException e1) {
			e1.printStackTrace();
		}
		
		nositeljDodanogPredmeta.getItems().addAll(profesori);
		
		if(sifraDodanogPredmeta.getText().isEmpty() || nazivDodanogPredmeta.getText().isEmpty() || 
				brojEctsBodovaDodanogPredmeta.getText().isEmpty() || nositeljDodanogPredmeta.getSelectionModel().isEmpty()) {
			svePopunjeno = false;
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogrešan unos podataka");
			alert.setHeaderText("Molimo ispravite sljedeće pogreške:");
			StringBuilder poruka = new StringBuilder();
			if(sifraDodanogPredmeta.getText().isEmpty()) {
				poruka.append("Šifra je obavezan podatak!\n");
			}
			if(nazivDodanogPredmeta.getText().isEmpty()) {
				poruka.append("Naziv je obavezan podatak!\n");
			}
			if(brojEctsBodovaDodanogPredmeta.getText().isEmpty()) {
				poruka.append("Broj ECTS bodova je obavezan podatak!\n");
			}
			if(nositeljDodanogPredmeta.getSelectionModel().isEmpty()) {
				poruka.append("Nositelj je obavezan podatak!");
			}
			alert.setContentText(poruka.toString());
			alert.showAndWait();
		}
		
		if(svePopunjeno) {
		Profesor nositelj = null;
		for(Profesor profesori1 : profesori) {
			if(nositeljDodanogPredmeta.getSelectionModel().getSelectedItem().getId().equals(profesori1.getId())) {
				nositelj = profesori1;
			}
		}
		
		
		
		Predmet noviPredmet = new Predmet(sifraDodanogPredmeta.getText(),
				nazivDodanogPredmeta.getText(), Integer.parseInt(brojEctsBodovaDodanogPredmeta.getText()),
				nositelj);
			
			try {
				BazaPodataka.spremiNoviPredmet(noviPredmet);
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Uspješno spremanje podataka");
				alert.setHeaderText("Spremanje podataka o novom predmetu");
				alert.setContentText("Podaci o novom predmetu su uspješno dodani!");
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