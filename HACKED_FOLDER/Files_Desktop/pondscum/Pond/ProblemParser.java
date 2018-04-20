package Pond;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Scanner;

/**
 * Created by Fair Nuri Aboshehwa on 2/12/18.
 */
public class ProblemParser {

    public static String[][] getProblem(String path) throws FileNotFoundException {
        Scanner file = new Scanner(new FileReader(path)).useDelimiter("[,\n]");
        String[][] problemSet = new String[getNumOfRows(path)][getNumOfColumns(path)];
        for(int row = 0; row < problemSet.length; row++) {
            for(int col = 0; col < problemSet[0].length; col++) {
                problemSet[row][col] = file.next();
            }
        }
        file.close();
        return problemSet;
    }

    public static int getNumOfRows(String path) throws FileNotFoundException {
        Scanner file = new Scanner(new FileReader(path));
        int counter = 0;
        while(file.hasNextLine()) {
            file.nextLine();
            counter++;
        }
        file.close();
        return counter;
    }

    public static int getNumOfColumns(String path) throws FileNotFoundException {
        Scanner file = new Scanner(new Scanner(new FileReader(path)).nextLine()).useDelimiter(",");
        int counter = 0;
        while(file.hasNext()) {
            file.next();
            counter++;
        }
        file.close();
        return counter;
    }

    public static HashMap<String, Integer> getProblemVariables(String[][] problem) {
        HashMap<String, Integer> variables = new HashMap<>();
        int index = 0;
        for(int row = 0; row < problem.length; row++) {
            for(int col = 0; col < problem[0].length; col++) {
                if(problem[row][col].charAt(0) == '!') {
                    variables.put(problem[row][col], index++);
                }
            }
        }
        return variables;
    }
}
/*
    e
  a b c
    d

    (b-e) + (b-c) = (d-b) + (a-b)
    4b -e -c -a -d

 */