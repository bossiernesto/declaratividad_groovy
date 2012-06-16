package examples.declarative.base

import java.sql.ResultSet;
import examples.utils.StringUtils
import examples.declarative.base.StringConverter
import examples.declarative.base.DateConverter
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
	
		abstract getValueFromResultSet(String columnName, ResultSet rs);
	
		// *************************************************************** //
	
		static def converters = [(String):new StringConverter(),(Date):new DateConverter()]
		
		static getConverterForType(type) {
			try
			{
				return converters[type]
			}
			catch (Exception e){
				throw new GroovyRuntimeException("Cannot create converter for ".join(type.name))
			}
			
			
		}
	
	
}
