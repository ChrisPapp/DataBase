package com.bitsplease.gui;

import java.awt.Dimension;
import java.util.ArrayList;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import com.bitsplease.dbms.Table;
import com.bitsplease.dbms.TableProcessing;

@SuppressWarnings("serial")
public class GuiTable extends JPanel {
  private JTable jtable;
  private Table table;
  private DefaultTableModel model;

  public GuiTable(Table table) {
    this.table = table;
    TableProcessing.rotate(table.getList());
    // columnNames are on the first list of the table now
    String[] columnNames = new String[table.getList().get(0).size()];
    for (int i = 0; i < columnNames.length; i++) {
      columnNames[i] = table.getList().get(0).get(i);
    }
    // Data is stored at the tables second list and after
    String data[][] = new String[table.getList().size()
        - 1][columnNames.length];
    for (int i = 0; i < table.getList().size() - 1; i++) {
      for (int j = 0; j < columnNames.length; j++) {
        data[i][j] = table.getList().get(i + 1).get(j);
      }
    }
    // Rotate table back to normal
    TableProcessing.rotate(table.getList());
    DefaultTableModel model = new DefaultTableModel(data, columnNames);
    jtable = new JTable(model);
    JScrollPane scrollPane = new JScrollPane(jtable);
    jtable.setPreferredScrollableViewportSize(new Dimension(500, 70));
    jtable.setFillsViewportHeight(true);
    add(scrollPane);
    // this.add(jtable);
  }

  /**
   * Updates the Table's list values, after the user is done editing the
   * GuiTable
   */
  public void updateTableValues() {
    model = (DefaultTableModel) jtable.getModel();
    table.getList().clear();
    // First add column Names
    for (int column = 0; column < model.getColumnCount(); column++) {
      table.getList().add(new ArrayList<String>());
      table.getList().get(column).add(model.getColumnName(column));
    }
    // Then add current values
    for (int column = 0; column < model.getColumnCount(); column++) {
      for (int row = 0; row < model.getRowCount(); row++) {
        table.getList().get(column).add((String) model.getValueAt(row, column));
      }
    }
  }

  public void addRow() {
    for (int column = 0; column < table.getList().size(); column++) {
      table.getList().get(column).add("Enter new data here");
    }
  }

}
