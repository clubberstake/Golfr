package golfCourseObjects;

import java.sql.Timestamp;


/**
 * This class represents a score for a particular user on a particular course.  It is used in use case 4 to populate the list of most recent scores.
 * @author Matt Gisoni
 *
 */
public class Score 
{

	private Timestamp timestamp;
	private User user;
	private Integer currentHole;
	private Integer totalScore;
	/**
	 * 
	 */
	public Score() {
		super();
	}
	/**
	 * @param date
	 * @param user
	 * @param currentHole
	 * @param totalScore
	 */
	public Score(Timestamp timestamp, User user, Integer currentHole, Integer totalScore) 
	{
		super();
		this.timestamp = timestamp;
		this.user = user;
		this.currentHole = currentHole;
		this.totalScore = totalScore;
	}
	/**
	 * @return the date
	 */
	public Timestamp getTimestamp() {
		return timestamp;
	}
	/**
	 * @param date the date to set
	 */
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
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
	public void setUserName(User user) {
		this.user = user;
	}
	/**
	 * @return the currentHole
	 */
	public Integer getCurrentHole() {
		return currentHole;
	}
	/**
	 * @param currentHole the currentHole to set
	 */
	public void setCurrentHole(Integer currentHole) {
		this.currentHole = currentHole;
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
