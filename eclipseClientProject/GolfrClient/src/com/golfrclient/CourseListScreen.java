package com.golfrclient;

import golfCourseObjects.GolfCourse;

import java.util.ArrayList;

import controller.CourseList;
import controller.CourseListFetcher;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import arrayAdapters.CourseListAdapter;

public class CourseListScreen extends Activity {

	private CourseList courseList;
	private ListView courseListView;
	private Button addCourseButton;
	private ArrayList<GolfCourse> golfCourseList;

	private class FetchCourseTask extends
			AsyncTask<Void, Void, ArrayList<GolfCourse>> {

		@Override
		protected ArrayList<GolfCourse> doInBackground(Void... params) {
			try {
				return new CourseListFetcher().getCourseList();
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		}

		@Override
		protected void onPostExecute(ArrayList<GolfCourse> courses) {
			golfCourseList = courses;
			establishArrayAdapter();
		}

	}

	@Override
	protected synchronized void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_course_list_screen);

		courseListView = (ListView) findViewById(R.id.CourseListScreen_CourseList);
		addCourseButton = (Button) findViewById(R.id.CourseListScreen_AddCourseButton);

		new FetchCourseTask().execute();

		/*
		 * ArrayList<String> courseNameList = new ArrayList<String>();
		 * 
		 * if (courseList.getCourseList() == null) throw new
		 * IllegalStateException("Course list is null in UI class"); else { for
		 * (GolfCourse g : courseList.getCourseList()) {
		 * courseNameList.add(g.getCourseName()); } }
		 * 
		 * ArrayAdapter<String> courseNamesArrayAdapter = new
		 * ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
		 * courseNameList); courseListView.setAdapter(courseNamesArrayAdapter);
		 */

		/*
		 * Button to navigate to course creation screen
		 */
		addCourseButton.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				// <<< INVOKE CONTROLLER HERE>>>
				Intent i = new Intent(CourseListScreen.this,
						CourseInfoEntryScreen.class);
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

	private void establishArrayAdapter() {
		ArrayAdapter<GolfCourse> courseNamesArrayAdapter = new ArrayAdapter<GolfCourse>(this, android.R.layout.simple_list_item_1, golfCourseList);
		courseListView.setAdapter(courseNamesArrayAdapter);
		
		//CourseListAdapter adapter = new CourseListAdapter(this, R.layout.activity_course_list_screen, golfCourseList);
		//courseListView.setAdapter(adapter);
	}

}
