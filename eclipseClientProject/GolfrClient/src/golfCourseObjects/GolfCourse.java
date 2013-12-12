package golfCourseObjects;

import java.util.ArrayList;
import java.util.regex.Pattern;

/**
 * The GolfCourse class represents a golf course.  It contains the necessary
 * golf course parameters and an ArrayList<Holes> size() == 18.
 * @author MAG
 *
 */
public class GolfCourse 
{
	private String courseName;
	private String streetName;
	private String streetNumber;
	private String postalCode;
	private String phoneNumber;
	private String webAddress;
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
			String postalCodeIn, String phoneNumberIn, String emailIn,
			ArrayList<Hole> holesIn, Integer golfCourseID) 
	{
		super();
		this.courseName = courseNameIn;
		this.streetName = streetNameIn;
		this.streetNumber = streetNumberIn;
		this.postalCode = postalCodeIn;
		this.phoneNumber = phoneNumberIn;
		this.webAddress = emailIn;
		this.golfCourseID = golfCourseID;
		this.totalPar = 0;
		if (holesIn != null && holesIn.size() == 18)
			{
				this.holes = holesIn;
				for (Hole h : this.holes)
				{
					this.totalPar += h.getPar();
				}
			}
		else
			{
				this.holes = new ArrayList<Hole>(18);
				this.totalPar = 72;
			}
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
	public String getPostalCode() {
		return this.postalCode;
	}

	/**
	 * @param postalCode the postalCode to set
	 */
	public void setPostalCode(String postalCode) {
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
	public String getWebaddress() {
		return this.webAddress;
	}

	/**
	 * @param email the email to set
	 */
	public void setWebaddress(String webaddress) {
		this.webAddress = webaddress;
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
	 * @return the webAddress
	 */
	public String getWebAddress() {
		return webAddress;
	}

	/**
	 * @param webAddress the webAddress to set
	 */
	public void setWebAddress(String webAddress) {
		this.webAddress = webAddress;
	}

	/**
	 * @return the totalPar
	 */
	public Integer getTotalPar() {
		return totalPar;
	}

	/**
	 * @param totalPar the totalPar to set
	 */
	public void setTotalPar(Integer totalPar) {
		this.totalPar = totalPar;
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
	
	public boolean courseNameValidityCheck()
	{
		boolean result = true;
		if(this.courseName == null || this.courseName == "" || this.courseName == " ")
		{
			result = false;
		}
		
		return result;
	}
	
	public boolean courseStreetNameValidityCheck()
	{
		boolean result = true;
		if(this.streetName == null || this.streetName == "" || this.streetName == " ")
		{
			result = false;
		}
		
		return result;
	}
	
	public boolean courseStreetNumberValidityCheck()
	{
		if ((Pattern.matches("[a-zA-Z]+", this.streetNumber) == true && (Pattern.matches("[a-zA-Z0-9]+", this.streetNumber) == true)))
		{
			return false;
		}
		
		
		return true;
	}
	
	public boolean coursePostalCodeValidityCheck()
	{
		if ((this.postalCode.length() !=5) || (Pattern.matches("[a-zA-Z]+", this.postalCode) == true && (Pattern.matches("[a-zA-Z0-9]+", this.postalCode) == true)))
		{
			return false;
		}
		
		
		return true;
	}
	
	/**
	 * Overrides the toString method to return the GolfCourse name
	 * @return the name of the GolfCourse
	 */
	@Override
	public String toString(){
		return this.courseName;
	}

	@Override
	public boolean equals(Object toCompare)
	{
		boolean toReturn = false;
		if (toCompare == null)
			return false;
		if (this == toCompare)
			return true;
		if (toCompare instanceof GolfCourse)
		{
			GolfCourse g = (GolfCourse) toCompare;
			if (
					this.courseName.equals(g.getCourseName()) &&
					this.streetName.equals(g.getStreetName()) &&
					this.streetNumber.equals(g.getStreetNumber()) &&
					this.postalCode.equals(g.getPostalCode()) &&				
					this.phoneNumber.equals(g.getPhoneNumber()) &&
					this.webAddress.equals(g.getWebaddress()) &&
					this.golfCourseID.equals(g.getGolfCourseID()) &&
					this.totalPar.equals(g.getTotalPar())
					)
			{
				for (int i = 0; i<18; i++)
				{
					if(!this.holes.get(i).equals(g.getHoles().get(i)))
						return false;
				}
				toReturn = true;
			}
		}
		return toReturn;
	}
}
