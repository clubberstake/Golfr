package controller;

import golfCourseObjects.GolfCourse;

import java.sql.SQLException;

/**
 * This Thread gets an existing GolfCourse from the DB.
 * 
 * Note: the GolfCourse desired can only be retrieved after a this.run() or a this.start() call, by using this.getCourse().
 * @author MAG
 *
 */
public class GetCourse extends SQLQueries implements Runnable {

	private GolfCourse course;
	private Integer courseID;
	
	/**
	 *Default constructor--not for typical use
	 */
	public GetCourse() 
	{
		super();
	}
	
	/**
	 * Typical constructor which is needed to get the GolfCourse from the DB
	 * @param courseID - the primary key of the t_golfcoursedetails table for the course to be retrieved
	 */
	public GetCourse(Integer courseID)
	{
		super();		
		this.courseID = courseID;
	}
	
	/**
	 * Override of Thread.run() which executes the logic to retrieve a course from the DB.
	 * 
	 * Note: the GolfCourse object can only be retrieved after this method is called, using this.getCourse().
	 */
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
