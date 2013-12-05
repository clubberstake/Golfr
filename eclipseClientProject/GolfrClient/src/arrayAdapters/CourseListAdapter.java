package arrayAdapters;

import java.util.ArrayList;
import java.util.List;

import golfCourseObjects.GolfCourse;
import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class CourseListAdapter extends ArrayAdapter<GolfCourse> {

	private int layoutResouseId;
	private Context context;
	private ArrayList<GolfCourse> courses;

	public CourseListAdapter(Context context, int layoutResourceId, //Bolong:here is an error "resourceID should be layoutResouseID" I changed.
			ArrayList<GolfCourse> courses) {
		super(context, resourceId, courses);

		this.layoutResouseId = layoutResouseId;
		this.context = context;
		this.courses = courses;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;

		LayoutInflater inflator = ((Activity) context).getLayoutInflater();
		row = inflator.inflate(layoutResouseId, parent, false);

		row.setTag(courses.get(position).getCourseName());

		return row;
	}

	static class CoursesHolder //this temporarily stores the item content of the list
    {
        TextView txtCourseName;
        TextView txtStreetName;
        TextView txtStreetNumber;        
    }
}
