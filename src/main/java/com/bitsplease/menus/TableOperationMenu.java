package com.bitsplease.menus;

import com.bitsplease.dbms.BasicArithmeticOperations;
import com.bitsplease.dbms.Table;
import com.bitsplease.dbms.TableArithmetics;
import com.bitsplease.gui.ButtonList;

public class TableOperationMenu extends AbstractMenu {

  private Table table;

  public TableOperationMenu(TableMenu menu) {
    super(menu);
    options = new String[] { "Sum & Product & Average",
        "Operations Between Columns", "Back" };
    buttons = new ButtonList(options,this);
  }

  public Table getTable() {
    return table;
  }

  public void setTable(Table table) {
    this.table = table;
  }

  @Override
  public void performAction() {
    switch (getChoice()) {
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
      // inputChoice.nextLine();
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
      calledFrom.run();
      break;
    }

  }
}
