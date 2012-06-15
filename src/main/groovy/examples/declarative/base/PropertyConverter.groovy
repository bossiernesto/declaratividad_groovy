package main.groovy.examples.declarative.base

import java.sql.ResultSet;
import main.groovy.examples.utils.StringUtils
import main.groovy.examples.declarative.base.StringConverter
import main.groovy.examples.declarative.base.DateConverter
import java.sql.SQLException;

abstract class PropertyConverter {

		def getValue(String propertyName, GroovyObject obj) {
			try {
				mappedClass = obj.class;
				methodName = StringUtils.getterMethodName(mappedclass, propertyName)
				return obj.invokeMethod(methodName,"")
			}
			catch (Exception e) {
				throw new GroovyRuntimeException(e);
			}
		}

		abstract getPropertyType();
	
		abstract getValueFromResultSet(String columnName, ResultSet rs) throws SQLException;
	
		// *************************************************************** //
	
		static getConverterForType(Class<?> type) {
			if (type.equals(String.class)) {
				return new StringConverter()
			}
			else if (type.equals(Date.class)) {
				return new DateConverter()
			}
			else {
				throw new GroovyRuntimeException("Cannot create converter for ".join(type.name))
			}
		}
	
	
}
