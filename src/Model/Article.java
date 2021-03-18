package Model;

public class Article {

	int id;
	String title;
	String shortTitle;
	String html;
	
	public Article(int id, String title, String shortTitle, String html) {
		this.id = id;
		this.title = title;
		this.shortTitle = shortTitle;
		this.html = html;
	}
	
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getShortTitle() {
		return shortTitle;
	}
	
	public String getHtml() {
		return html;
	}
	
	public String toString() {
		return "Article [" + title + "][" + shortTitle + "][" + html + "]";
	}
	
}
