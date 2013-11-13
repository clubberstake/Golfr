/**
 * 
 */
package controller;

import golfCourseObjects.Game;
import golfCourseObjects.GolfCourse;
import golfCourseObjects.User;

/**
 * The PlayGame class represents the local controller for a game of golf.
 * It also contains the local data model for a game of golf.
 *  
 * @author Matt Gisoni
 *
 */
public class PlayGame 
{
	private Game game;
	private User user;
	private GolfCourse course;

	/**
	 *Default constructor.  A PlayGame initialized with this constructor
	 *contains null/default values for all variables.  I.e. it is not a 
	 *valid game.
	 */
	public PlayGame() 
	{
		super();
		this.user = new User();
		this.course = new GolfCourse();
		/*even in the default sense, a Game must be associated with a
		 * user and a course 
		 */
		this.game = new Game(this.user,this.course,0,0);
		/*
		 *A game and a user must always have a bi-directional <--> link. 
		 */
		this.user.setCurrentGame(this.game);

	}

	/**
	 * Typical-use constructor.  Instantiates a new game with a 0 score.
	 * @param game
	 * @param user
	 * @param course
	 */
	public PlayGame(User user, GolfCourse course) 
	{
		super();
		//null check and parameter validity checks
		if (user != null && course != null &&
				user.validityCheckGeneric() == true && 
				course.validityCheck() == true)
		{
		
			this.user = user;
			this.course = course;
			this.game = new Game(this.user,this.course,0,0);
			this.user.setCurrentGame(this.game);
		}
		else
		{
			throw (new IllegalArgumentException(
					"Constructor must contain a valid User and GolfCourse"));
		}
	}
	
	/**
	 * @return the game
	 */
	public Game getGame() {
		return this.game;
	}
	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}
	/**
	 * @return the user
	 */
	public User getUser() {
		return this.user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the course
	 */
	public GolfCourse getCourse() {
		return this.course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(GolfCourse course) {
		this.course = course;
	}


}
