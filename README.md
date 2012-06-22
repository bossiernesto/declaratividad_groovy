declaratividad_groovy
=====================

Descripción: Ejemplo de los diferentes niveles de declaratividad que se pueden obtener en groovy. El ejemplo 
en cuestión es un ORM, que permite persistir y traer de una BBDD entes de un modelo.

'''
interface Dao {

  def add(obj);

	def findAll();
}
'''
