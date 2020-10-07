package hr.java.vjezbe.entitet;

import hr.java.vjezbe.iznimke.PostojiViseNajmladjihStudenataException;

/**
 * 	Predstavlja sucelje diplomski kojeg koriste fakulteti, a nasljeđuje sucelje Visokoskolska
 * 
 * 
 * @author tgtom
 *
 */
public interface Diplomski extends Visokoskolska{

	
	/**
	 *	Koristi se da bi se odredio student koji je osvojio Rektorovu Nagradu.
	 * 
	 * @author tgtom
	 * @return Vraca studenta koji je osvojio Rektorovu Nagradu
	 * @throws PostojiViseNajmladjihStudenataException Baca ovu iznimku ako su dvoje najuspjesnijih studenata rođeni na isti datum.
	 */
	public Student odrediStudentaZaRektorovuNagradu() throws PostojiViseNajmladjihStudenataException;
	
}
