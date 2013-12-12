package arrayAdapters;

import golfCourseObjects.GolfCourse;
import android.content.Context;
import android.widget.ArrayAdapter;

public class CourseListAdapter extends ArrayAdapter<GolfCourse> {

	public CourseListAdapter(Context context, int resource) {
		super(context, resource);
		// TODO Auto-generated constructor stub
	}
/*
	private int layoutResouseId;
	private Context context;
	private ArrayList<GolfCourse> courses;

	public CourseListAdapter(Context context, int layoutResourceId, //Bolong:here is an error "resourceID should be layoutResouseID" I changed.
			ArrayList<GolfCourse> courses) {
		super(context, layoutResourceId, courses);

		this.layoutResouseId = layoutResouseId;
		this.context = context;
		this.courses = courses;
	}

	//@Override
	//public View getView(int position, View convertView, ViewGroup parent) {
	//	View row = convertView;

	//	LayoutInflater inflator = ((Activity) context).getLayoutInflater();
	//	row = inflator.inflate(layoutResouseId, parent, false);

	//	row.setTag(courses.get(position).getCourseName());

	//	return row;
	//}

	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View row = convertView;
		CoursesHolder holder = null;
		if(row == null)
        {
			LayoutInflater inflator = ((Activity) context).getLayoutInflater();
			row = inflator.inflate(layoutResouseId, parent, false);
            
            holder = new CoursesHolder();
            holder.txtCourseName = (TextView)row.findViewById(R.id.txtCourseName);
	//holder.txtStreetName = (TextView)row.findViewById(R.id.txtStreetName);
	//holder.txtStreetNumber = (TextView)row.findViewById(R.id.txtStreetNumber);            
            
            row.setTag(holder);
        }
        else
        {
            holder = (CoursesHolder)row.getTag();
        }
		
        holder.txtCourseName.setText(courses.get(position).getCourseName()+"  "+courses.get(position).getStreetName());
	//holder.txtStreetName.setText(courses.get(position).getStreetName());
	//holder.txtStreetNumber.setText(courses.get(position).getStreetNumber());        
 
        return row;		
	}
	
	
	static class CoursesHolder //this temporarily stores the item content of the list
    {
        TextView txtCourseName;
        TextView txtStreetName;
        TextView txtStreetNumber;        
    }
    */
}
