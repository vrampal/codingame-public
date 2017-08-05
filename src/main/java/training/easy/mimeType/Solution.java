package training.easy.mimeType;

import java.util.*;

class Solution {

	static final String UNKNOWN_MINE = "UNKNOWN";

	public static void main(String args[]) {
		new Solution().run();
	}
	
	Scanner in = new Scanner(System.in);
	
	void run() {
		int typeCount = in.nextInt(); // Number of elements which make up the association table.
		int fileCount = in.nextInt(); // Number Q of file names to be analyzed.

		Map<String, String> mimeTypeByExt = new HashMap<>(typeCount * 2);
		for (int typeIdx = 0; typeIdx < typeCount; typeIdx++) {
			String ext = in.next(); // file extension.
			String mimeType = in.next(); // MIME type.
			System.err.println(ext + " -> " + mimeType);
			mimeTypeByExt.put(ext.toLowerCase(), mimeType);
		}
		in.nextLine();

		for (int fileIdx = 0; fileIdx < fileCount; fileIdx++) {
			String fileName = in.nextLine(); // One file name per line.

			String ext = "";
			int lastDot = fileName.lastIndexOf('.');
			if (lastDot != -1) {
				ext = fileName.substring(lastDot + 1, fileName.length());
			}

			String mimeType = mimeTypeByExt.get(ext.toLowerCase());
			if (mimeType == null) {
				mimeType = UNKNOWN_MINE;
			}

			System.err.println(fileName + " -> " + ext + " -> " + mimeType);

			// For each of the Q filenames, display on a line the corresponding MIME type.
			// If there is no corresponding type, then display UNKNOWN.
			System.out.println(mimeType);
		}

	}

}
