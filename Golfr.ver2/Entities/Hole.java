package Entities;

public class Hole {

	public Integer par;
	public Integer redTeeYardage;
	public Integer whiteTeeYardage;
	public Integer blueTeeYardage;
	public Integer handicapForCourse;
	public Integer score;

	public Hole(Integer par, Integer redTeeYardage, Integer whiteTeeYardage,
			Integer blueTeeYardage, Integer handicapForCourse, Integer score) {
		this.par = par;
		this.redTeeYardage = redTeeYardage;
		this.whiteTeeYardage = whiteTeeYardage;
		this.blueTeeYardage = blueTeeYardage;
		this.handicapForCourse = handicapForCourse;
		this.score = score;
	}
}
