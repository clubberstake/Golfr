package com.golfrclient.test;

import golfCourseObjects.GolfCourse;
import golfCourseObjects.Hole;
import golfCourseObjects.User;
import controller.SQLQueries;

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
	private SQLQueries DBConnection;
	private Connection connection;
	private User user;


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
		if (DBConnection == null || DBConnection.getConnection() == null)
		{
			try
			{
				this.DBConnection = new SQLQueries();
				this.DBConnection.start();			
			} 
			catch (Exception e) 
			{			
				e.printStackTrace();
				fail();
			}	
		}
	}

	/**
	 * Tears down the test fixture. 
	 * (Called after every test case method.)
	 */
	@After
	public void tearDown()
	{
		try 
		{
			this.DBConnection.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Test
	public void testGetCourseListFromDB()
	{


		try
		{

			ArrayList<GolfCourse> courses = DBConnection.getCourseListFromDB();
			assertTrue(courses.size() >0 && courses.get(0).getCourseName().equalsIgnoreCase("course1"));
			ArrayList<Hole> holes = DBConnection.getHoleListNoScore(courses.get(0));
			assertTrue(holes.size() == 18);

			for (int i=0;i<18;i++)
			{
				assertTrue(holes.get(i).getHoleNumber() == i+1);
			}

			GolfCourse newCourse = new GolfCourse("Course2", "West Street", "155", "26606", "555-965-5555", "www.course2.com", null,null);
			DBConnection.sendCourseDetailsToDB(newCourse);			
			assertNotNull(DBConnection.getCoursePrimaryKey(newCourse));
			System.out.println(DBConnection.getCoursePrimaryKey(newCourse));
		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail();
		}
		finally
		{
				DBConnection.close();
		}
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
			fail();
			throw new RuntimeException("Cannot find the driver in the classpath!", e);
		}
	}

	@Test
	public void testConnectToDB()
	{


		try 
		{
			assertNotNull(DBConnection);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			fail();
		}
		finally
		{
			DBConnection.close();
		}

	}




	@Test
	public void testAddandDeleteCourseFromDB()
	{

		try
		{
			fail();


		}
		catch (Exception e)
		{
			e.printStackTrace();
			fail();
		}
		finally
		{
			//	DBConnection.close();

		}
	}


}
