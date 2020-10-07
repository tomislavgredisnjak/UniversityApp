package hr.java.vjezbe.entitet;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
/**
 * Predstavlja klasu sveucilista koja ima listu obrazovnih ustanova
 * @author tgtom
 *
 * @param <T> T nasljeduje ObrazonaUstanova i nije definiranog tipa
 */
public class Sveuciliste<T extends ObrazovnaUstanova>{


	private List<T> listaObrazovnihUstanova;
	
	/**
	 * Konstruktor Sveuclilista u kojoj se listaObrazovnihUstanova kreira.
	 */
	public Sveuciliste() {
		super();
		this.listaObrazovnihUstanova = new ArrayList<>();
	}
	/**
	 * Dodaje obrazovne ustanove u listu
	 * @param obrazovnaUstanova Dodaje se u listu obrazovnih ustanova
	 */
	public void dodajObrazovnuUstanovu(T obrazovnaUstanova) {
		
		listaObrazovnihUstanova.add(obrazovnaUstanova);
		
	}
	/**
	 * Vraca potrebnu obrazovnu ustanovu
	 * @param Index indeks obrazovne ustanove koja treba biti dohvacena
	 * @return	Vraca obrazovnu ustanovu zeljenog indeksa
	 */
	public T dohvatiObrazovnuUstanovu(int index) {
		
		return listaObrazovnihUstanova.get(index);
		
	}
	/**
	 * Vraca listu svih obrazovnih ustanova
	 * @return	Lista svih obrazovnih ustanova koja se dohvaca
	 */
	public List<T> getListaObrazovnihUstanova(){
		return listaObrazovnihUstanova;
	}
	
}
