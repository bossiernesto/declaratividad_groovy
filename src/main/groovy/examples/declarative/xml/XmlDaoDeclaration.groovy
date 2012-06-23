package examples.declarative.xml

import java.io.IOException
import org.apache.commons.digester.Digester
import org.springframework.jdbc.core.JdbcTemplate
import org.xml.sax.SAXException
import examples.declarative.base.GenericDao

class XmlDaoDeclaration {

	def createDao(JdbcTemplate jdbcTemplate, String fileName) throws IOException, SAXException {
		Digester digester = new Digester()
		digester.addObjectCreate("mapping", GenericDao.class)
		digester.addSetProperties("mapping")
		digester.addObjectCreate("mapping/property", PropertyMapping.class)
		digester.addSetProperties("mapping/property")
		digester.addSetNext("mapping/property", "addProperty")

		dao = (GenericDao) digester.parse(XmlDaoDeclaration.class.getResourceAsStream(fileName))
		dao.setJdbcTemplate(jdbcTemplate)
		return dao
	}
	
}
