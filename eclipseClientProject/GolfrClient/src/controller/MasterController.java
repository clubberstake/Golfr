package controller;

import golfCourseObjects.Game;
import golfCourseObjects.GolfCourse;
import golfCourseObjects.Hole;
import golfCourseObjects.User;

/**
 * The MasterController class serves as a local "data store" for the Golfr application.
 * 
 * It centrally stores all of the shared variables needed when using the application. 
 * @author MAG
 *
 */
public class MasterController {
	public static User user = null;
	public static Game game = null;
	public static GolfCourse currentCourse = null;
	public static Hole currentHole = null;
	public static Integer currentHoleNum = null;
	

}
