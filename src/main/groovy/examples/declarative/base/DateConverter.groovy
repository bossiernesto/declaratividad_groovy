package examples.declarative.base

import java.sql.ResultSet
import java.sql.SQLException
import java.util.Date

class DateConverter extends PropertyConverter {
	
	@Override
	def getPropertyType() {
		return Date.class;
	}

	@Override
	def getValueFromResultSet(String columnName, ResultSet rs) {
		return rs.getDate(columnName);
	}
}
	

