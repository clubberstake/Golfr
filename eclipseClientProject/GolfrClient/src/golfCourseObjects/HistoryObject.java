package golfCourseObjects;

import java.sql.Timestamp;

/**
 * Holds the data necessary for the user history screen in use case 7
 * @author MAG
 *
 */
public class HistoryObject implements Comparable<HistoryObject>
{
	private Game game;
	private Integer totalScore;
	private Timestamp timestamp;

	/**
	 * Default constructor
	 */
	public HistoryObject() 
	{
		super();
	}

	/**
	 * Typical constructor
	 */
	public HistoryObject(Game game, Integer totalScore, Timestamp timestamp)
	{
		this.game = game;
		this.totalScore = totalScore;
		this.timestamp = timestamp;
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

	/**
	 * @return the timestamp
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}

	/**
	 * @param timestamp the timestamp to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public int compareTo(HistoryObject toCompare) 
	{	
		return this.timestamp.compareTo(toCompare.getTimestamp());
	}

}
