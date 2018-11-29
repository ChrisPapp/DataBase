package com.bitsplease.menus;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Scanner;

import com.bitsplease.dbms.TableArithmetics;
import com.bitsplease.dbms.Table;
import com.bitsplease.dbms.TableProcessing;

public class TableMenu extends AbstractMenu {
  private static Scanner inputOperation = new Scanner(System.in);
  private Table table;

  public Table getTable() {
    return table;
  }

  public void runWith(Table table) {
    // Menu will run with this table
    this.table = table;
    // Make showAgain true again (bug fix)
    showAgain = true;
    run();
  }

  public void performAction() {
    int choice = 0;
    boolean flag = true;
    char ch = inputChoice.next().charAt(0);
    do {
      if (!(Character.isDigit(ch))) {
        System.out.println("\n WRONG ");
        Toolkit.getDefaultToolkit().beep();
        printMenu();
        ch = inputChoice.next().charAt(0);
      } else {
        choice = Character.getNumericValue(ch);
        if ((choice < 0) || (choice > 9)) {
          System.out.println("\n WRONG ");
          Toolkit.getDefaultToolkit().beep();
          printMenu();
          ch = inputChoice.next().charAt(0);
        } else {
          flag = false;
        }
      }
    } while (flag);
    switch (choice) {
    case 1:
      Scanner obj = new Scanner(System.in);
      System.out.println(
          "IF YOU WANT TO SEE WHOLE DATA NAMES PRESS 1 OR ELSE PRESS 2");
      int press = obj.nextInt();
      TableProcessing.displayData(table.getList(), press);
      break;
    case 2:
      table.inputData();
      break;
    case 3:
      System.out.println("WHICH CELL DO YOU WANT TO CHANGE? (Type position)");
      Scanner input = new Scanner(System.in);
      String cell = input.nextLine();
      TableProcessing.changeData(table.getList(), cell);
      break;
    case 4:
      TableProcessing.removeLine(table.getList());
      break;
    case 5:
      TableProcessing.removeColumn(table.getList());
      break;
    case 6:
      System.out.println(" SELECT COLUMN: ");
      Scanner inputColumn = new Scanner(System.in);
      int askedColumn = TableProcessing.codeToNum(inputColumn.nextLine());
      if (TableArithmetics.areAllNumbers(table.getList(), askedColumn)) {
        TableArithmetics.sumOfAll(table.getList(), askedColumn);
        TableArithmetics.productOfAll(table.getList(), askedColumn);
        TableArithmetics.averageOfAll(table.getList(), askedColumn);
        TableArithmetics.displayMore();
      } else {
        System.out.println(
            "Check your table and change the ones that are not numbers!");
      }
      break;
    case 7:
      System.out.println(" GIVE OPERATION ");
      String askedOperation = inputOperation.nextLine();
      TableArithmetics.extraordinaryOption(table.getList(), askedOperation);
      break;
    case 8:
      search();
      break;
    case 9:
      sort();
      break;
    case 0:
      showAgain = false;
      break;
    default:
      break;
    }

  }

  private void sort() {
    System.out.println("Which field (1 - " + (table.getList().size()) + ")");
    TableProcessing.rotate(table.getList()); // Line Mode
    // Print fields as a column
    TableProcessing.printColumn(table.getList().get(0));
    TableProcessing.rotate(table.getList()); // Column Mode
    Scanner input = new Scanner(System.in);
    int column = input.nextInt() - 1;
    TableProcessing.sort(table.getList(), column, 1,
        table.getList().get(column).size() - 1);
  }

  private void search() {
    System.out.println("What are you looking for?");
    Scanner input = new Scanner(System.in);
    String item = input.nextLine();
    ArrayList<String> cells = new ArrayList<String>(
        TableProcessing.find(table.getList(), item));
    if (cells.size() == 0) {
      System.out.println("The item was not found");
    } else {
      System.out.println("The item was found in these cells:");
      TableProcessing.printColumn(cells);
    }
    // input.close();
  }

  @Override
  protected final void printMenu() {
    System.out.println("\n*** DATABASE MENU ***\n" + "  1. DISPLAY DATA \n"
        + "  2. INPUT DATA \n" + "  3. CHANGE DATA\n" + "  4. REMOVE LINE\n"
        + "  5. REMOVE COLUMN\n" + "  6. MORE CALCULATIONS \n"
        + "  7. OPERATIONS BETWEEN COLUMNS \n" + "  8. SEARCH \n"
        + "  9. SORT \n" + "  0. BACK \n" + "  SELECT AN OPTION: ");
  }
}
