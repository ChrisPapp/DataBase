package com.bitsplease.dbms;

import java.util.ArrayList;

/**
 * Makes arithmetic operations between columns.
 * 
 * @author The BitsPlease Project
 *
 */
public class TableArithmetics {
  private static int countTemporaryColumns = 0;

  private static boolean wasInsideOnce = false;
  private static boolean addSymbol = false;

  private static String nameOfNewColumn;

  /**
   * Removes all spaces from the given arithmetic operation and reads the inner
   * pair of parenthesis which have priority. Starts the calculation and replaces
   * the previous String of the mathematical operation with String 'Result'. If
   * there are parallel arithmetic operations like ( (something) / (something) )
   * adds new columns in the ArrayList, does the calculations and then removes them.
   * 
   * @param lists
   *            An ArrayList table to calculate (database)
   * @param mathOperation
   *            The mathematical operation
   */
  public static void startingOperationsBetweenColumns(ArrayList<ArrayList<String>> lists, String mathOperation) {

    int numberOfParentheses;
    int[] startFrom = new int[2];
    ArrayList<Integer> positionOfParentheses = new ArrayList<Integer>();

    mathOperation = mathOperation.replaceAll("\\s+", "");
    mathOperation = addParenthesesAtStartAndEnd(mathOperation);
    nameOfNewColumn = gettingTheNameOfTheColumn(mathOperation);
    if (mathOperation.contains("=")) {
      mathOperation = mathOperation.substring(0, mathOperation.indexOf("="));
    }

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

        System.out.println(sbMathOperation.toString());

        if (sbMathOperation.toString().matches(nameOfNewColumn)) {
          sbMathOperation.delete(0, sbMathOperation.length());
          positionOfParentheses.clear();
          continue;
        }
        if (mathOperation.contains(nameOfNewColumn) && !sbMathOperation.toString().contains(nameOfNewColumn)) {
          temporaryColumn = true;
          countTemporaryColumns++;
        }
        StringBuilder sbmo = new StringBuilder(mathOperation);
        if (temporaryColumn) {
          sbmo.replace(startFrom[0], startFrom[1] + 1,
              "TemporaryColumn" + TableProcessing.numToCode(countTemporaryColumns));
        } else {
          sbmo.replace(startFrom[0], startFrom[1] + 1, nameOfNewColumn);
        }
        mathOperation = sbmo.toString();

        seperatingTheVariablesOfOperation(lists, sbMathOperation.toString(), temporaryColumn);
        sbMathOperation.delete(0, sbMathOperation.length());
        positionOfParentheses.clear();

      } catch (ArrayIndexOutOfBoundsException e) {
      }
    }

    for (int i = 0; i < countTemporaryColumns; i++) {
      lists.remove(lists.size() - 2);
    }
    countTemporaryColumns = 0;
    wasInsideOnce = false;
    addSymbol = false;
  }

  /**
   * Receives the specific String of the arithmetic operation and separates the
   * names of the columns or the numbers and enters them to ArrayLists. It stores
   * the position of the mathematical operators and the position of Result.
   * 
   * @param lists
   *            The ArrayList table to calculate (database)
   * @param sbToString
   *            is the mathematical operation
   * @param temporaryColumn
   *            if we have parallel operations
   *
   */
  public static void seperatingTheVariablesOfOperation(ArrayList<ArrayList<String>> lists, String sbToString,
      boolean temporaryColumn) {
    ArrayList<String> fields = new ArrayList<String>();
    ArrayList<String> digits = new ArrayList<String>();
    ArrayList<Integer> position = new ArrayList<Integer>();
    int positionOfResult = -1;

    if ((!addSymbol && !wasInsideOnce) || temporaryColumn || sbToString.startsWith(nameOfNewColumn)) {
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

  /**
   * Starts executing the operation by doing the Result. It initializes a
   * counter for the position of the operators, which depends on whether the
   * operation starts with a mathematical operator or includes temporary columns,
   * and we increase it by 2, because between the position of the operators there
   * are the ones of the names/numbers.
   * 
   */
  public static void executeOperation(ArrayList<ArrayList<String>> lists, ArrayList<String> fields,
      ArrayList<String> digits, ArrayList<Integer> position, String sbToString, int positionOfResult,
      boolean temporaryColumn) {

    int countFields = 0;
    int countDigits = 0;
    int countHasStoppedForPosition = 1;
    int sizeOfPositions = position.size();
    String nameOfColumn = nameOfNewColumn;
    boolean booForSymbol = startsWithSymbol(sbToString);
    boolean tempColumn = temporaryColumn;
    boolean boo = booForSymbol || temporaryColumn;

    if (positionOfResult > 0
        && (sbToString.charAt(positionOfResult - 1) == '-' || sbToString.charAt(positionOfResult - 1) == '/')) {
      sbToString = addPlusSymbolAtStartOfOperation(sbToString);
    }

    if (temporaryColumn) {
      nameOfColumn = "TemporaryColumn" + TableProcessing.numToCode(countTemporaryColumns);
    }
    System.out.println(nameOfColumn);

    if (!boo) {
      countHasStoppedForPosition = 0;
    }

    if (positionOfResult != -1) {
      makeOperationOfResult(lists, sbToString, positionOfResult);
    }

    System.out.println("I am in! executeOperation");
    outerloop:
    while (countHasStoppedForPosition < sizeOfPositions) {
      try {
        int at = -1;
        if (Character.isLetter(sbToString.charAt(position.get(countHasStoppedForPosition)))) {
          at = whereIsField(lists, fields.get(countFields));
          if (at == -1) {
            break outerloop;
          }
          System.out.println(at);
          if (lists.get(at).get(0).equals(nameOfNewColumn)) {
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
            addColumn(lists, nameOfColumn, at);
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

  /**
   * Makes the arithmetic operations for column's names.
   * 
   */
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

  /**
   * Makes the arithmetic operations for numbers.
   * 
   */
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

  /**
   * If the arithmetic operation contains the symbol of equality, gets the right hand
   * part of it and names the new column. Otherwise the new
   * column get the name Result.
   * 
   */
  public static String gettingTheNameOfTheColumn(String mathOperation) {
    int positionOfEquality = -1;
    for (int i = 0; i < mathOperation.length(); i++) {
      if (mathOperation.charAt(i) == '=') {
        positionOfEquality = i + 1;
        break;
      }
    }
    if (positionOfEquality == -1) {
      return "Result";
    } else {
      return mathOperation.substring(positionOfEquality);
    }
  }

  /**
   * Finds the inner parentheses which have priority by counting the first round bracket.
   * It stops if it has searched all the parentheses and got the maximum
   * amount of them.
   * 
   * @return An array with two integers which are the positions of the inner pair
   *         of parentheses.
   */
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

  /**
   * Makes the Result operation.
   * 
   */
  public static void makeOperationOfResult(ArrayList<ArrayList<String>> lists, String sbToString,
      int positionOfResult) {
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

  /**
   * Adds parenthesis at the start and at the end of the arithmetic operation.
   * 
   */
  public static String addParenthesesAtStartAndEnd(String mathOperation) {
    int position;
    StringBuilder newMathOperation = new StringBuilder(mathOperation);
    if (mathOperation.contains("=")) {
      position = mathOperation.indexOf("=");
      newMathOperation.insert(0, "(");
      newMathOperation.insert(position + 1, ")");
    } else {
      newMathOperation.insert(0, "(");
      newMathOperation.insert(newMathOperation.length(), ")");
    }
    return newMathOperation.toString();
  }

  /**
   * Checks if the arithmetic operation starts with mathematical operator.
   * 
   */
  public static boolean startsWithSymbol(String mathOperation) {
    if (mathOperation.charAt(0) == '+' || mathOperation.charAt(0) == '-') {
      return true;
    } else {
      return false;
    }
  }

  /**
   * Checks if the given column name exists.
   * 
   */
  public static boolean atLeastOneResult(String field) {
    boolean boo = false;
    if (field.equals(nameOfNewColumn)) {
      boo = true;
    }
    return boo;
  }

  /**
   * Adds a new column to the ArrayList table with a name depending on whether
   * there is a column named Result or not.
   *
   */
  public static void addColumn(ArrayList<ArrayList<String>> lists, String name, int at) {
    if (name.startsWith("TemporaryColumn")) {
      lists.add(lists.size() - 1, new ArrayList<String>());
      lists.get(lists.size() - 2).add(name);
      for (int i = 1; i < lists.get(at).size(); i++) {
        lists.get(lists.size() - 2).add("0");
      }
    } else if (name == nameOfNewColumn) {
      lists.add(new ArrayList<String>());
      lists.get(lists.size() - 1).add(name);
      for (int i = 1; i < lists.get(at).size(); i++) {
        lists.get(lists.size() - 1).add("0");
      }
    }
  }

  /**
   * Adds plus symbol at the start of the arithmetic operation.
   * 
   */
  public static String addPlusSymbolAtStartOfOperation(String mathOperation) {
    if (Character.isLetter(mathOperation.charAt(0)) || Character.isDigit(mathOperation.charAt(0))
        || mathOperation.charAt(0) == ' ') {
      return '+' + mathOperation;
    } else {
      return mathOperation;
    }

  }

  /**
   * Searching for parentheses and enter the position of them at an ArrayList.
   * 
   */
  public static ArrayList<Integer> searchingForParenthesis(String mathOperation) {
    ArrayList<Integer> positionOfParenthesis = new ArrayList<Integer>();
    for (int i = 0; i < mathOperation.length(); i++) {
      if (mathOperation.charAt(i) == '(' || mathOperation.charAt(i) == ')') {
        positionOfParenthesis.add(i);
      }
    }
    return positionOfParenthesis;
  }

  /**
   * Finds the position of the name of the field from the lists.
   *
   */
  public static int whereIsField(final ArrayList<ArrayList<String>> lists, final String given) {
    System.out.println("I am in! whereIsField");
    int position = -1;
    for (int i = 0; i < lists.size(); i++) {
      if (lists.get(i).get(0).equals(given)) {
        position = i;
        break;
      }
    }
    return position;
  }

  /**
   * Checks if all the variables on the selected column are numbers.
   * 
   */
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
