package examples.base

class SimpleEmail implements Email {
	
	def message
	def fromAddress
	def toAddress
	def subject
	def sent
	
	SimpleEmail(String from, String to, String subject, String message, Date date) {
		this.fromAddress = from;
		this.toAddress = to;
		this.subject = subject;
		this.message = message;
		this.sent = date;
	}

	
	def setMessage(String message){
		this.message=message		
	}
	
	def getMessage(){
		return this.message
	}
	
	def setFromAddress(String address){
		this.fromAddress=address
	}
	
	def getFromAddress(){
		return this.fromAddress
	}
	
	def setToAddress(String address){
		this.toAddress=address
	}
	
	def getToAddress(){
		return this.toAddress
	}
	
	def setSubject(String subject){
		this.subject=subject
	}
	
	def getSubject(){
		return this.subject
	}
	
	def setSent(String sent){
		this.sent=sent
	}

	def getSent(){
		return this.sent
	}
}


