package training.easy.chuckNorris;

import static org.junit.Assert.*;

import org.junit.*;

public class SolutionTest {

	private Solution solution;

	@Before
	public void setUp() throws Exception {
		solution = new Solution();
	}

	@Test
	public void test1a() {
		Splitter splitter = new Splitter("C");
		assertEquals(true, splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertEquals(false, splitter.hasNextBit());
	}

	@Test
	public void test1b() {
		Splitter splitter = new Splitter("C");
		StringBuilder buffOut = new StringBuilder();
		solution.encode(splitter, buffOut);
		assertEquals("0 0 00 0000 0 00", buffOut.toString());
	}

	@Test
	public void test2a() {
		Splitter splitter = new Splitter("CC");
		assertEquals(true, splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertEquals(true, splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertEquals(false, splitter.hasNextBit());
	}

	@Test
	public void test2b() {
		Splitter splitter = new Splitter("CC");
		StringBuilder buffOut = new StringBuilder();
		solution.encode(splitter, buffOut);
		assertEquals("0 0 00 0000 0 000 00 0000 0 00", buffOut.toString());
	}

}
