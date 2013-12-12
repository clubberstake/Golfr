package com.golfrclient;

import golfCourseObjects.Hole;
import controller.AddHoleToCourse;
import controller.MasterController;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class HoleInfoEntryScreen extends Activity {

	private EditText whiteTeeYardEntry;
	private EditText redTeeYardEntry;
	private EditText blueTeeYardEntry;
	private EditText parEntry;
	private EditText handicapEntry;
	private Button nextButton;
	private TextView holeCounterView;
	private Hole newHole;
	private Button testButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hole_info_entry_screen);

		whiteTeeYardEntry = (EditText) findViewById(R.id.HoleInfoScreen_WhiteTeeEntry);
		redTeeYardEntry = (EditText) findViewById(R.id.HoleInfoScreen_RedTeeEntry);
		blueTeeYardEntry = (EditText) findViewById(R.id.HoleInfoScreen_BlueTeeEntry);
		parEntry = (EditText) findViewById(R.id.HoleInfoScreen_ParEntry);
		handicapEntry = (EditText) findViewById(R.id.HoleInfoScreen_HandicapEntry);
		nextButton = (Button) findViewById(R.id.HoleInfoScreen_NextButton);
		holeCounterView = (TextView) findViewById(R.id.HoleInfoScreen_HoleCounterView);
		testButton = (Button) findViewById(R.id.HoleInfoScreen_testButton);
		holeCounterView.setText("Hole: " + MasterController.currentHoleNum);
		
		
		testButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				createTestHole();
				new SendHoleInfoToDBTask().execute();
				MasterController.currentHoleNum++;
				if (MasterController.currentHoleNum > 18) {
					MasterController.currentHoleNum = 1;
					Intent i = new Intent(HoleInfoEntryScreen.this,
							CourseListScreen.class);
					startActivity(i);
				} else {
					// clear all entry fields
					whiteTeeYardEntry.setText(null);
					redTeeYardEntry.setText(null);
					blueTeeYardEntry.setText(null);
					parEntry.setText(null);
					handicapEntry.setText(null);
					holeCounterView.setText("Hole: "
							+ MasterController.currentHoleNum);
				}

			}
		});
		
		
		nextButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				createHoleFromParams();
				new SendHoleInfoToDBTask().execute();
				MasterController.currentHoleNum++;
				if (MasterController.currentHoleNum > 18) {
					MasterController.currentHoleNum = 1;
					Intent i = new Intent(HoleInfoEntryScreen.this,
							CourseListScreen.class);
					startActivity(i);
				} else {
					// clear all entry fields
					whiteTeeYardEntry.setText(null);
					redTeeYardEntry.setText(null);
					blueTeeYardEntry.setText(null);
					parEntry.setText(null);
					handicapEntry.setText(null);
					holeCounterView.setText("Hole: "
							+ MasterController.currentHoleNum);
				}

			}
		});

		/*
		 * MAG: once you get all of the paramaters from the user, you need to
		 * run the following code to add the hole to the DB
		 * 
		 * 
		 * Hole toAdd = new Hole(paramaters from the user); AddHoleToCourse
		 * holeAdder = new
		 * AddHoleToCourse(coursePrimaryKeyThatYouSavedInTheCourseInfoEntryScreen
		 * , toAdd); Thread t = new Thread(holeAdder); t.start();
		 * 
		 * synchronized(t) {
		 * 
		 * try { t.wait(500); } catch (InterruptedException e) { // TODO
		 * Auto-generated catch block e.printStackTrace(); } }
		 */

	}

	private class SendHoleInfoToDBTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected Void doInBackground(Void... params) {
			AddHoleToCourse controller = new AddHoleToCourse(
					MasterController.currentCourse.getGolfCourseID(), newHole);
			controller.run();
			return null;
		}

	}

	private void createHoleFromParams() {
		Integer whiteTee = Integer.parseInt(whiteTeeYardEntry.getText()
				.toString());
		Integer redTee = Integer.parseInt(redTeeYardEntry.getText().toString());
		Integer blueTee = Integer.parseInt(blueTeeYardEntry.getText()
				.toString());
		Integer par = Integer.parseInt(parEntry.getText().toString());
		Integer handicap = Integer.parseInt(handicapEntry.getText().toString());

		newHole = new Hole(null,
				MasterController.currentCourse.getGolfCourseID(),
				MasterController.currentHoleNum, whiteTee, redTee, blueTee,
				handicap, par, null);
	}
	
	private void createTestHole(){
		newHole = new Hole(null,
				MasterController.currentCourse.getGolfCourseID(),
				MasterController.currentHoleNum, 255, 123, 365,
				1, 2, null);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hole_info_entry_screen, menu);
		return true;
	}

}
