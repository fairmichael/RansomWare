package Matrix;

/**
 * Created by Fair Nuri Aboshehwa on 2/9/18.
 * 
 * Class That Provides Adding Matrices Functionalities
 */
public class MatrixAdder {

    /**
     * Adds Two Given NxM Matrices
     * @param left Left Matrix
     * @param right Right Matrix
     * @return sum of left and right matrices
     * @throws Exception Checks If Matrices Can Be Added Together
     */
    public static long[][] addMatrix(long left[][], long right[][]) throws Exception {
        if(MatrixChecker.matrixCannotBeAdded(left, right)) {
            throw new Exception("Matrices Cannot Be Added Together\n");
        }
        long[][] result = new long[left.length][left[0].length];
        for(int row = 0; row < result.length; row++) {
            for(int col = 0; col < result[0].length; col++) {
                result[row][col] = left[row][col] + right[row][col];
            }
        }
        return result;
    }
}