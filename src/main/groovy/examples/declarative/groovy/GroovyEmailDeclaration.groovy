package main.groovy.examples.declarative.groovy


import org.springframework.jdbc.core.JdbcTemplate;



public class GroovyEmailDaoDeclaration {
	public static Dao<SimpleEmail> createDao(JdbcTemplate jdbcTemplate) {
		return new GenericDao<SimpleEmail>(jdbcTemplate, SimpleEmail.class, "EMAIL")//
			.addProperty("fromAddress", "FROMEMAIL", new StringConverter())
			.addProperty("toAddress", "TOEMAIL", new StringConverter())
			.addProperty("subject", "SUBJECT", new StringConverter())
			.addProperty("message", "MESSAGE", new StringConverter())
			.addProperty("sent", "SENT", new DateConverter());
	}
}
