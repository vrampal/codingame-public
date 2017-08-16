package training.hard.superComputer;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

public class SolutionTest {

	@Test
	public void testData01() throws IOException {
		testFile("superComputer-01.txt");
	}
	
	@Test
	public void testData02() throws IOException {
		testFile("superComputer-02.txt");
	}
	
	@Test
	public void testData03() throws IOException {
		testFile("superComputer-03.txt");
	}
	
	private void testFile(String filename) throws IOException {
		Solution solution = new Solution();
		solution.in = new Scanner(new File("data/" + filename));
		solution.run();
	}

}
