package controller;

import java.sql.SQLException;

import golfCourseObjects.Game;
import golfCourseObjects.Hole;

public class AddScoreForHole extends SQLQueries {

	private Game game;
	private Hole hole;
	private Integer score;

	public AddScoreForHole() {
		super();
	}

	public AddScoreForHole(Game game, Hole hole, Integer score) {
		super();
		this.game = game;
		this.hole = hole;
		this.score = score;
	}

	private void connectAndSendScoreToDB() {

		super.connect();
		try {
			super.addScoreForHole(game, hole, score);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			this.close();

		}

	}
	
	public void sendScoreToDB(Game gameIn, Hole holeIn, Integer scoreIn)
	{
		this.game = gameIn;
		this.hole = holeIn;
		this.score = scoreIn;
		
		connectAndSendScoreToDB();
	}

}
