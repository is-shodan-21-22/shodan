package Model;

public class Game {

	int id;
	int price;
	String title;
	String image;
	String description;
	String release;
	
	public Game(int id, int price, String title, String image, String description, String release) {
		this.id = id;
		this.price = price;
		this.title = title;
		this.image = image;
		this.description = description;
		this.release = release;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getRelease() {
		return release;
	}

	public void setRelease(String release) {
		this.release = release;
	}
	
}
