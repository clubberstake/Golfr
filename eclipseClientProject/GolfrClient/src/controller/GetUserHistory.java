package controller;

import golfCourseObjects.HistoryObject;
import golfCourseObjects.User;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;

public class GetUserHistory extends SQLQueries implements Runnable 
{

	ArrayList<HistoryObject> historyList = null;
	User user = null;
	
	public GetUserHistory() 
	{
		super();
	}
	
	public GetUserHistory(User user) 
	{
		super();
		this.user = user;
	}
	
	/**
	 * @return the historyList
	 */
	public ArrayList<HistoryObject> getHistoryList() {
		return historyList;
	}

	/**
	 * @param historyList the historyList to set
	 */
	public void setHistoryList(ArrayList<HistoryObject> historyList) {
		this.historyList = historyList;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public void run()
	{
		synchronized(this)
		{
			super.connect();
			try {
				this.historyList = super.getUserHistory(this.user);
				Collections.sort(historyList);
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
