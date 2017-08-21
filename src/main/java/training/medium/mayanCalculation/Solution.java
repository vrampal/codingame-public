package training.medium.mayanCalculation;

import java.util.*;

class Solution {

	static final int BASE = 20;

	public static void main(String[] args) {
		new Solution().run();
	}

	Scanner in = new Scanner(System.in);

	private int width;
	private int height;
	private final List<List<String>> patternsByDigit = new ArrayList<>(BASE);
	private final Map<List<String>, Integer> digitsByPattern = new HashMap<>(BASE * 2);
	
	void run() {
		width = in.nextInt();
		height = in.nextInt();

		List<String> alphabetRaw = new ArrayList<>(height);
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			String alphabetLine = in.next();
			alphabetRaw.add(alphabetLine);
		}

		for (int digit = 0; digit < BASE; digit++) {
			List<String> pattern = extractPattern(alphabetRaw, digit);
			patternsByDigit.add(pattern);
			digitsByPattern.put(pattern, digit);
		}

		long n1 = readNumber();
		System.err.println("---");
		long n2 = readNumber();
		String operation = in.next();
		long result = 0;

		switch (operation.charAt(0)) {
		case '*': result = n1 * n2; break;
		case '/': result = n1 / n2; break;
		case '+': result = n1 + n2; break;
		case '-': result = n1 - n2; break;
		}
		
		System.err.println(n1 + " " + operation + " " + n2 + " = " + result);
		
		Deque<Integer> decomp = new LinkedList<>();
		if (result == 0) {
			decomp.addFirst(0);
		} else {
			while (result > 0) {
				int mod = (int) (result % BASE);
				decomp.addFirst(mod);
				result /= BASE;
			}
		}
		
		for (int digit : decomp) {
			System.err.println("Out " + digit);
			List<String> pattern = patternsByDigit.get(digit);
			for (int rowIdx = 0; rowIdx < height; rowIdx++) {
				System.out.println(pattern.get(rowIdx));
			}
		}
	}

	List<String> extractPattern(List<String> from, int index) {
		List<String> pattern = new ArrayList<>(height);
		for (int rowIdx = 0; rowIdx < height; rowIdx++) {
			int begin = index * width;
			int end = begin + width;
			String elem = from.get(rowIdx).substring(begin, end);
			pattern.add(elem);
		}
		return pattern;
	}

	long readNumber() {
		int s1 = in.nextInt();
		long number = 0;
		List<String> numRaw = new ArrayList<>(height);
		for (int rowIdx = 0; rowIdx < s1;) {
			String numLine = in.next();
			numRaw.add(numLine);
			rowIdx++;
			if ((rowIdx % height) == 0) {
				List<String> pattern = extractPattern(numRaw, 0);
				int digit = digitsByPattern.get(pattern);
				System.err.println("In " + digit);
				number = (number * BASE) + digit;
				numRaw.clear();
			}
		}
		return number;
	}

}
