package global.remoteExec;

import java.io.*;
import java.lang.management.*;
import java.util.*;
import java.util.Map.*;

class Player {

	public static void main(String[] args) throws Exception {
		//cat("/proc/cpuinfo");
		//cat("/proc/meminfo");
		//cat("/proc/self/cmdline");
		//cat("/etc/passwd");
		//exec("id");
		//exec("pwd"); 
		//exec("ls -l /home");
		//exec("ls -l /bin/java");
		//exec("ls -l /opt/coderunner");
		//exec("uname -a");
		//exec("ifconfig /all");
		//exec("ping google.fr");
		//exec("traceroute google.fr");
		//osInfo();
		//properties();
		//env();
		//memoryRuntime();
		//memoryJMX();
	}

	static void cat(String fileName) throws IOException {
		print(new FileReader(fileName));
	}

	static void exec(String command) throws Exception {
		Runtime runtime = Runtime.getRuntime();
		Process p = runtime.exec(command);
		p.waitFor();
		print(new InputStreamReader(p.getInputStream()));
	}

	static void print(Reader reader) throws IOException {
		BufferedReader buff = new BufferedReader(reader);
		String line = buff.readLine();
		while (line != null) {
			System.err.println(line);
			line = buff.readLine();
		}
	}

	static void ls(String pathname) {
		File dir = new File(pathname);
		System.err.println(dir.getAbsolutePath());
		File[] subFiles = dir.listFiles();
		System.err.println(Integer.toString(subFiles.length) + " files");
		for (File f : subFiles) {
			System.err.println(f.getAbsolutePath());
		}
	}

	static void osInfo() {
		System.err.println("os.name: " + System.getProperty("os.name"));
		System.err.println("os.version: " + System.getProperty("os.version"));
		System.err.println("os.arch: " + System.getProperty("os.arch"));
	}

	static void properties() {
		Properties props = System.getProperties();
		Iterator<Entry<Object, Object>> iter = props.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<Object, Object> entry = iter.next();
			System.err.println(entry.getKey() + " -> " + entry.getValue());
		}
	}

	static void env() {
		Map<String, String> env = System.getenv();
		Iterator<Entry<String, String>> iter = env.entrySet().iterator();
		while (iter.hasNext()) {
			Entry<String, String> entry = iter.next();
			System.err.println(entry.getKey() + " -> " + entry.getValue());
		}
	}

	static void memoryRuntime() {
		Runtime runtime = Runtime.getRuntime();
		long totalMem = runtime.totalMemory();
		long freeMem = runtime.freeMemory();
		long usedMem = totalMem - freeMem;
		int usedPercent = (int) ((usedMem * 100) / totalMem);
		System.err.println("Memory usage: " + usedPercent + "% (" + (usedMem / 1024) + "kB / " + (totalMem / 1024) + "kB)");
	}

	static void memoryJMX() {
		MemoryMXBean memoryMxBean = ManagementFactory.getMemoryMXBean();
		MemoryUsage heapUsage = memoryMxBean.getHeapMemoryUsage();
		long totalMem = heapUsage.getMax();
		long usedMem = heapUsage.getUsed();
		int usedPercent = (int) ((usedMem * 100) / totalMem);
		System.err.println("Heap usage: " + usedPercent + "% (" + (usedMem / 1024) + "kB / " + (totalMem / 1024) + "kB)");
	}
}
