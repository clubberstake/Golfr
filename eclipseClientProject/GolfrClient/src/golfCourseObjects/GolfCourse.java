package golfCourseObjects;

import java.util.ArrayList;

/**
 * The GolfCourse class represnts a golf course.  It contains the necessairy
 * golf course parameters and an ArrayList<Holes> size() == 18.
 * @author matt
 *
 */
public class GolfCourse 
{
	private String courseName;
	private String streetName;
	private String streetNumber;
	private Integer postalCode;
	private String phoneNumber;
	private String email;
	private ArrayList<Hole> holes;
	private Integer golfCourseID;
	private Integer totalPar;

	/**
	 * Default constructor 
	 */
	public GolfCourse() 
	{
		super();
		this.holes = new ArrayList<Hole>(18);
	}

	/**
	 * Full constructor.
	 * @param courseNameIn
	 * @param streetNameIn
	 * @param streetNumberIn
	 * @param postalCodeIn
	 * @param phoneNumberIn
	 * @param emailIn
	 * @param holesIn
	 */
	public GolfCourse(String courseNameIn, String streetNameIn, String streetNumberIn,
			Integer postalCodeIn, String phoneNumberIn, String emailIn,
			ArrayList<Hole> holesIn, Integer golfCourseID) 
	{
		super();
		this.courseName = courseNameIn;
		this.streetName = streetNameIn;
		this.streetNumber = streetNumberIn;
		this.postalCode = postalCodeIn;
		this.phoneNumber = phoneNumberIn;
		this.email = emailIn;
		this.golfCourseID = golfCourseID;

		if (holesIn != null && holesIn.size() == 18)
			this.holes = holesIn;
		else
			this.holes = new ArrayList<Hole>(18);
	}
	/**
	 * @return the courseName
	 */
	public String getCourseName(){
		return this.courseName;
	}
	
	public void setCourseName(String courseName){
		this.courseName = courseName;
	}

	/**
	 * @return the streetName
	 */
	public String getStreetName() {
		return this.streetName;
	}

	/**
	 * @param streetName the streetName to set
	 */
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * @return the streetNumber
	 */
	public String getStreetNumber() {
		return this.streetNumber;
	}

	/**
	 * @param streetNumber the streetNumber to set
	 */
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	/**
	 * @return the postalCode
	 */
	public Integer getPostalCode() {
		return this.postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(Integer postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * @return the phoneNumber
	 */
	public String getPhoneNumber() {
		return this.phoneNumber;
	}

	/**
	 * @param phoneNumber the phoneNumber to set
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the holes
	 */
	public ArrayList<Hole> getHoles() {
		return this.holes;
	}

	/**
	 * @param holes the holes to set
	 */
	public void setHoles(ArrayList<Hole> holes) {
		this.holes = holes;
	}

	/**
	 * @return the golfCourseID
	 */
	public Integer getGolfCourseID() {
		return this.golfCourseID;
	}

	/**
	 * @param golfCourseID the golfCourseID to set
	 */
	public void setGolfCourseID(Integer golfCourseID) {
		this.golfCourseID = golfCourseID;
	}

	/**
	 * Checks the validity of GolfCourse variables.
	 * @return true if data is valid, false otherwise.
	 */
	public boolean validityCheck() 
	{
		boolean result = false;
		
		//ID check and holes array length check
		if (this.golfCourseID > 0 && this.holes != null && 
				this.holes.size() == 18)
		{
			//hole validity check
			for (int i = 0; i <this.holes.size();i++)
			{
				if (this.holes.get(i).validityCheck() == true)
					result = true;
			}			
		}
		return result;
	}
}
