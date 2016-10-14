package co.edureka.controller;

public class Trainer {
	
	String name;
	int experience;
	String email;
	long phone;
	String address;
	
	
	public Trainer(String name, int experience, String email, long phone,
			String address) {
		super();
		this.name = name;
		this.experience = experience;
		this.email = email;
		this.phone = phone;
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getExperience() {
		return experience;
	}
	public void setExperience(int experience) {
		this.experience = experience;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

}
