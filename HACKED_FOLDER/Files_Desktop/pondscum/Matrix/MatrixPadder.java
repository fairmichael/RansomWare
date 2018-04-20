package Matrix;

/**
 * Created by Fair Nuri Aboshehwa on 2/9/18.
 */
public class MatrixPadder {

    public static long[][] removeAllZeroRowLayer(long matrix[][]) {
        boolean matrixHasAnyZeroRow = false;
        boolean[] hasZeros = new boolean[matrix[0].length];
        int count = 0;
        for(int col = 0; col < matrix[0].length; col++) {
            if(hasZeroRowLayer(matrix, col)) {
                hasZeros[col] = true;
                count++;
                matrixHasAnyZeroRow = true;
            }
        }
        return matrixHasAnyZeroRow ? removeZeroRowLayer(matrix, hasZeros, count) : matrix;
    }

    public static long[][] removeAllZeroColLayer(long[][] matrix) {
        boolean matrixHasAnyZeroCol = false;
        boolean[] hasZeros = new boolean[matrix.length];
        int count = 0;
        for(int row = 0; row < matrix.length; row++) {
            if(hasZeroColLayer(matrix, row)) {
                hasZeros[row] = true;
                count++;
                matrixHasAnyZeroCol = true;
            }
        }
        return matrixHasAnyZeroCol ? removeZeroColLayer(matrix, hasZeros, count) : matrix;
    }

    public static long[][] removeZeroColLayer(long matrix[][], boolean[] hasZeros, int count) {
        long[][] removedLayer = new long[matrix.length-count][matrix[0].length];
        int r = 0, c = 0;
        for(int col = 0; col < matrix[0].length; col++) {
            for(int row = 0; row < matrix.length; row++) {
                if(!hasZeros[row]) {
                    removedLayer[r++][c] = matrix[row][col];
                    if(r == matrix.length-1) {
                        r = 0;
                        c++;
                    }
                }
            }
        }
        return removedLayer;
    }

    public static long[][] removeZeroRowLayer(long matrix[][], boolean[] hasZeros, int count) {
        long[][] removedLayer = new long[matrix.length][matrix[0].length-count];
        int r = 0, c = 0;
        for(int row = 0; row < matrix.length; row++) {
            for(int co = 0; co < matrix[0].length; co++) {
                if(!hasZeros[co]) {
                    removedLayer[r][c++] = matrix[row][co];
                    if(c == matrix[0].length-1) {
                        c = 0;
                        r++;
                    }
                }
            }
        }
        return removedLayer;
    }

    public static boolean hasZeroColLayer(long matrix[][], int row) {
        for(int col = 0; col < matrix[0].length; col++) {
            if(matrix[row][col] != 0)
                return false;
        }
        return true;
    }

    public static boolean hasZeroRowLayer(long matrix[][], int col) {
        for(int row = 0; row < matrix.length; row++) {
            if(matrix[row][col] != 0)
                return false;
        }
        return true;
    }

    public static long[][] addZerosLayer(long matrix[][]) {
        long zeroLayerArray[][] = new long[matrix.length + 1][matrix[0].length + 1];
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
                zeroLayerArray[row][col] = matrix[row][col];
            }
        }
        return zeroLayerArray;
    }

    public static long[][] addRightZerolayer(long matrix[][]) {
        long[][] zeroLayerArray = new long[matrix.length][matrix[0].length+1];
        for(int row = 0; row < zeroLayerArray.length; row++) {
            for(int col = 0; col < zeroLayerArray[0].length-1; col++) {
                zeroLayerArray[row][col] = matrix[row][col];
            }
        }
        return zeroLayerArray;
    }
    
    public static long[][] addBottomZeroLayer(long matrix[][]) {
        long[][] zeroLayerArray = new long[matrix.length + 1][matrix[0].length];
        for(int row = 0; row < matrix.length; row++) {
            for(int col = 0; col < matrix[0].length; col++) {
                zeroLayerArray[row][col] = matrix[row][col];
            }
        }
        return zeroLayerArray;
    }

    public static long[][] removeZeroLayer(long matrix[][]) {
//        if(!MatrixChecker.hasZeroLayer(matrix))
//            return matrix;
//        long[][] noZeroLayerArray = new long[matrix.length-1][matrix.length-1];
//        for(long row = 0; row < noZeroLayerArray.length; row++) {
//            for(long col = 0; col < noZeroLayerArray.length; col++) {
//                noZeroLayerArray[row][col] = matrix[row][col];
//            }
//        }
//        return noZeroLayerArray;
        matrix = removeAllBottomZeroLayers(matrix);
        matrix = removeAllRightZeroLayers(matrix);
        return matrix;
    }

    public static long[][] removeRightZeroLayer(long matrix[][]) {
        long[][] removedRightLayerArray = new long[matrix.length][matrix[0].length-1];
        for(int row = 0; row < removedRightLayerArray.length; row++) {
            for(int col = 0; col < removedRightLayerArray[0].length; col++) {
                removedRightLayerArray[row][col] = matrix[row][col];
            }
        }
        return removedRightLayerArray;
    }

    public static long[][] removeBottomZeroLayer(long matrix[][]) {
        long[][] removedBottomLayerArray = new long[matrix.length-1][matrix[0].length];
        for(int row = 0; row < removedBottomLayerArray.length; row++) {
            for(int col = 0; col < removedBottomLayerArray[0].length; col++) {
                removedBottomLayerArray[row][col] = matrix[row][col];
            }
        }
        return removedBottomLayerArray;
    }

    public static long[][] removeAllRightZeroLayers(long matrix[][]) {
        while(MatrixChecker.hasRightZeroLayer(matrix)) {
            matrix = removeRightZeroLayer(matrix);
        }
        return matrix;
    }

    public static long[][] removeAllBottomZeroLayers(long matrix[][]) {
        while(MatrixChecker.hasBottomZeroLayer(matrix)) {
            matrix = removeBottomZeroLayer(matrix);
        }
        return matrix;
    }
}