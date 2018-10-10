import java.util.ArrayList;
import java.lang.*;

/**
 * 
 * @author team21
 * The derived class of Voting.
 * Represents the STV voting algorithm with the use of droop quota.
 *
 */

public class STV extends Voting {

	/**
	 * Create a new STV with the given numSeat, ballots and candidates.
	 * @param numSeat The total number of seats
	 * @param ballots The ArrayList of ballots obtained from csv file
	 * @param candidates The ArrayList of candidates obtained from csv file
	 */
	public STV(int numSeat, ArrayList<Ballot> ballots, ArrayList<Candidate> candidates) {
		super(numSeat, ballots, candidates);
	}

	/**
	 * Calculate droop quota DQ with given numBallots and numSeat.
	 * @param numBallots The total number of ballots
	 * @param numSeat The total number of seats
	 * @return the calculated droop quota 
	 */
	public int calculateDQ(int numBallots, int numSeat) {
		return (int)Math.floor(numBallots/(numSeat + 1)) + 1;
	}

	/**
	 * The method runs the STV algorithm to elect candidates based on numSeat and DQ
	 * @param DQ The calculated droop quota
	 */
	public void runAlgorithm(int DQ) {
		int numB = ballots.size();
		boolean[] selected = new boolean[candidates.size()];
		for (int i = 0; i < selected.length; i++) {
			selected[i] = false;
		}

		// Voting
    for (int i = 0; i < numB; i++) {

			// The index to define the winner
			int winner = 99999;

			// At first, add vote just like Plurality
      int[] vote = ballots.get(i).getBallot();
      for (int j = 0; j < vote.length; j++) {

				if (vote[j] != -1) {
					if (winner == 99999) {
						winner = j;
					} else {
						if (vote[j] < vote[winner]) {
							winner = j;
						}
					}
				}
			} // for int j... !
			if (winner == 99999) {
				invalidateBallots.add(ballots.get(i));
			} else {
				candidates.get(winner).setVote();
				candidates.get(winner).setVoteOrder(i+1);
				// Once it reaches droop quota......
				if (candidates.get(winner).getVote() == DQ) {
					// First add that candidate to electedCandidates
					electedCandidates.add(candidates.get(winner));
					selected[winner] = true;
					// Change the whole ballots' vote with vote to that candidate to -1
					for (int k = 0; k < numB; k++) {
						int[] v = ballots.get(k).getBallot();
						v[winner] = -1;
						ballots.get(k).setBallot(v);
					} // for int k... !!
				} // if candidates.get(winner)...
			} // else
		} // for int i...

		while(electedCandidates.size() + nonElectedCandidates.size() != candidates.size()) {
			// To determine nonElected
			int nonElected = 0;
			for (int i = 0; i < candidates.size(); i++) {
				if (selected[i] == false) {
					if (candidates.get(i).getVote() < candidates.get(nonElected).getVote()) {
						nonElected = i;
					} else if (candidates.get(i).getVote() == candidates.get(nonElected).getVote()) {
						// tie
	        	if (candidates.get(i).getVoteOrder().size() != 0) {
	          	if(candidates.get(i).getVoteOrder().get(0)
	              	> candidates.get(nonElected).getVoteOrder().get(0)) {
	            	nonElected = i;
							}
						}
					}
				}
			} // for int i...
			nonElectedCandidates.add(candidates.get(nonElected));
			selected[nonElected] = true;
			for (int k = 0; k < numB; k++) {
				int[] v = ballots.get(k).getBallot();
				v[nonElected] = -1;
				ballots.get(k).setBallot(v);
			} // for int k... !!

			// Redistribute !!
			int countNon = nonElectedCandidates.size() - 1;
			ArrayList<Integer> order = nonElectedCandidates.get(countNon).getVoteOrder();
			for (int i = 0; i < order.size(); i++) {
				int[] vote = ballots.get(order.get(i) - 1).getBallot();
				int winner = 99999;
				for (int j = 0; j < vote.length; j++) {
					if (vote[j] != -1) {
						if (winner == 99999) {
							winner = j;
						} else {
							if (vote[j] < vote[winner]) {
								winner = j;
							} // if
						} // else
					} // if (vote[j] != -1)
				} // for int j... !
				if (winner == 99999) {
					invalidateBallots.add(ballots.get(i));
				} else {
					candidates.get(winner).setVote();
					candidates.get(winner).setVoteOrder(i+1);
					// Once it reaches droop quota......
					if (candidates.get(winner).getVote() == DQ) {
						// First add that candidate to electedCandidates
						electedCandidates.add(candidates.get(winner));
						selected[winner] = true;
						for (int j = 0; j < numB; j++) {
							int[] v = ballots.get(j).getBallot();
							v[winner] = -1;
							ballots.get(j).setBallot(v);
						} // for int j... !
					}
				}
			} // for int i...
		} // while

		/* If there are open seats, that means there are too many
		   invalidateBallots. */
		while (electedCandidates.size() < numSeat) {
			int countNon = nonElectedCandidates.size() - 1;
			electedCandidates.add(nonElectedCandidates.get(countNon));
			nonElectedCandidates.remove(countNon);
		} // while

	} // runAlgorithm()

}
