package golfCourseObjects;

import java.sql.Date;


/**
 * This class represents a score for a particurlar user on a particurlar course.  It is used in use case 4 to populate the list of most recent scores.
 * @author Matt Gisoni
 *
 */
public class Score 
{

	private Date date;
	private String userName;
	private Integer currentHole;
	private Integer totalScore;
	/**
	 * 
	 */
	public Score() {
		super();
		// TODO Auto-generated constructor stub
	}
	/**
	 * @param date
	 * @param user
	 * @param currentHole
	 * @param totalScore
	 */
	public Score(Date date, String userName, Integer currentHole, Integer totalScore) 
	{
		super();
		this.date = date;
		this.userName = userName;
		this.currentHole = currentHole;
		this.totalScore = totalScore;
	}
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}
	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}
	/**
	 * @return the user
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * @param user the user to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
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
