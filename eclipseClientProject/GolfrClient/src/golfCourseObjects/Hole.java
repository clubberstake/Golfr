package golfCourseObjects;

public class Hole {

	public Integer par;
	public Integer redTeeYardage;
	public Integer whiteTeeYargage;
	public Integer blueTeeYardage;
	public Integer score;
	
	public Hole(Integer parIn, Integer redTeeYardageIn, Integer whiteTeeYardageIn, Integer blueTeeYardageIn, Integer scoreIn)
	{
		this.par = parIn;
		this.redTeeYardage = redTeeYardageIn;
		this.whiteTeeYargage = whiteTeeYardageIn;
		this.blueTeeYardage = blueTeeYardageIn;
		this.score = scoreIn;
	}
}
