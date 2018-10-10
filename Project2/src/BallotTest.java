import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class BallotTest {

	@Test
	public void getterTest() {
		int[] b = {1, -1, 4};
		//constructor
		Ballot ballot = new Ballot(b, 3);

		assertEquals(b, ballot.getBallot()); //test getBallot()
		assertEquals(3, ballot.getBallotID()); //test getBallotID()

	}
	@Test
	public void setterTest() {
		int[] b = {1, -1, 4};
		//constructor
		Ballot ballot = new Ballot(b, 3);

		ballot.setBallotID(4); //test setBallotID()
		assertEquals(4, ballot.getBallotID());

		int[] newB = {2, 1, 3};
		ballot.setBallot(newB); //test setBallot()
		assertEquals(newB, ballot.getBallot());
	}

}
