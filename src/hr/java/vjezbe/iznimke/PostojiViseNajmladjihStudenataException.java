package hr.java.vjezbe.iznimke;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * 
 *Predstavlja iznimku koja se baca ako postoji više studenata koji bi trebali osvojiti rektorovu nagradu, a rođeni su na
 *	isti datum. 
 *@author tgtom
 */

public class PostojiViseNajmladjihStudenataException extends Exception{
	public static final Logger log = LoggerFactory.getLogger(PostojiViseNajmladjihStudenataException.class);
	private static final long serialVersionUID = -296381963817458610L;

	/**
	 * Hvatanje iznimke bez parametra.
	 * @author tgtom
	 */
	public PostojiViseNajmladjihStudenataException() {
		super("Postoji više najmladjih studenata.");
		System.exit(0);
	}
	/**
	 * Hvatanje iznimke kada nam je poslana String poruka
	 * @param poruka Poruka koja opisuje problem.
	 */
	public PostojiViseNajmladjihStudenataException(String poruka) {
		super(poruka);
		log.info(poruka);
		System.out.println("Program završava s izvođenjem.");
		System.out.println(poruka); 
		System.exit(0);
		
	}
	/**
	 * Hvatanje iznimke kada nam je poslana String poruka i sama iznimka.
	 * @param poruka	Poruka koja opisuje problem.
	 * @param cause	Iznimka, razlog pogreske.
	 */
	public PostojiViseNajmladjihStudenataException(String poruka, Throwable cause) {
		super(poruka, cause);
		System.exit(0);
	}
	/**
	 * Hvatanje iznimke kada nam je poslana sama iznimka
	 * @param cause	Iznimka, razlog pogreske.
	 */
	public PostojiViseNajmladjihStudenataException(Throwable cause) {
		super(cause);
		System.exit(0);
	}
	
}
