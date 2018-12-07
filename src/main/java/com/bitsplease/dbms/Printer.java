package com.bitsplease.dbms;

import java.util.ArrayList;

/**
 * Display Database either the whole data names or a shortcut.
 * 
 * @author The BitsPlease Project
 *
 */

public class Printer {

	// By default, print data with variable margin
	private static boolean marginVariable = true;

	public Printer() {

	}

	/**
	 * Calculate the length of the biggest String in each column.
	 * 
	 * @param arraylist
	 *            given to calculate
	 * @return an ArrayList with lengths
	 */
	private static ArrayList<Integer> dataLength(ArrayList<ArrayList<String>> arraylist) {
		TableProcessing.rotate(arraylist); // Line mode
		ArrayList<Integer> stringLength = new ArrayList<Integer>();
		for (int i = 0; i < arraylist.size(); i++) {
			int length = (arraylist.get(i).get(0)).length();
			for (int j = 0; j < arraylist.get(0).size(); j++) {
				if ((arraylist.get(i).get(j)).length() > length) {
					length = (arraylist.get(i).get(j)).length();
				}
			}
			stringLength.add(length);
		}
		TableProcessing.rotate(arraylist); // Back to column mode
		return stringLength;
	}

	/**
	 * Print columns' names (e.g. A, B,...).
	 * 
	 * @param arraylist
	 *            to find how many columns there are
	 */
	private static void columnName(ArrayList<ArrayList<String>> arraylist) {
		TableProcessing.rotate(arraylist); // Line mode
		ArrayList<String> names = new ArrayList<String>();
		ArrayList<Integer> length = dataLength(arraylist);
		names.add(TableProcessing.numToCode(0)); // first name needs different space
		System.out.printf("%-5s%-" + length.get(0) + "s", " ", names.get(0));
		for (int i = 1; i < arraylist.get(0).size(); i++) {
			// turn column's number to a String
			String cell = TableProcessing.numToCode(i);
			names.add(cell);
			System.out.printf("%-3s%-" + length.get(i) + "s", " ", names.get(i));
		}
		System.out.println();
		TableProcessing.rotate(arraylist); // Back to column mode
	}

	/**
	 * Print formatted line with data.
	 * 
	 * @param arraylist
	 *            with data to print
	 * @param line
	 *            which line should be printed
	 */
	public static void printLine(ArrayList<ArrayList<String>> arraylist, final int line) {
		ArrayList<Integer> length = dataLength(arraylist);
		System.out.printf("%4d%s", (line + 1), "|");
		for (int field = 0; field < arraylist.get(0).size(); field++) {
			// format line considering the biggest length
			System.out.printf("%-" + length.get(field) + "s   ", arraylist.get(line).get(field));
		}
		System.out.println();
	}

	/**
	 * Display data and columns' names.
	 * 
	 * @param arraylist
	 *            that should be printed
	 */
	public static void displayData(ArrayList<ArrayList<String>> arraylist) {
		columnName(arraylist);
		TableProcessing.rotate(arraylist); // Line mode

		for (int line = 0; line < arraylist.size(); line++) {
			printLine(arraylist, line);
		}
		TableProcessing.rotate(arraylist); // Back to column mode

	}

	/**
	 * Print data up to 10 characters. The rest are replaced with dots.
	 * 
	 * @param arraylist
	 *            that should be printed
	 */
	public static void printShortcut(ArrayList<ArrayList<String>> arraylist) {

		TableProcessing.rotate(arraylist); // Line mode
		ArrayList<String> names = new ArrayList<String>();
		// column name
		names.add(TableProcessing.numToCode(0));
		System.out.printf("%-5s%-10s", " ", names.get(0));
		for (int i = 1; i < arraylist.get(0).size(); i++) {
			String cell = TableProcessing.numToCode(i);
			names.add(cell);
			System.out.printf("%-5s%-10s", " ", names.get(i));

		}
		System.out.println();
		// replace characters up to 10 with "..."
		for (int line = 0; line < arraylist.size(); line++) {
			System.out.printf("%4d%s", (line + 1), "|");
			for (int field = 0; field < arraylist.get(line).size(); field++) {
				if ((arraylist.get(line).get(field)).length() > 10) {
					System.out.printf("%-10s%s", (arraylist.get(line).get(field)).substring(0, 10), "...  ");
				} else {
					System.out.printf("%-15s", arraylist.get(line).get(field));
				}
			}
			// After printing a line with data print a new line
			System.out.println();
		}
		TableProcessing.rotate(arraylist);
	}

	public static boolean isMarginVariable() {
		return marginVariable;
	}

	public static void setMarginVariable(boolean marginVariable) {
		Printer.marginVariable = marginVariable;
	}
}
