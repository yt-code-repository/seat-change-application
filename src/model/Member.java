package model;

import java.io.Serializable;

public class Member implements Serializable {
	private int id;
	private String name;
	private int seat_id;
	private boolean enrolled;
	private int gender;
	private int position_request;

	public Member() {}
	public Member(int id, String name, int seat_id, boolean enrolled, int gender, int position_request) {
		this.id = id;
		this.name = name;
		this.seat_id = seat_id;
		this.enrolled = enrolled;
		this.gender = gender;
		this.position_request = position_request;
	}

	public int getId() { return id; }
	public void setId(int id) { this.id = id; }
	public String getName() { return name; }
	public void setName(String name) { this.name = name; }
	public int getSeat_id() { return seat_id; }
	public void setSeat_id(int seat_id) { this.seat_id = seat_id; }

	public boolean isEnrolled() {
		return enrolled;
	}
	public void setEnrolled(boolean enrolled) {
		this.enrolled = enrolled;
	}
	public int getGender() {
		return gender;
	}
	public void setGender(int gender) {
		this.gender = gender;
	}
	public int getPosition_request() {
		return position_request;
	}
	public void setPosition_request(int position_request) {
		this.position_request = position_request;
	}
}
