package training.medium.aCoinGuessingGame;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import org.junit.Test;

public class SolutionTest {

	@Test
	public void testData01() throws IOException {
		testFile("aCoinGuessingGame-01.txt");
	}
	
	@Test
	public void testData02() throws IOException {
		testFile("aCoinGuessingGame-02.txt");
	}
	
	@Test
	public void testData03() throws IOException {
		testFile("aCoinGuessingGame-03.txt");
	}
	
	@Test
	public void testData04() throws IOException {
		testFile("aCoinGuessingGame-04.txt");
	}
	
	private void testFile(String filename) throws IOException {
		Solution solution = new Solution();
		solution.in = new Scanner(new File("../data/" + filename));
		solution.run();
	}

}
