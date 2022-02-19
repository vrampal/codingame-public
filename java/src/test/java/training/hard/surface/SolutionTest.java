package training.hard.surface;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

public class SolutionTest {

	@Test
	public void testData01() throws IOException {
		testFile("surface-01.txt");
	}
	
	@Test
	public void testData02() throws IOException {
		testFile("surface-02.txt");
	}
	
	@Test
	public void testData03() throws IOException {
		testFile("surface-03.txt");
	}
	
	@Test
	public void testData04() throws IOException {
		testFile("surface-04.txt");
	}
	
	@Test
	public void testData05() throws IOException {
		testFile("surface-05.txt");
	}
	
	@Test
	public void testData06() throws IOException {
		testFile("surface-06.txt");
	}
	
	@Test
	public void testData07() throws IOException {
		testFile("surface-07.txt");
	}
	
	@Test
	public void testData08() throws IOException {
		testFile("surface-08.txt");
	}
	
	private void testFile(String filename) throws IOException {
		Solution solution = new Solution();
		solution.in = new Scanner(new File("../data/" + filename));
		solution.run();
	}

}
