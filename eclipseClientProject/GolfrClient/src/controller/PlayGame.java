/**
 * 
 */
package controller;

import golfCourseObjects.Game;
import golfCourseObjects.GolfCourse;
import golfCourseObjects.User;

/**
 * The PlayGame class serves as the controller for use case 5, playing a game and scoring a hole.
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
	 * Sets the total score for a game to the score passed in.
	 * @param score - the updated score for the game
	 */
	public void setTotalScore(Integer score)
	{
		if (score != null && score >= 0)
			this.game.setTotalScore(score);
		else
			throw new IllegalArgumentException("score must be >= 0");
	}

	/**
	 * Sets the score for the given hole number
	 * @param holeNumber - the hole to be scored (must be 1-18)
	 * @param score - the updated score for the hole
	 */
	public void scoreHole(Integer holeNumber, Integer score)
	{
		if (holeNumber != null && score != null && holeNumber < 0 && holeNumber <= 18 && score >= 0)
			this.game.getCourse().getHoles().get(holeNumber-1).setScore(score);
		else
			throw new IllegalArgumentException("holeNumber must be 1-18 and score must be >= 0");
	}
	
	/**
	 * Gets the score for the given hole number
	 * @param holeNumber - the hole whose score is desired
	 * @return - the score for the given holeNumber
	 */
	public Integer getHoleScore(Integer holeNumber)
	{
		if (holeNumber != null && holeNumber > 0 && holeNumber <= 18)
			return this.game.getCourse().getHoles().get(holeNumber-1).getScore();
		else
			throw new IllegalArgumentException("holeNumber must be 1-18");
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
