package examples.imperative

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import examples.base.*;

public class ImperativeEmailDao implements Dao {
	public JdbcTemplate jdbcTemplate;

	def ImperativeEmailDao(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	def add(email) {
		this.jdbcTemplate.update(//
			"INSERT INTO EMAIL (FROMEMAIL, TOEMAIL, SUBJECT, MESSAGE, SENT) VALUES (?, ?, ?, ?, ?)", //
			 [email.getFromAddress(), //
				email.getToAddress(), //
				email.getSubject(), //
				email.getMessage(), //
				new java.sql.Date(email.getSent().getTime()) ] as Object[]);
	}

	def findAll() {
		return this.jdbcTemplate.query("select * from EMAIL;", new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				return new SimpleEmail(//
					rs.getString("FROMEMAIL"), //
					rs.getString("TOEMAIL"), //
					rs.getString("SUBJECT"), //
					rs.getString("MESSAGE"),//
					rs.getDate("SENT"));
			}
		});
	}
}