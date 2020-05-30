package A4;

import java.util.*;
import java.io.*;
// todo: naive(), karatsuba() -> need to make so if size = 16 and 16, add 1 to front
public class Multiply{

	private static int randomInt(int size) {
        Random rand = new Random();
        int maxval = (1 << size) - 1;
        return rand.nextInt(maxval + 1);
    }

    public static int[] naive(int size, int x, int y) {

        // YOUR CODE GOES HERE  (Note: Change return statement)
        int[] result = new int[] {0,0};  
        int xSign = 0;
    	int ySign = 0;
    	if (x < 0) {
    		xSign = -1; // set flag to negative
    		x = x * -1; // make x positive
    	}
    	if (y < 0) {
    		ySign = -1; // set flag to negative
    		y = y * -1; // make y positive
    	}
        
    	if (size == 1) // base case cost = 1
    	{
    		result[0] = (x&1) * (y&1);
    		result[1] = 1;
    		if (xSign == -1|| ySign == -1) {
           		result[0] = result[0] * -1; // make negative
           	}
    		if (xSign == -1 && ySign == -1) {
           		result[0] = result[0] * -1; // make positive again (since made negative earlier)
           	}
    		return result;
    	}
        
    	int m = (int) Math.ceil(size/2.0);
    	int a = (int)(x >> m);
    	int b = x % (1 << m);
    	int c = (int)(y >> m);
    	int d = y % (1 << m);
        
        int[] e = naive(m, a, c);
        int[] f = naive(m, b, d);
        int[] g = naive(m, b, c);
        int[] h = naive(m, a, d);
        
        //product
        result[0] = (int)(e[0] << (2 * m)) + ((g[0]+h[0]) << m) + f[0];
    	if (xSign < 0 || ySign < 0) {
       		result[0] = result[0] * -1; // make negative
       	}
       	if (xSign < 0 && ySign < 0) {
       		result[0] = result[0] * -1; // make positive again if BOTH signs negative
       	}
        //cost
        result[1] = (e[1] + f[1] + g[1] + h[1]) + 3*m;
        return result;  
    }

    public static int[] karatsuba(int size, int x, int y) {
        
        // YOUR CODE GOES HERE  (Note: Change return statement)
    	int[] result = new int[] {0,0};
    	int[] e = new int[] {0,0};
    	int[] f = new int[] {0,0};
    	int[] g = new int[] {0,0};
    	int xSign = 0;
    	int ySign = 0;
    	if (x < 0) {
    		xSign = -1; // set flag to negative
    		x = x * -1; // make x positive
    	}
    	if (y < 0) {
    		ySign = -1; // set flag to negative
    		y = y * -1; // make y positive
    	}
        
    	if (size == 1) // base case cost = 1
    	{
    		result[0] = (x&1) * (y&1);
    		result[1] = 1;
    		if (xSign == -1|| ySign == -1) {
           		result[0] = result[0] * -1; // make negative
           	}
    		if (xSign == -1 && ySign == -1) {
           		result[0] = result[0] * -1; // make positive again (since made negative earlier)
           	}
    		return result;
    	}
    	
    	int m = (int) Math.ceil(size/2.0);
    	int a = (int)(x >> m);
    	int b = x % (1 << m);
    	int c = (int)(y >> m);
    	int d = y % (1 << m);
        
        e = karatsuba(m, a, c);
        f = karatsuba(m, b, d);
        g = karatsuba(m, a-b, c-d);   
        
        //product
       	result[0] = (e[0] << (2 * m)) + ((e[0] + f[0] - g[0]) << m) + f[0];
       	if (xSign < 0 || ySign < 0) {
       		result[0] = result[0] * -1; // make negative
       	}
       	if (xSign < 0 && ySign < 0) {
       		result[0] = result[0] * -1; // make positive again if BOTH signs negative
       	}
       	
        //cost
        result[1] = (e[1]+f[1]+g[1])+6*m;
        return result;       
    }
    
    public static void main(String[] args){

        try{
            int maxRound = 20;
            int maxIntBitSize = 16;
            for (int size=1; size<=maxIntBitSize; size++) {
                int sumOpNaive = 0;
                int sumOpKaratsuba = 0;
                for (int round=0; round<maxRound; round++) {
                    int x = randomInt(size);
                    int y = randomInt(size);
                    int[] resNaive = naive(size,x,y);
                    int[] resKaratsuba = karatsuba(size,x,y);
            
                    if (resNaive[0] != resKaratsuba[0]) {
                        throw new Exception("Return values do not match! (x=" + x + "; y=" + y + "; Naive=" + resNaive[0] + "; Karatsuba=" + resKaratsuba[0] + ")");
                    }
                    
                    if (resNaive[0] != (x*y)) {
                        int myproduct = x*y;
                        throw new Exception("Evaluation is wrong! (x=" + x + "; y=" + y + "; Your result=" + resNaive[0] + "; True value=" + myproduct + ")");
                    }
                    
                    sumOpNaive += resNaive[1];
                    sumOpKaratsuba += resKaratsuba[1];
                }
                int avgOpNaive = sumOpNaive / maxRound;
                int avgOpKaratsuba = sumOpKaratsuba / maxRound;
                System.out.println(size + "," + avgOpNaive + "," + avgOpKaratsuba);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

   } 
}
