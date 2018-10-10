import static org.hamcrest.CoreMatchers.not;
import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.Test;

public class MainTest {

	@Test
	public void shuffleTest(){

	    int[] firstB = {1, 0, 0, 0};
		Ballot ballot = new Ballot(firstB, 1);
		int[] secondB = {1, 0, 0, 0};
		Ballot secondBallot = new Ballot(secondB, 2);
		int[] thirdB = {1, 0, 0, 0};
		Ballot thirdBallot = new Ballot(thirdB, 3);
		int[] fouthB = {1, 0, 0, 0};
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
		ArrayList<Integer> voteOrderD = new ArrayList<Integer>();
		Candidate d = new Candidate("D", 0, voteOrderC);
		ArrayList<Candidate> can = new ArrayList<Candidate>();
		can.add(a);
		can.add(b);
		can.add(c);
		can.add(d);

		//Create Voting instance with numseat, ballots and candidates
		Voting v = new Voting(2, ballots, can);

		//create an identical arraylist as ballots
		ArrayList<Ballot> originalB = new ArrayList<Ballot>();
		originalB.add(ballot);
		originalB.add(secondBallot);
		originalB.add(thirdBallot);
		originalB.add(fouthBallot);

		//if main has two arguments input and second is off, shuffle turned off
	    Main.main(new String[] {"test1_unit.csv", "off"});
		//create plurality instance with 1 seat, defined ballots and can
		Plurality p = new Plurality(2,ballots, can);
		p.runAlgorithm();
		assertEquals(originalB, p.ballots);

		//if main has two arguments input and second is not off, shuffle turned on
		Main.main(new String[] {"test1_unit.csv", "abc"});
		//create plurality instance with 1 seat, defined ballots and can
		assertThat(originalB, not(p.ballots));

		//no second arguments, shuffle on
		Main.main(new String[] {"test1_unit.csv"});
		assertThat(originalB, not(p.ballots));

	}



	@Test
	public void parseFileTest(){
		//get file from resource folder
		File file = new File("test1_unit.csv");
		Main.parseFile(file);
		//set standard first line
		String first_line="p";
		assertEquals(first_line, Main.getAlg());

		File file2 = new File("test2.csv");
		Main.parseFile(file2);
		//set standard first line
		first_line="s";
		assertEquals(first_line, Main.getAlg());

	}

	@Test
	public void parseTest(){

	    int[] firstB = {1, 0, 0, 0};
		Ballot ballot = new Ballot(firstB, 1);
		int[] secondB = {1, 0, 0, 0};
		Ballot secondBallot = new Ballot(secondB, 2);
		int[] thirdB = {1, 0, 0, 0};
		Ballot thirdBallot = new Ballot(thirdB, 3);
		int[] fouthB = {1, 0, 0, 0};
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
		ArrayList<Integer> voteOrderD = new ArrayList<Integer>();
		Candidate d = new Candidate("D", 0, voteOrderC);
		ArrayList<Candidate> can = new ArrayList<Candidate>();
		can.add(a);
		can.add(b);
		can.add(c);
		can.add(d);

		//Create Voting instance with numseat, ballots and candidates
		Voting v = new Voting(2, ballots, can);

		//create an identical arraylist as ballots
		ArrayList<Ballot> originalB = new ArrayList<Ballot>();
		originalB.add(ballot);
		originalB.add(secondBallot);
		originalB.add(thirdBallot);
		originalB.add(fouthBallot);

		Main.main(new String[] {"test1_unit.csv"});

		//create plurality instance with 1 seat, defined ballots and can


		assertThat(originalB, not(v.ballots));

	}

}
