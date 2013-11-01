package Validation;

import Entities.GolfCourse;
import Entities.Hole;

public class GolfCourseValidation {
	
	public static boolean isCourseValid(GolfCourse course){
		if(course.streetName.length() == 0){
			return false;
		}
		if(course.streetNumber.length() != 4 || !course.streetNumber.matches("[0-9]*")){
			return false;
		}
		if(course.postalCode.length() != 5 || !course.postalCode.matches("[0-9]*")){
			return false;
		}
		if(course.phoneNumber.length() != 10 || !course.phoneNumber.matches("[0-9]*")){
			return false;
		}
		return true;
	}	

	public static boolean areTeesYardageDefinedProperly(GolfCourse course) {
		for (Hole hole : course.holes) {
			if(!(hole.redTeeYardage <= hole.whiteTeeYardage && hole.whiteTeeYardage <= hole.blueTeeYardage))
			{
				return false;
			}
		}
		return true;
	}

	public static boolean isCourseParValid(GolfCourse course) {
		if(course == null){
			return false;
		}
		if (course.holes == null || course.holes.size() != 18) {
			return false;
		}

		for (Hole hole : course.holes) {
			if (hole.par < 3) {
				return false;
			}
		}

		return true;
	}
}
