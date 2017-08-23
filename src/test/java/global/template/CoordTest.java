package global.template;

import static org.junit.Assert.*;

import org.junit.*;

public class CoordTest {
	
	@Test
	public void testDirection4() {
		Direction4 dir = Direction4.N;
		assertEquals(Direction4.E, dir.cw());
		assertEquals(Direction4.S, dir.oposite());
		assertEquals(Direction4.W, dir.ccw());
	}

	@Test
	public void testDirection6() {
		Direction6 dir = Direction6.NE;
		assertEquals(Direction6.E, dir.cw());
		assertEquals(Direction6.SW, dir.oposite());
		assertEquals(Direction6.NW, dir.ccw());
		
		assertEquals(1, dir.angle(Direction6.E));
		assertEquals(1, dir.angle(Direction6.NW));
		dir = Direction6.E;
		assertEquals(1, dir.angle(Direction6.SE));
		assertEquals(1, dir.angle(Direction6.NE));
		dir = Direction6.NW;
		assertEquals(1, dir.angle(Direction6.NE));
		assertEquals(1, dir.angle(Direction6.W));
	}

	@Test
	public void testDirection8() {
		Direction8 dir = Direction8.N;
		assertEquals(Direction8.NE, dir.cw());
		assertEquals(Direction8.S, dir.oposite());
		assertEquals(Direction8.NW, dir.ccw());

		assertEquals(1, dir.angle(Direction8.NE));
		assertEquals(1, dir.angle(Direction8.NW));
		dir = Direction8.E;
		assertEquals(1, dir.angle(Direction8.SE));
		assertEquals(1, dir.angle(Direction8.NE));
		dir = Direction8.NW;
		assertEquals(1, dir.angle(Direction8.N));
		assertEquals(1, dir.angle(Direction8.W));
	}
	
	@Test
	public void testCoord() {
		Coord first = new Coord(2, 3);
		Coord second = new Coord(7, 11);
		
		assertEquals(13, first.distanceMan(second));
		assertEquals(8, first.distanceCheb(second));
		assertEquals(9.4339811320566, first.distanceEcl(second), 1e-14);
		assertEquals(89, first.distanceEcl2(second));
		
		assertFalse(first.hashCode() == second.hashCode());
		assertFalse(first.equals(second));
		assertFalse(second.equals(first));
		
		Coord third = new Coord(2, 3);
		assertTrue(first.hashCode() == third.hashCode());
		assertTrue(first.equals(third));
		assertTrue(third.equals(first));
	}

	@Test
	public void testSegmentIntersect() {
		// Tested on GeoGebra
		// https://www.geogebra.org/m/aHF6k9rp
		Segment s1 = new Segment(new Coord(100, 168), new Coord(911, 395));
		Segment s2 = new Segment(new Coord(404, 506), new Coord(602, -149));
		Segment s3 = new Segment(new Coord(404, 506), new Coord(750, 960));
		assertTrue(s1.intersect(s2));
		assertTrue(s2.intersect(s1));
		assertFalse(s1.intersect(s3));
		assertFalse(s3.intersect(s1));
		
		Segment s4 = new Segment(new Coord(0, 0), new Coord(3, 4));
		Segment s5 = new Segment(new Coord(3, 4), new Coord(9, 12));
		Segment s6 = new Segment(new Coord(6, 8), new Coord(9, 12));
		assertTrue(s4.intersect(s5));
		assertTrue(s5.intersect(s4));
		assertFalse(s4.intersect(s6));
		assertFalse(s6.intersect(s4));
	}

}
