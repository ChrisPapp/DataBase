package com.bitsplease.dbms;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import com.bitsplease.gui.GuiTable;

public class Table implements Serializable {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;
  // Fill in our Table with fields and table
  private String name;
  private boolean constructionCompleted;

  private ArrayList<ArrayList<String>> list = new ArrayList<ArrayList<String>>();

  public Table() {
    setConstructionCompleted(false);
    inputName();
    inputFields();
  }

  public Table(String name, ArrayList<ArrayList<String>> list) {
    super();
    this.name = name;
    this.list = list;
  }

  private void inputName() {
    String tableName = JOptionPane.showInputDialog(StartMain.getWindow(),
        "Enter Table Name");
    // If the user clicked on cancel or closed the dialog
    if (tableName == null)
      return;
    setName(tableName);
  }

  public void inputFields() {
    if (getName() == null)
      return;
    String category;
    do {
      category = JOptionPane.showInputDialog(StartMain.getWindow(),
          "PLEASE ENTER THE FIELD #" + (list.size() + 1) + " OF YOUR DATABASE");
      // If category is not "exit"
      if (category == null) {
        break;
      }
      // Add a new List
      list.add(new ArrayList<String>());
      // Add category to the last position
      list.get(list.size() - 1).add(category);
    } while (true);
    constructionCompleted = !list.isEmpty();
  }

  @Override
  public boolean equals(Object o) {
    if (o == this) { // Compare hash codes
      return true;
    }
    // If given Object is not a Table return false
    if (!(o instanceof Table)) {
      return false;
    }
    // If I am still here, that means o is a Table
    Table table = (Table) o;
    // I hereby declare that two Tables are equal
    // only and only if their names are equal
    return table.getName().equals(this.getName());

  }

  public int hashCode() {
    assert false : "hashCode not designed";
    return 42;
  }

  public void print() {
    if (Printer.isMarginVariable()) {
      Printer.displayData(this.getList());
    } else {
      Printer.printShortcut(this.getList());
    }
  }

  public ArrayList<ArrayList<String>> getList() {
    return list;
  }

  public void setList(ArrayList<ArrayList<String>> list) {
    this.list = list;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public boolean isConstructionCompleted() {
    return constructionCompleted;
  }

  public void setConstructionCompleted(boolean constructionCompleted) {
    this.constructionCompleted = constructionCompleted;
  }
}
