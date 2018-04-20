package Matrix;

/**
 * Created by Fair Nuri Aboshehwa on 2/10/18.
 * 
 * Class That Provides Functionalities To Combine Matrices
 * That Has Been Divided Into 4 parts. 
 */
public class CombineMatrices {

    /**
     * @param upperLeft Upper Left Matrix
     * @param upperRight Upper Right Matrix
     * @param lowerLeft Lower Left Matrix
     * @param lowerRight Lower Right Matrix
     * @return All Of Them Combined Into A Single Matrix
     */
    public static long[][] combineMatrices(long[][] upperLeft, long[][] upperRight, long[][] lowerLeft, long[][] lowerRight) {
        long[][] result = new long[upperLeft.length*2][upperLeft.length*2];
        long dim = result.length;
        for(int row = 0; row < result.length; row++) {
            for(int col = 0; col < result[0].length; col++) {
                if(isUpperLeftDomain(row, col, dim)) {
                    result[row][col] = upperLeft[row][col];
                } else if(isUpperRightDomain(row, col, dim)) {
                    result[row][col] = upperRight[row][col-upperRight[0].length];
                } else if(isLowerLeftDomain(row, col, dim)) {
                    result[row][col] = lowerLeft[row-lowerLeft.length][col];
                } else {
                    result[row][col] = lowerRight[row-lowerRight.length][col-lowerRight[0].length];
                }
            }
        }
        return result;
    }

    /**
     * Helper Method
     * @param row
     * @param col
     * @param dim
     * @return 
     */
    private static boolean isLowerLeftDomain(long row, long col, long dim) {
        return (row >= dim/2 && col < dim/2);
    }

    /**
     * Helper Method
     * @param row
     * @param col
     * @param dim
     * @return 
     */
    private static boolean isUpperRightDomain(long row, long col, long dim) {
        return (row < dim/2 && col >= dim/2);
    }

    /**
     * Helper Method
     * @param row
     * @param col
     * @param dim
     * @return 
     */
    private static boolean isUpperLeftDomain(long row, long col, long dim) {
        return (row < dim/2 && col < dim/2);
    }
}