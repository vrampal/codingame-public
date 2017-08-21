package training.medium.theLastCrusadeEpisode1;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

public class PlayerTest {

	@Test(expected = NoSuchElementException.class)
	public void testData05() throws IOException {
		testFile("theLastCrusadeEpisode1-05.txt");
	}
	
	private void testFile(String filename) throws IOException {
		Player player = new Player();
		player.in = new Scanner(new File("data/" + filename));
		player.run();
	}

}
