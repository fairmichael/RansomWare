package Matrix;

import StringMathematics.*;


/**
 * Created by Fair Nuri Aboshehwa on 2/9/18.
 */

public class MatrixMultiplyer {

    public static long[][] multiplyMatrix(long left[][], long right[][]) throws Exception {
        return MatrixPadder.removeAllZeroColLayer(MatrixPadder.removeAllZeroRowLayer(MatrixPadder.removeZeroLayer(_multiplyMatrix(left, right))));
    }

    public static String[][] contantxMatrix(String constant, long[][] matrix) {
        String strVersion[][] = new String[matrix.length][matrix[0].length];
        if(constant == "0") {
            return new String[1][1];
        } else {
            for(int row = 0; row < matrix.length; row++) {
                for(int col = 0; col < matrix[0].length; col++) {
                    strVersion[row][col] = StringMultiplication.multiply(constant, String.valueOf(matrix[row][col]));
                }
            }
        }
        return strVersion;
    }

    private static long[][] _multiplyMatrix(long left[][], long right[][]) throws Exception {
        if(MatrixChecker.matrixCannotBeMultiplied(left, right)) {
            throw new Exception("Matrices Cannot Be Multiplied Together\n");
        }
        
        left = CompatableMatrix.makeLeftSquareMatrix(left);
        right = CompatableMatrix.makeRightSquareMatrix(right);
        
        if(MatrixChecker.isMatrixOdd(left)) {
            left = MatrixPadder.addZerosLayer(left);
        }
        if(MatrixChecker.isMatrixOdd(right)) {
            right = MatrixPadder.addZerosLayer(right);
        }
        if(isBaseCase(left)) {
            return multiply2x2Matrix(left, right);
        }
        MatrixDividedBy4 leftMatrix = new MatrixDividedBy4(left);
        MatrixDividedBy4 rightMatrix = new MatrixDividedBy4(right);
        long resultUpperLeft[][]   = MatrixAdder.addMatrix(_multiplyMatrix(leftMatrix.getUpperLeft(), rightMatrix.getUpperLeft()), _multiplyMatrix(leftMatrix.getUpperRight(), rightMatrix.getLowerLeft()));
        long resultLowerLeft[][]   = MatrixAdder.addMatrix(_multiplyMatrix(leftMatrix.getLowerLeft(), rightMatrix.getUpperLeft()), _multiplyMatrix(leftMatrix.getLowerRight(), rightMatrix.getLowerLeft()));
        long resultUpperRight[][]  = MatrixAdder.addMatrix(_multiplyMatrix(leftMatrix.getUpperLeft(), rightMatrix.getUpperRight()), _multiplyMatrix(leftMatrix.getUpperRight(), rightMatrix.getLowerRight()));
        long resultLowerRight[][]  = MatrixAdder.addMatrix(_multiplyMatrix(leftMatrix.getLowerLeft(), rightMatrix.getUpperRight()), _multiplyMatrix(leftMatrix.getLowerRight(), rightMatrix.getLowerRight()));

        if(MatrixChecker.isCompatableWithNoPadding(resultUpperLeft, resultLowerLeft, resultUpperRight, resultLowerRight)) {
            return CombineMatrices.combineMatrices(MatrixPadder.removeZeroLayer(resultUpperLeft), MatrixPadder.removeZeroLayer(resultUpperRight), MatrixPadder.removeZeroLayer(resultLowerLeft), MatrixPadder.removeZeroLayer(resultLowerRight));
        }
        return CombineMatrices.combineMatrices(resultUpperLeft, resultUpperRight, resultLowerLeft, resultLowerRight);
    }

    private static boolean isBaseCase(long[][] left) {
        return left.length == 2;
    }

    private static long[][] multiply2x2Matrix(long[][] a, long[][] b) {
        long[][] result = new long[2][2];
        result[0][0] = a[0][0]*b[0][0] + a[0][1]*b[1][0];
        result[0][1] = a[0][0]*b[0][1] + a[0][1]*b[1][1];
        result[1][0] = a[1][0]*b[0][0] + a[1][1]*b[1][0];
        result[1][1] = a[1][0]*b[0][1] + a[1][1]*b[1][1];
        return result;
    }
}