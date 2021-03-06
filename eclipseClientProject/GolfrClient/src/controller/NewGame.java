package controller;

import golfCourseObjects.Game;
import golfCourseObjects.GolfCourse;
import golfCourseObjects.User;

import java.sql.SQLException;

/**
 * This Thread creates all of the records needed to store a "new" Game in the DB.
 * @author MAG
 *
 */
public class NewGame extends SQLQueries implements Runnable
{
	private User user;
	private GolfCourse course;
	private Game game;

	public NewGame() 
	{
		super();
	}

	public NewGame(User user, GolfCourse course) 
	{
		super();
		this.user = user;
		this.course = course;
	}

	@Override
	public void run()
	{
		synchronized(this)
		{
			super.connect();
			try {
				this.game = super.newGame(user, course);

			} catch (SQLException e) {			
				e.printStackTrace();
			}
			finally
			{
				this.close();
				notifyAll();
			}
		}
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
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
		return course;
	}

	/**
	 * @param course the course to set
	 */
	public void setCourse(GolfCourse course) {
		this.course = course;
	}

	/**
	 * @return the game
	 */
	public Game getGame() {
		return game;
	}

	/**
	 * @param game the game to set
	 */
	public void setGame(Game game) {
		this.game = game;
	}

}
