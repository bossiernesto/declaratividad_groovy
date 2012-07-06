package examples.utils

class StringUtils {

	
	static setterMethodsName(String propertyName,Class clazz){
		return this.findMethodsNameByAffix('set',propertyName,clazz)	
		}
	
	static getterMethodsName(String propertyName,Class clazz){
		return this.findMethodsNameByAffix('get',propertyName,clazz)
		}
	
	def findMethodsNameByAffix(String prefix,String propertyName,Class clazz){
		expr='/^'.join(prefix).join('[A-Z]/')
		methods=clazz.methods
		methods_selection= {it.methods.name.findAll{ it =~ expr }.collect{ it[3].toLowerCase()+it[4..-1] }.join(', ')}
		return methods_selection.find{it.name==prefix.join(propertyName)}.name
		}
	
	static String propertyName(String methodName) {
		return methodName.substring(3,4).toLowerCase().join(methodName.substring(4));
	}
	
}
