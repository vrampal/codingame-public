package training.easy.temperatures;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

public class SolutionTest {

	@Test
	public void testSolution1() throws IOException {
		testFile("temperatures-01.txt");
	}
	
	@Test
	public void testSolution2() throws IOException {
		testFile("temperatures-02.txt");
	}
	
	@Test
	public void testSolution3() throws IOException {
		testFile("temperatures-03.txt");
	}
	
	@Test
	public void testSolution4() throws IOException {
		testFile("temperatures-04.txt");
	}
	
	@Test
	public void testSolution5() throws IOException {
		testFile("temperatures-05.txt");
	}
	
	@Test
	public void testSolution6() throws IOException {
		testFile("temperatures-06.txt");
	}
	
	private void testFile(String filename) throws IOException {
		Solution solution = new Solution();
		solution.in = new Scanner(new File("../data/" + filename));
		solution.run();
	}

}
