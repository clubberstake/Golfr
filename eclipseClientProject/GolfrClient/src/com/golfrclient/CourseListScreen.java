package com.golfrclient;

import java.util.ArrayList;

import controller.SelectACourse;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class CourseListScreen extends Activity {

	private SelectACourse controller;
	private ListView courseListView;
	private Button addCourseButton;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_list_screen);

		controller = new SelectACourse();

		courseListView = (ListView) findViewById(R.id.CourseListScreen_CourseList);
		addCourseButton = (Button) findViewById(R.id.CourseListScreen_AddCourseButton);

		ArrayList<String> courseNameList = new ArrayList<String>();
		courseNameList.add("Oakmont");
		courseNameList.add("Nevilewood");
		ArrayAdapter<String> courseNamesArrayAdapter = new ArrayAdapter<String>(CourseListScreen.this,
				R.layout.activity_course_list_screen, courseNameList);
		courseListView.setAdapter(courseNamesArrayAdapter);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.course_list_screen, menu);
		return true;
	}

}
