package StringMathematics;

/**
 * Created by Fair Nuri Aboshehwa on 2/11/18.
 * 
 * This Class Allows For Working With Fractions.
 * Anything From Extracting Numerator / Denominator
 * To Simplifying Fractions.
 */
public class Fraction {

    /**
     * Fraction simplifier
     * @param fraction Fraction or non decimal number as a string
     * @return Simplified fraction as a string
     */
    public static String simplify(String fraction) {
        if(fraction.indexOf('/') == -1)
            return fraction;
        long gcd = GCD.gcd(fraction);

        String num = String.valueOf(Long.parseLong(Fraction.extractNumerator(fraction, fraction.indexOf('/'))) / gcd);
        String den = String.valueOf(Long.parseLong(Fraction.extractDenominator(fraction, fraction.indexOf('/'))) / gcd);
        
        if(den.equals("1"))
            return String.valueOf(num);
        
        boolean isNumNeg = MathStringChecker.hasMinusOperator(num);
        boolean isDenNeg = MathStringChecker.hasMinusOperator(den);
        if(isNumNeg) {
            if(isDenNeg) {
                return num.substring(1,num.length()) + "/" + den.substring(1,den.length());
            }
            return num + "/" + den;
        } else if(isDenNeg) {
            return "-" + num + "/" + den.substring(1,den.length());
        }
        return num + "/" + den;
    }

    /**
     * Extracts the denominator of a fraction
     * @param fraction Fraction represented as a string
     * @param index Character index of division sign
     * @return Extracted Numerator as a string
     */
    protected static String extractDenominator(String fraction, int index) {
        return fraction.substring(index+1, fraction.length());
    }

    /**
     * Extracts the numerator of a fraction
     * @param fraction Fraction represented as a string
     * @param index Character index of division sign
     * @return Extracted Denominator as a string
     */
    protected static String extractNumerator(String fraction, int index) {
        return fraction.substring(0, index);
    }
}