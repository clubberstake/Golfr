package com.golfrclient;

import golfCourseObjects.Game;
import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import controller.MasterController;
import controller.NewGame;

public class NowPlayingCourseScreen extends Activity {
	
	private ListView currentlyPlayingListView;
	private Button newGameButton;
	
	private class SendNewGameTask extends AsyncTask<Void, Void, Game>{

		@Override
		protected Game doInBackground(Void... params) {
			NewGame controller = new NewGame(MasterController.user, MasterController.currentCourse);
			controller.run();
			
			return controller.getGame();
		}
		
		@Override
		protected void onPostExecute(Game gameIn)
		{
			MasterController.game = gameIn;
			MasterController.currentHoleNum =null;
			Intent i = new Intent(NowPlayingCourseScreen.this, HoleSelectionScreen.class);
			startActivity(i);
		}
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_now_playing_course_screen);
		
		currentlyPlayingListView = (ListView) findViewById(R.id.NowPlayingCourseScreen_CurrentlyPlayingListView);
		newGameButton = (Button) findViewById(R.id.NowPlayingCourseScreen_NewGameButton);
		
		newGameButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				//MasterController.game = new Game(MasterController.user, MasterController.currentCourse, null, null);
				//Intent i = new Intent(NowPlayingCourseScreen.this, HoleSelectionScreen.class);
				//startActivity(i);
				new SendNewGameTask().execute();
				
				
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
