package com.bitsplease.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.bitsplease.dbms.StartMain;
import com.bitsplease.dbms.Table;
import com.bitsplease.dbms.TableArithmetics;
import com.bitsplease.dbms.TableProcessing;
import com.bitsplease.gui.ButtonList;
import com.bitsplease.gui.GuiTable;
import com.bitsplease.utilities.MemoryCard;

public class TableMenu extends AbstractMenu {
  private static Scanner inputOperation = new Scanner(System.in);
  private Table table;
  private GuiTable guitable;

  public TableMenu(TableChoiceMenu menu) {
    super(menu);
    options = new String[] { "Display Data", "Add Row", "Change Data",
        "Remove Row", "Remove Column", "Operations Between Columns", "Search",
        "Sort", "Save", "Back" };
    buttons = new ButtonList(options, this);

  }

  public Table getTable() {
    return table;
  }

  public void runWith(Table table) {
    // Menu will run with this table
    this.table = table;
    this.guitable = new GuiTable(table);
    StartMain.getWindow().add(guitable);
    run();
  }

  public void performAction() {
    guitable.updateTableValues();
    switch (getChoice()) {
    case 1:
      table.print();
      break;
    case 2:
      guitable.addRow();
      updateGUI();
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
      System.out.print(" GIVE OPERATION: ");
      String askedOperation = inputOperation.nextLine();
      TableArithmetics.startingOperationsBetweenColumns(table.getList(),
          askedOperation);
      break;
    case 7:
      search();
      break;
    case 8:
      sort();
      break;
    case 9:
      MemoryCard.save(table);
      break;
    case 10:
      guitable.updateTableValues();
      StartMain.getWindow().remove(this.guitable);
      StartMain.getWindow().getContentPane().validate();
      StartMain.getWindow().getContentPane().repaint();
      calledFrom.run();
      break;
    default:
      error.printWrong("Out of Bounds");
      break;
    }
    if (getChoice() != 10) {
      guitable.updateTableValues();
      updateGUI();
      this.showButtons();
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

  private void updateGUI() {
    StartMain.getWindow().remove(this.guitable);
    guitable = new GuiTable(this.table);
    StartMain.getWindow().add(this.guitable);
    StartMain.getWindow().getContentPane().validate();
  }

}
