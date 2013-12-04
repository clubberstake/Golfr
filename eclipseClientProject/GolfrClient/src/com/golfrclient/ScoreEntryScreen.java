package com.golfrclient;

import java.util.ArrayList;

import controller.HoleFetcher;
import controller.MasterController;
import golfCourseObjects.GolfCourse;
import golfCourseObjects.Hole;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ScoreEntryScreen extends Activity {
	private TextView holeNumView;
	private TextView parView;
	private TextView redYardView;
	private TextView whiteYardView;
	private TextView blueYardView;
	private TextView mensHandicapView;
	private TextView womensHandicapView;
	
	private ArrayList<Hole> holeList;

	private EditText scoreEntry;

	private Button nextButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_entry_screen);
		
		/*
		 * Force dummy data into master controller for test
		 */
		MasterController.currentCourse = new GolfCourse(null, null, null, null, null, null, null, 1);
		
		if(MasterController.currentHoleNum == null)
		{
			MasterController.currentHoleNum = 0;
		}
		

		holeNumView = (TextView) findViewById(R.id.ScoreEntryScreen_HoleNumView);
		

		parView = (TextView) findViewById(R.id.ScoreEntryScreen_ParView);
		redYardView = (TextView) findViewById(R.id.ScoreEntryScreen_RedYardView);
		whiteYardView = (TextView) findViewById(R.id.ScoreEntryScreen_WhiteYardView);
		blueYardView = (TextView) findViewById(R.id.ScoreEntryScreen_BlueYardView);
		mensHandicapView = (TextView) findViewById(R.id.ScoreEntryScreen_MensHandicapView);
		womensHandicapView = (TextView) findViewById(R.id.ScoreEntryScreen_WomensHandicapView);

		scoreEntry = (EditText) findViewById(R.id.ScoreEntryScreen_ScoreEntryField);
		nextButton = (Button) findViewById(R.id.ScoreEntryScreen_NextButton);
		
		new FetchHolesTask().execute();
		
		/*
		 * Establish behavior for Next button
		 */
		
		nextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score_entry_screen, menu);
		return true;
	}
	
	private class FetchHolesTask extends AsyncTask<Void, Void, ArrayList<Hole>>
	{
	

		@Override
		protected ArrayList<Hole> doInBackground(Void... params) {
			try{
				return new HoleFetcher().getHoleList(MasterController.currentCourse);
			}
			catch (Exception e){
				e.printStackTrace();
			}
			return null;
		}
		
	
		@Override
		protected void onPostExecute(ArrayList<Hole> holes) {
			holeList = holes;
			populateHoleDataToScreen();			
		}
		
	}
	
	
	/**
	 * Populates all the text views with relevant data from hole
	 */
	private void populateHoleDataToScreen()
	{
		holeNumView.setText("Hole #: " + holeList.get(MasterController.currentHoleNum).getHoleNumber().toString());
		parView.setText("Par: " + holeList.get(MasterController.currentHoleNum).getPar().toString());
		redYardView.setText("Red tee: " + holeList.get(MasterController.currentHoleNum).getRedTeeYardage().toString());
		whiteYardView.setText("White tee: " + holeList.get(MasterController.currentHoleNum).getWhiteTeeYargage().toString());
		blueYardView.setText("Blue tee: " + holeList.get(MasterController.currentHoleNum).getBlueTeeYardage().toString());
		mensHandicapView.setText("Handicap: " + holeList.get(MasterController.currentHoleNum).getHandicap().toString());
	}

}
