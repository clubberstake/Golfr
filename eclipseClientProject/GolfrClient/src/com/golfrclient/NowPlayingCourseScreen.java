package com.golfrclient;

import golfCourseObjects.Game;
import golfCourseObjects.GolfCourse;
import controller.MasterController;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

public class NowPlayingCourseScreen extends Activity {
	
	private ListView currentlyPlayingListView;
	private Button newGameButton;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_now_playing_course_screen);
		
		currentlyPlayingListView = (ListView) findViewById(R.id.NowPlayingCourseScreen_CurrentlyPlayingListView);
		newGameButton = (Button) findViewById(R.id.NowPlayingCourseScreen_NewGameButton);
		
		newGameButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				MasterController.game = new Game(null, MasterController.currentCourse, null, null);
				
				
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.now_playing_course_screen, menu);
		return true;
	}

}
