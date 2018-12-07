package com.bitsplease.dbms;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

/**
 * This class is used to process a table.
 * 
 * @author The BitsPlease Project
 *
 */
public class TableProcessing {

	/**
	 * Default Constructor is used for this class.
	 */
	public TableProcessing() {

	}

	/**
	 * Rotate the given table.
	 * 
	 * @param lists
	 *            given table to rotate
	 */
	public static void rotate(ArrayList<ArrayList<String>> lists) {
		ArrayList<ArrayList<String>> temp = new ArrayList<ArrayList<String>>();
		for (int line = 0; line < lists.get(0).size(); line++) {
			temp.add(new ArrayList<String>());
			for (int column = 0; column < lists.size(); column++) {
				temp.get(line).add(lists.get(column).get(line));
			}
		}
		lists.clear();
		lists.addAll(temp);
		temp.clear(); // Free space

	}

	/**
	 * Remove a line from the table processing. Dynamically asks the user which line
	 * wants to remove.
	 * 
	 * @param lists
	 *            to remove a line from it.
	 */
	public static void removeLine(ArrayList<ArrayList<String>> lists) {
		System.out.print("WHICH LINE DO YOU WANT TO REMOVE? ");
		Scanner input = new Scanner(System.in);
		int line = Integer.parseInt(input.nextLine()) - 1;
		// input.close();
		TableProcessing.rotate(lists); // Line mode
		if (line > 0 && line < lists.size()) {
			lists.remove(line);
		} else {
			System.out.println("OUT OF BOUNDS");
		}
		TableProcessing.rotate(lists); // Back to column mode
	}

	/**
	 * Similar to removeLine, removeColumn is responsible for removing a Column from
	 * the table.
	 * 
	 * @param lists
	 *            to remove a column from it.
	 */
	public static void removeColumn(ArrayList<ArrayList<String>> lists) {
		System.out.print("WHICH COLUMN DO YOU WANT TO REMOVE? ");
		Scanner input = new Scanner(System.in);
		int column = codeToNum(input.nextLine());
		// input.close();
		if (column > 0 && column < lists.size()) {
			lists.remove(column);
		} else {
			System.out.println("OUT OF BOUNDS");
		}
	}

	/**
	 * Change the data stored in a specific cell of the table.
	 * 
	 * @param lists
	 *            to make alterations.
	 * @param cell
	 *            String value of the specific cell user wants to change.
	 */
	public static void changeData(ArrayList<ArrayList<String>> lists, String cell) {
		Scanner input = new Scanner(System.in);
		if (equalsExit(cell)) {
			System.out.println("DATA CHANGE STOPPED");
		} else if (cell.matches("\\d+")) { // cell given has only digits
			// Change whole Line
			for (int i = 0; i < lists.size(); i++) {
				changeData(lists, numToCode(i) + cell);
			}
		} else if (cell.matches("[A-Z]+")) {
			// cell given has only upper case characters
			// Change whole column
			int column = codeToNum(cell);
			for (int i = 1; i <= lists.get(column).size(); i++) {
				changeData(lists, cell + Integer.toString(i));
			}
		} else if (cell.matches("[A-Z]+\\d+")) {
			// cell given has the structure we want
			// Build a new sequence that will contain only the characters
			StringBuilder sb = new StringBuilder();
			int i = 0;
			do {
				char currentCharacter = cell.charAt(i);
				sb.append(currentCharacter);
			} while (!Character.isDigit(cell.charAt(++i))); // Stop if next char is a
															// digit
			String columnCode = sb.toString();
			// Add the remaining characters (the numbers) to line
			int line = Integer.parseInt(cell.substring(i)) - 1;
			int column = codeToNum(columnCode);
			if (column < lists.size()) {
				if (line < lists.get(column).size()) {
					// Catch OutOfBoundsException
					System.out.print("ENTER NEW DATA FOR '" + lists.get(column).get(line) + "': ");
					String data = input.nextLine();
					System.out.println();
					lists.get(column).set(line, data);
				} else {
					System.out.println("OUT OF BOUNDS");
				}
			} else {
				System.out.println("OUT OF BOUNDS");
			}
		} else {
			System.out.println("WRONG INPUT");
		}
	}

	/**
	 * isNumber examines whether a string value is basically a number.
	 * 
	 * @param data
	 *            String user wants to examine.
	 * @return Returns true if the string given is a number, else returns false.
	 */
	public static boolean isNumber(String data) {
		return (data.matches("[+-]?(\\d+|\\d*\\.?\\d+)"));
	}

	/**
	 * Convert String into Integer.
	 * 
	 * @param code
	 *            data needs to be converted.
	 * @return the integer result of conversion.
	 */
	public static int codeToNum(String code) {
		int num = 0;
		for (int i = 0; i < code.length(); i++) {
			num *= 26; // 26 characters on English Alphabet
			// Transposes character from ASCII to (A=0, B=1...Z=25)
			// and adds this value to num
			num += code.charAt(i) - 'A';
		}
		return num;
	}

	/**
	 * Convert Integer into String.
	 * 
	 * @param num
	 *            data needs to be converted.
	 * @return the String result of conversion.
	 */
	public static String numToCode(int num) {
		// This is the inverse function of CodeToNum
		StringBuilder sb = new StringBuilder();
		do {
			int lastCharASCIIcode = num % 26 + 'A'; // Find last character
			sb.append((char) lastCharASCIIcode); // Add last character to a string
			num /= 26;
		} while (num > 0);
		return sb.reverse().toString(); // Return the reverse
	}

	public static boolean equalsExit(String answer) {
		// Used when it is needed to check if user types exit
		if (answer.equals("exit") || answer.equals("Exit") || answer.equals("EXIT")) {
			return true;
		} else
			return false;
	}

	public static void swapLine(final ArrayList<ArrayList<String>> lists, final int a, final int b) {
		for (int i = 0; i < lists.size(); i++) {
			Collections.swap(lists.get(i), a, b);
		}
	}

	public static void printColumn(final ArrayList<String> arrayList) {
		for (int i = 0; i < arrayList.size(); i++) {
			System.out.printf("%s. %s\n", i + 1, arrayList.get(i));
		}
	}

	public static ArrayList<String> find(final ArrayList<ArrayList<String>> lists, final String item) {
		ArrayList<String> cells = new ArrayList<String>();
		for (int i = 0; i < lists.size(); i++) { // Search every column
			int index = lists.get(i).indexOf(item);
			if (index != -1) { // If file is found
				// Traspose to cell format and add it to cells list
				cells.add(numToCode(i) + (index + 1));
			}

		}
		return cells;
	}

	public static void sort(final ArrayList<ArrayList<String>> lists, final int column, final int low, final int high) {
		// This is a Quicksort implementation
		if (low < high) {

			String pivot = (String) lists.get(column).get(high);
			int i = low - 1;
			boolean allAreNumbers = TableArithmetics.areAllNumbers(lists, column, false);
			for (int j = low; j < high; j++) {
				String current = (String) lists.get(column).get(j);
				if (allAreNumbers) {
					// Numeric Sort
					if (Double.parseDouble(pivot) >= Double.parseDouble(current)) {
						i++;
						swapLine(lists, i, j);
					}
				} else {
					// Lexicographical Sort
					if (pivot.compareTo(current) == 1 || pivot.equals(current)) {
						i++;
						swapLine(lists, i, j);
					}
				}
			}
			swapLine(lists, i + 1, high);
			int pi = i + 1;
			sort(lists, column, low, pi - 1);
			sort(lists, column, pi + 1, high);
		}
	}

}
