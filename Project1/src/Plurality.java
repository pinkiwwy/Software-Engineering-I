import java.util.*;

/**
 * 
 * @author team21
 * The derived class of Voting.
 * Represents the plurality voting algorithm.
 *
 */

public class Plurality extends Voting {

	/**
	 * Create a new Plurality with the given numSeat, ballots and candidates.
	 * @param numSeat The total number of seats
	 * @param ballots The ArrayList of ballots obtained from csv file
	 * @param candidates The ArrayList of candidates obtained from csv file
	 */
  public Plurality(int numSeat,
              ArrayList<Ballot> ballots, ArrayList<Candidate> candidates) {
    super(numSeat, ballots, candidates);
  }

  /**
   * The method runs the plurality algorithm to elect candidates based on numSeat. 
   */
  public void runAlgorithm() {

    // Voting
    int numB = ballots.size();
    for (int i = 0; i < numB; i++) {
      int[] vote = ballots.get(i).getBallot();
      for (int j = 0; j < vote.length; j++) {
        if (vote[j] == 1) {
          candidates.get(j).setVote();
          candidates.get(j).setVoteOrder(i+1);
          break;
        }
      } // for int j... !
    } // for int i...

    // Determining the winner
    for (int i = 0; i < numSeat; i++) {
      int max = 0;
      for (int j = 0; j < candidates.size(); j++) {
        if (candidates.get(j).getVote() > candidates.get(max).getVote()) {
          max = j;
        }
        //tie
        if (candidates.get(j).getVote() == candidates.get(max).getVote()) {
          if (candidates.get(j).getVoteOrder().size() != 0) {
            if(candidates.get(j).getVoteOrder().get(0)
                < candidates.get(max).getVoteOrder().get(0)) {
              max = j;
            }
          }


	        }
      } // for int j...
      electedCandidates.add(candidates.get(max));
      candidates.remove(max);
    } // for int i...
    nonElectedCandidates = candidates;

  }

}
