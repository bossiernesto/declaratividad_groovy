package test.base

import junit.framework.TestSuite

class DeclaratividadTestSuite extends TestSuite{

	static TEST_ROOT="src/test/groovy/test/base"	
	
	static suite(){
		def suite=new TestSuite()
		def gsuite= new GroovyTestSuite()
		suite.addTestSuite(gsuite.compile(TEST_ROOT.join("DeclaratividadTestSuite.groovy")))
		return suite
	}
	
	
}
