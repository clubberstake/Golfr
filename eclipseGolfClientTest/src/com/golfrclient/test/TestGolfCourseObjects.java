package com.golfrclient.test;

import golfCourseObjects.Game;
import golfCourseObjects.GolfCourse;
import golfCourseObjects.Hole;
import golfCourseObjects.User;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.test.ActivityInstrumentationTestCase2;

import com.golfrclient.MainActivity;

public class TestGolfCourseObjects extends ActivityInstrumentationTestCase2<MainActivity> {

	public TestGolfCourseObjects()
	{
		super(com.golfrclient.MainActivity.class);		
	}

	private User user1;
	private ArrayList<Hole> holes;
	private GolfCourse course1;
	
	
	/**
     * Sets up the test fixture. 
     * (Called before every test case method.)
     */
    @Before
    public void setUp() 
    {
    	this.user1 = new User("user1", 1);
    	this.holes = new ArrayList<Hole>(18);
    	
    	//from DB INSERT INTO `t_holes` VALUES
    	Hole h1 = new Hole(19,1,1,339,307,366,11,4,0);//(19,1,1,'339','307','366','11','4'),
    	Hole h2 = new Hole(20,1,2,373,334,417,3,4,0);//(20,1,2,'373','334','417','3','4'),
    	Hole h3 = new Hole(21,1,3,369,338,409,7,4,0);//(21,1,3,'369','338','409','7','4'),
    	Hole h4 = new Hole(22,1,4,128,106,154,17,3,0);//(22,1,4,'128','106','154','17','3'),
    	Hole h5 = new Hole(23,1,5,514,477,545,1,5,0);//(23,1,5,'514','477','545','1','5'),
    	Hole h6 = new Hole(24,1,6,242,192,292,13,4,0);//(24,1,6,'242','192','292','13','4'),
    	Hole h7 = new Hole(25,1,7,141,102,163,15,3,0);//(25,1,7,'141','102','163','15','3'),
    	Hole h8 = new Hole(26,1,8,374,324,412,5,4,0);//(26,1,8,'374','324','412','5','4'),
    	Hole h9 = new Hole(27,1,9,327,295,366,9,4,0);//(27,1,9,'327','295','366','9','4'),
    	Hole h10 = new Hole(28,1,10,376,346,422,10,4,0);//(28,1,10,'376','346','422','10','4'),
    	Hole h11 = new Hole(29,1,11,347,298,391,2,4,0);//(29,1,11,'347','298','391','2','4'),
    	Hole h12 = new Hole(30,1,12,141,118,168,18,3,0);//(30,1,12,'141','118','168','18','3'),
    	Hole h13 = new Hole(31,1,13,458,408,503,12,4,0);//(31,1,13,'458','408','503','12','5'),
    	Hole h14 = new Hole(32,1,14,290,276,323,14,4,0);//(32,1,14,'290','276','323','14','4'),
    	Hole h15 = new Hole(33,1,15,93,78,123,16,3,0);//(33,1,15,'93','78','123','16','3'),
    	Hole h16 = new Hole(34,1,16,352,274,394,6,4,0);//(34,1,16,'352','274','394','6','4'),
    	Hole h17 = new Hole(35,1,17,380,332,416,8,4,0);//(35,1,17,'380','332','416','8','4'),
    	Hole h18 = new Hole(36,1,17,434,385,477,4,5,0);//(36,1,18,'434','385','477','4','5');
    	    	
    	holes.add(0, h1);
    	holes.add(1, h2);
    	holes.add(2, h3);
    	holes.add(3, h4);
    	holes.add(4, h5);
    	holes.add(5, h6);
    	holes.add(6, h7);
    	holes.add(7, h8);
    	holes.add(8, h9);
    	holes.add(9, h10);
    	holes.add(10, h11);
    	holes.add(11, h12);
    	holes.add(12, h13);
    	holes.add(13, h14);
    	holes.add(14, h15);
    	holes.add(15, h16);
    	holes.add(16, h17);
    	holes.add(17, h18);
    	    	
    	this.course1 = new GolfCourse("Test Course","West Street", "400","15237","412-555-5555","www.testcourse.com", holes, 1);
    }

    /**
     * Tears down the test fixture. 
     * (Called after every test case method.)
     */
    @After
    public void tearDown() 
    {
    	this.user1 = null;
    	this.holes.clear();
    	this.holes = null;
        course1 = null;
    }
    
    @Test
    public void testUserValidityCheckGeneric()
    {
    	assertTrue(user1.validityCheckGeneric());
    }
    
    @Test
    public void testUserValidtyCheckInGame()
    {
    	assertFalse(user1.validityCheckInGame());
    	Game g = new Game(user1,course1,0,0);
    	user1.setCurrentGame(g);
    	assertTrue(user1.validityCheckInGame());
    }
    
    @Test
    public void testCourseValidityCheck()
    {
    	assertTrue(course1.validityCheck());
    }

	@Test
	public void testHoleValidityCheck() 
	{
		try
		{
			//null hole
			Hole holeNull = new Hole();			
			Hole invalidID  = new Hole(0,1,1,339,307,366,11,4,0);			
			Hole invalidHoleNumberLow = new Hole(1,1,0,339,307,366,11,4,0);
			Hole invalidHoleNumberHigh = new Hole(1,1,19,339,307,366,11,4,0);
			Hole invalidCourseID = new Hole(1,0,1,339,307,366,11,4,0);
			Hole invalidRedTee = new Hole(1,1,1,339,500,366,11,4,0);
			Hole invalidWhiteTeeLessThanRed = new Hole(1,1,1,306,307,366,11,4,0);
			Hole invalidWhiteTeeGreaterThanBlue = new Hole(1,1,1,367,307,366,11,4,0);
			Hole invalidBlueTeeLessThanRed = new Hole(1,1,1,339,307,306,11,4,0);
			Hole invalidBlueTeeLessThanWhite = new Hole(1,1,1,339,307,338,11,4,0);
			
			assertFalse(holeNull.validityCheck());
			assertFalse(invalidID.validityCheck());
			assertFalse(invalidHoleNumberLow.validityCheck());
			assertFalse(invalidHoleNumberHigh.validityCheck());
			assertFalse(invalidCourseID.validityCheck());
			assertFalse(invalidRedTee.validityCheck());
			assertFalse(invalidWhiteTeeLessThanRed.validityCheck());
			assertFalse(invalidWhiteTeeGreaterThanBlue.validityCheck());
			assertFalse(invalidBlueTeeLessThanRed.validityCheck());
			assertFalse(invalidBlueTeeLessThanWhite.validityCheck());
			
			//test data from setUp() is valid
			assertTrue(course1.getHoles().get(0).validityCheck());
			assertTrue(course1.getHoles().get(17).validityCheck());
			
		}
		catch (Exception e)
		{
			fail("test setUp() failure");
		}
	}
	


}
