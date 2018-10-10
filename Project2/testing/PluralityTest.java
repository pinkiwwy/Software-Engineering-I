import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class PluralityTest {

	@Test
	public void runAlgorithm_Test() {
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
				Plurality p = new Plurality(1,ballots, can);
				p.runAlgorithm();

				//create the correct output of elected and nonelected candidates
				ArrayList<Candidate> electedcan = new ArrayList<Candidate>();
				electedcan.add(a);
				ArrayList<Candidate> nonelectedcan = new ArrayList<Candidate>();
				nonelectedcan.add(b);
				nonelectedcan.add(c);

				//create if p.electedCandidates is equal to the standard output
				assertEquals(electedcan, p.electedCandidates);
				//create if p.nonElectedCandidates is equal to the standard output
				assertEquals(nonelectedcan, p.nonElectedCandidates);

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
		Voting v = new Voting(1, ballots, can);

		v.runAlgorithm();
		v.generateShortReport();

		File f = new File("short_report.txt");

		//test if short_report.txt exists
		assertTrue(f.exists());
		Scanner file = new Scanner(f);
		String line = file.nextLine();
		line=file.nextLine();

		//test if the format of short report is correct
		assertEquals("Voting algorithm: Plurality", line);
		line = file.nextLine();
		assertEquals("Number of seats: 1", line);

	}


}
