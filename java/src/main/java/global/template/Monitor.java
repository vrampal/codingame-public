package global.template;

import java.lang.management.*;

class Monitor {
	private static final MemoryMXBean MEMORY_MX_BEAN = ManagementFactory.getMemoryMXBean();

	private final boolean logEnabled;
	private long lastMark = System.nanoTime();

	int turn = 0;

	Monitor(boolean logEnabled) {
		this.logEnabled = logEnabled;
	}

	void start() {
		lastMark = System.nanoTime();
	}

	int elapsedMs() {
		long now = System.nanoTime();
		int elapsed = (int) ((now - lastMark) / 1000000L);
		return elapsed;
	}

	int memUsed() {
		MemoryUsage heapUsage = MEMORY_MX_BEAN.getHeapMemoryUsage();
		long totalMem = heapUsage.getMax();
		long usedMem = heapUsage.getUsed();
		int  usedPercent = (int) ((usedMem * 100L) / totalMem);
		return usedPercent;
	}

	void nextTurn() {
		long now = System.nanoTime();
		if (logEnabled) {
			int elapsed = (int) ((now - lastMark) / 1000000L);
			MemoryUsage heapUsage = MEMORY_MX_BEAN.getHeapMemoryUsage();
			long totalMem = heapUsage.getMax();
			long usedMem = heapUsage.getUsed();
			int  usedPercent = (int) ((usedMem * 100L) / totalMem);
			long usedMemKb = usedMem / 1024;
			System.err.println("Turn " + turn + " in " + elapsed + "ms, memory " + usedMemKb + "kB (" + usedPercent + "%)");
		}
		lastMark = now;
		turn++;
	}
}

