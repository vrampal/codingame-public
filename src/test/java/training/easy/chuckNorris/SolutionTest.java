package training.easy.chuckNorris;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;
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
		assertTrue(splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertFalse(splitter.hasNextBit());
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
		assertTrue(splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(0, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertTrue(splitter.hasNextBit());
		assertEquals(1, splitter.getNextBit());
		assertFalse(splitter.hasNextBit());
	}

	@Test
	public void test2b() {
		Splitter splitter = new Splitter("CC");
		StringBuilder buffOut = new StringBuilder();
		solution.encode(splitter, buffOut);
		assertEquals("0 0 00 0000 0 000 00 0000 0 00", buffOut.toString());
	}

}
