package training.medium.scrabble;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

public class SolutionTest {

	@Test
	public void testRemoveLetter() {
		Solution solution = new Solution();

		Collection<String> possible = new TreeSet<>();
		possible.add("abcde");

		possible = solution.shorterWords(possible);
		assertEquals(true, possible.contains("bcde"));
		assertEquals(true, possible.contains("acde"));
		assertEquals(true, possible.contains("abde"));
		assertEquals(true, possible.contains("abce"));
		assertEquals(true, possible.contains("abcd"));
	}

}
