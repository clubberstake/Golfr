package controller;

import golfCourseObjects.GolfCourse;

import java.sql.SQLException;
import java.util.ArrayList;

public class CourseListFetcher extends SQLQueries {
	private ArrayList<GolfCourse> courseList;

	public CourseListFetcher() {
		super();
	}

	private void populateCourseListFromDB() {
		super.connect();
		try {
			this.courseList = super.getCourseListFromDB();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();
		}
	}
	
	public ArrayList<GolfCourse> getCourseList()
	{
		populateCourseListFromDB();
		return courseList;
	}

}
