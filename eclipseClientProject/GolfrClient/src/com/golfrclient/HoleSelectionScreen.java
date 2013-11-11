package com.golfrclient;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class HoleSelectionScreen extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_hole_selection_screen);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.hole_selection_screen, menu);
		return true;
	}

}
