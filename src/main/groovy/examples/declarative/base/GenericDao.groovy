package examples.declarative.base

import examples.base.Home
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.jdbc.core.RowMapper
import java.lang.reflect.Constructor
import java.sql.ResultSet
import java.sql.SQLException


class GenericDao implements Home {
	
	def mappedClass
	def tableName
	def mappings = []

	public GenericDao(JdbcTemplate jdbcTemplate, mappedClass, String tableName) {
		this.jdbcTemplate = jdbcTemplate
		this.mappedClass = mappedClass
		this.tableName = tableName
	}

	def addProperty(String propertyName, String columnName, PropertyConverter converter) {
		return this.addProperty(new PropertyMapping(propertyName, columnName, converter))
	}

	def addProperty(mapping) {
		this.mappings.add(mapping)
		return this
	}
	
	@Override
	def add(obj) {
		fieldNames=""
		questionMarks=""
		values = []

		this.mappings.each{
			
			fieldNames.join(it.getColumnName())
			questionMarks.join("?")
			values.add(it.getValue(obj))
			
			if (it!= this.mappings.last){
				fieldNames.join(", ")
				questionMarks.join(", ")
			}
		}

		String query = "INSERT INTO ${this.tableName} (${fieldNames}) VALUES (${questionMarks})"
		
		this.jdbcTemplate.update(query, values.toArray())
	}


	def findAll() {
		return this.jdbcTemplate.query("select * from ${this.tableName}", new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				try {
					Object result = mappedClass.newInstance()
					GenericDao.this.mappings.each{ it.setValue(result, rs) }
					return result
				}
				catch (Exception e) {
					throw new RuntimeException(e)
				}
			}
		})
	}

}
