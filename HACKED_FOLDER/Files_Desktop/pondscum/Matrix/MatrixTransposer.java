package Matrix;

/**
 * Created by Fair Nuri Aboshehwa on 2/11/18.
 */
public class MatrixTransposer {
    public static long[][] transposeMatrix(long[][] matrix) {
        long[][] transposed = new long[matrix[0].length][matrix.length];

        for(int matrixRow = 0; matrixRow < matrix.length; matrixRow++) {
            for (int matrixCol = 0; matrixCol < matrix[0].length; matrixCol++) {
                transposed[matrixCol][matrixRow] = matrix[matrixRow][matrixCol];
            }
        }
        return transposed;
    }

    public static String[][] transposeMatrix(String[][] matrix) {
        String[][] transposed = new String[matrix[0].length][matrix.length];

        for(int matrixRow = 0; matrixRow < matrix.length; matrixRow++) {
            for(int matrixCol = 0; matrixCol < matrix[0].length; matrixCol++) {
                transposed[matrixCol][matrixRow] = matrix[matrixRow][matrixCol];
            }
        }
        return transposed;
    }
}