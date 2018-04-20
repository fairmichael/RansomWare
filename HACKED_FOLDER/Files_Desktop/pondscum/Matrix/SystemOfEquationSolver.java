package Matrix;

/**
 * Created by Fair Nuri Aboshehwa on 2/11/18.
 */
public class SystemOfEquationSolver {
    public static String[][] solveMatrices(long[][] left, long[][] right) throws Exception {
        long leftDeterminant = MatrixDeterminant.getDeterminant(left);
        
        long[][] rightTransposed = MatrixTransposer.transposeMatrix(right);
        
        long[][] leftCoFactor = MatrixTransposer.transposeMatrix(MatrixCoFactor.getCoFactor(left));
        
        return MatrixMultiplyer.contantxMatrix("1/" + String.valueOf(leftDeterminant), MatrixMultiplyer.multiplyMatrix(leftCoFactor, rightTransposed));
        
    }
}
