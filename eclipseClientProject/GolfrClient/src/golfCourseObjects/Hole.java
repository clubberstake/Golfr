package golfCourseObjects;

/**
 * The Hole class represents a hole on a golf course.
 * 
 * @author Matt Gisoni
 * 
 */
public class Hole {

	private Integer holeID;
	private Integer courseID;
	private Integer holeNumber;
	private Integer whiteTeeYargage;
	private Integer redTeeYardage;
	private Integer blueTeeYardage;
	private Integer handicap;
	private Integer par;
	/**
	 * The player's score for the hole, if played.
	 */
	private Integer score;

	/**
	 * Default Constructor.
	 */
	public Hole() {
		this.par = 0;
		this.redTeeYardage = 0;
		this.whiteTeeYargage = 0;
		this.blueTeeYardage = 0;
		this.score = 0;
		this.handicap = 0;
		this.holeID = 0;
		this.holeNumber = 0;
	}

	/**
	 * Typical constructor for all fields.
	 * 
	 * @param holeID
	 * @param courseID
	 * @param holeNumber
	 * @param whiteTeeYargage
	 * @param redTeeYardage
	 * @param blueTeeYardage
	 * @param handicap
	 * @param par
	 * @param score
	 */
	public Hole(Integer holeID, Integer courseID, Integer holeNumber,
			Integer whiteTeeYargage, Integer redTeeYardage,
			Integer blueTeeYardage, Integer handicap, Integer par, Integer score) {
		super();
		this.holeID = holeID;
		this.courseID = courseID;
		this.holeNumber = holeNumber;
		this.whiteTeeYargage = whiteTeeYargage;
		this.redTeeYardage = redTeeYardage;
		this.blueTeeYardage = blueTeeYardage;
		this.handicap = handicap;
		this.par = par;
		this.score = score;
	}

	/**
	 * @return the par
	 */
	public Integer getPar() {
		return this.par;
	}

	/**
	 * @param par
	 *            the par to set
	 */
	public void setPar(Integer par) {
		this.par = par;
	}

	/**
	 * @return the redTeeYardage
	 */
	public Integer getRedTeeYardage() {
		return this.redTeeYardage;
	}

	/**
	 * @param redTeeYardage
	 *            the redTeeYardage to set
	 */
	public void setRedTeeYardage(Integer redTeeYardage) {
		this.redTeeYardage = redTeeYardage;
	}

	/**
	 * @return the whiteTeeYargage
	 */
	public Integer getWhiteTeeYargage() {
		return this.whiteTeeYargage;
	}

	/**
	 * @param whiteTeeYargage
	 *            the whiteTeeYargage to set
	 */
	public void setWhiteTeeYargage(Integer whiteTeeYargage) {
		this.whiteTeeYargage = whiteTeeYargage;
	}

	/**
	 * @return the blueTeeYardage
	 */
	public Integer getBlueTeeYardage() {
		return this.blueTeeYardage;
	}

	/**
	 * @param blueTeeYardage
	 *            the blueTeeYardage to set
	 */
	public void setBlueTeeYardage(Integer blueTeeYardage) {
		this.blueTeeYardage = blueTeeYardage;
	}

	/**
	 * @return the score
	 */
	public Integer getScore() {
		return this.score;
	}

	/**
	 * @param score
	 *            the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}

	/**
	 * @return the handicap
	 */
	public Integer getHandicap() {
		return this.handicap;
	}

	/**
	 * @param handicap
	 *            the handicap to set
	 */
	public void setHandicap(Integer handicap) {
		this.handicap = handicap;
	}

	/**
	 * @return the holeNumber
	 */
	public Integer getHoleNumber() {
		return this.holeNumber;
	}

	/**
	 * @param holeNumber
	 *            the holeNumber to set
	 */
	public void setHoleNumber(Integer holeNumber) {
		this.holeNumber = holeNumber;
	}

	/**
	 * @return the holeID
	 */
	public Integer getHoleID() {
		return this.holeID;
	}

	/**
	 * @param holeID
	 *            the holeID to set
	 */
	public void setHoleID(Integer holeID) {
		this.holeID = holeID;
	}

	/**
	 * Checks the validity of all Hole variable data.
	 * 
	 * @return true if data is valid, false otherwise.
	 */
	public boolean validityCheck() {
		if (this.holeID > 0 && this.courseID > 0 && this.holeNumber >= 1
				&& this.holeNumber <= 18 && this.par >= 3 && this.par <= 5
				&& this.redTeeYardage <= this.whiteTeeYargage
				&& this.whiteTeeYargage <= this.blueTeeYardage)
			return true;
		else
			return false;
	}

	/**
	 * Checks the validity of Hole number only
	 * 
	 * @return - true if valid hole number, false otherwise
	 */
	public boolean holeNumberValidtyCheck() {
		if (this.holeNumber >= 1 && this.holeNumber <= 18)
			return true;
		else
			return false;
	}

	/**
	 * Checks the validity of Hole par data only
	 * 
	 * @return - true if the par value is valid, false otherwise
	 */
	public boolean parValidtyCheck() {
		if (this.par >= 3 && this.par <= 5)
			return true;
		else
			return false;
	}

	/**
	 * Checks the validity of the values for red tee, white tee, and blue tee
	 * yardages.
	 * 
	 * @return - true if the range of values is valid, false otherwise
	 */
	public boolean yardageValidityCheck() {
		if (this.redTeeYardage <= this.whiteTeeYargage
				&& this.whiteTeeYargage <= this.blueTeeYardage)
			return true;
		else
			return false;
	}

	/**
	 * @return the courseID
	 */
	public Integer getCourseID() {
		return courseID;
	}

	/**
	 * @param courseID
	 *            the courseID to set
	 */
	public void setCourseID(Integer courseID) {
		this.courseID = courseID;
	}

	/**
	 * Override of Object.equals() to compare hole objects
	 */
	@Override
	public boolean equals(Object toCompare) {
		boolean result = false;
		if (toCompare == null)
			return false;
		if (toCompare == this)
			return true;
		if (toCompare instanceof Hole) {
			Hole h = (Hole) toCompare;

			if (this.blueTeeYardage.equals(h.getBlueTeeYardage())
					&& this.handicap.equals(h.getHandicap())
					&& this.holeNumber.equals(h.getHoleNumber())
					&& this.par.equals(h.getPar())
					&& this.redTeeYardage.equals(h.getRedTeeYardage())
					&& this.whiteTeeYargage.equals(h.getWhiteTeeYargage()))
				result = true;
		}

		return result;
	}

	/**
	 * Override of Object.toString() to provided formatted string representation
	 * of a Hole.
	 */
	@Override
	public String toString() {
		return "Hole: " + this.holeNumber + " Par: " + this.par + " Score: "
				+ this.score;
	}
}
