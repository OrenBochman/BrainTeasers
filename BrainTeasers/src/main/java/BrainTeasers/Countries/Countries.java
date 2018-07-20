package BrainTeasers.Countries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public class Countries {
	//switch debug messages off
	private final boolean DEBUG = false ;

	private int[][] matrix;
	public int COLS, ROWS;

	GenNeighbourStrategy strategy;

	//nice printout
	String printMatrix() {
		StringBuilder sb= new StringBuilder(); 
		sb.append("rows:").append(ROWS).append("\n");
		sb.append("cols:").append(COLS).append("\n");

		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				// System.out.print("P[" + row + "][" + col + "]:" + matrix[row][col] + ", ");
				sb.append(matrix[row][col] ).append(",");
			}
			sb.append("\n");
		}
		sb.append("==============");
		return sb.toString();
	}

	public int[][] getMatrix() {
		return this.matrix;
	}

	public void setMatrix(int[][] matrix) {
		this.matrix = matrix;
		this.COLS = matrix[0].length;
		this.ROWS = matrix.length;
		getBounds().put("COLS", this.COLS);
		getBounds().put("ROWS", this.ROWS);
	}

	private HashMap<String,Integer> bounds= new HashMap<String,Integer>();

	Countries(int[][] matrix) {
		this.matrix = matrix;
		this.COLS = matrix[0].length;
		this.ROWS = matrix.length;
		getBounds().put("COLS", this.COLS);
		getBounds().put("ROWS", this.ROWS);
		this.strategy= new FourNeighboutStrategy();
	}

	Countries(int[][] matrix,String alt) {
		this(matrix);
		this.strategy= new EightNeighboutStrategy();
	}

	public static void main(String[] args) {

		ArrayList<int[][]> testMatrecies = new ArrayList<int[][]>();
		int[][] m31 = { { 1, 1, 2, 1, 2, 3 }, { 1, 2, 2, 1, 3, 3 }, { 3, 3, 2, 2, 4, 3 }, { 2, 1, 4, 4, 4, 3 }, { 2, 1, 4, 3, 3, 3 } };
		testMatrecies.add(m31);

		for (int[][] matrix : testMatrecies) {
			Countries countries = new Countries(matrix);
			System.out.println(countries.printMatrix());
			System.out.println(countries.countCountries());
		}
	}

	SortedSet<Point> universe, component;

	public int countCountries() {
		universe = new TreeSet<Point>();
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLS; j++) {
				universe.add(new Point(i, j));
			}
		}

		int counter = 0;
		while (universe.size() > 0) {
			Point point = universe.first();
			// component.add(point);
			universe.removeAll(getNeighbours(point));
			counter++;
		}
		return counter;
	}


	interface GenNeighbourStrategy {
		public Set<Point> genAdjacents(Point start);
	}

	class FourNeighboutStrategy implements GenNeighbourStrategy{
		@Override
		public Set<Point> genAdjacents(Point start) {
			Set<Point> adjecants = new TreeSet<Point>();
			int color = matrix[start.row][start.col];

			start.getDown(getBounds()).ifPresent((p) -> {
				if (goodPoint(p, color)) {
					adjecants.add(p);
				}
			});

			start.getLeft(getBounds()).ifPresent(p -> {
				if (goodPoint(p, color)) {
					adjecants.add(p);
				}
			});
			start.getRight(getBounds()).ifPresent(p -> {
				if (goodPoint(p, color)) {
					adjecants.add(p);
				}
			});

			start.getUp(getBounds()).ifPresent(p -> {
				if (goodPoint(p, color)) {
					adjecants.add(p);
				}
			});
			return adjecants;
		}
	}

	class EightNeighboutStrategy extends FourNeighboutStrategy {

		@Override
		public Set<Point> genAdjacents(Point start) {
			Set<Point> adjecants = super.genAdjacents(start);
			int color = matrix[start.row][start.col];

			start.getUpperLeft(getBounds()).ifPresent(p -> {
				if (goodPoint(p, color)) adjecants.add(p);
			});

			start
			.getUpperRight(getBounds())
			.ifPresent(p -> {
				if (goodPoint(p, color)) 
					adjecants.add(p);
			});

			start.getLowerLeft(getBounds()).ifPresent(p -> {
				if (goodPoint(p, color)) adjecants.add(p);
			});

			start.getLowerRight(getBounds()).ifPresent(p -> {
				if (goodPoint(p, color)) adjecants.add(p);
			});
			return adjecants;
		}
	}

	boolean goodPoint(Point p, int color) {
		return  !consumer.contains(p) && matrix[p.row][p.col] == color;
	}

	SortedSet<Point> producer=new TreeSet<>();
	SortedSet<Point> consumer=new TreeSet<>();

	Set<Point> getNeighbours(Point start) {
		producer = new TreeSet<>();
		consumer = new TreeSet<>();
		//int color = matrix[start.x][start.y];
		// gen adjecent point
		producer.addAll(strategy.genAdjacents(start));
		consumer.add(start);
		while (producer.size() > 0) {
			Point active = producer.first();
			producer.addAll(strategy.genAdjacents(active));
			consumer.add(active);
			producer.remove(active);
			if (DEBUG)
				System.err.println("found " + consumer + " so far " + consumer.size());
			if (DEBUG)
				System.err.println("found " + producer + " still " + producer.size());
		}
		return consumer;
	}

	public HashMap<String,Integer> getBounds() {
		return bounds;
	}
}