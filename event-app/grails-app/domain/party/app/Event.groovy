package party.app

class Event {

	public enum Status{
		TBC,
		TO_CELEBRATE,
		CELEBRATING,
		OVER
	}
	static belongsTo=User
	static hasMany= [ users:User ]
	static constraints = {
	
	}

	Tuple schedule=new Tuple(null, null)
    Status status

	void setSchedule(Tuple schedule){
		if (schedule[0] && schedule[1] && schedule[0]>=schedule[1]){
			throw new IllegalArgumentException("end_date must be later than start_date")
		}
		this.schedule=schedule
	}

	Status getStatus(){
		def calendar=Calendar.getInstance()
		def now=calendar.getTime()

		if (schedule[0] && schedule[0]>now){
			return Status.TO_CELEBRATE
		}
		if (schedule[1] && schedule[1]<=now){
			return Status.OVER
		}
		if (schedule[0]){
			return Status.CELEBRATING
		}
		return Status.TBC
	}
}
