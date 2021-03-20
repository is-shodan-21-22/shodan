package Model;

public class User {

	int id;
	int money;
	String name;
	String password;
	String email;
	String avatar;
	boolean admin;
	
	public User(int id, int money, String username, String password, String email, String avatar, boolean admin) {
		this.id = id;
		this.money = money;
		this.name = username;
		this.password = password;
		this.email = email;
		this.avatar = avatar;
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
	
	public String getAvatar() {
		return avatar;
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
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public void setAdmin(boolean admin) {
		this.admin = admin;
	}
	
}
