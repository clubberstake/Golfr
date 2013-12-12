package controller;

import java.sql.SQLException;

/**
 * This Thread deletes a current or prior Game from the DB.  It is meant for testing purposes so that the DB
 * can be kept "clean" of junk test records.
 * @author MAG
 *
 */
public class DeleteGameFromDB extends SQLQueries implements Runnable {

	private Integer scoreHistory_pk = null;
	
	public DeleteGameFromDB() {
		super();
	}
	
	public DeleteGameFromDB(Integer scoreHistory_pk)
	{
		super();
		this.scoreHistory_pk = scoreHistory_pk;
	}

	@Override
	public void run() {
		synchronized(this)
		{
			super.connect();
			try 
			{
				if(scoreHistory_pk != null)
				 super.deleteGameFromDB(scoreHistory_pk);
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
