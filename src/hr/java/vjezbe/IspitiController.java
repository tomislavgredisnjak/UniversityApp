package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
/**
 * Predstavlja Controller klasu za sucelje izbornika ispita
 * @author tgtom
 *
 */
public class IspitiController implements Initializable{

	
	@FXML
	private TableView<Ispit> tablicaIspita;

	
	@FXML
	private TableColumn<Ispit, String> nazivPredmeta;
	@FXML
	private TableColumn<Ispit, String> imeStudentaIspita;
	@FXML
	private TableColumn<Ispit, Integer> ocjenaStudentaIspita;
	@FXML
	private TableColumn<Ispit, String> datumIVrijemeIspita;
	
	@FXML
	private ComboBox<Predmet> nazivTrazenogPredmetaIspita;

	@FXML
	private ComboBox<Student> imeTrazenogStudentaIspita;
	
	@FXML
	private ComboBox<Integer> ocjenaTrazenogIspita;
	
	@FXML
	private DatePicker datumTrazenogIspita;
	
	@FXML
	private TextField vrijemeTrazenogIspita;
	
	/**
	 * Predstavlja inicijalizaciju podataka prije filtriranja podataka
	 */
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		datumTrazenogIspita.setConverter(new StringConverter<LocalDate>() {
			 String pattern = "yyyy-MM-dd";
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
		
		Predmet sviPredmeti = new Predmet(null, null, null, null);
		
		List<Predmet> predmeti = null;
		try {
			predmeti = BazaPodataka.dohvatiPredmetePremaKriterijima(sviPredmeti);
		} catch (BazaPodatakaException e1) {
			e1.printStackTrace();
		}
		
		nazivTrazenogPredmetaIspita.getItems().addAll(predmeti);
		
		Student sviStudenti = new Student(null, null, null, null);
		
		List<Student> studenti = null;
		try {
			studenti = BazaPodataka.dohvatiStudentePremaKriterijima(sviStudenti);
		} catch (BazaPodatakaException e1) {
			e1.printStackTrace();
		}
		
		imeTrazenogStudentaIspita.getItems().addAll(studenti);
		
		ocjenaTrazenogIspita.getItems().addAll(1, 2, 3, 4, 5);
		
		Ispit sviIspiti = new Ispit(null, null, null, null);
		
		ObservableList<Ispit> listaIspita = null;
		try {
			listaIspita = FXCollections.observableArrayList(
					
					BazaPodataka.dohvatiIspitePremaKriterijima(sviIspiti)
					
					);
		} catch (BazaPodatakaException e) {
			e.printStackTrace();
		}	
		
		nazivPredmeta.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Ispit, String> ispit) {
				SimpleStringProperty property = new
				SimpleStringProperty();
				String ispit1 = new String(ispit.getValue().getPredmetX().getNaziv());
				property.setValue(ispit1);
				return property;
				}
				});
		imeStudentaIspita.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Ispit, String> student) {
				SimpleStringProperty property = new
				SimpleStringProperty();
				String student1 = new String(student.getValue().getStudentX().getIme() + " " + student.getValue().getStudentX().getPrezime());
				property.setValue(student1);
				return property;
				}
				});
		ocjenaStudentaIspita.setCellValueFactory(new PropertyValueFactory<Ispit, Integer>("Ocjena"));
		
		datumIVrijemeIspita.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Ispit, String> ispit) {
				SimpleStringProperty property = new
				SimpleStringProperty();
				DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("dd.MM.yyyy.' 'HH:mm");
				property.setValue(
				ispit.getValue().getDatumIVrijeme().format(formatter));
				return property;
				}
				});
		
		
		tablicaIspita.setItems(listaIspita);
	
	}
	
	/**
	 * Predstavlja metodu kojom filtriramo trazene podatke
	 */
	
	public void pretraziIspit(){
		List<Ispit> temp = new ArrayList<>();
		
		Ispit sviIspiti = new Ispit(null,null,null,null);
		
		List<Ispit> listaIspita = null;
		try {
			listaIspita = (
					
					BazaPodataka.dohvatiIspitePremaKriterijima(sviIspiti)
					
					);
		} catch (BazaPodatakaException e) {
			
			e.printStackTrace();
		}

		String datumIvrijemeString = null;
		LocalDateTime datumIvrijeme = null;
		if(datumTrazenogIspita.getValue() != null && vrijemeTrazenogIspita.getText().isBlank()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogrešan unos podataka");
			alert.setHeaderText("Molimo ako unosite datum ispita, upišite i vrijeme.");
			alert.showAndWait();
		}
		else if(datumTrazenogIspita.getValue() == null && !vrijemeTrazenogIspita.getText().isBlank()) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Pogrešan unos podataka");
			alert.setHeaderText("Molimo ako upišete vrijeme ispita, unesite i datum.");
			alert.showAndWait();
		}
		else {
		int brojac = 0;
		if(!(datumTrazenogIspita.getValue() == null)) {
		
		datumIvrijemeString = new String (datumTrazenogIspita.getValue() + " "+ vrijemeTrazenogIspita.getText());
		
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		datumIvrijeme = LocalDateTime.parse(datumIvrijemeString, formatter);
		}
		
		else {
			brojac = 1;
		}
		boolean nastaviDalje = true;
		
		if(nazivTrazenogPredmetaIspita.getSelectionModel().isEmpty() && imeTrazenogStudentaIspita.getSelectionModel().isEmpty() &&
				ocjenaTrazenogIspita.getSelectionModel().isEmpty() && datumTrazenogIspita.getValue() == null && vrijemeTrazenogIspita.getText().isBlank()) {
			temp = listaIspita;
			nastaviDalje = false;
		}
		
		if(nazivTrazenogPredmetaIspita.getSelectionModel().isEmpty() && imeTrazenogStudentaIspita.getSelectionModel().isEmpty() &&
				ocjenaTrazenogIspita.getSelectionModel().isEmpty() && datumTrazenogIspita.getValue() != null && !vrijemeTrazenogIspita.getText().isBlank()) {
				nastaviDalje = false;
				for(Ispit ispiti : listaIspita) {
					if(ispiti.getDatumIVrijeme().equals(datumIvrijeme)) {
						temp.add(ispiti);
				}
			}
		}
		if(nastaviDalje) {
		if(nazivTrazenogPredmetaIspita.getSelectionModel().isEmpty() && imeTrazenogStudentaIspita.getSelectionModel().isEmpty()) {
			nastaviDalje = false;
			for(Ispit ispiti : listaIspita) {
				if(ocjenaTrazenogIspita.getSelectionModel().getSelectedItem().equals(ispiti.getOcjena()) && (ispiti.getDatumIVrijeme().equals(datumIvrijeme) || brojac == 1)) {
					temp.add(ispiti);
					}
				}
			}
		}
		

		
		if(nastaviDalje) {
		if(!nazivTrazenogPredmetaIspita.getSelectionModel().isEmpty()) {
		for(Ispit ispiti : listaIspita) {
			if(ocjenaTrazenogIspita.getSelectionModel().isEmpty()) {	
				
				if(!imeTrazenogStudentaIspita.getSelectionModel().isEmpty()) {
					if(ispiti.getPredmetX().getId().equals(nazivTrazenogPredmetaIspita.getSelectionModel().getSelectedItem().getId()) &&
						(ispiti.getStudentX().getId().equals(imeTrazenogStudentaIspita.getSelectionModel().getSelectedItem().getId())) &&
							(ispiti.getDatumIVrijeme().equals(datumIvrijeme) || brojac == 1)){
						
					temp.add(ispiti);
				
				}
				}
				else {
					if(ispiti.getPredmetX().getId().equals(nazivTrazenogPredmetaIspita.getSelectionModel().getSelectedItem().getId()) &&
								(ispiti.getDatumIVrijeme().equals(datumIvrijeme) || brojac == 1)){
							
						temp.add(ispiti);
						
						}
				}
			}
			else {
				if(!imeTrazenogStudentaIspita.getSelectionModel().isEmpty()) {
					if(ispiti.getPredmetX().getId().equals(nazivTrazenogPredmetaIspita.getSelectionModel().getSelectedItem().getId()) &&
							(ispiti.getStudentX().getId().equals(imeTrazenogStudentaIspita.getSelectionModel().getSelectedItem().getId())) &&
										ispiti.getOcjena().equals(ocjenaTrazenogIspita.getSelectionModel().getSelectedItem())
									&& (ispiti.getDatumIVrijeme().equals(datumIvrijeme) || brojac == 1)
							) {
							
						temp.add(ispiti);
							}
						}
				else {
					if(ispiti.getPredmetX().getId().equals(nazivTrazenogPredmetaIspita.getSelectionModel().getSelectedItem().getId()) &&
										ispiti.getOcjena().equals(ocjenaTrazenogIspita.getSelectionModel().getSelectedItem())
									&& (ispiti.getDatumIVrijeme().equals(datumIvrijeme) || brojac == 1)
							) {
							
						temp.add(ispiti);
							}
				}
				}
			}
		}
		else {
			for(Ispit ispiti : listaIspita) {
				if(ocjenaTrazenogIspita.getSelectionModel().isEmpty()) {
					if(!imeTrazenogStudentaIspita.getSelectionModel().isEmpty()) {
						if((ispiti.getStudentX().getId().equals(imeTrazenogStudentaIspita.getSelectionModel().getSelectedItem().getId())) &&
								(ispiti.getDatumIVrijeme().equals(datumIvrijeme) || brojac == 1)){
							
						temp.add(ispiti);
						
						}
					}
					else {
						if(ispiti.getDatumIVrijeme().equals(datumIvrijeme) || brojac == 1){
							
						temp.add(ispiti);
						
						}
					}
					
				}
				else {
					if(!imeTrazenogStudentaIspita.getSelectionModel().isEmpty()) {
						if(ispiti.getStudentX().getId().equals(imeTrazenogStudentaIspita.getSelectionModel().getSelectedItem().getId()) &&
											ispiti.getOcjena().equals(ocjenaTrazenogIspita.getSelectionModel().getSelectedItem())
										&& (ispiti.getDatumIVrijeme().equals(datumIvrijeme) || brojac == 1)
								) {
								
							temp.add(ispiti);
							}
						}
					else {
						if(ispiti.getPredmetX().getId().equals(nazivTrazenogPredmetaIspita.getSelectionModel().getSelectedItem().getId()) &&
											ispiti.getOcjena().equals(ocjenaTrazenogIspita.getSelectionModel().getSelectedItem())
										&& (ispiti.getDatumIVrijeme().equals(datumIvrijeme) || brojac == 1)
								) {
								
							temp.add(ispiti);
							}
						}
					}
				}
			}
		
		}
		
		ObservableList<Ispit> listaIspita1 = FXCollections.observableArrayList(
				
				temp
				
				);
		
		nazivPredmeta.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Ispit, String> ispit) {
				SimpleStringProperty property = new
				SimpleStringProperty();
				String ispit1 = new String(ispit.getValue().getPredmetX().getNaziv());
				property.setValue(ispit1);
				return property;
				}
				});
		imeStudentaIspita.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Ispit, String> student) {
				SimpleStringProperty property = new
				SimpleStringProperty();
				String student1 = new String(student.getValue().getStudentX().getIme() + " " + student.getValue().getStudentX().getPrezime());
				property.setValue(student1);
				return property;
				}
				});
		ocjenaStudentaIspita.setCellValueFactory(new PropertyValueFactory<Ispit, Integer>("Ocjena"));
		
		datumIVrijemeIspita.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Ispit, String>,
				ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Ispit, String> ispit) {
				SimpleStringProperty property = new
				SimpleStringProperty();
				DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("dd.MM.yyyy.' 'HH:mm");
				property.setValue(
				ispit.getValue().getDatumIVrijeme().format(formatter));
				return property;
				}
				});
		
		
		tablicaIspita.setItems(listaIspita1);
		
		datumTrazenogIspita.setValue(null);
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
	public void prikaziPretraguStudenata() {
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
