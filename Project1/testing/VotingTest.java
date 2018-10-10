import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class VotingTest {

	@Test
	public void shuffle_test(){
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

		//create an identical arraylist as ballots
		ArrayList<Ballot> originalB = new ArrayList<Ballot>();
		originalB.add(ballot);
		originalB.add(secondBallot);
		originalB.add(thirdBallot);
		originalB.add(fouthBallot);

		//testing for shuffle()
		v.shuffle(false);
		assertEquals(originalB, ballots);
		v.shuffle(true);
		assertThat(originalB, not(ballots));
	}

	@Test
	public void redistribute_Test(){
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

		//create an identical arraylist as ballots
		ArrayList<Ballot> originalB = new ArrayList<Ballot>();
		originalB.add(ballot);
		originalB.add(secondBallot);
		originalB.add(thirdBallot);
		originalB.add(fouthBallot);

		ballots.remove(ballot);
		ballots.remove(secondBallot);
		ballots.remove(thirdBallot);
		ballots.remove(fouthBallot);
		//empty ballots and redistribute the originalB back into ballots
		v.redistribute(originalB);
		assertEquals(originalB, ballots);
	}

	@Test
	public void generateReport_test() throws FileNotFoundException {
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

		v.electedCandidates.add(a);
		v.nonElectedCandidates.add(b);
		v.nonElectedCandidates.add(c);
		v.generateReport();

		File f = new File("output.txt");

		//test if output.txt exists
		assertTrue(f.exists());
		Scanner file = new Scanner(f);
		String line = file.nextLine();

		//test if there is output for elected lists
		assertEquals("Elected Candidates:", line);
		line = file.nextLine();
		assertEquals("A: 3", line);
		line = file.nextLine(); //skip line break
		line = file.nextLine();

		//test if there is output for non-elected lists
		assertEquals("Non-elected Candidates:", line);
		line = file.nextLine();
		assertEquals("B: 0", line);
	}

}
