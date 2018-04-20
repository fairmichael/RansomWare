package Matrix;

/**
 * Created by Fair Nuri Aboshehwa on 2/11/18.
 * 
 * Class That Provides Inverting Matrix Functionalities.
 */
public class MatrixInverter {
    
    /**
     * Inverts A Given Integer Array Matrix
     * @param matrix Integer Array Matrix
     * @return Inverted String Representation Of Integer Array Matrix
     * @throws Exception Checks If Matrix Has Determinant
     */
    public static String[][] invert(long[][] matrix) throws Exception {
        long determinant = MatrixDeterminant.getDeterminant(matrix);
        String constant = "1/" + String.valueOf(determinant);
        return MatrixMultiplyer.contantxMatrix(constant, MatrixCoFactor.getCoFactor(matrix));
    }
}