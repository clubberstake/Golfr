package com.golfrclient;

import golfCourseObjects.GolfCourse;
import golfCourseObjects.HistoryObject;

import java.util.ArrayList;

import controller.GetUserHistory;
import controller.MasterController;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class UserHistoryScreen extends Activity {
	
	private ListView historyListView;
	private ArrayList<HistoryObject> historyList;
	private ArrayList<String> dummyList = new ArrayList<String>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_history_screen);
		historyListView = (ListView) findViewById(R.id.UserHistoryScreen_HistoryListView);
		establishDummyAdapter();
		//new FetchHistoryFromDBTask().execute(); <- returns an empty array for some reason
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_history_screen, menu);
		return true;
	}

	/**
	 * when press logout,return to the login menu
	 * @author Bolong
	 */
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case R.id.action_settings:
	    			Intent i = new Intent(this,MainActivity.class);
	    			startActivity(i);
	                break; 
	        }
	        // TODO Auto-generated method stub
	        return super.onOptionsItemSelected(item);
	    }
	 
	 private void establishDummyAdapter()
	 {
		 dummyList.add("12/02/2013 - Pine Creek - Total Score: 72");
		 dummyList.add("Oakmont: - Total Score: 105");
		 dummyList.add("St. Andrew's - Total Score: 115");
		 
		 ArrayAdapter<String> dummyAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dummyList);
		 historyListView.setAdapter(dummyAdapter);
	 }
	 
	 private void establishArrayAdapter()
	 {
		 ArrayAdapter<HistoryObject> historyArrayAdapter = new ArrayAdapter<HistoryObject>(
					this, android.R.layout.simple_list_item_1, historyList);
			historyListView.setAdapter(historyArrayAdapter);
	 }
	 
	 private class FetchHistoryFromDBTask extends AsyncTask<Void, Void, ArrayList<HistoryObject>>
	 {

		@Override
		protected ArrayList<HistoryObject> doInBackground(Void... params) {
			GetUserHistory controller = new GetUserHistory(MasterController.user);
			controller.run();
			return controller.getHistoryList();
		}
		
		@Override
		protected void onPostExecute(ArrayList<HistoryObject> historyIn)
		{
			historyList = historyIn;
			establishArrayAdapter();
		}
		 
	 }
}
