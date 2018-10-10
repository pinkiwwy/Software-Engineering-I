import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class STVTest {

	@Test
	public void DQ_test() {
		//Initialize ArrayList ballots
				int[] firstB = {1, -1, 2};
				Ballot ballot = new Ballot(firstB, 3);
				int[] secondB = {3, 2, 1};
				Ballot secondBallot = new Ballot(secondB, 5);
				int[] thirdB = {1, 3, -1};
				Ballot thirdBallot = new Ballot(thirdB, 6);
				int[] fouthB = {2, 3, 1};
				Ballot fouthBallot = new Ballot(fouthB, 7);
				ArrayList<Ballot> ballots = new ArrayList<Ballot>();
				//adding enough Ballots for shuffle
				ballots.add(ballot);
				ballots.add(secondBallot);
				ballots.add(thirdBallot);
				ballots.add(fouthBallot);

				//Initialize Candidates A, B and C
				ArrayList<Integer> voteOrderA = new ArrayList<Integer>();
				Candidate a = new Candidate("A", 0, voteOrderA);
				ArrayList<Integer> voteOrderB = new ArrayList<Integer>();
				Candidate b = new Candidate("B", 0, voteOrderB);
				ArrayList<Integer> voteOrderC = new ArrayList<Integer>();
				Candidate c = new Candidate("C", 0, voteOrderC);

				ArrayList<Candidate> can = new ArrayList<Candidate>();
				can.add(a);
				can.add(b);
				can.add(c);

				//create plurality instance with 1 seat, defined ballots and can
				STV s = new STV(1,ballots, can);

				//check if Math.floor works
				int DQ = s.calculateDQ(20, 2);
				assertEquals(DQ, 7);
				//check other cases for calculation
				DQ = s.calculateDQ(4, 1);
				assertEquals(DQ, 3);

	}

	@Test
	public void runAlgorithm_test() {
		//Initialize ArrayList ballots
		int[] firstB = {1, 0, 0};
		Ballot ballot = new Ballot(firstB, 1);
		int[] secondB = {1, 0, 0};
		Ballot secondBallot = new Ballot(secondB, 2);
		int[] thirdB = {1, 0, 0};
		Ballot thirdBallot = new Ballot(thirdB, 3);
		int[] fouthB = {1, 0, 0};
		Ballot fouthBallot = new Ballot(fouthB, 4);
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();
		//adding enough Ballots for shuffle
		ballots.add(ballot);
		ballots.add(secondBallot);
		ballots.add(thirdBallot);
		ballots.add(fouthBallot);

		//Initialize Candidates A, B and C
		ArrayList<Integer> voteOrderA = new ArrayList<Integer>();
		Candidate a = new Candidate("A", 0, voteOrderA);
		ArrayList<Integer> voteOrderB = new ArrayList<Integer>();
		Candidate b = new Candidate("B", 0, voteOrderB);
		ArrayList<Integer> voteOrderC = new ArrayList<Integer>();
		Candidate c = new Candidate("C", 0, voteOrderC);

		ArrayList<Candidate> can = new ArrayList<Candidate>();
		can.add(a);
		can.add(b);
		can.add(c);

		//create STV instance with 1 seat, defined ballots and can
		STV s = new STV(1,ballots, can);

		int DQ = s.calculateDQ(4, 1);
		s.runAlgorithm(DQ);
		for (int i=0; i<3; i++){
			System.out.println(can.get(i).getVote());
		}
		//create the correct output of elected and nonelected candidates
		ArrayList<Candidate> electedcan = new ArrayList<Candidate>();
		electedcan.add(b);
		//electedcan.add(c);
		ArrayList<Candidate> nonelectedcan = new ArrayList<Candidate>();
		nonelectedcan.add(a);
		nonelectedcan.add(c);
		//create standard output for invalidated ballot
		ArrayList<Ballot> invalidate = new ArrayList<Ballot>();
		invalidate.add(secondBallot);	//only secondBallot is not redistributed as A has reached DQ

		//check if p.electedCandidates is equal to the standard output
		assertEquals(electedcan, s.electedCandidates);
		//check if p.nonElectedCandidates is equal to the standard output
		assertEquals(nonelectedcan, s.nonElectedCandidates);

		//check if p.noninvalidated is equal to the standard output
		//assertEquals(invalidate, s.invalidateBallots);
	}

	@Test
	public void generateShortReport_test() throws FileNotFoundException{
		//Initialize ArrayList ballots
		int[] firstB = {1, -1, 2};
		Ballot ballot = new Ballot(firstB, 3);
		int[] secondB = {3, 2, 1};
		Ballot secondBallot = new Ballot(secondB, 5);
		int[] thirdB = {1, 3, -1};
		Ballot thirdBallot = new Ballot(thirdB, 6);
		int[] fouthB = {2, 3, 1};
		Ballot fouthBallot = new Ballot(fouthB, 7);
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();
		//adding enough Ballots for shuffle
		ballots.add(ballot);
		ballots.add(secondBallot);
		ballots.add(thirdBallot);
		ballots.add(fouthBallot);

		//Initialize Candidates A, B and C
		ArrayList<Integer> voteOrderA = new ArrayList<Integer>();
		voteOrderA.add(3);
		voteOrderA.add(2);
		voteOrderA.add(6);
		Candidate a = new Candidate("A", 3, voteOrderA);
		ArrayList<Integer> voteOrderB = new ArrayList<Integer>();
		Candidate b = new Candidate("B", 0, voteOrderB);
		ArrayList<Integer> voteOrderC = new ArrayList<Integer>();
		voteOrderC.add(5);
		Candidate c = new Candidate("C", 1, voteOrderC);

		ArrayList<Candidate> can = new ArrayList<Candidate>();
		can.add(a);
		can.add(b);
		can.add(c);
		//Create Voting instance with numseat, ballots and candidates
		STV s = new STV(1, ballots, can);

		s.runAlgorithm();
		s.generateShortReport();

		File f = new File("short_report.txt");

		//test if short_report.txt exists
		assertTrue(f.exists());
		Scanner file = new Scanner(f);
		String line = file.nextLine();
		line=file.nextLine();

		//test if the format of short report is correct
		assertEquals("Voting algorithm: STV", line);
		line = file.nextLine();
		assertEquals("Number of seats: 1", line);

	}

}
