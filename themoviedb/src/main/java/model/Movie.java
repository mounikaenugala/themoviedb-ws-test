package model;

/**
 * Class for Movie object. It contains id, title and many other fields which are
 * ignored for testing purpose.
 * 
 * Reference Doc: https://developers.themoviedb.org/3/getting-started
 * 
 * @author mounikaenugala
 * 
 *
 */
public class Movie {
	private int id;
	private String title;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
