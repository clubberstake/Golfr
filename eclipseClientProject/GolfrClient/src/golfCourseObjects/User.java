package golfCourseObjects;


/**
 * The User class represents a user who plays a game of golf.
 * @author MAG
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
	}
	
	/**
	 * Creates a new User with the given name, and a null userID
	 * @param userName
	 */
	public User(String userName)
	{
		super();
		this.username = userName;
		this.userID = null;
	}

	/**
	 * @param username
	 * @param userID
	 */
	public User(String username, Integer userID) 
	{
		super();
		this.username = username;
		this.userID = userID;		
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

	/**
	 * Checks the validity of User variable data.  For use when a user
	 * is not currently playing a game.
	 * @return true if the data is valid, false otherwise.
	 */
	public boolean validityCheckGeneric() 
	{
		if (this.userID > 0 &&
				this.username.length() >=1)
			return true;
		else
			return false;
	}

	/**
	 * Checks the validity of User variable data.  For use when a user
	 * is currently playing a game.
	 * @return true if the data is valid, false otherwise.
	 */
	public boolean validityCheckInGame() 
	{
		if (this.userID > 0 &&
				this.username.length() >=1 &&
				this.currentGame != null)
			return true;
		else
			return false;
	}

}
