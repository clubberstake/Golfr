package com.golfrclient;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class UserHistoryScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_user_history_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.user_history_screen, menu);
		return true;
	}

}
