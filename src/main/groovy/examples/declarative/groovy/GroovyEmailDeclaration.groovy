package examples.declarative.groovy


import org.springframework.jdbc.core.JdbcTemplate;
import examples.declarative.base.GenericDao
import examples.declarative.base.StringConverter
import examples.declarative.base.DateConverter
import examples.base.Home
import examples.base.SimpleEmail


public class GroovyEmailDaoDeclaration {
	public static Home createDao(JdbcTemplate jdbcTemplate) {
		return new GenericDao(jdbcTemplate, SimpleEmail.class, "EMAIL")
			.addProperty("fromAddress", "FROMEMAIL", new StringConverter())
			.addProperty("toAddress", "TOEMAIL", new StringConverter())
			.addProperty("subject", "SUBJECT", new StringConverter())
			.addProperty("message", "MESSAGE", new StringConverter())
			.addProperty("sent", "SENT", new DateConverter());
	}
}
