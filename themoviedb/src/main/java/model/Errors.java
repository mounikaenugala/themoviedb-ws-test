package model;

/**
 * Class for Errors object. It contains list of error messages. 
 * 
 * Reference Doc: https://developers.themoviedb.org/3/getting-started
 * 
 * @author mounikaenugala
 * 
 *
 */
public class Errors {
	private String[] errors;

	public String[] getErrors() {
		return errors;
	}

	public void setErrors(String[] errors) {
		this.errors = errors;
	}
}
