package com.bitsplease.menus;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.bitsplease.dbms.BasicArithmeticOperations;
import com.bitsplease.dbms.StartMain;
import com.bitsplease.dbms.Table;
import com.bitsplease.dbms.TableArithmetics;
import com.bitsplease.dbms.TableProcessing;
import com.bitsplease.gui.ButtonList;

public class TableOperationMenu extends AbstractMenu {

  private Table table;

  public TableOperationMenu(TableMenu menu) {
    super(menu);
    options = new String[] { "Sum & Product & Average",
        "Operations Between Columns", "Back" };
    buttons = new ButtonList(options, this);
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
      ImageIcon icon = new ImageIcon(
          ClassLoader.getSystemResource("pickAButton.jpg"));
      int askedColumn = TableProcessing.GUIcolumnChooser(table, icon,
          "Select a Column", "Ultimate Column Picker");
      if (askedColumn == -1) {
        break;
      }
      BasicArithmeticOperations.sumOfAll(table.getList(), askedColumn);
      BasicArithmeticOperations.productOfAll(table.getList(), askedColumn);
      BasicArithmeticOperations.averageOfAll(table.getList(), askedColumn);
      BasicArithmeticOperations.displayMore(askedColumn);
      this.showButtons();
      break;
    case 2:
      String mathOperation = JOptionPane.showInputDialog(StartMain.getWindow(),
          "Give the mathematical operation \\n (e.g. (columnName + columnName) / 2 = newColumnName):");
      // inputChoice.nextLine();
      try {
        TableArithmetics.startingOperationsBetweenColumns(table.getList(),
            mathOperation);
      } catch (Exception e) {
        error.printWrong(
            "ERROR! Something has occured! Check the mathematical operation again!");
      }
      this.showButtons();
      ((TableMenu) calledFrom).updateGUI();
      break;
    case 3:
      calledFrom.run();
      break;
    }

  }
}
