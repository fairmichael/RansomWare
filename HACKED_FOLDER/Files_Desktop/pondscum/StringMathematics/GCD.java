package StringMathematics;

/**
 * Created by Fair Nuri Aboshehwa on 2/11/18.
 * 
 * Class That Provides Functionality Greatest Common Denominator Of
 * A Given Fraction.
 */
public class GCD {

    /**
     * This is the function called by the user.
     * @param fraction String Representation of a Fraction
     * @return Greatest Common Denominator as Integer
     */
    public static long gcd(String fraction) {
        long x = Long.parseLong(Fraction.extractNumerator(fraction, fraction.indexOf('/')));
        long y = Long.parseLong(Fraction.extractDenominator(fraction, fraction.indexOf('/')));

        if(x < y) {
            long temp = x;
            x = y;
            y = temp;
        }
        return _gcd(x, y);
    }

    /**
     * Recursive Call To Solve GCD
     * @param x Numerator longeger
     * @param y Denominator longeger
     * @return Greatest Common Denominator as Integer
     */
    private static long _gcd(long x, long y) {
        if(y == 0)
            return x;
        return _gcd(y, x%y);
    }
}
/*
gcd(6, 9)

9 = 6*1 + 3
6 = 3*2 + 0
x = y*(x/y) + rem
*/