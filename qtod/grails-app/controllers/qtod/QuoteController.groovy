package qtod

class QuoteController {
	static defaultAction="index"
	static scaffold=Quote
	def quoteService
	def index() { }
	
	def random(){
		def randomQuote=quoteService.getRandomQuote()
		def user=User.metaClass.properties*.name.sort().unique()
		//def user=User.find("from User as u where u.quote=?", [randomQuote])
		[ quote: randomQuote, user: user]
	}
}
