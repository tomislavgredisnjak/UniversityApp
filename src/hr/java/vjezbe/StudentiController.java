package hr.java.vjezbe;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;

import hr.java.vjezbe.baza.BazaPodataka;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.util.Callback;
import javafx.util.StringConverter;
/**
 * Predstavlja Controller klasu za sucelje izbornika studenta
 * @author tgtom
 *
 */
public class StudentiController implements Initializable{

	
	@FXML
	private TableView<Student> tablicaStudenata;

	
	@FXML
	private TableColumn<Student, String> jmbag;
	@FXML
	private TableColumn<Student, String> ime;
	@FXML
	private TableColumn<Student, String> prezime;
	@FXML
	private TableColumn<Student, String> datumRodjenja;
	
	@FXML
	private TextField jmbagTrazenogStudenta;

	@FXML
	private TextField imeTrazenogStudenta;
	
	@FXML
	private TextField prezimeTrazenogStudenta;
	
	@FXML
	private DatePicker datumRodjenjaTrazenogStudenta;
	
	/**
	 * Predstavlja inicijalizaciju podataka prije filtriranja podataka
	 */
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		
		
		datumRodjenjaTrazenogStudenta.setConverter(new StringConverter<LocalDate>() {
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
		
		
		Student sviStudenti = new Student(null, null, null, null);
		
		ObservableList<Student> listaStudenata = null;
		try {
			listaStudenata = FXCollections.observableArrayList(
					
					BazaPodataka.dohvatiStudentePremaKriterijima(sviStudenti)
					
					);
		} catch (BazaPodatakaException e) {
			e.printStackTrace();
		}	

		jmbag.setCellValueFactory(new PropertyValueFactory<Student, String>("jmbag"));
		ime.setCellValueFactory(new PropertyValueFactory<Student, String>("Ime"));
		prezime.setCellValueFactory(new PropertyValueFactory<Student, String>("Prezime"));
		
		datumRodjenja.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Student, String>,
				ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Student, String> student) {
				SimpleStringProperty property = new
				SimpleStringProperty();
				DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				property.setValue(
				student.getValue().getDatumRodjenja().format(formatter));
				return property;
				}
				});
		
		
		tablicaStudenata.setItems(listaStudenata);
	
		
	}
	/**
	 * Predstavlja metodu kojom filtriramo trazene podatke
	 */
	public void pretraziStudenta(){
	
		Student trazeniStudenti = new Student(imeTrazenogStudenta.getText(),
				prezimeTrazenogStudenta.getText(), jmbagTrazenogStudenta.getText(), datumRodjenjaTrazenogStudenta.getValue());
		
		List<Student> listaStudenata = null;
		
		try {
			listaStudenata = BazaPodataka.dohvatiStudentePremaKriterijima(trazeniStudenti);
		} catch (BazaPodatakaException e) {
			e.printStackTrace();
		}
		
		ObservableList<Student> listaStudenata1 = null;
		
		if(listaStudenata != null) {
		listaStudenata1 = FXCollections.observableArrayList(
				
				listaStudenata
				
				);
		}
		jmbag.setCellValueFactory(new PropertyValueFactory<Student, String>("jmbag"));
		ime.setCellValueFactory(new PropertyValueFactory<Student, String>("Ime"));
		prezime.setCellValueFactory(new PropertyValueFactory<Student, String>("Prezime"));
		
		datumRodjenja.setCellValueFactory(
				new Callback<TableColumn.CellDataFeatures<Student, String>,
				ObservableValue<String>>() {
				@Override
				public ObservableValue<String> call(
				TableColumn.CellDataFeatures<Student, String> student) {
				SimpleStringProperty property = new
				SimpleStringProperty();
				DateTimeFormatter formatter =
				DateTimeFormatter.ofPattern("dd.MM.yyyy.");
				property.setValue(
				student.getValue().getDatumRodjenja().format(formatter));
				return property;
				}
				});
		
		
		tablicaStudenata.setItems(listaStudenata1);
		
		datumRodjenjaTrazenogStudenta.setValue(null);
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
}
