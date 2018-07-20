package BrainTeasers.Countries;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Optional;

/**
 * Pojo for points. Uses Contries.COLS and Countries.ROWS to
 * 
 * @author oren
 *
 */
class Point implements Comparator<Point>, Comparable<Point> {
	int row, col;

	Point(int x, int y) {
		this.row = x;
		this.col = y;
	}

	boolean checkBounds(HashMap<String, Integer> bounds) {
		return row >= 0 && col >= 0 && col < bounds.get("COLS") && row < bounds.get("ROWS");
	}


	public Optional<Point> getUp(HashMap<String, Integer> bounds) {
		Point candiate = new Point(row - 1, col);
		return (candiate.checkBounds(bounds)) ? Optional.of(candiate) : Optional.empty();
	}

	public Optional<Point> getRight(HashMap<String, Integer> bounds) {
		Point candiate = new Point(row, col + 1);		
		return (candiate.checkBounds(bounds)) ? Optional.of(candiate) : Optional.empty();

	}

	public Optional<Point> getDown(HashMap<String, Integer> bounds) {
		Point candiate = new Point(row+1, col );	
		return (candiate.checkBounds(bounds)) ? Optional.of(candiate) : Optional.empty();
	}

	public Optional<Point> getLeft(HashMap<String, Integer> bounds) {
		Point candiate = new Point(row, col - 1);	
		return (candiate.checkBounds(bounds)) ? Optional.of(candiate) : Optional.empty();
	}

	public Optional<Point> getUpperLeft(HashMap<String, Integer> bounds) {
		Point candiate = new Point(row - 1, col - 1);	
		return (candiate.checkBounds(bounds)) ? Optional.of(candiate) : Optional.empty();
	}

	Optional<Point> getUpperRight(HashMap<String, Integer> bounds) {
		Point candiate = new Point(row - 1, col + 1);	
		return (candiate.checkBounds(bounds)) ? Optional.of(candiate) : Optional.empty();
	}

	Optional<Point> getLowerLeft(HashMap<String, Integer> bounds) {
		Point candiate = new Point(row + 1, col - 1);	
		return (candiate.checkBounds(bounds)) ? Optional.of(candiate) : Optional.empty();
	}

	Optional<Point> getLowerRight(HashMap<String, Integer> bounds) {
		Point candiate = new Point(row + 1, col + 1);	
		return (candiate.checkBounds(bounds)) ? Optional.of(candiate) : Optional.empty();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + row;
		result = prime * result + col;
		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Point other = (Point) obj;
		if (row != other.row)
			return false;
		if (col != other.col)
			return false;
		return true;
	}

	public int compareRow(Point o) {
		return this.row - o.row;
	}

	public int compareCol(Point o) {
		return this.col - o.col;
	}

	@Override
	public int compare(Point o1, Point o2) {
		return o1.compareTo(o2);
	}

	@Override
	public int compareTo(Point o) {
		return this.compareRow(o) != 0 ? this.compareRow(o) : this.compareCol(o);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Point [x=" + row + ", y=" + col + "]";
	}
}