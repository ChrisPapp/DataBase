import java.util.*;

public class DatabaseProcessing {
	// Process data from database

	public DatabaseProcessing() {

	}

	public static void rotate(ArrayList<ArrayList<Object>> lists) {
		ArrayList<ArrayList<Object>> temp = new ArrayList<ArrayList<Object>>();
		for (int line = 0; line < lists.get(0).size(); line++) {
			temp.add(new ArrayList<Object>());
			for (int column = 0; column < lists.size(); column++) {
				temp.get(line).add(lists.get(column).get(line));
			}
		}
		lists.clear();
		lists.addAll(temp);
		temp.clear(); // Free space

	}

	public static void displayData(ArrayList<ArrayList<Object>> lists) {
		DatabaseProcessing.rotate(lists); //Every ArrayList is now a line (Line mode)
		System.out.println();
		for (int line = 0; line < lists.size(); line++) {
			for (int field = 0; field < lists.get(line).size(); field++) {
				System.out.print(lists.get(line).get(field) + " ");
			}
			System.out.println(); // After printing a line, print a new line
		}
		DatabaseProcessing.rotate(lists); //Rotates back to Column mode
	}

	public static void removeLine(ArrayList<ArrayList<Object>> lists) {
		System.out.println("Which line do you want to remove");
		Scanner input = new Scanner(System.in);
		int line = Integer.parseInt(input.nextLine());
		//input.close();
		DatabaseProcessing.rotate(lists); //Line mode
		lists.remove(line);
		DatabaseProcessing.rotate(lists); //Back to column mode
	}
	
	public static void changeData(ArrayList<ArrayList<Object>> lists) {
		DatabaseProcessing.rotate(lists); //Line mode
		System.out.println("Which Field");
		System.out.println(lists.get(0)); //Line with fields
		Scanner input = new Scanner(System.in);
		int column = Integer.parseInt(input.nextLine());
		DatabaseProcessing.rotate(lists); //Column mode
		System.out.println("Which one?");
		System.out.println(lists.get(column)); //Print the column selected
		int line = Integer.parseInt(input.nextLine());
		System.out.println("Enter new data");
		String data = input.nextLine();
		lists.get(column).set(line, data);
	}

	public static void swapLine(ArrayList<ArrayList<Object>> lists, int a, int b) {
		for (int i = 0; i < lists.size(); i++) {
			Collections.swap(lists.get(i), a, b);
		}
	}

}
