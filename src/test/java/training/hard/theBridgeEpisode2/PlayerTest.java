package training.hard.theBridgeEpisode2;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

public class PlayerTest {

	@Test(expected = NoSuchElementException.class)
	public void testData06() throws IOException {
		testFile("theBridgeEpisode2-06.txt");
	}
	
	@Test(expected = NoSuchElementException.class)
	public void testData12() throws IOException {
		testFile("theBridgeEpisode2-12.txt");
	}
	
	private void testFile(String filename) throws IOException {
		Player player = new Player();
		player.in = new Scanner(new File("data/" + filename));
		player.run();
	}

}
