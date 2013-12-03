package controller;

import golfCourseObjects.GolfCourse;
import golfCourseObjects.Hole;

import java.sql.SQLException;
import java.util.ArrayList;

public class GetHoleMetadata extends SQLQueries implements Runnable {

	private ArrayList<Hole> holeMetadataList;
	private GolfCourse course;
	
	public GetHoleMetadata() {
		super();
	}
	public GetHoleMetadata(GolfCourse course) {
		super();
		this.course = course;
	}

	@Override
	public void run() {
		synchronized(this)
		{
			super.connect();
			try {
				this.holeMetadataList = super.getHoleMetadata(course);
			} catch (SQLException e) {
				e.printStackTrace();			
			}
			finally
			{
				this.close();
				notifyAll();
			}
		}

	}
	/**
	 * @return the holeMetadataList
	 */
	public ArrayList<Hole> getHoleMetadataList() {
		return holeMetadataList;
	}
	/**
	 * @param holeMetadataList the holeMetadataList to set
	 */
	public void setHoleMetadataList(ArrayList<Hole> holeMetadataList) {
		this.holeMetadataList = holeMetadataList;
	}
	/**
	 * @return the course
	 */
	public GolfCourse getCourse() {
		return course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(GolfCourse course) {
		this.course = course;
	}

}
