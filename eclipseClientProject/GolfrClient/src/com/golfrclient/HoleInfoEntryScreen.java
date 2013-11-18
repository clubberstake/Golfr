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

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hole_info_entry_screen, menu);
		return true;
	}

}
