package controller;

import java.util.ArrayList;

import golfCourseObjects.GolfCourse;

public class SQLQueries 
{

	/**
	 * Sends a new golf course to the MySQL DB via JDBC UPDATE query	
	 * @param toAdd - the course to be added
	 */
	public static void sendCourseToDB(GolfCourse toAdd)
	{
		//TODO: implement this method
	}

	/**
	 * Gets the list of courses from the MySQL DB via JDBC SELECT query
	 * @return - the list of courses in the DB, ordered alphabetically 
	 */
	public static ArrayList<GolfCourse> getCourseListFromDB() 
	{
		//TODO: implement this method
		ArrayList<GolfCourse> toReturn = new ArrayList<GolfCourse>();
		
		/*pseudocode
		for (each record returned by SELECT query)
		{
			GolfCourse toAdd = new GolfCourse(paramaters from DB query);
			toReturn.add(toAdd);
		}
		*/
		return toReturn;
	}
}
