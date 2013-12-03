package controller;

import golfCourseObjects.GolfCourse;

import java.sql.SQLException;

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
