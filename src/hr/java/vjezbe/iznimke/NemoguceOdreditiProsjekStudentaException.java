package hr.java.vjezbe.iznimke;
/**Predstavlja iznimku koja se baca ako je jedan od ispita ocjenjen sa nedovoljan. Nasljeđuje Exception.
 * 
 * @author tgtom
 *	
 */
public class NemoguceOdreditiProsjekStudentaException extends Exception{

	
	private static final long serialVersionUID = 267880090245711736L;
	/**
	 * Hvatanje iznimke bez parametra.
	 * @author tgtom
	 */
	public NemoguceOdreditiProsjekStudentaException() {
		super("Nije moguće odrediti prosjek studentu.");
	}
	/**
	 * Hvatanje iznimke kada nam je poslana String poruka
	 * @param poruka Poruka koja opisuje problem.
	 */
	public NemoguceOdreditiProsjekStudentaException(String poruka) {
		
		super(poruka);
		
	}
	/**
	 * Hvatanje iznimke kada nam je poslana String poruka i sama iznimka.
	 * @param poruka	Poruka koja opisuje problem.
	 * @param cause	Iznimka, razlog pogreske.
	 */
	public NemoguceOdreditiProsjekStudentaException(String poruka, Throwable cause) {
		super(poruka, cause);
	}
	/**
	 * Hvatanje iznimke kada nam je poslana sama iznimka
	 * @param cause	Iznimka, razlog pogreske.
	 */
	public NemoguceOdreditiProsjekStudentaException(Throwable cause) {
		super(cause);
	}
	
}
