package controller;

import java.sql.SQLException;

public class DeleteCourseFromDB extends SQLQueries implements Runnable {

	Integer courseKey;
	public DeleteCourseFromDB() {
		super();
	}
	
	public DeleteCourseFromDB(Integer courseKey)
	{
		super();
		this.courseKey = courseKey;
	}

	@Override
	public void run() 
	{
		synchronized(this)
		{
			super.connect();
			try {
				super.deleteCourseFromDB(courseKey);
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
