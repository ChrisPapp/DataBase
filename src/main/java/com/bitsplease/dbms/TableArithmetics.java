package com.bitsplease.dbms;

import java.util.ArrayList;

public class TableArithmetics {
	private static double sum = 0;
	private static double product = 1;
	private static double average = 0;

	private static boolean wasInsideOnce = false;
	private static boolean addSymbol = false;

	public static void sumOfAll(ArrayList<ArrayList<Object>> lists, int askedColumn) {
		for (int i = 1; i < lists.get(askedColumn).size(); i++) {
			double temp = Double.parseDouble((String) lists.get(askedColumn).get(i));
			sum += temp;
		}
	}

	public static void productOfAll(ArrayList<ArrayList<Object>> lists, int askedColumn) {
		for (int i = 1; i < lists.get(askedColumn).size(); i++) {
			double temp = Double.parseDouble((String) lists.get(askedColumn).get(i));
			product *= temp;
		}
	}

	public static void averageOfAll(ArrayList<ArrayList<Object>> lists, int askedColumn) {
		average = sum / (lists.get(askedColumn).size() - 1);
	}

	public static void displayMore(int askedColumn) {
		System.out.printf("Column %s \n", TableProcessing.numToCode(askedColumn));
		System.out.printf("Sum: %f \nProduct: %f \nAverage: %f \n", sum, product, average);
	}

	public static void startingOperationsBetweenColumns(ArrayList<ArrayList<String>> lists, String mathOperation) {
				
		int count = 0;
		int numberOfParentheses;
		int[] startFrom = new int[2];
		ArrayList<Integer> positionOfParentheses = new ArrayList<Integer>();
		
		mathOperation = mathOperation.replaceAll("\\s+", "");
		mathOperation = addParenthesesAtStartAndEnd(mathOperation);

		positionOfParentheses = searchingForParenthesis(mathOperation);
		numberOfParentheses = positionOfParentheses.size() / 2;
		positionOfParentheses.clear();

		StringBuilder sbMathOperation = new StringBuilder();
		for (int countPosition = 0; countPosition < numberOfParentheses; countPosition++) {
			try {
				boolean temporaryColumn = false;
				positionOfParentheses = searchingForParenthesis(mathOperation);
				startFrom = whereToStart(positionOfParentheses, mathOperation);
				sbMathOperation.append(mathOperation, startFrom[0] + 1, startFrom[1]);

				if (sbMathOperation.toString().matches("Result")) {
					sbMathOperation.delete(0, sbMathOperation.length());
					positionOfParentheses.clear();
					continue;
				}
				if (mathOperation.contains("Result") && !sbMathOperation.toString().contains("Result")) {
					temporaryColumn = true;
					count++;
				}
				StringBuilder sbmo = new StringBuilder(mathOperation);
				if (temporaryColumn) {
					sbmo.replace(startFrom[0], startFrom[1] + 1, "TemporaryColumn" + count);
				} else {
					sbmo.replace(startFrom[0], startFrom[1] + 1, "Result");
				}
				mathOperation = sbmo.toString();

				seperatingTheVariablesOfOperation(lists, sbMathOperation.toString(), temporaryColumn);
				sbMathOperation.delete(0, sbMathOperation.length()); 
				positionOfParentheses.clear();
		
			} catch (ArrayIndexOutOfBoundsException e) { }
		}
		
		for (int i = 1; i <= count; i++) {
			lists.remove(lists.size() - i - 1);
		}
	}

	public static void seperatingTheVariablesOfOperation(ArrayList<ArrayList<String>> lists, String sbToString,
			boolean temporaryColumn) {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<String> digits = new ArrayList<String>();
		ArrayList<Integer> position = new ArrayList<Integer>();
		int positionOfResult = -1;

		if ((!addSymbol && !wasInsideOnce) || temporaryColumn || sbToString.startsWith("Result")) {
			addSymbol = true;
			sbToString = addPlusSymbolAtStartOfOperation(sbToString);
		}


		for (int i = 0; i < sbToString.length(); i++) {
			if (Character.isLetter(sbToString.charAt(i))) {
				int j = i;
				position.add(j);
				StringBuilder sbFields = new StringBuilder();
				while (Character.isLetter(sbToString.charAt(j))) {
					sbFields.append(sbToString.charAt(j));
					try {
						j++;
						sbToString.charAt(j);
					} catch (StringIndexOutOfBoundsException e) {
						break;
					}
				}
				if (atLeastOneResult(sbFields.toString())) {
					positionOfResult = position.get(position.size() - 2);
				}
				System.out.println("positionOfResult  " + positionOfResult);
				position.add(j);
				fields.add(sbFields.toString());
				i = j;
			} else if (Character.isDigit(sbToString.charAt(i))) {
				int j = i;
				position.add(j);
				StringBuilder sbDigits = new StringBuilder();
				while (Character.isDigit(sbToString.charAt(j)) || sbToString.charAt(j) == '.') {
					sbDigits.append(sbToString.charAt(j));
					try {
						j++;
						sbToString.charAt(j);
					} catch (StringIndexOutOfBoundsException e) {
						break;
					}
				}
				position.add(j);
				digits.add(sbDigits.toString());
				i = j;
			} else if (sbToString.charAt(0) == '+' || sbToString.charAt(0) == '-') {
				int j = i;
				position.add(j);
			}
		}
		executeOperation(lists, fields, digits, position, sbToString, positionOfResult, temporaryColumn);
	}

	public static void executeOperation(ArrayList<ArrayList<String>> lists, ArrayList<String> fields,
			ArrayList<String> digits, ArrayList<Integer> position, String sbToString, int positionOfResult,
			boolean temporaryColumn) {

		int countFields = 0;
		int countDigits = 0;
		int countHasStoppedForPosition = 1;
		int sizeOfPositions = position.size();
		String nameOfColumn = "Result";
		boolean booForSymbol = startsWithSymbol(sbToString);
		boolean tempColumn = temporaryColumn;
		boolean boo = booForSymbol || temporaryColumn;

		if (temporaryColumn) {
			nameOfColumn = "TemporaryColumn";
		}
		System.out.println(nameOfColumn);

		if (!boo) {
			countHasStoppedForPosition = 0;
		}

		if (positionOfResult != -1) {
			try {
				if (sbToString.charAt(positionOfResult - 1) == '-') {
					for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
						double temp1 = Double.parseDouble((String) lists.get(lists.size() - 1).get(b));
						double temp2 = -1;
						lists.get(lists.size() - 1).set(b, String.valueOf(temp1 * temp2));
					}
				} else if (sbToString.charAt(positionOfResult - 1) == '/') {
					for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
						double temp1 = Double.parseDouble((String) lists.get(lists.size() - 1).get(b));
						lists.get(lists.size() - 1).set(b, String.valueOf(1 / temp1));
						StringBuilder sbmo = new StringBuilder(sbToString);
						sbmo.replace(positionOfResult - 1, positionOfResult - 1, "*");
						sbToString = sbmo.toString();
					}
				}
			} catch (IndexOutOfBoundsException e) {
			}
		}

		System.out.println("I am in! executeOperation");
		while (countHasStoppedForPosition < sizeOfPositions) {
			try {
				int at = -1;
				if (Character.isLetter(sbToString.charAt(position.get(countHasStoppedForPosition)))) {
					at = whereIsField(lists, fields.get(countFields));
					System.out.println(at);
					if (lists.get(at).get(0).equals("Result")) {
						countFields++;
						countHasStoppedForPosition += 2;
						continue;
					}
					if (!wasInsideOnce || tempColumn) {
						wasInsideOnce = true;
						tempColumn = false;
						addColumn(lists, nameOfColumn, at);
						switchForStrings(lists, position, sbToString, countHasStoppedForPosition, at, boo,
								temporaryColumn);
					} else {
						switchForStrings(lists, position, sbToString, countHasStoppedForPosition, at, boo,
								temporaryColumn);
					}
					countFields++;
				} else if (Character.isDigit(sbToString.charAt(position.get(countHasStoppedForPosition)))) {
					if (!wasInsideOnce || tempColumn) {
						wasInsideOnce = true;
						tempColumn = false;
						addColumn(lists, nameOfColumn, at); // to at na to allaksw
						switchForNumbers(lists, position, sbToString, digits, countDigits, countHasStoppedForPosition,
								boo, temporaryColumn);
					} else {
						switchForNumbers(lists, position, sbToString, digits, countDigits, countHasStoppedForPosition,
								boo, temporaryColumn);
					}
					countDigits++;
				}
				countHasStoppedForPosition += 2;
			} catch (StringIndexOutOfBoundsException e) {
				break;
			}
		}
	}

	public static void switchForStrings(ArrayList<ArrayList<String>> lists, ArrayList<Integer> position,
			String sbToString, int countHasStopped, int at, boolean boo, boolean temporaryColumn) {
		System.out.println("I am in! switchForStrings");
		int where = lists.size() - 1;
		if (temporaryColumn) {
			where--;
		}
		if (!boo) {
			countHasStopped++;
		} else {
			countHasStopped--;
		}
		System.out.println(where);
		switch (sbToString.charAt(position.get(countHasStopped))) {
		case '+':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(where).get(b));
				double temp2 = Double.parseDouble((String) lists.get(at).get(b));
				lists.get(where).set(b, String.valueOf(temp1 + temp2));
			}
			break;
		case '-':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(where).get(b));
				double temp2 = Double.parseDouble((String) lists.get(at).get(b));
				lists.get(where).set(b, String.valueOf(temp1 - temp2));
			}
			break;
		case '*':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(where).get(b));
				double temp2 = Double.parseDouble((String) lists.get(at).get(b));
				lists.get(where).set(b, String.valueOf(temp1 * temp2));
			}
			break;
		case '/':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(where).get(b));
				double temp2 = Double.parseDouble((String) lists.get(at).get(b));
				lists.get(where).set(b, String.valueOf(temp1 / temp2));
			}
			break;
		default:
			break;
		}
	}

	public static void switchForNumbers(ArrayList<ArrayList<String>> lists, ArrayList<Integer> position,
			String sbToString, ArrayList<String> digits, int countDigits, int countHasStopped, boolean boo,
			boolean temporaryColumn) {
		int where = lists.size() - 1;
		if (temporaryColumn) {
			where--;
		}
		if (!boo) {
			countHasStopped++;
		} else {
			countHasStopped--;
		}
		System.out.println("I am in! switchForNumbers");
		switch (sbToString.charAt(position.get(countHasStopped))) {
		case '+':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(where).get(b));
				double temp2 = Double.parseDouble((String) digits.get(countDigits));
				lists.get(where).set(b, String.valueOf(temp1 + temp2));
			}
			break;
		case '-':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(where).get(b));
				double temp2 = Double.parseDouble((String) digits.get(countDigits));
				lists.get(where).set(b, String.valueOf(temp1 - temp2));
			}
			break;
		case '*':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(where).get(b));
				double temp2 = Double.parseDouble((String) digits.get(countDigits));
				lists.get(where).set(b, String.valueOf(temp1 * temp2));
			}
			break;
		case '/':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(where).get(b));
				double temp2 = Double.parseDouble((String) digits.get(countDigits));
				lists.get(where).set(b, String.valueOf(temp1 / temp2));
			}
			break;
		default:
			break;
		}
	}

	public static int[] whereToStart(ArrayList<Integer> positionOfParenthesis, String mathOperation) {
		int maxStartingParenthesis = 0;
		int max = 0;
		int[] startFrom = new int[2];
		
		int tempStartingPosition = -1;
		for (int i = 0; i < positionOfParenthesis.size(); i++) {
			if (mathOperation.charAt(positionOfParenthesis.get(i)) == '(') {
				maxStartingParenthesis++;
				if (maxStartingParenthesis > max) {
					max = maxStartingParenthesis;
					tempStartingPosition = i;
				}
			} else if (mathOperation.charAt(positionOfParenthesis.get(i)) == ')') {
				maxStartingParenthesis--;
			}
		}
		startFrom[0] = positionOfParenthesis.get(tempStartingPosition);
		startFrom[1] = positionOfParenthesis.get(tempStartingPosition + 1);

		return startFrom;
	}

	public static String addParenthesesAtStartAndEnd(String mathOperation) {
		return '(' + mathOperation + ')';
	}

	public static boolean startsWithSymbol(String mathOperation) {
		if (mathOperation.charAt(0) == '+' || mathOperation.charAt(0) == '-') {
			return true;
		} else {
			return false;
		}
	}

	public static boolean atLeastOneResult(String field) {
		boolean boo = false;
		if (field.equals("Result")) {
			boo = true;
		}
		return boo;
	}

	public static void addColumn(ArrayList<ArrayList<String>> lists, String name, int at) {
		if (name.startsWith("TemporaryColumn")) {
			lists.add(lists.size() - 1, new ArrayList<String>());
			lists.get(lists.size() - 2).add(name);
			for (int i = 1; i < lists.get(at).size(); i++) {
				lists.get(lists.size() - 2).add("0");
			}
		} else if (name == "Result") {
			lists.add(new ArrayList<String>());
			lists.get(lists.size() - 1).add(name);
			for (int i = 1; i < lists.get(at).size(); i++) {
				lists.get(lists.size() - 1).add("0");
			}
		}
	}

	public static String addPlusSymbolAtStartOfOperation(String mathOperation) {
		if (Character.isLetter(mathOperation.charAt(0)) || Character.isDigit(mathOperation.charAt(0))
				|| mathOperation.charAt(0) == ' ') {
			return '+' + mathOperation;
		} else {
			return mathOperation;
		}

	}

	public static ArrayList<Integer> searchingForParenthesis(String mathOperation) {
		ArrayList<Integer> positionOfParenthesis = new ArrayList<Integer>();
		for (int i = 0; i < mathOperation.length(); i++) {
			if (mathOperation.charAt(i) == '(' || mathOperation.charAt(i) == ')') {
				positionOfParenthesis.add(i);
			}
		}
		return positionOfParenthesis;
	}

	public static int whereIsField(final ArrayList<ArrayList<String>> lists, final String given) {
		System.out.println("I am in! whereIsField");
		int i = 0;
		for (i = 0; i < lists.size(); i++) {
			if (lists.get(i).get(0).equals(given)) {
				break;
			}
		}
		return i;
	}

	public static boolean areAllNumbers(final ArrayList<ArrayList<String>> lists, final int askedColumn,
			boolean printMessages) {
		boolean areAllNumbers = true;
		for (int i = 1; i < lists.get(askedColumn).size(); i++) {
			if (!TableProcessing.isNumber((String) lists.get(askedColumn).get(i))) {
				if (printMessages) {
					System.out.println("ERROR! \nInvalid String at line " + i + ".");
					System.out.println("Please enter another value.");
				}
				areAllNumbers = false;
			}
		}
		return areAllNumbers;
	}
}
