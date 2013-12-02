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

import golfCourseObjects.Game;
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
	 * Creates the records required for a new game in the database.
	 * @param user - the user who is playing the game
	 * @param course - the course to be played--must have a valid course.getGolfCourseID()
	 * @return Game object representing the new game
	 * @throws SQLException 
	 */
	public Game newGame(User user, GolfCourse course) throws SQLException
	{
		Integer scoreHistoryPK = null;
		Statement statement = null;

		//determine if the user exists in t_user table
		String query = "SELECT * " +
				"FROM t_user " +
				"WHERE faceBookID = '" + user.getUsername()+ "'";

		try
		{
			if (connection == null || connection.isClosed())
				this.connect();

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			Integer userID_pk = null;
			while (rs.next()) 
			{
				userID_pk = rs.getInt("userID_pk");
			}
			rs.close();

			//if user does not exist in t_user table, add him to the table and get the user's primary key
			if (userID_pk == null)
			{
				//close 1st statement and connection
				statement.close();
				connection.close();
				//reconnect
				this.connect();

				//Insert the new user into t_user
				query = "INSERT INTO t_user (faceBookID) VALUES ('" + user.getUsername() + "');";
				Statement statement2 = connection.createStatement();				
				if (statement2.executeUpdate(query) != 1)
					throw new IllegalStateException("Tried to create a new user in the DB, but failed!");
				//close 2nd statement and connection
				statement2.close();
				connection.close();

				//get new user's primary key
				query = "SELECT * " +
						"FROM t_user " +
						"WHERE faceBookID = '" + user.getUsername()+ "'";

				this.connect();
				Statement statement2point5 = connection.createStatement();
				ResultSet rs2point5 = statement2point5.executeQuery(query);
				while (rs2point5.next()) 
				{
					userID_pk = rs.getInt("userID_pk");
				}
				rs2point5.close();
				statement2point5.close();
				connection.close();
			}
			else
			{
				//close 1st statement and connection, if the user was already in t_user table
				statement.close();
				connection.close();
			}

			/*create t_scorehistory record to represent a new game*/

			//reconnect
			this.connect();
			query = "INSERT INTO t_scorehistory (userID, totalScore, courseID) VALUES ('" + userID_pk + "','0', '" + course.getGolfCourseID() + "');";
			Statement statement3 = connection.createStatement();

			if (statement3.executeUpdate(query) != 1)
				throw new IllegalStateException("Tried to create a new user in the DB, but failed!");
			//close 3rd statement and connection
			statement3.close();
			connection.close();

			/*create t_golfcoursehistory record to represent a new game*/

			//first, get the primaryKey of the t_scorehistory record
			//reconnect
			this.connect();
			query = "SELECT scoreHistory_pk FROM t_scorehistory WHERE userID = '" + userID_pk + "' AND totalScore = '0' AND courseID = '" + course.getGolfCourseID() + "' ORDER BY `timestamp`;";
			System.out.println(query);
			Statement statement4 = connection.createStatement();
			ResultSet rs4 = statement4.executeQuery(query);

			while (rs4.next())
			{
				scoreHistoryPK = rs4.getInt("scoreHistory_pk");
			}
			if (scoreHistoryPK == null)
				throw new IllegalStateException("Could not get t_scorehistory.scoreHistory_pk for new game!");

			//close 4th statement and connection
			rs4.close();
			statement4.close();
			connection.close();

			//second, INSERT new t_golfcoursehistory record
			//reconnect
			this.connect();
			query = "INSERT INTO t_golfcoursehistory (courseID, scoreHistory) VALUES ('" + course.getGolfCourseID() + "', '" + scoreHistoryPK +"');";
			Statement statement5 = connection.createStatement();
			if (statement5.executeUpdate(query) != 1)
				throw new IllegalStateException("Could not create a new t_golfcoursehistory record for a new game!");
			//close 5th statement and connection
			statement5.close();
			connection.close();

			/*create 18 new t_scorecard records to store the game's scorecard*/

			//first get the 18 holes of the course to duplicate
			ArrayList<Hole> holeList = this.getHoleMetadata(course);
			if (holeList == null)
				throw new IllegalStateException("Could not get the course's hole list, and therefore could not create scorecard for the new game!");

			//create 18 new scorecard records
			for (Hole h : holeList)
			{
				query = "INSERT INTO t_scorecard (holeID, scoreHistory_fk) VALUES ('" + h.getHoleID() + "', '" + scoreHistoryPK +"');";
				//reconnect
				this.connect();
				Statement statement6 = connection.createStatement();
				if (statement6.executeUpdate(query) != 1)
					throw new IllegalStateException("Could not create new t_scorecard records for the new game!");
				//close 6th statement and connection
				statement6.close();
				connection.close();
			}

		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Could not create records needed for the new game!");

		}	
		finally
		{
			if (connection != null)
				connection.close();
		}
		Game toReturn = new Game(user,course,0,0);
		toReturn.setScoreHistoryPK(scoreHistoryPK);
		return toReturn;

	}


	/**
	 * Deletes all of the records in the DB created by the newGame() method.  Meant for testing purposes.
	 * @param scorecardID - the t_scorecard.scorecardID primary key of the game to be deleted
	 */
	/*public void deleteGameFromDB(Integer scorecardID)
	{
		Statement statement1 = null;
		Statement statement2 = null;
		Statement statement3 = null;
		String deleteScorecardQuery = "DELETE FROM t_scorecard WHERE scorecardID = " + scorecardID;		
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
	}*/

	/*
	
	addTotalScore(user, GolfCourse, Game) 
	getUserHistory(user)
	 */

	/**
	 * Scores a hole for the given game.  Note that the game and hole paramaters must be correctly configured for their corresponding primary keys in the DB.
	 * @param game
	 * @param hole
	 * @param score
	 * @throws SQLException
	 */
	public void addScoreForHole(Game game, Hole hole, Integer score) throws SQLException
	{
		Statement statement1 = null;
		Statement statement2 = null;
		String query = "SELECT DISTINCT t_scorecard.scorecardID FROM t_holes, t_scorecard, t_scorehistory " +
				"WHERE t_scorehistory.scoreHistory_pk = '" + game.getScoreHistoryPK() + "' AND " +
				"t_scorecard.scoreHistory_fk = '" + game.getScoreHistoryPK() + "' AND " +
				"t_holes.golfCourseID = '" + game.getCourse().getGolfCourseID() + "' AND " +
				"t_holes.holeNumber = '" + hole.getHoleNumber() + "' AND " +
				"t_scorecard.holeID = '" + hole.getHoleID()	+ "';";
		Integer scorecardID = null;

		try
		{

			/*first, get scorecardID of the record to update*/
			if (connection == null || connection.isClosed())
				this.connect();

			statement1 = connection.createStatement();
			ResultSet rs = statement1.executeQuery(query);

			while (rs.next()) 
			{
				scorecardID = rs.getInt("strokes");				
			}	

			if (scorecardID == null)
				throw new IllegalStateException("Could not get scorecardID which is needed to update the score for a hole!");
			rs.close();
			statement1.close();
			connection.close();

			/*second, update the appropriate scorecard record*/
			this.connect();

			query = "UPDATE t_scorecard SET strokes = '" + score + "' WHERE scorecardID = '" + scorecardID + "';";
			statement2 = connection.createStatement();
			if (statement2.executeUpdate(query) != 1)
				throw new IllegalStateException("Could not update score for the hole!");
			statement2.close();
			connection.close();

		}
		catch (SQLException e)
		{
			e.printStackTrace();
			throw new IllegalStateException("Could not get holes for the provided course.");

		}		
		finally
		{

			if (connection != null)			
				connection.close();

		}

	}


	/**
	 * Returns an ArrayList<Hole> which represents the scorecard for a game.  The scores are included in the Hole objects.
	 * 
	 * @param game - the game to retrieve the scorecard for.  Game.user and Game.course must be valid and complete (i.e. their key/ID fields must not be null)
	 * @return
	 */	
	public ArrayList<Hole> getScorecard(Game game)  throws SQLException
	{
		ArrayList<Hole> toReturn = new ArrayList<Hole>(18);
		Statement statement = null;
		String query = "SELECT DISTINCT t_holes.holeID, t_holes.golfCourseID, t_holes.holeNumber, t_holes.whiteTee, t_holes.redTee, t_holes.blueTee, t_holes.handicap," +
				"t_holes.par, t_scoreHistory.scoreHistory_pk, t_scorecard.strokes FROM t_holes, t_scorecard, t_scorehistory " +
				"WHERE t_scorehistory.scoreHistory_pk = '" + game.getScoreHistoryPK() + "' AND " +
				"t_scorecard.scoreHistory_fk = '" + game.getScoreHistoryPK() + "' AND " +
				"t_holes.golfCourseID = '" + game.getCourse().getGolfCourseID() + "';";
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
				Integer strokes = rs.getInt("strokes");
				Hole toAdd = new Hole(holeID, courseID,holeNumber,whiteTee,redTee,blueTee,handicap,par,strokes);				
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

			if (connection != null)			
				connection.close();

		}

		return toReturn;

	}

	/**
	 * Returns an ArrayList of Holes which contains the 18 holes of the provided GolfCourse.  This list contains the "metadata" for each hole (number, par, yardage, etc.).
	 * @param course
	 * @return - array list of Holes for the provided course
	 */
	public ArrayList<Hole> getHoleMetadata(GolfCourse course)  throws SQLException
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

			if (connection != null)			
				connection.close();
		}

		return toReturn;

	}

	public ArrayList<Score> getTenMostRecentScores(GolfCourse course)  throws SQLException
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

			if (connection != null)			
				connection.close();

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

			if (connection != null)
			{ 
				connection.close(); 
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

			if (statement1 != null || statement2 != null || connection != null)
			{ 

				statement1.close();
				statement2.close();
				connection.close();

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
			if (connection != null)
				connection.close();

		}

	}

	/**
	 * Adds a Hole object to the associated course in the DB.
	 * @param courseKey - the primary key of the course which is to have a hole added to it.  Use SQLQueries.getCoursePrimaryKey() to get the correct key if not known.
	 * @param toAdd - the hole to add to the DB
	 */
	public void addHoleToCourseInDB(Integer courseKey, Hole toAdd) throws SQLException
	{
		String query = "INSERT INTO t_holes (golfCourseID, holeNumber, whiteTee, redTee, blueTee, handicap, par) VALUES ('" + 
				courseKey + "', '" +
				toAdd.getHoleNumber() + "', '" +
				toAdd.getWhiteTeeYargage() + "', '" +
				toAdd.getRedTeeYardage() + "', '" +
				toAdd.getBlueTeeYardage() + "', '" +
				toAdd.getHandicap() + "', '" +
				toAdd.getPar() + "');";

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
			if (connection != null)
				connection.close();

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
				statement.close(); 
			if (connection != null)
				connection.close();

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