package co.edureka.controller;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Courses {

	@NotNull
	@Size(min=1, max=100)
	String name;

	@NotNull
	@Size(min=1, max=10)
	@Pattern(regexp = "^[a-zA-Z0-9]+$",
			message = "ID must be alphanumeric with no spaces")
	String id;


	@NotNull
	@Min(0)
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
