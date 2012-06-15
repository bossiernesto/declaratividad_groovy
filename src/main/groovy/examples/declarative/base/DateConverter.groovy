package main.groovy.examples.declarative.base

import java.sql.ResultSet
import java.sql.SQLException
import java.util.Date

class DateConverter extends PropertyConverter {
	
	@Override
	def getPropertyType() {
		return Date.class;
	}

	@Override
	def getValueFromResultSet(String columnName, ResultSet rs) throws SQLException {
		return rs.getDate(columnName);
	}
}
	

