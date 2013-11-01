package Validation;

import Entities.Hole;

public class ScoringValidation {

	public static boolean isPar(Hole hole)
	{
		return hole.score == hole.par;
	}
	
	public static boolean isBirdieOrBetter(Hole hole)
	{
		return hole.score < hole.par;
	}
	
	public static boolean isBogey(Hole hole)
	{
		return hole.score > hole.par;
	}
	
}
