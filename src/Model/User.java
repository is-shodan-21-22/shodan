package Model;

import java.io.Serializable;

public class User implements Serializable {

	private static final long serialVersionUID = -3555522637968508649L;
	
	int id;
	int money;
	String name;
	String password;
	String email;
	boolean admin;
	
	public User(int id, String username, String password, String email, int money, boolean admin) {
		this.id = id;
		this.money = money;
		this.name = username;
		this.password = password;
		this.email = email;
		this.admin = admin;
	}
	
	public int getId() {
		return id;
	}
	
	public int getMoney() {
		return money;
	}
	
	public String getName() {
		return name;
	}
	
	public String getPassword() {
		return password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public boolean isAdmin() {
		return admin;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setMoney(int money) {
		this.money = money;
	}
	
	public void setUsername(String name) {
		this.name = name;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}

	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
