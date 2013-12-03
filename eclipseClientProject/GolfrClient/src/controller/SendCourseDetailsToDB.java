package controller;

import golfCourseObjects.GolfCourse;

import java.sql.SQLException;

public class SendCourseDetailsToDB extends SQLQueries implements Runnable {

	private GolfCourse newCourse;
	
	public SendCourseDetailsToDB() {
		super();
	}
	public SendCourseDetailsToDB(GolfCourse newCourse) {
		super();
		this.newCourse = newCourse;
	}

	@Override
	public void run() {
		synchronized(this)
		{
			super.connect();
			try {
				super.sendCourseDetailsToDB(newCourse);
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
	 * @return the newCourse
	 */
	public GolfCourse getNewCourse() {
		return newCourse;
	}
	/**
	 * @param newCourse the newCourse to set
	 */
	public void setNewCourse(GolfCourse newCourse) {
		this.newCourse = newCourse;
	}

}
