import java.util.ArrayList;

/**
 *
 * @author Team21
 * Represent a candidate.
 * Each ballot contains a candidate name, number of votes, and an integer
 * ArrayList of voteOrder.
 *
 */

public class Candidate{
	/**
	 *  The name of the candidate.
	 */
	private String name;
	/**
	 * The total number of votes received of the candidate.
	 */
	private int vote;
	/**
	 * An integer ArrayList of the ballotID of the votes received
	 */
	private ArrayList<Integer> voteOrder;


	/**
	 * Create a new candidate with the candidate name, number of votes, and an integer
	 * ArrayList of voteOrder
	 * @param name The name of the candidate
	 * @param vote The total number of votes this candidate receives
	 * @param voteOrder The ballotID of votes this candidate receives
	 */
	public Candidate(String name, int vote, ArrayList<Integer> voteOrder){
		this.name = name;
		this.vote = vote;
		this.voteOrder = voteOrder;
	}

	/**
	 * Gets the total number of votes this candidate received
	 * @return this candidate's total number of votes
	 */
	public int getVote(){
		return this.vote;
	}

	/**
	 * Increments the total number of votes by one.
	 */
	public void setVote(){
		this.vote += 1;
	}

	/**
	 * Gets the name of this candidate.
	 * @return the name of this candidate
	 */
	public String getName(){
		return this.name;
	}

	/**
	 * Gets the ballotID of the votes this candidate received.
	 * @return the ArrayList of ballotID of votes this candidate's received
	 */
	public ArrayList<Integer> getVoteOrder(){
		return this.voteOrder;
	}

	/**
	 * Changes the ballotID of the votes this candidate's received.
	 * @param ballot_id The new ArrayList of ballotID of the votes this candidate's received
	 */
	public void setVoteOrder(int ballot_id){
		this.voteOrder.add(ballot_id);
	}
}
