package com.golfrclient;

import golfCourseObjects.GolfCourse;
import golfCourseObjects.Hole;

import java.sql.SQLException;
import java.util.ArrayList;

import controller.HoleFetcher;

import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HoleSelectionScreen extends Activity {

	private ListView holeListView;
	private ArrayList<String> holeNameList = new ArrayList<String>();
	private ArrayList<Hole> holeList;
	//private GolfCourse testCourse = new GolfCourse();
	
	
	/*
	 * gets the hole list from an Async Thread.
	 * Needs a golfcourse object passed in
	 */
	private class FetchHolesTask extends AsyncTask<Void, Void, ArrayList<Hole>>
	{
	

		@Override
		protected ArrayList<Hole> doInBackground(Void... params) {
			try{
				
				
				//HoleFetcher fetcher = new HoleFetcher(testCourse);
				return new HoleFetcher().getHoleList(new GolfCourse(null, null, null, null, null, null, null, 1));
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
	
		@Override
		protected void onPostExecute(ArrayList<Hole> holes) {
			holeList = holes;
			establishArrayAdapter();
			
		}
		
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hole_selection_screen);
		//testCourse.setGolfCourseID(1);
		holeListView = (ListView) findViewById(R.id.HoleListView);
		
		new FetchHolesTask().execute();
		
		//populateHoleList();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hole_selection_screen, menu);
		return true;
	}
	
	/*
	 * Fills the array of holes for the hole list
	 * This might need to be refactored when it is possible to connect to DB
	 */
	
	private void holeListToHoleNameList()
	{
		if (holeList.isEmpty()==true)
		{
			throw new IllegalStateException("Hole list is NULL. It shouldn't be");
		}
		else{
			for (Hole i : holeList)
			{
				holeNameList.add(i.getHoleNumber().toString());
			}
		}
	}
	private void populateHoleList()
	{
		for(int x = 1; x <=18; x++)
		{
			holeNameList.add("Hole "+x);
		}
	}
	
	private void establishArrayAdapter(){
		holeListToHoleNameList();
		ArrayAdapter<String> holeListArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,holeNameList);
		holeListView.setAdapter(holeListArrayAdapter);
	}
	
	

}
