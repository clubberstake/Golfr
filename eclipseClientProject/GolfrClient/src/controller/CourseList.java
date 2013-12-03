package controller;

import golfCourseObjects.GolfCourse;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseList extends SQLQueries implements Runnable
{
	private ArrayList<GolfCourse> courseList;

	public CourseList() 
	{
		super();
	}

	@Override
	public void run()
	{
		synchronized(this)
		{
			super.connect();
			try {
				this.courseList = super.getCourseListFromDB();
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
	 * @return the courseList
	 */
	public ArrayList<GolfCourse> getCourseList() {
		return courseList;
	}

	/**
	 * @param courseList the courseList to set
	 */
	public void setCourseList(ArrayList<GolfCourse> courseList) {
		this.courseList = courseList;
	}

}
