package controller;

import golfCourseObjects.GolfCourse;

import java.sql.SQLException;


/**
 * This Thread gets the primary key of the t_golfcoursedetails table for the golf course which is provided.  This
 * thread is needed to GolfCourse.courseID variable when a course is created for the first time.
 * 
 * Note: the key can only be retrieved after a this.run or a this.start() call by using this.getCourseKey()
 * @author MAG
 *
 */
public class GetCoursePrimaryKey extends SQLQueries implements Runnable {

	private GolfCourse course;
	private Integer courseKey;

	public GetCoursePrimaryKey() {
		super();
	}

	public GetCoursePrimaryKey(GolfCourse course) {
		super();
		this.course = course;
	}

	@Override
	public void run() {
		
		synchronized(this)
		{
			super.connect();
			try {
				this.courseKey = super.getCoursePrimaryKey(course);
				course.setGolfCourseID(this.courseKey);
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
	 * @return the courseKey
	 */
	public Integer getCourseKey() {
		return courseKey;
	}

	/**
	 * @param courseKey the courseKey to set
	 */
	public void setCourseKey(Integer courseKey) {
		this.courseKey = courseKey;
	}

}
