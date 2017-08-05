package training.medium.winamaxBattle;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import org.junit.*;

public class SolutionTest {

	private Solution solution;

	@Before
	public void setUp() throws Exception {
		solution = new Solution();
	}

	@Test
	public void testCard2Val() {
		assertEquals(2, solution.card2val("2D"));
		assertEquals(7, solution.card2val("7H"));
		assertEquals(10, solution.card2val("10S"));
		assertEquals(11, solution.card2val("JD"));
		assertEquals(14, solution.card2val("AS"));
	}

}
