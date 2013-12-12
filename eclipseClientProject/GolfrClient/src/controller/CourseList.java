package controller;

import golfCourseObjects.GolfCourse;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * This Thread runs the operation to get the list of available GolfCourses from the DB.
 * 
 * The course list is stored as an ArrayList<GolfCourse>, and after execution of the thread, it can be retrieved
 * by this.getCourseList()
 * @author MAG
 *
 */
public class CourseList extends SQLQueries implements Runnable
{
	private ArrayList<GolfCourse> courseList;

	/**
	 * Default constructor
	 */
	public CourseList() 
	{
		super();
	}

	/**
	 * Override of Thread.run() which executes the logic to get the list of GolfCourses from the DB.
	 * 
	 * Note:  the resulting course list can be retrieved only after a this.run() or this.start() call.  this.courseList
	 * can be retrieved by this.getCourseList().
	 */
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
