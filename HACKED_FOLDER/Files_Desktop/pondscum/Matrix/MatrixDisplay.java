package Matrix;

/**
 * Created by Fair Nuri Aboshehwa on 2/10/18.
 */
public class MatrixDisplay {
    public static void printMatrix(long[][] list) {
        for(int i = 0; i < list.length; i++) {
            for(int j = 0; j < list[0].length; j++) {
                char end = (j == list[0].length-1 ? '\n' : ',');
                System.out.print(String.valueOf(list[i][j]) + end);
            }
        }
    }

    public static void printMatrix(boolean[][] list) {
        for(int i = 0; i < list.length; i++) {
            for(int j = 0; j < list[0].length; j++) {
                char end = (j == list[0].length-1 ? '\n' : ',');
                System.out.print(String.valueOf(list[i][j]) + end);
            }
        }
    }

    public static void printMatrix(String[][] list) {
        for(int i = 0; i < list.length; i++) {
            for(int j = 0; j < list[0].length; j++) {
                char end = (j == list[0].length-1 ? '\n' : ',');
                System.out.print(String.valueOf(list[i][j]) + end);
            }
        }
    }
}