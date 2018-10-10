/**
 *
 * @author Team21
 * Represent a ballot.
 * Each ballot contains a BallotID and an int array of votes.
 *
 */
public class Ballot{
	/**
	 * The vote ranking for each candidate.
	 */
	private int[] ballot;
	/**
	 * The identifier of a ballot.
	 */
	private int ballotID;

	/**
	 *
	 * Create a new Ballot with the given int array of vote ranking
	 * and Ballot ID
	 * @param b The int array of vote rankings.
	 * @param ID The unique ID of the ballot.
	 */
	public Ballot(int[] b, int ID){
		ballot = b;
		ballotID = ID;
	}

	/**
	 * This method gets the vote ranking  of the ballot.
	 * @return an int array of this ballot's rankings
	 */
	public int[] getBallot(){
		return ballot;
	}

	/**
	 * Changes the ranking of ballot.
	 * @param newBallot This ballot's new ranking.
	 * 					Should be an int array.
	 */
	public void setBallot(int[] newBallot){
		ballot = newBallot;
	}

	/**
	 * Gets the ID of this ballot.
	 * @return this ballot's ID
	 */
	public int getBallotID(){
		return ballotID;
	}

	/**
	 * Changes the ID of this ballot.
	 * @param newBallotID The new ID for this ballot.
	 */
	public void setBallotID(int newBallotID){
		ballotID = newBallotID;
	}
}
