package Matrix;

/**
 * Created by Fair Nuri Aboshehwa on 2/10/18.
 */
public class MatrixDeterminant {

    public static long getDeterminant(long[][] matrix) throws Exception {
        if(!MatrixChecker.isSquareMatrix(matrix)) {
            throw new Exception("Non Square Matrix Has No Determinant\n");
        }
        return _getDeterminant(matrix);
    }

    private static long _getDeterminant(long[][] matrix) throws Exception {
        if(isBaseCase(matrix)) {
            return det2x2Matrix(matrix);
        }

        long det = 0;
        boolean isAdding = true;
        for(int col = 0; col < matrix[0].length; col++) {
            if(isAdding) {
                det += matrix[0][col] * getDeterminant(getRemainingMatrix(col, matrix));
            } else {
                det -= matrix[0][col] * getDeterminant(getRemainingMatrix(col, matrix));
            }
            isAdding = !isAdding;
        }
        return det;
    }

    private static long[][] getRemainingMatrix(int covered, long[][] matrix) {
        long result[][] = new long[matrix.length-1][matrix.length-1];
        for(int row = 1; row < matrix.length; row++) {
            int c = 0;
            for(int col = 0; col < matrix[0].length; col++) {
                if(col != covered) {
                    result[row-1][c] = matrix[row][col];
                    c++;
                }
            }
        }
        return result;
    }

    private static boolean isBaseCase(long[][] matrix) {
        if(matrix.length == 2) {
            return true;
        }
        return false;
    }

    private static long det2x2Matrix(long[][] matrix) {
        return (matrix[0][0]*matrix[1][1] - matrix[1][0]*matrix[0][1]);
    }
}