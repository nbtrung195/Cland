package model.bean;

import java.sql.Date;

public class user {
	
	private int id;
	private String name;
	private String pass;
	private Date birth;
	private String email;
	private role Role;
	
	public user() {
		super();
		// TODO Auto-generated constructor stub
	}

	public user(int id, String name, String pass, Date birth, String email, role role) {
		super();
		this.id = id;
		this.name = name;
		this.pass = pass;
		this.birth = birth;
		this.email = email;
		Role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Date getBirth() {
		return birth;
	}

	public void setBirth(Date birth) {
		this.birth = birth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public role getRole() {
		return Role;
	}

	public void setRole(role role) {
		Role = role;
	}
	
	
}
