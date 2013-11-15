package tuenti.p9;

import java.util.ArrayList;
import java.util.List;

public class DocumentInfo {

	public List<Ocurrence> occurrences = new ArrayList<Ocurrence>();

	public static class Ocurrence{
		int line;
		int word;
		int file;

		public Ocurrence(int file, int line, int word) {
			this.line = line;
			this.file = file;
			this.word = word;
		}

		@Override
		public String toString() {
			return file + "-" + line + "-" + word;
		}

	}

}
