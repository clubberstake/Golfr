package controller;

import golfCourseObjects.Game;
import golfCourseObjects.Hole;

import java.sql.SQLException;

/**
 * This Thread allows for the scoring of a Hole in a Game of golf.
 * @author MAG
 *
 */
public class AddScoreForHole extends SQLQueries implements Runnable {

	private Game game;
	private Hole hole;
	private Integer score;

	/**
	 * Default constructor--not for typical use
	 */
	public AddScoreForHole() {
		super();
	}

	/**
	 * Typical constructor
	 * @param game - the Game being played
	 * @param hole - the Hole to be scored
	 * @param score - the value of the score to send to the DB
	 */
	public AddScoreForHole(Game game, Hole hole, Integer score) {
		super();
		this.game = game;
		this.hole = hole;
		this.score = score;
	}

	private void connectAndSendScoreToDB() {

		super.connect();
		try {
			super.addScoreForHole(game, hole, score);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();

		}

	}

	public void sendScoreToDB(Game gameIn, Hole holeIn, Integer scoreIn)
	{
		this.game = gameIn;
		this.hole = holeIn;
		this.score = scoreIn;

		connectAndSendScoreToDB();
	}

	@Override
	public void run()
	{
		synchronized (this)
		{
			super.connect();
			try 
			{
				super.addScoreForHole(game, hole, score);
			} 
			catch (SQLException e) 
			{
				e.printStackTrace();
			} 
			finally 
			{
				this.close();
				this.notifyAll();
			}
		}
	}

}
