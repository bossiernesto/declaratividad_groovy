package main.groovy.examples.declarative.groovy


import org.springframework.jdbc.core.JdbcTemplate;
import main.groovy.examples.declarative.base.GenericDao
import main.groovy.examples.declarative.base.StringConverter
import main.groovy.examples.declarative.base.DateConverter
import main.groovy.examples.base.Dao
import main.groovy.examples.base.SimpleEmail


public class GroovyEmailDaoDeclaration {
	public static Dao createDao(JdbcTemplate jdbcTemplate) {
		return new GenericDao(jdbcTemplate, SimpleEmail.class, "EMAIL")
			.addProperty("fromAddress", "FROMEMAIL", new StringConverter())
			.addProperty("toAddress", "TOEMAIL", new StringConverter())
			.addProperty("subject", "SUBJECT", new StringConverter())
			.addProperty("message", "MESSAGE", new StringConverter())
			.addProperty("sent", "SENT", new DateConverter());
	}
}
