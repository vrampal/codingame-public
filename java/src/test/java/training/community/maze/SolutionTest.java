package training.community.maze;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

public class SolutionTest {

	@Test
	public void testData01() throws IOException {
		testFile("maze-01.txt");
	}
	
	@Test
	public void testData02() throws IOException {
		testFile("maze-02.txt");
	}
	
	@Test
	public void testData03() throws IOException {
		testFile("maze-03.txt");
	}
	
	@Test
	public void testData04() throws IOException {
		testFile("maze-04.txt");
	}
	
	@Test
	public void testData05() throws IOException {
		testFile("maze-05.txt");
	}
	
	private void testFile(String filename) throws IOException {
		Solution solution = new Solution();
		solution.in = new Scanner(new File("../data/" + filename));
		solution.run();
	}

}
