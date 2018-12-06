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

		ArrayList<Integer> positionOfParenthesis = new ArrayList<Integer>();
		positionOfParenthesis = searchingForParenthesis(mathOperation);

		for (int i = 0; i < positionOfParenthesis.size(); i++) {
			System.out.println(positionOfParenthesis.get(i));
		}

		positionOfParenthesis.clear();
		mathOperation = addParenthesisWhereIsNecessary(mathOperation);
		positionOfParenthesis = searchingForParenthesis(mathOperation);

		int start = -1;
		int end = 0;
		StringBuilder sbMathOperation = new StringBuilder();
		for (int countPosition = 0; countPosition < positionOfParenthesis.size() / 2; countPosition++) {
			try {
				System.out.println("Hello World");
				System.out.println(mathOperation);

				String sbToString;
				ArrayList<Integer> newPositionOfParenthesis = new ArrayList<Integer>();
				newPositionOfParenthesis = searchingForParenthesis(mathOperation);
				int standard = newPositionOfParenthesis.size() / 2;

				sbMathOperation.append(mathOperation, newPositionOfParenthesis.get(standard + start) + 1,
						newPositionOfParenthesis.get(standard + end));
				sbToString = sbMathOperation.toString();
				System.out.println(sbToString);
				sbMathOperation.delete(0, sbMathOperation.length());

				StringBuilder sbmo = new StringBuilder(mathOperation);
				sbmo.replace(newPositionOfParenthesis.get(standard + start),
						newPositionOfParenthesis.get(standard + end) + 1, "Result");
				mathOperation = sbmo.toString();

				seperatingTheVariablesOfOperation(lists, sbToString);
				System.out.println(lists.get(lists.size() - 1).get(1));
				System.out.println("I am in!");

			} catch (ArrayIndexOutOfBoundsException e) {
			}
		}

	}

	public static void seperatingTheVariablesOfOperation(ArrayList<ArrayList<String>> lists, String sbToString) {
		ArrayList<String> fields = new ArrayList<String>();
		ArrayList<String> digits = new ArrayList<String>();
		ArrayList<Integer> position = new ArrayList<Integer>();
		int positionOfResult = -1;
		boolean startsWithOperator = false;

		if (!addSymbol && !wasInsideOnce) {
			addSymbol = true;
			sbToString = addPlusSymbolAtStartOfOperation(sbToString);
		}

		System.out.println(sbToString);

		System.out.println("I am in! seperatingTheVariablesOfOperation");

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
				try {
					if (sbToString.charAt(j) == ' ') {
						sbToString.charAt(++j);
					}
				} catch (StringIndexOutOfBoundsException e) {
				} finally {
					position.add(j);
				}
				if (atLeastOneResult(sbFields.toString())) {
					positionOfResult = position.get(position.size() - 2);
				}
				System.out.println("positionOfResult  " + positionOfResult);
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
				try {
					if (sbToString.charAt(j) == ' ') {
						sbToString.charAt(++j);
					}
				} catch (StringIndexOutOfBoundsException e) {
				} finally {
					position.add(j);
				}
				digits.add(sbDigits.toString());
				i = j;
			} else if (sbToString.charAt(0) == '+' || sbToString.charAt(0) == '-') {
				int j = i;
				position.add(j);
				startsWithOperator = true;
				try {
					if (sbToString.charAt(j) == ' ') {
						sbToString.charAt(++j);
					}
				} catch (StringIndexOutOfBoundsException e) {
				}
			}
		}
		for (int i = 0; i < fields.size(); i++) {
			System.out.println(fields.get(i));
		}
		for (int i = 0; i < position.size(); i++) {
			System.out.println(position.get(i));
		}

		executeOperation(lists, fields, digits, position, sbToString, startsWithOperator, positionOfResult);
	}

	public static void executeOperation(ArrayList<ArrayList<String>> lists, ArrayList<String> fields,
			ArrayList<String> digits, ArrayList<Integer> position, String sbToString, boolean startsWithOperator,
			int positionOfResult) {

		int countFields = 0;
		int countDigits = 0;
		int countHasStoppedForPosition = 1;
		int sizeOfPositions = position.size();
		boolean boo = startsWithSymbol(sbToString);

		if (!boo) {
			countHasStoppedForPosition = 0;
		}

		if (positionOfResult != -1) {
			if (sbToString.charAt(positionOfResult - 1) == '-') {
				for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
					double temp1 = Double.parseDouble((String) lists.get(lists.size() - 1).get(b));
					double temp2 = -1;
					lists.get(lists.size() - 1).set(b, String.valueOf(temp1 * temp2));
				}
			}
		}

		System.out.println("I am in! executeOperation");
		while (countHasStoppedForPosition < sizeOfPositions) {
			try {
				int at = -1;
				if (Character.isLetter(sbToString.charAt(position.get(countHasStoppedForPosition)))) {
					at = whereIsField(lists, fields.get(countFields));
					if (lists.get(at).get(0).equals("Result")) {
						countFields++;
						countHasStoppedForPosition += 2;
						continue;
					}
					if (!wasInsideOnce) {
						wasInsideOnce = true;
						addColumn(lists, "Result", at);
						switchForStrings(lists, position, sbToString, countHasStoppedForPosition, at, boo);
					} else {
						switchForStrings(lists, position, sbToString, countHasStoppedForPosition, at, boo);
					}
					countFields++;
				} else if (Character.isDigit(sbToString.charAt(position.get(countHasStoppedForPosition)))) {
					if (!wasInsideOnce) {
						wasInsideOnce = true;
						addColumn(lists, "Result", at); // to at na to allaksw
						switchForNumbers(lists, position, sbToString, digits, countDigits, countHasStoppedForPosition,
								boo);
					} else {
						switchForNumbers(lists, position, sbToString, digits, countDigits, countHasStoppedForPosition,
								boo);
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
			String sbToString, int countHasStopped, int at, boolean boo) {
		System.out.println("I am in! switchForStrings");
		if (!boo) {
			countHasStopped++;
		} else {
			countHasStopped--;
		}
		switch (sbToString.charAt(position.get(countHasStopped))) {
		case '+':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(lists.size() - 1).get(b));
				double temp2 = Double.parseDouble((String) lists.get(at).get(b));
				lists.get(lists.size() - 1).set(b, String.valueOf(temp1 + temp2));
			}
			break;
		case '-':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(lists.size() - 1).get(b));
				double temp2 = Double.parseDouble((String) lists.get(at).get(b));
				lists.get(lists.size() - 1).set(b, String.valueOf(temp1 - temp2));
			}
			break;
		case '*':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(lists.size() - 1).get(b));
				double temp2 = Double.parseDouble((String) lists.get(at).get(b));
				lists.get(lists.size() - 1).set(b, String.valueOf(temp1 * temp2));
			}
			break;
		case '/':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(lists.size() - 1).get(b));
				double temp2 = Double.parseDouble((String) lists.get(at).get(b));
				lists.get(lists.size() - 1).set(b, String.valueOf(temp1 / temp2));
			}
			break;
		default:
			break;
		}
	}

	public static void switchForNumbers(ArrayList<ArrayList<String>> lists, ArrayList<Integer> position,
			String sbToString, ArrayList<String> digits, int countDigits, int countHasStopped, boolean boo) {
		if (!boo) {
			countHasStopped++;
		} else {
			countHasStopped--;
		}
		System.out.println("I am in! switchForNumbers");
		switch (sbToString.charAt(position.get(countHasStopped))) {
		case '+':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(lists.size() - 1).get(b));
				double temp2 = Double.parseDouble((String) digits.get(countDigits));
				lists.get(lists.size() - 1).set(b, String.valueOf(temp1 + temp2));
			}
			break;
		case '-':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(lists.size() - 1).get(b));
				double temp2 = Double.parseDouble((String) digits.get(countDigits));
				lists.get(lists.size() - 1).set(b, String.valueOf(temp1 - temp2));
			}
			break;
		case '*':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(lists.size() - 1).get(b));
				double temp2 = Double.parseDouble((String) digits.get(countDigits));
				lists.get(lists.size() - 1).set(b, String.valueOf(temp1 * temp2));
			}
			break;
		case '/':
			for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
				double temp1 = Double.parseDouble((String) lists.get(lists.size() - 1).get(b));
				double temp2 = Double.parseDouble((String) digits.get(countDigits));
				lists.get(lists.size() - 1).set(b, String.valueOf(temp1 / temp2));
			}
			break;
		default:
			break;
		}
	}

	public static String addParenthesisWhereIsNecessary(String mathOperation) {
		StringBuilder sbmo = new StringBuilder(mathOperation);
		ArrayList<Integer> positionOfStartEndParenthesis = new ArrayList<Integer>();
		System.out.println(sbmo.toString());
		if (sbmo.charAt(0) != '(') {
			sbmo.insert(0, '(');
			sbmo.insert(sbmo.length(), ')');
		}
		System.out.println(sbmo.toString());
		for (int i = 0; i < sbmo.length(); i++) {
			if (sbmo.charAt(i) == '*' || sbmo.charAt(i) == '/') {
				System.out.println(i);
				boolean booFront = false;
				boolean booBack = false;
				int countBack = i;
				int countFront = i;
				while (!booBack) {
					countBack--;
					if (!(Character.isLetter(sbmo.charAt(countBack)) || Character.isDigit(sbmo.charAt(countBack)))) {
						booBack = true;
						sbmo.insert(++countBack, '(');
						System.out.println(sbmo.toString());
					}
				}
				while (!booFront) {
					try {
						countFront++;
						if (!(Character.isLetter(sbmo.charAt(countFront)) || Character.isDigit(sbmo.charAt(countFront))
								|| sbmo.charAt(countFront) == '*' || sbmo.charAt(countFront) == '/'
								|| sbmo.charAt(countFront) == '(')) {
							booFront = true;
							sbmo.insert(countFront, ')');
						} else if (sbmo.charAt(countFront) == '(') {
							System.out.println(sbmo.substring(countFront));
							positionOfStartEndParenthesis = searchingForParenthesis(sbmo.substring(countFront));
							int countStartingParenthesis = 0;
							int countEndingParenthesis = 0;
							int count = 0;
							while ((countStartingParenthesis != countEndingParenthesis) || count == 0) {
								System.out.println("i m in");
								try {
									if (sbmo.charAt(positionOfStartEndParenthesis.get(count)) == '(') {
										countStartingParenthesis++;
									} else if (sbmo.charAt(positionOfStartEndParenthesis.get(count)) == ')') {
										countEndingParenthesis++;
									}
									count++;
								} catch (IndexOutOfBoundsException e) {
									break;
								}
							}
							System.out.println("i m in if");
							countFront = positionOfStartEndParenthesis.get(--count) + i;
							System.out.println(countFront);
							booFront = true;
							sbmo.insert(countFront, ')');
							System.out.println(sbmo.toString());
						}
					} catch (StringIndexOutOfBoundsException e) {
					}
				}
				i = countFront;
			}
		}

		mathOperation = sbmo.toString();
		return mathOperation;
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
		lists.add(new ArrayList<String>());
		lists.get(lists.size() - 1).add(name);

		for (int i = 1; i < lists.get(at).size(); i++) {
			lists.get(lists.size() - 1).add("0");
		}
	}

	public static String addPlusSymbolAtStartOfOperation(String mathOperation) {
		if (Character.isLetter(mathOperation.charAt(0)) || Character.isDigit(mathOperation.charAt(0))
				|| mathOperation.charAt(0) == ' ') {
			StringBuilder newMathOperation = new StringBuilder();
			newMathOperation.append("+" + mathOperation);
			return newMathOperation.toString();
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
