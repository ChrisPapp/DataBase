package com.bitsplease.menus;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Scanner;

import com.bitsplease.dbms.DataProcessingMore;
import com.bitsplease.dbms.Database;
import com.bitsplease.dbms.DatabaseProcessing;

public class MainMenu extends AbstractMenu {
  private static Scanner inputOperation = new Scanner(System.in);

  public MainMenu(Database data, boolean showAgain) {
    super(data, showAgain);
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
      DatabaseProcessing.displayData(data.getList(), press);
      break;
    case 2:
      data.inputData();
      break;
    case 3:
      System.out.println("WHICH CELL DO YOU WANT TO CHANGE? (Type position)");
      Scanner input = new Scanner(System.in);
      String cell = input.nextLine();
      DatabaseProcessing.changeData(data.getList(), cell);
      break;
    case 4:
      DatabaseProcessing.removeLine(data.getList());
      break;
    case 5:
      DatabaseProcessing.removeColumn(data.getList());
      break;
    case 6:
      System.out.println(" SELECT COLUMN: ");
      Scanner inputColumn = new Scanner(System.in);
      int askedColumn = DatabaseProcessing.codeToNum(inputColumn.nextLine());
      if (DataProcessingMore.areAllNumbers(data.getList(), askedColumn)) {
        DataProcessingMore.sumOfAll(data.getList(), askedColumn);
        DataProcessingMore.productOfAll(data.getList(), askedColumn);
        DataProcessingMore.averageOfAll(data.getList(), askedColumn);
        DataProcessingMore.displayMore();
      } else {
        System.out.println(
            "Check your data and change the ones that are not numbers!");
      }
      break;
    case 7:
      System.out.println(" GIVE OPERATION ");
      String askedOperation = inputOperation.nextLine();
      DataProcessingMore.extraordinaryOption(data.getList(), askedOperation);
      break;
    case 8:
      search();
      break;
    case 9:
      sort();
      break;
    default:
      break;
    }

  }

  private void sort() {
    System.out.println("Which field (1 - " + (data.getList().size()) + ")");
    DatabaseProcessing.rotate(data.getList()); // Line Mode
    // Print fields as a column
    DatabaseProcessing.printColumn(data.getList().get(0));
    DatabaseProcessing.rotate(data.getList()); // Column Mode
    Scanner input = new Scanner(System.in);
    int column = input.nextInt() - 1;
    DatabaseProcessing.sort(data.getList(), column, 1,
        data.getList().get(column).size() - 1);
  }

  private void search() {
    System.out.println("What are you looking for?");
    Scanner input = new Scanner(System.in);
    String item = input.nextLine();
    ArrayList<String> cells = new ArrayList<String>(
        DatabaseProcessing.find(data.getList(), item));
    if (cells.size() == 0) {
      System.out.println("The item was not found");
    } else {
      System.out.println("The item was found in these cells:");
      DatabaseProcessing.printColumn(cells);
    }
    // input.close();
  }

  @Override
  protected final void printMenu() {
    System.out.println("\n*** DATABASE MENU ***\n" + "  1. DISPLAY DATA \n"
        + "  2. INPUT DATA \n" + "  3. CHANGE DATA\n" + "  4. REMOVE LINE\n"
        + "  5. REMOVE COLUMN\n" + "  6. MORE CALCULATIONS \n"
        + "  7. OPERATIONS BETWEEN COLUMNS \n" + "  8. SEARCH \n"
        + "  9. SORT \n" + "  SELECT AN OPTION: ");
  }
}
