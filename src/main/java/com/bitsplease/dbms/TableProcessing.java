package com.bitsplease.dbms;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.bitsplease.utilities.Wrong;

/**
 * This class is used to process a table.
 * 
 * @author The BitsPlease Project
 *
 */
public class TableProcessing {

  private static Wrong error = new Wrong("buzzer");

  /**
   * Default Constructor is used for this class.
   */
  public TableProcessing() {

  }

  /**
   * Rotate the given table.
   * 
   * @param lists
   *          given table to rotate
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
   * Remove a line from the table processing. Dynamically asks the user which
   * line wants to remove.
   * 
   * @param lists
   *          to remove a line from it.
   */
  public static void removeLine(Table table) {
    rotate(table.getList());
    if (table.getList().size() == 1) {
      error.printWrong("The list has no rows");
      rotate(table.getList());
      return;
    }
    ImageIcon icon = new ImageIcon(ClassLoader.getSystemResource("remove.png"));
    String[] possibilities = new String[table.getList().size()];
    /*
     * It's possible to remove every row except the first one, which contains
     * the column names
     */
    for (int row = 0; row < possibilities.length - 1; row++) {
      possibilities[row] = ((row + 1) + ". "
          + table.getList().get(row + 1).get(0));
    }
    String choice = (String) JOptionPane.showInputDialog(StartMain.getWindow(),
        "Select a row to delete", "Turple Exterminator",
        JOptionPane.PLAIN_MESSAGE, icon, possibilities,
        possibilities[possibilities.length - 2]);
    if (choice == null) {
      rotate(table.getList());
      return;
    }
    int position = Character.getNumericValue(choice.charAt(0));
    table.getList().remove(position);

    // Rotate back to default
    rotate(table.getList());
  }

  /**
   * Similar to removeLine, removeColumn is responsible for removing a Column
   * from the table.
   * 
   * @param lists
   *          to remove a column from it.
   */
  public static void removeColumn(Table table) {
    if (table.getList().size() == 1) {
      error
          .printWrong("This is your last column! Try deleting the whole Table");
    }
    ImageIcon icon = new ImageIcon(
        ClassLoader.getSystemResource("pickAButton.jpg"));
    int toDelete = guiColumnChooser(table, icon, "Select a column to delete",
        "Column Exterminator");
    if (toDelete != -1) {
      table.getList().remove(toDelete);
    }
  }

  /**
   * Show a Dialog Box with the Table's columns as Options.
   * 
   * @param table
   *          to get columns from
   * @param icon
   *          to show
   * @param message
   *          to show
   * @param dialogName
   *          the title of the dialog
   * @return Column index
   */
  public static int guiColumnChooser(Table table, ImageIcon icon,
      String message, String dialogName) {
    if (table.getList().size() == 0) {
      error.printWrong("The list has no columns");
      return -1;
    }
    String[] possibilities = new String[table.getList().size()];
    for (int column = 0; column < possibilities.length; column++) {
      possibilities[column] = ((column + 1) + ". "
          + table.getList().get(column).get(0));
    }
    String choice = (String) JOptionPane.showInputDialog(StartMain.getWindow(),
        message, dialogName, JOptionPane.PLAIN_MESSAGE, icon, possibilities,
        possibilities[possibilities.length - 1]);
    if (choice != null) {
      int position = Character.getNumericValue(choice.charAt(0));
      return position - 1;
    }
    return -1;
  }

  /**
   * isNumber examines whether a string value is basically a number.
   * 
   * @param data
   *          String user wants to examine.
   * @return Returns true if the string given is a number, else returns false.
   */
  public static boolean isNumber(String data) {
    return (data.matches("[+-]?(\\d+|\\d*\\.?\\d+)"));
  }

  /**
   * Convert String into Integer.
   * 
   * @param code
   *          data needs to be converted.
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
   *          data needs to be converted.
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

  public static void swapLine(final ArrayList<ArrayList<String>> lists,
      final int a, final int b) {
    for (int i = 0; i < lists.size(); i++) {
      Collections.swap(lists.get(i), a, b);
    }
  }

  public static void printColumn(final ArrayList<String> arrayList) {
    for (int i = 0; i < arrayList.size(); i++) {
      System.out.printf("%s. %s\n", i + 1, arrayList.get(i));
    }
  }

  /**
   * Takes a list from a Table and an item, and searches the list for the item.
   * 
   * @param lists
   *          list from a Table
   * @param item
   *          Item to look for
   * @return list with positions inside the Table
   */
  public static ArrayList<String> find(final ArrayList<ArrayList<String>> lists,
      final String item) {
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

  /**
   * Take a table's column and sort's the whole table according to this column.
   * 
   * @param table
   *          Table to sort
   * @param column
   *          Desired Column
   * @param low
   *          Starting row
   * @param high
   *          Ending row
   */
  public static void sort(final Table table, final int column, final int low,
      final int high) {
    // This is a Quicksort implementation
    if (low < high) {

      String pivot = (String) table.getList().get(column).get(high);
      int i = low - 1;
      boolean allAreNumbers = TableArithmetics.areAllNumbers(table.getList(),
          column, false);
      for (int j = low; j < high; j++) {
        String current = (String) table.getList().get(column).get(j);
        if (allAreNumbers) {
          // Numeric Sort
          if (Double.parseDouble(pivot) >= Double.parseDouble(current)) {
            i++;
            swapLine(table.getList(), i, j);
          }
        } else {
          // Lexicographical Sort
          if (pivot.compareTo(current) >= 0) {
            i++;
            swapLine(table.getList(), i, j);
          }
        }
      }
      swapLine(table.getList(), i + 1, high);
      int pi = i + 1;
      sort(table, column, low, pi - 1);
      sort(table, column, pi + 1, high);
    }
  }

}
