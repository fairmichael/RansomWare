package Checkboard;

/**
 * Created by Fair Nuri Aboshehwa on 2/11/18.
 * 
 * Boolean Colored Checker Board
 */
public class BooleanCheckBoard {

    /**
     * Creates Boolean Checkered Values
     * @param row row size
     * @param col col size
     * @return 2-d Array of Booleans
     * @throws Exception None Squared Matrix
     */
    public static boolean[][] createCheckeredBoolValues(int row, int col) throws Exception {
        if(row != col) {
            throw new Exception("Cannot Create Checkered Boolean Values For Non Square Matrix");
        }
        return (row % 2 == 0) ? createEvenValues(row, col) : createOddValues(row, col);
    }

    /**
     * Creates Boolean Checkered Values For Odd Matrix
     * @param row row size
     * @param col col size
     * @return 2-d Array of Booleans
=    */
    private static boolean[][] createOddValues(int row, int col) {
        boolean[][] result = new boolean[row][col];
        boolean isPositive = true;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                result[i][j] = isPositive;
                isPositive = !isPositive;
            }
        }
        return result;
    }

    /**
     * Creates Boolean Checkered Values For Even Matrix
     * @param row row size
     * @param col col size
     * @return 2-d Array of Booleans
=    */
    private static boolean[][] createEvenValues(int row, int col) {
        boolean[][] result = new boolean[row][col];
        boolean isPositive = true;
        for(int i = 0; i < row; i++) {
            for(int j = 0; j < col; j++) {
                result[i][j] = isPositive;
                isPositive = !isPositive;
            }
            isPositive = result[i][result[0].length-1];
        }
        return result;
    }
}