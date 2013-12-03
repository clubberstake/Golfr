package com.golfrclient;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.Button;
import android.widget.EditText;

public class HoleInfoEntryScreen extends Activity {

	private EditText whiteTeeYardEntry;
	private EditText redTeeYardEntry;
	private EditText blueTeeYardEntry;
	private EditText parEntry;
	private Button nextButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hole_info_entry_screen);

		whiteTeeYardEntry = (EditText) findViewById(R.id.HoleInfoScreen_WhiteTeeEntry);
		redTeeYardEntry = (EditText) findViewById(R.id.HoleInfoScreen_RedTeeEntry);
		blueTeeYardEntry = (EditText) findViewById(R.id.HoleInfoScreen_BlueTeeEntry);
		parEntry = (EditText) findViewById(R.id.HoleInfoScreen_ParEntry);
		nextButton = (Button) findViewById(R.id.HoleInfoScreen_NextButton);

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
