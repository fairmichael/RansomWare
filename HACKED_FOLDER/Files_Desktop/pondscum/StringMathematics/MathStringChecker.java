package StringMathematics;

/**
 * Created by Fair Nuri Aboshehwa on 2/11/18.
 * 
 * Class That Provides Functionality With Extraction
 * Mathematical Operators From String Formatted Numbers.
 */

public class MathStringChecker {
    /**
     * Checks If A String Number Has A Division Operator
     * @param num String Number
     * @return True or False
     */
    public static boolean hasDivisionOperator(String num) {
        return num.indexOf('/') != -1;
    }
    
    public static boolean hasMinusOperator(String num) {
        return num.indexOf('-') != -1;
    }
}