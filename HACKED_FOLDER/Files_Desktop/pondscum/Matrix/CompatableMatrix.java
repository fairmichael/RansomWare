/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Matrix;

/**
 *
 * @author peynu
 */
public class CompatableMatrix {
    
    public static long[][] makeLeftSquareMatrix(long[][] orig) {
        while(orig.length != orig[0].length) {
            orig = MatrixPadder.addBottomZeroLayer(orig);
        }
        return orig;
    }
    
    public static long[][] makeRightSquareMatrix(long[][] orig) {
        while(orig[0].length != orig.length) {
            orig = MatrixPadder.addRightZerolayer(orig);
        }
        return orig;
    }
}
