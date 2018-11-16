package com.bitsplease.dbms;

import java.util.ArrayList;

public class DataProcessingMore {
  private static double sum = 0;
  private static double product = 1;
  private static double average = 0;

  public static void sumOfAll(ArrayList<ArrayList<Object>> lists,
      int askedColumn) {
    for (int i = 1; i < lists.get(askedColumn).size(); i++) {
      double temp = Double.parseDouble((String) lists.get(askedColumn).get(i));
      sum += temp;
    }
  }

  public static void productOfAll(ArrayList<ArrayList<Object>> lists,
      int askedColumn) {
    for (int i = 1; i < lists.get(askedColumn).size(); i++) {
      double temp = Double.parseDouble((String) lists.get(askedColumn).get(i));
      product *= temp;
    }
  }

  public static void averageOfAll(ArrayList<ArrayList<Object>> lists,
      int askedColumn) {
    average = sum / (lists.get(askedColumn).size() - 1);
  }

  public static void displayMore() {
    System.out.printf("%f %f %f \n\n", sum, product, average);
  }

  public static void extraordinaryOption(ArrayList<ArrayList<Object>> lists,
      String mathOperation) {
    int countFields = 0;
    int countDigits = 0;
    int countHasStopped = 0;
    String[] fields = new String[10];
    String[] digits = new String[50];
    ArrayList<Integer> position = new ArrayList<Integer>();
    boolean wasInsideOnce = false;
    lists.add(new ArrayList<Object>());
    lists.get(lists.size() - 1).add("Result");

    for (int i = 0; i < mathOperation.length(); i++) {
      if (Character.isLetter(mathOperation.charAt(i))) {
        int j = i;
        position.add(j);
        StringBuilder sbFields = new StringBuilder();
        while (Character.isLetter(mathOperation.charAt(j))) {
          sbFields.append(mathOperation.charAt(j));
          try {
            j++;
            mathOperation.charAt(j);
          } catch (StringIndexOutOfBoundsException e) {
            break;
          }
        }
        position.add(j);
        fields[countFields] = sbFields.toString();
        countFields++;
        i = j;
      } else if (Character.isDigit(mathOperation.charAt(i))) {
        int j = i;
        position.add(j);
        StringBuilder sbDigits = new StringBuilder();
        while (Character.isDigit(mathOperation.charAt(j))
            || mathOperation.charAt(j) == '.') {
          sbDigits.append(mathOperation.charAt(j));
          try {
            j++;
            mathOperation.charAt(j);
          } catch (StringIndexOutOfBoundsException e) {
            break;
          }
        }
        position.add(j);
        digits[countDigits] = sbDigits.toString();
        countDigits++;
        i = j;
      }
    }

    // apo thn database
    countFields = 0;
    countDigits = 0;
    while (countHasStopped < position.size()) {
      if (Character
          .isLetter(mathOperation.charAt(position.get(countHasStopped)))) {
        int at = whereIsField(lists, fields[countFields]);
        if ((at > -1 && at < lists.size()) && !wasInsideOnce) {
          wasInsideOnce = true;
          for (int a = 1; a < lists
              .get(whereIsField(lists, fields[countFields])).size(); a++) {
            lists.get(lists.size() - 1).add(lists.get(at).get(a));
          }
          countHasStopped = countHasStopped + 2;
          countFields++;
        } else if ((at > -1 && at < lists.size()) && wasInsideOnce) {
          switch (mathOperation.charAt(position.get(countHasStopped - 1))) {
          case '+':
            for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
              double temp1 = Double
                  .parseDouble((String) lists.get(lists.size() - 1).get(b));
              double temp2 = Double.parseDouble((String) lists.get(at).get(b));
              lists.get(lists.size() - 1).set(b, String.valueOf(temp1 + temp2));
            }
            break;
          case '-':
            for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
              double temp1 = Double
                  .parseDouble((String) lists.get(lists.size() - 1).get(b));
              double temp2 = Double.parseDouble((String) lists.get(at).get(b));
              lists.get(lists.size() - 1).set(b, String.valueOf(temp1 - temp2));
            }
            break;
          case '*':
            for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
              double temp1 = Double
                  .parseDouble((String) lists.get(lists.size() - 1).get(b));
              double temp2 = Double.parseDouble((String) lists.get(at).get(b));
              lists.get(lists.size() - 1).set(b, String.valueOf(temp1 * temp2));
            }
            break;
          case '/':
            for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
              double temp1 = Double
                  .parseDouble((String) lists.get(lists.size() - 1).get(b));
              double temp2 = Double.parseDouble((String) lists.get(at).get(b));
              lists.get(lists.size() - 1).set(b, String.valueOf(temp1 / temp2));
            }
            break;
          }
          countHasStopped = countHasStopped + 2;
          countFields++;
        } else {
          System.out.println("Not compatible data.");
          break;
        }
      } else if (Character
          .isDigit(mathOperation.charAt(position.get(countHasStopped)))) {
        if (!wasInsideOnce) {
          wasInsideOnce = true;
          for (int a = 1; a < lists.get(0).size(); a++) {
            lists.get(lists.size() - 1).add(digits[countDigits]);
          }
          countDigits++;
        } else if (wasInsideOnce) {
          switch (mathOperation.charAt(position.get(countHasStopped - 1))) {
          // to aaaaaaaaaaiiiiiiiiiiiiiiiiiiiiiiiiiiiii
          case '+':
            for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
              double temp1 = Double
                  .parseDouble((String) lists.get(lists.size() - 1).get(b));
              double temp2 = Double.parseDouble((String) digits[countDigits]);
              lists.get(lists.size() - 1).set(b, String.valueOf(temp1 + temp2));
            }
            break;
          case '-':
            for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
              double temp1 = Double
                  .parseDouble((String) lists.get(lists.size() - 1).get(b));
              double temp2 = Double.parseDouble((String) digits[countDigits]);
              lists.get(lists.size() - 1).set(b, String.valueOf(temp1 - temp2));
            }
            break;
          case '*':
            for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
              double temp1 = Double
                  .parseDouble((String) lists.get(lists.size() - 1).get(b));
              double temp2 = Double.parseDouble((String) digits[countDigits]);
              lists.get(lists.size() - 1).set(b, String.valueOf(temp1 * temp2));
            }
            break;
          case '/':
            for (int b = 1; b < lists.get(lists.size() - 1).size(); b++) {
              double temp1 = Double
                  .parseDouble((String) lists.get(lists.size() - 1).get(b));
              double temp2 = Double.parseDouble((String) digits[countDigits]);
              lists.get(lists.size() - 1).set(b, String.valueOf(temp1 / temp2));
            }
            break;
          default:
            break;
          }
          countHasStopped = countHasStopped + 2;
          countDigits++;
        } else {
          System.out.println("Not compatible data.");
          break;
        }
      }
    }
  }

  public static int whereIsField(ArrayList<ArrayList<Object>> lists,
      String given) {
    int i = 0;
    for (i = 0; i < lists.size(); i++) {
      if (lists.get(i).get(0).equals(given)) {
        break;
      }
    }
    return i;
  }

  public static boolean areAllNumbers(ArrayList<ArrayList<Object>> lists,
      int askedColumn) {
    boolean areAllNumbers = true;
    for (int i = 1; i < lists.get(askedColumn).size(); i++) {
      if (DatabaseProcessing
          .isNumber((String) lists.get(askedColumn).get(i)) == false) {
        System.out.println("ERROR! \nInvalid String at line " + i + ".");
        System.out.println("Please enter another value.");
        areAllNumbers = false;
      }
    }
    return areAllNumbers;
  }

}
