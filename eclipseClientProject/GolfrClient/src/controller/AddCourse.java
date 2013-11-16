package controller;

import java.util.ArrayList;

import golfCourseObjects.GolfCourse;
import golfCourseObjects.Hole;

/**
 * This class acts as the controller for use case 3 "Adding a course"
 * 
 * @author Matt Gisoni
 *
 */
public class AddCourse 
{
	private GolfCourse toAdd;

	/**
	 * Default Constructor creates an "empty" course
	 */
	public AddCourse() 
	{
		this.toAdd = new GolfCourse();
	}

	/**
	 * Full constructor.  A wrapper for new GolfCourse(String streetNameIn, String streetNumberIn,
			String postalCodeIn, String phoneNumberIn, String emailIn,
			ArrayList<Hole> holesIn, Integer golfCourseID) 
	 * @param streetNameIn
	 * @param streetNumberIn
	 * @param postalCodeIn
	 * @param phoneNumberIn
	 * @param emailIn
	 * @param holesIn
	 * @param golfCourseID
	 */
	public AddCourse(String courseName, String streetNameIn, String streetNumberIn,
			String postalCodeIn, String phoneNumberIn, String webAddressIn,
			ArrayList<Hole> holesIn, Integer golfCourseID)
	{
		this.toAdd = new GolfCourse(courseName,streetNameIn,streetNumberIn,postalCodeIn,phoneNumberIn,webAddressIn,holesIn,golfCourseID);
	}

	/**
	 * @return the toAdd
	 */
	public GolfCourse getToAdd() 
	{
		return toAdd;
	}

	/**
	 * @param toAdd the toAdd to set
	 */
	public void setToAdd(GolfCourse toAdd) 
	{
		this.toAdd = toAdd;
	}
	
	/**
	 * Sends the added course to the MySQL DB
	 */
	public void sendToDB()
	{
		if(this.toAdd.validityCheck())
		{
			SQLQueries.sendCourseToDB(this.toAdd);
		}
		else
			throw new IllegalStateException("The new golf course is not configured correctly.  Not sent to DB");
	}

}
