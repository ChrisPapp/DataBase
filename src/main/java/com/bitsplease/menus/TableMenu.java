package com.bitsplease.menus;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import com.bitsplease.dbms.StartMain;
import com.bitsplease.dbms.Table;
import com.bitsplease.dbms.TableProcessing;
import com.bitsplease.gui.ButtonList;
import com.bitsplease.gui.GuiTable;
import com.bitsplease.utilities.MemoryCard;

public class TableMenu extends AbstractMenu {
  private Table table;
  private GuiTable guitable;
  private TableOperationMenu tableOperationMenu;

  public TableMenu(TableChoiceMenu menu) {
    super(menu);
    tableOperationMenu = new TableOperationMenu(this);
    options = new String[] { "Add Row", "Remove Row", "Remove Column",
        "Operations Between Columns", "Search", "Sort", "Save", "Back" };
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
      guitable.addRow();
      updateGUI();
      break;
    case 2:
      TableProcessing.removeLine(table);
      updateGUI();
      break;
    case 3:
      TableProcessing.removeColumn(table);
      updateGUI();
      break;
    case 4:
      tableOperationMenu.setTable(table);
      tableOperationMenu.run();
      updateGUI();
      break;
    case 5:
      search();
      break;
    case 6:
      sort();
      updateGUI();
      break;
    case 7:
      MemoryCard.save(table);
      break;
    case 8:
      guitable.updateTableValues();
      StartMain.getWindow().remove(this.guitable);
      StartMain.getWindow().getContentPane().validate();
      StartMain.getWindow().getContentPane().repaint();
      calledFrom.run();
      break;
    }
    if (getChoice() != 8) {
      guitable.updateTableValues();
      updateGUI();
      if (getChoice() != 4) {
        this.showButtons();
      }
    }

  }

  private void sort() {
    ImageIcon icon = new ImageIcon(
        ClassLoader.getSystemResource("bubbles.jpg"));
    int column = TableProcessing.guiColumnChooser(table, icon,
        "Sort according to which column?",
        "The Grand Sortifier requests your choice");
    TableProcessing.sort(table, column, 1,
        table.getList().get(column).size() - 1);
  }

  private void search() {
    String item = JOptionPane.showInputDialog(StartMain.getWindow(),
        "What are you looking for?");
    ArrayList<String> cells = new ArrayList<String>(
        TableProcessing.find(table.getList(), item));
    if (cells.size() == 0) {
      error.printWrong("The item was not found");
    } else {
      JOptionPane.showMessageDialog(StartMain.getWindow(),
          "The item was found in these cells:");
      TableProcessing.printColumn(cells);
    }
    // input.close();
  }

  public void updateGUI() {
    StartMain.getWindow().remove(this.guitable);
    guitable = new GuiTable(this.table);
    StartMain.getWindow().add(this.guitable);
    StartMain.getWindow().getContentPane().validate();
    StartMain.getWindow().getContentPane().repaint();
  }

}
