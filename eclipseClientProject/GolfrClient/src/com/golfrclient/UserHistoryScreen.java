package com.golfrclient;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;

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
