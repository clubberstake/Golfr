package com.golfrclient;

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
	
	private EditText scoreEntry;
	
	private Button nextButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_entry_screen);
		
		holeNumView = (TextView) findViewById(R.id.ScoreEntryScreen_HoleNumView);
		holeNumView.setText("Look a Hole Num");// <-- get the hole num from the controller
		
		parView = (TextView) findViewById(R.id.ScoreEntryScreen_ParView);
		
		redYardView = (TextView) findViewById(R.id.ScoreEntryScreen_RedYardView);
		
		whiteYardView = (TextView) findViewById(R.id.ScoreEntryScreen_WhiteYardView);
		
		blueYardView = (TextView) findViewById(R.id.ScoreEntryScreen_BlueYardView);
		
		mensHandicapView = (TextView) findViewById(R.id.ScoreEntryScreen_MensHandicapView);
		
		womensHandicapView = (TextView) findViewById(R.id.ScoreEntryScreen_WomensHandicapView);
		
		scoreEntry = (EditText) findViewById(R.id.ScoreEntryScreen_NextButton);
		nextButton = (Button) findViewById(R.id.ScoreEntryScreen_NextButton);
		
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

}
