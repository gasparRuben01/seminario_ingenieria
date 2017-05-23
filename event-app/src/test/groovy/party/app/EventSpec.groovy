package party.app

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Event)
class EventSpec extends Specification {

	def setup() {
	}

	def cleanup() {
	}

	void "test date and status of event"() {
		given: "an undated event" 
		def user=new User([name: "User"])
		def event=new Event()

		when: "its status is requested" 
		def status=event.status

		then: "it returns TBC"
		status==Event.Status.TBC

		when: "it's scheduled for tomorrow" 
		def calendar=Calendar.getInstance()
		calendar.add(Calendar.DATE, 1)
		def date=calendar.getTime()
		//if end_date is null, it is assumed that the event is eternal"
		event.schedule=new Tuple(date, null)

		then: "it returns TO_CELEBRATE"
		event.status==Event.Status.TO_CELEBRATE

		when: "it's scheduled for now and its status is requested"
		calendar=Calendar.getInstance()
		date=calendar.getTime()
		event.schedule=new Tuple(date, null)

		then: "it returns CELEBRATING"
		event.status==Event.Status.CELEBRATING

		when: "its end_date is over"
		calendar.add(Calendar.DATE, -1)
		def end_date=calendar.getTime()
		calendar.add(Calendar.DATE, -2);
		def start_date=calendar.getTime()
		event.schedule=new Tuple(start_date, end_date)
	
		then: "it returns OVER" 
		event.status==Event.Status.OVER

		when: "its start_date is later or equal than its end_date"
		start_date=calendar.getTime();
		calendar.add(Calendar.DATE, -1);
		end_date=calendar.getTime();	

		then: "it throws exception"
		shouldFail(IllegalArgumentException){
			event.schedule=new Tuple(start_date, end_date)
		}
	}
}
