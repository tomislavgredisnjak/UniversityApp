package hr.java.vjezbe.baza;


import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import hr.java.vjezbe.entitet.Ispit;
import hr.java.vjezbe.entitet.Predmet;
import hr.java.vjezbe.entitet.Profesor;
import hr.java.vjezbe.entitet.Student;
import hr.java.vjezbe.iznimke.BazaPodatakaException;
/**
 * Predstavlja klasu koja radi sve metode vezane s bazom podataka
 * @author tgtom
 *
 */
public class BazaPodataka {
	private static final String DATABASE_FILE = "database.properties";
	public static final Logger logger = LoggerFactory.getLogger(BazaPodataka.class);
	
	/**
	 * Predstavlja metodu spajanja na bazu podataka
	 * @return vraca objekt tipa Connection
	 * @throws SQLException baca ovaj exception ako je došlo do ikakve pogreške u procesu spajanja na bazu podataka
	 * @throws IOException bacen exception
	 */
	private static Connection spajanjeNaBazu() throws SQLException, IOException{
	
		Properties svojstva = new Properties();
		
		svojstva.load(new FileReader(DATABASE_FILE));
		
		String urlBazePodataka = svojstva.getProperty("bazaPodatakaUrl");
		String korisnickoIme = svojstva.getProperty("korisnickoIme");
		String lozinka = svojstva.getProperty("lozinka");
		
		Connection veza = DriverManager.getConnection(urlBazePodataka, korisnickoIme, lozinka);
		
		return veza;
		
	}
	
	public static List<Student> dohvatiSlavljenike() throws BazaPodatakaException{
		
		List<Student> slavljenici = new ArrayList<>();
		
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza.prepareStatement("SELECT * FROM STUDENT WHERE DAY(DATUM_RODJENJA) = DAY(?) AND MONTH(DATUM_RODJENJA) = MONTH(?)");
			LocalDate danas = LocalDate.now();
			preparedStatement.setDate(1, Date.valueOf(danas));
			preparedStatement.setDate(2, Date.valueOf(danas));
			
			
			ResultSet resultSet = preparedStatement.executeQuery();
			
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String jmbag = resultSet.getString("jmbag");
				String ime = resultSet.getString("ime");
				String prezime = resultSet.getString("prezime");
				LocalDate datumRodjenja = resultSet.getTimestamp("datum_rodjenja").toInstant().atZone(
						ZoneId.systemDefault()).toLocalDate();
				Student noviStudent = new Student(id, ime, prezime, jmbag, datumRodjenja);
				slavljenici.add(noviStudent);
				}
		}
		catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
		}

		return slavljenici;
	}
	
	/**
	 * Predstavlja metodu kojom se dohvaca lista profesora iz baze podataka zadanim kriterijima
	 * @param profesor prima objekt tipa Profesor
	 * @return vraca listu profesora koji zadovoljavaju kriterije
	 * @throws BazaPodatakaException baca ovaj exception ako je doslo do pogreske u radu s bazom
	 */
	public static List<Profesor> dohvatiProfesorePremaKriterijima(Profesor profesor) throws
	BazaPodatakaException {
		List<Profesor> listaProfesora = new ArrayList<>();
				try (Connection veza = spajanjeNaBazu()) {
		StringBuilder sqlUpit = new StringBuilder("SELECT * FROM PROFESOR WHERE 1 = 1");
			if (Optional.ofNullable(profesor).isEmpty() == false) {
				if (Optional.ofNullable(profesor).map(Profesor::getId).isPresent()) {
					sqlUpit.append(" AND ID = " + profesor.getId());
					}
				if (Optional.ofNullable(profesor.getSifra()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND SIFRA LIKE '%" + profesor.getSifra() + "%'");
					}
				if (Optional.ofNullable(profesor.getIme()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND IME LIKE '%" + profesor.getIme() + "%'");
				}
				if (Optional.ofNullable(profesor.getPrezime()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND PREZIME LIKE '%" + profesor.getPrezime() + "%'");
				}
				if (Optional.ofNullable(profesor.getTitula()).map(String::isBlank).orElse(true) == false) {
					sqlUpit.append(" AND TITULA LIKE '%" + profesor.getTitula() + "%'");
				}
			}
			
			Statement upit = veza.createStatement();
			ResultSet resultSet = upit.executeQuery(sqlUpit.toString());
			
			while (resultSet.next()) {
				Long id = resultSet.getLong("id");
				String sifra = resultSet.getString("sifra");
				String ime = resultSet.getString("ime");
				String prezime = resultSet.getString("prezime");
				String titula = resultSet.getString("titula");
				Profesor noviProfesor = new Profesor(id, sifra, ime, prezime,
				titula);
				listaProfesora.add(noviProfesor);
				}
			}
				catch (SQLException | IOException ex) {
					String poruka = "Došlo je do pogreške u radu s bazom podataka";
					logger.error(poruka, ex);
					throw new BazaPodatakaException(poruka, ex);
				}
				return listaProfesora;
		}
	/**
	 * Predstavlja metodu kojom se sprema novi profesor u bazu podataka.
	 * @param ispit Prima objekt tipa profesor
	 * @throws BazaPodatakaException baca ovaj exception ako je doslo do pogreske u radu s bazom
	 */
		public static void spremiNovogProfesora(Profesor profesor) throws BazaPodatakaException {
		try (Connection veza = spajanjeNaBazu()) {
			PreparedStatement preparedStatement = veza
			.prepareStatement(
			"INSERT INTO PROFESOR(ime, prezime, sifra, titula) VALUES (?, ?, ?, ?)");
			preparedStatement.setString(1, profesor.getIme());
			preparedStatement.setString(2, profesor.getPrezime());
			preparedStatement.setString(3, profesor.getSifra());
			preparedStatement.setString(4, profesor.getTitula());
			preparedStatement.executeUpdate();
		} catch (SQLException | IOException ex) {
			String poruka = "Došlo je do pogreške u radu s bazom podataka";
			logger.error(poruka, ex);
			throw new BazaPodatakaException(poruka, ex);
			}
		}
		/**
		 * Predstavlja metodu kojom se dohvaca lista studenata iz baze podataka zadanim kriterijima
		 * @param student prima objekt tipa Student
		 * @return vraca listu studenata koji zadovoljavaju kriterije
		 * @throws BazaPodatakaException baca ovaj exception ako je doslo do pogreske u radu s bazom
		 */
		public static List<Student> dohvatiStudentePremaKriterijima(Student student) throws
		BazaPodatakaException {
			List<Student> listaStudenata = new ArrayList<>();
					try (Connection veza = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM STUDENT WHERE 1 = 1");
				if (Optional.ofNullable(student).isEmpty() == false) {
					if (Optional.ofNullable(student).map(Student::getId).isPresent()) {
						sqlUpit.append(" AND ID = " + student.getId());
						}
					if (Optional.ofNullable(student.getJmbag()).map(String::isBlank).orElse(true) == false) {
						sqlUpit.append(" AND JMBAG LIKE '%" + student.getJmbag() + "%'");
						}
					if (Optional.ofNullable(student.getIme()).map(String::isBlank).orElse(true) == false) {
						sqlUpit.append(" AND IME LIKE '%" + student.getIme() + "%'");
					}
					if (Optional.ofNullable(student.getPrezime()).map(String::isBlank).orElse(true) == false) {
						sqlUpit.append(" AND PREZIME LIKE '%" + student.getPrezime() + "%'");
					}
					if (Optional.ofNullable(student.getDatumRodjenja()).isPresent()) {
						sqlUpit.append(" AND DATUM_RODJENJA = '"
						+ student.getDatumRodjenja().format(
						DateTimeFormatter.ISO_DATE) + "'");
						}
				}
				
				Statement upit = veza.createStatement();
				ResultSet resultSet = upit.executeQuery(sqlUpit.toString());
				
				while (resultSet.next()) {
					Long id = resultSet.getLong("id");
					String jmbag = resultSet.getString("jmbag");
					String ime = resultSet.getString("ime");
					String prezime = resultSet.getString("prezime");
					LocalDate datumRodjenja = resultSet.getTimestamp("datum_rodjenja").toInstant().atZone(
							ZoneId.systemDefault()).toLocalDate();
					Student noviStudent = new Student(id, ime, prezime, jmbag, datumRodjenja);
					listaStudenata.add(noviStudent);
					}
				}
					catch (SQLException | IOException ex) {
						String poruka = "Došlo je do pogreške u radu s bazom podataka";
						logger.error(poruka, ex);
						throw new BazaPodatakaException(poruka, ex);
					}
					return listaStudenata;
			}
		/**
		 * Predstavlja metodu kojom se sprema novi student u bazu podataka.
		 * @param ispit Prima objekt tipa Student
		 * @throws BazaPodatakaException baca ovaj exception ako je doslo do pogreske u radu s bazom
		 */
		public static void spremiNovogStudenta(Student student) throws BazaPodatakaException {
			try (Connection veza = spajanjeNaBazu()) {
				PreparedStatement preparedStatement = veza
				.prepareStatement(
				"INSERT INTO STUDENT(ime, prezime, jmbag, datum_rodjenja) VALUES (?, ?, ?, ?)");
				preparedStatement.setString(1, student.getIme());
				preparedStatement.setString(2, student.getPrezime());
				preparedStatement.setString(3, student.getJmbag());
				preparedStatement.setDate(4, Date.valueOf(student.getDatumRodjenja()));
				preparedStatement.executeUpdate();
			} catch (SQLException | IOException ex) {
				String poruka = "Došlo je do pogreške u radu s bazom podataka";
				logger.error(poruka, ex);
				throw new BazaPodatakaException(poruka, ex);
				}
			}
		/**
		 * Predstavlja metodu kojom se dohvaca lista predmeta iz baze podataka zadanim kriterijima
		 * @param predmet prima objekt tipa Predmet
		 * @return vraca listu predmeta koji zadovoljavaju kriterije
		 * @throws BazaPodatakaException baca ovaj exception ako je doslo do pogreske u radu s bazom
		 */
		public static List<Predmet> dohvatiPredmetePremaKriterijima(Predmet predmet) throws
		BazaPodatakaException {
			
			List<Predmet> listaPredmeta = new ArrayList<>();
					try (Connection veza = spajanjeNaBazu()) {
			StringBuilder sqlUpit = new StringBuilder("SELECT * FROM PREDMET WHERE 1 = 1");
				if (Optional.ofNullable(predmet).isEmpty() == false) {
					if (Optional.ofNullable(predmet).map(Predmet::getId).isPresent()) {
						sqlUpit.append(" AND ID = " + predmet.getId());
						}
					if (Optional.ofNullable(predmet.getSifra()).map(String::isBlank).orElse(true) == false) {
						sqlUpit.append(" AND SIFRA LIKE '%" + predmet.getSifra() + "%'");
						}
					if (Optional.ofNullable(predmet.getNaziv()).map(String::isBlank).orElse(true) == false) {
						sqlUpit.append(" AND NAZIV LIKE '%" + predmet.getNaziv() + "%'");
					}
					if (Optional.ofNullable(predmet).map(Predmet::getBrojEctsBodova).isPresent()) {
						sqlUpit.append(" AND BROJ_ECTS_BODOVA LIKE '%" + predmet.getBrojEctsBodova() + "%'");
					}

					if (Optional.ofNullable(predmet).map(Predmet::getNositelj).isPresent()) {
						sqlUpit.append(" AND PROFESOR_ID LIKE '%" + predmet.getNositelj().getId() + "%'");
					
					}
				}
				
				Statement upit = veza.createStatement();
				ResultSet resultSet = upit.executeQuery(sqlUpit.toString());
				
				while (resultSet.next()) {
					
					Long id = resultSet.getLong("id");
					String sifra = resultSet.getString("sifra");
					String naziv = resultSet.getString("naziv");
					Integer brojEctsBodova = resultSet.getInt("broj_ects_bodova");
					Long nositeljId = resultSet.getLong("profesor_id");
					
					Profesor nositelj1 = new Profesor(nositeljId, null, null, null, null);
					List<Profesor> prof = dohvatiProfesorePremaKriterijima(nositelj1);
					
					Predmet noviPredmet = new Predmet(id, sifra, naziv, brojEctsBodova,
					prof.get(0));
					
	
					listaPredmeta.add(noviPredmet);
				}
				
					
					}
				
					catch (SQLException | IOException ex) {
						String poruka = "Došlo je do pogreške u radu s bazom podataka";
						logger.error(poruka, ex);
						throw new BazaPodatakaException(poruka, ex);
					}
					return listaPredmeta;
			}
			
		/**
		 * Predstavlja metodu kojom se sprema novi predmet u bazu podataka.
		 * @param predmet Prima objekt tipa Predmet
		 * @throws BazaPodatakaException baca ovaj exception ako je doslo do pogreske u radu s bazom
		 */
		
			public static void spremiNoviPredmet(Predmet predmet) throws BazaPodatakaException {
			try (Connection veza = spajanjeNaBazu()) {
				PreparedStatement preparedStatement = veza
				.prepareStatement(
				"INSERT INTO PREDMET(sifra, naziv, broj_ects_bodova, profesor_id) VALUES (?, ?, ?, ?)");
				preparedStatement.setString(1, predmet.getSifra());
				preparedStatement.setString(2, predmet.getNaziv());
				preparedStatement.setInt(3, predmet.getBrojEctsBodova());
				preparedStatement.setLong(4, predmet.getNositelj().getId());
				preparedStatement.executeUpdate();
			} catch (SQLException | IOException ex) {
				String poruka = "Došlo je do pogreške u radu s bazom podataka";
				logger.error(poruka, ex);
				throw new BazaPodatakaException(poruka, ex);
				}
			}
			/**
			 * Predstavlja metodu kojom se dohvaca lista ispita iz baze podataka zadanim kriterijima
			 * @param ispit prima objekt tipa Ispit
			 * @return vraca listu ispita koji zadovoljavaju kriterije
			 * @throws BazaPodatakaException baca ovaj exception ako je doslo do pogreske u radu s bazom
			 */
			public static List<Ispit> dohvatiIspitePremaKriterijima(Ispit ispit) throws
			BazaPodatakaException {
				
				List<Ispit> listaIspita = new ArrayList<>();
						try (Connection veza = spajanjeNaBazu()) {
				StringBuilder sqlUpit = new StringBuilder("SELECT * FROM ISPIT WHERE 1 = 1");
					if (Optional.ofNullable(ispit).isEmpty() == false) {
						if (Optional.ofNullable(ispit).map(Ispit::getId).isPresent()) {
							sqlUpit.append(" AND ID = " + ispit.getId());
							}
						if (Optional.ofNullable(ispit).map(Ispit::getPredmetX).isPresent()) {
							sqlUpit.append(" AND PREDMET_ID LIKE '%" + ispit.getPredmetX().getId() + "%'");
							}
						if (Optional.ofNullable(ispit).map(Ispit::getStudentX).isPresent()) {
							sqlUpit.append(" AND STUDENT_ID LIKE '%" + ispit.getStudentX().getId() + "%'");
						}
						if (Optional.ofNullable(ispit).map(Ispit::getOcjena).isPresent()) {
							sqlUpit.append(" AND OCJENA LIKE '%" + ispit.getOcjena() + "%'");
						}

						if (Optional.ofNullable(ispit.getDatumIVrijeme()).isPresent()) {
							DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SS");
							sqlUpit.append(" AND datum_i_vrijeme = '" + ispit.getDatumIVrijeme().format(formatter) + "'");
							}
					}
					
					Statement upit = veza.createStatement();
					ResultSet resultSet = upit.executeQuery(sqlUpit.toString());
					
					while (resultSet.next()) {
						
						Long id = resultSet.getLong("id");
						Long predmetId = resultSet.getLong("predmet_id");
						Long studentId= resultSet.getLong("student_id");
						Integer ocjena = resultSet.getInt("ocjena");
						LocalDateTime datumIVrijemeIspita = resultSet.getTimestamp("datum_i_vrijeme").toLocalDateTime();
						
						Predmet predmet1 = new Predmet(predmetId, null, null, null, null);
						List<Predmet> pred = dohvatiPredmetePremaKriterijima(predmet1);
						
						Student student1 = new Student(studentId, null, null, null, null);
						List<Student> stud = dohvatiStudentePremaKriterijima(student1);
						
						
						
						Ispit noviIspit = new Ispit(id, pred.get(0), stud.get(0), ocjena, datumIVrijemeIspita);
						
		
						listaIspita.add(noviIspit);
					}
					
						}
					
						catch (SQLException | IOException ex) {
							String poruka = "Došlo je do pogreške u radu s bazom podataka";
							logger.error(poruka, ex);
							throw new BazaPodatakaException(poruka, ex);
						}
						return listaIspita;
				}
			/**
			 * Predstavlja metodu kojom se sprema novi ispit u bazu podataka.
			 * @param ispit Prima objekt tipa ispit
			 * @throws BazaPodatakaException baca ovaj exception ako je doslo do pogreske u radu s bazom
			 */
				public static void spremiNoviIspit(Ispit ispit) throws BazaPodatakaException {
				try (Connection veza = spajanjeNaBazu()) {
					PreparedStatement preparedStatement = veza
					.prepareStatement(
					"INSERT INTO ISPIT(predmet_id, student_id, ocjena, datum_i_vrijeme) VALUES (?, ?, ?, ?)");
					preparedStatement.setLong(1, ispit.getPredmetX().getId());
					preparedStatement.setLong(2, ispit.getStudentX().getId());
					preparedStatement.setInt(3, ispit.getOcjena());
					preparedStatement.setTimestamp(4, Timestamp.valueOf(ispit.getDatumIVrijeme()));
					preparedStatement.executeUpdate();
				} catch (SQLException | IOException ex) {
					String poruka = "Došlo je do pogreške u radu s bazom podataka";
					logger.error(poruka, ex);
					throw new BazaPodatakaException(poruka, ex);
					}
				}
			
}
