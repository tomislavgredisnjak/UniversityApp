package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
/**
 * Predstavlja Controller klasu za sucelje izbornika predmeta
 * @author tgtom
 *
 */
public class PredmetiController implements Initializable{

	

	@FXML
	private TableView<Predmet> tablicaPredmeta;

	
	@FXML
	private TableColumn<Predmet, String> sifra;
	@FXML
	private TableColumn<Predmet, String> naziv;
	@FXML
	private TableColumn<Predmet, Integer> brojEctsBodova;
	@FXML
	private TableColumn<Predmet, String> nositelj;
	
	@FXML
	private TextField sifraTrazenogPredmeta;

	@FXML
	private TextField nazivTrazenogPredmeta;
	
	@FXML
	private TextField brojEctsBodovaTrazenogPredmeta;
	
	@FXML
	private ComboBox<Profesor> nositeljTrazenogPredmeta;
	
	/**
	 * Predstavlja inicijalizaciju podataka prije filtriranja podataka
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
		
		nositeljTrazenogPredmeta.getItems().addAll(profesori);
		
		
		Predmet sviPredmeti = new Predmet(null, null, null, null);
		
		ObservableList<Predmet> listaPredmeta = null;
		try {
			listaPredmeta = FXCollections.observableArrayList(
					
					BazaPodataka.dohvatiPredmetePremaKriterijima(sviPredmeti)
					
					);
		} catch (BazaPodatakaException e) {
			e.printStackTrace();
		}	
		sifra.setCellValueFactory(new PropertyValueFactory<Predmet, String>("Sifra"));
		naziv.setCellValueFactory(new PropertyValueFactory<Predmet, String>("Naziv"));
		brojEctsBodova.setCellValueFactory(new PropertyValueFactory<Predmet, Integer>("brojEctsBodova"));
		nositelj.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Predmet, String>,
				ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Predmet, String> predmet) {
				SimpleStringProperty property = new
				SimpleStringProperty();

				property.setValue(predmet.getValue().getNositelj().getIme() + " " + predmet.getValue().getNositelj().getPrezime());
				return property;
				}
				});
		
		tablicaPredmeta.setItems(listaPredmeta);
		
	}
	/**
	 * Predstavlja metodu kojom filtriramo trazene podatke
	 */
	public void pretraziPredmet(){

		Predmet trazeniPredmeti = null;
		
		if(!brojEctsBodovaTrazenogPredmeta.getText().isBlank()) {
		trazeniPredmeti = new Predmet(sifraTrazenogPredmeta.getText(),
				nazivTrazenogPredmeta.getText(), Integer.parseInt(brojEctsBodovaTrazenogPredmeta.getText()),
				nositeljTrazenogPredmeta.getSelectionModel().getSelectedItem());
		}
		else {
		trazeniPredmeti = new Predmet(sifraTrazenogPredmeta.getText(),
					nazivTrazenogPredmeta.getText(), null,
					nositeljTrazenogPredmeta.getSelectionModel().getSelectedItem());
		}
		List<Predmet> listaPredmeta = null;
		
		try {
			listaPredmeta = BazaPodataka.dohvatiPredmetePremaKriterijima(trazeniPredmeti);
		} catch (BazaPodatakaException e) {
			e.printStackTrace();
		}
		
		ObservableList<Predmet> listaPredmeta1 = null;
		
		if(listaPredmeta != null) {
		listaPredmeta1 = FXCollections.observableArrayList(
				
				listaPredmeta
				
				);
		}
		
		sifra.setCellValueFactory(new PropertyValueFactory<Predmet, String>("Sifra"));
		naziv.setCellValueFactory(new PropertyValueFactory<Predmet, String>("Naziv"));
		brojEctsBodova.setCellValueFactory(new PropertyValueFactory<Predmet, Integer>("brojEctsBodova"));
		nositelj.setCellValueFactory(
				
				new Callback<TableColumn.CellDataFeatures<Predmet, String>,
				ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Predmet, String> predmet) {
				SimpleStringProperty property = new
				SimpleStringProperty();
				String profesor1 = new String(predmet.getValue().getNositelj().getIme() + " " + predmet.getValue().getNositelj().getPrezime());
				property.setValue(profesor1);
				return property;
				}
				});
		
		tablicaPredmeta.setItems(listaPredmeta1);
		
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
