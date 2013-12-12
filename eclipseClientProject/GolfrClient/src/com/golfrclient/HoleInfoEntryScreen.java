package com.golfrclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import controller.MasterController;

public class HoleInfoEntryScreen extends Activity {

	private EditText whiteTeeYardEntry;
	private EditText redTeeYardEntry;
	private EditText blueTeeYardEntry;
	private EditText parEntry;
	private Button nextButton;
	private TextView holeCounterView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hole_info_entry_screen);

		whiteTeeYardEntry = (EditText) findViewById(R.id.HoleInfoScreen_WhiteTeeEntry);
		redTeeYardEntry = (EditText) findViewById(R.id.HoleInfoScreen_RedTeeEntry);
		blueTeeYardEntry = (EditText) findViewById(R.id.HoleInfoScreen_BlueTeeEntry);
		parEntry = (EditText) findViewById(R.id.HoleInfoScreen_ParEntry);
		nextButton = (Button) findViewById(R.id.HoleInfoScreen_NextButton);
		holeCounterView = (TextView) findViewById(R.id.HoleInfoScreen_HoleCounterView);
		
		holeCounterView.setText("Hole: " + MasterController.currentHoleNum);
		
		
		nextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MasterController.currentHoleNum ++;
				if(MasterController.currentHoleNum > 18)
				{
					MasterController.currentHoleNum = 1;
					Intent i = new Intent(HoleInfoEntryScreen.this, ScoreEntryScreen.class);
					startActivity(i);
				}
				else{
					// clear all entry fields
					whiteTeeYardEntry.setText(null);
					redTeeYardEntry.setText(null);
					blueTeeYardEntry.setText(null);
					parEntry.setText(null);
					holeCounterView.setText("Hole: " + MasterController.currentHoleNum);
				}
			}
		});
		
		
		
		/*
		 * MAG: once you get all of the paramaters from the user, you need to run the following code to add the hole to the DB
		 * 
		 * 
		 Hole toAdd = new Hole(paramaters from the user);
		 AddHoleToCourse holeAdder = new AddHoleToCourse(coursePrimaryKeyThatYouSavedInTheCourseInfoEntryScreen, toAdd);
		 Thread t = new Thread(holeAdder);
		t.start();
				
		synchronized(t)
		{
			
			try {
				t.wait(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 */
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hole_info_entry_screen, menu);
		return true;
	}

}
