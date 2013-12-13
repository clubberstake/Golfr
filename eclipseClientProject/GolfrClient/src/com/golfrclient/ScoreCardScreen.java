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
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ScoreCardScreen extends Activity {
	 private ListView scoreCardView;
     private ArrayList<Hole> scoreList;
     private ArrayList<String> dummyScoreList;
     private Button doneButton;
     
     /**
      * Async Task that gets scorecard information from the DB on a thread
      * @author Andrew
      *
      */
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
		
		scoreCardView = (ListView) findViewById(R.id.ScoreCardListView);
		doneButton = (Button) findViewById(R.id.ScoreCardScreen_DoneButton);
		
		/*
		 * Set the listener for the done button
		 */
		doneButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent i = new Intent(ScoreCardScreen.this, MainMenuScreen.class);
				startActivity(i);
				
			}
		});
        
        new FetchScorecardClass().execute(); //Run the thread that gets scorecard info from DB
		
		/*
		 * fill list view with dummy scorecard data
		 */
	//	createDummyDataAdapter();
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
	 
	 /**
      * sets up an array adapter to populate the scorecard list view
      */
     private void establishArrayAdapter(){
             ArrayAdapter<Hole> scoreListArrayAdapeter = new ArrayAdapter<Hole>(this,android.R.layout.simple_list_item_1,scoreList);
             scoreCardView.setAdapter(scoreListArrayAdapeter);
             }
     
     /**
      * Fills an arraylist with dummy score data for testing
      */
     private void createDummyDataAdapter()
     {
    	 dummyScoreList.add("Pine Springs | Total Score: 89");
    	 dummyScoreList.add("Hole 1 | Strokes: 5 | Par: 3");
    	 dummyScoreList.add("Hole 2 | Strokes: 4 | Par: 4");
    	 dummyScoreList.add("Hole 3 | Strokes: 6 | Par: 5");
    	 dummyScoreList.add("Hole 4 | Strokes: 7 | Par: 3");
    	 dummyScoreList.add("Hole 5 | Strokes: 3 | Par: 2");
    	 dummyScoreList.add("Hole 6 | Strokes: 5 | Par: 5");
    	 dummyScoreList.add("Hole 7 | Strokes: 7 | Par: 4");
    	 dummyScoreList.add("Hole 8 | Strokes: 4 | Par: 3");
    	 dummyScoreList.add("Hole 9 | Strokes: 5 | Par: 5");
    	 dummyScoreList.add("Hole 10 | Strokes: 6 | Par: 4");
    	 dummyScoreList.add("Hole 11 | Strokes: 2 | Par: 3");
    	 dummyScoreList.add("Hole 12 | Strokes: 6 | Par: 4");
    	 dummyScoreList.add("Hole 13 | Strokes: 4 | Par: 6");
    	 dummyScoreList.add("Hole 14 | Strokes: 3 | Par: 3");
    	 dummyScoreList.add("Hole 15 | Strokes: 6 | Par: 4");
    	 dummyScoreList.add("Hole 16 | Strokes: 7 | Par: 5");
    	 dummyScoreList.add("Hole 17 | Strokes: 5 | Par: 5");
    	 dummyScoreList.add("Hole 18 | Strokes: 4 | Par: 4");
    	 
    	 ArrayAdapter<String> dummyListArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,dummyScoreList);
    	 scoreCardView.setAdapter(dummyListArrayAdapter);
     }
}
