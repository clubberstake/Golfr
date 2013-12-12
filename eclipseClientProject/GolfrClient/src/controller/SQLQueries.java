package controller;

import golfCourseObjects.Game;
import golfCourseObjects.GolfCourse;
import golfCourseObjects.HistoryObject;
import golfCourseObjects.Hole;
import golfCourseObjects.Score;
import golfCourseObjects.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;


/**
 * This abstract class contains the code needed to execute all MySQL queries for the client application.  It was decided to implement it this way so that all of the SQL could be in
 * one central location, instead of many distributed classes.
 * 
 *  The child classes implement the Runnable interface so that their run() method invokes the corresponding method in this superclass.
 *  
 *  The class extends Thread so that the SQL queries can be run on a separate thread for both Android jUnit tests and the client application.
 *  
 *  This class executes rather slowly due to the need to repeatedly open and close the java.SQL connection to the DB.  This implementation was chosen for safety, because reusing an active
 *  connection for multiple queries is not recommended.  A "query pool" would be needed on the server side in order to allow for connection reuse. 
 * @author MAG
 *
 */
public abstract class SQLQueries extends Thread
{

	/**
	 * The java.sql connection to the database
	 */
	private Connection connection;

	/**
	 * Default constructor.  To be called in child classes.
	 */
	public SQLQueries()
	{
		super();		
	}


	/**
	 * Creates the records required for a new game in the database.
	 * 
	 * Also creates new users in t_user if needed.
	 * 
	 * @param user - the user who is playing the game
	 * @param course - the course to be played--must have a valid course.getGolfCourseID()
	 * @return Game object representing the new game
	 * @throws SQLException - if DB operations fail, an SQLException is thrown
	 */
	//MAG: tested in TestDBOperations
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

			//first get the 18 holes of the course to duplicate in the t_scorecard table
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
		//instantiate a new Game object
		Game toReturn = new Game(user,course,0,0);
		//important: set the scoreHistory_pk for the new Game object
		toReturn.setScoreHistoryPK(scoreHistoryPK);
		return toReturn;

	}


	/**
	 * Deletes all of the records in the DB created by the newGame() method.  Meant for testing purposes.
	 * @param scorecardID - the t_scorehistory.scoreHistory_pk primary key of the game to be deleted
	 * @throws SQLException 
	 */
	//MAG: tested in TestDBOperations
	public void deleteGameFromDB(Integer scoreHistory_pk) throws SQLException
	{
		Statement statement1 = null;
		Statement statement2 = null;
		Statement statement2point5 = null;
		Statement statement3 = null;		
		String query = "DELETE FROM t_scorecard WHERE scoreHistory_fk = '" + scoreHistory_pk + "'";

		try 
		{
			/*first delete the 18 t_scorecard records*/
			if (connection == null || connection.isClosed())
				this.connect();
			statement1 = connection.createStatement();
			if (statement1.executeUpdate(query) != 18)
				throw new IllegalStateException("deleteGameFromDB() failed to delete all 18 t_scorecard records");
			statement1.close();
			connection.close();

			/*then delete the t_scorehistory record*/
			if (connection == null || connection.isClosed())
				this.connect();
			statement2 = connection.createStatement();
			query = "DELETE FROM t_scoreHistory WHERE scoreHistory_pk = '" + scoreHistory_pk +"'";
			if(statement2.executeUpdate(query)!=1)
				throw new IllegalStateException("deleteGameFromDB() failed to delete the t_scoreHistory record");
			statement2.close();
			connection.close();

			/*then get t_golfcoursehistory primary key*/
			if (connection == null || connection.isClosed())
				this.connect();
			statement2point5 = connection.createStatement();
			query = "SELECT golfCourseHistory_pk FROM t_golfcoursehistory WHERE scoreHistory = '" + scoreHistory_pk + "'";
			ResultSet r2point5 = statement2point5.executeQuery(query);
			Integer golfCourseHistory_pk = null;
			while (r2point5.next())
			{
				golfCourseHistory_pk = r2point5.getInt("golfCourseHistory_pk");
			}
			r2point5.close();
			statement2point5.close();
			connection.close();

			if (golfCourseHistory_pk == null)
				throw new IllegalStateException("deleteGameFromDB could not find the primary key for t_golfcoursehistory, and therefore could not delete the corresponding record");

			/*then delete t_golfcoursehistory record*/
			if (connection == null || connection.isClosed())
				this.connect();
			statement3 = connection.createStatement();
			query = "DELETE FROM t_golfcoursehistory WHERE golfCourseHistory_pk = '" + golfCourseHistory_pk + "'";
			if (statement3.executeUpdate(query) != 1)
				throw new IllegalStateException("deleteGameFromDB() failed to delete the t_golfcoursehistory record");
			statement3.close();
			connection.close();
		} 
		catch (SQLException e) 
		{
			throw new IllegalStateException("deleteGameFromDB() threw an SQLException while trying to delete a game");			
		}
		finally
		{
			if (connection!=null)
				connection.close();
		}


	}

	/**
	 * This method returns an array list of Games which the user has played.  It is meant to be used for use case 7
	 * @param user - the user whose history is to be retrieved.  The user.getUsername() must not be null
	 * @return an ArrayList<HistoryObject> whose size() is <= 10
	 */
	//MAG: tested by TestDBOperations.java
	public ArrayList<HistoryObject> getUserHistory(User user) throws SQLException
	{
		if (user == null || user.getUsername() == null)
			throw new IllegalArgumentException("In getUserHistory(user), the user cannot be null.");

		
		Statement statement1 = null;
		String query = "SELECT * " +
				"FROM t_user " +
				"WHERE faceBookID = '" + user.getUsername()+ "'";
		ArrayList<Game> gameList = new ArrayList<Game>();
		ArrayList<Integer> courseIDList = new ArrayList<Integer>();
		ArrayList<Integer> totalScoreList = new ArrayList<Integer>();
		ArrayList<Timestamp> timestampList = new ArrayList<Timestamp>();
		ArrayList<HistoryObject> toReturn = new ArrayList<HistoryObject>();

		try
		{
			/*
			 * First, verify that the user exists
			 */
			if (connection == null || connection.isClosed())
				this.connect();

			statement1 = connection.createStatement();
			ResultSet rs1 = statement1.executeQuery(query);

			Integer userID_pk = null;
			while (rs1.next()) 
			{
				userID_pk = rs1.getInt("userID_pk");
			}
			rs1.close();

			if (userID_pk == null)
				throw new IllegalStateException("Cannot find user's primary key in SQLQueries.getUserHistory()");

			statement1.close();
			connection.close();

			/*
			 * Get all games associated with user
			 */
			Statement statement2 = null;
			query = "SELECT * from t_scorehistory WHERE userID = '" + user.getUserID() + "'";

			if (connection == null || connection.isClosed())
				this.connect();

			statement2 = connection.createStatement();
			ResultSet rs2 = statement2.executeQuery(query);
			Integer scoreHistory_pk = null;
			Integer totalScore = null;
			Integer courseID = null;
			Timestamp timestamp = null;

			
			/*get the list of games with their ancillary information.  
			 * Seperate arrayLists are needed here to store data temporarily because of the way the this.connect() works--if you call other methods of this class inside the while loop, the connection
			 * would be reset and the rs2 loop would not work anymore.
			 * */
			while (rs2.next())
			{
				scoreHistory_pk = rs2.getInt("scoreHistory_pk");
				totalScore = rs2.getInt("totalScore");
				courseID = rs2.getInt("courseID");
				timestamp = rs2.getTimestamp("timestamp");
				Game g = new Game(user,null,0,0);
				g.setScoreHistoryPK(scoreHistory_pk);
				gameList.add(g);
				courseIDList.add(courseID);
				totalScoreList.add(totalScore);
				timestampList.add(timestamp);
			}
			rs2.close();
			statement2.close();
			connection.close();
			
			
			/*Create and populate the HistoryObjects based on the arrayLists*/ 
			for(int i=0;i<courseIDList.size();i++)
			{
				GolfCourse course = this.getCourse(courseIDList.get(i));
				ArrayList<Hole> scorecard = this.getScorecard(gameList.get(i));
				course.setHoles(scorecard);
				gameList.get(i).setCourse(course);
				HistoryObject o = new HistoryObject(gameList.get(i),totalScoreList.get(i),timestampList.get(i));
				toReturn.add(o);				
			}
			
			if (connection != null)
				connection.close();

		}
		catch (SQLException e)
		{
			throw new IllegalStateException("Cannot find user's primary key in SQLQueries.getUserHistory()");
		}
		finally
		{
			if (connection!=null)
				connection.close();
		}

		return toReturn;
	}



	/**
	 * Computes the total score for a game, and updates the total score in the t_scorehistory table in the DB.
	 * @param game - the game with the total score to be updated--must have correct game.getScoreHistoryPK() value.
	 * @return - the total score for the game
	 */
	//MAG: tested in TestDBOperations
	public Integer computeTotalScore(Game game) throws SQLException
	{

		ArrayList<Hole> scorecard = null;
		Integer totalScore = 0;

		
		/*first get the scorecard for the given game*/
		try 
		{
			scorecard = this.getScorecard(game);
			if (scorecard == null)
				throw new IllegalStateException("Unable to compute total score, because the game scorecard could not be retrieved.");	
		} catch (SQLException e) 
		{			
			e.printStackTrace();
			throw new IllegalStateException("Unable to compute total score, because the game scorecard could not be retrieved.");
		}		

		/*compute the total score*/
		for (Hole h : scorecard)
		{
			totalScore += h.getScore();
		}

		/*the update the t_scorehistory table with the new score*/
		Statement statement1 = null;
		String query = "UPDATE t_scorehistory SET totalScore = '" + totalScore + "' WHERE scoreHistory_PK = '" + game.getScoreHistoryPK() + "'";

		try
		{
			if (connection == null || connection.isClosed())
				this.connect();

			statement1 = connection.createStatement();
			if (statement1.executeUpdate(query) != 1)
				throw new IllegalStateException("Unable to update totalScore in t_scorehistory in the DB.");
			statement1.close();
			connection.close();
		}
		catch (SQLException e)
		{
			throw new IllegalStateException("Unable to update totalScore in t_scorehistory in the DB.");
		}
		finally
		{
			if (connection != null)
				connection.close();
		}

		return totalScore;		
	}

	/**
	 * Scores a hole for the given game, and updates the DB accordingly.  Note that the game and hole parameters must be correctly configured for their corresponding primary keys in the DB.
	 * @param game - the Game being played
	 * @param hole - the Hole to score
	 * @param score - the score value to update in the DB
	 * @throws SQLException
	 */
	//MAG: tested in TestDBOperations.java
	public void addScoreForHole(Game game, Hole hole, Integer score) throws SQLException
	{
		Statement statement1 = null;
		Statement statement2 = null;
		String query = "SELECT DISTINCT t_scorecard.scorecardID " + 
				"FROM t_scorecard INNER JOIN (t_holes, t_scorehistory, t_golfcoursehistory) " +
		"ON (t_holes.holeID = t_scorecard.holeID AND t_scorehistory.scoreHistory_pk = t_scorecard.scoreHistory_fk) " +
		"WHERE t_scorecard.holeID = '" + hole.getHoleID() + "' AND t_scorecard.scoreHistory_fk = '" + game.getScoreHistoryPK() + "';";
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
				scorecardID = rs.getInt("scorecardID");				
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
	 * Returns an ArrayList<Hole> which represents the scorecard for a game.  The scores are included in the Hole objects.  This query has similar RESULTS to SQLQueries.getHoleMetadata(),
	 * but is a much more complicated query due to the normalized DB design.  This method is to be used when the actual scores for the Holes are needed.
	 * 
	 * @param game - the game to retrieve the scorecard for.  Game.user and Game.course must be valid and complete (i.e. their key/ID fields must not be null)
	 * @return - an ArrayList<Hole> which contains the 18 holes of the course and the score for each hole (as it exists in the DB)
	 */	
	//MAG: tested by TestDBOperations.java
	public ArrayList<Hole> getScorecard(Game game)  throws SQLException
	{
		ArrayList<Hole> toReturn = new ArrayList<Hole>(18);
		Statement statement = null;
		String query = "SELECT t_holes.holeID, t_holes.golfCourseID, t_holes.holeNumber, t_holes.whiteTee, t_holes.redTee, t_holes.blueTee, t_holes.handicap, " +
				"t_holes.par, t_scoreHistory.scoreHistory_pk, t_scorecard.strokes " + 
				"FROM t_scorecard INNER JOIN (t_scorehistory, t_holes) " +
				"ON (t_scorehistory.scoreHistory_pk = t_scorecard.scoreHistory_fk AND t_holes.holeID = t_scorecard.holeID) " +
				"WHERE scoreHistory_pk = '" + game.getScoreHistoryPK() + "';";
		try
		{
			/*first, run the complicated query which relates t_scorecard, t_scorehistory, and t_holes.  This will return all of the information for a Hole object, including the score*/
			if (connection == null || connection.isClosed())
				this.connect();

			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);

			while (rs.next()) 
			{
				/*get all of the paramaters for new Hole(...)*/
				Integer holeID = rs.getInt("holeID");
				Integer courseID = rs.getInt("golfCourseID");
				Integer whiteTee = rs.getInt("whiteTee");
				Integer redTee = rs.getInt("redTee");
				Integer blueTee = rs.getInt("blueTee");
				Integer handicap = rs.getInt("handicap");
				Integer par = rs.getInt("par");
				Integer holeNumber = rs.getInt("holeNumber");
				Integer strokes = rs.getInt("strokes");
				
				/*instantiate a new Hole(...) and add to the return list*/
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
	 * 
	 * Note: the score for each hole is null.  It is meant to produce a generic list of the 18 holes which are associated with the given course.  It does not provide any information
	 * on the scores of a Game.
	 * 
	 * 
	 * @param course - the course to get the hole list from
	 * @return - array list of Holes for the provided course (without the score)
	 */
	//MAG: tested in TestDBOperations.java
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
			/*run the query to get the 18 hole records associated with the course from t_holes*/
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
	
	/**
	 * Gets the 10 most recent scores for a given course.  To be used on the "now playing" screen.
	 * @param course - the course for which the most recent scores are desired
	 * @return - an ArrayList of Score objects, which represent the "scores" of a user's game(s).  The list is sorted with the most recent score at position 0.
	 * @throws SQLException
	 */
	//MAG: tested by TestDBOperaions	
	public ArrayList<Score> getTenMostRecentScores(GolfCourse course)  throws SQLException
	{
		ArrayList<Score> toReturn = new ArrayList<Score>(10);

		Statement statement = null;
		String query = "SELECT t_user.faceBookID, t_user.userID_pk, t_golfcoursehistory.golfCourseHistory_pk, t_golfcoursehistory.courseID, t_golfcoursehistory.scoreHistory, t_scorehistory.userID, t_scorehistory.totalScore, t_scorehistory.`timestamp` AS `time` "+ 
"FROM golfr.t_golfcoursehistory INNER JOIN (t_scorehistory, t_user) " + 
"ON (t_scorehistory.scoreHistory_pk = t_golfcoursehistory.scoreHistory AND " + 
"t_user.userID_pk = t_scorehistory.userID) " + 
"WHERE t_golfcoursehistory.courseID = '" + course.getGolfCourseID() + "' ORDER BY t_scorehistory.timestamp DESC LIMIT 0, 10;";		

		try
		{
			/*Run the complicated query to get the 10 most recent entries in the t_golfcoursehistory table*/
			if (connection == null || connection.isClosed())
				this.connect();
			statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(query);
			
			while (rs.next()) 
			{
				Timestamp timestamp = rs.getTimestamp("time");
				String userName = rs.getString("facebookID");				
				Integer totalScore = rs.getInt("totalScore");
				Integer userID = rs.getInt("userID_pk");
				User u = new User(userName,userID);
				
				//create a new Score object and add to the return list
				Score toAdd = new Score(timestamp, u, null, totalScore);				
				toReturn.add(toAdd);
			}	
			rs.close();
			
			/*for each Score, get the corresponding Game, and determine current hole*/
			for (int i=0; i<toReturn.size();i++)
			{				
				Game game = new Game(toReturn.get(i).getUser(), course,toReturn.get(i).getTotalScore(),0);
				ArrayList<Hole> scorecard = this.getScorecard(game);
				int currentHole = 0;
				for (Hole h : scorecard)
				{
					if (h.getScore() == null || h.getScore() == 0)
						break;
					currentHole++;
				}
				//set the current hole for the Score object
				toReturn.get(i).setCurrentHole(currentHole);
			}
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
	 * @param course - a GolfCourse with a null cousreID_pk
	 * @return if found, the primary key.  Null otherwise.
	 * @throws SQLException 
	 */
	//MAG: tested in TestDBOperations.java
	public Integer getCoursePrimaryKey(GolfCourse course) throws SQLException
	{
		Integer key = null;
		Statement statement = null;
		/*
		 * The query works on course name, phone, and webAddress.  If all 3 are an exact match, then set the key to return
		 */
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
	//MAG: tested in TestDBOperations.java
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
	 * Adds a course to the DB table t_golfcoursedetails.  It is meant for use case 3.  Note, it does not add the 18 holes of the new course to the DB, 
	 * that is done by SQLQueries.addHoleToCourseInDB(). 
	 * @param newCourse - the GolfCourse to be created.  Should have a null courseID
	 * @throws SQLException 
	 */
	//MAG: tested in TestDBOperations.java
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
	 * @param toAdd - the hole to add to the DB.  HoleID should be null
	 */
	//MAG: tested in TestDBOperations.java
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
				throw new IllegalStateException("Could not add Hole to course in DB");
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
	//MAG: tested in TestDBOperations.java
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
			if (rs != null)
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


	}

	/**
	 * Gets the GolfCourse object from the DB for the given courseID primary key of t_golfcoursedetails
	 * @param courseID - the primary key of t_golfcoursedetails
	 * @return - a fully populated GolfCourse object
	 * @throws SQLException 
	 */
	//MAG tested in TestDBOperaions.java
	public GolfCourse getCourse(Integer courseID) throws SQLException
	{
		Statement statement1 = null;
		String query = "SELECT * FROM t_golfcoursedetails WHERE courseID_pk = '" + courseID + "'";
		ArrayList<Hole> holes = null;
		GolfCourse course = null;

		try
		{
			if (connection == null || connection.isClosed())
				this.connect();

			statement1 = connection.createStatement();
			ResultSet rs1 = statement1.executeQuery(query);

			/*
			 * First, get the course object from t_coursedetails
			 */
			while(rs1.next())
			{
				String courseName = rs1.getString("courseName");
				String streetName = rs1.getString("streetName");
				String streetNumber = rs1.getString("streetNumber");
				String postalCode = rs1.getString("postalCode");
				String phone = rs1.getString("phone");
				String webAddress = rs1.getString("webAddress");
				holes = new ArrayList<Hole>();
				course = new GolfCourse(courseName,streetName,streetNumber,postalCode,phone,webAddress,holes,courseID);
			}
			rs1.close();
			statement1.close();
			connection.close();

			/*
			 *Second, get the holes associated with the course 
			 */
			holes = this.getHoleMetadata(course);
			course.setHoles(holes);
			//set totalPar for the course
			Integer totalPar = 0;
			for (Hole h: holes)
			{
				totalPar += h.getPar();
			}
			course.setTotalPar(totalPar);
		}
		catch (SQLException e)
		{
			throw new IllegalStateException("Unable to get the golfCourse object from the DB for SQLQueries.getCourse()");
		}
		finally
		{
			if (connection != null)
				connection.close();			
		}
		return course;
	}

	/**
	 * Helper method to establish or re-establish a connection to the DB.
	 */
	public void connect() throws IllegalStateException
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		} 
		catch (InstantiationException e) 
		{
			e.printStackTrace();
		} 
		catch (IllegalAccessException e) 
		{
			e.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}

		String url = "jdbc:mysql://192.168.1.6:3306/golfr";//this IP address needs to be set to the machine which is hosting the MySQL DB.

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