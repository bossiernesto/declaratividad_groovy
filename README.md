declaratividad_groovy
=====================

Descripción: Ejemplo de los diferentes niveles de declaratividad que se pueden obtener en groovy. El ejemplo 
en cuestión es un ORM, que permite persistir y traer de una BBDD entes de un modelo. El motor para realizar 
esto, se basa sobre una interfaz en común denominada Home


##Implementación de los Home

```
interface Home {

  def add(obj);

	def findAll();
}
```
