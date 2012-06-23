package test.base

import java.util.Date

import org.springframework.context.support.ClassPathXmlApplicationContext
import org.springframework.jdbc.core.JdbcTemplate
import groovy.util.GroovyTestCase
import examples.base.Home
import examples.base.Email
import examples.base.SimpleEmail

class persistanceTest extends GroovyTestCase {
	
	def emailHome
	def context
	def vendedorChinoEmail = "vendedorChino@simpsons.com"
	def homeroEmail = "doh@simpsons.com"
	def subject = "Regalo a Bart"
	def jdbcTemplate

	void setUp() {
		this.context = new ClassPathXmlApplicationContext("appContext.xml")
		this.jdbcTemplate = (JdbcTemplate) this.context.getBean("jdbcTemplate")
		this.emailDao = (Home) this.context.getBean("emailDao")
		this.initializeDB()
	}

	/*Initialization methods*/
	
	void initializeDB() {
		jdbcTemplate.execute(//
			"create table EMAIL (FROMEMAIL varchar(255), TOEMAIL varchar(255), SUBJECT varchar(255), MESSAGE varchar(255), SENT timestamp)")
		this.insert(vendedorChinoEmail, homeroEmail, subject,"tome este objeto, pelo cuilado, tiene una olible maldicion")
		this.insert(homeroEmail, vendedorChinoEmail, subject, "uuuuy que mal!")
		this.insert(vendedorChinoEmail, homeroEmail, subject, "y le doy congul glatis")
		this.insert(homeroEmail, vendedorChinoEmail, subject, "que bien!")
		this.insert(vendedorChinoEmail, homeroEmail, subject, "el congul tambien esta maldito")
		this.insert(homeroEmail, vendedorChinoEmail, subject, "que mal!")
		this.insert(vendedorChinoEmail, homeroEmail, subject, "pelo usted esoje la cubielta")
		this.insert(homeroEmail, vendedorChinoEmail, subject, "que bien!")
		this.insert(vendedorChinoEmail, homeroEmail, subject, "la cubielta tiene benzoato de potasio")
		this.insert(homeroEmail, vendedorChinoEmail, subject, ":-|")
		this.insert(vendedorChinoEmail, homeroEmail, subject, "que mal...")
		this.insert(homeroEmail, vendedorChinoEmail, subject, "ya puedo irme?")
	}

	void insert(String from, String to, String subject, String message) {
		this.jdbcTemplate.update(
			"INSERT INTO EMAIL (FROMEMAIL, TOEMAIL, SUBJECT, MESSAGE, SENT) VALUES (?, ?, ?, ?, ?)", [ 
				from, to, subject, message, new java.sql.Date(new Date().getTime())] as Object[])
	}

	void tearDown(){
		this.jdbcTemplate.execute("drop table EMAIL")
	}
	
	/*Tests*/
	
	void testSize(){
		assertEquals(12, this.emailDao.findAll().size())
	}

	void testInsert(){
		Email email = new SimpleEmail(homeroEmail, vendedorChinoEmail, "nuevo mail", "", new Date())
		this.emailDao.add(email)
		assertEquals(13, this.emailDao.findAll().size())
	}

	void testAllMessagesSameThread() throws Exception {
		for (email in this.emailDao.findAll()) {
			if (!email.getSubject().equals(this.subject)) {
				failNotEquals("There is a message from a different thread.", this.subject, email.getSubject())
			}
		}
	}
		
}
