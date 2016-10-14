package co.edureka.controller;

public class Courses {
	String name;
	String id;
	int price;
	int sessions;

	public String getName() {
		return name;
	}

	public Courses() {

	}

	public Courses(String name, String id, int price, int sessions) {
		super();
		this.name = name;
		this.id = id;
		this.price = price;
		this.sessions = sessions;
	}

	public Courses(String name, String id, int price) {
		super();
		this.name = name;
		this.id = id;
		this.price = price;

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getSessions() {
		return sessions;
	}

	public void setSessions(int sessions) {
		this.sessions = sessions;
	}
}
