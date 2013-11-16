package controller;

import golfCourseObjects.GolfCourse;
import golfCourseObjects.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This class serves as the controller for use case 4, selecting a course
 * @author Matt Gisoni
 *
 */
public class SelectACourse 
{
	private ArrayList<GolfCourse> courseList;
	private User user;

	/**
	 * Default constructor.  Instantiates and empty course list
	 */
	public SelectACourse() 
	{
		this.courseList = new ArrayList<GolfCourse>();
		this.user = null;
	
	}

	/**
	 * Typical constructor.  Populates a course list from DB.
	 * @param user - the user who is selecting a course to play
	 */
	public SelectACourse(User user) 
	{
		this.courseList = new ArrayList<GolfCourse>();
		this.user = user;
		//this.populateCourseListFromDB(user.getConnection(),user.getDbName());
	}

	/**
	 * Populates the course list based on the corresponding table in the DB
	 * 
	 */
	public void populateCourseListFromDB(Connection connection, String DB_Name)
	{
		try 
		{
			this.courseList = SQLQueries.getCourseListFromDB(connection, DB_Name);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
			throw new IllegalStateException("Unable to populate course list from DB");
		}
	}

	
	/**
	 * Starts a new PlayGame using the selected course
	 * @param selected - the course desired by the player
	 * @return - a new PlayGame which the user will play
	 */
	public PlayGame selectCourse(GolfCourse selected)
	{
		if (selected != null && selected.validityCheck())
			return new PlayGame(user,selected);
		else
			throw new IllegalArgumentException("The selected golf course is invalid");
	}

	/**
	 * @return the courseList
	 */
	public ArrayList<GolfCourse> getCourseList() {
		return courseList;
	}

	/**
	 * @param courseList the courseList to set
	 */
	public void setCourseList(ArrayList<GolfCourse> courseList) {
		this.courseList = courseList;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

}
