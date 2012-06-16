package examples.declarative.base

import java.sql.ResultSet;
import java.sql.SQLException;

class StringConverter extends PropertyConverter {

	@Override
	def getPropertyType() {
		return String.class;
	}

	@Override
	def getValueFromResultSet(String columnName, ResultSet rs) {
		return rs.getString(columnName);
	}
}
