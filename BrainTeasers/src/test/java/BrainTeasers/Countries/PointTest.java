package BrainTeasers.Countries;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class PointTest {

	class Fixture {
		int[][] matrix;
		String name;
		int solution;
		int altSolution;

		Fixture(int[][] matrix, int solution, String name, int altSolution) {
			this.name = name;
			this.solution = solution;
			this.matrix = matrix;
			this.altSolution = altSolution;
		}
	}

	// All the matracies
	int[][] diag = { { 1, 0, 0, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 }, { 1, 0, 0, 0, 1 } };

	int[][] mBig9 = { { 1, 1, 2, 1, 2, 3 }, { 1, 2, 2, 1, 3, 3 }, { 3, 3, 2, 2, 4, 3 }, { 2, 1, 4, 4, 4, 3 },
			{ 2, 1, 4, 3, 3, 3 } };
	int[][] mBig5 = { { 1, 1, 1, 1, 2, 3 }, { 1, 1, 1, 1, 3, 3 }, { 1, 1, 1, 4, 4, 3 }, { 2, 1, 4, 4, 4, 3 },
			{ 2, 1, 1, 4, 3, 3 } };
	int[][] mForks = { { 1, 1, 1, 1, 1, 1 }, { 1, 2, 2, 2, 2, 2 }, { 1, 1, 1, 1, 1, 2 }, { 1, 2, 2, 2, 2, 2 },
			{ 1, 1, 1, 1, 1, 1 } };

	int[][] sOne4by4 = { { 1, 1 }, { 1, 1 } };
	int[][] sFourDiagonals = { { 0, 1 }, { 1, 0 } };

	int[][] sFourDiagonalsTranspose = { { 1, 0 }, { 0, 1 } };
	int[][] sFour4by4 = { { 1, 2 }, { 3, 4 } };

	int[][] sTwo4by4 = { { 1, 1 }, { 2, 2 } };

	int[][] sTwo4by4Transpose = { { 1, 2 }, { 1, 2 } };
	int[][] sing = { { 1 } };

	// for end to end
	ArrayList<Fixture> fixtures = new ArrayList<>();

	// for getDirXX tests
	Fixture fixtureForks, fixtureBigMap9, fixtureSing;
	Countries countries;
	// start
	Point start;
	// Destintion
	Point upperLeft;
	Point lowerLeft;
	Point up;
	Point down;
	Point upperRight;
	Point right;
	Point lowerRight;
	Point left;

	@BeforeEach
	public void setUp() throws Exception {
		fixtures.add(new Fixture(mBig9, 9, "mBig9", 9));
		fixtures.add(new Fixture(mBig5, 5, "mBig5", 5));
		fixtures.add(new Fixture(mForks, 2, "mForks", 2));
		fixtures.add(new Fixture(sFour4by4, 4, "sFour4by4", 4));
		fixtures.add(new Fixture(sFourDiagonals, 4, "sFourDiagonals", 2));
		fixtures.add(new Fixture(sFourDiagonalsTranspose, 4, "sFourDiagonalsTranspose", 2));
		fixtures.add(new Fixture(sOne4by4, 1, "sOne4by4", 1));
		fixtures.add(new Fixture(sTwo4by4, 2, "sTwo4by4", 2));
		fixtures.add(new Fixture(sTwo4by4Transpose, 2, "sTwo4by4Transpose", 2));
		fixtures.add(new Fixture(diag, 13, "diag", 2));

		fixtureSing = new Fixture(sing, 1, "sing", 1);
		fixtures.add(fixtureSing);

		fixtureForks = new Fixture(mForks, 2, "mForks", 2);
		countries = new Countries(fixtureForks.matrix);
		fixtureBigMap9 = new Fixture(mBig9, 9, "mBig9", 9);

		// 00 01 02
		// 10 11 12
		// 20 21 22
		upperLeft = new Point(0, 0);
		up = new Point(0, 1);
		upperRight = new Point(0, 2);

		left = new Point(1, 0);
		start = new Point(1, 1);
		right = new Point(1, 2);

		lowerLeft = new Point(2, 0);
		down = new Point(2, 1);
		lowerRight = new Point(2, 2);
	}

	@AfterEach
	public void tearDown() throws Exception {
	}

	/////// test getting points when they are avaiavle

	@Test
	public void testGetLeft() {
		assertEquals(left, start.getLeft(countries.getBounds()).get(), "Left");
	}

	@Test
	public void testGetRight() {
		assertEquals(right, start.getRight(countries.getBounds()).get(), "Right");
	}

	@Test
	public void testGetUp() {
		assertEquals(up, start.getUp(countries.getBounds()).get(), "Up");
	}

	@Test
	public void testGetDown() {
		assertEquals(down, start.getDown(countries.getBounds()).get(), "Down");
	}

	@Test
	public void testGetUpperLeft() {
		assertEquals(upperLeft, start.getUpperLeft(countries.getBounds()).get(), "Upper Left");
	}

	@Test
	public void testGetLowerLeft() {
		assertEquals(lowerLeft, start.getLowerLeft(countries.getBounds()).get(), "Lower Left");
	}

	@Test
	public void testGetLowerRight() {
		assertEquals(lowerRight, start.getLowerRight(countries.getBounds()).get(), "Lower Right");
	}

	@Test
	public void testGetUpperRight() {
		assertEquals(upperRight, start.getUpperRight(countries.getBounds()).get(), "Upper Right");
	}

	////// test getting points when they are unavailable
	Point start00 = new Point(0, 0);
	Countries neverLand = new Countries(sing);

	@Test
	public void testGetLeftOutOfBounds() {
		assertFalse(start00.getLeft(neverLand.getBounds()).isPresent(), "Left");
	}

	@Test
	public void testGetRightOutOfBounds() {
		assertFalse(start00.getRight(neverLand.getBounds()).isPresent(), "Right");
	}

	@Test
	public void testGetUpOutOfBounds() {
		assertFalse(start00.getUp(neverLand.getBounds()).isPresent(), "Up");
	}

	@Test
	public void testGetDownOutOfBounds() {
		assertFalse(start00.getDown(neverLand.getBounds()).isPresent(), "Down");
	}

	@Test
	public void testGetUpperLeftOutOfBounds() {
		assertFalse(start00.getUpperLeft(neverLand.getBounds()).isPresent(), "Upper Left");
	}

	@Test
	public void testGetLowerLeftOutOfBounds() {
		assertFalse(start00.getLowerLeft(neverLand.getBounds()).isPresent(), "Lower Left");
	}

	@Test
	public void testGetLowerRightOutOfBounds() {
		assertFalse(start00.getLowerRight(neverLand.getBounds()).isPresent(), "Lower Right");
	}

	@Test
	public void testGetUpperRightOutOfBounds() {
		assertFalse(start00.getUpperRight(neverLand.getBounds()).isPresent(), "Upper Right");
	}

	// looking at the lexicographic ordering
	@Test
	public void testOrderLeft() {
		assertEquals(1, start.compareTo(left), "Start bigger than left ");
		assertEquals(1, start.compare(start, left), "Start bigger than left ");
		// start.compareTo(left).thenComparing(left);
	}

	@Test
	public void testOrderRight() {
		assertEquals(-1, start.compareTo(right), "Start smaller then right");
		assertEquals(-1, start.compare(start, right), "Start smaller then right");
	}

	@Test
	public void testOrderUp() {
		assertEquals(1, start.compareTo(up), "Start bigger than up");
		assertEquals(1, start.compare(start, up), "Start bigger than up");
	}

	@Test
	public void testOrderDown() {
		assertEquals(-1, start.compareTo(down), "Start smaller than down");
		assertEquals(-1, start.compare(start, down), "Start smaller than down");
	}

	@Test
	public void testOrderUpperLeft() {
		assertEquals(1, start.compareTo(upperLeft), "Start smaller than down");
		assertEquals(-1, start.compare(start, down), "Start smaller than down");
	}

	@Test
	public void testOrderUpperRight() {
		assertEquals(1, start.compareTo(upperRight), "Start bigger than upper right");
		assertEquals(1, start.compare(start, upperRight), "Start bigger than upper right");
	}

	@Test
	public void testOrderLowerLeft() {
		assertEquals(-1, start.compareTo(lowerLeft), "Start bigger than lower left");
		assertEquals(-1, start.compare(start, lowerLeft), "Start bigger than lower left");
	}

	@Test
	public void testOrderLowerRight() {
		assertEquals(-1, start.compareTo(lowerRight), "Start smaller than lower right");
		assertEquals(-1, start.compare(start, lowerRight), "Start smaller than lower right");
	}

	@Test
	public void testEquals() {
		assertEquals(right, start.getRight(countries.getBounds()).get(), "self");
		assertNotEquals(start, start.getUp(countries.getBounds()).get(), "other");
	}

	@Test
	public void testHash() {
		assertEquals(right.hashCode(), start.getRight(countries.getBounds()).get().hashCode(), "self");
		assertNotEquals(start.hashCode(), start.getUp(countries.getBounds()).get().hashCode(), "other");
	}

	Comparator<Point> byX = (s1, s2) -> s2.row - s1.row;
	Comparator<Point> byY = (s1, s2) -> s2.col - s1.col;

	// if our comparator is not so great the set will reject the second point
	Comparator<Point> byXPlusByY = (s1, s2) -> s1.compareRow(s2) + s1.compareCol(s2);

	@Test
	public void TreeSetWithBadCompartor() {
		SortedSet<Point> badTreeSet = new TreeSet<Point>(byXPlusByY);

		assertTrue(badTreeSet.add(start.getLeft(countries.getBounds()).get()), "first ok");
		assertEquals(1, byXPlusByY.compare(start, start.getLeft(countries.getBounds()).get()));
		// check that two differenr point are equal ...
		assertEquals(0, byXPlusByY.compare(start.getLeft(countries.getBounds()).get(),
				start.getUp(countries.getBounds()).get()));
		assertFalse(badTreeSet.add(start.getLeft(countries.getBounds()).get()), "same fails");
		assertFalse(badTreeSet.add(start.getUp(countries.getBounds()).get()), "second fails");
	}

	// Later corrected the comparator to use lexicographic ordering (first check by
	// x then check by y)
	Comparator<Point> composedXY = byX.thenComparing(byY);

	@Test
	public void TreeSetWithGoodCompartor() {
		SortedSet<Point> goodTreeSet = new TreeSet<Point>(composedXY);

		assertEquals(1, composedXY.compare(start.getRight(countries.getBounds()).get(),
				start.getDown(countries.getBounds()).get()));
		assertTrue(goodTreeSet.add(start.getUp(countries.getBounds()).get()), "ok");
		assertTrue(goodTreeSet.add(start.getLeft(countries.getBounds()).get()), "ok");
	}

	@Test
	public void TreeSetOrder() {
		SortedSet<Point> goodTreeSet = new TreeSet<Point>(composedXY);
		SortedSet<Point> goodTreeSet2 = new TreeSet<Point>(composedXY);

		// add to first treeset using one order
		goodTreeSet.add(start.getLeft(countries.getBounds()).get());
		goodTreeSet.add(start.getRight(countries.getBounds()).get());
		goodTreeSet.add(start.getUp(countries.getBounds()).get());
		goodTreeSet.add(start.getDown(countries.getBounds()).get());
		// add to second treeset using another order
		goodTreeSet2.add(start.getDown(countries.getBounds()).get());
		goodTreeSet2.add(start.getRight(countries.getBounds()).get());
		goodTreeSet2.add(start.getUp(countries.getBounds()).get());
		goodTreeSet2.add(start.getLeft(countries.getBounds()).get());
		// both start and end the same...
		assertEquals(goodTreeSet.first(), goodTreeSet2.first(), "matching first");
		assertEquals(goodTreeSet.last(), goodTreeSet2.last(), "matching last");
	}

	@Test
	public void testGen4AdjacentPoints() {
		Countries country = new Countries(fixtureBigMap9.matrix);
		assertEquals(1, country.strategy.genAdjacents(start).size(), "test=point");
	}

	@Test
	public void testGen8AdjacentPoints() {
		int[][] diag = { { 1, 0, 0, 0, 1 }, { 0, 1, 0, 1, 0 }, { 0, 0, 1, 0, 0 }, { 0, 1, 0, 1, 0 },
				{ 1, 0, 0, 0, 1 } };

		Countries country1 = new Countries(diag);
		assertEquals(0, country1.strategy.genAdjacents(start).size(), "test=point");
		Countries country2 = new Countries(diag, "");
		assertEquals(2, country2.strategy.genAdjacents(start).size(), "test=point");
		assertEquals(4, country2.strategy.genAdjacents(new Point(2, 2)).size(), "test=point");

	}
	// 0,1,2,5,9,3,
	// 1,2,2,5,3,3,
	// 6,6,2,2,4,3,
	// 7,8,4,4,4,3,
	// 7,8,4,3,3,3,

	class ComponnentFixture {
		Point start;
		int size;

		ComponnentFixture(Point start, int size) {
			this.start = start;
			this.size = size;
		}
	}

	@Test
	public void testGetNeighbours() {
		int[][] simple9 = { { 1, 1, 2, 5, 9, 3 }, { 1, 2, 2, 5, 3, 3 }, { 6, 6, 2, 2, 4, 3 }, { 7, 8, 4, 4, 4, 3 },
				{ 7, 8, 4, 3, 3, 3 } };
		Countries country = new Countries(simple9);
		ArrayList<ComponnentFixture> ComponnentFixtureList = new ArrayList<>();
		ComponnentFixtureList.add(new ComponnentFixture(new Point(0, 0), 3));
		ComponnentFixtureList.add(new ComponnentFixture(new Point(0, 2), 5));
		ComponnentFixtureList.add(new ComponnentFixture(new Point(0, 5), 8));
		ComponnentFixtureList.add(new ComponnentFixture(new Point(2, 4), 5));
		ComponnentFixtureList.add(new ComponnentFixture(new Point(0, 3), 2));
		ComponnentFixtureList.add(new ComponnentFixture(new Point(2, 0), 2));
		ComponnentFixtureList.add(new ComponnentFixture(new Point(1, 3), 2));
		ComponnentFixtureList.add(new ComponnentFixture(new Point(0, 5), 8));
		ComponnentFixtureList.add(new ComponnentFixture(new Point(0, 4), 1));

		for (ComponnentFixture cf : ComponnentFixtureList) {
			assertEquals(cf.size, country.getNeighbours(cf.start).size(), "testing component");
		}
	}

	@Test
	public void testAllMaps() {
		for (Fixture fixture : fixtures) {
			Countries countries = new Countries(fixture.matrix);
			System.out.println(fixture.name);
			System.out.println(countries.printMatrix());
			assertEquals(fixture.solution, countries.countCountries(), fixture.name);
		}
	}

	@Test // @Ignore
	public void testAllMaps8() {
		for (Fixture fixture : fixtures) {
			Countries countries8 = new Countries(fixture.matrix, "");
			System.out.println(fixture.name);
			System.out.println(countries8.printMatrix());
			assertEquals(fixture.altSolution, countries8.countCountries(), fixture.name);
		}
	}
}