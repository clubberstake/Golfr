package com.golfrclient;

import golfCourseObjects.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import controller.MasterController;

public class MainActivity extends Activity {

	private Button goToCL;//Just testing navigation to course screen
	private Button login;
	private EditText usernameEntry;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		login = (Button) findViewById(R.id.Main_LoginButton);
		usernameEntry = (EditText) findViewById(R.id.Main_UsernameEntry);
		
		/*
		 * Action listener for login button
		 */
		login.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//Navigate to the Main menu screen
				MasterController.user = new User(usernameEntry.getText().toString());
				Intent i = new Intent(MainActivity.this, MainMenuScreen.class);
				startActivity(i);
			}
		});
		
		
		
		goToCL = (Button) findViewById(R.id.Main_GoToCourseList);// This can be deleted later
		
		goToCL.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			Intent i = new Intent(MainActivity.this, CourseListScreen.class);
			startActivity(i);
				
			}
		});
	}

	/**
	 * create logout menu
	 * @author Bolong
	 * 
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}