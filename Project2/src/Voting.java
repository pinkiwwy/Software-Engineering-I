import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedWriter;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 *
 * @author team21
 * Represents the Voting algorithm.
 *
 */

public class Voting{
	/**
	 * The ArrayList for storing ballots.
	 */
	protected static ArrayList<Ballot> ballots;
	/**
	 * The ArrayList for storing candidates.
	 */
	protected static ArrayList<Candidate> candidates;
	/**
	 * The ArrayList for storing elected candidates.
	 */
	protected static ArrayList<Candidate> electedCandidates = new ArrayList<Candidate>();
	/**
	 * The ArrayList for storing non-elected candidates.
	 */
	protected static ArrayList<Candidate> nonElectedCandidates = new ArrayList<Candidate>();
	/**
	 * The ArrayList for storing invalidated ballots.
	 */
	protected static ArrayList<Ballot> invalidateBallots = new ArrayList<Ballot>();
	/**
	 * The total number of seats.
	 */
	protected static int numSeat;

	/**
	 * Create a new Voting with the given numSeat, ballots and candidates.
	 * @param numSeat The total number of seats
	 * @param ballots The ArrayList of ballots obtained from csv file
	 * @param candidates The ArrayList of candidates obtained from csv file
	 */
	public Voting(int numSeat, ArrayList<Ballot> ballots, ArrayList<Candidate> candidates) {
		this.numSeat = numSeat;
		this.ballots = ballots;
		this.candidates = candidates;

	}

	/**
	 *
	 * The method is for plurality or STV to override
	 */
	public void generateShortReport() {
	}

	/**
	 * This method generate an output file in the directory using the ArrayList of
	 * electedCandidates, nonElectedCandidates, and invalidateBallots obtained from
	 * plurality or STV.
	 */
	public static void generateReport(){
		try (BufferedWriter bw = new BufferedWriter (new FileWriter("output.txt"))) {
			int count = 0;
			//only write to file when the number of elected candidates is larger than 0
			if (electedCandidates.size() > count){
				bw.write("Elected Candidates:");
				bw.newLine();
			}
		    while (electedCandidates.size() > count) {
		    	String line = electedCandidates.get(count).getName()+ ": "+
							electedCandidates.get(count).getVote();
		    	bw.write(line);
		    	bw.newLine();
		        count++;
		    }
		    bw.newLine();

		    count = 0;
		  //only write to file when the number of nonelected candidates is larger than 0
			if (nonElectedCandidates.size() > count){
				bw.write("Non-elected Candidates:");
				bw.newLine();
			}
		    while (nonElectedCandidates.size() > count) {
		    	String line = nonElectedCandidates.get(count).getName()+ ": "+ nonElectedCandidates.get(count).getVote();
		    	bw.write(line);
		    	bw.newLine();
		        count++;
		    }
		    bw.newLine();

		    count = 0;
			  //only write to file when the number of invalidated ballot is larger than 0
				if (invalidateBallots.size() > count){
					bw.write("Invalidated Ballots:");
					bw.newLine();
				}
			    while (invalidateBallots.size() > count) {
			    	String line = "Ballot ID: "+invalidateBallots.get(count).getBallotID();
			    	bw.write(line);
			    	bw.newLine();
			        count++;
			    }

			bw.close ();
			System.out.println("Output file name is output.txt");

		} catch (IOException e) {
			e.printStackTrace ();
		}
	}

	/**
	 *
	 * The method is for plurality or STV to override
	 */
	public void runAlgorithm(){
		//overriding method
	}

	/**
	 * Gets the ArrayList of ballots and redistribute it back to the original
	 * ballots
	 * @param ballots The new ArrayList of ballots that needs to be redistributed.
	 */
	public void redistribute(ArrayList<Ballot> ballots){
		int count = 0;
		//Adding each ballot that needs to be redistributed back to the original ballots arraylist
	    while (ballots.size() > count) {
	    	this.ballots.add(ballots.get(count));
	        count++;
	    }
	}

	/**
	 * Gets a boolean of shuffle option and shuffle the ArrayList of ballots.
	 * @param shuffle The user's shuffle option
	 */
	public static void shuffle(boolean shuffle){
		if (shuffle){
			//shuffle 4 times to maximize randomness
			for(int i = 0; i < 3; i++){
				Collections.shuffle(ballots);
			}
		}
	}
}
