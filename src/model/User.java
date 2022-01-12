package model;

import java.io.Serializable;

public class User implements Serializable{

	private int user_id;
	private String login_id;
	private String pass;
	private String name;

	public User() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	public User(int user_id,String login_id,String pass,String name) {
		this.user_id = user_id;
		this.login_id = login_id;
		this.pass = pass;
		this.name = name;
	}

	public User(String login_id,String pass) {
		this.login_id = login_id;
		this.pass = pass;
	}

	public int getUser_id() {
		return user_id;
	}

	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}

	public String getLogin_id() {
		return login_id;
	}

	public void setLogin_id(String login_id) {
		this.login_id = login_id;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
