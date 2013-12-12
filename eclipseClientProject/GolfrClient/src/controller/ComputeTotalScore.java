package controller;

import golfCourseObjects.Game;

import java.sql.SQLException;

public class ComputeTotalScore extends SQLQueries implements Runnable 
{
	private Game game;
	private Integer totalScore;

	public ComputeTotalScore() 
	{
		super();
	}
	
	public ComputeTotalScore(Game game)
	{
		this.game = game;
	}
	
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
