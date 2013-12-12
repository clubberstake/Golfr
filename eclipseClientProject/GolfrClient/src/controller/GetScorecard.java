package controller;

import golfCourseObjects.Game;
import golfCourseObjects.Hole;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This Thread retrieves an ArrayList<Hole> which represents a scorecard for a particular game.  It is very similar
 * to the GetHoleMetadata class, but this class executes a much more complicated query.
 * 
 * Note: the ArrayList<Hole> can only be retrieved after a call to this.run() or this.start(), by using this.getScorecardList()
 * @author MAG
 *
 */
public class GetScorecard extends SQLQueries implements Runnable {

	private ArrayList<Hole> scorecardList;
	private Game game;
	
	/**
	 * Default constructor--not for typical use
	 */
	public GetScorecard() {
		super();
	}
	
	/**
	 * Typical constructor
	 * @param game - the Game whose scorecard you want to retrieve from the DB
	 */
	public GetScorecard(Game game) {
		super();
		this.game = game;
		this.scorecardList = new ArrayList<Hole>();
	}
	
	@Override
	public void run() {
		synchronized(this)
		{
			super.connect();
			try {
				this.scorecardList = super.getScorecard(game);
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
	 * @return the scorecardList
	 */
	public ArrayList<Hole> getScorecardList() {
		return scorecardList;
	}

	/**
	 * @param scorecardList the scorecardList to set
	 */
	public void setScorecardList(ArrayList<Hole> scorecardList) {
		this.scorecardList = scorecardList;
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
