package party.app

class User {
	static hasMany= [ events: Event ]
	String name
	static constraints = {
	}
	
}
