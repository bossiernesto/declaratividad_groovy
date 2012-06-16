package examples.declarative.base

import examples.base.Dao
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import java.lang.reflect.Constructor;
import java.sql.ResultSet
import java.sql.SQLException;


class GenericDao implements Dao {
	
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
		this.mappings.add(mapping);
		return this;
	}
	
	@Override
	def add(obj) {
		fieldNames="";
		questionMarks="";
		values = [];

		for (it in this.mappings) {
			
			fieldNames.join(mapping.getColumnName());
			questionMarks.join("?")
			values.add(mapping.getValue(obj));
			
			if (it!= this.mappings.last){
				fieldNames.join(", ");
				questionMarks.join(", ");
			}
		}

		String query = "INSERT INTO ${this.tableName} (${fieldNames}) VALUES (${questionMarks})";
		
		this.jdbcTemplate.update(query, values.toArray());
	}


	@SuppressWarnings("unchecked")
	def findAll() {
		return this.jdbcTemplate.query("select * from EMAIL;", new RowMapper() {
			public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
				try {
					Object result = mappedClass.newInstance();
					for (mapping in GenericDao.this.mappings) {
						mapping.setValue(result, rs);
					}
					return result;
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}

	/**
	 * Esto no sirve más pero lo dejo como otro ejemplo de reflection.
	 */
	@SuppressWarnings("unchecked")
	def oldFindAll() {
		return this.jdbcTemplate.query("select * from EMAIL;", new RowMapper() {
			def mapRow(ResultSet rs, int rowNum){
				try {
					List<Class> types = []
					List<Object> arguments = []
					for (mapping in GenericDao.this.mappings) {
						types.add(mapping.getPropertyType());
						arguments.add(mapping.getValueFromResultSet(rs));
					}

					constructor = mappedClass.getConstructor(types.toArray([] as Class[]));
					return constructor.newInstance(arguments.toArray());
				}
				catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		});
	}


}
