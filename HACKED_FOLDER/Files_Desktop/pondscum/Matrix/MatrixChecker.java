package Matrix;

/**
 * Created by Fair Nuri Aboshehwa on 2/9/18.
 */

public class MatrixChecker {

    public static boolean matrixCannotBeAdded(long[][] left, long[][] right) {
        return left.length != right.length || left[0].length != right[0].length;
    }

    public static boolean matrixCannotBeMultiplied(long[][] left, long[][] right) {
        return left[0].length != right.length;
    }

    public static boolean isMatrixOdd(long[][] matrix) {
        return matrix.length % 2 != 0;
    }

    public static boolean hasZeroLayer(long[][] matrix) {
        if(matrix.length == 0)
            return false;
        for(int row = 0; row < matrix.length; row++) {
            if(matrix[row][matrix[0].length-1] != 0)
                return false;
        }
        return true;
    }

    public static boolean hasRightZeroLayer(long[][] matrix) {
        if(matrix.length == 0)
            return false;
        for(int row = 0; row < matrix.length; row++) {
            if(matrix[row][matrix[0].length-1] != 0)
                return false;
        }
        return true;
    }

    public static boolean hasBottomZeroLayer(long[][] matrix) {
        if(matrix.length == 0)
            return false;
        for(int col = 0; col < matrix[0].length; col++) {
            if(matrix[matrix.length-1][col] != 0)
                return false;
        }
        return true;
    }

    public static boolean isSquareMatrix(long[][] matrix) {
        return matrix.length == matrix[0].length;
    }

    public static boolean isCompatableWithNoPadding(long[][] resultUpperLeft, long[][] resultLowerLeft, long[][] resultUpperRight, long[][] resultLowerRight) {
        long[][] noZeroUL = MatrixPadder.removeZeroLayer(resultUpperLeft);
        long[][] noZeroUR = MatrixPadder.removeZeroLayer(resultUpperRight);
        long[][] noZeroLL = MatrixPadder.removeZeroLayer(resultLowerLeft);
        long[][] noZeroLR = MatrixPadder.removeZeroLayer(resultLowerRight);

        return ((noZeroUL.length >= 2 && (noZeroUL.length == noZeroUR.length && noZeroUL.length == noZeroLL.length && noZeroUL.length == noZeroLR.length) && (noZeroUL[0].length == noZeroUR[0].length && noZeroUL[0].length == noZeroLL[0].length && noZeroUL[0].length == noZeroLR[0].length)
                ? true : false));
    }
}