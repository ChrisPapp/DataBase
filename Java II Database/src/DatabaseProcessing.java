import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

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
		ArrayList<String> array1 = new ArrayList<String>(); //for lines
		ArrayList<String> array2 = new ArrayList<String>(); //for columns
		for(int i=0; i<lists.size(); i++) {
			array1.add(String.valueOf(i));
		}
		
		for (int i=0; i<lists.get(0).size(); i++) {
			array2.add(String.valueOf(i));
			System.out.printf("%-2s%-18s"," ",array2.get(i));
		}
		System.out.println();
		for (int line = 0; line < lists.size(); line++) {
			System.out.print (array1.get(line) + "|");
			for (int field = 0; field < lists.get(line).size(); field++) {
				System.out.printf("%-20s",lists.get(line).get(field));
			}
			System.out.println(); // After printing a line, print a new line
		}
		System.out.println();
		DatabaseProcessing.rotate(lists); //Rotates back to Column mode
	}

	public static void removeLine(ArrayList<ArrayList<Object>> lists) {
		System.out.print("Which line do you want to remove? ");
		Scanner input = new Scanner(System.in);
		int line = Integer.parseInt(input.nextLine());
		System.out.println();
		//input.close();
		DatabaseProcessing.rotate(lists); //Line mode
		lists.remove(line);
		DatabaseProcessing.rotate(lists); //Back to column mode
	} 
	
	public static void changeData(ArrayList<ArrayList<Object>> lists) {
		DatabaseProcessing.rotate(lists); //Line mode
		System.out.println("Which data do you want to change? (Type position)");
		Scanner input = new Scanner(System.in);
		System.out.print("Type line: ");
		int line = Integer.parseInt(input.nextLine());
		System.out.print("Type column: ");
		int column = Integer.parseInt(input.nextLine());
		System.out.println();
		DatabaseProcessing.rotate(lists); //Column mode
		System.out.print("Enter new data for '" + lists.get(column).get(line) + "': ");
		String data = input.nextLine();
		System.out.println();
		lists.get(column).set(line, data);
	}

	public static void swapLine(ArrayList<ArrayList<Object>> lists, int a, int b) {
		for (int i = 0; i < lists.size(); i++) {
			Collections.swap(lists.get(i), a, b);
		}
	}

}
