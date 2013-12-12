package controller;

import golfCourseObjects.Game;

import java.sql.SQLException;


/**
 * This Thread computes the total score for a game of golf, and stores the result in t_scorehistory.
 * @author MAG
 *
 */
public class ComputeTotalScore extends SQLQueries implements Runnable 
{
	private Game game;
	private Integer totalScore;

	/**
	 * Default constructor--not for typical use
	 */
	public ComputeTotalScore() 
	{
		super();
	}
	
	/**
	 * Typical constructor
	 * @param game - the Game which you want to compute the total score for, and store
	 */
	public ComputeTotalScore(Game game)
	{
		this.game = game;
	}
	
	
	/**
	 * Override of Thread.run().  Executes the operation which calculates the total score for a game and stores
	 * the result in the DB.
	 * 
	 * Note:  The resulting total score is returned locally in this.totalScore, and can be retrieved by
	 * this.getTotalScore() after this.run() or this.start() have been called.
	 */
	@Override
	public void run()
	{
		synchronized(this)
		{
			super.connect();
			try {
				this.totalScore = super.computeTotalScore(this.game);
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

	/**
	 * @return the totalScore
	 */
	public Integer getTotalScore() {
		return totalScore;
	}

	/**
	 * @param totalScore the totalScore to set
	 */
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	

}
