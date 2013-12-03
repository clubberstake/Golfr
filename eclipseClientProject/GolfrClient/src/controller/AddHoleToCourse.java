package controller;

import golfCourseObjects.GolfCourse;
import golfCourseObjects.Hole;

import java.sql.SQLException;

public class AddHoleToCourse extends SQLQueries implements Runnable {

	private Integer courseKey;
	private Hole toAdd;
	
	public AddHoleToCourse() 
	{
		super();
	}
	
	public AddHoleToCourse(Integer courseKey, Hole toAdd)
	{
		super();
		this.courseKey = courseKey;
		this.toAdd = toAdd;
	}

	@Override
	public void run() 
	{
		synchronized(this)
		{
			super.connect();
			try {
				super.addHoleToCourseInDB(courseKey, toAdd);
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

}
