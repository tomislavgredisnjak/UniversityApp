package hr.java.vjezbe.iznimke;

public class BazaPodatakaException extends Exception{

	private static final long serialVersionUID = 6765386089692576620L;
	

	/**
	 * Hvatanje iznimke bez parametra.
	 * @author tgtom
	 */
	public BazaPodatakaException() {
		super("Došlo je do pogreške u radu s bazom podataka");
	}
	/**
	 * Hvatanje iznimke kada nam je poslana String poruka
	 * @param poruka Poruka koja opisuje problem.
	 */
	public BazaPodatakaException(String poruka) {
		
		super(poruka);
		
	}
	/**
	 * Hvatanje iznimke kada nam je poslana String poruka i sama iznimka.
	 * @param poruka	Poruka koja opisuje problem.
	 * @param cause	Iznimka, razlog pogreske.
	 */
	public BazaPodatakaException(String poruka, Throwable cause) {
		super(poruka, cause);
	}
	/**
	 * Hvatanje iznimke kada nam je poslana sama iznimka
	 * @param cause	Iznimka, razlog pogreske.
	 */
	public BazaPodatakaException(Throwable cause) {
		super(cause);
	}
	
}
