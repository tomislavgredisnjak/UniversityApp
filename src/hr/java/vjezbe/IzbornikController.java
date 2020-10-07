package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
/**
 * Predstavlja pocetno sucelje
 * @author tgtom
 *
 */
public class IzbornikController implements Initializable{
	

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
/**
 * Predstavlja metodu koju je potrebno naslijediti kako bi mogli kreirati klasu
 */
@Override
public void initialize(URL arg0, ResourceBundle arg1) {
	// TODO Auto-generated method stub
	
}
}