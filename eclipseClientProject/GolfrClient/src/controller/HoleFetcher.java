package controller;

import golfCourseObjects.GolfCourse;
import golfCourseObjects.Hole;
import java.sql.SQLException;
import java.util.ArrayList;

public class HoleFetcher  extends SQLQueries{
	private ArrayList<Hole> holeList;
	private GolfCourse course;
	
	public HoleFetcher(){
		super();
	}
	
	public HoleFetcher(GolfCourse course){
		super();
		this.course = course;
	}
	
	private void getHoleListFromDB(){
		super.connect();
		try{
			this.holeList = super.getHoleMetadata(course);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
		finally{
			this.close();
		}
	}
	
	public ArrayList<Hole> getHoleList() throws SQLException
	{
		getCourseListFromDB();
		return holeList;
	}

}
