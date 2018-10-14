import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class TestDatabase {
	Scanner scanner = new Scanner(System.in);

	List<List> lists = new ArrayList<List>();
	List<String> names = new ArrayList<String>();

	public TestDatabase() {
		input();
		output();
	}

	public void swap(int a, int b) {
		for (int i = 0; i < lists.size(); i++) {
			Collections.swap(lists.get(i), a, b);
		}
	}

	public void removeLine(int line) {
		for (int i = 0; i < lists.size(); i++) {
			lists.get(i).remove(line);
		}
	}

	public void input() {
		while (true) {
			System.out.println("Enter 0 for String");
			System.out.println("Enter 1 for Number");
			System.out.println("Enter 69 to Stop");
			int answer = Integer.parseInt(scanner.nextLine());
			if (answer == 0) {
				lists.add(new ArrayList<String>());
				System.out.println("Enter the name of this Data:");
				names.add(scanner.nextLine());
			} else if (answer == 1) {
				lists.add(new ArrayList<Integer>());
				System.out.println("Enter the name of this Data:");
				names.add(scanner.nextLine());
			} else break;
		}
		for (int i = 0; i < lists.size(); i++) {
			while (true) {
				System.out.println("Give me " + names.get(i) + " or give me a 69 to stop");
				String answer = scanner.nextLine();
				if (answer.equals("69")) {
					break;
				}
				lists.get(i).add(answer);
			}
		}
	}
	
	public void output() {
		int counter = 0;
		printNames();
		for (int i = 0; i < lists.get(0).size(); i++) {
			for (int j = 0; j < lists.size(); j++) {
				System.out.print(lists.get(j).get(i) + " ");
				if (++counter % lists.size() == 0) System.out.println();
			}
		}
	}
	
	public void printNames() {
		for (int i = 0; i < names.size(); i++) {
			System.out.print(names.get(i) + " ");
		}
		System.out.println();
	}

	public static void main(String args[]) {
		TestDatabase lists = new TestDatabase();
	}

}

