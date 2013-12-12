package com.golfrclient;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
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
		 * on click listener to navigate to course list screen
		 */
		courseListButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainMenuScreen.this, CourseListScreen.class);
				startActivity(i);
				
			}
		});
		
		
		/*
		 * on click listener to navigate to history screen
		 */
		historyButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(MainMenuScreen.this, UserHistoryScreen.class);
				startActivity(i);
				
			}
		});
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main_menu_screen, menu);
		return true;
	}

	/**
	 * when press logout,return to the login menu
	 * @author Bolong
	 */
	 @Override
	    public boolean onOptionsItemSelected(MenuItem item) {
	        switch (item.getItemId()) {
	            case R.id.action_settings:
	    			Intent i = new Intent(this,MainActivity.class);
	    			startActivity(i);
	                break; 
	        }
	        // TODO Auto-generated method stub
	        return super.onOptionsItemSelected(item);
	    }	
}
