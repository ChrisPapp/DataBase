package Dbms;
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
		DatabaseProcessing.rotate(lists); // Every ArrayList is now a line (Line mode)
		System.out.println();
		ArrayList<Integer> array1 = new ArrayList<Integer>(); // for lines
		ArrayList<String> array2 = new ArrayList<String>(); // for columns
		for (int i = 0; i < lists.size(); i++) {
			array1.add(i + 1);
		}
		for (int i = 0; i < lists.get(0).size(); i++) {
			String cell = numToCode(i + 1);
			array2.add(cell);
			System.out.printf("%-5s%-20s", " ", array2.get(i));
		}
		System.out.println();
		for (int line = 0; line < lists.size(); line++) {
			System.out.printf("%4d%s", array1.get(line), "|");
			for (int field = 0; field < lists.get(line).size(); field++) {
				System.out.printf("%-25s", lists.get(line).get(field));
			}
			System.out.println(); // After printing a line, print a new line
		}
		System.out.println();
		DatabaseProcessing.rotate(lists); // Rotates back to Column mode
	}

	public static void removeLine(ArrayList<ArrayList<Object>> lists) {
		System.out.print("WHICH LINE DO YOU WANT TO REMOVE? ");
		Scanner input = new Scanner(System.in);
		int line = Integer.parseInt(input.nextLine()) - 1; // Subtract 1 (ArrayList indexes start from 0)
		// input.close();
		DatabaseProcessing.rotate(lists); // Line mode
		lists.remove(line);
		DatabaseProcessing.rotate(lists); // Back to column mode
	}

	public static void changeData(ArrayList<ArrayList<Object>> lists) {
		DatabaseProcessing.rotate(lists); // Line mode
		System.out.println("WHICH CELL DO YOU WANT TO CHANGE? (Type position)");
		Scanner input = new Scanner(System.in);
		String cell = input.nextLine();
		if (cell.matches("\\d+")) { // cell given has only digits
			System.out.println("Work in Progress... ChangeLine(Integer.parseInt(cell))"); // Change whole Line
		} else if (cell.matches("[A-Z]+")) { // cell given has only upper case characters
			System.out.println("Work in Progress... ChangeColumn(CodeToNum() -1)"); // Change whole column
		} else if (cell.matches("[A-Z]+\\d+")) { // cell given has the structure we want
			StringBuilder sb = new StringBuilder(); // Build a new sequence that will contain only the characters
			int i = 0;
			do {
				char currentCharacter = cell.charAt(i);
				sb.append(currentCharacter);
			} while (!Character.isDigit(cell.charAt(++i))); // Stop if next char is a digit
			String columnCode = sb.toString();
			int line = Integer.parseInt(cell.substring(i)) - 1; // Add the remaining characters (the numbers) to line
			int column = codeToNum(columnCode) - 1;
			DatabaseProcessing.rotate(lists); // Column mode
			System.out.print("ENTER NEW DATA FOR '" + lists.get(column).get(line) + "': ");
			String data = input.nextLine();
			System.out.println();
			lists.get(column).set(line, data);
		} else
			System.out.println("PROBLEM");
	}

	public static boolean isNumber(String data) {
		return (data.matches("[+-]?(\\d+|\\d*\\.?\\d+)"));
	}

	public static int codeToNum(String code) {
		int num = 0;
		for (int i = 0; i < code.length(); i++) {
			num *= 26; // 26 characters on English Alphabet
			num += code.charAt(i) - ('A' - 1); // Transposes character from ASCII to (A=1, B=2...Z=26)
												// and adds this value to num
		}
		return num;
	}

	public static String numToCode(int num) { // This is the inverse function of CodeToNum
		StringBuilder sb = new StringBuilder();
		while (num-- > 0) { // "num--" required for a bug fix
			int lastCharASCIIcode = num % 26 + 'A'; // Find last character
			sb.append((char) lastCharASCIIcode); // Add last character to a string
			num /= 26;
		}
		return sb.reverse().toString(); // Return the reverse
	}

	public static void swapLine(ArrayList<ArrayList<Object>> lists, int a, int b) {
		for (int i = 0; i < lists.size(); i++) {
			Collections.swap(lists.get(i), a, b);
		}
	}

}