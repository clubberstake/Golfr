package controller;

import golfCourseObjects.Hole;

import java.sql.SQLException;

/**
 * This class is a Thread which calls SQLQueries.addHoleToCourseInDB() method.
 * @author MAG
 *
 */
public class AddHoleToCourse extends SQLQueries implements Runnable {

	private Integer courseKey;
	private Hole toAdd;
	
	/**
	 * Default constructor--not for typical use
	 */
	public AddHoleToCourse() 
	{
		super();
	}
	
	/**
	 * Full constructor
	 * @param courseKey - the primary key of the GolfCourse to which you want to add a Hole
	 * @param toAdd - the Hole which you want to add to the golf course.
	 */
	public AddHoleToCourse(Integer courseKey, Hole toAdd)
	{
		super();
		this.courseKey = courseKey;
		this.toAdd = toAdd;
	}

	/**
	 * Override of Thread.run().  Contains the code for synchronized threading of the operation needed
	 * to add a Hole to a Course.  
	 * 
	 *  Note: this.courseKey and this.toAdd must be set correctly for this thread to execute correctly.
	 */
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
