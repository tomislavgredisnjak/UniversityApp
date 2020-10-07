package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
/**
 * Predstavlja Controller klasu za sucelje izbornika profesora
 * @author tgtom
 *
 */
public class ProfesoriController implements Initializable{


	
	@FXML
	private TableView<Profesor> tablicaProfesori;

	@FXML
	private TableColumn<Profesor, String> sifra;
	@FXML
	private TableColumn<Profesor, String> ime;
	@FXML
	private TableColumn<Profesor, String> prezime;
	@FXML
	private TableColumn<Profesor, String> titula;
	
	@FXML
	private TextField sifraTrazenogProfesora;

	@FXML
	private TextField imeTrazenogProfesora;
	
	@FXML
	private TextField prezimeTrazenogProfesora;
	
	@FXML
	private TextField titulaTrazenogProfesora;
	/**
	 * Predstavlja inicijalizaciju podataka prije filtriranja podataka
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		Profesor sviProfesori = new Profesor(null, null, null, null);
		
		ObservableList<Profesor> listaProfesora = null;
		try {
			listaProfesora = FXCollections.observableArrayList(
					
					BazaPodataka.dohvatiProfesorePremaKriterijima(sviProfesori)
					
					);
		} catch (BazaPodatakaException e) {
			e.printStackTrace();
		}	
		sifra.setCellValueFactory(new PropertyValueFactory<Profesor, String>("Sifra"));
		ime.setCellValueFactory(new PropertyValueFactory<Profesor, String>("Ime"));
		prezime.setCellValueFactory(new PropertyValueFactory<Profesor, String>("Prezime"));
		titula.setCellValueFactory(new PropertyValueFactory<Profesor, String>("Titula"));
		tablicaProfesori.setItems(listaProfesora);
		
	}
	/**
	 * Predstavlja metodu kojom filtriramo trazene podatke
	 */
	public void pretraziProfesora(){
		
		Profesor trazeniProfesori = new Profesor(sifraTrazenogProfesora.getText(), imeTrazenogProfesora.getText(),
				prezimeTrazenogProfesora.getText(), titulaTrazenogProfesora.getText());
		
		List<Profesor> listaProfesora = null;
		
		try {
			listaProfesora = BazaPodataka.dohvatiProfesorePremaKriterijima(trazeniProfesori);
		} catch (BazaPodatakaException e) {
			e.printStackTrace();
		}
		
		ObservableList<Profesor> listaProfesora1 = FXCollections.observableArrayList(
				
				listaProfesora
				
				);
		
		sifra.setCellValueFactory(new PropertyValueFactory<Profesor, String>("Sifra"));
		ime.setCellValueFactory(new PropertyValueFactory<Profesor, String>("Ime"));
		prezime.setCellValueFactory(new PropertyValueFactory<Profesor, String>("Prezime"));
		titula.setCellValueFactory(new PropertyValueFactory<Profesor, String>("Titula"));
		tablicaProfesori.setItems(listaProfesora1);
		
		
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


