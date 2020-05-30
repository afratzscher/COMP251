package A4;

import java.util.Random;

public class KaratsubaTester {
	private static int randomInt(int size) {
        Random rand = new Random();
        int maxval = (1 << size) - 1;
        return maxval;  
    }
    public static void main(String[] args){
//    	try{
//            int maxIntBitSize = 10;
//            for (int size=9; size<=maxIntBitSize; size++) {
//                for (int x = 0; x < randomInt(size); x++) {
//                	for (int y = 0; y < randomInt(size); y++) {
//                		int[] resNaive = Multiply.naive(size,x,y);
//                      int[] resKaratsuba = Multiply.karatsuba(size,x,y);
//                		if (resNaive[0] != resKaratsuba[0]) {
//                          throw new Exception("Return values do not match! (x=" + x + "; y=" + y + "; Naive=" + resNaive[0] + "; Karatsuba=" + resKaratsuba[0] + ")");
//                      }
//                      
//                      if (resNaive[0] != (x*y)) {
//                          int myproduct = x*y;
//                          throw new Exception("Evaluation is wrong! (x=" + x + "; y=" + y + "; Your result=" + resNaive[0] + "; True value=" + myproduct + ")");
//                      }
//                	}
//                }
//            }
//            System.out.print("done");
//    	}
//        catch (Exception e){
//            System.out.println(e);
//    	}
            
    	int product1 = Multiply.karatsuba(3, 5, 7)[0];
        int cost2 = Multiply.karatsuba(3, 5, 7)[1];

        if (product1 == 35) {
            System.out.println("Product is a success!");
        } else {
            System.out.println("Product has error!");
            System.out.println(product1);
        }

        if (cost2 == 39) {
            System.out.println("Cost is a success!");
        } else {
            System.out.println("Cost has error!");
            System.out.println(cost2);
        }
       
        int product3 = Multiply.karatsuba(2, 5, 7)[0];
        if (product3 == 3) {
            System.out.println("Product is a success!");
        } else {
            System.out.println("Product has error!");
            System.out.println(product1);
        }
        
        int product4 = Multiply.karatsuba(1, 5, -7)[0];
        if (product4 == 1) {
            System.out.println("Product is a success!");
        } else {
            System.out.println("Product has error!");
            System.out.println(product1);
        }

        int product5 = Multiply.karatsuba(1, -5, 7)[0];
        if (product5 == -1) {
            System.out.println("Product is a success!");
        } else {
            System.out.println("Product has error!");
            System.out.println(product5);
        }
        
        int product = Multiply.karatsuba(1, 8, 7)[0];
        int cost = Multiply.karatsuba(1, 8, 7)[1];
        if (product == 0) {
            System.out.println("Product is a success!");
        } else {
            System.out.println("Product has error!");
            System.out.println(product);
        }
    }
}

