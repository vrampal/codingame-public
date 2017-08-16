package training.hard.superComputer;

import java.util.*;

class Task {
	final int start;
	final int end;

	Task(Scanner in) {
		start = in.nextInt();
		int length = in.nextInt();
		//System.err.println(start + " " + length);
		end = start + length;
	}

	public String toString() {
		return start + " " + end;
	}
}

class FinishFirst implements Comparator<Task> {
	static final FinishFirst INSTANCE = new FinishFirst();

	public int compare(Task o1, Task o2) {
		return Integer.compare(o1.end, o2.end);
	}
}

class Solution {

	public static void main(String args[]) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	void run() {
		int taskCount = in.nextInt();
		List<Task> tasks = new ArrayList<>(taskCount);
		for (int taskIdx = 0; taskIdx < taskCount; taskIdx++) {
			tasks.add(new Task(in));
		}
		Collections.sort(tasks, FinishFirst.INSTANCE);
		int ending = 0;
		int result = 0;
		for (Task task : tasks) {
			//System.err.println(task);
			if (task.start >= ending) {
				ending = task.end;
				result++;
			}
		}
		System.out.println(result);
	}

}
