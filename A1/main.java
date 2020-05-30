package A1;
import java.io.*;
import java.util.*;
import A1.Chaining.*;
import A1.Open_Addressing.*;


public class main {     

	public static void main(String[] args) {

		/*
		 * Exercise 2: Inserting Keys
		 */

		int[] keysToInsertOne = {86, 85, 6, 97, 19, 66, 26, 14,
				15, 49, 75, 64, 35, 54, 31, 9, 82, 29, 81, 13};
		int[] keysToInsertTwo = {52, 71, 34, 95, 68, 25, 44, 38, 
				47, 77, 92, 84, 94, 12, 61, 9, 89, 56, 28, 75};
		int oneA = 554;
		int twoA = 1018;

		/*------Exercise 2.1: Collisions for A = 554 vs. A = 1018-------*/
		int chainSumOne = 0;
		Chaining chainTableOne = new Chaining(10, 0, oneA);
		// Check collisions for A = 554 using chaining
		for (int i = 0; i < keysToInsertOne.length; i++) {
			chainSumOne += chainTableOne.insertKey(keysToInsertOne[i]);
		}
		System.out.println(chainSumOne + " collisions for A = 554 (list X) using chaining"); // 3 collisions

		int chainSumTwo = 0;
		Chaining chainTableTwo = new Chaining(10, 0, twoA);
		// Check collisions for A = 1018 using chaining 
		for (int i = 0; i < keysToInsertTwo.length; i++) {
			chainSumTwo += chainTableTwo.insertKey(keysToInsertOne[i]);
		}
		System.out.println(chainSumTwo + " collisions for A = 1018 (list Y) using chaining"); // 6 collisions

		/*------Exercise 2.2: Collisions using Chaining vs Open Addressing-------*/
		int openSumOne = 0;
		Open_Addressing openTableOne = new Open_Addressing(10,0,oneA);
		// Check collisions for A = 554 using open addressing
		for (int i = 0; i < keysToInsertOne.length; i++) {
			openSumOne += openTableOne.insertKey(keysToInsertOne[i]);
		}
		System.out.println(openSumOne + " collisions for A = 554 (list X) using open addressing"); // 3 collisions

		int openSumTwo = 0;
		Open_Addressing openTableTwo = new Open_Addressing(10,0,twoA);
		// Check collisions for A = 1018 using addressing 
		for (int i = 0; i < keysToInsertTwo.length; i++) {
			openSumTwo += openTableTwo.insertKey(keysToInsertOne[i]);
		}
		System.out.println(openSumTwo + " collisions for A = 1018 (list Y) using open addressing"); // 25 collisions	 
	}    
}