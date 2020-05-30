package A2;
import java.lang.reflect.Array;
import java.util.Arrays;

public class GreedyTester {
	public static void main(String[] args) {
		
		//This is the typical kind of input you will be tested with. The format will always be the same
		//Each index represents a single homework. For example, homework zero has weight 23 and deadline t=3.
//		int[] weights = new int[] {23, 60, 14, 25, 7}; 
//		int[] deadlines = new int[] {3, 1, 2, 1, 3};
//		int m = weights.length;
//		
//		
		 // same deadline
		int[] weights = new int[] {25, 60, 14, 25, 7}; 
		int[] deadlines = new int[] {5, 5, 5, 5, 5};
		int m = weights.length;
		HW_Sched schedule =  new HW_Sched(weights, deadlines, m);
		int[] res = schedule.SelectAssignments();
		System.out.println("Should be: \n[1, 0, 3, 2, 4]");
		System.out.println("Actually: \n" + Arrays.toString(res));
		System.out.println();
		
		// same weight
		int[] weights2 = new int[] {25, 25, 25, 25, 25}; 
		int[] deadlines2 = new int[] {5, 2, 3, 1, 4};
		int m2 = weights2.length;
		HW_Sched schedule2 =  new HW_Sched(weights2, deadlines2, m2);
		int[] res2 = schedule2.SelectAssignments();
		System.out.println("Should be: \n[3, 1, 2, 4, 0]");
		System.out.println("Actually: \n" + Arrays.toString(res2));
		System.out.println();
		
		// can do all in order
		int[] weights3 = new int[] {23, 60, 14, 25, 7}; 
		int[] deadlines3 = new int[] {3, 1, 4, 2, 5};
		int m3 = weights3.length;
		HW_Sched schedule3 =  new HW_Sched(weights3, deadlines3, m3);
		int[] res3 = schedule3.SelectAssignments();
		System.out.println("Should be: \n[1, 3, 0, 2, 4]");
		System.out.println("Actually: \n" + Arrays.toString(res3));	
		System.out.println();
		
		//can do all test random
		int[] weights4 = new int[] {23, 60, 14, 25, 7}; 
		int[] deadlines4 = new int[] {1, 2, 3, 4, 5};
		int m4 = weights4.length;
		HW_Sched schedule4 =  new HW_Sched(weights4, deadlines4, m4);
		int[] res4 = schedule4.SelectAssignments();
		System.out.println("Should be: \n[1, 3, 2, 4, -1]");
		System.out.println("Actually: \n" + Arrays.toString(res4));
		System.out.println();
		
		//can do all test reverse
		int[] weights5 = new int[] {23, 60, 14, 25, 7}; 
		int[] deadlines5 = new int[] {3, 5, 2, 4, 1};
		int m5 = weights5.length;
		HW_Sched schedule5 =  new HW_Sched(weights5, deadlines5, m5);
		int[] res5 = schedule5.SelectAssignments();
		System.out.println("Should be: \n[1, 3, 0, -1, -1]");
		System.out.println("Actually: \n" + Arrays.toString(res5));
		System.out.println();
		
		//can do all test 2
		int[] weights6 = new int[] {23, 5, 14, 25, 7, 10, 40, 55, 12}; 
		int[] deadlines6 = new int[] {3, 1, 2,  1, 3,  4,  5,  4,  6};
		int m6 = weights6.length;
		HW_Sched schedule6 =  new HW_Sched(weights6, deadlines6, m6);
		int[] res6 = schedule6.SelectAssignments();
		System.out.println("Should be: \n[7, 6, 0, 8, -1, -1]");
		System.out.println("Actually: \n" + Arrays.toString(res6));
		System.out.println();
		
		//can do all test 3
		int[] weights7 = new int[] {23, 5, 14, 25, 7, 10, 40, 55, 12, 32, 78}; 
		int[] deadlines7 = new int[] {3, 1, 2,  5, 3,  6, 4,  1,  3,  2,  1};
		int m7 = weights7.length;
		HW_Sched schedule7 =  new HW_Sched(weights7, deadlines7, m7);
		int[] res7 = schedule7.SelectAssignments();
		System.out.println("Should be: \n[10, 6, 3, 5, -1, -1]");
		System.out.println("Actually: \n" + Arrays.toString(res7));
		System.out.println();
		

		//can do all test 4
		int[] weights8 = new int[] {5, 10, 14, 80, 75, 10, 40, 55, 10, 5, 5}; 
		int[] deadlines8 = new int[] {4, 1, 2,  3, 3,   6,  4,  5,  3, 2, 1};
		int m8 = weights8.length;
		HW_Sched schedule8 =  new HW_Sched(weights8, deadlines8, m8);
		int[] res8 = schedule8.SelectAssignments();
		System.out.println("Should be: \n[3, 4, 7, 6, 5, -1]");
		System.out.println("Actually: \n" + Arrays.toString(res8));
		System.out.println();

	}
}
