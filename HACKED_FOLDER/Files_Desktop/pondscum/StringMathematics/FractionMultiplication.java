package StringMathematics;

/**
 * Created by Fair Nuri Aboshehwa on 2/11/18.
 * 
 * This Class Provides Fraction Multiplication
 * Functionality.
 */

public class FractionMultiplication {

    /**
     * Given Two Non Decimal Numbers. Multiply them together
     * @param x First String number argument
     * @param y Second String number argument
     * @return  Returns a non-simplified fraction format
     */
    public static String multiplyFraction(String x, String y) {
        String xNum, yNum, xDen, yDen;

        long fNum = 0, fDen = 0;

        if(MathStringChecker.hasDivisionOperator(x)) {
            xNum = Fraction.extractNumerator(x, x.indexOf('/'));
            xDen = Fraction.extractDenominator(x, x.indexOf('/'));
        } else {
            xNum = x;
            xDen = "1";
        }
        if(MathStringChecker.hasDivisionOperator(y)) {
            yNum = Fraction.extractNumerator(y, y.indexOf('/'));
            yDen = Fraction.extractDenominator(y, y.indexOf('/'));
        } else {
            yNum = y;
            yDen = "1";
        }
        fNum = Long.parseLong(xNum) * Long.parseLong(yNum);
        fDen = Long.parseLong(xDen) * Long.parseLong(yDen);

        return fDen == 1 ? String.valueOf(fNum) : String.valueOf(fNum) + "/" + String.valueOf(fDen);
    }
}
