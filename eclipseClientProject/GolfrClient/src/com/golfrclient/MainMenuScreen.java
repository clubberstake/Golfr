package com.golfrclient;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainMenuScreen extends Activity {
	private Button courseListButton;
	private Button historyButton;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_menu_screen);
		
		courseListButton = (Button) findViewById(R.id.CourseListButton);
		historyButton = (Button) findViewById(R.id.HistoryButton);
		/*
		courseListButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
			}
		}}
	*/
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu_screen, menu);
		return true;
	}

}
