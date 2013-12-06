package com.golfrclient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button goToCL;//Just testing navigation to course screen
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		goToCL = (Button) findViewById(R.id.Main_GoToCourseList);// This can be deleted later
		
		goToCL.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			Intent i = new Intent(MainActivity.this, CourseListScreen.class);
			startActivity(i);
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
