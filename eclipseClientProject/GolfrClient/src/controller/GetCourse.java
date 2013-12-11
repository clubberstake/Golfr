package controller;

import java.sql.SQLException;

import golfCourseObjects.GolfCourse;

public class GetCourse extends SQLQueries implements Runnable {

	private GolfCourse course;
	private Integer courseID;
	
	public GetCourse() 
	{
		super();
	}
	
	public GetCourse(Integer courseID)
	{
		super();		
		this.courseID = courseID;
	}
	
	@Override
	public void run() 
	{
		synchronized(this)
		{
			super.connect();
			try {
				this.course = super.getCourse(courseID);
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
	 * @return the courseID
	 */
	public Integer getCourseID() {
		return courseID;
	}

	/**
	 * @param courseID the courseID to set
	 */
	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

}
