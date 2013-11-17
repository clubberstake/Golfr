package com.golfrclient;

import java.util.ArrayList;

import controller.AddCourse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;

public class CourseInfoEntryScreen extends Activity {

	private AddCourse controller;
	private EditText courseNameEntry;
	private EditText courseStreetNum;
	private EditText courseStreetName;
	private EditText courseCityEntry;
	private ExpandableListView courseStateList;
	private EditText coursePostalCodeEntry;
	private EditText coursePhoneNumberEntry;
	private EditText courseWebsiteEntry;
	private Button nextButton;
	private ArrayList<String> stateList;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_couse_info_entry_screen);
		
		
		
		courseNameEntry = (EditText) findViewById(R.id.CourseInfo_NameEntry);
		courseStreetNum = (EditText) findViewById(R.id.CourseInfo_StreetNum);
		courseStreetName = (EditText) findViewById(R.id.CourseInfo_StreetName);
		
		courseCityEntry = (EditText) findViewById(R.id.CourseInfo_CityEntry);
		courseStateList = (ExpandableListView) findViewById(R.id.CourseInfo_StateExpandable);
		coursePostalCodeEntry = (EditText) findViewById(R.id.CourseInfo_PostalCodeEntry);
		coursePhoneNumberEntry = (EditText) findViewById(R.id.CourseInfo_PhoneNumEntry);
		courseWebsiteEntry = (EditText) findViewById(R.id.CourseInfo_WebsiteEntry);
		nextButton = (Button) findViewById(R.id.CourseInfo_NextButton);
		
		nextButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				String courseName = courseNameEntry.getText().toString();
				String courseAddress = courseStreetNum.getText().toString();
				String courseAddress2 = courseStreetName.getText().toString();
				String courseCity = courseCityEntry.getText().toString();
				String courseState = "PA"; // <-- FINISH THIS IMPLEMENTATION!!
				String coursePostalCode = coursePostalCodeEntry.getText().toString();
				String coursePhoneNumber = coursePhoneNumberEntry.getText().toString();
				String courseWebsite = courseWebsiteEntry.getText().toString();
				
				
				
				
				Intent i = new Intent(CourseInfoEntryScreen.this, HoleInfoEntryScreen.class);
				startActivity(i);
			}
		});
		
		
	
		
		/*
		 * Add states to the courseStateList
		 * FININISH THIS IMPLEMENTATION
		 */
		
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.couse_info_entry_screen, menu);
		return true;
	}
	
	/**
	 * Invokes the controller when the "next" button is clicked and sends info to db.
	 * @param view
	 */
	
	
	
	private void fillStateList()
	{
		stateList.add("AL");
		stateList.add("AK");
		stateList.add("AZ");
		stateList.add("AR");
		stateList.add("CA");
		stateList.add("CO");
		stateList.add("CT");
		stateList.add("DE");
		stateList.add("DC");
		stateList.add("FL");
		stateList.add("GA");
		stateList.add("HI");
		stateList.add("ID");
		stateList.add("IL");
		stateList.add("IN");
		stateList.add("IA");
		stateList.add("KS");
		stateList.add("KY");
		stateList.add("LA");
		stateList.add("ME");
		stateList.add("MD");
		stateList.add("MA");
		stateList.add("MI");
		stateList.add("MN");
		stateList.add("MS");
		stateList.add("MO");
		stateList.add("MT");
		stateList.add("NE");
		stateList.add("NV");
		stateList.add("NH");
		stateList.add("NJ");
		stateList.add("NM");
		stateList.add("NY");
		stateList.add("NC");
		stateList.add("ND");
		stateList.add("OH");
		stateList.add("OK");
		stateList.add("OR");
		stateList.add("PA");
		stateList.add("RI");
		stateList.add("SC");
		stateList.add("SD");
		stateList.add("TN");
		stateList.add("TX");
		stateList.add("UT");
		stateList.add("VT");
		stateList.add("VA");
		stateList.add("WA");
		stateList.add("WV");
		stateList.add("WI");
		stateList.add("WY");
	}

}
