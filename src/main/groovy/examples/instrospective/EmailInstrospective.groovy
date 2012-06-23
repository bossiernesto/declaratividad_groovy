package examples.instrospective

import java.util.Date

import examples.base.Email
import examples.instrospective.Column

class EmailInstrospective implements Email{
	
	def getMessage() {
		return message
	}

	@Column("FROMEMAIL")
	def getFromAddress() {
		return fromAddress
	}

	@Column("TOEMAIL")
	def getToAddress() {
		return toAddress
	}

	def getSubject() {
		return subject
	}

	def getSent() {
		return sent
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
