package global.gameCapture;

import java.io.*;
import java.nio.*;

class DebugInput implements Readable {
	final BufferedReader in;
	final boolean logEnabled;

	int remaining = 0;
	StringReader stringReader;

	DebugInput(InputStream source, boolean logEnabled) {
		this.in = new BufferedReader(new InputStreamReader(source));
		this.logEnabled = logEnabled;
	}

	public int read(CharBuffer cb) throws IOException {
		if (remaining == 0) {
			String line = in.readLine();
			if (logEnabled) {
				System.err.println(line);
			}
			remaining = line.length() + 1;
			stringReader = new StringReader(line + '\n');
		}
		int n = stringReader.read(cb);
		remaining -= n;
		return n;
	}
}

