package golfCourseObjects;

/**
 * The User class represents a user who plays a game of golf.
 * @author Matt Gisoni
 *
 */
public class User 
{
	private String username;
	private Integer userID;
	private Game currentGame;
	
	/**
	 * Default constructor
	 */
	public User() 
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @param username
	 * @param userID
	 * @param currentGame
	 */
	public User(String username, Integer userID, Game currentGame) 
	{
		super();
		this.username = username;
		this.userID = userID;
		this.currentGame = currentGame;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return this.username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the userID
	 */
	public Integer getUserID() {
		return this.userID;
	}
	/**
	 * @param userID the userID to set
	 */
	public void setUserID(Integer userID) {
		this.userID = userID;
	}

	/**
	 * @return the currentGame
	 */
	public Game getCurrentGame() {
		return this.currentGame;
	}

	/**
	 * @param currentGame the currentGame to set
	 */
	public void setCurrentGame(Game currentGame) {
		this.currentGame = currentGame;
	}

}
