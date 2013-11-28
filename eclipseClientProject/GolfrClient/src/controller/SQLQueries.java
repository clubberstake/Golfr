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
			if (connection == null || connection.isClosed())
				this.connect();

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
			rs.close();
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

	/**
	 * Returns an ArrayList of Holes which contains the 18 holes of the provided GolfCourse
	 * @param course
	 * @return
	 */
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
			if (connection == null || connection.isClosed())
				this.connect();

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
			rs.close();
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
			if (connection == null || connection.isClosed())
				this.connect();
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
			rs.close();
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
	 * Returns the primary key from the t_golfcoursedetails table if the provided course parameters are an exact match.
	 * @param course
	 * @return if found, the primary key.  Null otherwise.
	 * @throws SQLException 
	 */
	public Integer getCoursePrimaryKey(GolfCourse course) throws SQLException
	{
		Integer key = null;
		Statement statement = null;
		String query = "SELECT courseID_PK FROM t_golfcoursedetails WHERE courseName = '" + course.getCourseName() + "' AND " +
				"phone = '" + course.getPhoneNumber() + "' AND " +
				"webAddress = '" + course.getWebaddress() + "'";


		try 
		{
			if (connection == null || connection.isClosed())
				this.connect();
			statement = connection.createStatement();			
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) 
			{
				Integer ID = rs.getInt("courseID_PK");							
				key = ID;
			}
			rs.close();
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
		return key;
	}

	/**
	 * Deletes the golf course with the provided primary key.  This is meant to be used primarily for junit testing purposes.
	 * @param primaryKey - the t_golfcoursedetails.courseID_PK of the course to be removed
	 * @throws SQLException
	 */
	public void deleteCourseFromDB(Integer primaryKey) throws SQLException
	{

		Statement statement1 = null;
		Statement statement2 = null;
		Statement statement3 = null;
		String deleteCourseQuery = "DELETE FROM t_golfCourseDetails WHERE courseID_PK = " + primaryKey;		
		String selectHolesQuery = "SELECT * " +
				"FROM t_holes " +
				"WHERE golfCourseID = " + primaryKey +
				" AND holeNumber IS NOT NULL";		
		String deleteHoleQuery;

		try
		{
			if (connection == null || connection.isClosed())
				this.connect();
			//first, find all the holes for the course, and delete them one by one
			statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery(selectHolesQuery);

			while (rs.next()) 
			{

				Integer holeID = rs.getInt("holeID");
							

				//delete the holes
				deleteHoleQuery = "DELETE FROM t_holes WHERE holeID = " + holeID.toString();
				statement3 = connection.createStatement();
				statement3.executeUpdate(deleteHoleQuery);
				if (statement3 != null)
					statement3.close();

			}	

			//then delete the coursedetails
			statement2 = connection.createStatement();
			Integer success = statement2.executeUpdate(deleteCourseQuery);
			System.out.println(deleteCourseQuery);
			if (success != 1)
				throw new IllegalStateException("Failed to delete the course to be deleted!");
			rs.close();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Could not get holes for the provided course.");

		}		
		finally
		{
			
			if (statement1 != null || statement2 != null)
			{ 
				try {
					statement1.close();
					statement2.close();					
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
			 
		}
	}

	/**
	 * Adds a course to the DB table t_golfcoursedetails.  It is meant for use case 3.  Note, it does not add the 18 holes of the new course to the DB, that is done by another method. 
	 * @param newCourse
	 * @throws SQLException 
	 */
	public void sendCourseDetailsToDB(GolfCourse newCourse) throws SQLException
	{
		String query = "INSERT INTO t_golfcoursedetails (courseName, streetName, streetNumber, postalCode, phone, webAddress) VALUES ('" + 
				newCourse.getCourseName() + "', '" +
				newCourse.getStreetName() + "', '" +
				newCourse.getStreetNumber() + "', '" +
				newCourse.getPostalCode() + "', '" +
				newCourse.getPhoneNumber() + "', '" +
				newCourse.getWebaddress() + "');";

		Statement statement = null;

		try
		{			
			if (connection == null || connection.isClosed())
				this.connect();
			statement = this.connection.createStatement();
			int success = statement.executeUpdate(query);
			if (success != 1)
				throw new IllegalStateException();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			
			if (statement != null)
				statement.close();
			 
		}

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
			if (connection == null || connection.isClosed())
				this.connect();
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
			rs.close();
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

	
	/**
	 * Runs the SQLQueries.Thread.  Establishes a connection to the Golfr DB and waits
	 */
	@Override
	public synchronized void run()
	{

		try 
		{
			this.connect();
			this.wait();
		} 
		catch (InterruptedException e) 
		{		
			if (this.connection != null)
			{
				try
				{
					this.connection.close();
				} 
				catch (SQLException e1) 
				{				
					e1.printStackTrace();
				}

			}

			this.interrupt();
		} 

		//if This.notify() is called
		if (this.connection != null)
		{
			try {
				this.connection.close();
			} catch (SQLException e) {
				
				e.printStackTrace();
			}	
			
		}
	}

	/**
	 * Closes the DB connection and the this.Thread
	 */
	public void close() 
	{
		try 
		{
			if (this.connection != null)
				this.connection.close();
		} 
		catch (SQLException e) 
		{		
			e.printStackTrace();
		} 
		finally
		{
			this.interrupt();
		}

	}


	/**
	 * Private helper method to establish or re-establish a connection to the DB.
	 */
	private void connect()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} 
		catch (InstantiationException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String url = "jdbc:mysql://127.0.0.1:3306/golfr";
		String userName = "client";
		String password = "12345";

		try 
		{
			//if the connection is closed try to open it
			if (this.connection == null || this.connection.isClosed())
				this.connection = DriverManager.getConnection(url,userName,password);
			//if the connection is still closed, throw an exception
			if (this.connection == null || this.connection.isClosed())
				throw new IllegalStateException("DB Connection failure!");
		} 
		catch (SQLException e) 
		{			 
			e.printStackTrace();
		} 		
	}


}