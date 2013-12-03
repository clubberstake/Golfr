package controller;

import java.sql.SQLException;

public class DeleteGame extends SQLQueries implements Runnable
{
private Integer scorecardID;
	public DeleteGame() {
		super();
	}
	
	public DeleteGame(Integer scorecardID) {
		super();
		this.scorecardID = scorecardID;
	}

	@Override
	public void run() {
		/*synchronized(this)
		{
			super.connect();
			try {
				super.deleteGameFromDB(scorecardID);
			} catch (SQLException e) {
				e.printStackTrace();			
			}
			finally
			{
				this.close();
				notifyAll();
			}
		}*/
		
	}

}
