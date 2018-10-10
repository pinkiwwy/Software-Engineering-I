import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class CandidateTest {

	@Test
	public void getter_test() {

		ArrayList<Integer> voteOrderA = new ArrayList<Integer>();
		voteOrderA.add(1);
		voteOrderA.add(3);
		voteOrderA.add(4);

		//test constructor
		Candidate a = new Candidate("A", 3, voteOrderA);

		assertEquals(3, a.getVote()); //test getVote()
		assertEquals("A", a.getName()); //test getName()
		assertEquals(voteOrderA, a.getVoteOrder()); //test getVoteOrder()
	}

	@Test
	public void setter_test() {

		ArrayList<Integer> voteOrder = new ArrayList<Integer>();


		//test constructor
		Candidate a = new Candidate("A", 3, voteOrder);

		//test setVote()
		a.setVote();
		assertEquals(4, a.getVote());

		ArrayList<Integer> voteOrderA = new ArrayList<Integer>();
		voteOrderA.add(1);

		a.setVoteOrder(1);
		assertEquals(voteOrderA, a.getVoteOrder());
	}
}
