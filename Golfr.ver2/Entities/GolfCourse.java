package Entities;

import java.util.ArrayList;

public class GolfCourse {
	
	public String streetName;
	public String streetNumber;
	public String postalCode;
	public String phoneNumber;
	public ArrayList<Hole> holes;
	
	public GolfCourse(String streetName,String streetNumber,String postalCode,String phoneNumber,ArrayList<Hole> holes)
	{
		this.streetNumber = streetNumber;
		this.streetName = streetName;
		this.postalCode = postalCode;
		this.phoneNumber = phoneNumber;
		this.holes = holes;
	}
}
