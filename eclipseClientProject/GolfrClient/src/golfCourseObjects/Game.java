package golfCourseObjects;

public class Game 
{
	private User user;
	private GolfCourse course;
	private Integer totalScore;
	private Integer currentOverUnder;
	private Integer scoreHistoryPK;

	/**
	 *Default constructor. 
	 */
	public Game() 
	{
		super();
		this.user = new User();
		this.course = new GolfCourse();
		this.totalScore = 0;
		this.currentOverUnder = 0;		
	}

	/**
	 * Full constructor.
	 * @param user
	 * @param course
	 * @param totalScore
	 * @param currentOverUnder
	 */
	public Game(User user, GolfCourse course, Integer totalScore,
			Integer currentOverUnder) 
	{
		super();
		if (user != null)
			this.user = user;
		else
			this.user = new User();
		if (course != null)
			this.course = course;
		else
			this.course = new GolfCourse();
		this.totalScore = totalScore;
		this.currentOverUnder = currentOverUnder;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return this.user;
	}
	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}
	/**
	 * @return the course
	 */
	public GolfCourse getCourse() {
		return this.course;
	}
	/**
	 * @param course the course to set
	 */
	public void setCourse(GolfCourse course) {
		this.course = course;
	}
	/**
	 * @return the totalScore
	 */
	public Integer getTotalScore() {
		return this.totalScore;
	}
	/**
	 * @param totalScore the totalScore to set
	 */
	public void setTotalScore(Integer totalScore) {
		this.totalScore = totalScore;
	}
	/**
	 * @return the currentOverUnder
	 */
	public Integer getCurrentOverUnder() {
		return this.currentOverUnder;
	}
	/**
	 * @param currentOverUnder the currentOverUnder to set
	 */
	public void setCurrentOverUnder(Integer currentOverUnder) {
		this.currentOverUnder = currentOverUnder;
	}

	/**
	 * @return the scoreHistoryPK
	 */
	public Integer getScoreHistoryPK() {
		return scoreHistoryPK;
	}

	/**
	 * @param scoreHistoryPK the scoreHistoryPK to set
	 */
	public void setScoreHistoryPK(Integer scoreHistoryPK) {
		this.scoreHistoryPK = scoreHistoryPK;
	}


}
