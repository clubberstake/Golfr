package golfCourseObjects;

import java.util.ArrayList;

public class GolfCourse 
{

	private String streetName;
	private String streetNumber;
	private String postalCode;
	private String phoneNumber;
	private String email;
	private ArrayList<Hole> holes;

	/**
	 * Default constructor 
	 */
	public GolfCourse() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	public GolfCourse(String streetNameIn, String streetNumberIn,
			String postalCodeIn, String phoneNumberIn, String emailIn,
			ArrayList<Hole> holesIn) {
		this.streetName = streetNameIn;
		this.streetNumber = streetNumberIn;
		this.postalCode = postalCodeIn;
		this.phoneNumber = phoneNumberIn;
		this.email = emailIn;
		this.holes = holesIn;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return this.streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return this.streetNumber;
	}

	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	/**
	 * @return the postalCode
	 */
	public String getPostalCode() {
		return this.postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the holes
	 */
	public ArrayList<Hole> getHoles() {
		return this.holes;
	}

	/**
	 * @param holes the holes to set
	 */
	public void setHoles(ArrayList<Hole> holes) {
		this.holes = holes;
	}
}
