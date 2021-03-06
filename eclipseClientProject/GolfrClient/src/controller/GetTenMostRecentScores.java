package controller;

import golfCourseObjects.GolfCourse;
import golfCourseObjects.Score;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This Thread gets the 10 most recent Scores for a given GolfCourse.  It is meant for the "now playing" screen.
 * 
 * Note: the ArrayList<Score> can only be retrieved after a this.run() or this.start() call, by using the this.getScoreList() method.
 * @author matt
 *
 */
public class GetTenMostRecentScores extends SQLQueries implements Runnable {

	private GolfCourse course;
	private ArrayList<Score> scoreList;
	
	public GetTenMostRecentScores() {
		super();
	}
	
	public GetTenMostRecentScores(GolfCourse course) {
		super();
		this.course = course;
	}
	
	@Override
	public void run()
	{
		synchronized(this)
		{
			super.connect();
			try {
				this.scoreList = super.getTenMostRecentScores(course);
				
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
	 * @return the scoreList
	 */
	public ArrayList<Score> getScoreList() {
		return scoreList;
	}

	/**
	 * @param scoreList the scoreList to set
	 */
	public void setScoreList(ArrayList<Score> scoreList) {
		this.scoreList = scoreList;
	}
	
	

}
