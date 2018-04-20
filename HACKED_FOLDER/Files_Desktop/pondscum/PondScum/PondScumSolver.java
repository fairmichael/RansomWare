package PondScum;

import Matrix.MatrixDisplay;
import Matrix.SystemOfEquationSolver;
import Pond.EquationParser;
import Pond.ProblemParser;
import java.io.FileNotFoundException;

/**
 * Created by Fair Nuri Aboshehwa on 2/12/18.
 */

public class PondScumSolver {

    private static final String problemPath = "/Users/Pey/Desktop/problem.txt";

    public static void main(String args[]) throws FileNotFoundException, Exception {

        String[][] unconfiguredPond = ProblemParser.getProblem(problemPath);

        long[][] leftMatrix = EquationParser.getLeftMatrix(unconfiguredPond);

        long[][] rightMatrix = EquationParser.getRightMatrix(unconfiguredPond);

        String[][] result = SystemOfEquationSolver.solveMatrices(leftMatrix, rightMatrix);


        String[][] configuredPond = new String[unconfiguredPond.length][unconfiguredPond[0].length];
        int resultIndex = 0;
        for(int row = 0; row < unconfiguredPond.length; row++) {
            for(int col = 0; col < unconfiguredPond[0].length; col++) {
                if(unconfiguredPond[row][col].startsWith("!")) {
                    configuredPond[row][col] = result[resultIndex++][0];
                } else {
                    configuredPond[row][col] = unconfiguredPond[row][col];
                }
            }
        }

        MatrixDisplay.printMatrix(configuredPond);
    }
}