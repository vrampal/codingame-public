package training.medium.benderEpisode1;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
import org.junit.*;

public class SolutionTest {
	
	@Test
	public void testSolution1() throws IOException {
		testFile("bender1-01.txt");
	}
	
	@Test
	public void testSolution2() throws IOException {
		testFile("bender1-02.txt");
	}
	
	@Test
	public void testSolution3() throws IOException {
		testFile("bender1-03.txt");
	}
	
	@Test
	public void testSolution4() throws IOException {
		testFile("bender1-04.txt");
	}
	
	@Test
	public void testSolution5() throws IOException {
		testFile("bender1-05.txt");
	}
	
	@Test
	public void testSolution6() throws IOException {
		testFile("bender1-06.txt");
	}
	
	@Test
	public void testSolution7() throws IOException {
		testFile("bender1-07.txt");
	}
	
	@Test
	public void testSolution8() throws IOException {
		testFile("bender1-08.txt");
	}
	
	@Test
	public void testSolution9() throws IOException {
		testFile("bender1-09.txt");
	}
	
	@Test
	public void testSolution10() throws IOException {
		testFile("bender1-10.txt");
	}
	
	@Test
	public void testSolution11() throws IOException {
		testFile("bender1-11.txt");
	}
	
	@Test
	public void testSolution12() throws IOException {
		testFile("bender1-12.txt");
	}
	
	private void testFile(String filename) throws IOException {
		Solution solution = new Solution();
		solution.in = new Scanner(new File("data/" + filename));
		solution.run();
	}

}
