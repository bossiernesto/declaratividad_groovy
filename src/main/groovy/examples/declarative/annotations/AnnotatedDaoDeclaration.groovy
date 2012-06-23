package examples.declarative.annotations

import java.lang.reflect.Method
import org.springframework.jdbc.core.JdbcTemplate

import examples.base.Home
import examples.declarative.base.GenericDao
import examples.declarative.base.PropertyConverter
import examples.utils.StringUtils

//TODO: revisar si se puede eliminar los .class
class AnnotatedDaoDeclaration {
	
	def jdbcTemplate
	
	def AnnotatedDaoDeclaration(JdbcTemplate jdbcTemplate, String mappedClassName){
		this.jdbcTemplate = jdbcTemplate
		this.mappedClass = Class.forName(mappedClassName)

	}
	
	def createDao(){
		tableName = this.getTableName()
		dao = new GenericDao(jdbcTemplate, mappedClass, tableName)

		for (method in this.mappedClass.getMethods()) {
			if (this.isProperty(method)) {
				dao.addProperty(StringUtils.propertyName(method.getName()), this.getColumnName(method), this
					.getConverter(method))
			}
		}
		return dao
	}
	
	def isProperty(Method method) {
		return method.isAnnotationPresent(MappedProperty.class)
	}

	def getColumnName(Method method) {
		return method.getAnnotation(MappedProperty.class).column()
	}

	def getConverter(Method method){
		return method.getAnnotation(MappedProperty.class).converter().newInstance()
	}
	
	/*Getters/Setters*/
	
	def getTableName() {
		return this.mappedClass.getAnnotation(MappedClass.class).table()
	}

	def getMappedClass() {
		return this.mappedClass
	}

}
