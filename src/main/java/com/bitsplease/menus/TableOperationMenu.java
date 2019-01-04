package com.bitsplease.menus;

import com.bitsplease.dbms.BasicArithmeticOperations;
import com.bitsplease.dbms.Table;
import com.bitsplease.dbms.TableArithmetics;

public class TableOperationMenu extends AbstractMenu {

  private Table table;

  public TableOperationMenu(Table table) {
    super();
    this.table = table;
  }

  @Override
  protected void performAction() {
    readChoice();
    switch (choice) {
    case 1:
      System.out.print("GIVE THE COLUMN NUMBER:");
      int askedColumn = -1;
      try {
        askedColumn = inputChoice.nextInt() - 1;
      } catch (Exception e) {
        error.printWrong("Out of bounds");
      }
      BasicArithmeticOperations.sumOfAll(table.getList(), askedColumn);
      BasicArithmeticOperations.productOfAll(table.getList(), askedColumn);
      BasicArithmeticOperations.averageOfAll(table.getList(), askedColumn);
      BasicArithmeticOperations.displayMore(askedColumn);
      break;
    case 2:
      System.out.print(
          "GIVE THE MATHEMATICAL OPERATION \n (e.g. (columnName + columnName) / 2 = newColumnName):");
      String mathOperation;
      //inputChoice.nextLine();
      mathOperation = inputChoice.nextLine();
      try {
        TableArithmetics.startingOperationsBetweenColumns(table.getList(),
            mathOperation);
      } catch (Exception e) {
        error.printWrong(
            "ERROR! Something has occured! Check the mathematical operation again!");
      }
      break;
    case 3:
      showAgain = false;
      break;
    default:
      error.printWrong("Out of bounds");
    }

  }

  @Override
  protected void printMenu() {
    System.out.println("\nMATHEMATICAL OPERATION MENU \n"
        + "1. SUM & PRODUCT & AVERAGE \n" + "2. OPERATIONS BETWEEN COLUMNS" + "3.BACK");
  }
}
