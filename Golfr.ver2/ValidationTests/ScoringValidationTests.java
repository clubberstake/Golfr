package ValidationTests;

import static org.junit.Assert.*;

import org.junit.Test;

import Entities.Hole;
import Validation.ScoringValidation;

public class ScoringValidationTests {

	@Test
	public void isPar_ScoreEqualsPar_ReturnsTrue() {
		assertTrue(ScoringValidation.isPar(new Hole(4, 0, 0, 0, 0, 4)));
	}

	@Test
	public void isPar_ScoreLessThanPar_ReturnsFalse() {
		assertFalse(ScoringValidation.isPar(new Hole(4, 0, 0, 0, 0, 3)));
	}

	@Test
	public void isPar_ScoreGreaterThanPar_ReturnsFalse() {
		assertFalse(ScoringValidation.isPar(new Hole(4, 0, 0, 0, 0, 5)));
	}

	@Test
	public void isBirdieOrBetter_ScoreEqualsPar_ReturnsFalse() {
		assertFalse(ScoringValidation.isBirdieOrBetter(new Hole(4, 0, 0, 0, 0, 4)));
	}

	@Test
	public void isBirdieOrBetter_ScoreLessThanPar_ReturnsTrue() {
		assertTrue(ScoringValidation.isBirdieOrBetter(new Hole(4, 0, 0, 0, 0, 3)));
	}

	@Test
	public void isBirdieOrBetter_ScoreGreaterThanPar_ReturnsFalse() {
		assertFalse(ScoringValidation.isBirdieOrBetter(new Hole(4, 0, 0, 0, 0, 5)));
	}

	@Test
	public void isBogey_ScoreEqualsPar_ReturnsFalse() {
		assertFalse(ScoringValidation.isBogey(new Hole(4, 0, 0, 0, 0, 4)));
	}

	@Test
	public void isBogey_ScoreLessThanPar_ReturnsFalse() {
		assertFalse(ScoringValidation.isBogey(new Hole(4, 0, 0, 0, 0, 3)));
	}

	@Test
	public void isBogey_ScoreGreaterThanPar_ReturnsTrue() {
		assertTrue(ScoringValidation.isBogey(new Hole(4, 0, 0, 0, 0, 5)));
	}
}
