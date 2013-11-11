package golfCourseObjects;

/**
 * The Hole class represents a hole on a golf course.
 * @author Matt Gisoni
 *
 */
public class Hole
{

	private Integer par;
	private Integer redTeeYardage;
	private Integer whiteTeeYargage;
	private Integer blueTeeYardage;
	private Integer score;
	private Integer handicap;

	/**
	 * Default Constructor.
	 */
	public Hole()
	{
		this.par = 0;
		this.redTeeYardage = 0;
		this.whiteTeeYargage = 0;
		this.blueTeeYardage = 0;
		this.score = 0;
		this.handicap = 0;
	}
	
	/**
	 * Full constructor to populate all class variables.
	 * @param parIn
	 * @param redTeeYardageIn
	 * @param whiteTeeYardageIn
	 * @param blueTeeYardageIn
	 * @param scoreIn
	 * @param handicap
	 */
	public Hole(Integer parIn, Integer redTeeYardageIn,
			Integer whiteTeeYardageIn, Integer blueTeeYardageIn,
			Integer scoreIn, Integer handicap) 
	{
		this.par = parIn;
		this.redTeeYardage = redTeeYardageIn;
		this.whiteTeeYargage = whiteTeeYardageIn;
		this.blueTeeYardage = blueTeeYardageIn;
		this.score = scoreIn;
		this.handicap = handicap;
	}

	/**
	 * @return the par
	 */
	public Integer getPar() {
		return this.par;
	}

	/**
	 * @param par the par to set
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
	 * @param redTeeYardage the redTeeYardage to set
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
	 * @param whiteTeeYargage the whiteTeeYargage to set
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
	 * @param blueTeeYardage the blueTeeYardage to set
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
	 * @param score the score to set
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
	 * @param handicap the handicap to set
	 */
	public void setHandicap(Integer handicap) {
		this.handicap = handicap;
	}
	
}
