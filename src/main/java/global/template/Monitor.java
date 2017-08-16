package global.template;

import java.lang.management.*;

class Monitor {
	private static final MemoryMXBean MEMORY_MX_BEAN = ManagementFactory.getMemoryMXBean();

	private final boolean debugEnable;
	private long timeMark = System.nanoTime();
	int round = 0;
	
	Monitor(boolean print) {
		this.debugEnable = print;
	}

	int elapsedMs() {
		long now = System.nanoTime();
		int elapsed = (int) ((now - timeMark) / 1000000L);
		return elapsed;
	}

	int memUsed() {
		MemoryUsage heapUsage = MEMORY_MX_BEAN.getHeapMemoryUsage();
		long totalMem = heapUsage.getMax();
		long usedMem = heapUsage.getUsed();
		int  usedPercent = (int) ((usedMem * 100) / totalMem);
		return usedPercent;
	}

	void nextRound() {
		long now = System.nanoTime();
		if (debugEnable) {
			int elapsed = (int) ((now - timeMark) / 1000000L);
			MemoryUsage heapUsage = MEMORY_MX_BEAN.getHeapMemoryUsage();
			long totalMem = heapUsage.getMax();
			long usedMem = heapUsage.getUsed();
			int  usedPercent = (int) ((usedMem * 100) / totalMem);
			long usedMemKb = usedMem / 1024;
			System.err.println("Round " + round + " in " + elapsed + "ms, memory " + usedMemKb + "kB (" + usedPercent + "%)");
		}
		timeMark = now;
		round++;
	}
}
