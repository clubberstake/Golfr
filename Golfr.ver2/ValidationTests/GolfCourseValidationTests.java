package ValidationTests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import Entities.GolfCourse;
import Entities.Hole;
import Validation.GolfCourseValidation;

public class GolfCourseValidationTests {
	
	
	@Test
	public void isCourseValid_StreetNameIsBlank_ReturnsFalse(){
		ArrayList<Hole> holes = new ArrayList<Hole>();
		for (int i= 0; i < 18; i++){
			holes.add(new Hole(0,0,0,0,0,0));
		}
		assertFalse(GolfCourseValidation.isCourseValid(new GolfCourse("","1234","12345","1234567890",holes)));
	}
	
	@Test
	public void isCourseValid_StreetNumberTooLong_ReturnsFalse(){
		ArrayList<Hole> holes = new ArrayList<Hole>();
		for (int i= 0; i < 18; i++){
			holes.add(new Hole(0,0,0,0,0,0));
		}
		assertFalse(GolfCourseValidation.isCourseValid(new GolfCourse("street name","1234567890123","12345","1234567890",holes)));
	}
	
	@Test
	public void isCourseValid_PostalCodeTooLong_ReturnsFalse(){
		ArrayList<Hole> holes = new ArrayList<Hole>();
		for (int i= 0; i < 18; i++){
			holes.add(new Hole(0,0,0,0,0,0));
		}
		assertFalse(GolfCourseValidation.isCourseValid(new GolfCourse("street name","1234","123456789","1234567890",holes)));
	}
	
	@Test
	public void isCourseValid_PhoneNumberTooShort_ReturnsFalse(){
		ArrayList<Hole> holes = new ArrayList<Hole>();
		for (int i= 0; i < 18; i++){
			holes.add(new Hole(0,0,0,0,0,0));
		}
		assertFalse(GolfCourseValidation.isCourseValid(new GolfCourse("street name","1234","12345","12345",holes)));
	}
	
	@Test
	public void isCourseValid_AllOK_ReturnsTrue(){
		ArrayList<Hole> holes = new ArrayList<Hole>();
		for (int i= 0; i < 18; i++){
			holes.add(new Hole(0,0,0,0,0,0));
		}
		assertTrue(GolfCourseValidation.isCourseValid(new GolfCourse("street name","1234","12345","1234567890",holes)));
	}

	@Test
	public void areTeesYardageDefinedProperly_RedEqualsWhiteEqualsBlue_ReturnsTrue() {
		ArrayList<Hole> holes = new ArrayList<Hole>();
		holes.add(new Hole(0, 350, 350, 350, 0, 0));
		assertTrue(GolfCourseValidation.areTeesYardageDefinedProperly(new GolfCourse("street name","1234","12345","1234567890",holes)));
	}

	@Test
	public void areTeesYardageDefinedProperly_RedLessThanWhiteLessThanBlue_ReturnsTrue() {
		ArrayList<Hole> holes = new ArrayList<Hole>();
		holes.add(new Hole(0, 350, 375, 400, 0, 0));
		assertTrue(GolfCourseValidation.areTeesYardageDefinedProperly(new GolfCourse("street name","1234","12345","1234567890",holes)));
	}

	@Test
	public void areTeesYardageDefinedProperly_RedGreaterThanWhiteLessThanBlue_ReturnsFalse() {
		ArrayList<Hole> holes = new ArrayList<Hole>();
		holes.add(new Hole(0, 375, 350, 400, 0, 0));
		assertFalse(GolfCourseValidation.areTeesYardageDefinedProperly(new GolfCourse("street name","1234","12345","1234567890",holes)));
	}

	@Test
	public void areTeesYardageDefinedProperly_RedGreaterThanWhiteRedGreaterThanBlue_ReturnsFalse() {
		ArrayList<Hole> holes = new ArrayList<Hole>();
		holes.add(new Hole(0, 400, 375, 350, 0, 0));
		assertFalse(GolfCourseValidation.areTeesYardageDefinedProperly(new GolfCourse("street name","1234","12345","1234567890",holes)));
	}

	@Test
	public void isCourseParValid_SendNullList_ReturnsFalse() {
		assertFalse(GolfCourseValidation.isCourseParValid(null));
	}

	@Test
	public void isCourseParValid_SendEmptyList_ReturnsFalse() {
		assertFalse(GolfCourseValidation
				.isCourseParValid(new GolfCourse("street name","1234","12345","1234567890",new ArrayList<Hole>())));
	}

	@Test
	public void isCourseParValid_SendAllZerosList_ReturnsFalse() {

		ArrayList<Hole> holes = new ArrayList<Hole>();
		for (int i = 0; i < 18; i++) {
			holes.add(new Hole(0, 0, 0, 0, 0, 0));
		}

		assertFalse(GolfCourseValidation.isCourseParValid(new GolfCourse("street name","1234","12345","1234567890",holes)));
	}

	@Test
	public void isCourseParValid_SendAllThreesList_ReturnsTrue() {

		ArrayList<Hole> holes = new ArrayList<Hole>();
		for (int i = 0; i < 18; i++) {
			holes.add(new Hole(3, 0, 0, 0, 0, 0));
		}

		assertTrue(GolfCourseValidation.isCourseParValid(new GolfCourse("street name","1234","12345","1234567890",holes)));
	}
}
