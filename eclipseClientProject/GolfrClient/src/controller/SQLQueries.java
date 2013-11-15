package controller;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
	 * @return - the list of courses in the DB, ordered alphabetically, note: the list of courses has null for ArrayList<Hole> in each course 
	 */
	public static ArrayList<GolfCourse> getCourseListFromDB(Connection connection, String dbName) throws SQLException
	{
		
		ArrayList<GolfCourse> toReturn = new ArrayList<GolfCourse>();

		Statement statement = null;
		String query = "SELECT * FROM golfr.t_golfcoursedetails ORDER BY t_golfcoursedetails.name";
		
		try 
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) 
			{
				Integer ID = rs.getInt("courseID_PK"); 
				String name = rs.getString("courseName");
				String streetName = rs.getString("streetName");
				String streetNumber = rs.getString("streetNumber");
				Integer postalCode = rs.getInt("postalCode");
				String phone = rs.getString("phone");
				String webaddress = rs.getString("webAddress");
				GolfCourse toAdd = new GolfCourse(name,streetName,streetNumber,postalCode,phone,webaddress,null,ID);
				toReturn.add(toAdd);
			}
		} 
		catch (SQLException e ) 
		{
			e.printStackTrace();
			throw new IllegalStateException("Failed to populate course list from DB");
		}
		finally
		{
			if (statement != null)
			{ 
				statement.close(); 
			}
		}
		
		return toReturn;
	}
}
