package global.benchmark;

import org.openjdk.jmh.annotations.*;

enum Direction4 {
	N(new Coord( 0, -1)),
	E(new Coord(+1,  0)),
	S(new Coord( 0, +1)),
	W(new Coord(-1,  0));

	final Coord coord;

	private Direction4(Coord coord) {
		this.coord = coord;
	}
}

enum Direction6 {
	NE(new Coord( 0, -1), new Coord(+1, -1), new CubeCoord(+1,  0, -1)),
	E( new Coord(+1,  0), new Coord(+1,  0), new CubeCoord(+1, -1,  0)),
	SE(new Coord( 0, +1), new Coord(+1, +1), new CubeCoord( 0, -1, +1)),
	SW(new Coord(-1, +1), new Coord( 0, +1), new CubeCoord(-1,  0, +1)),
	W( new Coord(-1,  0), new Coord(-1,  0), new CubeCoord(-1, +1,  0)),
	NW(new Coord(-1, -1), new Coord( 0, -1), new CubeCoord( 0, +1, -1));

	final Coord even;
	final Coord odd;
	final CubeCoord cubeCoord;

	private Direction6(Coord even, Coord odd, CubeCoord cube) {
		this.even = even;
		this.odd = odd;
		this.cubeCoord = cube;
	}
}

enum Direction8 {
	N( new Coord( 0, -1)),
	NE(new Coord(+1, -1)),
	E( new Coord(+1,  0)),
	SE(new Coord(+1, +1)),
	S( new Coord( 0, +1)),
	SW(new Coord(-1, +1)),
	W( new Coord(-1,  0)),
	NW(new Coord(-1, -1));

	final Coord coord;

	private Direction8(Coord coord) {
		this.coord = coord;
	}
}

class Coord {
	final int x;
	final int y;

	public Coord(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public CubeCoord toCubeCoord() {
		int newX = x - (y - (y & 1)) / 2;
		int newZ = y;
		int newY = -(newX + newZ);
		return new CubeCoord(newX, newY, newZ);
	}

	public Coord add(Coord other) {
		return new Coord(x + other.x, y + other.y);
	}

	public Coord add4m1(Direction4 dir) {
		switch (dir) {
		case N:  return new Coord(x,     y - 1);
		case E:  return new Coord(x + 1, y    );
		case S:  return new Coord(x,     y + 1);
		case W:  return new Coord(x - 1, y    );
		default: throw new IllegalArgumentException("Invalid dir: " + dir);
		}
	}

	public Coord add4m2(Direction4 dir) {
		if (dir == Direction4.N) return new Coord(x,     y - 1);
		if (dir == Direction4.E) return new Coord(x + 1, y    );
		if (dir == Direction4.S) return new Coord(x,     y + 1);
		if (dir == Direction4.W) return new Coord(x - 1, y    );
		throw new IllegalArgumentException("Invalid dir: " + dir);
	}

	public Coord add4m3(Direction4 dir) {
		return add(dir.coord);
	}

	public Coord add6m1(Direction6 dir) {
		if ((y % 2) == 0) {
			// Even lines
			switch (dir) {
			case NE: return new Coord(x,     y - 1);
			case E:  return new Coord(x + 1, y    );
			case SE: return new Coord(x,     y + 1);
			case SW: return new Coord(x - 1, y + 1);
			case W:  return new Coord(x - 1, y    );
			case NW: return new Coord(x - 1, y - 1);
			default: throw new IllegalArgumentException("Invalid dir: " + dir);
			}
		} else {
			// Odd lines
			switch (dir) {
			case NE: return new Coord(x + 1, y - 1);
			case E:  return new Coord(x + 1, y    );
			case SE: return new Coord(x + 1, y + 1);
			case SW: return new Coord(x,     y + 1);
			case W:  return new Coord(x - 1, y    );
			case NW: return new Coord(x,     y - 1);
			default: throw new IllegalArgumentException("Invalid dir: " + dir);
			}
		}
	}

	public Coord add6m3(Direction6 dir) {
		if ((y % 2) == 0) {
			// Even lines
			return add(dir.even);
		} else {
			// Odd lines
			return add(dir.odd);
		}
	}

	public Coord add6m4(Direction6 dir) {
		return toCubeCoord().add6m3(dir).toCoord();
	}

	public Coord add8m1(Direction8 dir) {
		switch (dir) {
		case N:  return new Coord(x,     y - 1);
		case NE: return new Coord(x + 1, y - 1);
		case E:  return new Coord(x + 1, y    );
		case SE: return new Coord(x + 1, y + 1);
		case S:  return new Coord(x,     y + 1);
		case SW: return new Coord(x - 1, y + 1);
		case W:  return new Coord(x - 1, y    );
		case NW: return new Coord(x - 1, y - 1);
		default: throw new IllegalArgumentException("Invalid dir: " + dir);
		}
	}
	public Coord add8m2(Direction8 dir) {
		if (dir == Direction8.N)  return new Coord(x,     y - 1);
		if (dir == Direction8.NE) return new Coord(x + 1, y - 1);
		if (dir == Direction8.E)  return new Coord(x + 1, y    );
		if (dir == Direction8.SE) return new Coord(x + 1, y + 1);
		if (dir == Direction8.S)  return new Coord(x,     y + 1);
		if (dir == Direction8.SW) return new Coord(x - 1, y + 1);
		if (dir == Direction8.W)  return new Coord(x - 1, y    );
		if (dir == Direction8.NW) return new Coord(x - 1, y - 1);
		throw new IllegalArgumentException("Invalid dir: " + dir);
	}

	public Coord add8m3(Direction8 dir) {
		return add(dir.coord);
	}

}

class CubeCoord {
	final int x;
	final int y;
	final int z;

	public CubeCoord(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	Coord toCoord() {
		int newX = x + (z - (z & 1)) / 2;
		int newY = z;
		return new Coord(newX, newY);
	}

	public CubeCoord add(CubeCoord other) {
		return new CubeCoord(x + other.x, y + other.y, z + other.z);
	}

	public CubeCoord add6m1(Direction6 dir) {
		switch (dir) {
		case NE: return new CubeCoord(x + 1, y,     z - 1);
		case E:  return new CubeCoord(x + 1, y - 1, z    );
		case SE: return new CubeCoord(x,     y - 1, z + 1);
		case SW: return new CubeCoord(x - 1, y,     z + 1);
		case W:  return new CubeCoord(x - 1, y + 1, z    );
		case NW: return new CubeCoord(x,     y + 1, z - 1);
		default: throw new IllegalArgumentException("Invalid dir: " + dir);
		}
	}

	public CubeCoord add6m3(Direction6 dir) {
		return add(dir.cubeCoord);
	}
}

public class CoordAddBenchmark {

	@State(Scope.Benchmark)
	public static class BenchState4 {
		Coord pos = new Coord(2, 3);

		Direction4 dir1 = Direction4.N;
		Direction4 dir2 = Direction4.E;
		Direction4 dir3 = Direction4.S;
		Direction4 dir4 = Direction4.W;
	}

	@State(Scope.Benchmark)
	public static class BenchState6 {
		Coord pos = new Coord(2, 3);

		Direction6 dir1 = Direction6.NE;
		Direction6 dir2 = Direction6.E;
		Direction6 dir3 = Direction6.SE;
		Direction6 dir4 = Direction6.SW;
		Direction6 dir5 = Direction6.W;
		Direction6 dir6 = Direction6.NW;
	}

	@State(Scope.Benchmark)
	public static class BenchState8 {
		Coord pos = new Coord(2, 3);

		Direction8 dir1 = Direction8.N;
		Direction8 dir2 = Direction8.NE;
		Direction8 dir3 = Direction8.E;
		Direction8 dir4 = Direction8.SE;
		Direction8 dir5 = Direction8.S;
		Direction8 dir6 = Direction8.SW;
		Direction8 dir7 = Direction8.W;
		Direction8 dir8 = Direction8.NW;
	}

	@State(Scope.Benchmark)
	public static class BenchStateCube6 {
		CubeCoord pos = new Coord(2, 3).toCubeCoord();

		Direction6 dir1 = Direction6.NE;
		Direction6 dir2 = Direction6.E;
		Direction6 dir3 = Direction6.SE;
		Direction6 dir4 = Direction6.SW;
		Direction6 dir5 = Direction6.W;
		Direction6 dir6 = Direction6.NW;
	}

	@Benchmark
	public Coord benchAdd4m1(BenchState4 state) {
		Coord pos = state.pos;
		pos = pos.add4m1(state.dir1);
		pos = pos.add4m1(state.dir2);
		pos = pos.add4m1(state.dir3);
		pos = pos.add4m1(state.dir4);
		return pos;
	}

	@Benchmark
	public Coord benchAdd4m2(BenchState4 state) {
		Coord pos = state.pos;
		pos = pos.add4m2(state.dir1);
		pos = pos.add4m2(state.dir2);
		pos = pos.add4m2(state.dir3);
		pos = pos.add4m2(state.dir4);
		return pos;
	}

	@Benchmark
	public Coord benchAdd4m3(BenchState4 state) {
		Coord pos = state.pos;
		pos = pos.add4m3(state.dir1);
		pos = pos.add4m3(state.dir2);
		pos = pos.add4m3(state.dir3);
		pos = pos.add4m3(state.dir4);
		return pos;
	}

	@Benchmark
	public Coord benchAdd6m1(BenchState6 state) {
		Coord pos = state.pos;
		pos = pos.add6m1(state.dir1);
		pos = pos.add6m1(state.dir2);
		pos = pos.add6m1(state.dir3);
		pos = pos.add6m1(state.dir4);
		pos = pos.add6m1(state.dir5);
		pos = pos.add6m1(state.dir6);
		return pos;
	}

	@Benchmark
	public Coord benchAdd6m3(BenchState6 state) {
		Coord pos = state.pos;
		pos = pos.add6m3(state.dir1);
		pos = pos.add6m3(state.dir2);
		pos = pos.add6m3(state.dir3);
		pos = pos.add6m3(state.dir4);
		pos = pos.add6m3(state.dir5);
		pos = pos.add6m3(state.dir6);
		return pos;
	}

	@Benchmark
	public Coord benchAdd6m4(BenchState6 state) {
		Coord pos = state.pos;
		pos = pos.add6m4(state.dir1);
		pos = pos.add6m4(state.dir2);
		pos = pos.add6m4(state.dir3);
		pos = pos.add6m4(state.dir4);
		pos = pos.add6m4(state.dir5);
		pos = pos.add6m4(state.dir6);
		return pos;
	}

	@Benchmark
	public Coord benchAdd8m1(BenchState8 state) {
		Coord pos = state.pos;
		pos = pos.add8m1(state.dir1);
		pos = pos.add8m1(state.dir2);
		pos = pos.add8m1(state.dir3);
		pos = pos.add8m1(state.dir4);
		pos = pos.add8m1(state.dir5);
		pos = pos.add8m1(state.dir6);
		pos = pos.add8m1(state.dir7);
		pos = pos.add8m1(state.dir8);
		return pos;
	}

	@Benchmark
	public Coord benchAdd8m2(BenchState8 state) {
		Coord pos = state.pos;
		pos = pos.add8m2(state.dir1);
		pos = pos.add8m2(state.dir2);
		pos = pos.add8m2(state.dir3);
		pos = pos.add8m2(state.dir4);
		pos = pos.add8m2(state.dir5);
		pos = pos.add8m2(state.dir6);
		pos = pos.add8m2(state.dir7);
		pos = pos.add8m2(state.dir8);
		return pos;
	}

	@Benchmark
	public Coord benchAdd8m3(BenchState8 state) {
		Coord pos = state.pos;
		pos = pos.add8m3(state.dir1);
		pos = pos.add8m3(state.dir2);
		pos = pos.add8m3(state.dir3);
		pos = pos.add8m3(state.dir4);
		pos = pos.add8m3(state.dir5);
		pos = pos.add8m3(state.dir6);
		pos = pos.add8m3(state.dir7);
		pos = pos.add8m3(state.dir8);
		return pos;
	}

	@Benchmark
	public CubeCoord benchCubeAdd6m1(BenchStateCube6 state) {
		CubeCoord pos = state.pos;
		pos = pos.add6m1(state.dir1);
		pos = pos.add6m1(state.dir2);
		pos = pos.add6m1(state.dir3);
		pos = pos.add6m1(state.dir4);
		pos = pos.add6m1(state.dir5);
		pos = pos.add6m1(state.dir6);
		return pos;
	}

	@Benchmark
	public CubeCoord benchCubeAdd6m3(BenchStateCube6 state) {
		CubeCoord pos = state.pos;
		pos = pos.add6m3(state.dir1);
		pos = pos.add6m3(state.dir2);
		pos = pos.add6m3(state.dir3);
		pos = pos.add6m3(state.dir4);
		pos = pos.add6m3(state.dir5);
		pos = pos.add6m3(state.dir6);
		return pos;
	}

}
