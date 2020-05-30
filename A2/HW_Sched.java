package A2;
import java.util.*;

class Assignment implements Comparator<Assignment>{
	int number;
	int weight;
	int deadline;


	protected Assignment() {
	}

	protected Assignment(int number, int weight, int deadline) {
		this.number = number;
		this.weight = weight;
		this.deadline = deadline;
	}



	/**
	 * This method is used to sort to compare assignment objects for sorting. 
	 * Return -1 if a1 > a2
	 * Return 1 if a1 < a2
	 * Return 0 if a1 = a2 
	 */
	@Override
	public int compare(Assignment a1, Assignment a2) {
		// TODO Implement this
		// NOTE: weight is considered first, THEN, if equal weight, consider deadline
		int weight1 = a1.weight;
		int weight2 = a2.weight;
		int deadline1 = a1.deadline;
		int deadline2 = a2.deadline;

		if (weight1 > weight2)
		{
			return -1;
		}
		else if (weight1 < weight2)
		{
			return 1;
		}
		else // means equal weights
		{
			if (deadline1 > deadline2)
			{
				return 1;
			}
			else if (deadline1 < deadline2)
			{
				return -1;
			}
			else {
				return 0;
			}
		}
	}
}

public class HW_Sched {
	ArrayList<Assignment> Assignments = new ArrayList<Assignment>();
	int m;
	int lastDeadline = 0;

	protected HW_Sched(int[] weights, int[] deadlines, int size) {
		for (int i=0; i<size; i++) {
			Assignment homework = new Assignment(i, weights[i], deadlines[i]);
			this.Assignments.add(homework);
			if (homework.deadline > lastDeadline) {
				lastDeadline = homework.deadline;
			}
		}
		m =size;
	}


	/**
	 * 
	 * @return Array where output[i] corresponds to the assignment 
	 * that will be done at time i.
	 */
	public int[] SelectAssignments() {
		//TODO Implement this

		//Sort assignments
		//Order will depend on how compare function is implemented
		Collections.sort(Assignments, new Assignment());

		// If schedule[i] has a value -1, it indicates that the 
		// i'th timeslot in the schedule is empty
		int[] homeworkPlan = new int[lastDeadline];
		for (int i=0; i < homeworkPlan.length; ++i) {
			homeworkPlan[i] = -1;
		}

		// CODE STARTS HERE

		int slotsUsed = 0;;

		for (int i=0; i<Assignments.size(); i++) {
			int slot = Assignments.get(i).deadline-1; // last slot to do assingment aka slot before due

			while (homeworkPlan[slot] == -1 && slot>slotsUsed) { // find earliest slot that is -1
				slot--;
			}
			if (homeworkPlan[slot] == -1) {
				homeworkPlan[slot] = Assignments.get(i).number;
				slotsUsed++;
			}
			if (slotsUsed == lastDeadline) {
				break;
			}
		}

		return homeworkPlan;
	}
}



