package hr.java.vjezbe;
	
import hr.java.vjezbe.niti.DatumRodjenjaNit;
import hr.java.vjezbe.niti.NajboljiStudentNit;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import javafx.util.Duration;
/**
 * Predstavlja glavnu klasu koju pokrecemo kako bi pokrenuli aplikaciju
 * @author tgtom
 *
 */
public class Main extends Application {
	
	/**
	 * Staticka varijabla koju koristimo kasnije u setMainPage metodi
	 */
	private static Stage stage;
	
	/**
	 * Predstavlja metodu koja postavlja glavne karakteristike aplikacije
	 */
	
	@Override
	public void start(Stage primaryStage) {
		try {
			BorderPane root = (BorderPane)FXMLLoader.load(getClass().getResource("Izbornik.fxml"));
			Scene scene = new Scene(root,400,500);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			primaryStage.setScene(scene);
			primaryStage.show();
			stage = primaryStage;
			stage.setTitle("Najbolji student još nije određen!");
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		Timeline prikazSlavljenika = new Timeline(
				new KeyFrame(Duration.seconds(10), new EventHandler<ActionEvent>() {
				@Override
				public void handle(ActionEvent event) {
				Platform.runLater(new DatumRodjenjaNit());
				}
				}));
				prikazSlavljenika.setCycleCount(Timeline.INDEFINITE);
				prikazSlavljenika.play();
				
		Timeline prikazNajboljegStudenta = new Timeline(
			new KeyFrame(Duration.seconds(3), new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
			Platform.runLater(new NajboljiStudentNit(stage));
			}
			
			}));
			prikazNajboljegStudenta.setCycleCount(Timeline.INDEFINITE);
			prikazNajboljegStudenta.play();
			
	}
	/**
	 * Predstavlja main klasu koja ima metodu launch
	 * @param args
	 */
	public static void main(String[] args) {
		launch(args);
		
	}
	
	/**
	 * Predstavlja metodu koja postavlja okvir aplikacije
	 * @param root
	 */
	public static void setMainPage(BorderPane root) {
		Scene scene = new Scene(root,400,500);
		stage.setScene(scene);
		stage.show();
		}	
}
