import java.util.*;

public class DatabaseProcessing {
	// Process data from database

	public DatabaseProcessing() {

	}

	public static void removeLine(ArrayList<ArrayList<Object>> lists, int line) {
		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).remove(line);
		}
	}

	public static void swap(ArrayList<ArrayList<Object>> lists, int a, int b) {
		for (int i = 0; i < lists.size(); i++) {
			Collections.swap(lists.get(i), a, b);
		}
	}

	public static void dataChange(ArrayList<ArrayList<Object>> lists) {

	}
}
