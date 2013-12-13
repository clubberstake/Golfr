package com.golfrclient;

import golfCourseObjects.Hole;

import java.util.ArrayList;

import controller.GetScorecard;
import controller.MasterController;
import android.os.AsyncTask;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ScoreCardScreen extends Activity {
	 private ListView scoreCardView;
     private ArrayList<Hole> scoreList;

     private class FetchScorecardClass extends
                     AsyncTask<Void, Void, ArrayList<Hole>> {

             @Override
             protected ArrayList<Hole> doInBackground(Void... params) {
                     try {
                             GetScorecard controller = new GetScorecard(
                                             MasterController.game);
                             controller.run();
                             return controller.getScorecardList();
                     } catch (Exception e) {
                             e.printStackTrace();
                     }
                     return null;
             }
             
             @Override
             protected void onPostExecute(ArrayList<Hole> scoreCardIn)
             {
             scoreList = scoreCardIn;
             establishArrayAdapter();
             }

     }
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_card_screen);
		
		scoreCardView = (ListView) findViewById(R.id.HoleListView);
        
        new FetchScorecardClass().execute();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score_card_screen, menu);
		return true;
	}

	/**
	 * Back button returns to Now Playing screen
	 * @author Bolong
	 * 
	 */
	@Override  
    public void onBackPressed()  
    {  
		Intent i = new Intent(this,NowPlayingCourseScreen.class);			
		startActivity(i);
		finish();
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
	 
	 /*
      * sets up an array adapter to populate the scorecard list view
      */
     private void establishArrayAdapter(){
             ArrayAdapter<Hole> scoreListArrayAdapeter = new ArrayAdapter<Hole>(this,android.R.layout.simple_list_item_1,scoreList);
             scoreCardView.setAdapter(scoreListArrayAdapeter);
             }
}
