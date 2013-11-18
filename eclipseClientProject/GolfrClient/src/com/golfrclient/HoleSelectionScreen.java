package com.golfrclient;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class HoleSelectionScreen extends Activity {

	private ListView holeListView;
	private ArrayList<String> holeList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hole_selection_screen);
		
		holeListView = (ListView) findViewById(R.id.HoleListView);
		
		/*
		 * Populate list of holes
		 * <<THIS IS JUST DUMMY DATA NOW, WIRE UP WHEN DB IS AVAILABLE>>>
		 */
		holeList = new ArrayList<String>();
		populateHoleList();
		ArrayAdapter<String> holeListArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,holeList);
		holeListView.setAdapter(holeListArrayAdapter);
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
	private void populateHoleList()
	{
		for(int x = 1; x <=18; x++)
		{
			holeList.add("Hole "+x);
		}
	}
	
	

}
