package examples.declarative.annotations

import java.util.Date

import examples.base.Email
import examples.declarative.base.DateConverter
import examples.declarative.base.StringConverter

class AnnotatedEmail implements Email{

	@MappedProperty(column = "FROMEMAIL", converter = StringConverter.class)
	def getFromAddress() {
		return this.fromAddress
	}

	@MappedProperty(column = "TOEMAIL", converter = StringConverter.class)
	def getToAddress() {
		return this.toAddress
	}

	@MappedProperty(column = "SUBJECT", converter = StringConverter.class)
	def getSubject() {
		return this.subject
	}

	@MappedProperty(column = "MESSAGE", converter = StringConverter.class)
	def getMessage() {
		return this.message
	}

	@MappedProperty(column = "SENT", converter = DateConverter.class)
	def getSent() {
		return this.sent
	}

	def setMessage(String message) {
		this.message = message
	}

	def setFromAddress(String fromAddress) {
		this.fromAddress = fromAddress
	}

	def setToAddress(String toAddress) {
		this.toAddress = toAddress
	}

	def setSubject(String subject) {
		this.subject = subject
	}

	def setSent(Date sent) {
		this.sent = sent
	}

	
	
}
