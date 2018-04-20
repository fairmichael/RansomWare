package Matrix;
import Checkboard.BooleanCheckBoard;

/**
 * Created by Fair Nuri Aboshehwa on 2/11/18.
 */
public class MatrixCoFactor {

    public static long[][] getCoFactor(long[][] matrix) throws Exception {
        if(isBaseCase(matrix))
            return coFactor2x2Matrix(matrix);
        long minorMatrix[][] =  new long[matrix.length][matrix[0].length];
        boolean checkBoard[][] = BooleanCheckBoard.createCheckeredBoolValues(minorMatrix.length, minorMatrix[0].length);
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
                minorMatrix[row][col] = checkBoard[row][col] ? MatrixDeterminant.getDeterminant(getRemainingMatrix(row, col, matrix))
                        : -1*MatrixDeterminant.getDeterminant(getRemainingMatrix(row, col, matrix));
            }
        }
        return minorMatrix;
    }

    private static long[][] coFactor2x2Matrix(long[][] matrix) {
        long[][] coFactor = new long[2][2];
        coFactor[0][0] = matrix[1][1];
        coFactor[1][1] = matrix[0][0];
        coFactor[1][0] = matrix[0][1] * -1;
        coFactor[0][1] = matrix[1][0] * -1;

        return coFactor;
    }

    private static long[][] getRemainingMatrix(long coveredRow, long coveredCol, long[][] matrix) {
        long result[][] = new long[matrix.length-1][matrix.length-1];
        int c = 0, r = 0;
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
                if(col != coveredCol && row != coveredRow) {
                    if(c == result[0].length) {
                        c = 0;
                        r++;
                    }
                    result[r][c] = matrix[row][col];
                    c++;
                }
            }
        }
        return result;
    }

    private static boolean isBaseCase(long[][] matrix) {
        if(matrix.length == 2)
            return true;
        return false;
    }
}