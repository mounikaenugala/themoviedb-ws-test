package model;

/**
 * Class for People object. It contains name, gender, place of birth and many
 * other fields which are ignored for testing purpose.
 * 
 * Reference Doc: https://developers.themoviedb.org/3/getting-started
 * 
 * @author mounikaenugala
 * 
 *
 */
public class People {
	private String name;
	private int gender;
	private String place_of_birth;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public String getPlace_of_birth() {
		return place_of_birth;
	}

	public void setPlace_of_birth(String place_of_birth) {
		this.place_of_birth = place_of_birth;
	}
}
