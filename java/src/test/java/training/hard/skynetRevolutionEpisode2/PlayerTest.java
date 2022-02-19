package training.hard.skynetRevolutionEpisode2;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

public class PlayerTest {

	@Test(expected = NoSuchElementException.class)
	public void testData04() throws IOException {
		testFile("skynet2-04.txt");
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testData05() throws IOException {
		testFile("skynet2-05.txt");
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testData06() throws IOException {
		testFile("skynet2-06.txt");
	}
	
	private void testFile(String filename) throws IOException {
		Player player = new Player();
		player.in = new Scanner(new File("../data/" + filename));
		player.run();
	}

}
