package controller;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Properties;

import android.os.AsyncTask;

import golfCourseObjects.GolfCourse;
import golfCourseObjects.Hole;
import golfCourseObjects.Score;
import golfCourseObjects.User;

public class SQLQueries extends Thread
{

	private Connection connection;

	public SQLQueries()
	{
		super();
	}


	/**
	 * Sends a new golf course to the MySQL DB via JDBC UPDATE query	
	 * @param toAdd - the course to be added
	 */
	public void sendCourseToDB(GolfCourse toAdd)
	{
		//TODO: implement this method
	}

	public ArrayList<Hole> getHoleListWithScore(User user, GolfCourse course)
	{
		ArrayList<Hole> toReturn = new ArrayList<Hole>(18);
		Statement statement = null;
		String query = "SELECT * " +
				"FROM t_holes " +
				"WHERE golfCourseID = " + course.getGolfCourseID() +
				" AND holeNumber IS NOT NULL";

		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) 
			{

				Integer holeID = rs.getInt("holeID");
				Integer courseID = rs.getInt("golfCourseID");
				Integer whiteTee = rs.getInt("whiteTee");
				Integer redTee = rs.getInt("redTee");
				Integer blueTee = rs.getInt("blueTee");
				Integer handicap = rs.getInt("handicap");
				Integer par = rs.getInt("par");
				Integer holeNumber = rs.getInt("holeNumber");							
				Hole toAdd = new Hole(holeID, courseID,holeNumber,whiteTee,redTee,blueTee,handicap,par,0);				
				toReturn.add(toAdd);

			}	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Could not get holes for the provided course.");

		}		
		finally
		{
			if (statement != null)
			{ 
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}

		return toReturn;

	}

	public ArrayList<Hole> getHoleListNoScore(GolfCourse course)
	{
		ArrayList<Hole> toReturn = new ArrayList<Hole>(18);
		Statement statement = null;
		String query = "SELECT * " +
				"FROM t_holes " +
				"WHERE golfCourseID = " + course.getGolfCourseID() +
				" AND holeNumber IS NOT NULL";

		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) 
			{

				Integer holeID = rs.getInt("holeID");
				Integer courseID = rs.getInt("golfCourseID");
				Integer whiteTee = rs.getInt("whiteTee");
				Integer redTee = rs.getInt("redTee");
				Integer blueTee = rs.getInt("blueTee");
				Integer handicap = rs.getInt("handicap");
				Integer par = rs.getInt("par");
				Integer holeNumber = rs.getInt("holeNumber");							
				Hole toAdd = new Hole(holeID, courseID,holeNumber,whiteTee,redTee,blueTee,handicap,par,0);				
				toReturn.add(toAdd);

			}	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Could not get holes for the provided course.");

		}		
		finally
		{
			if (statement != null)
			{ 
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}

		return toReturn;

	}

	public ArrayList<Score> getTenMostRecentScores(GolfCourse course)
	{
		ArrayList<Score> toReturn = new ArrayList<Score>(10);

		Statement statement = null;

		//this query is known to be incomplete
		//TODO: figure out how to select "current hole" for each user playing the course
		String query = "SELECT DISTINCT t_golfcoursehistory.`date` AS `date`, " +
				"t_user.facebookID AS `facebookID`, " +
				"t_holes.holeNumber AS `holeNumber`," +
				"t_scorehistory.totalScore AS `totalScore` " +
				"FROM t_golfcoursehistory,t_user,t_holes,t_scorehistory,t_scorecard " +
				"WHERE " +
				"t_user.userID_pk = t_golfcoursehistory.userID AND " +
				"t_golfcoursehistory.scoreHistory = t_scorehistory.scoreHistory_pk AND " +
				"t_scorecard.holeID = t_holes.holeID AND " +
				"t_golfcoursehistory.courseID = "+ course.getGolfCourseID() +" AND " +
				"(t_scorecard.strokes = 0 OR holeNumber = 18) " +
				"ORDER BY t_golfcoursehistory.`date` DESC";		

		try
		{
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			int count = 0;

			while (rs.next() && count < 10) 
			{
				Date date = rs.getDate("date");
				String userName = rs.getString("facebookID");
				Integer holeNumber = rs.getInt("holeNumber");
				Integer totalScore = rs.getInt("totalScore");
				Score toAdd = new Score(date, userName, holeNumber, totalScore);				
				toReturn.add(toAdd);
				count++;
			}	
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Could not get 10 most recent scores for the selected course");

		}		
		finally
		{
			if (statement != null)
			{ 
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		}

		return toReturn;
	}

	/**
	 * Gets the list of courses from the MySQL DB via JDBC SELECT query
	 * @return - the list of courses in the DB, ordered alphabetically, note: the list of courses has null for ArrayList<Hole> in each course 
	 */
	public ArrayList<GolfCourse> getCourseListFromDB() throws SQLException
	{

		ArrayList<GolfCourse> toReturn = new ArrayList<GolfCourse>();

		Statement statement = null;
		String query = "SELECT * FROM golfr.t_golfcoursedetails ORDER BY t_golfcoursedetails.courseName";

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
				String postalCode = rs.getString("postalCode");
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


	/**
	 * @return the connection
	 */
	public Connection getConnection() {
		return connection;
	}


	/**
	 * @param connection the connection to set
	 */
	public void setConnection(Connection connection) {
		this.connection = connection;
	}

	@Override
	public void run()
	{
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:mysql://127.0.0.1:3306/golfr";
		String userName = "client";
		String password = "12345";
		try 
		{
			this.connection = DriverManager.getConnection(url,userName,password);			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}

	/**
	 * Closes the DB connection and the this.Thread
	 */
	public void close() 
	{
		try {
			this.connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		finally
		{
			this.interrupt();
		}

	}




}