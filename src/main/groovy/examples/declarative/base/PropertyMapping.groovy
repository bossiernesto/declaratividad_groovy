package examples.declarative.base

import java.sql.ResultSet
import java.sql.SQLException
import examples.utils.StringUtils


class PropertyMapping {
	
	def String propertyName
	def String columnName
	def PropertyConverter converter

	def PropertyMapping() {
	}

	def PropertyMapping(String propertyName, String columnName, PropertyConverter converter) {
		this.propertyName = propertyName
		this.columnName = columnName
		this.converter = converter
	}

	def getValueFromResultSet(ResultSet rs) {
		try {
			return this.converter.getValueFromResultSet(this.columnName, rs)
		}
		catch (SQLException e) {
			throw new GroovyRuntimeException(e)
		}
	}

	def getValue(GroovyObject obj) {
		return this.converter.getValue(this.propertyName, obj)
	}

	def setValue(GroovyObject obj, ResultSet rs) throws Exception {
		propertyValue = this.converter.getValueFromResultSet(columnName, rs)

		setterMethodName = StringUtils.setterMethodName(this.propertyName,obj.class)
		
		obj.invokeMehod(setterMethodName, propertyValue)
	}

	def getPropertyType() {
		return this.converter.getPropertyType()
	}

	def setPropertyType(type) {
		this.converter = PropertyConverter.getConverterForType(type)
	}

}
