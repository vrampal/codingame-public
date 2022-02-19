package training.easy.horseRacingDuals;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

public class SolutionTest {

	@Test
	public void testSolution1() throws IOException {
		testFile("horseRacingDuals-01.txt");
	}
	
	@Test
	public void testSolution2() throws IOException {
		testFile("horseRacingDuals-02.txt");
	}
	
	@Test
	public void testSolution3() throws IOException {
		testFile("horseRacingDuals-03.txt");
	}
	
	private void testFile(String filename) throws IOException {
		Solution solution = new Solution();
		solution.in = new Scanner(new File("../data/" + filename));
		solution.run();
	}

}
