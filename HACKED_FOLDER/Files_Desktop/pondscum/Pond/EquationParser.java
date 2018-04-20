package Pond;

import java.util.HashMap;

/**
 * Created by Fair Nuri Aboshehwa on 2/12/18.
 */
public class EquationParser {

    public static long[][] getLeftMatrix(String[][] problem) {
        HashMap<String, Integer> varIndexes = ProblemParser.getProblemVariables(problem);

        long[][] leftMatrix = new long[varIndexes.size()][varIndexes.size()];
        int matRow = 0;
        boolean atLeastOneVar = false;

        for(int row = 1; row < problem.length-1; row++) {
            for(int col = 1; col < problem[0].length-1; col++) {
                if(!problem[row][col].startsWith("!"))
                    continue;
                leftMatrix[matRow][varIndexes.get(problem[row][col])] = 4;

                if(problem[row][col-1].startsWith("!")) {
                    leftMatrix[matRow][varIndexes.get(problem[row][col - 1])] = -1;
                    atLeastOneVar = true;
                }
                if(problem[row][col+1].startsWith("!")) {
                    leftMatrix[matRow][varIndexes.get(problem[row][col + 1])] = -1;
                    atLeastOneVar = true;
                }
                if(problem[row-1][col].startsWith("!")) {
                    leftMatrix[matRow][varIndexes.get(problem[row - 1][col])] = -1;
                    atLeastOneVar = true;
                }
                if(problem[row+1][col].startsWith("!")) {
                    leftMatrix[matRow][varIndexes.get(problem[row + 1][col])] = -1;
                    atLeastOneVar = true;
                }
                matRow = atLeastOneVar ? ++matRow : matRow;
            }
        }
        return leftMatrix;
    }

    public static long[][] getRightMatrix(String[][] problem) {

        long[][] rightMatrix = new long[1][ProblemParser.getProblemVariables(problem).size()];

        int matCol = 0;
        for(int row = 0; row < problem.length; row++) {
            for(int col = 0; col < problem[0].length; col++) {
                if(!problem[row][col].startsWith("!"))
                    continue;
                long temp = 0;
                if(!problem[row][col-1].startsWith("!")) {
                    temp += Long.parseLong(problem[row][col-1]);
                }
                if(!problem[row][col+1].startsWith("!")) {
                    temp += Long.parseLong(problem[row][col+1]);
                }
                if(!problem[row-1][col].startsWith("!")) {
                    temp += Long.parseLong(problem[row-1][col]);
                }
                if(!problem[row+1][col].startsWith("!")) {
                    temp += Long.parseLong(problem[row+1][col]);
                }
                rightMatrix[0][matCol++] = temp;
            }
        }

        return rightMatrix;
    }
}
