package com.golfrclient;

import golfCourseObjects.GolfCourse;

import java.util.ArrayList;

import controller.SelectACourse;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CourseListScreen extends Activity {

	private SelectACourse controller;
	private ListView courseListView;
	private Button addCourseButton;
	private ArrayList<GolfCourse> golfCourseList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_list_screen);

		controller = new SelectACourse();

		courseListView = (ListView) findViewById(R.id.CourseListScreen_CourseList);
		addCourseButton = (Button) findViewById(R.id.CourseListScreen_AddCourseButton);
		
		/*
		 * Populate the list of courses
		 *  <<< THIS JUST USES DUMMY INFO RIGHT NOW, WIRE UP TO CONTROLLER >>>
		 */
		ArrayList<String> courseNameList = new ArrayList<String>();
		courseNameList.add("Oakmont");
		courseNameList.add("Nevilewood");
		ArrayAdapter<String> courseNamesArrayAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1, courseNameList);
		//golfCourseList = controller.getCourseList(); <-- THROWS ERRORS!!
		//ArrayAdapter<GolfCourse> courseNamesArrayAdapter = new ArrayAdapter<GolfCourse>(this,android.R.layout.simple_list_item_1, golfCourseList);
		courseListView.setAdapter(courseNamesArrayAdapter);
		
		/*
		 * Button to navigate to course creation screen
		 */
		addCourseButton.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				//<<< INVOKE CONTROLLER HERE>>>
				Intent i = new Intent(CourseListScreen.this, CourseInfoEntryScreen.class);
				startActivity(i);
				
			}
		});
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.course_list_screen, menu);
		return true;
	}

}
