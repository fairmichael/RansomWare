package StringMathematics;

/**
 * Created by Fair Nuri Aboshehwa on 2/11/18.
 * 
 * Deals With Multiplication of String Formatted Numbers.
 */
public class StringMultiplication {

    /**
     * Multiplies Two String Numbers
     * @param a Operand one
     * @param b Operand two
     * @return Simplified Product of The Result
     */
    public static String multiply(String a, String b) {
        return Fraction.simplify(FractionMultiplication.multiplyFraction(a, b));
    }
}
