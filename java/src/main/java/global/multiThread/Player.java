package global.multiThread;

import java.util.*;

class SegmentedProcessing implements Runnable {
	final int[][] data;
	final int begin;
	final int end;

	SegmentedProcessing(int[][] data, int begin, int end) {
		this.data = data;
		this.begin = begin;
		this.end = end;
	}

	public void run() {
		for (int i = begin; i < end; i++) {
			int[] line = data[i];
			Arrays.sort(line);
		}
	}
}

class Player {

	static final int SIZE = 1000;

	public static void main(String[] args) throws Exception {
		new Player().run();
	}

	void run() throws Exception {
		//directSort();
		//multiSort(1);
		//multiSort(2);
		//multiSort(4);
		//multiSort(8);
		//multiSort(16);
	}

	int[][] generateData() {
		Random rand = new Random(0);
		int[][] data = new int[SIZE][];
		for (int i = 0; i < SIZE; i++) {
			int[] line = new int[SIZE];
			data[i] = line;
			for (int j = 0; j < SIZE; j++) {
				line[j] = rand.nextInt();
			}
		}
		return data;
	}

	void directSort() {
		int[][] data = generateData();
		long tStart = System.nanoTime();

		Runnable processing = new SegmentedProcessing(data, 0, SIZE);
		processing.run();

		long tEnd = System.nanoTime();
		System.err.println("Direct elapsed: " + (tEnd - tStart) + "ns");
	}

	void multiSort(int threadCount) throws Exception {
		int[][] data = generateData();
		long tStart = System.nanoTime();

		List<Runnable> tasks = new ArrayList<>(threadCount);
		List<Thread> threads = new ArrayList<>(threadCount);
		int begin = 0;
		for (int i = 0; i < threadCount; i++) {
			int end = (SIZE * (i + 1)) / threadCount;
			Runnable task = new SegmentedProcessing(data, begin, end);
			tasks.add(task);
			Thread thread = new Thread(task);
			threads.add(thread);
			thread.start();
			begin = end;
		}
		for (Thread t : threads) {
			t.join();
		}

		long tEnd = System.nanoTime();
		System.err.println("Multi elapsed: " + (tEnd - tStart) + "ns");
	}

}
