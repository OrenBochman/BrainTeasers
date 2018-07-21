package BrainTeasers.DataStructres.Matrix;

public class AntiSyymetric {
	private int[][] matrix = null;
	
	final int rows;
	final int collumns; 

	void setMatrix(int[][] matrix) {
		this.matrix = matrix;
	}

	AntiSyymetric(int[][] matrix){
		this.matrix=matrix;
		this.rows = matrix[0].length;
		this.collumns = matrix.length ;
	}


		final static int[][] MATRIX1 = 
			{ 
				{ 1, 1, 1, 1, 2, 3 }, 
				{ 1, 1, 1, 1, 3, 3 }, 
				{ 1, 1, 1, 4, 4, 3 },
				{ 2, 1, 4, 4, 4, 3 }, 
				{ 2, 1, 1, 4, 3, 3 } 
			};

	public static void main(String[] args) {
		int[][] MATRIX = { 
				{  1,  6,  7,  8,  9 },
				{  6,  2, 10, 11, 12 },
				{  7, 10,  3, 13, 14 },
				{  8, 11, 13,  4, 15 },
				{  9, 12, 14, 15,  5 } };

		AntiSyymetric antiSymmetric = new AntiSyymetric(MATRIX);
		System.out.println("symmetric: " + antiSymmetric.check());
	}

	boolean check(){
		
		for(int row = 0 ; row < rows-1; row++){
			//skip the diagonal
			for(int col = row+1 ; col < collumns; col++){
				//check for symmetry
				if(matrix[row][col] != matrix[col][row])
						return false;
			}
		}
		return true;

	}

}
