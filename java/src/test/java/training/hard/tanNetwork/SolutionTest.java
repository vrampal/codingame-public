package training.hard.tanNetwork;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

import org.junit.*;

public class SolutionTest {

	@Test
	public void testHeuristic() {
		Scanner in;
		in = new Scanner("target,'target',,47.0,-2.0,,,,");
		BusStop target = new BusStop(in);
		
		in = new Scanner("target,'target',,47.0,-3.0,,,,");
		BusStop stop1 = new BusStop(in);
		in = new Scanner("target,'target',,47.0,-4.0,,,,");
		BusStop stop2 = new BusStop(in);
		in = new Scanner("target,'target',,47.0,-5.0,,,,");
		BusStop stop3 = new BusStop(in);
		
		Comparator<BusStop> heuristic = new DistanceFrom(target);
		Queue<BusStop> queue = new PriorityQueue<>(heuristic);
		
		queue.add(stop3);
		queue.add(stop1);
		queue.add(stop2);
		
		assertEquals(stop1, queue.poll());
		assertEquals(stop2, queue.poll());
		assertEquals(stop3, queue.poll());
	}

	@Test
	public void testData01() throws IOException {
		testFile("tanNetwork-01.txt");
	}
	
	@Test
	public void testData02() throws IOException {
		testFile("tanNetwork-02.txt");
	}
	
	@Test
	public void testData03() throws IOException {
		testFile("tanNetwork-03.txt");
	}
	
	@Test
	public void testData04() throws IOException {
		testFile("tanNetwork-04.txt");
	}
	
	@Test
	public void testData05() throws IOException {
		testFile("tanNetwork-05.txt");
	}
	
	@Test
	public void testData06() throws IOException {
		testFile("tanNetwork-06.txt");
	}
	
	private void testFile(String filename) throws IOException {
		Solution solution = new Solution();
		solution.in = new Scanner(new File("../data/" + filename));
		solution.run();
	}

}
