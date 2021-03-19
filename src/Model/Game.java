package Model;

import java.sql.Date;

public class Game {

	public int id;
	public int price;
	public String name;
	public String description;
	public String image;
	public Date release;
	
	public Game(int id, int price, String name, String description, String image, Date release) {
		this.id = id;
		this.price = price;
		this.name = name;
		this.description = description;
		this.image = image;
		this.release = release;
	}
	
	public int getId() {
		return id;
	}
	
	public int getPrice() {
		return price;
	}
	
	public String getName() {
		return name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public String getImage() {
		return image;
	}
	
	public Date getRelease() {
		return release;
	}
	
}
