package training.medium.scrabble;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import org.junit.*;

public class SolutionTest {

	@Test
	public void testRemoveLetter() {
		Solution solution = new Solution();

		Set<String> possible = new TreeSet<>();
		possible.add("abcde");

		possible = solution.shorterWords(possible);
		assertTrue(possible.contains("bcde"));
		assertTrue(possible.contains("acde"));
		assertTrue(possible.contains("abde"));
		assertTrue(possible.contains("abce"));
		assertTrue(possible.contains("abcd"));
	}

}
