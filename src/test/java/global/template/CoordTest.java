package global.template;

import static org.junit.Assert.*;

import java.io.*;
import java.util.*;

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
		Coord pos1 = new Coord(2, 3);
		Coord pos2 = new Coord(7, 11);
		Coord pos3 = new Coord(new Scanner("2 3"));

		CubeCoord pos1Cube = new CubeCoord(1, -4, 3);
		CubeCoord pos2Cube = new CubeCoord(2, -13, 11);
		CubeCoord pos3Cube = new CubeCoord(1, -4, 3);

		// Conversions
		assertEquals(pos1Cube, pos1.toCubeCoord());
		assertEquals(pos2Cube, pos2.toCubeCoord());

		assertEquals(pos1, pos1Cube.toOffsetCoord());
		assertEquals(pos2, pos2Cube.toOffsetCoord());

		// Distances
		assertEquals(13, pos1.distanceMan(pos2));
		assertEquals(13, pos2.distanceMan(pos1));

		assertEquals(9, pos1Cube.distanceHexa(pos2Cube));
		assertEquals(9, pos2Cube.distanceHexa(pos1Cube));

		assertEquals(9, pos1.distanceHexa(pos2));
		assertEquals(9, pos2.distanceHexa(pos1));

		assertEquals(8, pos1.distanceCheb(pos2));
		assertEquals(8, pos2.distanceCheb(pos1));

		assertEquals(9.4339811320566, pos1.distanceEcl(pos2), 1e-14);
		assertEquals(9.4339811320566, pos2.distanceEcl(pos1), 1e-14);

		// hashCode equals on Coord
		assertEquals(false, pos1.hashCode() == pos2.hashCode());

		assertEquals(true, pos1.hashCode() == pos3.hashCode());

		assertEquals(false, pos1.equals(pos2));
		assertEquals(false, pos2.equals(pos1));

		assertEquals(true, pos1.equals(pos3));
		assertEquals(true, pos3.equals(pos1));

		assertEquals(true, pos1.equals(pos1));
		assertEquals(false, pos1.equals(null));

		// hashCode equals on CubeCoord
		assertEquals(false, pos1Cube.hashCode() == pos2Cube.hashCode());

		assertEquals(false, pos1Cube.equals(pos2Cube));
		assertEquals(false, pos2Cube.equals(pos1Cube));

		assertEquals(true, pos1Cube.hashCode() == pos3Cube.hashCode());

		assertEquals(true, pos1Cube.equals(pos3Cube));
		assertEquals(true, pos3Cube.equals(pos1Cube));

		assertEquals(true, pos1Cube.equals(pos1Cube));
		assertEquals(false, pos1Cube.equals(null));
	}

	@Test
	public void testAddDirection() {
		Coord pos1 = new Coord(8, 3);
		Coord pos1N = new Coord(8, 2);
		Coord pos1NE = new Coord(9, 2);
		Coord pos2E = new Coord(9, 3);

		CubeCoord posCube = pos1.toCubeCoord();
		CubeCoord posNECube = pos1NE.toCubeCoord();
		CubeCoord posECube = pos2E.toCubeCoord();

		assertEquals(pos1N, pos1.add(Direction4.N));
		assertEquals(pos2E, pos1.add(Direction4.E));

		assertEquals(pos1NE, pos1.add(Direction6.NE));
		assertEquals(pos2E, pos1.add(Direction6.E));

		assertEquals(posNECube, posCube.add(Direction6.NE));
		assertEquals(posECube, posCube.add(Direction6.E));

		assertEquals(pos1N, pos1.add(Direction8.N));
		assertEquals(pos1NE, pos1.add(Direction8.NE));
		assertEquals(pos2E, pos1.add(Direction8.E));

		for (Direction4 dir : Direction4.values()) {
			assertEquals(1, pos1.distanceMan(pos1.add(dir)));
			assertEquals(2, pos1.distanceMan(pos1.add(dir).add(dir)));
			assertEquals(3, pos1.distanceMan(pos1.add(dir).add(dir).add(dir)));
		}

		for (Direction6 dir : Direction6.values()) {
			assertEquals(1, pos1.distanceHexa(pos1.add(dir)));
			assertEquals(2, pos1.distanceHexa(pos1.add(dir).add(dir)));
			assertEquals(3, pos1.distanceHexa(pos1.add(dir).add(dir).add(dir)));

			assertEquals(1, posCube.distanceHexa(posCube.add(dir)));
			assertEquals(2, posCube.distanceHexa(posCube.add(dir).add(dir)));
			assertEquals(3, posCube.distanceHexa(posCube.add(dir).add(dir).add(dir)));
		}

		for (Direction8 dir : Direction8.values()) {
			assertEquals(1, pos1.distanceCheb(pos1.add(dir)));
			assertEquals(2, pos1.distanceCheb(pos1.add(dir).add(dir)));
			assertEquals(3, pos1.distanceCheb(pos1.add(dir).add(dir).add(dir)));
		}
	}

	@Test
	public void testSegmentIntersect() {
		// Tested on GeoGebra
		// https://www.geogebra.org/m/aHF6k9rp
		Segment s1 = new Segment(new Coord(100, 168), new Coord(911, 395));
		Segment s2 = new Segment(new Coord(404, 506), new Coord(602, -149));
		Segment s3 = new Segment(new Coord(404, 506), new Coord(750, 960));

		assertEquals(true, s1.intersect(s2));
		assertEquals(true, s2.intersect(s1));

		assertEquals(false, s1.intersect(s3));
		assertEquals(false, s3.intersect(s1));

		Segment s4 = new Segment(new Coord(0, 0), new Coord(3, 4));
		Segment s5 = new Segment(new Coord(3, 4), new Coord(9, 12));
		Segment s6 = new Segment(new Coord(6, 8), new Coord(9, 12));

		assertEquals(true, s4.intersect(s5));
		assertEquals(true, s5.intersect(s4));

		assertEquals(false, s4.intersect(s6));
		assertEquals(false, s6.intersect(s4));
	}

	@Test
	public void testBoard() throws IOException {
		Board board = new Board(new Scanner(new File("data/boardTest-01.txt")));

		assertEquals(34, board.width);
		assertEquals(19, board.height);

		Coord pos1 = new Coord(7, 12);
		Coord pos2 = new Coord(27, 3);
		Coord pos3 = new Coord(0, 0);
		Coord pos4 = new Coord(33, 18);

		assertEquals(true, board.cellExist(pos1));
		assertEquals(true, board.cellExist(pos2));
		assertEquals(true, board.cellExist(pos3));
		assertEquals(true, board.cellExist(pos4));

		assertEquals(false, board.cellExist(new Coord(-2, 3)));
		assertEquals(false, board.cellExist(new Coord(3, -2)));
		assertEquals(false, board.cellExist(new Coord(72, 3)));
		assertEquals(false, board.cellExist(new Coord(3, 72)));

		assertEquals('1', board.getCellAt(pos1));
		assertEquals('2', board.getCellAt(pos2));
		assertEquals('3', board.getCellAt(pos3));
		assertEquals('4', board.getCellAt(pos4));

		assertEquals(pos1, board.findFirst('1'));
		assertEquals(pos2, board.findFirst('2'));
		assertEquals(null, board.findFirst('5'));

		Zone zone1 = board.getZoneAt(pos1);
		Zone zone2 = board.getZoneAt(pos2);
		assertSame(zone1, zone2);

		Zone zone3 = board.getZoneAt(new Coord(17, 15));
		Zone zone4 = board.getZoneAt(new Coord(4, 16));
		assertEquals(35, zone3.size());
		assertEquals(1, zone4.size());

		board.setCellAt(pos1, 'A');
		board.setCellAt(pos2, 'B');

		assertEquals('A', board.getCellAt(pos1));
		assertEquals('B', board.getCellAt(pos2));
	}

}
