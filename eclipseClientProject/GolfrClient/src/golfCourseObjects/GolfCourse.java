package golfCourseObjects;

import java.util.ArrayList;

public class GolfCourse {

	public String streetName;
	public String streetNumber;
	public String postalCode;
	public String phoneNumber;
	public String email;
	public ArrayList<Hole> holes;
	
	public GolfCourse(String streetNameIn, String streetNumberIn, String postalCodeIn, String phoneNumberIn, String emailIn, ArrayList<Hole> holesIn)
	{
		this.streetName = streetNameIn;
		this.streetNumber = streetNumberIn;
		this.postalCode = postalCodeIn;
		this.phoneNumber = phoneNumberIn;
		this.email = emailIn;
		this.holes = holesIn;
	}
}
