package examples.instrospective

import java.lang.reflect.Method

import org.springframework.jdbc.core.JdbcTemplate

import examples.declarative.annotations.AnnotatedDaoDeclaration

class IntrospectiveDaoBuilder extends AnnotatedDaoDeclaration {

	public IntrospectiveDaoBuilder(JdbcTemplate jdbcTemplate, String mappedClassName){
		super(jdbcTemplate, mappedClassName)
	}

	@Override
	def getTableName() {
		return this.getMappedClass().getSimpleName().toUpperCase()
	}

	@Override
	def isProperty(Method method) {
		return method.getName().startsWith("get") && method.getName() != "getClass"
	}

	@Override
	def getColumnName(Method method) {
		return method.isAnnotationPresent(Column.class) ? method.getAnnotation(Column.class).value()
			: StringUtils.propertyName(method.getName()).toUpperCase()
	}

	@Override
	def getConverter(Method method){
		return PropertyConverter.getConverterForType(method.getReturnType())
	}
}
