package co.edureka.controller;

public class Course {
	public String name;
	public String start_date;
	public String end_date;
	public int sessions;
	Course(String name,String start_date,String end_date,int sessions){
		this.name=name;
		this.start_date=start_date;
		this.end_date=end_date;
		this.sessions=sessions;
	}
	
	
	
	public String toString(){
		return name+start_date+end_date+sessions;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStart_date() {
		return start_date;
	}
	public void setStart_date(String start_date) {
		this.start_date = start_date;
	}
	public String getEnd_date() {
		return end_date;
	}
	public void setEnd_date(String end_date) {
		this.end_date = end_date;
	}
	public int getSessions() {
		return sessions;
	}
	public void setSessions(int sessions) {
		this.sessions = sessions;
	}

}
