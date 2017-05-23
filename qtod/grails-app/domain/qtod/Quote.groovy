package qtod

class Quote {
	String content
	String author
	Date created=new Date()
	static hasMany=[users: User]

	static constraints = {
		author nullable: true, blank: true 
		content maxSize:1000, blank:false
	}
	
}
