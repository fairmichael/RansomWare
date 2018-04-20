package Matrix;

/**
 * Created by Fair Nuri Aboshehwa on 2/9/18.
 */
public class MatrixDividedBy4 {

    private long matrix[][];
    private long upperLeft[][];
    private long lowerLeft[][];
    private long upperRight[][];
    private long lowerRight[][];

    public MatrixDividedBy4(long[][] matrix) {
        this.matrix = matrix;
        extractUpperLeft();
        extractLowerLeft();
        extractUpperRight();
        extractLowerRight();
    }

    private void extractLowerRight() {
        lowerRight = new long[matrix.length/2][matrix.length/2];
        for(int row = matrix.length/2; row < matrix.length; row++) {
            for(int col = matrix[0].length/2; col < matrix[0].length; col++) {
                lowerRight[row-matrix.length/2][col-matrix[0].length/2] = matrix[row][col];
            }
        }
    }

    private void extractUpperRight() {
        upperRight = new long[matrix.length/2][matrix.length/2];
        for(int row = 0; row < matrix.length/2; row++) {
            for(int col = matrix[0].length/2; col < matrix[0].length; col++) {
                upperRight[row][col-matrix[0].length/2] = matrix[row][col];
            }
        }
    }

    private void extractLowerLeft() {
        lowerLeft = new long[matrix.length/2][matrix.length/2];
        for(int row = matrix.length/2; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length/2; col++) {
                lowerLeft[row-matrix.length/2][col] = matrix[row][col];
            }
        }
    }

    private void extractUpperLeft() {
        upperLeft = new long[matrix.length/2][matrix.length/2];
        for(int row = 0; row < matrix[0].length/2; row++) {
            for(int col = 0; col < matrix[0].length/2; col++) {
                upperLeft[row][col] = matrix[row][col];
            }
        }
    }

    public long[][] getUpperLeft() {
        return upperLeft;
    }
    public long[][] getMatrix() {
        return matrix;
    }

    public long[][] getLowerLeft() {
        return lowerLeft;
    }

    public long[][] getUpperRight() {
        return upperRight;
    }

    public long[][] getLowerRight() {
        return lowerRight;
    }
}