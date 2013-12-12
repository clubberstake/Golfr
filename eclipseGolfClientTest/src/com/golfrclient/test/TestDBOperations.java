package com.golfrclient.test;

import golfCourseObjects.Game;
import golfCourseObjects.GolfCourse;
import golfCourseObjects.HistoryObject;
import golfCourseObjects.Hole;
import golfCourseObjects.User;
import controller.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import android.os.AsyncTask;
import android.test.ActivityInstrumentationTestCase2;

import com.golfrclient.MainActivity;

public class TestDBOperations extends ActivityInstrumentationTestCase2<MainActivity>
{



	public TestDBOperations() 
	{
		super(com.golfrclient.MainActivity.class);

	}

	/**
	 * Sets up the test fixture. 
	 * (Called before every test case method.)
	 */
	@Before
	public void setUp() 
	{

	}

	/**
	 * Tears down the test fixture. 
	 * (Called after every test case method.)
	 */
	@After
	public void tearDown()
	{

	}

	@Test
	public void testDBDriver()
	{
		try 
		{
			Class.forName("com.mysql.jdbc.Driver").getClass();
			assertTrue(true);
		} catch (ClassNotFoundException e) 
		{
			
			throw new RuntimeException("Cannot find the driver in the classpath!", e);
		}
		catch (IllegalStateException e)
		{
			
			e.printStackTrace();
		}
	}

	@Test
	public void testConnectToDB()
	{

		CourseList listGetter = new CourseList();
		try 
		{

			listGetter.start();
			synchronized (listGetter)
			{
				listGetter.wait();
				assertNotNull(listGetter.getConnection());
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			if (listGetter != null)
				listGetter.close();
		}

	}

	@Test
	public void testGetCourseListFromDB()
	{

		CourseList listGetter = new CourseList();
		try
		{			
			listGetter.start();	
			synchronized (listGetter)
			{
				listGetter.wait();
				ArrayList<GolfCourse> courses = listGetter.getCourseList();
				assertTrue(courses.size() >0 && courses.get(0).getCourseName().equalsIgnoreCase("course1"));
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			if (listGetter != null)
				listGetter.close();
		}
	}

	@Test
	public void testGetHoleMetadata()
	{
		GolfCourse course = new GolfCourse();
		course.setGolfCourseID(1);
		GetHoleMetadata metaDataGetter = new GetHoleMetadata(course);

		try
		{
			metaDataGetter.start();	
			synchronized (metaDataGetter)
			{	
				metaDataGetter.wait();
				ArrayList<Hole> holes = metaDataGetter.getHoleMetadataList();
				assertTrue(holes.size() == 18);

				for (int i=0;i<18;i++)
				{
					assertTrue(holes.get(i).getHoleNumber() == i+1);
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			if (metaDataGetter != null)
				metaDataGetter.close();
		}

	}


	@Test
	public void testAddandDeleteCourseFromDB()
	{

		SendCourseDetailsToDB courseSender = null;
		DeleteCourseFromDB courseDeleter = null;
		GetCoursePrimaryKey keyGetter = null;
		Integer primaryKey = null;
		try
		{
			/*send course*/
			GolfCourse newCourse = new GolfCourse("Course2", "West Street", "155", "26606", "555-965-5555", "www.course2.com", null,null);
			courseSender = new SendCourseDetailsToDB(newCourse);
			courseSender.start();

			synchronized (courseSender)
			{
				courseSender.wait();
			}

			/*try to get key*/
			keyGetter = new GetCoursePrimaryKey(newCourse);
			keyGetter.start();
			synchronized (keyGetter)
			{
				keyGetter.wait();			
				primaryKey = keyGetter.getCourseKey();
				assertNotNull(primaryKey);
			}
			/*delete new course*/
			if (primaryKey != null)
			{
				courseDeleter = new DeleteCourseFromDB(primaryKey);
				courseDeleter.start();		
				synchronized (courseDeleter)
				{
					courseDeleter.wait();					
				}
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
			
		}
		finally
		{
			if (courseSender != null)
				courseSender.close();
			if(courseDeleter != null)
				courseDeleter.close();
			if (keyGetter != null)
				keyGetter.close();

		}
	}

	@Test
	public void testAddHolesToCourse()
	{
		SendCourseDetailsToDB courseSender = null;
		DeleteCourseFromDB courseDeleter = null;
		GetCoursePrimaryKey keyGetter = null;
		AddHoleToCourse holeAdder = null;
		GetHoleMetadata metadataGetter = null;
		Integer primaryKey = null;

		ArrayList<Hole> holes = new ArrayList<Hole>(18);		

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
		Hole h18 = new Hole(36,1,18,434,385,477,4,5,0);//(36,1,18,'434','385','477','4','5');

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

		GolfCourse newCourse = new GolfCourse("Course2", "West Street", "155", "26606", "555-965-5555", "www.course2.com", holes,null);

		try 
		{
			courseSender = new SendCourseDetailsToDB(newCourse);
			courseSender.start();
			synchronized (courseSender)
			{
				courseSender.wait();
			}

			/*try to get key*/
			keyGetter = new GetCoursePrimaryKey(newCourse);
			keyGetter.start();
			synchronized (keyGetter)
			{
				keyGetter.wait();			
				primaryKey = keyGetter.getCourseKey();
				assertNotNull(primaryKey);
				newCourse.setGolfCourseID(primaryKey);//important to set the key locally after each creation
			}

			/*add holes to the new course*/	
			for (Hole h : holes)
			{
				holeAdder = new AddHoleToCourse(primaryKey, h);
				holeAdder.start();
				synchronized (holeAdder)
				{
					holeAdder.wait();
				}
			}

			/*get hole metadata for the new course*/
			metadataGetter = new GetHoleMetadata(newCourse);
			metadataGetter.start();
			synchronized (metadataGetter)
			{
				metadataGetter.wait();	
				ArrayList<Hole> fromDBList = metadataGetter.getHoleMetadataList();

				//test the paramaters of holes vs. fromDBList
				assertEquals(fromDBList.size(),holes.size());
				for (int i = 0; i<18; i++)
				{
					assertTrue(holes.get(i).equals(fromDBList.get(i)));
				}
			}

			/*delete new course including the new holes*/
			if (primaryKey != null)
			{
				courseDeleter = new DeleteCourseFromDB(primaryKey);
				courseDeleter.start();		
				synchronized (courseDeleter)
				{
					courseDeleter.wait();					
				}
			}
		}
		catch (Exception e) {
			
			e.printStackTrace();
		}		
		finally
		{

			if (courseSender != null)
				courseSender.close();
			if(courseDeleter != null)
				courseDeleter.close();
			if (keyGetter != null)
				keyGetter.close();
			if (holeAdder != null)
				holeAdder.close();
			if (metadataGetter != null)
				metadataGetter.close();
		}


	}

	@Test
	public void testGetCourse()
	{

		SendCourseDetailsToDB courseSender = null;
		DeleteCourseFromDB courseDeleter = null;
		GetCoursePrimaryKey keyGetter = null;
		AddHoleToCourse holeAdder = null;
		GetHoleMetadata metadataGetter = null;
		GetCourse courseGetter = null;
		Integer primaryKey = null;


		ArrayList<Hole> holes = new ArrayList<Hole>(18);		

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
		Hole h18 = new Hole(36,1,18,434,385,477,4,5,0);//(36,1,18,'434','385','477','4','5');

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

		GolfCourse newCourse = new GolfCourse("Course2", "West Street", "155", "26606", "555-965-5555", "www.course2.com", holes,null);
		Integer totalPar = 0;
		for (Hole h : holes)
		{
			totalPar += h.getPar();
		}
		newCourse.setTotalPar(totalPar);


		try 
		{
			courseSender = new SendCourseDetailsToDB(newCourse);
			courseSender.start();
			synchronized (courseSender)
			{
				courseSender.wait();
			}

			/*try to get key*/
			keyGetter = new GetCoursePrimaryKey(newCourse);
			keyGetter.start();
			synchronized (keyGetter)
			{
				keyGetter.wait();			
				primaryKey = keyGetter.getCourseKey();
				assertNotNull(primaryKey);
				newCourse.setGolfCourseID(primaryKey);//important to set the key locally after each creation
			}

			/*add holes to the new course*/	
			for (Hole h : holes)
			{
				holeAdder = new AddHoleToCourse(primaryKey, h);
				holeAdder.start();
				synchronized (holeAdder)
				{
					holeAdder.wait();
				}
			}

			/*get hole metadata for the new course*/
			metadataGetter = new GetHoleMetadata(newCourse);
			metadataGetter.start();
			synchronized (metadataGetter)
			{
				metadataGetter.wait();	
				ArrayList<Hole> fromDBList = metadataGetter.getHoleMetadataList();

				//test the paramaters of holes vs. fromDBList
				assertEquals(fromDBList.size(),holes.size());
				for (int i = 0; i<18; i++)
				{
					assertTrue(holes.get(i).equals(fromDBList.get(i)));
				}
			}

			/*run getCourse() method and compare*/
			GolfCourse courseFromDB = null;
			courseGetter = new GetCourse(primaryKey);
			courseGetter.start();
			synchronized (courseGetter)
			{
				courseGetter.wait();
				courseFromDB = courseGetter.getCourse();
				assertTrue(courseFromDB.equals(newCourse));
			}

			/*delete new course including the new holes*/
			if (primaryKey != null)
			{
				courseDeleter = new DeleteCourseFromDB(primaryKey);
				courseDeleter.start();		
				synchronized (courseDeleter)
				{
					courseDeleter.wait();					
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}		
		finally
		{

			if (courseSender != null)
				courseSender.close();
			if(courseDeleter != null)
				courseDeleter.close();
			if (keyGetter != null)
				keyGetter.close();
			if (holeAdder != null)
				holeAdder.close();
			if (metadataGetter != null)
				metadataGetter.close();
			if(courseGetter != null)
				courseGetter.close();
		}
	}

	@Test
	public void testGetScorecard()
	{
		GetScorecard cardGetter = null;

		try
		{
			/*set up a dummy game and course based on known data in DB*/
			Game g = new Game();
			GolfCourse c = new GolfCourse();
			c.setGolfCourseID(1);
			g.setScoreHistoryPK(1);
			g.setCourse(c);

			//get the scorecard
			cardGetter = new GetScorecard(g);
			cardGetter.start();
			synchronized (cardGetter)
			{
				cardGetter.wait();
				ArrayList<Hole> scorecard = cardGetter.getScorecardList();
				assertTrue(scorecard.size() == 18);

				/*compare scorecard against known data from the DB*/
				assertTrue(scorecard.get(0).getHoleNumber() == 1);
				assertTrue(scorecard.get(0).getPar() == 4);					
				assertTrue(scorecard.get(1).getHoleNumber() == 2);
				assertTrue(scorecard.get(1).getPar() == 4);
				assertTrue(scorecard.get(2).getHoleNumber() == 3);
				assertTrue(scorecard.get(2).getPar() == 4);
				assertTrue(scorecard.get(3).getHoleNumber() == 4);
				assertTrue(scorecard.get(3).getPar() == 3);
				assertTrue(scorecard.get(4).getHoleNumber() == 5);
				assertTrue(scorecard.get(4).getPar() == 5);
				assertTrue(scorecard.get(5).getHoleNumber() == 6);
				assertTrue(scorecard.get(5).getPar() == 4);
				assertTrue(scorecard.get(6).getHoleNumber() == 7);
				assertTrue(scorecard.get(6).getPar() == 3);
				assertTrue(scorecard.get(7).getHoleNumber() == 8);
				assertTrue(scorecard.get(7).getPar() == 4);
				assertTrue(scorecard.get(8).getHoleNumber() == 9);
				assertTrue(scorecard.get(8).getPar() == 4);
				assertTrue(scorecard.get(9).getHoleNumber() == 10);
				assertTrue(scorecard.get(9).getPar() == 4);
				assertTrue(scorecard.get(10).getHoleNumber() == 11);
				assertTrue(scorecard.get(10).getPar() == 4);
				assertTrue(scorecard.get(11).getHoleNumber() == 12);
				assertTrue(scorecard.get(11).getPar() == 3);
				assertTrue(scorecard.get(12).getHoleNumber() == 13);
				assertTrue(scorecard.get(12).getPar() == 5);
				assertTrue(scorecard.get(13).getHoleNumber() == 14);
				assertTrue(scorecard.get(13).getPar() == 4);
				assertTrue(scorecard.get(14).getHoleNumber() == 15);
				assertTrue(scorecard.get(14).getPar() == 3);
				assertTrue(scorecard.get(15).getHoleNumber() == 16);
				assertTrue(scorecard.get(15).getPar() == 4);
				assertTrue(scorecard.get(16).getHoleNumber() == 17);
				assertTrue(scorecard.get(16).getPar() == 4);
				assertTrue(scorecard.get(17).getHoleNumber() == 18);
				assertTrue(scorecard.get(17).getPar() == 5);

			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (cardGetter != null)
				cardGetter.close();
		}
	}


	@Test
	public void testNewGame()
	{
		NewGame newGameCreator = null;
		DeleteGameFromDB gameDeletor = null;
		GetCourse courseGetter = null;
		GetScorecard scorecardGetter = null;
		User Bob = new User("Bob");
		GolfCourse course = null;
		Game newGame = null;

		try
		{
			courseGetter = new GetCourse(1);
			courseGetter.start();
			synchronized (courseGetter)
			{
				courseGetter.wait();
			}
			course = courseGetter.getCourse();
			newGameCreator = new NewGame(Bob,course);
			newGameCreator.start();
			synchronized (newGameCreator)
			{
				newGameCreator.wait();
			}
			newGame = newGameCreator.getGame();	

			/*Get the newGame's scorecard to see if it was created correctly*/
			scorecardGetter = new GetScorecard(newGame);
			scorecardGetter.start();
			synchronized (scorecardGetter)
			{
				scorecardGetter.wait();
			}
			ArrayList<Hole> scorecard = scorecardGetter.getScorecardList();

			/*compare scorecared with course.getHoleList()*/
			assertTrue(scorecard.size() == 18);
			for (int i=0;i<scorecard.size();i++)
			{
				assertTrue(course.getHoles().get(i).equals(scorecard.get(i)));
			}

			/*Delete the new game from the DB*/
			gameDeletor = new DeleteGameFromDB(newGame.getScoreHistoryPK());
			gameDeletor.start();
			synchronized (gameDeletor)
			{
				gameDeletor.wait();
			}
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (newGameCreator != null)
				newGameCreator.close();
			if(gameDeletor != null)
				gameDeletor.close();
			if(courseGetter != null)
				courseGetter.close();
			if(scorecardGetter != null)
				scorecardGetter.close();

		}
	}

	@Test
	public void testAddScoreForHole()
	{
		NewGame newGameCreator = null;
		DeleteGameFromDB gameDeletor = null;
		GetCourse courseGetter = null;
		GetScorecard scorecardGetter = null;
		AddScoreForHole scoreAdder = null;
		User Bob = new User("Bob");
		GolfCourse course = null;
		Game newGame = null;

		try
		{
			/*create a new game*/
			courseGetter = new GetCourse(1);
			courseGetter.start();
			synchronized (courseGetter)
			{
				courseGetter.wait();
			}
			course = courseGetter.getCourse();
			newGameCreator = new NewGame(Bob,course);
			newGameCreator.start();
			synchronized (newGameCreator)
			{
				newGameCreator.wait();
			}
			newGame = newGameCreator.getGame();	

			/*score hole 1*/
			scoreAdder = new AddScoreForHole(newGame, newGame.getCourse().getHoles().get(0), 7);
			scoreAdder.start();
			synchronized (scoreAdder)
			{
				scoreAdder.wait();
			}

			/*get scorecard and check to see if score was stored in DB*/
			scorecardGetter = new GetScorecard(newGame);
			scorecardGetter.start();
			synchronized (scorecardGetter)
			{
				scorecardGetter.wait();
			}
			ArrayList<Hole> scorecard = scorecardGetter.getScorecardList();
			assertTrue(scorecard.get(0).getScore() == 7);

			gameDeletor = new DeleteGameFromDB(newGame.getScoreHistoryPK());
			gameDeletor.start();
			synchronized (gameDeletor)
			{
				gameDeletor.wait();
			}

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (newGameCreator != null)
				newGameCreator.close();
			if(gameDeletor != null)
				gameDeletor.close();
			if(courseGetter != null)
				courseGetter.close();
			if(scorecardGetter != null)
				scorecardGetter.close();
			if(scoreAdder != null)
				scoreAdder.close();
		}
	}

	@Test
	public void testComputeTotalScore()
	{
		GetScorecard cardGetter = null;
		ComputeTotalScore scoreGetter = null;

		try
		{
			/*set up a dummy game and course based on known data in DB*/
			Game g = new Game();
			GolfCourse c = new GolfCourse();
			c.setGolfCourseID(1);
			g.setScoreHistoryPK(1);
			g.setCourse(c);

			//get the scorecard
			cardGetter = new GetScorecard(g);
			cardGetter.start();
			synchronized (cardGetter)
			{
				cardGetter.wait();
			}
			ArrayList<Hole> scorecard = cardGetter.getScorecardList();
			c.setHoles(scorecard);

			scoreGetter = new ComputeTotalScore(g);
			scoreGetter.start();
			synchronized (scoreGetter)
			{
				scoreGetter.wait();
			}
			Integer totalScoreFromDB = scoreGetter.getTotalScore();
			Integer totalScoreCalculated = 0;
			for (Hole h : scorecard)
			{
				totalScoreCalculated += h.getScore();
			}
			assertEquals(totalScoreFromDB,totalScoreCalculated);

		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
		finally
		{
			if (cardGetter != null)
				cardGetter.close();
			if (scoreGetter != null)
				scoreGetter.close();
		}
	}

	@Test
	public void testGetUserHistory()
	{
		//instantiate a known user from the DB
		User user = new User("mgisoni", 1);
		GetUserHistory userHistoryGetter = new GetUserHistory(user);
		
		SendCourseDetailsToDB courseSender = null;
		DeleteCourseFromDB courseDeleter = null;
		GetCoursePrimaryKey keyGetter = null;
		AddHoleToCourse holeAdder = null;
		GetHoleMetadata metadataGetter = null;
		DeleteGameFromDB gameDeletor = null;
		NewGame newGameMaker = null;
		GetCourse courseGetter = null;
		Integer primaryKey = null;


		ArrayList<Hole> holes = new ArrayList<Hole>(18);		

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
		Hole h18 = new Hole(36,1,18,434,385,477,4,5,0);//(36,1,18,'434','385','477','4','5');

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

		/*create a new course*/
		GolfCourse newCourse = new GolfCourse("Course2", "West Street", "155", "26606", "555-965-5555", "www.course2.com", holes,null);
		Integer totalPar = 0;
		for (Hole h : holes)
		{
			totalPar += h.getPar();
		}
		newCourse.setTotalPar(totalPar);


		try 
		{
			/*send the new course to the DB*/
			courseSender = new SendCourseDetailsToDB(newCourse);
			courseSender.start();
			synchronized (courseSender)
			{
				courseSender.wait();
			}

			/*try to get key*/
			keyGetter = new GetCoursePrimaryKey(newCourse);
			keyGetter.start();
			synchronized (keyGetter)
			{
				keyGetter.wait();			
				primaryKey = keyGetter.getCourseKey();
				assertNotNull(primaryKey);
				newCourse.setGolfCourseID(primaryKey);//important to set the key locally after each creation
			}

			/*add holes to the new course*/	
			for (Hole h : holes)
			{
				holeAdder = new AddHoleToCourse(primaryKey, h);
				holeAdder.start();
				synchronized (holeAdder)
				{
					holeAdder.wait();
				}
			}

			/*get hole metadata for the new course*/
			metadataGetter = new GetHoleMetadata(newCourse);
			metadataGetter.start();
			synchronized (metadataGetter)
			{
				metadataGetter.wait();	
				ArrayList<Hole> fromDBList = metadataGetter.getHoleMetadataList();

				//test the paramaters of holes vs. fromDBList
				assertEquals(fromDBList.size(),holes.size());
				for (int i = 0; i<18; i++)
				{
					assertTrue(holes.get(i).equals(fromDBList.get(i)));
				}
			}

			/*run getCourse() method and compare*/
			GolfCourse courseFromDB = null;
			courseGetter = new GetCourse(primaryKey);
			courseGetter.start();
			synchronized (courseGetter)
			{
				courseGetter.wait();
				courseFromDB = courseGetter.getCourse();
				assertTrue(courseFromDB.equals(newCourse));
			}
			
			/*play a new game as user mgisoni*/
			newGameMaker = new NewGame(user, courseFromDB);
			newGameMaker.start();
			synchronized (newGameMaker)
			{
				newGameMaker.wait();
			}
			
			Game newGame = newGameMaker.getGame();
			
			/*add scores for each hole in the new game*/
			for (int i = 0;i<18;i++)
			{
				AddScoreForHole scoreAdder = new AddScoreForHole(newGame,newGame.getCourse().getHoles().get(i),5);
				scoreAdder.start();
				synchronized (scoreAdder)
				{
					scoreAdder.wait();
				}
			}
			
			/*computer total score*/
			ComputeTotalScore scoreSetter = new ComputeTotalScore(newGame);
			scoreSetter.start();
			synchronized (scoreSetter)
			{
				scoreSetter.wait();
			}
			
			/*get user history list*/
			userHistoryGetter.start();
			synchronized (userHistoryGetter)
			{
				userHistoryGetter.wait();
			}
			ArrayList<HistoryObject> historyList = userHistoryGetter.getHistoryList();			
			assertNotNull(historyList);
			assertEquals(2,historyList.size());			
			assertEquals(Integer.valueOf(89),historyList.get(0).getTotalScore());
			assertEquals(Integer.valueOf(90),historyList.get(1).getTotalScore());
			assertTrue(historyList.get(0).getTimestamp().compareTo(historyList.get(1).getTimestamp()) < 0);

			
			/*Delete the new game from the DB*/
			gameDeletor = new DeleteGameFromDB(newGame.getScoreHistoryPK());
			gameDeletor.start();
			synchronized (gameDeletor)
			{
				gameDeletor.wait();
			}
			
			/*delete new course including the new holes*/
			if (primaryKey != null)
			{
				courseDeleter = new DeleteCourseFromDB(primaryKey);
				courseDeleter.start();		
				synchronized (courseDeleter)
				{
					courseDeleter.wait();					
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			
		}		
		finally
		{

			if (courseSender != null)
				courseSender.close();
			if(courseDeleter != null)
				courseDeleter.close();
			if (keyGetter != null)
				keyGetter.close();
			if (holeAdder != null)
				holeAdder.close();
			if (metadataGetter != null)
				metadataGetter.close();
			if(courseGetter != null)
				courseGetter.close();
		}

	}
}
