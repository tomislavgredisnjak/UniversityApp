package hr.java.vjezbe.entitet;

import java.io.Serializable;

/**
 * Predstavlja klasu Entitet koju nasljeduju sve ostale klase
 * @author tgtom
 *
 */

public class Entitet implements Serializable{
	
	/**
	 * Predstavlja generirani serial id
	 */
	private static final long serialVersionUID = 2002394707834502698L;
	/**
	 * Predstavlja id određenog entiteta
	 */
	private Long id;
	
	/**
	 * Konstruktor klase entitet
	 * @param id	Prestavlja id određenog entiteta
	 */
	public Entitet(Long id) {
		super();
		this.id = id;
	}
	/**
	 * Predstavlja konstoruktor bez primanja id-a
	 */
	public Entitet() {
		super();
		
	}
	/**
	 * Predstavlja getter funkciju za id
	 * @return	Vraca id određenog entiteta
	 */
	public Long getId() {
		return id;
	}
	/**
	 * Predstavlja setter funkciju za id
	 * @param id	Postavlja id određenog entiteta
	 */
	public void setId(Long id) {
		this.id = id;
	}
	
	
	
}
